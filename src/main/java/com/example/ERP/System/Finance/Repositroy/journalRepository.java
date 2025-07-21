package com.example.ERP.System.Finance.Repositroy;

import com.example.ERP.System.Finance.JOURNAL.journalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface journalRepository extends JpaRepository<journalEntity,Long> {
}
