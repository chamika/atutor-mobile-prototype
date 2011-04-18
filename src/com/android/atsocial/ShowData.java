package com.android.atsocial;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ShowData extends Activity {

	private String URI;
	private int userID;
	private String type;
	private String suffix;
	private TextView textview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showdata);

		Data dat = new Data();

		URI = dat.getURI();
		userID = dat.getUserID();
		type = dat.getType();
		suffix = dat.getSuffix();

		runHTTPget();

		Log.d("Data","Successfully fetched response URL : " +
				URI + type + "/" + Integer.toString(userID)
				+ suffix);
	}

	private void runHTTPget() {
		BufferedReader in = null;
		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet();
			// request.setURI(new
			// URI("http://social.atutor.ca/shindig/php/social/rest/people/219/@self"));
			request.setURI(new URI(URI + type + "/" + Integer.toString(userID)
					+ suffix));
			HttpResponse response = client.execute(request);
			in = new BufferedReader(new InputStreamReader(response.getEntity()
					.getContent()));
			StringBuffer sb = new StringBuffer("");
			String line = "";
			String NL = System.getProperty("line.separator");
			while ((line = in.readLine()) != null) {
				sb.append(line + NL);
			}
			in.close();
			String page = sb.toString();
			Log.d("Response", "response: " + page);

			textview = (TextView) findViewById(R.id.TextView_Response);
			textview.setText(page);
			
			useJSON(page);

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void useJSON(String Response){
		//code for getting all people connected to user.
		
		if(type.equals("people") && suffix.equals("/@all")){
			try {
				JSONObject jsonobject = new JSONObject(Response);
				JSONArray dataArray= jsonobject.getJSONArray("entry");
				
				Log.d("JSON", "JSON array built");
				
				Log.d("JSON","Number of entrees in array: " + dataArray.length());
				
				textview.setText("");
				
				for (int i = 0; i < dataArray.length(); i++) {
		            if (!dataArray.isNull(i)) {
		                JSONObject item = dataArray.getJSONObject(i);
		                if (item.has("id")) {
		                    String id = item.getString("id");
		                    Log.d("JSON Array", "id= " + id);
		                    textview.setText( textview.getText() +  "id=" + id);
		                }
		                if (item.has("displayName")) {
		                    String name = item.getString("displayName");
		                    Log.d("JSON Array", "Name= " + name);
		                    textview.setText( textview.getText()+ "\n" +  "Name=" + name);
		                }
		                if (item.has("profileUrl")) {
		                    String profileUrl = item.getString("profileUrl");
		                    Log.d("JSON Array", "Profile URL= " + profileUrl);
		                    textview.setText( textview.getText()+ "\n" +  "Profile URL=" + profileUrl);
		                }
		            }
		            
		            textview.setText(textview.getText() + "\n");
		           	            
		        }
				
				textview.setText(textview.getText()+ "\n" + "Start Index: " + jsonobject.getInt("startIndex"));
				textview.setText(textview.getText()+ "\n" + "Total results: " + jsonobject.getInt("totalResults"));
				textview.setText(textview.getText()+ "\n" + "Items per page: " + jsonobject.getInt("itemsPerPage"));
				textview.setText(textview.getText()+ "\n" + "Sorted: " + jsonobject.getBoolean("sorted"));
				
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.d("JSON","Error in JSON object or Array");
			}
		
		}
		
	}

}
