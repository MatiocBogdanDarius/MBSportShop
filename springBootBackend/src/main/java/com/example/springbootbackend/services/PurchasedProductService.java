package com.example.springbootbackend.services;

import com.example.springbootbackend.model.PurchasedProduct;
import com.example.springbootbackend.repository.PurchasedProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PurchasedProductService {
    private PurchasedProductRepository purchasedProductRepository;

    public PurchasedProduct add(PurchasedProduct purchasedProduct){
        return purchasedProductRepository.save(purchasedProduct);
    }

    public List<PurchasedProduct> findAll(){
        return purchasedProductRepository.findAll();
    }
}
