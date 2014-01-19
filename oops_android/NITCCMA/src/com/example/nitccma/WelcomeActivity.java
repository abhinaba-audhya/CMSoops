package com.example.nitccma;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WelcomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		Typeface font1 = Typeface.createFromAsset(getAssets(), "fonts/Yellow_Dog.ttf");
		Typeface font2 = Typeface.createFromAsset(getAssets(), "fonts/f2.ttf");
		 TextView customText1 = (TextView)findViewById(R.id.textView1);
		 TextView customText2 = (TextView)findViewById(R.id.textView2);
		 TextView customText3 = (TextView)findViewById(R.id.textView3);
		 Button b1=(Button)findViewById(R.id.button1);
		 Button b2=(Button)findViewById(R.id.button2);
		 Intent intent=getIntent();
		 String name1=intent.getStringExtra("name");
		 customText1.setTypeface(font1);
		 customText2.setTypeface(font2);
		 customText2.setText(name1);
		 customText3.setTypeface(font1);
		 b1.setTypeface(font2);
		 b2.setTypeface(font2);
		 b1.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//Context context=this;
					//EditText edit1=(EditText)findViewById(R.id.editText1);
					//String s=edit1.getText().toString();
					Intent intent=new Intent(getApplicationContext(),Subjects.class);
					intent.putExtra("next","details");
					startActivity(intent);
					
				}
			});
		/* b2.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//Context context=this;
					//EditText edit1=(EditText)findViewById(R.id.editText1);
					//String s=edit1.getText().toString();
					Intent intent=new Intent(getApplicationContext(),Subjects.class);
					intent.putExtra("next","attendence");
					startActivity(intent);
					
				}
			});*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_welcome, menu);
		return true;
	}

}
