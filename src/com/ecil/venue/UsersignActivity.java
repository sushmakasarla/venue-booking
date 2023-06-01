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

public class UsersignActivity extends Activity {
	EditText et1,et2,et3,et4,et5;
	Button a;
	Usersignup db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_usersign);
		setTitle("User Registration");
		db=new Usersignup(this);
		et1=(EditText)findViewById(R.id.editText1);
		et2=(EditText)findViewById(R.id.editText2);
		et3=(EditText)findViewById(R.id.editText3);
		et4=(EditText)findViewById(R.id.editText4);
		et5=(EditText)findViewById(R.id.editText5);
		onSubmit();
	
	}

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.usersign, menu);
		return true;
	}
	public void onSubmit() {

		a = (Button) findViewById(R.id.button1);

		a.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v1) {

				
				if(et1.getText().toString().length()==0)
				{
					//Toast.makeText(getApplicationContext(), "Name cannot be Blank", Toast.LENGTH_LONG).show();
					et1.setError("Name cannot be Blank");
					return;
				}
				else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(et2.getText().toString()).matches())
				{
					//Validation for Invalid Email Address
					//Toast.makeText(getApplicationContext(),"gfjdhg "+et2.getText().toString(), Toast.LENGTH_LONG).show();
					et2.setError("Invalid Email");
					return;
				}
				else if(!et3.getText().toString().equals(et4.getText().toString()))
				{
					et4.setError("Passwords doesn't match");
					return;
				}
				else if(et5.getText().toString().length()!=10)
				{
					et5.setError("InValid mobile number");
					return;
				}
				Cursor res1=db.emailCheck(et2.getText().toString());
				if(res1.getCount()!=0)
				{
					et2.setError("Email id already exists");
					return;
				}
				 //Toast.makeText(UsersignActivity.this,"before insert "+et2.getText().toString(),Toast.LENGTH_LONG).show();
				boolean isInserted = db.insertData(et1.getText().toString(),
                        et3.getText().toString(),et2.getText().toString(),et5.getText().toString());
                if(isInserted == true)
                {
                    Toast.makeText(UsersignActivity.this,"Registration successful",Toast.LENGTH_LONG).show();
                    //Toast.makeText(UsersignActivity.this,et2.getText().toString(),Toast.LENGTH_LONG).show();
                    Intent i1 = new Intent(UsersignActivity.this,SearchActivity.class);
                    i1.putExtra("uemail",et2.getText().toString());
                    startActivity(i1);
                }
                else
                    Toast.makeText(UsersignActivity.this,"Registration unsuccessful",Toast.LENGTH_LONG).show();

			}
		});

	
	}
	
	
	
}
