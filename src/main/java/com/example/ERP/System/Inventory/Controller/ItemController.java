package com.example.ERP.System.Inventory.Controller;

import com.example.ERP.System.Inventory.Model.ItemEntity;
import com.example.ERP.System.Inventory.Service.CategoryService;
import com.example.ERP.System.Inventory.Service.ItemService;
import jakarta.validation.Valid;
import org.aspectj.weaver.AnnotationNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/items")
public class ItemController {
    @Autowired
    private ItemService service;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<ItemEntity> create(@Valid @RequestBody ItemEntity item) {

        return ResponseEntity.ok(service.createItem(item));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'EMPLOYEE')")
    public List<ItemEntity> getAll() {
        return service.getAllItems();
    }
}
