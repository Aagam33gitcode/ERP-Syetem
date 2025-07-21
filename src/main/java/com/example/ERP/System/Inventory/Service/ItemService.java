package com.example.ERP.System.Inventory.Service;

import com.example.ERP.System.Inventory.Model.ItemEntity;
import com.example.ERP.System.Inventory.Repostiory.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ItemService {
    @Autowired
    private ItemRepository repository;

    public ItemEntity createItem(ItemEntity item) {
        return repository.save(item);
    }

    public List<ItemEntity> getAllItems() {
        return repository.findAll();
    }
}
