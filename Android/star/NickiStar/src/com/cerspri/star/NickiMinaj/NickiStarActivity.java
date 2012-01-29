package com.cerspri.star.NickiMinaj;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StreamCorruptedException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cerspri.star.NickiMinaj.model.Model;
import com.cerspri.star.NickiMinaj.model.Video;
import com.cerspri.star.NickiMinaj.widget.MultiDirectionSlidingDrawer;

/**
 * main application activity
 * 
 * @author churava, sale
 * 
 */

public class NickiStarActivity extends Activity {

	Button toggleMenuButton;
	Button factsButton;
	Button newsButton;
	Button pictButton;
	Button videosButton;
	Button videoBackButton;
	Button videoNextButton;
	Button videoPlayButton;
	MultiDirectionSlidingDrawer mDrawer;
	MultiDirectionSlidingDrawer factsDrawer;
	MultiDirectionSlidingDrawer videoButtonsDrawer;
	MultiDirectionSlidingDrawer newsDrawer;
	Button quotesButton;
	TextView scrollText;
	TextView newsNumber;
	TextView newsHeader;
	TextView videoTitle;
	TextView videoDescription;
	ImageView videoImage;
	LinearLayout textLayout;
	LinearLayout newsLayout;
	LinearLayout videosLayout;
	Button randomButton;
	Button shareButton;
	Button refreshButton;
	int state;
	int videoPosition = 0;
	AlertDialog alert;
	ProgressDialog progressDialog;
	Context mContext = this;

	private boolean isToogle = false;
	private boolean menuShown = true;
	private OnClickListener videoPlayListener;

	private boolean loadData() {
		new JSONLoaderTask().execute("quote", "fact");
		if (Model.getInstance().getNumberOfChanges().size() > 0) {
			return true;
		}
		return false;
	}

	// Done on activity creation
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.disclaimer);
		SharedPreferences sPrefs = this.getSharedPreferences("nikiStarPrefs",
				MODE_WORLD_READABLE);
		if (sPrefs.getInt("isFirstTime", 1) == 1) {
			firstStart();
		} else {
			startApp();
		}
	}

	private void startApp() {
		buildGUI();
		Model.getInstance().createMaps();
		getData("fact", false);
		getData("quote", false);
		getVideos();
		
		addButtonActions();
	}

	private void firstStart() {
		Intent myIntent = new Intent(this, Disclaimer.class);
		startActivityForResult(myIntent, 0);
	}

	private void addButtonActions() {
		// listener for opening/closing menu
		OnClickListener toggleMenuButtonListener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!mDrawer.isOpened()) {
					if (factsDrawer.isOpened()) {
						factsDrawer.animateClose();
						textLayout.setVisibility(View.GONE);
					}
					if (videoButtonsDrawer.isOpened()) {
						videoButtonsDrawer.animateClose();
						videosLayout.setVisibility(View.GONE);
						textLayout.setVisibility(View.GONE);
					}
					mDrawer.animateOpen();
					toggleMenuButton
							.setBackgroundResource(R.drawable.close_menu_button);
					menuShown = true;
				} else {
					menuShown = false;
					switch (state) {
					case 1:
						isToogle = true;
						factsButton.performClick();
						break;
					case 2:
						isToogle = true;
						quotesButton.performClick();
						break;
					case 3:
						isToogle = true;
						if (videoPosition > 0) {
							mDrawer.animateClose();
							videoButtonsDrawer.animateOpen();
							videosLayout.setVisibility(View.VISIBLE);
							state = 3;
							toggleMenuButton
									.setBackgroundResource(R.drawable.open_menu_button);
							isToogle = false;
							menuShown = false;
						} else {
							videosButton.performClick();
						}
						break;
					// case 3:
					// newsButton.performClick();
					// break;
					default:
						mDrawer.animateClose();
						toggleMenuButton
								.setBackgroundResource(R.drawable.open_menu_button);
					}
				}
			}
		};
		toggleMenuButton.setOnClickListener(toggleMenuButtonListener);
		// listener for facts button
		factsButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mDrawer.animateClose();
				factsDrawer.animateOpen();
				state = 1;
				toggleMenuButton
						.setBackgroundResource(R.drawable.open_menu_button);
				if (!isToogle)
					scrollText.setText(Model.getInstance().getNext("fact"));
				final float scale = getResources().getDisplayMetrics().density;
				int padding_5dp = (int) (20 * scale + 0.5f);
				textLayout.setPadding(0, padding_5dp, 0, 0);
				textLayout.setVisibility(View.VISIBLE);
				isToogle = false;
				menuShown = false;
			}
		});
		// listener for quotes button
		quotesButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mDrawer.animateClose();
				factsDrawer.animateOpen();
				state = 2;
				toggleMenuButton
						.setBackgroundResource(R.drawable.open_menu_button);
				if (!isToogle)
					scrollText.setText(Model.getInstance().getNext("quote"));
				final float scale = getResources().getDisplayMetrics().density;
				int padding_5dp = (int) (20 * scale + 0.5f);
				textLayout.setPadding(0, padding_5dp, 0, 0);
				textLayout.setVisibility(View.VISIBLE);
				isToogle = false;
				menuShown = false;
			}
		});
		// listener for news button
		newsButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				displayMessage();
				// new NewsLoaderTask().execute(0);

			}
		});
		// listener for pictures button
		pictButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Uri uriUrl = Uri.parse("http://www.google.rs/search?q="
						+ getString(R.string.app_name).toLowerCase().replace(
								' ', '+')
						+ "&hl=sr&prmd=imvnso&source=lnms&tbm=isch");
				Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
				startActivity(launchBrowser);
				// displayMessage();
			}
		});
		// listener for videos button
		videosButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (videoPosition > 0) {
					mDrawer.animateClose();
					videoButtonsDrawer.animateOpen();
					videosLayout.setVisibility(View.VISIBLE);
					state = 3;
					toggleMenuButton
							.setBackgroundResource(R.drawable.open_menu_button);
					isToogle = false;
					menuShown = false;
				} else {
					new VideosLodaerTask().execute(Model.getInstance().getLastVideoID());
				}
			}
		});
		randomButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String text = "";
				if (state == 1) {
					text = Model.getInstance().getNext("fact");
				} else if (state == 2) {
					text = Model.getInstance().getNext("quote");
				}
				scrollText.setText(text);
			}
		});
		refreshButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (state == 1) {
					new JSONLoaderTask().execute("fact");
				} else if (state == 2) {
					new JSONLoaderTask().execute("quote");
				}
			}
		});

		shareButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String text = scrollText.getText().toString();
				String subject = " about " + getString(R.string.app_name);
				String type = "";
				if (state == 1) {
					type = "fact";
				} else if (state == 2) {
					type = "quote";
				}
				subject = type + subject;
				share(subject, text, type);
			}
		});

		videoBackButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (videoPosition > 1) {
					videoPosition -= 1;
					videoTitle.setText(Model.getInstance().getVideos()
							.get(videoPosition).getTitle());
					videoDescription.setText(Model.getInstance().getVideos()
							.get(videoPosition).getDescription());
					videoImage.setOnClickListener(videoPlayListener);
					new LoadImageTask(videoImage, Model.getInstance()
							.getVideos().get(videoPosition).getImagePath())
							.execute();
				}
				if (videoPosition == 1) {
					videoBackButton.setVisibility(View.INVISIBLE);
				} else if (videoPosition == Model.getInstance().getVideos()
						.size() - 1) {
					videoNextButton.setVisibility(View.VISIBLE);
				}
			}
		});

		videoNextButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (videoPosition < Model.getInstance().getVideos().size()) {
					videoPosition += 1;
					if (Model.getInstance().getVideos()
							.get(videoPosition).getTitle()==null){
						new VideoLodaerTask().execute();
					}else{
					videoTitle.setText(Model.getInstance().getVideos()
							.get(videoPosition).getTitle());
					videoDescription.setText(Model.getInstance().getVideos()
							.get(videoPosition).getDescription());
					videoImage.setOnClickListener(videoPlayListener);
					new LoadImageTask(videoImage, Model.getInstance()
							.getVideos().get(videoPosition).getImagePath())
							.execute();
					}
				}
				if (videoPosition == Model.getInstance().getVideos().size()) {
					videoNextButton.setVisibility(View.INVISIBLE);
				} else if (videoPosition == 2) {
					videoBackButton.setVisibility(View.VISIBLE);
				}
			}
		});
		
		videoPlayButton.setOnClickListener(videoPlayListener);
	}

	private void buildGUI() {
		setContentView(R.layout.main);
		state = 0;

		// layout objects asignment
		toggleMenuButton = (Button) findViewById(R.id.toggle_menu);
		mDrawer = (MultiDirectionSlidingDrawer) findViewById(R.id.menuDrawer);
		factsButton = (Button) findViewById(R.id.facts_button);
		factsDrawer = (MultiDirectionSlidingDrawer) findViewById(R.id.factsDrawer);
		videoButtonsDrawer = (MultiDirectionSlidingDrawer) findViewById(R.id.videoButtonDrawer);
		quotesButton = (Button) findViewById(R.id.quotes_button);
		newsButton = (Button) findViewById(R.id.news_button);
		pictButton = (Button) findViewById(R.id.pict_button);
		videosButton = (Button) findViewById(R.id.videos_button);
		scrollText = (TextView) findViewById(R.id.scroll_text);
		randomButton = (Button) findViewById(R.id.random_button);
		shareButton = (Button) findViewById(R.id.share_button);
		textLayout = (LinearLayout) findViewById(R.id.text_layout);
		newsNumber = (TextView) findViewById(R.id.news_number);
		newsHeader = (TextView) findViewById(R.id.news_header);
		newsLayout = (LinearLayout) findViewById(R.id.news_layout);
		refreshButton = (Button) findViewById(R.id.refresh_button);
		videosLayout = (LinearLayout) findViewById(R.id.videos_layout);
		videoTitle = (TextView) findViewById(R.id.video_title);
		videoDescription = (TextView) findViewById(R.id.video_description);
		videoImage = (ImageView) findViewById(R.id.video_picture_link);
		videoBackButton = (Button) findViewById(R.id.video_back_button);
		videoNextButton = (Button) findViewById(R.id.video_next_button);
		videoPlayButton = (Button) findViewById(R.id.play_video_button);
		
		mDrawer.animateOpen();
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Coming soon!!!");
		alert = builder.create();
		
		videoPlayListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent videoIntent = new Intent(Intent.ACTION_VIEW,
						Uri.parse("vnd.youtube:"
								+ Model.getInstance().getVideos()
										.get(videoPosition)
										.getVideoTag()));
				List<ResolveInfo> activities = getPackageManager().queryIntentActivities(videoIntent, PackageManager.MATCH_DEFAULT_ONLY );
				if(activities.size()>0){
					startActivity(videoIntent);
				}else{
					Toast.makeText(NickiStarActivity.this, "You do not have application that support playing video files!", Toast.LENGTH_LONG).show();
				}
			}
		};
	}

	protected void displayMessage() {
		state = 0;
		alert.show();
		final Timer t = new Timer();
		t.schedule(new TimerTask() {
			public void run() {
				alert.dismiss();
				t.cancel();
			}
		}, 2000);
	}

	private void share(String subject, String text, String type) {
		final Intent intent = new Intent(Intent.ACTION_SEND);

		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_SUBJECT, subject);
		intent.putExtra(Intent.EXTRA_TEXT, text);

		startActivity(Intent.createChooser(intent, type + " about "
				+ getString(R.string.app_name)));
	}

	private void getData(String type, boolean shouldUpdate) {
		List<String> data = new ArrayList<String>();
		try {
			Scanner sc = new Scanner(openFileInput(type));
			while (sc.hasNext()) {
				data.add(sc.nextLine());
			}
			Collections.shuffle(data);
			sc.close();
			Model.getInstance().getTexts().put(type, data);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (shouldUpdate) {
			int version = getPreferences(MODE_WORLD_READABLE).getInt(
					"version_" + type, 0);
			int newVersion = Model.getInstance().loadData(type,
					getString(R.string.app_name), version);
			if (newVersion > version){
				version = newVersion;
				saveToPhone(type, version,
						Model.getInstance().getTexts().get(type), true);
			}
		}
	}
	
	private void getVideos(){
		try {
			ObjectInputStream ois = new ObjectInputStream(openFileInput("videos"));
			boolean read = true;
			int max = 0;
			while(read){
				try{
					Video video = (Video)ois.readObject();
					Model.getInstance().getVideos().put(video.getId(), video);
					System.out.println(video.getId());
					System.out.println(video.getVideoTag());
					System.out.println(video.getTitle());
					if(video.getId() > max){
						max = video.getId();
					}
				}catch (Exception e) {
					read = false;
				}
			}
			ois.close();
			Model.getInstance().setLastVideoID(max);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (StreamCorruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void saveVideoToPhone(){
		try {
			ObjectOutputStream oos = new ObjectOutputStream(openFileOutput("videos", MODE_WORLD_WRITEABLE));
			for(Map.Entry<Integer, Video> entry: Model.getInstance().getVideos().entrySet()){
				oos.writeObject(entry.getValue());
				System.out.println(entry.getValue().getId()+" "+entry.getValue().getVideoTag());
			}
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void saveToPhone(String type, int version, List<String> data,
			boolean changed) {
		SharedPreferences preferences = getPreferences(MODE_WORLD_WRITEABLE);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putInt("version_" + type, version);
		editor.commit();
		if (changed) {
			try {
				PrintWriter writer = new PrintWriter(openFileOutput(type,
						MODE_WORLD_WRITEABLE));
				for (String value : data) {
					writer.println(value);
				}
				writer.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 0) {
			if (resultCode == Codes.NO_DATA_LOAD_ALLOWED) {
				finish();
			} else if (resultCode == Codes.ACCEPT_LOAD) {
				startApp();
				loadData();
			} else if (resultCode == Codes.NO_INTERNET_CONNECTION) {
				finish();
			}
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			if (menuShown) {
				return super.onKeyDown(keyCode, event);
			} else {
				toggleMenuButton.performClick();
				return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	private void updatePrefs() {
		SharedPreferences sPrefs = getSharedPreferences("nikiStarPrefs",
				MODE_WORLD_READABLE);
		SharedPreferences.Editor editor = sPrefs.edit();
		editor.putInt("isFirstTime", 0);
		editor.commit();
	}

	private class JSONLoaderTask extends AsyncTask<String, Void, Void> {

		@Override
		protected Void doInBackground(String... params) {
			for (String name : params) {
				getData(name, true);
			}
			return null;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = ProgressDialog.show(NickiStarActivity.this, "",
					"Loading...");
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			progressDialog.dismiss();
			String text = "";
			int i = 0;
			for (Map.Entry<String, Integer> entry : Model.getInstance()
					.getNumberOfChanges().entrySet()) {
				if (entry.getValue() > 0) {
					text += entry.getValue() + " " + entry.getKey()
							+ "s loaded";
					if (i < Model.getInstance().getNumberOfChanges().size() - 1) {
						text += "\n";
					}
					i++;
				}
			}
			if (i == 0) {
				text = "There is no new data";
			}
			Toast toast = Toast.makeText(mContext, text, 1000);
			toast.show();
			if (Model.getInstance().getNumberOfChanges().size() > 0)
				updatePrefs();
		}
	}

	private class VideosLodaerTask extends AsyncTask<Integer, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = ProgressDialog.show(NickiStarActivity.this, "",
					"Loading...");
		}

		@Override
		protected Void doInBackground(Integer... params) {
			Model.getInstance().loadVideos(params[0],
					getString(R.string.app_name));
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			saveVideoToPhone();
			progressDialog.dismiss();
			mDrawer.animateClose();
			videoButtonsDrawer.animateOpen();
			videosLayout.setVisibility(View.VISIBLE);
			state = 3;
			toggleMenuButton.setBackgroundResource(R.drawable.open_menu_button);
			isToogle = false;
			menuShown = false;
			videoPosition++;
			new VideoLodaerTask().execute();
			videoBackButton.setVisibility(View.INVISIBLE);
			videoImage.setOnClickListener(videoPlayListener);
		}

	}
	
	private class VideoLodaerTask extends AsyncTask<Integer, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = ProgressDialog.show(NickiStarActivity.this, "",
					"Loading...");
		}

		@Override
		protected Void doInBackground(Integer... params) {
			Video video = new Video();
			video.extractFromTag(Model.getInstance().getVideos().get(videoPosition).getVideoTag());
			Model.getInstance().getVideos().put(videoPosition, video);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			saveVideoToPhone();
			progressDialog.dismiss();
			videoTitle.setText(Model.getInstance().getVideos()
					.get(videoPosition).getTitle());
			videoDescription.setText(Model.getInstance().getVideos()
					.get(videoPosition).getDescription());
			videoImage.setOnClickListener(videoPlayListener);
			new LoadImageTask(videoImage, Model.getInstance()
					.getVideos().get(videoPosition).getImagePath())
					.execute();
		}

	}
	

//	@SuppressWarnings("unused")
//	private class NewsLoaderTask extends AsyncTask<Integer, Void, Void> {
//
//		@Override
//		protected Void doInBackground(Integer... params) {
//			Model.getInstance().loadNews(params[0],
//					getString(R.string.app_name));
//			return null;
//		}
//
//		@Override
//		protected void onPreExecute() {
//			super.onPreExecute();
//			progressDialog = ProgressDialog.show(NickiStarActivity.this, "",
//					"Loading...");
//		}
//
//		@Override
//		protected void onPostExecute(Void result) {
//			super.onPostExecute(result);
//			progressDialog.dismiss();
//			mDrawer.animateClose();
//			newsDrawer.animateOpen();
//			state = 3;
//
//			toggleMenuButton.setBackgroundResource(R.drawable.open_menu_button);
//			News news = Model.getInstance().getNews().get(0);
//			scrollText.setText(Html.fromHtml(news.getText()));
//			newsNumber.setText("1/10");
//			newsHeader.setText(Html.fromHtml("<a href = " + news.getLink())
//					+ ">" + news.getLinkDescription());
//
//			final float scale = getResources().getDisplayMetrics().density;
//			int padding_50dp = (int) (90 * scale + 0.5f);
//
//			newsLayout.setVisibility(View.VISIBLE);
//			textLayout.setPadding(0, newsLayout.getHeight() + padding_50dp, 0,
//					0);
//
//			scrollText.setText(new Integer(newsLayout.getHeight()).toString());
//			textLayout.setVisibility(View.VISIBLE);
//		}
//	}

	private class LoadImageTask extends AsyncTask<Void, Void, Drawable> {
		private ImageView view;
		private String url;

		LoadImageTask(ImageView view, String url) {
			this.view = view;
			this.url = url;
			this.view.setImageResource(R.drawable.loading);
		}

		@Override
		protected Drawable doInBackground(Void... params) {
			InputStream is = null;
			if (url != null) {
				url = url.replaceAll(" ", "%20");
				try {
					is = new URL(url).openStream();
					BitmapFactory.Options options = new BitmapFactory.Options();
					Bitmap bitmap = BitmapFactory.decodeStream(is, null,
							options);

					Drawable drawable = new BitmapDrawable(bitmap);
					return drawable;
				} catch (Exception e) {
				} finally {
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return null;
		}

		@Override
		protected void onPostExecute(Drawable result) {
			if (result != null) {
				view.setImageDrawable(result);
			}
		}

	}

}