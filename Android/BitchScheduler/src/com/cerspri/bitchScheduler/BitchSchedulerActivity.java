package com.cerspri.bitchScheduler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.cerspri.bitchScheduler.model.Bitch;

public class BitchSchedulerActivity extends Activity {
    /** Called when the activity is first created. */
	List<Bitch> bitches = new ArrayList<Bitch>();
	BitchListAdapter adapter;
	Context myContext = this;
	int position;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ListView view = (ListView) findViewById(R.id.bitchList);
        load();
//        List<Bitch> bitches = new ArrayList<Bitch>();
//        Calendar calendar = Calendar.getInstance();
//        Bitch bitch1 = new Bitch();
//        bitch1.setId(1);
//        bitch1.setName("Bitch 1");
//        calendar.add(Calendar.MONTH, -2);
//        calendar.add(Calendar.DAY_OF_MONTH, -1);
//        bitch1.setPeriodStart(calendar.getTime());
//        bitches.add(bitch1);
//        Bitch bitch2 = new Bitch();
//        bitch2.setId(2);
//        bitch2.setName("Bitch 2");
//        calendar.add(Calendar.MONTH, -12);
//        calendar.add(Calendar.DAY_OF_MONTH, +10);
//        bitch2.setPeriodStart(calendar.getTime());
//        bitches.add(bitch2);
//        Bitch bitch3 = new Bitch();
//        bitch3.setId(3);
//        bitch3.setName("Bitch 3");
//        calendar.add(Calendar.DAY_OF_MONTH, -7);
//        calendar.add(Calendar.MONTH,14);
//        bitch3.setPeriodStart(calendar.getTime());
//        bitches.add(bitch3);
        adapter = new BitchListAdapter(this,bitches);
        view.setAdapter(adapter);
        view.setOnItemClickListener(new OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
        			long arg3) {
        		position = arg2;
        		AlertDialog.Builder builder = new AlertDialog.Builder(myContext);
    			builder.setMessage(
    					"")
    					.setCancelable(false)
    					.setPositiveButton("Edit",
    							new DialogInterface.OnClickListener() {
    								public void onClick(DialogInterface dialog,
    										int id) {
    									edit(position);
    								}
    							})
    					.setNegativeButton("Delete",
    							new DialogInterface.OnClickListener() {
    								public void onClick(DialogInterface dialog,
    										int id) {
    									
    								}
    							});
    			AlertDialog alert = builder.create();
    			alert.show();
        	}
		});
//        save();
    }
    
    private void load(){
    	try {
			ObjectInputStream ois = new ObjectInputStream(openFileInput("bitches"));
			boolean work = true;
			while(work){
				try{
					bitches.add((Bitch)ois.readObject());
				}catch (Exception e) {
					work = false;
				}
			}
			Collections.sort(bitches);
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (StreamCorruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private void save(){
    	try {
    		ObjectOutputStream oos = new ObjectOutputStream(openFileOutput("bitches", MODE_WORLD_WRITEABLE));
    		for(Bitch bitch:bitches){
    			oos.writeObject(bitch);
    		}
    		oos.close();
    	} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private void edit(int id){
		Intent intent = new Intent(myContext, EditBitchActivity.class);	
    	intent.putExtra("bitch", bitches.get(id));
		startActivityForResult(intent, 1331);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.layout.menu_layout, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
        case R.id.add_bitch:
        	Intent intent = new Intent(this,
					AddBitchActivity.class);
        	
        	intent.putExtra("size", bitches.size());
			startActivityForResult(intent, 1221);
            return true;
        default:
            return super.onOptionsItemSelected(item);
            
        }
    }
    
    
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {   	
    	super.onActivityResult(requestCode, resultCode, data);
    	Bundle extras = data.getExtras();
    	if (resultCode == 1221){
    		bitches.add((Bitch)extras.get("bitch"));
    		Collections.sort(bitches);
    		save();
    		adapter.notifyDataSetChanged();
    	}
   
    }
}