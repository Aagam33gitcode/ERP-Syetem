package com.example.ERP.System.Inventory.Controller;

import com.example.ERP.System.Inventory.Model.CategoryEntity;
import com.example.ERP.System.Inventory.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService service;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")

    public ResponseEntity<CategoryEntity> create(@RequestBody CategoryEntity category) {
        return ResponseEntity.ok(service.createCategory(category));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")

    public List<CategoryEntity> getAll() {
        return service.getAllCategories();
    }
}
