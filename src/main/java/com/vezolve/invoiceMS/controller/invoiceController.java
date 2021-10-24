package com.vezolve.invoiceMS.controller;

import com.vezolve.invoiceMS.exception.ResourceNotFoundException;
import com.vezolve.invoiceMS.model.Invoice;
import com.vezolve.invoiceMS.repository.invoiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class invoiceController {

    @Autowired
    invoiceRepo invoiceRepo;

    // Get All invoices
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/invoice")
    public List<Invoice> getAllInvoice() {
        return invoiceRepo.findAll();
    }

    // Create a new invoice
    @PostMapping("/invoice")
    public Invoice createInvoice(@Valid @RequestBody Invoice invoice) {
        return invoiceRepo.save(invoice);
    }

    // Get a Single invoice
    @GetMapping("/invoice/{id}")
    public Invoice getInvoiceById(@PathVariable(value = "id") Long invoiceId) {
        return invoiceRepo.findById(invoiceId)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice", "id", invoiceId));
    }

    // Update an invoice
    @PutMapping("/invoice/{id}")
    public Invoice updateInvoice(@PathVariable(value = "id") Long invoiceId,
                           @Valid @RequestBody Invoice invoiceDetails) {

        Invoice invoice = invoiceRepo.findById(invoiceId)
                .orElseThrow(() -> new ResourceNotFoundException("invoice", "id", invoiceId));

        invoice.setGrossAmount(invoiceDetails.getGrossAmount());
        invoice.setCustomerName(invoiceDetails.getCustomerName());
        invoice.setDiscount(invoiceDetails.getDiscount());
        invoice.setNetAmount(invoiceDetails.getNetAmount());
        invoice.setStatus(invoiceDetails.getStatus());

        Invoice updatedInvoice = invoiceRepo.save(invoice);
        return updatedInvoice;
    }

    // Delete an Invoice
    @DeleteMapping("/invoice/{id}")
    public ResponseEntity<?> deleteInvoice(@PathVariable(value = "id") Long invoiceId) {
        Invoice invoice = invoiceRepo.findById(invoiceId)
                .orElseThrow(() -> new ResourceNotFoundException("invoice", "id", invoiceId));

        invoiceRepo.delete(invoice);

        return ResponseEntity.ok().build();
    }
}
