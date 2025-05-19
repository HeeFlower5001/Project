package com.example.model.setting.alarm;

import java.time.LocalDate;

public class RemainderAlarmSetting {
    private long id;
    private boolean canAlarm = true;
    private LocalDate alarmTime;
    private int alarmValue;
    private AlarmUnit alarmUnit;
}

enum AlarmUnit {
    DAY,
    WEEK,
    MONTH
}
