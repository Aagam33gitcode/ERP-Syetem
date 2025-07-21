package com.example.ERP.System.Finance.Service;

import com.example.ERP.System.Finance.JOURNAL.journalDTO;
import com.example.ERP.System.Finance.JOURNAL.journalEntity;
import com.example.ERP.System.Finance.Repositroy.journalRepository;
import com.example.ERP.System.HR.Department.Model.departmentEntity;
import com.example.ERP.System.HR.Department.Repository.departmentRepo;
import com.example.ERP.System.HR.Employee.Model.employeeEntity;
import com.example.ERP.System.HR.Employee.Repository.employeeRepository;
import com.example.ERP.System.HR.ExceptionHandling.ResourceNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class journalService {

    private final journalRepository journalRepo;
    private final employeeRepository employeeRepo;
    private final departmentRepo departmentRepo;
    private final ModelMapper mapper;

//    public journalService(journalRepository journalRepo, employeeRepository employeeRepo, departmentRepo departmentRepo, ModelMapper mapper) {
//        this.journalRepo = journalRepo;
//        this.employeeRepo = employeeRepo;
//        this.departmentRepo = departmentRepo;
//        this.mapper = mapper;
//    }

    public List<journalDTO> getAllEntry() {
        return journalRepo.findAll().stream()
                .map(e -> {
                    journalDTO dto = mapper.map(e, journalDTO.class);
                    dto.setEmployeeId(e.getEmployee().getEmpId());
                    dto.setDepartmentId(e.getDepartment().getDepartmentId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public journalDTO createEntry(@Valid journalDTO dto) {
        journalEntity entity = mapper.map(dto, journalEntity.class);
        entity.setDataentryTime(LocalDateTime.now());

        if (dto.getEmployeeId() != null) {
            employeeEntity emp = employeeRepo.findById(dto.getEmployeeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Employee not found" + dto.getEmployeeId()));
            entity.setEmployee(emp);
        }

        if (dto.getDepartmentId() != null) {
            departmentEntity dept = departmentRepo.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
            entity.setDepartment(dept);
        }

        journalEntity saved = journalRepo.save(entity);
        journalDTO responseDto = mapper.map(saved, journalDTO.class);
        responseDto.setEmployeeId(saved.getEmployee().getEmpId());
        responseDto.setDepartmentId(saved.getDepartment().getDepartmentId());
        return responseDto;
    }

    public journalDTO getEntryById(Long journalId) {
        journalEntity entry = journalRepo.findById(journalId).orElseThrow(() -> new ResourceNotFoundException("Journal not found"));
        journalDTO dto = mapper.map(entry, journalDTO.class);
        dto.setEmployeeId(entry.getEmployee().getEmpId());
        dto.setDepartmentId(entry.getDepartment().getDepartmentId());
        return dto;
    }

    public journalDTO updateEntry(Long journalId, journalDTO dto) {
        journalEntity existing = journalRepo.findById(journalId)
                .orElseThrow(() -> new ResourceNotFoundException("Journal entry not found with ID: " + journalId));

        // 2. Update only non-null fields (partial or full update)
        if (dto.getDescription() != null) {
            existing.setDescription(dto.getDescription());
        }

        if (dto.getDebit() != null) {
            existing.setDebitAmount(dto.getDebit());
        }

        if (dto.getCredit() != null) {
            existing.setCreditAmount(dto.getCredit());
        }

        if (dto.getEntryTimestamp() != null) {
            existing.setDataentryTime(dto.getEntryTimestamp());
        }

        if (dto.getEmployeeId() != null) {
            employeeEntity emp = employeeRepo.findById(dto.getEmployeeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + dto.getEmployeeId()));
            existing.setEmployee(emp);
        }

        if (dto.getDepartmentId() != null) {
            departmentEntity dept = departmentRepo.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + dto.getDepartmentId()));
            existing.setDepartment(dept);
        }

        // 3. Save updated entity
        journalEntity updated = journalRepo.save(existing);

        // 4. Map back to DTO and enrich with employee/department names
        journalDTO response = mapper.map(updated, journalDTO.class);
        response.setEmployeeId(updated.getEmployee().getEmpId());
        response.setEmployeeName(updated.getEmployee().getFirstName());
        response.setDepartmentId(updated.getDepartment().getDepartmentId());
        response.setDepartmentName(updated.getDepartment().getDepartmentName());

        return response;

    }

    public String deleteEntity(Long journalId) {
        journalRepo.findById(journalId)
                .orElseThrow(() -> new ResourceNotFoundException("Journal entry not found"));
        journalRepo.deleteById(journalId);
        return "Journal entry deleted.";
    }
}
