package com.example.a2201783583_uas_mobprog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class SQLiteHelper  extends SQLiteOpenHelper {
    private Context context;
    private SQLiteDatabase dbRead = this.getReadableDatabase();
    private SQLiteDatabase dbWrite = this.getWritableDatabase();
    private Cursor cursor = null;
    private static final String DATABASE_NAME = "ezycommerce.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "cart";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_BOOKID = "book_id";
    private static final String COLUMN_NAME = "book_name";
    private static final String COLUMN_DESC = "book_desc";
    private static final String COLUMN_PRICE = "book_price";
    private static final String COLUMN_AUTHOR = "book_author";
    private static final String COLUMN_TYPE = "book_type";
    private static final String COLUMN_IMG = "book_img";
    private static final String COLUMN_CATEGORY = "book_category";
    private static final String COLUMN_QTY = "book_quantity";

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_BOOKID + " INTEGER, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_DESC + " TEXT, " +
                COLUMN_PRICE + " DOUBLE, " +
                COLUMN_AUTHOR + " TEXT, " +
                COLUMN_TYPE + " TEXT, " +
                COLUMN_IMG + " TEXT, " +
                COLUMN_CATEGORY + " TEXT, " +
                COLUMN_QTY + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addToCart(Integer id, String name, String desc, Double price, String author, String type, String img, String category, Integer qty) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_BOOKID,id);
        contentValues.put(COLUMN_NAME,name);
        contentValues.put(COLUMN_DESC, desc);
        contentValues.put(COLUMN_PRICE, price);
        contentValues.put(COLUMN_AUTHOR, author);
        contentValues.put(COLUMN_TYPE, type);
        contentValues.put(COLUMN_IMG, img);
        contentValues.put(COLUMN_CATEGORY, category);
        contentValues.put(COLUMN_QTY, qty);
        long result = dbWrite.insert(TABLE_NAME, null, contentValues);

        if(result == -1)
            System.out.println("FAIL");
        else
            System.out.println("SUCCESS");
    }

    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;

        if(dbRead != null) cursor = dbRead.rawQuery(query, null);
        return cursor;
    }

    Cursor readById(int id) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_BOOKID + " == " + id;

        if(dbRead != null) cursor = dbRead.rawQuery(query, null);
        return cursor;
    }

    void updateQty(int id, int qty) {
        String query = "UPDATE " + TABLE_NAME + " SET " + COLUMN_QTY + " = " + qty + " WHERE " + COLUMN_BOOKID + " == " + id;
        dbWrite.execSQL(query);
    }
}
