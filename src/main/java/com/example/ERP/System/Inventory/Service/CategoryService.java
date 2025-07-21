package com.example.ERP.System.Inventory.Service;

import com.example.ERP.System.Inventory.Model.CategoryEntity;
import com.example.ERP.System.Inventory.Repostiory.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

        @Autowired
        private CategoryRepository repository;

        public CategoryEntity createCategory(CategoryEntity category) {
            return repository.save(category);
        }

        public List<CategoryEntity> getAllCategories() {
            return repository.findAll();
        }
    }


