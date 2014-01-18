package com.example.nitccma;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {


	
	
	
	protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Typeface font1 = Typeface.createFromAsset(getAssets(), "fonts/Yellow_Dog.ttf");
		Typeface font2 = Typeface.createFromAsset(getAssets(), "fonts/f2.ttf");
        //Typeface font2 = Typeface.createFromAsset(getAssets(), "fonts/FEASFBRG.TTF");
        TextView customText1 = (TextView)findViewById(R.id.textView1);
        TextView customText2 = (TextView)findViewById(R.id.textView2);
        TextView customText3 = (TextView)findViewById(R.id.textView3);
        TextView customText4 = (TextView)findViewById(R.id.textView4);
        TextView customText5 = (TextView)findViewById(R.id.textView5);
        TextView customText6 = (TextView)findViewById(R.id.textView6);
        TextView customText7 = (TextView)findViewById(R.id.textView7);
        Button b1=(Button)findViewById(R.id.button1);
        customText1.setTypeface(font1);
        customText2.setTypeface(font1);
        customText3.setTypeface(font1);
        customText4.setTypeface(font1);
        customText5.setTypeface(font2);
        customText6.setTypeface(font2);
        customText7.setTypeface(font2);
        b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Context context=this;
				EditText edit1=(EditText)findViewById(R.id.editText1);
				String s=edit1.getText().toString();
				Intent intent=new Intent(getApplicationContext(),WelcomeActivity.class);
				intent.putExtra("name",s);
				startActivity(intent);
				
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
