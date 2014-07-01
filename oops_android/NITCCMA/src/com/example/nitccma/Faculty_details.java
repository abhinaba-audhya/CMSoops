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
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Faculty_details extends ListActivity {
	
	String h="Banchod";
	int length=0;
	private ProgressDialog pDialog;

	// URL to get contacts JSON
	private static String url = "http://allstuffcodes.info/classmanagement/all_fac_det.php";

	// JSON Node names
	//private static final String TAG_CONTACTS = "contacts";
	private static final String TAG_FACULTY = "faculty";
	//private static final String TAG_ROLL = "Roll";
	private static final String TAG_FNAME = "T_Firstname";
	private static final String TAG_LNAME = "T_Lastname";
	private static final String TAG_NAME = "Name";
	private static final String TAG_EMAIL = "Email";
	private static final String TAG_MOBILE = "Mobile";
	//private static final String TAG_INDEX = "1";
	//private static final String TAG_PHONE_OFFICE = "office";
	
	// contacts JSONArray
	JSONArray facs = null;

	// Hashmap for ListView
	JSONParser jsonParser = new JSONParser();
	ArrayList<HashMap<String, String>> facultyList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_faculty_details);
		facultyList = new ArrayList<HashMap<String, String>>();

		ListView lv = getListView();
				// Calling async task to get json
		new GetFac().execute();
	}

	/**
	 * Async task class to get json by making HTTP call
	 * */
	private class GetFac extends AsyncTask<String,String,String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			pDialog = new ProgressDialog(Faculty_details.this);
			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(false);
			pDialog.show();

		}

		@Override
		protected String doInBackground(String... arg) {
			// Creating service handler class instance
			// Making a request to url and getting response
			//Intent intent=getIntent();
			 //final String Roll= intent.getStringExtra("Roll");//getting the prof name
			 List<NameValuePair> params = new ArrayList<NameValuePair>();
	           // params.add(new BasicNameValuePair("Roll",Roll));

			// Making a request to url and getting response
			JSONObject jsonStr = jsonParser.makeHttpRequest(url,"GET", params);

			Log.d("Response: ", "> " + jsonStr.toString());

			
				try {
					//JSONObject jsonObj = new JSONObject();
					
					// Getting JSON Array node
					facs = jsonStr.getJSONArray(TAG_FACULTY);
					// looping through All Contacts
					for (int i = 0; i < facs.length(); i++) {
						JSONObject c = facs.getJSONObject(i);
						
						//String Roll = c.getString(TAG_ROLL);
						String Fname = c.getString(TAG_FNAME);
						String Lname = c.getString(TAG_LNAME);
						String name=Fname+" "+Lname;
						String Email = c.getString(TAG_EMAIL);
						String Mobile = c.getString(TAG_MOBILE);
						 //h = c.getString(TAG_MOBILE);
						
						// tmp hashmap for single contact
						HashMap<String, String> facs1 = new HashMap<String, String>();

						// adding each child node to HashMap key => value
						facs1.put(TAG_FNAME, Fname);
						facs1.put(TAG_LNAME, Lname);
						facs1.put(TAG_NAME, "NAME : "+name);
						facs1.put(TAG_EMAIL,"EMAIL : "+Email);
						facs1.put(TAG_MOBILE,"PHONE-NO : "+ Mobile);
						// adding contact to contact list
						facultyList.add(facs1);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			 /*else {
				Log.e("ServiceHandler", "Couldn't get any data from the url");
			}*/

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
			
			ListAdapter adapter = new SimpleAdapter(
					Faculty_details.this, facultyList,
					R.layout.list_fac_det, new String[] { TAG_NAME,TAG_EMAIL,TAG_MOBILE }, new int[] { R.id.name,R.id.email,R.id.ph_no });

			setListAdapter(adapter);
		}

	}
}

