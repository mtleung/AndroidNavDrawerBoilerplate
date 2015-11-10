package com.example.apptemplate.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Marco on 10/11/2015.
 */
public class AppDatabase extends SQLiteOpenHelper {
    private final String TAG = AppDatabase.class.getSimpleName();

    private static final String DATABASE_NAME = "App.db";
    private static final int DATABASE_VERSION = 1;
    private Context mContext;

    private String DB_CREATE_ITEM;
    private String DB_DROP_ITEM;

    public static abstract class FeedItem implements BaseColumns {
        public static final String TABLE_NAME = "item";
        public static final String COLUMN_NAME_ITEM = "item_name";
    }

    public AppDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;

        DB_CREATE_ITEM = "CREATE TABLE IF NOT EXISTS " + FeedItem.TABLE_NAME + " (" +
                FeedItem._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FeedItem.COLUMN_NAME_ITEM + " TEXT NOT NULL " +
                ")";
        DB_DROP_ITEM = "DROP TABLE IF EXISTS " + FeedItem.TABLE_NAME;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE_ITEM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DB_DROP_ITEM);
        db.execSQL(DB_CREATE_ITEM);
    }
}
