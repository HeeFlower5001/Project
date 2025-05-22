package com.example.repository.setting.alarm;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.setting.alarm.RemainderAlarmSetting;

public interface RemainderAlarmSettingRepository extends JpaRepository<RemainderAlarmSetting, Long> {

    
}