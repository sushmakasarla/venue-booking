package com.ecil.venue;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Tables extends SQLiteOpenHelper{
	SQLiteDatabase db;

	public Tables(Context context) {
		super(context,"venuebooking", null, 1);
		// TODO Auto-generated constructor stub
		db=this.getWritableDatabase();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table user (username TEXT,pwd TEXT,email Text primary key,mobileno TEXT)");
		db.execSQL("create table venue (Username TEXT,Venuename TEXT,VenueAddr Text,pwd TEXT,email TEXT PRIMARY KEY,mobileno TEXT,FamilyEvent TEXT,SocialEvent TEXT,BusinessEvent TEXT,cost TEXT,Capacity TEXT,AC TEXT,ParkingCapacity TEXT,VEG TEXT,NON_VEG TEXT,Catering TEXT,OpenTime INTEGER,CloseTime INTEGER,AF TEXT,ASo TEXT,AB TEXT,Location TEXT,image blob );");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS user");
		db.execSQL("DROP TABLE IF EXISTS venue");
        onCreate(db);
		
	}
	

}
