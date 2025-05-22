package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Inquiry;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {

    
}