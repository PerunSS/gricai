package com.cerspri.bitchScheduler;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.cerspri.bitchScheduler.model.Bitch;

public class AddBitchActivity extends Activity {

	Button addButton;
	Bitch bitch;
	EditText bitchName;
	DatePicker datePicker;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.bitch_creator);
        
        datePicker = (DatePicker)findViewById(R.id.period_start);
        bitchName = (EditText)findViewById(R.id.bitch_name);
        
        addButton = (Button) findViewById(R.id.add_bitch);
        addButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Calendar calendar = Calendar.getInstance();
		        bitch = new Bitch();
		        bitch.setId(getIntent().getExtras().getInt("size"));
		        bitch.setName(bitchName.getText().toString());
		        calendar.set(datePicker.getYear(),datePicker.getMonth(),datePicker.getDayOfMonth());
		        bitch.setPeriodStart(calendar.getTime());
		        Intent resultIntent = new Intent();
		        resultIntent.putExtra("bitch", bitch);
				setResult(1221,resultIntent);
                finish();
			}
		});
	}
	
//    private void save(){
//    	try {
//    		ObjectOutputStream oos = new ObjectOutputStream(openFileOutput("bitches", MODE_APPEND));
//    		
//    		oos.writeObject(bitch);
//    		
//    		oos.close();
//    	} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    }
}
