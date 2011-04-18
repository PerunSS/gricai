package com.client;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.client.web.NetworkClient;

public class AndroidHttpClientActivity extends Activity {
    /** Called when the activity is first created. */
	String line;
	String textString="";
	TextView text;
	boolean doneNotifying;
	NetworkClient client;
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		text= (TextView)findViewById(R.id.text);
		text.setText(getString(R.string.Boot_msg));
		client = new NetworkClient(getString(R.string.server_addr),getString(R.string.server_req_target));
		new VersionChecker().execute(client);
		text.setText("done");
    }	
    private class VersionChecker extends AsyncTask<NetworkClient,Integer,String>
    {

		@Override
		protected String doInBackground(NetworkClient... clients) 
		{
			String s = clients[0].executePost();
			System.out.println("DONE: "+s);
			return s;
		}
    }
}