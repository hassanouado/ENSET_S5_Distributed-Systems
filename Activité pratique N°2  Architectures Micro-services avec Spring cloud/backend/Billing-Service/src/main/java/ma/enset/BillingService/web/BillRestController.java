package ma.enset.BillingService.web;

import ma.enset.BillingService.entities.Bill;
import ma.enset.BillingService.feing.CustomerServiceClient;
import ma.enset.BillingService.feing.InventoryServiceClient;
import ma.enset.BillingService.model.Customer;
import ma.enset.BillingService.model.Product;
import ma.enset.BillingService.repository.BillRepository;
import ma.enset.BillingService.repository.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillRestController {
    @Autowired  private BillRepository billRepository;
    @Autowired private ProductItemRepository productItemRepository;
    @Autowired private CustomerServiceClient customerServiceClient;
    @Autowired private InventoryServiceClient inventoryServiceClient;
    @GetMapping(path = "fullBill/{id}")
    Bill getBill(@PathVariable(name="id") Long id){
        Bill bill=billRepository.findById(id).get();
        Customer customer = customerServiceClient.findCustomerById(bill.getCustomerID());
        bill.setCustomer(customer);
        bill.getProductItems().forEach(productItem -> {
            Product product = inventoryServiceClient.findProductById(productItem.getId());
            productItem.setProductName(product.getName());
        });
        return bill; }
}
