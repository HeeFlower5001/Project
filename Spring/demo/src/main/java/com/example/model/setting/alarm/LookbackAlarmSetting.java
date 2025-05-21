package com.example.model.setting.alarm;

import java.time.LocalTime;

import com.example.model.setting.enums.AlarmUnit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
public class LookbackAlarmSetting {
    @Id
    @Column(name = "user_id", nullable = false)
    private long userId;

    @Column(name = "can_alarm", nullable = false)
    private boolean canAlarm = true;

    @Column(name = "alarm_time", nullable = false)
    private LocalTime alarmTime = LocalTime.of(20, 0);

    @Column(name = "lookback_value", nullable = false)
    private int lookbackValue = 1;

    @Enumerated(EnumType.STRING)
    @Column(name = "lookback_unit", nullable = false)
    private AlarmUnit alarmUnit = AlarmUnit.DAY;

    public LookbackAlarmSetting() {}

    public LookbackAlarmSetting(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
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

    public int getLookbackValue() {
        return lookbackValue;
    }

    public void setLookbackValue(int lookbackValue) {
        this.lookbackValue = lookbackValue;
    }

    public AlarmUnit getLookbackUnit() {
        return alarmUnit;
    }

    public void setLookbackUnit(AlarmUnit alarmUnit) {
        this.alarmUnit = alarmUnit;
    }
}