package com.example.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.repository.UserRepository;

@RestController
public class UserController {
    private final UserRepository repository;

    UserController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/register")
    void register(@RequestBody User user) {
        // 예외 처리 추가

        repository.save(user);
    }

    @PostMapping("/login")
    void login(@RequestBody String id, @RequestBody String pw) {
        // 예외 처리 추가
        return repository.find(id, pw);
    }

    @GetMapping("/User/{id}")
    User getUser(@PathVariable String id) {
        // 찾기 로직 바꾸기
        return repository.findById(id);
    }

    // 비밀번호 변경
    // 로그아웃

    @DeleteMapping("/User/{id}")
    void deleteUser(@PathVariable String id) {
        // 삭제 로직 바꾸기
        repository.deleteById(id);
    }

    // TTS 설정 조회
    // TTS 설정 변경
    // 알림 설정 조회
    // 알림 설정 변경
    // 주요 질문 조회
    // 문의하기 전송
    // 애플리케이션 정보 조회회
}
