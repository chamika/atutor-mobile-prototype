package com.android.atsocial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class Main extends Activity {
	
	private String URI;
	private int userID;
	private String type;
	private String suffix;
	private RadioButton radiobuttonPeople;
	private RadioButton radiobuttonGroup;
	private RadioButton radiobuttonActivities;
	private RadioButton radiobuttonAppData; 
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        EditText edittextSuffix = (EditText) findViewById(R.id.EditText_suffix);
        edittextSuffix.setText("/@all");
        
        OnClickListener radio_listener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d("radio","radio onClick executed");
				if(radiobuttonPeople.isChecked() == true){
					type = "people";
				}
				else if(radiobuttonGroup.isChecked() == true ){
					type = "groups";
				}
				else if(radiobuttonActivities.isChecked() == true){
					type = "activities";
				}
				else if(radiobuttonAppData.isChecked() == true){
					type = "appdata";
				}
				else
					type = null;
								
			}
		};
        
        radiobuttonPeople = (RadioButton) findViewById(R.id.RadioButton_people);
        radiobuttonGroup = (RadioButton) findViewById(R.id.RadioButton_group);
        radiobuttonActivities = (RadioButton) findViewById(R.id.RadioButton_activities);
        radiobuttonAppData = (RadioButton) findViewById(R.id.RadioButton_appdata);
        
        
        radiobuttonPeople.setOnClickListener(radio_listener);
        radiobuttonGroup.setOnClickListener(radio_listener);
        radiobuttonActivities.setOnClickListener(radio_listener);
        radiobuttonAppData.setOnClickListener(radio_listener);
                
        
        Button ViewButton = (Button) findViewById(R.id.Button_View);
        ViewButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setData();
				Data data = new Data(URI, userID, type, suffix);
				startActivity(new Intent(Main.this,ShowData.class));
			}
		});
        
               
        
    }
    
    public void setData(){
    	EditText edittextURI = (EditText) findViewById(R.id.EditText_URI);
        EditText edittextUserID = (EditText) findViewById(R.id.EditText_UID);
        EditText edittextSuffix = (EditText) findViewById(R.id.EditText_suffix);
        
        URI = edittextURI.getText().toString();
        userID =Integer.parseInt(edittextUserID.getText().toString());
        suffix = edittextSuffix.getText().toString();
        
        if(radiobuttonPeople.isChecked() == true){
			type = "people";
		}
		else if(radiobuttonGroup.isChecked() == true ){
			type = "groups";
		}
		else if(radiobuttonActivities.isChecked() == true){
			type = "activities";
		}
		else if(radiobuttonAppData.isChecked() == true){
			type = "appdata";
		}
		else
			type = null;
        
    }
}