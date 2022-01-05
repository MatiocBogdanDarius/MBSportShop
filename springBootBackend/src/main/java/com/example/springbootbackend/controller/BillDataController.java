package com.example.springbootbackend.controller;

import com.example.springbootbackend.model.BillData;
import com.example.springbootbackend.services.BillDataService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "api/billData")
@AllArgsConstructor
public class BillDataController {
    private BillDataService billDataService;

    @GetMapping("/all")
    public List<BillData> findAll(){
        return billDataService.findAll();
    }

    @PostMapping
    public BillData add(@RequestBody BillData billData){
        System.out.println(billData);
        return billDataService.add(billData);
    }
}
