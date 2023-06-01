package com.ecil.venue;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class VenuesignActivity extends Activity {
	EditText et1,et2,et3,et4,et5,et6,et7;
	Button a;
	Usersignup db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_venuesign);
		setTitle("Venue Book");
		db=new Usersignup(this);
		et1=(EditText)findViewById(R.id.editText1);
        et2=(EditText)findViewById(R.id.editText2);
        et3=(EditText)findViewById(R.id.editText3);
        et4=(EditText)findViewById(R.id.editText4);
        et5=(EditText)findViewById(R.id.editText5);
        et6=(EditText)findViewById(R.id.editText6);
        et7=(EditText)findViewById(R.id.editText7);
		addListenerOnButton();
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.venuesign, menu);
		return true;
	}

	 public void addListenerOnButton() {
		 
         a = (Button) findViewById(R.id.button1);

         a.setOnClickListener(new View.OnClickListener() {

                         @Override
           public void onClick(View v1) {

          
          if(et1.getText().toString().length()==0)
          {
        	  //Toast.makeText(getApplicationContext(), "Name cannot be Blank", Toast.LENGTH_LONG).show();
              et1.setError("UserName cannot be Blank");
              return;                                           
          }
          else if(et2.getText().toString().length()==0)
          {
                          //Toast.makeText(getApplicationContext(), "Name cannot be Blank", Toast.LENGTH_LONG).show();
                          et2.setError("VenueName cannot be Blank");
                          return;
          }
          else if(et3.getText().toString().length()==0)
          {
                          //Toast.makeText(getApplicationContext(), "Name cannot be Blank", Toast.LENGTH_LONG).show();
                          et3.setError("VenueAddr cannot be Blank");
          }
          else if(!et4.getText().toString().equals(et5.getText().toString()))
          {
                          et5.setError("Passwords doesn't match");
                          return;
          }
          else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(et6.getText().toString()).matches())
          {
                          //Validation for Invalid Email Address
                          //Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_LONG).show();
                          et6.setError("Invalid Email");
                          return;  
          }
          else if(et7.getText().toString().length()!=10)
          {
                          et7.setError("InValid mobile number");
                          return;
          }
          Cursor res1=db.emailCheck1(et6.getText().toString());
			if(res1.getCount()!=0)
			{
				et6.setError("Email id already exists");
				return;
			}
         
         boolean isInserted = db.insertDatav(et1.getText().toString(),et2.getText().toString(),et3.getText().toString(),
                  et4.getText().toString(),et6.getText().toString(),et7.getText().toString());
          if(isInserted == true)
          {
              
        	  Toast.makeText(VenuesignActivity.this,"Registration successful",Toast.LENGTH_LONG).show();
              db.create(et2.getText().toString());
              Bundle b=new Bundle();
        	  Intent i1 = new Intent(VenuesignActivity.this,VenuedetailsActivity.class);
              b.putString("email",et6.getText().toString());
              b.putString("vname",et2.getText().toString());
              i1.putExtras(b);
              startActivity(i1);
          }
           else
              Toast.makeText(VenuesignActivity.this,"Registration unsuccessful",Toast.LENGTH_LONG).show();
      
                         }
});
         
                                               
                                         
                                               
	 }
}
	



