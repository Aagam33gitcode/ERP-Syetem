package com.example.ERP.System.Finance.Controller;

import com.example.ERP.System.Finance.JOURNAL.journalDTO;
import com.example.ERP.System.Finance.Service.journalService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/journal")
public class journalController {
    private final journalService service;

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN', 'ACCOUNTANT')")

    public ResponseEntity<journalDTO> createEntry(@Valid @RequestBody journalDTO dto) {
        journalDTO created = service.createEntry(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    @GetMapping("/getAll")
    @PreAuthorize("hasAnyRole('ADMIN', 'ACCOUNTANT')")

    public ResponseEntity<List<journalDTO>> getAllEntry(){
        List<journalDTO> dto=service.getAllEntry();
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @GetMapping("/{journalId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'ACCOUNTANT')")

    public ResponseEntity<journalDTO> getEntryById(@PathVariable("journalId") Long journalId) {
        journalDTO entry = service.getEntryById(journalId);
        return new ResponseEntity<>(entry,HttpStatus.OK);
    }

    @PutMapping("/{journalId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'ACCOUNTANT')")
    public ResponseEntity<journalDTO> updateEntry(@PathVariable("journalId") Long journalId, @RequestBody journalDTO dto) {
        journalDTO updated = service.updateEntry(journalId, dto);
        return new ResponseEntity<>(updated,HttpStatus.OK);
    }
    @DeleteMapping("/{journalId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteEntry(@PathVariable("journalId") Long journalId) {
        return new ResponseEntity<>(service.deleteEntity(journalId),HttpStatus.GONE);
    }

//    @GetMapping("/employee/{empId}")
//    public ResponseEntity<journalDTO> getEntriesByEmployee(@PathVariable("empId") Long empId) {
//        journalDTO dto=service.getEntriesByEmployee(empId);
//        return new ResponseEntity<>(dto,HttpStatus.FOUND);
//    }


}
