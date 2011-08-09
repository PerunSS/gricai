package com.cerSprikRu.webreadertest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class WebReaderTest extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        JSONObject myJson = JSONFromUrl("http://www.cerspri.com/api/cocktail_types.php");
        TextView tekst = (TextView) findViewById(R.id.tekst);
        tekst.setText(myJson.toString());
    }
    
    private JSONObject JSONFromUrl(String URL){
    	JSONObject JSONresult=null;
    	try{
    	URL url = new URL(URL);
		XmlPullParser parser = XmlPullParserFactory.newInstance()
				.newPullParser();
		
		StringBuffer buffer = new StringBuffer();
		BufferedReader stream = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"), 64 * 1024);
		String line;
		while((line = stream.readLine())!=null){
			buffer.append(line);
		}
		stream.close();
		String result = new String(buffer.toString().getBytes(),"UTF-8");
		JSONresult = new JSONObject(result);
    	}catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JSONresult;
    }
    
}