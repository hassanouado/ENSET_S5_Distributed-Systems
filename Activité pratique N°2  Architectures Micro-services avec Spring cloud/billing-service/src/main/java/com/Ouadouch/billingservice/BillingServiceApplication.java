package com.Ouadouch.billingservice;

import com.Ouadouch.billingservice.feign.CustomerRestClient;
import com.Ouadouch.billingservice.feign.ProductRestClient;
import com.Ouadouch.billingservice.model.Customer;
import com.Ouadouch.billingservice.model.Product;
import com.Ouadouch.billingservice.repositories.BillRepository;
import com.Ouadouch.billingservice.repositories.ProductItemRepository;
import com.Ouadouch.billingservice.entities.Bill;
import com.Ouadouch.billingservice.entities.ProductItem;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(
            BillRepository billRepository,
            ProductItemRepository productItemRepository,
            CustomerRestClient customerRestClient,
            ProductRestClient productRestClient
    ){
        return args -> {

            Customer customer = customerRestClient.getCustomerById(2L);
            Bill bill1=billRepository.save(
                    new Bill(null,new Date(),null,customer.getId(),null)
            );
            PagedModel<Product> productPagedModel= productRestClient.pageProducts();
            productPagedModel.forEach(p->{
                ProductItem productItem=new ProductItem();
//                productItem.setProductName(p.getName());
                productItem.setPrice(p.getPrice());
                productItem.setProductId(p.getId());
                productItem.setQuantity(new Random().nextInt(1,100));
                productItem.setBill(bill1);
                productItemRepository.save(productItem);
            });
//            System.out.println(customer.toString());
        };
    }

}
