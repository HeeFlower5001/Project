package com.example.model;

import java.util.Time;

public class LookbackAlarmSetting {
    private long id;
    private boolean canAlarm = true;
    private Time alarmTime;
    private int alarmValue;
    private AlarmUnit alarmUnit;
}

enum AlarmUnit {
    DAY,
    WEEK,
    MONTH
}