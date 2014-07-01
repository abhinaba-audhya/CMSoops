package com.example.nitccma;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class StudentWelcomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_student_welcome);
		Typeface font1 = Typeface.createFromAsset(getAssets(), "fonts/Yellow_Dog.ttf");
		Typeface font2 = Typeface.createFromAsset(getAssets(), "fonts/f2.ttf");
		 TextView customText1 = (TextView)findViewById(R.id.textView1);
		 TextView customText2 = (TextView)findViewById(R.id.textView2);
		 TextView customText3 = (TextView)findViewById(R.id.textView3);
		 Button b2=(Button)findViewById(R.id.button2);
		 Button b4=(Button)findViewById(R.id.button3);
		 ImageButton b3=(ImageButton)findViewById(R.id.imageButton1); 
		 Intent intent=getIntent();
		 final String Roll=intent.getStringExtra("Roll");
		 final String Name=intent.getStringExtra("Name");
		 customText1.setTypeface(font1);
		 customText2.setTypeface(font2);
		 customText2.setText(Name);
		 customText3.setTypeface(font1);
		 b2.setTypeface(font2);
		 b4.setTypeface(font2);
		 //b3.setTypeface(font2);
		 b2.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//Context context=this;
					//EditText edit1=(EditText)findViewById(R.id.editText1);
					//String s=edit1.getText().toString();
					Intent intent=new Intent(getApplicationContext(),Faculty_details.class);
					intent.putExtra("Roll",Roll);//passing name of student
					startActivity(intent);
					
				}
			});
		 b4.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//Context context=this;
					//EditText edit1=(EditText)findViewById(R.id.editText1);
					//String s=edit1.getText().toString();
					Intent intent=new Intent(getApplicationContext(),Show_Sub_Attendance.class);
					intent.putExtra("next","attendance");//passing option for attendence
					intent.putExtra("Roll",Roll);//passing name of teacher
					startActivity(intent);
					
				}
			});
		 b3.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//Context context=this;
					//EditText edit1=(EditText)findViewById(R.id.editText1);
					//String s=edit1.getText().toString();
					Intent intent=new Intent(getApplicationContext(),Student_Login.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		             
		            // Add new Flag to start new Activity
		            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(intent);
					finish();
					
				}
			});
		 	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_student_welcome, menu);
		return true;
	}

}
