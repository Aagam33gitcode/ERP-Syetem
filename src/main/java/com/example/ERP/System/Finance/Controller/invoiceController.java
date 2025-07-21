package com.example.ERP.System.Finance.Controller;

import com.example.ERP.System.Finance.INVOICE.invoiceDTO;
import com.example.ERP.System.Finance.Service.invoiceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/invoice")
public class invoiceController {
    private final invoiceService service;
invoiceController(invoiceService service) {
this.service=service;
}
    
    @PostMapping
    public ResponseEntity<invoiceDTO> createInvoice(@Valid @RequestBody invoiceDTO invoiceDTO) {
        invoiceDTO createdInvoice = service.createInvoice(invoiceDTO);
        return new ResponseEntity<>(createdInvoice, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'ACCOUNTANT')")

    public ResponseEntity<List<invoiceDTO>> getAllInvoices() {
        List<invoiceDTO> invoice=service.getAllInvoices();
return new ResponseEntity<>(invoice,HttpStatus.OK);
}

    @GetMapping("/{departmentId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'ACCOUNTANT')")
    public ResponseEntity<invoiceDTO> getInvoiceById(@PathVariable("departmentId") Long departmentId) {
        invoiceDTO invoice = service.getInvoiceById(departmentId);
        return ResponseEntity.ok(invoice);
    }

    @DeleteMapping("/{invoiceId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteInvoice(@PathVariable Long invoiceId) {
        return new ResponseEntity<>(service.deleteInvoice(invoiceId),HttpStatus.OK);
    }

    @PutMapping("/{invoiceId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'ACCOUNTANT')")

    public ResponseEntity<invoiceDTO> updateInvoice(@PathVariable Long invoiceId, @RequestBody invoiceDTO dto) {
        invoiceDTO updated = service.updateInvoice(invoiceId, dto);
        return new ResponseEntity<>(updated,HttpStatus.OK);
    }

}
