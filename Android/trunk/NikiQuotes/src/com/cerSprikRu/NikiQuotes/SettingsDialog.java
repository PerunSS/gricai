package com.cerSprikRu.NikiQuotes;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.cerSprikRu.NikiQuotes.ColorPickerDialog.OnColorChangedListener;

public class SettingsDialog extends Dialog {
	
	public interface SettingsListener{
		public void changeSettings( int fontSize, int shadowSize,
				int textColor, int shadowColor);
	}

	private int shadowSize, startShadowSize;
	private int fontSize, startFontSize;
	private int textColor, startTextColor;
	private int shadowColor, startShadowColor;
	private Context context;
	private SettingsListener listener;

	public SettingsDialog(Context context, int fontSize, int shadowSize,
			int textColor, int shadowColor, SettingsListener listener) {
		super(context);
		this.listener = listener;
		startFontSize = this.fontSize = fontSize;
		startTextColor = this.textColor = textColor;
		startShadowColor = this.shadowColor = shadowColor;
		startShadowSize = this.shadowSize = shadowSize;
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		final TextView fontView = (TextView) findViewById(R.id.fontSize);
		fontView.setTextSize(fontSize);
		fontView.setText("" + fontSize);
		final SeekBar fontBar = (SeekBar) findViewById(R.id.seekFontSize);
		fontBar.setMax(32);
		fontBar.setProgress(fontSize - 8);
		fontBar
				.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {
					}

					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {
					}

					@Override
					public void onProgressChanged(SeekBar seekBar,
							int progress, boolean fromUser) {
						fontSize = progress + 8;
						fontView.setTextSize(fontSize);
						fontView.setText("" + fontSize);
					}
				});
		final TextView shView = (TextView) findViewById(R.id.fontSize);
		shView.setText("" + shadowSize);
		final SeekBar shadowBar = (SeekBar) findViewById(R.id.seekShSize);
		shadowBar.setMax(20);
		shadowBar.setProgress(shadowSize);
		shadowBar
				.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {
					}

					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {
					}

					@Override
					public void onProgressChanged(SeekBar seekBar,
							int progress, boolean fromUser) {
						shadowSize = progress;
						shadowBar.setProgress(shadowSize);
					}
				});
		final TextView txtClr = (TextView) findViewById(R.id.textColor);
		txtClr.setBackgroundColor(textColor);
		txtClr.setText(Integer.toHexString(textColor));
		final Button btnTxtClr = (Button) findViewById(R.id.btnTxtColor);
		btnTxtClr.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				new ColorPickerDialog(context, new OnColorChangedListener() {

					@Override
					public void colorChanged(int color) {
						textColor = color;
						txtClr.setBackgroundColor(textColor);
						txtClr.setText(Integer.toHexString(textColor));
					}
				}, textColor).show();
			}
		});
		final TextView shClr = (TextView) findViewById(R.id.shColor);
		shClr.setBackgroundColor(shadowColor);
		shClr.setText(Integer.toHexString(shadowColor));
		final Button btnShClr = (Button) findViewById(R.id.btnShColor);
		btnShClr.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				new ColorPickerDialog(context, new OnColorChangedListener() {

					@Override
					public void colorChanged(int color) {
						shadowColor = color;
						shClr.setBackgroundColor(shadowColor);
						shClr.setText(Integer.toHexString(shadowColor));
					}
				}, shadowColor).show();
			}
		});
		Button btnCancel = (Button) findViewById(R.id.settBtnCancel);
		btnCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				shadowColor = startShadowColor;
				shadowSize = startShadowSize;
				fontSize = startFontSize;
				textColor = startTextColor;
				listener.changeSettings(fontSize, shadowSize, textColor, shadowColor);
				SettingsDialog.this.dismiss();
			}
		});
		Button btnOk = (Button) findViewById(R.id.settBtnOk);
		btnOk.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				listener.changeSettings(fontSize, shadowSize, textColor, shadowColor);
				SettingsDialog.this.dismiss();
			}
		});
	}

}
