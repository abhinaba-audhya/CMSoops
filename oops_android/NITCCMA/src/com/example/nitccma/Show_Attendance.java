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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Show_Attendance extends ListActivity {

	private ProgressDialog pDialog;
	private static String url = "http://allstuffcodes.info/classmanagement/all_student_attend.php";
	private static final String TAG_STUDENTS = "students";
	private static final String TAG_ROLL = "Roll";
	private static final String TAG_FNAME = "Firstname";
	private static final String TAG_LNAME = "Lastname";
	private static final String TAG_NAME = "Name";
	private static final String TAG_EMAIL = "Email";
	private static final String TAG_BRANCH = "Branch";
	private static final String TAG_YEAR = "Year";	
	private static final String TAG_MOBILE = "Mobile";
	private static final String TAG_ABSENT = "Absent_Days";
	private static final String TAG_PRESENT = "Present_Days";
	private static final String TAG_TOTAL = "Total_Days";
	// contacts JSONArray
		JSONArray students = null;

		// Hashmap for ListView
		JSONParser jsonParser = new JSONParser();
		ArrayList<HashMap<String, String>> studentList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show__attendance);
		Intent intent=getIntent();
		 //String choice=intent.getStringExtra("next");
		studentList = new ArrayList<HashMap<String, String>>();

		ListView lv = getListView();
		new GetStudents().execute();

	}
	/**
	 * Async task class to get json by making HTTP call
	 * */
	private class GetStudents extends AsyncTask<String,String,String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			pDialog = new ProgressDialog(Show_Attendance.this);
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
			 List<NameValuePair> params = new ArrayList<NameValuePair>();
	            params.add(new BasicNameValuePair("Sub_Code",Sub_Code));

			// Making a request to url and getting response
			JSONObject jsonStr = jsonParser.makeHttpRequest(url,"GET", params);

			Log.d("Response: ", "> " + jsonStr.toString());

			if (jsonStr.toString() != null) {
				try {
					//JSONObject jsonObj = new JSONObject();
					
					// Getting JSON Array node
					students = jsonStr.getJSONArray(TAG_STUDENTS);

					// looping through All Contacts
					for (int i = 0; i < students.length(); i++) {
						JSONObject c = students.getJSONObject(i);
						
						String Roll = c.getString(TAG_ROLL);
						String Fname = c.getString(TAG_FNAME);
						String Lname = c.getString(TAG_LNAME);
						String name=Fname+" "+Lname;
						String Email = c.getString(TAG_EMAIL);
						String Mobile = c.getString(TAG_MOBILE);
						String Branch = c.getString(TAG_BRANCH);
						String Year = c.getString(TAG_YEAR);
						String Absent_Days = c.getString(TAG_ABSENT);
						String Present_Days = c.getString(TAG_PRESENT);
						String Total_Days = c.getString(TAG_TOTAL);
						
						// tmp hashmap for single contact
						HashMap<String, String> students = new HashMap<String, String>();

						// adding each child node to HashMap key => value
						students.put(TAG_ROLL, Roll);
						students.put(TAG_FNAME, Fname);
						students.put(TAG_FNAME, Fname);
						students.put(TAG_NAME, name);
						students.put(TAG_EMAIL, Email);
						students.put(TAG_MOBILE, Mobile);
						students.put(TAG_BRANCH, Branch);
						students.put(TAG_YEAR, Year);
						students.put(TAG_ABSENT, Absent_Days);
						students.put(TAG_PRESENT, Present_Days);
						students.put(TAG_TOTAL, Total_Days);
						//students.put(TAG_INDEX,Index);
						// adding contact to contact list
						studentList.add(students);
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
			ListAdapter adapter = new SimpleAdapter(
					Show_Attendance.this, studentList,
					R.layout.list_item_show_attendance, new String[] { TAG_NAME,TAG_ROLL,TAG_ABSENT,TAG_PRESENT,TAG_TOTAL }, new int[] { R.id.name,R.id.roll,R.id.attend,R.id.present,R.id.total });

			setListAdapter(adapter);
		}


	
	}
}
