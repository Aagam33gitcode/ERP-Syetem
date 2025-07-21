package com.example.ERP.System.Inventory.Repostiory;

import com.example.ERP.System.Inventory.Model.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity,Long> {
}
