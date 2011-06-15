package rs.novosti;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

public class Disclaimer extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.disclaimer);
		SharedPreferences sPrefs = this.getSharedPreferences("novostiPrefs", MODE_WORLD_READABLE);
		final SharedPreferences.Editor editor = sPrefs.edit();
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("UPOZORENJE \n ova aplikacija koristi internet konekciju.\n Da li ste saglasni sa tim ?")
		       .setCancelable(false)
		       .setPositiveButton("Da", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	    editor.putInt("isFirstTime", 0);
		        	    editor.commit();
		        	    finish();
		           }
		       })
		       .setNegativeButton("Ne", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	   setResult(4373);
		        	   finish();
		           }
		       });
		AlertDialog alert = builder.create();
		alert.show();
	}	
}
