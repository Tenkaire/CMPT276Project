package com.group8.dragcode.db;

public class QuestionStats {

    private String questionId;
    private boolean isCompleted;
    private long startTime;
    private long completedTime;

    public QuestionStats(String questionId, boolean isCompleted, long startTime, long completedTime) {
        this.questionId = questionId;
        this.isCompleted = isCompleted;
        this.startTime = startTime;
        this.completedTime = completedTime;
    }

    public String getQuestionId() {
        return questionId;
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
