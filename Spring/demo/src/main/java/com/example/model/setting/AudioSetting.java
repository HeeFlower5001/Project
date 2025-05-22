package com.example.model.setting;

import com.example.model.setting.enums.Bgm;
import com.example.model.setting.enums.VoiceType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
public class AudioSetting {
    @Id
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "voice_type", nullable = false)
    private VoiceType voiceType = VoiceType.ROBOT;

    @Column(name = "voice_speed", nullable = false)
    private double voiceSpeed = 1.0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Bgm bgm = Bgm.CALM;

    public AudioSetting() { }
    
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public VoiceType getVoiceType() {
        return voiceType;
    }

    public void setVoiceType(VoiceType voiceType) {
        this.voiceType = voiceType;
    }

    public double getVoiceSpeed() {
        return voiceSpeed;
    }

    public void setVoiceSpeed(double voiceSpeed) {
        this.voiceSpeed = voiceSpeed;
    }

    public Bgm getBgm() {
        return bgm;
    }

    public void setBgm(Bgm bgm) {
        this.bgm = bgm;
    }
}



