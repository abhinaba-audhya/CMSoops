package com.example.nitccma;

import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.example.nitccma.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Attendance extends ListActivity{

	private ProgressDialog pDialog;

	// URL to get contacts JSON
	private static String url = "http://allstuffcodes.info/classmanagement/all_student.php";
	
	// JSON Node names
	//private static final String TAG_CONTACTS = "contacts";
	private static final String TAG_STUDENTS = "students";
	private static final String TAG_ROLL = "Roll";
	private static final String TAG_FNAME = "Firstname";
	private static final String TAG_LNAME = "Lastname";
	private static final String TAG_NAME = "Name";
	private static final String TAG_EMAIL = "Email";
	private static final String TAG_BRANCH = "Branch";
	private static final String TAG_YEAR = "Year";	
	private static final String TAG_MOBILE = "Mobile";
	//private static final String TAG_INDEX = "1";
	//private static final String TAG_PHONE_OFFICE = "office";
	
	// contacts JSONArray
	JSONArray students = null;
	ListAdapter adapter,a1;
	int len=0;
	String s1[]=new String[2000];
	String s2[]=new String[2000];
	int i=0;

	// Hashmap for ListView
	JSONParser jsonParser = new JSONParser();
	ArrayList<HashMap<String, String>> studentList;
	ArrayList<Country> countryList ;//= new ArrayList<Country>();
	MyCustomAdapter dataAdapter = null;
	//ListView listView;
	


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_attendence);
		Typeface font3 = Typeface.createFromAsset(getAssets(), "fonts/You.ttf");
		 Intent intent=getIntent();
		 TextView customText1 = (TextView)findViewById(R.id.textView1);
		 customText1.setTypeface(font3);
		 //String choice=intent.getStringExtra("next");
		 //String choice=intent.getStringExtra("next");
		studentList = new ArrayList<HashMap<String, String>>();
		Typeface font2 = Typeface.createFromAsset(getAssets(), "fonts/f2.ttf");
		Button button1=(Button)findViewById(R.id.button1);
		button1.setTypeface(font2);
		button1.setEnabled(false);
		button1.setVisibility(View.INVISIBLE);
		ListView lv = getListView();
		CheckBox ck = (CheckBox)findViewById(R.id.check);
				// Calling async task to get json
		new GetStudents().execute();
		//displayListView();
		checkButtonClick();
		
	}

	/**
	 * Async task class to get json by making HTTP call
	 * */
	private class GetStudents extends AsyncTask<String,String,String> {

		@Override
		protected void onPreExecute() 
		{
			super.onPreExecute();
			// Showing progress dialog
			pDialog = new ProgressDialog(Attendance.this);
			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(false);
			pDialog.show();

		}

		@Override
		protected String doInBackground(String... arg) 
		{
			// Creating service handler class instance
			// Making a request to url and getting response
			Intent intent=getIntent();
			 final String Sub_Code=intent.getStringExtra("Sub_Code");//getting the sub code
			 List<NameValuePair> params = new ArrayList<NameValuePair>();
	            params.add(new BasicNameValuePair("Sub_Code",Sub_Code));
	            
			// Making a request to url and getting response
			JSONObject jsonStr = jsonParser.makeHttpRequest(url,"GET", params);

			Log.d("Response: ", "> " + jsonStr.toString());

			if (jsonStr.toString() != null)
			{
				try {
					//JSONObject jsonObj = new JSONObject();
					
					// Getting JSON Array node
					students = jsonStr.getJSONArray(TAG_STUDENTS);
					//String s1="",s2="";
					len=students.length();
					// looping through All Contacts
					for (int i = 0; i < students.length(); i++)
					{
						JSONObject c = students.getJSONObject(i);
						String Roll = c.getString(TAG_ROLL);
						String Fname = c.getString(TAG_FNAME);
						String Lname = c.getString(TAG_LNAME);
						String name=Fname+" "+Lname;
						String Email = c.getString(TAG_EMAIL);
						String Mobile = c.getString(TAG_MOBILE);
						String Branch = c.getString(TAG_BRANCH);
						String Year = c.getString(TAG_YEAR);
						// tmp hashmap for single contact
						HashMap<String, String> students = new HashMap<String, String>();
						//countryList = new ArrayList<Country>();
						// adding each child node to HashMap key => value
						students.put(TAG_ROLL, Roll);
						students.put(TAG_FNAME, Fname);
						students.put(TAG_FNAME, Fname);
						students.put(TAG_NAME, name);
						students.put(TAG_EMAIL, Email);
						students.put(TAG_MOBILE, Mobile);
						students.put(TAG_BRANCH, Branch);
						students.put(TAG_YEAR, Year);
						//students.put(TAG_INDEX,Index);
						// adding contact to contact list
						studentList.add(students);
						
						s1[i]=studentList.get(i).get(TAG_NAME).toString();
						
						s2[i]=studentList.get(i).get(TAG_ROLL).toString();
						
						/*Country country = new Country(s1,s2,true);
						countryList.add(country);*/
					}
				}
				catch (JSONException e) 
				{
					e.printStackTrace();
				}
			} 
			else
			{
				Log.e("ServiceHandler", "Couldn't get any data from the url");
			}

			return null;
		}

		@Override
		protected void onPostExecute(String result)
		{
			super.onPostExecute(result);
			// Dismiss the progress dialog
			if (pDialog.isShowing())
				pDialog.dismiss();
			/**
			 * Updating parsed JSON data into ListView
			 * */
			 /*adapter = new SimpleAdapter(
					Attendance.this, studentList,
					R.layout.list_item_attend, new String[] { TAG_NAME,TAG_ROLL }, new int[] { R.id.name,R.id.roll });

			setListAdapter(adapter);*/
			displayListView();
			Button button1=(Button)findViewById(R.id.button1);
			button1.setVisibility(View.VISIBLE);
			button1.setEnabled(true);
		}

	}
	private void displayListView()
	{
		//String s1="",s2="";
		countryList = new ArrayList<Country>();
		
		for (int i = 0; i < len; i++)
		{  
			Country country = new Country(s1[i],s2[i],false);
			countryList.add(country);
		}
		dataAdapter = new MyCustomAdapter(Attendance.this,R.layout.list_item_attend, countryList);
		  // Assign adapter to ListView
		ListView listView = getListView();
		listView.setAdapter(dataAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			   public void onItemClick(AdapterView<?> parent, View view,
			     int position, long id) {
			    // When clicked, show a toast with the TextView text
			    Country country = (Country) parent.getItemAtPosition(position);
			    Toast.makeText(getApplicationContext(),
			      "Clicked on Row: " + country.getName(), 
			      Toast.LENGTH_LONG).show();
			   }
			  });
	}
	
	private class MyCustomAdapter extends ArrayAdapter<Country> {
		 
		  private ArrayList<Country> countryList;
		 
		  public MyCustomAdapter(Context context, int textViewResourceId, 
		    ArrayList<Country> countryList) {
		   super(context, textViewResourceId, countryList);
		   this.countryList = new ArrayList<Country>();
		   this.countryList.addAll(countryList);
		  }
		 
		  private class ViewHolder {
		   TextView roll;
		   TextView name;
		   CheckBox chk;
		  }
		 
		  @Override
		  public View getView(int position, View convertView, ViewGroup parent) {
		 
		   ViewHolder holder = null;
		   Log.v("ConvertView", String.valueOf(position));
		 
		   if (convertView == null) {
		   LayoutInflater vi = (LayoutInflater)getSystemService(
		     Context.LAYOUT_INFLATER_SERVICE);
		   convertView = vi.inflate(R.layout.list_item_attend, null);
		 
		   holder = new ViewHolder();
		   holder.roll = (TextView) convertView.findViewById(R.id.roll);
		   holder.name = (TextView) convertView.findViewById(R.id.name);
		   holder.chk = (CheckBox) convertView.findViewById(R.id.check);
		   convertView.setTag(holder);
		 
		    holder.chk.setOnClickListener( new View.OnClickListener() {  
		     public void onClick(View v) {  
		      CheckBox cb = (CheckBox) v ;  
		      Country country = (Country) cb.getTag();  
		      Toast.makeText(getApplicationContext(),
		       "Clicked on Checkbox: " + cb.getText() +
		       " is " + cb.isChecked(), 
		       Toast.LENGTH_LONG).show();
		      country.setSelected(cb.isChecked());
		     }  
		    });  
		   } 
		   else {
		    holder = (ViewHolder) convertView.getTag();
		   }
		 
		   Country country = countryList.get(position);
		   holder.roll.setText(" (" +  country.getRoll() + ")");
		   holder.name.setText(country.getName());
		   holder.chk.setChecked(country.isSelected());
		   holder.chk.setTag(country);
		 
		   return convertView;
		 
		  }
		 
		 }
	 private void checkButtonClick() {
		 
		 
		  Button myButton = (Button) findViewById(R.id.button1);
		  myButton.setOnClickListener(new OnClickListener() {
		 
		   @Override
		   public void onClick(View v) {
		 
		    StringBuffer responseText = new StringBuffer();
		    String rollText="";
		    responseText.append("The following were selected...\n");
		    int j=0;
		    ArrayList<Country> countryList = dataAdapter.countryList;
		    for(int i=0;i<countryList.size();i++)
		    {
		    	Country country = countryList.get(i);
		     if(country.isSelected()){
		      responseText.append("\n" + country.getName());
		      rollText+=country.getRoll()+" ";
		     }
		    }
		    /*Toast.makeText(getApplicationContext(),
				      responseText, Toast.LENGTH_LONG).show();*/
				 
		    Intent intent=getIntent();
		    //Intent i;
			String Sub_Code=intent.getStringExtra("Sub_Code");//getting the sub code
			String Teacher_Id=intent.getStringExtra("Teacher_Id");//getting the sub code
			//String Teacher_Id=intent.getStringExtra("Teacher_Id");//getting the sub code
			Intent i=new Intent(getApplicationContext(),Confirm_Attend.class);
			//intent.putExtra("len", j);
			i.putExtra("Sub_Code",Sub_Code);//passing name of teacher
			i.putExtra("Stu_Roll",rollText);//passing name of teacher
			i.putExtra("Teacher_Id",Teacher_Id);
			startActivity(i);
			finish();
		    
		   }
		  });
		 
		 }
	

}