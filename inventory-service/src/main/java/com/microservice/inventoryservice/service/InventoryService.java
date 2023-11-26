package com.microservice.inventoryservice.service;

import com.microservice.inventoryservice.dto.InventoryResponse;
import com.microservice.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    public List<InventoryResponse> isInStock(List<String> skuCode){
//        return inventoryRepository.findBySkuCode(skuCode).isPresent();
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory -> InventoryResponse.builder().skuCode(inventory.getSkuCode())
                        .inStock(inventory.getQuantity() > 0)
                        .build())
                .collect(Collectors.toList());
    }
}
