package com.example.springbootbackend.services;

public interface EmailSender {
    void send(String to, String email);
}
