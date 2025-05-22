package com.example.repository.setting;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.setting.DefaultSetting;

public interface DefaultSettingRepository extends JpaRepository<DefaultSetting, Long> {

    
}