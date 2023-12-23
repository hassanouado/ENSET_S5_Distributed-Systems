package ma.enset.BillingService.feing;
import ma.enset.BillingService.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "PRODUCT-SERVICE")
public interface InventoryServiceClient {
    @GetMapping(path = "/products")
    PagedModel<Product> findAll();
    @GetMapping(path = "/products/{id}")
    Product findProductById(@RequestParam(value = "id") Long id);
}
