package com.ecil.venue;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
Button a,b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();
    }

    public void addListenerOnButton()
    {
    	a=(Button)findViewById(R.id.button1);
    	b=(Button)findViewById(R.id.button2);
        a.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent d=new Intent(MainActivity.this,UserActivity.class);
		    	startActivity(d);
				
			}
			});
        b=(Button)findViewById(R.id.button2);
        b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent d=new Intent(MainActivity.this,VenueloginActivity.class);
		    	startActivity(d);
				
			}
			});

    }
   
    
 
}