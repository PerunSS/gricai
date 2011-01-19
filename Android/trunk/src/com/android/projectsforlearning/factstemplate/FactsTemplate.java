package com.android.projectsforlearning.factstemplate;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;

public class FactsTemplate extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
       
        GridView gridview = (GridView) findViewById(R.id.startPageGridView);
        gridview.setAdapter(new ImageAdapter(this));
        gridview.setBackgroundColor(android.graphics.Color.WHITE);

        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            	setContentView(R.layout.selected);
            }
        });
    }
}