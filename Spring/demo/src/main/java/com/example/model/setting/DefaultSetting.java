package com.example.model.setting;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class DefaultSetting {

    @Id
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "can_alarm", nullable = false)
    private boolean canAlarm = true;

    public DefaultSetting() {}

    public DefaultSetting(Long userId) {
        this.userId = userId;
    }

    public long getUserId() {
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
        this.canAlarm = !this.canAlarm;
    }
}
