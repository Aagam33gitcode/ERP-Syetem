package com.example.ERP.System.HR.Department.Service;

import com.example.ERP.System.HR.Department.Model.departmentDto;
import com.example.ERP.System.HR.Department.Model.departmentEntity;
import com.example.ERP.System.HR.Department.Repository.departmentRepo;
import com.example.ERP.System.HR.ExceptionHandling.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class depService {
    private final departmentRepo repo;
    private final ModelMapper mapper;
    depService(departmentRepo repo, ModelMapper mapper){
        this.repo=repo;
        this.mapper=mapper;
    }

    public List<departmentDto> getAlldepartment() {
       List< departmentEntity> entities=repo.findAll();
       return entities
               .stream()
               .map(departmentEntity -> mapper
                       .map(departmentEntity,departmentDto.class))
               .collect(Collectors.toList());
    }

    public departmentDto getDepartmentById(Long  departmentId) {
     departmentEntity entity=repo.findById(departmentId).orElseThrow(()->new RuntimeException(departmentId+" not exist")) ;
     return mapper.map(entity, departmentDto.class);


    }

    public departmentDto addNewDepartment(departmentDto dto) {
        departmentEntity entity=mapper.map(dto,departmentEntity.class);
        Optional<departmentEntity> existed=repo.findByDepartmentNameContainingIgnoreCase(dto.getDepartmentName());
        if(existed.isPresent()){
            throw new ResourceNotFoundException.ResourceAlreadyExistsException("already exist"+entity);
        }
        departmentEntity saved=repo.save(entity);

        return mapper.map(saved, departmentDto.class);
    }

    public String deleteDepartment(Long departmentId) {
      if(repo.findById(departmentId).isPresent()){
          repo.deleteById(departmentId);
          return "DELETED";
      }
      else {
          throw new ResourceNotFoundException(departmentId+" not exit");
      }
    }
}
