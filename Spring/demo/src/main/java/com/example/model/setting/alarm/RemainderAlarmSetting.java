package com.example.model.setting.alarm;

import java.time.LocalTime;

import com.example.model.setting.enums.AlarmUnit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
public class RemainderAlarmSetting {
    @Id
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "can_alarm", nullable = false)
    private boolean canAlarm = true;

    @Column(name = "alarm_time", nullable = false)
    private LocalTime alarmTime = LocalTime.of(20, 0);

    @Column(name = "alarm_value", nullable = false)
    private int alarmValue = 1;

    @Enumerated(EnumType.STRING)
    @Column(name = "alarm_unit", nullable = false)
    private AlarmUnit alarmUnit = AlarmUnit.DAY;

    public RemainderAlarmSetting() {}

    public RemainderAlarmSetting(Long userId) {
        this.userId = userId;
    } 

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean getCanAlarm() {
        return canAlarm;
    }

    public void setCanAlarm(boolean canAlarm) {
        this.canAlarm = canAlarm;
    }

    public void toggleCanAlarm() {
        this.canAlarm = !canAlarm;
    }

    public LocalTime getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(LocalTime alarmTime) {
        this.alarmTime = alarmTime;
    }

    public int getAlarmValue() {
        return alarmValue;
    }

    public void setAlarmValue(int alarmValue) {
        this.alarmValue = alarmValue;
    }

    public AlarmUnit getAlarmUnit() {
        return alarmUnit;
    }

    public void setAlarmUnit(AlarmUnit alarmUnit) {
        this.alarmUnit = alarmUnit;
    }
}


