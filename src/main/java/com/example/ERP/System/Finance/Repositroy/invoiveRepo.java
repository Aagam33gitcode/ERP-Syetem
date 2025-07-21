package com.example.ERP.System.Finance.Repositroy;

import com.example.ERP.System.Finance.INVOICE.invoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface invoiveRepo extends JpaRepository<invoiceEntity,Long> {
}
