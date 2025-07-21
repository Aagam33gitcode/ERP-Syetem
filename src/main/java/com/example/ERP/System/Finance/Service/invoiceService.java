package com.example.ERP.System.Finance.Service;

import com.example.ERP.System.Finance.INVOICE.invoiceDTO;
import com.example.ERP.System.Finance.INVOICE.invoiceEntity;
import com.example.ERP.System.Finance.Repositroy.invoiveRepo;
import com.example.ERP.System.HR.Department.Model.departmentEntity;
import com.example.ERP.System.HR.Department.Repository.departmentRepo;

import com.example.ERP.System.HR.Employee.Model.employeeEntity;
import com.example.ERP.System.HR.Employee.Repository.employeeRepository;
import com.example.ERP.System.HR.ExceptionHandling.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class invoiceService {

    private final invoiveRepo repo;
    private final ModelMapper mapper;
    private final departmentRepo dpRopo;
    private final employeeRepository emprepo;
    invoiceService(invoiveRepo repo,ModelMapper mapper,departmentRepo dprepo,employeeRepository emprepo){
        this.repo=repo;
        this.mapper=mapper;
        this.dpRopo=dprepo;
        this.emprepo=emprepo;
    }
    public  invoiceDTO createInvoice(invoiceDTO invoiceDTO) {
        invoiceEntity invoice = mapper.map(invoiceDTO, invoiceEntity.class);

        // 2. Set Department manually (ModelMapper can’t resolve relationships by ID)
        if (invoiceDTO.getDepartmentId() != null) {
           departmentEntity department = dpRopo.findById(invoiceDTO.getDepartmentId()).orElseThrow(()->new ResourceNotFoundException("not departmet exist "+invoiceDTO.getCustomerName()));
            invoice.setDepartment(department);
        }
        if (invoiceDTO.getEmployeeId() != null) {
            employeeEntity customer = emprepo.findById(invoiceDTO.getEmployeeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + invoiceDTO.getEmployeeId()));
            invoice.setcustomer(customer);
        }

        // 3. Save
        invoiceEntity savedInvoice = repo.save(invoice);
        // 4. Map back to DTO
         invoiceDTO mapped=mapper.map(savedInvoice, invoiceDTO.class);
         mapped.setCustomerName(savedInvoice.getcustomer().getFirstName());
         return mapped;
    }

    public List<invoiceDTO> getAllInvoices() {
        List<invoiceEntity> entities=repo.findAll();
        return entities
                .stream()
                .map(invoiceEntity -> mapper
                        .map(invoiceEntity, invoiceDTO.class))
                .collect(Collectors.toList());
    }

    public  invoiceDTO getInvoiceById(Long id) {
        invoiceEntity entity=repo.findById(id).orElseThrow(()->new ResourceNotFoundException(id+" not found"));
        return mapper.map(entity, invoiceDTO.class);
    }

    public  String deleteInvoice(Long invoiceId) {
        if(repo.findById(invoiceId).isPresent()){
            repo.deleteById(invoiceId);
            return "DELETED";
        }
        else {
            throw new ResourceNotFoundException(invoiceId+" not exit");
        }
    }

    public invoiceDTO updateInvoice(Long invoiceId, invoiceDTO dto) {
        invoiceEntity existing = repo.findById(invoiceId)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found with ID: " + invoiceId));

        // Partial or full update — only update if fields are not null
        if (dto.getTotalAmount() != null) {
            existing.setTotalAmount(dto.getTotalAmount());
        }

        if (dto.getStatus() != null) {
            existing.setStatus(dto.getStatus());
        }

        if (dto.getDueDate() != null) {
            existing.setDueDate(dto.getDueDate());
        }

        if (dto.getIssuedDate() != null) {
            existing.setIssueDate(dto.getIssuedDate());
        }

        if (dto.getDepartmentId() != null) {
            departmentEntity department = dpRopo.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + dto.getDepartmentId()));
            existing.setDepartment(department);
        }

        if (dto.getEmployeeId() != null) {
            employeeEntity employee = emprepo.findById(dto.getEmployeeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + dto.getEmployeeId()));
            existing.setcustomer(employee);
        }

        // Save updated invoice
        invoiceEntity updated = repo.save(existing);

        // Map to DTO and manually add customer name
        invoiceDTO response = mapper.map(updated, invoiceDTO.class);
        response.setCustomerName(updated.getcustomer().getFirstName());
        return response;
}}
