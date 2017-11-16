package android.liyuehu.com.sqlitedemo;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Yuehu.Li on 2017/11/14.
 */

public class MyContentProvider extends ContentProvider {
    public static final String AUTHORITY = "android.liyuehu.com.sqlitedemo";
    public static final String TABLE = "menu";
    private MySQLiteOpenHelper mySQLiteOpenHelper;
    public static final Uri URI_MY_MENU = Uri.parse("content://" + AUTHORITY + "/" + TABLE);
    public static final UriMatcher URI_MATCHER;
    static {
        URI_MATCHER=new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(AUTHORITY,TABLE,1);
    }
    @Override
    public boolean onCreate() {
        mySQLiteOpenHelper=new MySQLiteOpenHelper(this.getContext(),"menu.db",1);
        return true;
//        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db=mySQLiteOpenHelper.getWritableDatabase();
        Cursor cursor;
        cursor=db.query("Menu",projection,selection,selectionArgs,null,null,sortOrder);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {



        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long id=0;
        SQLiteDatabase db=mySQLiteOpenHelper.getWritableDatabase();
        id=db.replace("Menu",null,values);
//        id=db.insert("Menu",null,values);
        db.close();
        return ContentUris.withAppendedId(uri,id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db=mySQLiteOpenHelper.getWritableDatabase();
        int result=db.delete("Menu",selection,selectionArgs);
        db.close();
        return result;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db=mySQLiteOpenHelper.getWritableDatabase();
        int count=db.update("Menu",values,selection,selectionArgs);
        db.close();
        return count;
    }
}