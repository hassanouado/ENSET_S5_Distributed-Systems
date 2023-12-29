package com.Ouadouch.billingservice.repositories;

import com.Ouadouch.billingservice.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import java.util.List;

@RepositoryRestResource
public interface BillRepository extends JpaRepository<Bill,Long> {

    List<Bill> findByCustomerId(Long customerId);
}
