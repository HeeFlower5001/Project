package com.example.model.setting.alarm;

import java.time.LocalTime;

public class LookbackAlarmSetting {
    private long id;
    private boolean canAlarm = true;
    private LocalTime alarmTime;
    private int lookbackValue;
    private LookbackUnit lookbackUnit;
}

enum LookbackUnit {
    DAY,
    WEEK,
    MONTH
}