package com.example.springbootbackend.controller;

import com.example.springbootbackend.model.BankCard;
import com.example.springbootbackend.model.Order;
import com.example.springbootbackend.model.requests.OrderRequest;
import com.example.springbootbackend.services.OrderServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "api/order")
@AllArgsConstructor
public class OrderController {
    private OrderServices orderServices;

    @GetMapping("/all")
    public List<Order> findAll(){
        return orderServices.findAll();
    }

    @PostMapping
    public Order add(@RequestBody OrderRequest orderRequest){
        return orderServices.add(orderRequest);
    }
}
