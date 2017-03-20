package com.group8.dragcode;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Tyler on 2017-03-18.
 *
 * This class is meant to implement ordering of questions with
 * one set corresponding to one difficulty/language pair
 */

public class QuestionSet implements Parcelable {
    private String[] questionKeys;
    private int currentIndex;

    public QuestionSet(String[] questionKeys){
        this.questionKeys = questionKeys;
        this.currentIndex = -1;
    }

    public int getNumberQuestions(){
        return questionKeys.length;
    }

    public String getCurrentQuestionKey(){
        return this.questionKeys[this.currentIndex];
    }

    public String getQuestionKeyForIndex(int index){
        this.currentIndex = index;
        return this.questionKeys[index];
    }

    public String getNextQuestionKeys(){
        this.currentIndex += 1;
        return this.questionKeys[this.currentIndex];
    }

    public String getPreviousQuestionKeys(){
        this.currentIndex -= 1;
        return this.questionKeys[this.currentIndex];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(this.questionKeys);
        dest.writeInt(this.currentIndex);
    }

    protected QuestionSet(Parcel in) {
        this.questionKeys = in.createStringArray();
        this.currentIndex = in.readInt();
    }

    public static final Parcelable.Creator<QuestionSet> CREATOR = new Parcelable.Creator<QuestionSet>() {
        @Override
        public QuestionSet createFromParcel(Parcel source) {
            return new QuestionSet(source);
        }

        @Override
        public QuestionSet[] newArray(int size) {
            return new QuestionSet[size];
        }
    };
}
