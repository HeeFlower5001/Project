package com.example.repository.setting.alarm;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.setting.alarm.LookbackAlarmSetting;

public interface LookbackAlarmSettingRepository extends JpaRepository<LookbackAlarmSetting, Long> {

    
}