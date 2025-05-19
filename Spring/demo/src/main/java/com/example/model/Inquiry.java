package com.example.model;

import java.time.LocalDateTime;

public class Inquiry {
    private long inquiryId;
    private long userId;

    private String title;
    private String content;
    private LocalDateTime createdAt = LocalDateTime.now();
    private Boolean isAnswered = false;

    Inquiry(long id, String title, String content) {
        this.userId = id;
        this.title = title;
        this.content = content;
    }
}
