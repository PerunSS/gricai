package com.cerspri.games.tod;

import java.io.File;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cerspri.games.tod.db.customManager.DBManager;
import com.cerspri.games.tod.model.Constants;
import com.cerspri.games.tod.model.Game;
import com.cerspri.games.tod.model.ToDQuery;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.FacebookError;
import com.facebook.android.Facebook.DialogListener;

public class QueryScreen extends Activity{
	
	private Button cameraButton;
	private TextView queryText;
	private Button shareButton;
	private Activity myActivity = this;
	private Context myContext = this;
	private SharedPreferences mPrefs;
	private Facebook facebook = new Facebook("397771856933308");
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.query);
        
        queryText = (TextView) findViewById(R.id.queryText);
        cameraButton = (Button) findViewById(R.id.cameraButton);
        shareButton = (Button) findViewById(R.id.shareButton);
        
        DBManager dbm = new DBManager(this);
        Map<Integer, ToDQuery> truths = dbm.getTruths();
        
        queryText.setText(new Integer(truths.size()).toString());
        
        cameraButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
				
				String externalDirectory = Environment.getExternalStorageDirectory().toString();
				String directory = "TruthOrDare";
				File file = new File(externalDirectory + File.separator
						+ directory);
				if (!file.isDirectory())
					file.mkdir();
				String timestamp = Long.toString(System.currentTimeMillis());
				file = new File(externalDirectory + File.separator + directory,
				"player"+timestamp+".mp4");
				Uri uri = Uri.fromFile(file);
				//intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
				
				intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
				
				startActivityForResult(intent, Constants.CAPTURE_VIDEO_ACTIVITY);
			}
		});
        
        shareButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				mPrefs = getPreferences(MODE_PRIVATE);
//				String access_token = mPrefs.getString("access_token", null);
//				long expires = mPrefs.getLong("access_expires", 0);
//				if(access_token != null){
//					facebook.setAccessToken(access_token);
//				}
//				if(expires != 0){
//					facebook.setAccessExpires(expires);
//				} 
//				if (!facebook.isSessionValid()){
//					facebook.authorize(myActivity, new DialogListener(){
//	
//						@Override
//						public void onCancel() {
//							// TODO Auto-generated method stub
//							
//						}
//	
//						@Override
//						public void onComplete(Bundle values) {
//							SharedPreferences.Editor editor = mPrefs.edit();
//							editor.putString("access_token", facebook.getAccessToken());
//							editor.putLong("access_expires", facebook.getAccessExpires());
//							editor.commit();
//						}
//	
//						@Override
//						public void onError(DialogError e) {
//							// TODO Auto-generated method stub
//							
//						}
//	
//						@Override
//						public void onFacebookError(FacebookError e) {
//							// TODO Auto-generated method stub
//							
//						}
//						
//					});
//				}
				Bundle parameters = new Bundle();
				parameters.putString("name", "name text");
				parameters.putString("description", "description text");
				parameters.putString("picture", "http://icons.iconarchive.com/icons/iconshock/real-vista-education/256/laboratory-icon.png");
				facebook.dialog(myContext, "stream.publish", parameters, new DialogListener() {
					
					@Override
					public void onFacebookError(FacebookError e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onError(DialogError e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onComplete(Bundle values) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onCancel() {
						// TODO Auto-generated method stub
						
					}
				});
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		if (requestCode == Constants.CAPTURE_VIDEO_ACTIVITY){
			if (resultCode == RESULT_OK){
				Toast.makeText(this, "Video saved to:\n" + data.getData().toString(), Toast.LENGTH_LONG).show();
			} else if (resultCode == RESULT_CANCELED) {
				Toast.makeText(this, "Video canceled\n", Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(this, "Video error\n", Toast.LENGTH_LONG).show();
			}
		}
		facebook.authorizeCallback(requestCode, resultCode, data);
	}
}
