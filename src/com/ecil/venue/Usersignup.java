
package com.ecil.venue;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Usersignup {
	SQLiteDatabase db;
	Tables t;
	Context c;
	public Usersignup(Context context) {
		this.c=context;
		t=new Tables(context);
		//open the database
		try{
			open();
		}
		catch(SQLException e)
		{
			Log.e("User Signup","SQLException on opening databse"+e.getMessage());
			e.printStackTrace();
		}
		
	}

	public void open()throws SQLException {
		// TODO Auto-generated method stub
		SQLiteDatabase db = t.getWritableDatabase();
		
	}
	public void close()
	{
		t.close();
	}

	public boolean insertData(String name,String pwd,String email,String mobile) {
	        SQLiteDatabase db = t.getWritableDatabase();
	        ContentValues contentValues = new ContentValues();
	        contentValues.put("username",name);
	        contentValues.put("pwd",pwd);
	        contentValues.put("email",email);
	        contentValues.put("mobileno",mobile);
	        
	     
	        long result = db.insert("user",null ,contentValues);
	        if(result == -1)
	            return false;
	        else
	            return true;
	    }
	public boolean updatetable(String vname,String date,String ca) {
        SQLiteDatabase db = t.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ca,vname);
        
        
     
        long result = db.insert(vname,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
	public boolean insertDatav(String name,String vname,String add,String pwd,String email,String mobile) {
        SQLiteDatabase db = t.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Username",name);
        contentValues.put("Venuename",vname);
        contentValues.put("VenueAddr",add);
        contentValues.put("pwd",pwd);
        contentValues.put("email",email);
        contentValues.put("mobileno",mobile);
        
        
        long result = db.insert("venue",null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
	 
	public boolean insertDetails(String fe,String se,String be,String cost,String seat,String ac,String car_par,String veg,String non_veg,String Cater,int ot,int ct,String location,String email)
	{
	    db = t.getWritableDatabase();
	    ContentValues contentValues = new ContentValues();
	    contentValues.put("FamilyEvent",fe);
	    contentValues.put("SocialEvent",se);
	    contentValues.put("BusinessEvent",be);
	    contentValues.put("cost",cost);
	    contentValues.put("Capacity",seat);
	    contentValues.put("AC",ac);
	    contentValues.put("ParkingCapacity",car_par);
	    contentValues.put("VEG",veg);
	    contentValues.put("NON_VEG",non_veg);
	    contentValues.put("Catering",Cater);
	    contentValues.put("OpenTime",ot);
	    contentValues.put("CloseTime",ct);
	    contentValues.put("Location",location);
	    long result = db.update("venue",contentValues,"email= ? ",new String[] {email});
	    if(result == -1)
	        return false;
	    else
	        return true;
	}

	 
	   
		public Cursor getAllData(String email) {
	        SQLiteDatabase db = t.getWritableDatabase();
	        Cursor res = db.rawQuery("select * from user where email='"+email+"'",null);
	        return res;
	    }
	   
	    public Cursor emailCheck(String email) {
	        SQLiteDatabase db = t.getWritableDatabase();
	        Cursor res = db.rawQuery("select email from user where email='"+email+"'",null);
	        return res;
	    }
	    public Cursor emailCheck1(String email) {
	        SQLiteDatabase db = t.getWritableDatabase();
	        Cursor res = db.rawQuery("select email from venue where email='"+email+"'",null);
	        return res;
	    }
	    public Cursor getemail(String vname) {
	        SQLiteDatabase db = t.getWritableDatabase();
	        Cursor res = db.rawQuery("select email from venue where Venuename='"+vname+"'",null);
	        return res;
	    }
		/*public Cursor selectFun(String s) {
			// TODO Auto-generated method stub
			SQLiteDatabase db = t.getWritableDatabase();
			Cursor res = db.rawQuery(s,null);
			return res;
		}*/
		public Cursor getVenueNames() {
	        SQLiteDatabase db = t.getWritableDatabase();
	        Cursor res = db.rawQuery("select venuename,Location from venue",null);
	        return res;
	    }
		public Cursor check(String email,String pwd)
	    {
	        SQLiteDatabase db = t.getWritableDatabase();
	        Cursor res=db.rawQuery("select email from user where pwd='"+pwd+"' and email='"+email+"'",null);
	        return res;
	    }
		public Cursor check1(String email,String pwd)
	    {
	        SQLiteDatabase db = t.getWritableDatabase();
	        Cursor res=db.rawQuery("select email from venue where pwd='"+pwd+"' and email='"+email+"'",null);
	        return res;
	    }
		
		public Cursor details(String email)
		{
		    db = t.getWritableDatabase();
		    Cursor res = db.rawQuery("select FamilyEvent,SocialEvent,BusinessEvent,cost,Capacity,AC,ParkingCapacity,VEG,NON_VEG,Catering,OpenTime,CloseTime,Location from venue where email='"+email+"'",null);
		    return res;
		}
		public Cursor detailsv(String vname)
		{
		    db = t.getWritableDatabase();
		    Cursor res = db.rawQuery("select VenueAddr,email,mobileno,FamilyEvent,SocialEvent,BusinessEvent,cost,Capacity,AC,ParkingCapacity,VEG,NON_VEG,Catering from venue where Venuename='"+vname+"'",null);
		    return res;
		}
		public void create(String vname)
		{
		    db=t.getWritableDatabase();
		    db.execSQL("create table "+vname+" (Date TEXT PRIMARY KEY,FM TEXT,FE TEXT,SM TEXT,SE TEXT,BM TEXT,BE TEXT)");
		}
		public boolean insertdate(String date,String vname)
		{
			db=t.getWritableDatabase();
			 ContentValues contentValues = new ContentValues();
			    contentValues.put("Date",date);
			    long result = db.insert(vname,null ,contentValues);
		        if(result == -1)
		            return false;
		        else
		            return true;
			
		}
		public Cursor checkdate(String date,String vname)
		{
		    db = t.getWritableDatabase();
		    //Cursor res = db.rawQuery("select FM,FE,SM,SE,BM,BE from "+vname+" where Date='"+date+"'",null);
		    Cursor res = db.rawQuery("select Date from "+vname,null);
		    return res;
		}
		public Cursor getVenuetable(String vname) {
	        SQLiteDatabase db = t.getWritableDatabase();
	        Cursor res = db.rawQuery("select Date from "+vname,null);
	        return res;
	    }
		public Cursor getimage(String email) {
	        db = t.getWritableDatabase();
	        Cursor res = db.rawQuery("select image from venue where email='"+email+"'",null);
	        return res;
	    }
		public Cursor getavaldate(String vname,String c,String date)
		{
			 SQLiteDatabase db = t.getWritableDatabase();
		        Cursor res = db.rawQuery("select "+c+" from "+vname+" where Date='"+date +"'",null);
		        return res;
		}
		public boolean insertimage(byte[] byteImage,String email) {
	        SQLiteDatabase db = t.getWritableDatabase();
	        ContentValues contentValues = new ContentValues();
	        contentValues.put("image",byteImage);
	       
	        long result = db.update("venue",contentValues,"email= ? ",new String[] {email});
	        if(result == -1)
	            return false;
	        else
	            return true;
	    }
		 public int getCountForCustomProfiles()
		    {
			 SQLiteDatabase db = t.getWritableDatabase();
			 int count=0;
		      Cursor  cursor= db.rawQuery("SELECT COUNT(image) FROM imaget",null);
		         
		        if(cursor.moveToFirst())
		        {
		            count= cursor.getInt(0);
		        }
		        cursor.close();
		        return count;
		    }
		     
	        
		
		
		}

		


