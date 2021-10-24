package com.vezolve.invoiceMS.repository;

import com.vezolve.invoiceMS.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface invoiceRepo extends JpaRepository<Invoice, Long> {

}
