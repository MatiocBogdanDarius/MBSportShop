package com.example.springbootbackend.services;

import com.example.springbootbackend.model.BillData;
import com.example.springbootbackend.repository.BillDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BillDataService {
    private BillDataRepository billDataRepository;

    public BillData add(BillData billData) {
        return billDataRepository.save(billData);
    }

    public List<BillData> findAll() {
        return billDataRepository.findAll();
    }
}
