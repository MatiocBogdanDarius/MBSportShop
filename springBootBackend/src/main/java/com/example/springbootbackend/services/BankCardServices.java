package com.example.springbootbackend.services;

import com.example.springbootbackend.model.BankCard;
import com.example.springbootbackend.model.BillData;
import com.example.springbootbackend.repository.BankCardRepository;
import com.example.springbootbackend.repository.BillDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BankCardServices {
    private BankCardRepository bankCardRepository;

    public BankCard add(BankCard bankCard) {
        return bankCardRepository.save(bankCard);
    }

    public List<BankCard> findAll() {
        return bankCardRepository.findAll();
    }
}