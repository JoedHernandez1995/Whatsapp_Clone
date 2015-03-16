package com.example.joedhernandez.toolbox;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

/**
 * Created by joedhernandez on 3/15/15.
 */
public class StorageManipulator extends SQLiteOpenHelper {

    public StorageManipulator(Context context,String name,CursorFactory factory,int version){
        super(context,name,factory,version);
    }

    public static final String Database_name = "Whatsapp_Clone.db";
    public static final int Database_version = 1;
    public static final String ID = "_id";
    public static final String table_name_message = "table_message";
    public static final String messageReciever = "Reciever";
    public static final String messageSender  = "sender";
    public static final String message_message = "message";

    private static final String TABLE_MESSAGE_CREATE =
            "CREATE TABLE"+table_name_message+"("+ID
            +"INTEGER PRIMARY KEY AUTOINCREMENT, "+ messageReciever+" VARCHAR(25), "
            +messageSender+" VARCHAR(25)";

    public static final String table_message_drop = "DROP TABLE IF EXISTS"+table_name_message;

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(TABLE_MESSAGE_CREATE);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(table_message_drop);
    }

    public void insert(String sender,String reciever,String message){
        long rowID;

        try{
            SQLiteDatabase db = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(messageReciever,reciever);
            contentValues.put(messageSender,sender);
            contentValues.put(message_message,message);
            rowID = db.insert(table_name_message,null,contentValues);
        }catch(Exception e){

        }
    }

    public Cursor get(String sender, String reciever){
        SQLiteDatabase db = getWritableDatabase();
        String selectQuery = "SELECT FROM "+table_name_message+" WHERE "+messageSender+" LIKE "+sender+" AND "+messageReciever+" LIKE "+reciever+" ORDER BY "+ID+"ASC";
        return null;
    }

}
