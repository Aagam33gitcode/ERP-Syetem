package com.example.ERP.System.Inventory.Repostiory;

import com.example.ERP.System.Inventory.Model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {
}
