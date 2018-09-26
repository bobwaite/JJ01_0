package com.example.user.jj01_0;

/**
 * Created by anupamchugh on 19/10/15.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import static com.example.user.jj01_0.DatabaseHelper.NAME2;

public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String name1, String name2, int totalScoreOne, int totalScoreTwo,
                        int winsOne, int winsTwo, int ties, int limit, int rank) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.NAME1, name1);
        contentValue.put(DatabaseHelper.NAME2, name2);
        contentValue.put(DatabaseHelper.TOTAL_SCORE_ONE, totalScoreOne);
        contentValue.put(DatabaseHelper.TOTAL_SCORE_TWO, totalScoreTwo);
        contentValue.put(DatabaseHelper.WINS_1, winsOne);
        contentValue.put(DatabaseHelper.WINS_2, winsTwo);
        contentValue.put(DatabaseHelper.TIES, ties);
        contentValue.put(DatabaseHelper.LIMIT, limit);
        contentValue.put(DatabaseHelper.RANK, rank);

        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.NAME1,
                DatabaseHelper.NAME2, DatabaseHelper.TOTAL_SCORE_ONE,
                DatabaseHelper.TOTAL_SCORE_TWO, DatabaseHelper.WINS_1,
                DatabaseHelper.WINS_2, DatabaseHelper.TIES, DatabaseHelper.LIMIT,
                DatabaseHelper.RANK};
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String name1, String name2, int totalScoreOne, int totalScoreTwo,
                      int winsOne, int winsTwo, int ties, int limit, int rank) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseHelper.NAME1, name1);
        contentValues.put(DatabaseHelper.NAME2, name2);
        contentValues.put(DatabaseHelper.TOTAL_SCORE_ONE, totalScoreOne);
        contentValues.put(DatabaseHelper.TOTAL_SCORE_TWO, totalScoreTwo);
        contentValues.put(DatabaseHelper.WINS_1, winsOne);
        contentValues.put(DatabaseHelper.WINS_2, winsTwo);
        contentValues.put(DatabaseHelper.TIES, ties);
        contentValues.put(DatabaseHelper.LIMIT, limit);
        contentValues.put(DatabaseHelper.RANK, rank);

        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues,
                DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }

}
