package ma.enset.customerservice.repository;

import ma.enset.customerservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
