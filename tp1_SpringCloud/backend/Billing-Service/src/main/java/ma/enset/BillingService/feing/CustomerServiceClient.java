package ma.enset.BillingService.feing;

import ma.enset.BillingService.model.Customer;
import ma.enset.BillingService.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerServiceClient {
    @GetMapping(path = "/customers/{id}")
    Customer findCustomerById(@RequestParam(value = "id") Long id);
}
