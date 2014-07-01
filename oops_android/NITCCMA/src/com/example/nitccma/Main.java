package com.example.nitccma;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.Menu;

public class Main extends Activity implements OnGestureListener  {
	private GestureDetector gDetector;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Typeface font1 = Typeface.createFromAsset(getAssets(), "fonts/Yellow_Dog.ttf");
		Typeface font2 = Typeface.createFromAsset(getAssets(), "fonts/f2.ttf");
		TextView customText1 = (TextView)findViewById(R.id.textView1);
        TextView customText2 = (TextView)findViewById(R.id.textView2);
        TextView customText3 = (TextView)findViewById(R.id.textView3);
        TextView customText4 = (TextView)findViewById(R.id.textView4);
        TextView customText5 = (TextView)findViewById(R.id.textView5);
        TextView customText6 = (TextView)findViewById(R.id.textView6);
        TextView customText7 = (TextView)findViewById(R.id.textView7);
        TextView customText8 = (TextView)findViewById(R.id.textView8);
        customText1.setTypeface(font2);
        customText2.setTypeface(font2);
        customText3.setTypeface(font2);
        customText4.setTypeface(font2);
        customText5.setTypeface(font1);
        customText6.setTypeface(font1);
        customText7.setTypeface(font1);
        customText8.setTypeface(font1);
        
		gDetector = new GestureDetector(this);
	}
	@Override
    public boolean onTouchEvent(MotionEvent me) {
        return gDetector.onTouchEvent(me);
    }

	@Override
	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onFling(MotionEvent start, MotionEvent finish, float xVelocity, float yVelocity) {
		if (start.getRawX() < finish.getRawX()) {
			//((ImageView)findViewById(R.id.image_place_holder)).setImageResource(R.drawable.down); 
			Intent intent = new Intent(Main.this, MainActivity.class);
	        startActivity(intent);
		} 
		else if (start.getRawX() > finish.getRawX()) {
			//((ImageView)findViewById(R.id.image_place_holder)).setImageResource(R.drawable.up); 
			Intent intent = new Intent(Main.this, Student_Login.class);
	        startActivity(intent);
		}
		else if (start.getRawY() > finish.getRawY()) {
			Intent intent = new Intent(Main.this, MainActivity.class);
	        startActivity(intent);
		}
		else
		{
			Intent intent = new Intent(Main.this, Student_Login.class);
	        startActivity(intent);

		}
		
		
		return true;
	}

	@Override
	public void onLongPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
