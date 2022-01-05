package com.example.springbootbackend.controller;

import com.example.springbootbackend.model.BankCard;
import com.example.springbootbackend.model.BillData;
import com.example.springbootbackend.services.BankCardServices;
import com.example.springbootbackend.services.BillDataService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "api/creditCard")
@AllArgsConstructor
public class BankCardController {
    private BankCardServices bankCardServices;

    @GetMapping("/all")
    public List<BankCard> findAll(){
        return bankCardServices.findAll();
    }

    @PostMapping
    public BankCard add(@RequestBody BankCard bankCard){
        return bankCardServices.add(bankCard);
    }
}
