package com.example.springbootbackend.controller;


import com.example.springbootbackend.model.Bill;
import com.example.springbootbackend.services.BillServices;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "api/")
@AllArgsConstructor
public class BillController {
    public BillServices billServices;

    @GetMapping("bill/")
    public ResponseEntity<Bill> getBill() {
        return ResponseEntity.ok(billServices.bill);
    }
}
