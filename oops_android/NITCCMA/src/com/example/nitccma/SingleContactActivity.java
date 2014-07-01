package com.example.nitccma;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.example.nitccma.R;
//import info.androidhive.jsonparsing.R;

public class SingleContactActivity  extends Activity {
	
	// JSON node keys
	private static final String TAG_NAME = "Name";
	private static final String TAG_ROLL = "Roll";
	private static final String TAG_EMAIL = "Email";
	private static final String TAG_MOBILE = "Mobile";
	//private static final String TAG_NAME = "Name";
	//private static final String TAG_EMAIL = "Email";
	private static final String TAG_BRANCH = "Branch";
	private static final String TAG_YEAR = "Year";	
	//private static final String TAG_MOBILE = "Mobile";

	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_contact);
        
        // getting intent data
        Intent in = getIntent();
        
        // Get JSON values from previous intent
        String name = in.getStringExtra(TAG_NAME);
        String email = in.getStringExtra(TAG_EMAIL);
        String mobile = in.getStringExtra(TAG_MOBILE);
        String roll = in.getStringExtra(TAG_ROLL);
        String branch = in.getStringExtra(TAG_BRANCH);
        String year = in.getStringExtra(TAG_YEAR);
        
        // Displaying all values on the screen
        TextView lblName = (TextView) findViewById(R.id.name_label);
        TextView lblRoll = (TextView) findViewById(R.id.roll_label);
        TextView lblEmail = (TextView) findViewById(R.id.email_label);
        TextView lblMobile = (TextView) findViewById(R.id.mobile_label);
        TextView lblBranch = (TextView) findViewById(R.id.branch_label);
        TextView lblYear = (TextView) findViewById(R.id.year_label);
        
        
        lblName.setText(name);
        lblRoll.setText(roll);
        lblEmail.setText(email);
        lblMobile.setText(mobile);
        lblBranch.setText(branch);
        lblYear.setText(year);
        
    }
}
