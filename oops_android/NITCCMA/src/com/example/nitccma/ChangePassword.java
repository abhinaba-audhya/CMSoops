package com.example.nitccma;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.nitccma.MainActivity.authenticate;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ChangePassword extends Activity {

	EditText edit1,edit2;
	private ProgressDialog pDialog;
	 
    JSONParser jsonParser = new JSONParser();
    // url to create new product
    private static String url_create_product = "http://allstuffcodes.info/classmanagement/changepass.php";
 
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_password);
		Typeface font1 = Typeface.createFromAsset(getAssets(), "fonts/Yellow_Dog.ttf");
		Typeface font2 = Typeface.createFromAsset(getAssets(), "fonts/f2.ttf");
        //Typeface font2 = Typeface.createFromAsset(getAssets(), "fonts/FEASFBRG.TTF");
        TextView customText1 = (TextView)findViewById(R.id.textView1);
        TextView customText2 = (TextView)findViewById(R.id.textView2);
        Button b1=(Button)findViewById(R.id.button1);
        customText1.setTypeface(font2);
        customText2.setTypeface(font2);
        b1.setTypeface(font2);
        edit1=(EditText)findViewById(R.id.editText1);
        edit2=(EditText)findViewById(R.id.editText2);
		edit1.setTypeface(font2);
		edit2.setTypeface(font2);
		 b1.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View view) {
					// TODO Auto-generated method stub
					//Context context=this;
					/*EditText edit1=(EditText)findViewById(R.id.editText1); *****
					String s=edit1.getText().toString();
					Intent intent=new Intent(getApplicationContext(),WelcomeActivity.class);
					intent.putExtra("name",s);
					startActivity(intent);*/
					edit1=(EditText)findViewById(R.id.editText1);
			        edit2=(EditText)findViewById(R.id.editText2);
					new authenticate().execute();
				}
			});
       
	}
	class authenticate extends AsyncTask<String, String, String> {
		 
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ChangePassword.this);
            pDialog.setMessage("Changing Password..");
            //pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
 
        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {
        	Intent intent=getIntent();
        	String Old_Password = edit1.getText().toString();
            String New_Password = edit2.getText().toString();
            String Teacher_Id=intent.getStringExtra("Teacher_Id");
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            if(Old_Password!="")
            params.add(new BasicNameValuePair("Old_Password", Old_Password));
            if(New_Password!="")
            params.add(new BasicNameValuePair("New_Password", New_Password));
            if(Teacher_Id!="")
            params.add(new BasicNameValuePair("Teacher_Id", Teacher_Id));	
            //params.add(new BasicNameValuePair("description", description));
 
            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_create_product,"POST",params);
 
            // check log cat fro response
            Log.d("Create Response", json.toString());
 
            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);
 
                if (success == 1) {
                    // successfully created product
                    //Intent i = new Intent(getApplicationContext(), AllProductsActivity.class);
                    //startActivity(i);
                    Intent intent1=new Intent(getApplicationContext(),MainActivity.class);
                    //intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		             
		            // Add new Flag to start new Activity
		            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    				startActivity(intent1);
    				finish();
                    // closing this screen
                } else {
                	//Toast.makeText(MainActivity.this, "error",5000).show();
                	Intent intent2=new Intent(getApplicationContext(),ChangePassword.class);
                	intent2.putExtra("Teacher_Id",Teacher_Id);
                	startActivity(intent2);
                	//Toast.makeText(getApplicationContext(),
      				  //    "Retry", Toast.LENGTH_LONG).show();	
                	finish();
                	//edit1.setText("Invalid name or ");
                	//edit2.setText("Invalid password");
                    // failed to create product
                	
                }
            } catch (JSONException e) {
            	//Toast.makeText(MainActivity.this, "error",5000).show();
                e.printStackTrace();
            }
 
            return null;
        }
 
        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
        }
 
    }

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_change_password, menu);
		return true;
	}

}
