package com.example.stavalfi.app1;

public class ClockReminder {
    private String reminderName;
    private String userId;
    private String callToNumber;
    private String datetime;

    public ClockReminder() {

    }

    public ClockReminder(String reminderName, String userId, String callToNumber, String datetime) {
        this.reminderName = reminderName;
        this.userId = userId;
        this.callToNumber = callToNumber;
        this.datetime = datetime;
    }

    public String getReminderName() {
        return reminderName;
    }

    public void setReminderName(String reminderName) {
        this.reminderName = reminderName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCallToNumber() {
        return callToNumber;
    }

    public void setCallToNumber(String callToNumber) {
        this.callToNumber = callToNumber;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "reminderName= " + reminderName +
                ", callToNumber= " + callToNumber +
                ", datetime= " + datetime;
    }
}
