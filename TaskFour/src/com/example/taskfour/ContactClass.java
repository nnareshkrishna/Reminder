package com.example.taskfour;

import android.content.Context;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ContactClass extends SQLiteOpenHelper {

	
	public static final String DB_NAME="event.db" ;
    public static final int DB_VERSION=1 ;
    public static final String DB_TABLE="events" ;
    public static final String EVENT="name" ;
    public static final String DATE="date" ;
    public static final String DATABASE_CREATE="create table "+ " " +DB_TABLE+" (_id integer primary key to autoincrement," +
    	    ""+EVENT+" text,"+ ""+DATE+" text not null); "  ;
	public ContactClass(Context context) {
		super(context, EVENT,null, DB_VERSION);		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		arg0.execSQL(DATABASE_CREATE)	 ;	
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		arg0.execSQL("drop table if exists"+DB_TABLE) ;
	}
	

}
