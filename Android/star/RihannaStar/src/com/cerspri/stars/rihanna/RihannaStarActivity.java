package com.cerspri.stars.rihanna;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.Bundle;

public class RihannaStarActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(
				"http://www.google.com/search?q=nicki+minaj&hl=en&prmd=imvnso&source=lnms&tbm=nws");
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
				while (true) {
					line = reader.readLine();
					if(line!=null)
						;//System.out.println(line);
					else
						break;
					builder.append(line+"\n");
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		boolean hasNext = true;
		int linkIndex = 0;
		while(hasNext){
			
			linkIndex = builder.indexOf("<h3 class=r><a href=",linkIndex+1);
			if(linkIndex >0){
				int endIndex = builder.indexOf("</a></h3>", linkIndex);
				System.out.println(builder.substring(linkIndex, endIndex));
				int textIndex = builder.indexOf("<div class=st>", linkIndex);
				if(textIndex>0){
					endIndex = builder.indexOf("</div>", textIndex);
					System.out.println(builder.substring(textIndex, endIndex));
				}else{
					hasNext = false;
				}
			}else{
				hasNext = false;
			}
		}
		
		System.out.println(builder.toString().indexOf("<div class=st"));
		
		setContentView(R.layout.main);
	}
}