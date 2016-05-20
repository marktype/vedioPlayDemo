package com.bruce.testgitdemo.fileutil;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDataBase extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1; // 版本信息


    /*
     * 单例模式
     */
    private static MyDataBase sDataBase;

    public static MyDataBase getInstance(Context context) {
        if (sDataBase == null) {
            sDataBase = new MyDataBase(context);
        }
        return sDataBase;
    }

    /**
     * 构造私有化
     * @param context
     */
    private MyDataBase(Context context) {
        super(context, DataBaseUtil.TableUserColumn.DB_BASENAME, null,
                DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + DataBaseUtil.TableUserColumn.TABLE_USER
                + " (" + DataBaseUtil.TableUserColumn._ID + " "
                + DataBaseUtil.TableUserColumn._ID_KEY + ","
                + DataBaseUtil.TableUserColumn.NAME + " varchar,"
                + DataBaseUtil.TableUserColumn.TIME + " varchar)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * 添加数据库
     * @param name
     *
     */
    public void add(String name,String time) {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBaseUtil.TableUserColumn.NAME, name);
        values.put(DataBaseUtil.TableUserColumn.TIME, time);
        db.insert(DataBaseUtil.TableUserColumn.TABLE_USER, null, values);
    }

    /**
     * 更新数据库
     * @param name
     */
    public void upData(String name){
        SQLiteDatabase db = getReadableDatabase();
        String whereClause = "name = ?";//通过name删除
        ContentValues values = new ContentValues();
        values.put(DataBaseUtil.TableUserColumn.NAME, name); //更新信息
        db.update(DataBaseUtil.TableUserColumn.TABLE_USER, values, whereClause, new String[]{"1"});  //通过id修改信息，此处待定
    }
    /**
     * 删除数据库数据
     * @param name
     */
    public void delete(String name){
        SQLiteDatabase db = getReadableDatabase();
        String whereClause = "name = ?";//通过name删除
        String[] whereArgs = new String[]{name};//name对应编号

        db.delete(DataBaseUtil.TableUserColumn.TABLE_USER, whereClause, whereArgs);

    }
    /**
     * 查询数据库
     * @return
     */
    public ArrayList<String> query() {
        ArrayList<String> userData = new ArrayList<String>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(DataBaseUtil.TableUserColumn.TABLE_USER, null,
                null, null, null, null, null);
        if (cursor.getCount() > 0 && cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor
                        .getColumnIndex(DataBaseUtil.TableUserColumn.NAME));  //文件名
                String time = cursor.getString(cursor
                        .getColumnIndex(DataBaseUtil.TableUserColumn.TIME));  //次数
                userData.add(name);
            }

        }

        return userData;
    }


}
