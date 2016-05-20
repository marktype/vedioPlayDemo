package com.bruce.testgitdemo.fileutil;

import android.net.Uri;
import android.provider.BaseColumns;

public class DataBaseUtil {

    public abstract class TableUserColumn implements BaseColumns{
        public static final String DB_BASENAME = "adName.db";//数据库名
        public static final String TABLE_USER = "user";//表名
        public static final String NAME = "name";//属性 名字
        public static final String TIME = "playtime";//属性 次数
        public static final String _ID = "_id"; //自增id cursorAdapter遍历使用_id
        public static final String _ID_KEY = "INTEGER PRIMARY KEY AUTOINCREMENT";//自增属性
    }
    public abstract class TableStudentColumn implements BaseColumns{
        public static final String DB_BASENAME = "teststudent.db";//数据库名
        public static final String TABLE_USER = "student";//表名
        public static final String NAME = "name";//属性 名字
        public static final String NUMBER = "number";//属性 学号
    }

    public interface SmsColumn extends BaseColumns{

        public static final Uri SMS_URI = Uri.parse("content://sms");
        public static final String ADDRESS = "address";  //电话地址——号码
        public static final String BODY = "body"; //短信内容
    }
}
