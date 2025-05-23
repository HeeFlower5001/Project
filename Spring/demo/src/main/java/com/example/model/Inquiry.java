package com.example.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Inquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inquiry_id")
    private Long inquiryId;

    // 외래키 어노테이션은 어떻게 설정할까?
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 최소 몇글자 이상 들어갈 수 있을까?
    // 최대는 100글자
    // Jakarta Bean Validation을 추가해야할까?
    @Column(nullable = false)
    private String title;
    
    // 최소 몇 글자 이상 들어갈 수 있을까?
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "is_answered", nullable = false)
    private Boolean isAnswered = false;

    public Inquiry() {}

    public Inquiry(User user, String title, String content) {
        this.user = user;
        this.title = title;
        this.content = content;
    }

    public Long getInquiryId() {
        return inquiryId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void updateCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

    public boolean getIsAnswered() {
        return isAnswered;
    }

    public void setIsAnswered(boolean isAnswered) {
        this.isAnswered = isAnswered;
    }

    public void toggleIsAnswered() {
        this.isAnswered = !isAnswered;
    }
}
