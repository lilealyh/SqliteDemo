package android.liyuehu.com.sqlitedemo;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Yuehu.Li on 2017/11/14.
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    public MySQLiteOpenHelper(Context context, String name, int version) {
        super(context, name, null, version);
    }

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + "Menu"
                + "("
                + "content" + " TEXT PRIMARY KEY,"
                + "price" + " integer DEFAULT 0,"
                + "remain" + " integer DEFAULT 0"
                + ");"
        );
        db.execSQL("create table " + "HealthSystem"
                + "("
                + "date" + " TEXT PRIMARY KEY,"
                + "breakfast" + " TEXT NOT NULL,"
                + "lunch" + " TEXT NOT NULL,"
                + "dinner" + " TEXT,"
                + "dage" + " integer DEFAULT 0,"
                + "dapi" + " integer DEFAULT 0"
                + ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Menu");
        db.execSQL("DROP TABLE IF EXISTS HealthSystem");
        onCreate(db);
    }
}
