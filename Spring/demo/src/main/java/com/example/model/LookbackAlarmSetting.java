package com.example.model;

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