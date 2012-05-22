package com.cerspri.games.tod;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cerspri.games.tod.model.Constants;
import com.cerspri.games.tod.model.Game;

public class QueryScreen extends Activity{
	
	private Button cameraButton;
	private TextView queryText;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.query);
        
        queryText = (TextView) findViewById(R.id.queryText);
        cameraButton = (Button) findViewById(R.id.cameraButton);
        
        queryText.setText(Game.getInstance().getDare(1));
        
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
				intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
				
				intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
				
				startActivityForResult(intent, Constants.CAPTURE_VIDEO_ACTIVITY);
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		if (requestCode == Constants.CAPTURE_VIDEO_ACTIVITY){
			if (resultCode == RESULT_OK){
				Toast.makeText(this, "Video saved to:\n" + data.getData(), Toast.LENGTH_LONG).show();
			} else if (resultCode == RESULT_CANCELED) {
				Toast.makeText(this, "Video canceled\n", Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(this, "Video error\n", Toast.LENGTH_LONG).show();
			}
		}
	}
}
