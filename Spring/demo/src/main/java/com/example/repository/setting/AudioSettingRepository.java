package com.example.repository.setting;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.setting.AudioSetting;

public interface AudioSettingRepository extends JpaRepository<AudioSetting, Long> {

    
}