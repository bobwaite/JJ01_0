package com.example.user.jj01_0;

/**
 * Created by anupamchugh on 19/10/15.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "PAIRS";

    // Table columns
    public static final String _ID = "_id";
    public static final String NAME1 = "name1";
    public static final String NAME2 = "name2";
    public static final String TOTAL_SCORE_ONE = "totalScoreOne";
    public static final String TOTAL_SCORE_TWO = "totalScoreOne";
    public static final String WINS_1 = "winsOne";
    public static final String WINS_2 = "winsTwo";
    public static final String TIES = "ties";
    public static final String LIMIT = "limit";
    public static final String RANK = "rank";

    // Database Information
    static final String DB_NAME = "HISTORY.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME1 + " TEXT NOT NULL DEFAULT 'FOO', " +
            NAME2 + " TEXT NOT NULL DEFAULT 'BAR', " +
            TOTAL_SCORE_ONE + "INTEGER NOT NULL DEFAULT 0," +
            TOTAL_SCORE_TWO + "INTEGER NOT NULL DEFAULT 0," +
            WINS_1 + "INTEGER NOT NULL DEFAULT 0," +
            WINS_2 + "INTEGER NOT NULL DEFAULT 0," +
            TIES + "INTEGER NOT NULL DEFAULT 0," +
            LIMIT + "INTEGER NOT NULL DEFAULT 0," +
            RANK + "INTEGER NOT NULL DEFAULT 0"  +")";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
