package com.cerSprikRu.ChekBejbi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChekBejbi extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInsntanceState) {
		super.onCreate(savedInsntanceState);
		setContentView(R.layout.main);

		final Button dugme = (Button) findViewById(R.id.dugme);
		dugme.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String FILENAME = "image.jpg";
				FileOutputStream fos = null;
				try {
					fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Bitmap bitmap = getContactPhoto("http://www.novosti.rs/upload/thumbs/images/2011/04/1404n/zLAVROV_150x90.jpg");
				try {
					System.out.println("izlaz ovo ono"+bitmap);
					bitmap.compress(CompressFormat.JPEG, 100, fos);
					// byte[] bitmapdata = bos.toByteArray();

					fos.flush();
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		final TextView slicica = (TextView)findViewById(R.id.slika);
		final Button dugme2 = (Button) findViewById(R.id.dugme2);
		dugme2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String FILENAME = "image.jpg";
				FileInputStream fis = null;
				try {
					fis = openFileInput(FILENAME);
				} catch (FileNotFoundException e) {
					System.out.println("nisam nasho jebeni fajl");
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					
					Drawable draw = Drawable.createFromStream(fis,"bla");
					System.out.println("drovabl ako je ima" +draw);
					fis.close();
					System.out.println(slicica);
					slicica.setBackgroundDrawable(draw);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("slika ako je ima" );
			}
		});
	}

	public Bitmap getContactPhoto(String url) {
		Bitmap pic = null;
		try {
			System.out.println("kurcina da vidimo drawable"  + "\n");
			pic = BitmapFactory.decodeStream((InputStream) new URL(url)
					.getContent());
			Drawable draw = Drawable.createFromStream((InputStream) new URL(url)
					.getContent(), "src");
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pic;

	}

}
