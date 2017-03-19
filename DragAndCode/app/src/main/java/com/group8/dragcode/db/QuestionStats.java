package com.group8.dragcode.db;

public class QuestionStats {

    private boolean isCompleted;
    private long startTime;
    private long completedTime;

    public QuestionStats(boolean isCompleted, long startTime, long completedTime) {
        this.isCompleted = isCompleted;
        this.startTime = startTime;
        this.completedTime = completedTime;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getCompletedTime() {
        return completedTime;
    }
}
