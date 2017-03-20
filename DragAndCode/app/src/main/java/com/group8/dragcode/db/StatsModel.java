package com.group8.dragcode.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.group8.dragcode.qclasses.Question;

public class StatsModel {

    private static class StatsTable implements BaseColumns {
        public static final String TABLE_NAME = "stats";

        public static final String COL_QUESTION_ID = "q_id";
        public static final String COL_LANGUAGE = "language";
        public static final String COL_DIFFICULTY = "difficulty";
        public static final String COL_COMPLETED = "completed";
        public static final String COL_STARTED_AT = "started_at";
        public static final String COL_COMPLETED_AT = "completed_at";
    }

    // Just clarifying that _ID is the id of the *stat*, and QUESTION_ID that of the question.
    // The difference is purely semantics.
    public static final String SQL_TABLE =
            "CREATE TABLE IF NOT EXISTS " + StatsTable.TABLE_NAME + " (" +
            StatsTable._ID + " INTEGER," +
            StatsTable.COL_QUESTION_ID + " TEXT," +
            StatsTable.COL_LANGUAGE + " TEXT," +
            StatsTable.COL_DIFFICULTY + " TEXT," +
            StatsTable.COL_COMPLETED + " INTEGER DEFAULT 0," +
            StatsTable.COL_STARTED_AT + " INTEGER," +
            StatsTable.COL_COMPLETED_AT + " INTEGER DEFAULT 0," +
            "PRIMARY KEY (" + StatsTable._ID + ", " + StatsTable.COL_QUESTION_ID + "))";

    public static final String SQL_DELETE = "DROP TABLE IF EXISTS " + StatsTable.TABLE_NAME;

    private SQLiteDatabase database;

    public StatsModel(SQLiteDatabase database) {
        this.database = database;
    }

    public QuestionStats getQuestionStats(String questionId) {
        String[] columns = {
                StatsTable.COL_COMPLETED,
                StatsTable.COL_COMPLETED_AT,
                StatsTable.COL_STARTED_AT
        };

        String selection = StatsTable.COL_QUESTION_ID + " = ?";
        String[] selectionArgs = { questionId };

        Cursor cursor = this.database.query(StatsTable.TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        if (!cursor.moveToNext()) return null;

        boolean completed = cursor.getInt(cursor.getColumnIndex(StatsTable.COL_COMPLETED)) != 0;
        long startedAt = cursor.getLong(cursor.getColumnIndex(StatsTable.COL_STARTED_AT));
        long completedAt = cursor.getLong(cursor.getColumnIndex(StatsTable.COL_COMPLETED_AT));

        cursor.close();
        return new QuestionStats(completed, startedAt, completedAt);
    }

    public int countCompletedForLanguage(String language) {
        String selection = StatsTable.COL_LANGUAGE + " = ? AND " + StatsTable.COL_COMPLETED + " = 1";
        String[] selectionArgs = { language };
        return countQuery(selection, selectionArgs);
    }

    public int countAttemptedForLanguage(String language) {
        String selection = StatsTable.COL_LANGUAGE + " = ?";
        String[] selectionArgs = { language };
        return countQuery(selection, selectionArgs);
    }

    public int countCompletedForDifficulty(String difficulty) {
        String selection = StatsTable.COL_DIFFICULTY + " = ? AND " + StatsTable.COL_COMPLETED + " = 1";
        String[] selectionArgs = { difficulty };
        return countQuery(selection, selectionArgs);
    }

    public int countAttemptedForDifficulty(String difficulty) {
        String selection = StatsTable.COL_DIFFICULTY + " = ?";
        String[] selectionArgs = { difficulty };
        return countQuery(selection, selectionArgs);
    }

    private int countQuery(String selection, String[] selectionArgs) {
        String sql = "SELECT COUNT(*) AS count FROM " + StatsTable.TABLE_NAME +
                " WHERE " + selection;
        Cursor cursor = database.rawQuery(sql, selectionArgs);

        if (!cursor.moveToNext()) return 0;

        int count = cursor.getInt(cursor.getColumnIndex("count"));
        cursor.close();
        return count;
    }

    //TODO: Insert difficulty and language from new Question object when that's done

    /**
        Inserts a new entry marked as incomplete, if it doesn't exist
     */
    public boolean insertAttempt(Question question) {
        ContentValues values = new ContentValues();
        values.put(StatsTable.COL_QUESTION_ID, question.getQuestionKey());
        values.put(StatsTable.COL_STARTED_AT, System.currentTimeMillis());
        values.put(StatsTable.COL_DIFFICULTY, question.getDifficulty());
        values.put(StatsTable.COL_LANGUAGE, question.getLanguage());

        return database.insertWithOnConflict(StatsTable.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE) != -1;
    }

    /**
        Marks a question as complete. An attempt must already exist.
     */
    public boolean markCompletion(Question question) {
        String selection = StatsTable.COL_QUESTION_ID + " = ?";
        String[] selectionArgs = { question.getQuestionKey() };

        ContentValues values = new ContentValues();
        values.put(StatsTable.COL_COMPLETED, 1);
        values.put(StatsTable.COL_COMPLETED_AT, System.currentTimeMillis());

        return database.update(StatsTable.TABLE_NAME, values, selection, selectionArgs) > 0;
    }


}
