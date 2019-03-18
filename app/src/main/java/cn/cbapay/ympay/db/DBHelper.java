package cn.cbapay.ympay.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by xuetao on 16/1/8.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "YMPay.db";
    private static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        // 用户表
        createUserTable(db);

    }

    private void createUserTable(SQLiteDatabase db) {
        StringBuilder builder = new StringBuilder();
        builder.append("create table user(");
        builder.append("ID integer primary key autoincrement,");
        builder.append("json text");
        builder.append(");");
        db.execSQL(builder.toString());
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
