package com.cerspri.bitchScheduler;

import java.text.SimpleDateFormat;
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

public class EditBitchActivity extends Activity {

	Button editButton;
	Bitch bitch;
	EditText bitchName;
	DatePicker datePicker;
	int position;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.bitch_creator);
        
        datePicker = (DatePicker)findViewById(R.id.period_start);
        bitchName = (EditText)findViewById(R.id.bitch_name);
        
        position = (Integer)getIntent().getExtras().get("position");
        bitch = (Bitch)getIntent().getExtras().get("bitch");
        bitchName.setText(bitch.getName());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(bitch.getPeriodStart());
//        System.out.println(calendar.get(Calendar.YEAR)+"month   "+calendar.get(Calendar.MONTH) + "day   "+calendar.get(Calendar.DAY_OF_MONTH));
        datePicker.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        
        editButton = (Button) findViewById(R.id.add_bitch);
        editButton.setText("Edit");
        editButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Calendar calendar = Calendar.getInstance();
		        bitch = new Bitch();
		        bitch.setId(getIntent().getExtras().getInt("size"));
		        bitch.setName(bitchName.getText().toString());
		        calendar.set(datePicker.getYear(),datePicker.getMonth(),datePicker.getDayOfMonth());
		        bitch.setPeriodStart(calendar.getTime());
//	    		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//	    		System.out.println(format.format(bitch.getPeriodStart()));
		        Intent resultIntent = new Intent();
		        resultIntent.putExtra("bitch", bitch);
		        resultIntent.putExtra("position", position);
				setResult(1331,resultIntent);
                finish();
			}
		});
        

	}
	
}
