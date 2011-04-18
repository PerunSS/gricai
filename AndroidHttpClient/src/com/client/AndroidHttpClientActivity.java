package com.client;

import java.nio.channels.AsynchronousCloseException;

import com.client.web.NetworkClient;
import com.client.web.NetworkConnection;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AndroidHttpClientActivity extends Activity {
    /** Called when the activity is first created. */
	String line;
	String textString="";
	TextView text;
	boolean doneNotifying;
	NetworkClient client;
	NetworkConnection connection;
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		text= (TextView)findViewById(R.id.text);
		text.setText(getString(R.string.Boot_msg));
		connection = new NetworkConnection(getString(R.string.server_addr),getString(R.string.server_req_target));
		client = new NetworkClient(connection);
		new VersionChecker().execute(client);
		text.setText("done");
    }
    private class VersionChecker extends AsyncTask<NetworkClient,Integer,String>
    {

		@Override
		protected String doInBackground(NetworkClient... clients) 
		{
			String s = clients[0].executePost();
			return s;
		}
    }
}