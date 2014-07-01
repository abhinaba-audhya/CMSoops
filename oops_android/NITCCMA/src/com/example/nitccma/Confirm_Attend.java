package com.example.nitccma;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class Confirm_Attend extends Activity {

	private ProgressDialog pDialog;
	Handler handler = new Handler() ;
	// URL to get contacts JSON
	private static String url = "http://allstuffcodes.info/classmanagement/take_attendance.php";

	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_MSG = "message";
	// contacts JSONArray
	JSONArray students = null;
	String Teacher_Id="";
	int success=0;
	String msg="";
	// Hashmap for ListView
	JSONParser jsonParser = new JSONParser();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirm__attend);
		Intent intent=getIntent();
		Teacher_Id=intent.getStringExtra("Teacher_Id");
		// Listview on item click listener
		// Calling async task to get json
		new GetStudents().execute();
		
		
		
		
		/*handler.postDelayed(runnable, delayinmilliseconds(2000));

		final Runnable runnable = new Runnable()
		{
		    public void run() 
		    {
		       //start the new activity here.
		    }
		};*/
			
		
		
	}
	/**
	 * Async task class to get json by making HTTP call
	 * */
	private class GetStudents extends AsyncTask<String,String,String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			pDialog = new ProgressDialog(Confirm_Attend.this);
			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(false);
			pDialog.show();

		}

		@Override
		protected String doInBackground(String... arg) {
			// Creating service handler class instance
			// Making a request to url and getting response
			Intent intent=getIntent();
			 final String Sub_Code=intent.getStringExtra("Sub_Code");//getting the prof name
			 final String Stu_Roll=intent.getStringExtra("Stu_Roll");
			 List<NameValuePair> params = new ArrayList<NameValuePair>();
	            params.add(new BasicNameValuePair("Sub_Code",Sub_Code));
	            params.add(new BasicNameValuePair("Stu_Roll",Stu_Roll));
			// Making a request to url and getting response
			JSONObject jsonStr = jsonParser.makeHttpRequest(url,"POST", params);

			Log.d("Response: ", "> " + jsonStr.toString());

			try
			{
				success = jsonStr.getInt(TAG_SUCCESS);
				msg= jsonStr.getString(TAG_MSG);
                
			}catch (JSONException e) {
                e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			// Dismiss the progress dialog
			if (pDialog.isShowing())
				pDialog.dismiss();
			/**
			 * Updating parsed JSON data into ListView
			 * */
			if (success == 1)
            {
                // successfully created product
            	new Timer().schedule(new TimerTask(){
    			    public void run() {
    			    	
    			    	Intent i=new Intent(Confirm_Attend.this, WelcomeActivity.class);
    			    	i.putExtra("Teacher_Id",Teacher_Id);
    			    	startActivity(i);
    			    	finish();
    			    	
    			        
    			        //finish();
    			    }
    			}, 10000);
    			Toast.makeText(getApplicationContext(),
    				      msg, Toast.LENGTH_LONG).show();				
            } 
            else
            {
            	Toast.makeText(getApplicationContext(),
  				      msg, Toast.LENGTH_LONG).show();				
            }
			
		}

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_confirm__attend, menu);
		return true;
	}

}
