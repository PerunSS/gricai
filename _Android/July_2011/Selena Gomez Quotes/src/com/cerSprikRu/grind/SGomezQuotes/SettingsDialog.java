package com.cerSprikRu.grind.SGomezQuotes;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.cerSprikRu.grind.SGomezQuotes.ColorPickerDialog.OnColorChangedListener;

public class SettingsDialog extends Dialog {

	public interface SettingsListener {
		public void changeSettings(float fontSize, float shadowSize,
				int textColor, int shadowColor);
	}

	private float shadowSize;
	private float fontSize;
	private int textColor;
	private int shadowColor;
	private Context context;
	private SettingsListener listener;

	public SettingsDialog(Context context, float fontSize, float shadowSize,
			int textColor, int shadowColor, SettingsListener listener) {
		super(context);
		this.listener = listener;
		this.fontSize = fontSize;
		this.textColor = textColor;
		this.shadowColor = shadowColor;
		this.shadowSize = shadowSize;
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		final TextView fontView = (TextView) findViewById(R.id.fontSize);
		fontView.setTextSize(fontSize);
		fontView.setText((int)fontSize+"");
		final SeekBar fontBar = (SeekBar) findViewById(R.id.seekFontSize);
		fontBar.setMax(32);
		fontBar.setProgress((int) fontSize - 8);
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
						fontView.setText((int)fontSize+"");
					}
				});
		final TextView shView = (TextView) findViewById(R.id.shSize);
		shView.setText((int)shadowSize+"");
		final SeekBar shadowBar = (SeekBar) findViewById(R.id.seekShSize);
		shadowBar.setMax(20);
		shadowBar.setProgress((int) shadowSize);
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
						shView.setText((int)shadowSize+"");
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
				SettingsDialog.this.dismiss();
			}
		});
		Button btnOk = (Button) findViewById(R.id.settBtnOk);
		btnOk.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				listener.changeSettings(fontSize, shadowSize, textColor,
						shadowColor);
				SettingsDialog.this.dismiss();
			}
		});
	}

}
