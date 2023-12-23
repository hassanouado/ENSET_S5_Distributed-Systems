package ma.enset.BillingService;

import ma.enset.BillingService.entities.Bill;
import ma.enset.BillingService.entities.ProductItem;
import ma.enset.BillingService.feing.CustomerServiceClient;
import ma.enset.BillingService.feing.InventoryServiceClient;
import ma.enset.BillingService.model.Customer;
import ma.enset.BillingService.model.Product;
import ma.enset.BillingService.repository.BillRepository;
import ma.enset.BillingService.repository.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
   CommandLineRunner start(BillRepository billRepository,
						   ProductItemRepository productItemRepositpry,
						   CustomerServiceClient customerClientRest,
						   InventoryServiceClient productItemClientRest){
		return args -> {
			Customer customer = customerClientRest.findCustomerById(1L);
			Bill bill = billRepository.save(new Bill(null,new Date(),null,customer.getId(),null));
			PagedModel<Product> pagedModel = productItemClientRest.findAll();
			pagedModel.forEach(product -> {
				ProductItem productItem = new ProductItem();
				productItem.setPrice(product.getPrice());
				productItem.setQuantity(new Random().nextInt(100));
				productItem.setBill(bill);
				productItem.setProductID(product.getId());
				productItemRepositpry.save(productItem);

			});



		};
   }
}
