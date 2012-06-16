package com.cerspri.games.tod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cerspri.games.tod.db.customManager.DBManager;
import com.cerspri.games.tod.model.ToDQuery;

public class TruthOrDareActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        
        Button coupleButton = (Button) findViewById(R.id.Couples);
        Button teamsButton = (Button) findViewById(R.id.Teams);
        Button settingsButton = (Button) findViewById(R.id.Settings);
        
        int rows = this.loadData("truth");
        
        
        teamsButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(v.getContext(), PlayerSelectionScreen.class);
				startActivityForResult(myIntent, 0);
			}
		});
    }
    
    private int loadData(String name) {
    	int amount = 0;
    	SharedPreferences settings = getPreferences(MODE_WORLD_READABLE);
    	SharedPreferences.Editor editor = settings.edit();
		int version = settings.getInt("version_" + name, 0);
		List<String> data = new ArrayList<String>();
		StringBuilder builder = readFromLink("http://www.cerspri.com/api/tod/get_data.php?" +
				"data="+name+"&version="+new Integer(version).toString());
		boolean changed = false;
		DBManager manager = new DBManager(this);
		System.out.println(builder.toString());
		try {
			JSONObject jsonobj = new JSONObject(builder.toString());
			JSONArray elements = jsonobj.getJSONArray("data");
			amount = elements.length();
			for (int i = 0; i < elements.length(); i++) {
				JSONObject elementobj = elements.getJSONObject(i);
				data.add(elementobj.getString("category"));
				data.add(elementobj.getString("text"));
				data.add(elementobj.getString("mask"));
				data.add(elementobj.getString("couples"));
				if (elementobj.getInt("version") > version){
					version = elementobj.getInt("version");
				}
				changed = true;
			}
			manager.insertTruths(data);
			editor.putInt("version_"+name, version);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return amount;
	}
    
    private StringBuilder readFromLink(String url) {
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		try {
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder;
	}
}