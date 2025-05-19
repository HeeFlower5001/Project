package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AudioSetting {
    @Id
    private Long id;

    private VoiceType voiceType;
    private double voiceSpeed;
    private Bgm bgm;
}

enum VoiceType {
    MALE,
    FEMALE,
    ROBOT
}

enum Bgm {
    CALM,
    HAPPY,
    SAD
}