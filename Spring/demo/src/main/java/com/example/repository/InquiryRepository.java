package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Inquiry;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
    List<Inquiry> findAllById(Long id);
    
    // 답변되지 않은 항목들만 어떻게 표현하지?
}