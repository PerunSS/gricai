package com.cerspri.star.NickiMinaj;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

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
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cerspri.star.NickiMinaj.widget.MultiDirectionSlidingDrawer;

/**
 * 
 * @author churava main application activity
 * 
 */

public class NickiStarActivity extends Activity {

	Button toggleMenuButton;
	Button factsButton;
	Button newsButton;
	Button pictButton;
	Button videosButton;
	MultiDirectionSlidingDrawer mDrawer;
	MultiDirectionSlidingDrawer factsDrawer;
	Button quotesButton;
	TextView scrollText;
	Button randomButton;
	Button shareButton;
	int state;
	AlertDialog alert;
	private Map<String,Integer> currents;
	private Map<String,List<String>> texts;
	ProgressDialog progressDialog;

	// Done on activity creation
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		checkForNetworkAndStart();
		new LoaderTask().execute("Loader");	
	}
	
	
	private void checkForNetworkAndStart(){
		if(!isNetworkAvailable()){
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("No internet connection")
			       .setCancelable(false)
			       .setPositiveButton("Try again", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			               checkForNetworkAndStart(); 
			           }
			       })
			       .setNegativeButton("Close", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			        	   NickiStarActivity.this.finish();
			           }
			       });
			AlertDialog alert = builder.create();
			alert.show();
		} else {
			setContentView(R.layout.main);			
			state = 0;

			// layout objects asignment

			toggleMenuButton = (Button) findViewById(R.id.toggle_menu);
			mDrawer = (MultiDirectionSlidingDrawer) findViewById(R.id.menuDrawer);
			factsButton = (Button) findViewById(R.id.facts_button);
			factsDrawer = (MultiDirectionSlidingDrawer) findViewById(R.id.factsDrawer);
			quotesButton = (Button) findViewById(R.id.quotes_button);
			newsButton = (Button) findViewById(R.id.news_button);
			pictButton = (Button) findViewById(R.id.pict_button);
			videosButton = (Button) findViewById(R.id.videos_button);
			scrollText = (TextView) findViewById(R.id.scroll_text);
			randomButton = (Button) findViewById(R.id.random_button);
			shareButton = (Button) findViewById(R.id.share_button);
			
			mDrawer.animateOpen();
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Coming soon!!!");
			alert = builder.create();

			// listener for opening/closing menu

			toggleMenuButton.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					if (!mDrawer.isOpened()) {
						if (factsDrawer.isOpened()) {
							factsDrawer.animateClose();
						}
						mDrawer.animateOpen();
						toggleMenuButton
								.setBackgroundResource(R.drawable.close_menu_button);
					} else {
						switch (state) {
						case 1:
							factsButton.performClick();
							break;
						case 2:
							quotesButton.performClick();
							break;
						default:
							mDrawer.animateClose();
							toggleMenuButton
									.setBackgroundResource(R.drawable.open_menu_button);
						}

					}
				}
			});

			// listener for facts button

			factsButton.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mDrawer.animateClose();
					factsDrawer.animateOpen();
					state = 1;
					toggleMenuButton
							.setBackgroundResource(R.drawable.open_menu_button);
					scrollText.setText(getNext("fact"));
					scrollText.setVisibility(View.VISIBLE);
				}
			});

			// listener for quotes button
			quotesButton.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mDrawer.animateClose();
					factsDrawer.animateOpen();
					state = 2;
					toggleMenuButton
							.setBackgroundResource(R.drawable.open_menu_button);
					scrollText.setText(getNext("quote"));
				}
			});

			// listener for news button
			newsButton.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					state = 0;
					alert.show();
					final Timer t = new Timer();
					t.schedule(new TimerTask() {
						public void run() {
							alert.dismiss();
							t.cancel();
						}
					}, 2000);
				}
			});

			// listener for pictures button
			pictButton.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					state = 0;
					alert.show();
					final Timer t = new Timer();
					t.schedule(new TimerTask() {
						public void run() {
							alert.dismiss();
							t.cancel();
						}
					}, 2000);
				}
			});

			// listener for videos button
			videosButton.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					state = 0;
					alert.show();
					final Timer t = new Timer();
					t.schedule(new TimerTask() {
						public void run() {
							alert.dismiss();
							t.cancel();
						}
					}, 2000);
				}
			});
			
			randomButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					String text = "";
					if(state == 1){
						text = getNext("fact");
					}else if(state == 2){
						text = getNext("quote");
					}
					System.out.println(text);
					scrollText.setText(text);
				}
			});

		}
	}
	
	String getNext(String name){
		List<String> data = texts.get(name);
		int index = (currents.get(name)+1) % data.size();
		currents.put(name, index);
		return data.get(index);		
	}

	private List<String> getData(String name) {
		int version = getPreferences(MODE_WORLD_READABLE).getInt("version_"+name, 0);
		System.out.println("verzija: "+version);
		List<String> data = new ArrayList<String>();
		try {
			Scanner sc = new Scanner(openFileInput(name));
			System.out.println("citam iz fajla");
			while (sc.hasNext()) {
				data.add(sc.nextLine());
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println(name + ": file not found");
		}
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(
				"http://www.cerspri.com/api/stars/get_data.php?" + 
						"star=" + getString(R.string.app_name).replaceAll(" ", "%20") + 
						"&text_type=" + name
						+ "&version=" + version); 
		// "http://www.google.com/search?q=nicki+minaj&hl=en&prmd=imvnso&source=lnms&tbm=nws"
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
		//System.out.println(builder);
		boolean changed = false;
		try {
			JSONObject jsonobj = new JSONObject(builder.toString());
			JSONArray elements = jsonobj.getJSONArray("data");
			for(int i=0;i<elements.length();i++){
				JSONObject elementobj = elements.getJSONObject(i);
				if(elementobj.getString("text_type").equalsIgnoreCase(name)){
					data.add(elementobj.getString("text_value"));
					if(version<elementobj.getInt("text_version")){
						version = elementobj.getInt("text_version");
					}
					if(!changed){
						System.out.println("dovuko nove podatke");
					}
					changed = true;
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		SharedPreferences preferences = getPreferences(MODE_WORLD_WRITEABLE);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putInt("version_"+name, version);
		editor.commit();
		if(changed){
			try {
				PrintWriter writer = new PrintWriter(openFileOutput(name, MODE_WORLD_WRITEABLE));
				System.out.println("pishem u fajl");
				for(String value:data){
					writer.println(value);
				}
				writer.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		System.out.println("verzija: "+version);
		return data;
	}

	@Override
	public void onContentChanged() {
		super.onContentChanged();

	}
	

	private class LoaderTask extends AsyncTask<String, Void, Void>{

		@Override
		protected Void doInBackground(String... params) {
			currents = new HashMap<String, Integer>();
			currents.put("fact", 0);
			currents.put("quote",0);
			List<String> quotes = getData("quote");
			List<String> facts = getData("fact");
			Collections.shuffle(quotes);
			Collections.shuffle(facts);
			texts = new HashMap<String, List<String>>();
			texts.put("quote", quotes);
			texts.put("fact", facts);
			return null;
		}
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = ProgressDialog.show(NickiStarActivity.this, "", "Loading...");
		}
		
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			progressDialog.dismiss();
			
		}
		
	}	
	
	private boolean isNetworkAvailable() {
		try{
		    ConnectivityManager connectivityManager 
		          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		    return activeNetworkInfo.isConnected();
		}catch (Exception e) {
			return false;
		}
	}
}