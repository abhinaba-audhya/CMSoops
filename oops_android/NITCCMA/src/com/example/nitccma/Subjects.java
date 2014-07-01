package com.example.nitccma;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Subjects extends ListActivity {
	private ProgressDialog pDialog;

	// URL to get contacts JSON
	private static String url = "http://allstuffcodes.info/classmanagement/all_subject.php";

	// JSON Node names
	private static final String TAG_SUBJECTS = "subjects";
	private static final String TAG_CODE = "Sub_Code";
	private static final String TAG_NAME = "Sub_Name";
	// subjects JSONArray
	JSONArray subjects = null;
	JSONParser jsonParser = new JSONParser();

	// Hashmap for ListView
	ArrayList<HashMap<String, String>> subjectList;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_subjects);
		Intent intent=getIntent();
		 //final String choice=intent.getStringExtra("next");//getting the choice
		 //final String Teacher_Id=intent.getStringExtra("name");//getting the prof name
	    
		subjectList = new ArrayList<HashMap<String, String>>();

		ListView lv = getListView();
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				String Sub_Code = ((TextView) view.findViewById(R.id.code)).getText().toString();
				Intent intent=getIntent();
				 final String choice=intent.getStringExtra("next");//getting the choice
				 final String Teacher_id=intent.getStringExtra("Teacher_Id");
				if(choice.compareTo("details")==0)
				{
					Intent i=new Intent(getApplicationContext(),MainQueryDetailsList.class);
					i.putExtra("next","details");//passing origin for student
					i.putExtra("Sub_Code",Sub_Code);//passing code of subject
					startActivity(i);
					finish();
					
				}
				else if(choice.compareTo("attendance")==0)
				{
					Intent i=new Intent(getApplicationContext(),Attendance.class);
					i.putExtra("next","attendance");//passing origin for student
					i.putExtra("Sub_Code",Sub_Code);//passing code of subject
					i.putExtra("Teacher_Id", Teacher_id);
					startActivity(i);
					finish();
				}
				else if(choice.compareTo("show_attendance")==0)
				{
					Intent i=new Intent(getApplicationContext(),Show_Attendance.class);
					i.putExtra("next","show_attendance");//passing origin for student
					i.putExtra("Sub_Code",Sub_Code);//passing code of subject
					i.putExtra("Teacher_Id", Teacher_id);
					startActivity(i);
					finish();
				}

			}
		});

		// Calling async task to get json
		new GetSubjects().execute();


			  }
	/**
	 * Async task class to get json by making HTTP call
	 * */
	private class GetSubjects extends AsyncTask<String,String,String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			pDialog = new ProgressDialog(Subjects.this);
			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(false);
			pDialog.show();

		}

		@Override
		protected String doInBackground(String... arg) {
			// Creating service handler class instance
			Intent intent=getIntent();
			 //final String choice=intent.getStringExtra("next");//getting the choice
			 final String Teacher_Id=intent.getStringExtra("Teacher_Id");//getting the prof name
			 List<NameValuePair> params = new ArrayList<NameValuePair>();
	            params.add(new BasicNameValuePair("Teacher_Id", Teacher_Id));
			//ServiceHandler sh = new ServiceHandler();

			// Making a request to url and getting response
			//String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
			JSONObject jsonStr = jsonParser.makeHttpRequest(url,"GET", params);

			Log.d("Response: ", "> " + jsonStr.toString());

			if (jsonStr.toString() != null) {
				try {
					//JSONObject jsonObj = new JSONObject(jsonStr.toString());
					
					// Getting JSON Array node
					subjects = jsonStr.getJSONArray(TAG_SUBJECTS);

					// looping through All Contacts
					for (int i = 0; i < subjects.length(); i++) {
						JSONObject c = subjects.getJSONObject(i);
						
						String Sub_Code = c.getString(TAG_CODE);
						String Sub_Name = c.getString(TAG_NAME);
						// tmp hashmap for single contact
						HashMap<String, String> subjects = new HashMap<String, String>();

						// adding each child node to HashMap key => value
						subjects.put(TAG_CODE, Sub_Code);
						subjects.put(TAG_NAME, Sub_Name);
						// adding contact to contact list
						subjectList.add(subjects);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				Log.e("ServiceHandler", "Couldn't get any data from the url");
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
			ListAdapter adapter = new SimpleAdapter(Subjects.this, subjectList,R.layout.list_item_sub, new String[] { TAG_NAME,TAG_CODE }, new int[] { R.id.name,R.id.code });
			setListAdapter(adapter);
		}

	}

	 
}

