package com.example.model;

import java.time.LocalDate;

public class User {
    private long id;
    private String password;
    private String nickname;
    private LocalDate birthDate;

    User(long id, String password, String nickname, LocalDate birthDate) {
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.birthDate = birthDate;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
