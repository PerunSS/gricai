package com.cerspri.star.NickiMinaj;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

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
import android.text.Html;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cerspri.star.NickiMinaj.model.Model;
import com.cerspri.star.NickiMinaj.model.News;
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
	Button newsNextButton;
	Button newsBackButton;
	Button newsReadButton;
	Button videosButton;
	Button videoBackButton;
	Button videoNextButton;
	Button videoPlayButton;
	Button updateButton;
	Button rateButton;
	MultiDirectionSlidingDrawer mDrawer;
	MultiDirectionSlidingDrawer factsDrawer;
	MultiDirectionSlidingDrawer videoButtonsDrawer;
	MultiDirectionSlidingDrawer newsDrawer;
	//Button quotesButton;
	TextView scrollText;
	TextView newsTitle;
	TextView newsText;
	TextView newsDate;
	TextView videoTitle;
	TextView videoDescription;
	ImageView newsImage;
	ImageView videoImage;
	LinearLayout textLayout;
	LinearLayout newsLayout;
	LinearLayout videosLayout;
	LinearLayout newsLinkLayout;
	Button randomButton;
	Button shareButton;
	Button refreshButton;
	State state;
	int videoPosition = -1;
	int newsVersion = -1;
	int newsPosition = -1;
	AlertDialog alert;
	ProgressDialog progressDialog;
	Context mContext = this;
	boolean initial = false;

	private boolean isToogle = false;
	private boolean menuShown = true;
	private OnClickListener videoPlayListener;
	private ImageLoaderTask imageTask;

	private enum State {
		FACTS, QUOTES, VIDEOS, NEWS, NULL
	}

	private boolean loadData() {
		initial = true;
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
		setContentView(R.layout.main);
		final Button quotesButton = (Button) findViewById(R.id.quotes);
		quotesButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				state = State.QUOTES;
				setContentView(R.layout.fq_view);
			}
		});
		/*SharedPreferences sPrefs = this.getSharedPreferences("nikiStarPrefs",
				MODE_WORLD_READABLE);
		if (sPrefs.getInt("isFirstTime", 1) == 1) {
			firstStart();
		} else {
			if (sPrefs.getInt("newsVersion", 0) > 0) {
				newsVersion = sPrefs.getInt("newsVersion", 0);
			}
			startApp();
		}*/
	}

	private void startApp() {
		buildGUI();
		Model.getInstance().createMaps();
		if (!initial) {
			getData("fact", false, false);
			getData("quote", false, false);
			getVideos();
			getNews(false, false);
		}
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
					}
					if (newsDrawer.isOpened()) {
						newsDrawer.animateClose();
						newsLayout.setVisibility(View.GONE);
					}
					mDrawer.animateOpen();
					toggleMenuButton
							.setBackgroundResource(R.drawable.close_menu_button);
					menuShown = true;
				} else {
					menuShown = false;
					switch (state) {
					case FACTS:
						isToogle = true;
						factsButton.performClick();
						break;
					case QUOTES:
						isToogle = true;
						//quotesButton.performClick();
						break;
					case VIDEOS:
						isToogle = true;
						if (videoPosition > 0) {
							mDrawer.animateClose();
							videoButtonsDrawer.animateOpen();
							videosLayout.setVisibility(View.VISIBLE);
							state = State.VIDEOS;
							toggleMenuButton
									.setBackgroundResource(R.drawable.open_menu_button);
							isToogle = false;
							menuShown = false;
						} else {
							videosButton.performClick();
						}
						break;
					case NEWS:
						isToogle = true;
						newsButton.performClick();
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
				state = State.FACTS;
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
//		quotesButton.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				mDrawer.animateClose();
//				factsDrawer.animateOpen();
//				state = State.QUOTES;
//				toggleMenuButton
//						.setBackgroundResource(R.drawable.open_menu_button);
//				if (!isToogle)
//					scrollText.setText(Model.getInstance().getNext("quote"));
//				final float scale = getResources().getDisplayMetrics().density;
//				int padding_5dp = (int) (20 * scale + 0.5f);
//				textLayout.setPadding(0, padding_5dp, 0, 0);
//				textLayout.setVisibility(View.VISIBLE);
//				isToogle = false;
//				menuShown = false;
//			}
//		});

		newsNextButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (newsPosition < Model.getInstance().getNews().size() - 1) {
					newsPosition++;
					showCurrentNews();
				}
				if (newsPosition + 1 == Model.getInstance().getNews().size()) {
					newsNextButton.setVisibility(View.INVISIBLE);
				} else if (newsPosition > 0) {
					newsBackButton.setVisibility(View.VISIBLE);
				}
			}
		});

		newsBackButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (newsPosition > 0) {
					newsPosition--;
					showCurrentNews();
				}
				if (newsPosition == 0) {
					newsBackButton.setVisibility(View.INVISIBLE);
				} else if (newsPosition < Model.getInstance().getNews().size() - 1) {
					newsNextButton.setVisibility(View.VISIBLE);
				}
			}
		});

		// listener for news button
		newsButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// displayMessage();
				// new NewsLoaderTask().execute(0);
				if (newsPosition >= 0) {
					mDrawer.animateClose();
					newsDrawer.animateOpen();
					newsLayout.setVisibility(View.VISIBLE);
					state = State.NEWS;
					toggleMenuButton
							.setBackgroundResource(R.drawable.open_menu_button);
					isToogle = false;
					menuShown = false;
					if (newsPosition == 0) {
						newsBackButton.setVisibility(View.INVISIBLE);
					}
					showCurrentNews();
				} else {
					new NewsLoaderTask().execute(newsVersion);
				}
			}
		});

		newsReadButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Uri uriUrl = Uri.parse(Model.getInstance().getNews()
						.get(newsPosition).getNewsUrl());
				Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
				startActivity(launchBrowser);
			}
		});

		newsLinkLayout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				newsReadButton.performClick();
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
					state = State.VIDEOS;
					toggleMenuButton
							.setBackgroundResource(R.drawable.open_menu_button);
					isToogle = false;
					menuShown = false;
					if (videoPosition == 1) {
						videoBackButton.setVisibility(View.INVISIBLE);
					}
					showCurrentVideo();
				} else {
					new VideosLodaerTask().execute(Model.getInstance()
							.getLastVideoID());
				}
			}
		});
		randomButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String text = "";
				if (state == State.FACTS) {
					text = Model.getInstance().getNext("fact");
				} else if (state == State.QUOTES) {
					text = Model.getInstance().getNext("quote");
				}
				scrollText.setText(text);
			}
		});
		refreshButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (state == State.FACTS) {
					new JSONLoaderTask().execute("fact");
				} else if (state == State.QUOTES) {
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
				if (state == State.FACTS) {
					type = "fact";
				} else if (state == State.QUOTES) {
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
					videoPosition--;
					showCurrentVideo();
				}
				if (videoPosition == 1) {
					videoBackButton.setVisibility(View.INVISIBLE);
				} else if (videoPosition < Model.getInstance().getVideos()
						.size()) {
					videoNextButton.setVisibility(View.VISIBLE);
				}
			}
		});

		videoNextButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (videoPosition < Model.getInstance().getVideos().size()) {
					videoPosition++;
					if (Model.getInstance().getVideos().get(videoPosition)
							.getTitle() == null) {
						new YoutubeMetadataLodaerTask().execute();
					} else {
						showCurrentVideo();
					}
				}
				if (videoPosition == Model.getInstance().getVideos().size()) {
					videoNextButton.setVisibility(View.INVISIBLE);
				} else if (videoPosition > 1) {
					videoBackButton.setVisibility(View.VISIBLE);
				}
			}
		});

		videoPlayButton.setOnClickListener(videoPlayListener);

		updateButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				new UpdateContentTask().execute(Model.getInstance()
						.getLastVideoID());
			}
		});

		rateButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri
						.parse("market://details?id=com.cerspri.star.NickiMinaj"));
				startActivity(intent);
			}
		});
	}

	private void buildGUI() {
		setContentView(R.layout.main);
		state = State.NULL;

		// layout objects asignment
		toggleMenuButton = (Button) findViewById(R.id.toggle_menu);
		mDrawer = (MultiDirectionSlidingDrawer) findViewById(R.id.menuDrawer);
		factsButton = (Button) findViewById(R.id.facts_button);
		factsDrawer = (MultiDirectionSlidingDrawer) findViewById(R.id.factsDrawer);
		videoButtonsDrawer = (MultiDirectionSlidingDrawer) findViewById(R.id.videoButtonDrawer);
		//quotesButton = (Button) findViewById(R.id.quotes_button);
		newsButton = (Button) findViewById(R.id.news_button);
		pictButton = (Button) findViewById(R.id.pict_button);
		videosButton = (Button) findViewById(R.id.videos_button);
		scrollText = (TextView) findViewById(R.id.scroll_text);
		randomButton = (Button) findViewById(R.id.random_button);
		shareButton = (Button) findViewById(R.id.share_button);
		textLayout = (LinearLayout) findViewById(R.id.text_layout);
		newsTitle = (TextView) findViewById(R.id.news_title);
		newsImage = (ImageView) findViewById(R.id.news_picture_link);
		newsText = (TextView) findViewById(R.id.news_text);
		newsDate = (TextView) findViewById(R.id.news_date);
		newsLayout = (LinearLayout) findViewById(R.id.news_layout);
		newsDrawer = (MultiDirectionSlidingDrawer) findViewById(R.id.newsButtonDrawer);
		refreshButton = (Button) findViewById(R.id.refresh_button);
		videosLayout = (LinearLayout) findViewById(R.id.videos_layout);
		videoTitle = (TextView) findViewById(R.id.video_title);
		videoDescription = (TextView) findViewById(R.id.video_description);
		videoImage = (ImageView) findViewById(R.id.video_picture_link);
		videoBackButton = (Button) findViewById(R.id.video_back_button);
		videoNextButton = (Button) findViewById(R.id.video_next_button);
		videoPlayButton = (Button) findViewById(R.id.play_video_button);
		newsNextButton = (Button) findViewById(R.id.news_next_button);
		newsBackButton = (Button) findViewById(R.id.news_back_button);
		newsReadButton = (Button) findViewById(R.id.read_news_button);
		newsLinkLayout = (LinearLayout) findViewById(R.id.news_link_layout);
		updateButton = (Button) findViewById(R.id.update_button);
		rateButton = (Button) findViewById(R.id.rate_button);

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
										.get(videoPosition).getVideoTag()));
				List<ResolveInfo> activities = getPackageManager()
						.queryIntentActivities(videoIntent,
								PackageManager.MATCH_DEFAULT_ONLY);
				if (activities.size() > 0) {
					startActivity(videoIntent);
				} else {
					Toast.makeText(
							NickiStarActivity.this,
							"You do not have application that support playing video files!",
							Toast.LENGTH_LONG).show();
				}
			}
		};
	}

	protected void displayMessage() {
		state = State.NULL;
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

	private void getData(String type, boolean shouldUpdate, boolean initial) {
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
					getString(R.string.app_name), version, initial);
			if (newVersion > version) {
				version = newVersion;
				saveToPhone(type, version,
						Model.getInstance().getTexts().get(type), true);
			}
		}
	}

	private void getVideos() {
		try {
			ObjectInputStream ois = new ObjectInputStream(
					openFileInput("videos"));
			boolean read = true;
			int max = 0;
			while (read) {
				try {
					Video video = (Video) ois.readObject();
					Model.getInstance().getVideos().put(video.getId(), video);
					if (video.getId() > max) {
						max = video.getId();
					}
				} catch (Exception e) {
					read = false;
				}
			}
			ois.close();
			Model.getInstance().setLastVideoID(max);
			if (max > 0)
				videoPosition = 1;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (StreamCorruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getNews(boolean shouldUpdate, boolean initial) {
		try {
			ObjectInputStream ois = new ObjectInputStream(openFileInput("news"));
			boolean read = true;

			while (read) {
				try {
					News news = (News) ois.readObject();
					Model.getInstance().getNews().add(news);
					newsPosition = 0;
				} catch (Exception e) {
					read = false;
				}
			}
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (StreamCorruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		newsVersion = getPreferences(MODE_WORLD_READABLE).getInt("newsVersion",
				0);
		if (shouldUpdate) {
			int newNewsVersion = Model.getInstance().loadNews(newsVersion,
					getString(R.string.app_name), initial);
			if (newNewsVersion > newsVersion) {
				newsVersion = newNewsVersion;
				saveNewsToPhone();
			}
		}
	}

	private void saveNewsToPhone() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(openFileOutput(
					"news", MODE_WORLD_WRITEABLE));
			for (News entry : Model.getInstance().getNews()) {
				oos.writeObject(entry);
				SharedPreferences preferences = getPreferences(MODE_WORLD_WRITEABLE);
				SharedPreferences.Editor editor = preferences.edit();
				editor.putInt("newsVersion", newsVersion);
				editor.commit();
			}
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void saveVideoToPhone() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(openFileOutput(
					"videos", MODE_WORLD_WRITEABLE));
			for (Map.Entry<Integer, Video> entry : Model.getInstance()
					.getVideos().entrySet()) {
				oos.writeObject(entry.getValue());
				// System.out.println(entry.getValue());
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
				initial = true;
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
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			toggleMenuButton.performClick();
			return true;
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

	private void showCurrentVideo() {
		Video v = Model.getInstance().getVideos().get(videoPosition);
		if (v != null && v.getTitle() != null)
			videoTitle.setText(v.getTitle());
		if (v != null && v.getDescription() != null) {
			videoDescription.setText(v.getDescription());
		}
		videoImage.setOnClickListener(videoPlayListener);
		if (v != null && v.getImagePath() != null) {
			if (imageTask != null
					&& imageTask.getStatus() == ImageLoaderTask.Status.RUNNING) {
				imageTask.cancel(true);
				imageTask = null;
			}
			imageTask = new ImageLoaderTask(videoImage, v.getImagePath());
			imageTask.execute();
		}
	}

	private void showCurrentNews() {
		newsTitle.setText(Html.fromHtml(Model.getInstance().getNews()
				.get(newsPosition).getTitle()));
		newsText.setText(Html.fromHtml(Model.getInstance().getNews()
				.get(newsPosition).getContent()));
		newsImage.setOnClickListener(videoPlayListener);
		newsDate.setText(Model.getInstance().getNews().get(newsPosition)
				.getPubDate());
		new ImageLoaderTask(newsImage, Model.getInstance().getNews()
				.get(newsPosition).getImagePath()).execute();
	}

	/**
	 * Task for getting facts and quotes from server
	 * 
	 * @author aleksandarvaricak
	 * 
	 */
	private class JSONLoaderTask extends AsyncTask<String, Void, Void> {

		@Override
		protected Void doInBackground(String... params) {
			for (String name : params) {
				getData(name, true, initial);
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
			initial = false;
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
			System.out.println(text);
			Toast toast = Toast.makeText(mContext, text, 1000);
			toast.show();
			if (Model.getInstance().getNumberOfChanges().size() > 0)
				updatePrefs();
		}
	}

	/**
	 * tasks request all video tags from cerspri.com
	 * 
	 * @author aleksandarvaricak
	 * 
	 */
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
					getString(R.string.app_name), false);
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
			state = State.VIDEOS;
			toggleMenuButton.setBackgroundResource(R.drawable.open_menu_button);
			isToogle = false;
			menuShown = false;
			videoPosition = 1;
			new YoutubeMetadataLodaerTask().execute();
			videoBackButton.setVisibility(View.INVISIBLE);
			videoImage.setOnClickListener(videoPlayListener);
		}

	}

	private class NewsLoaderTask extends AsyncTask<Integer, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = ProgressDialog.show(NickiStarActivity.this, "",
					"Loading...");
		}

		@Override
		protected Void doInBackground(Integer... params) {
			getNews(true, false);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			saveNewsToPhone();
			progressDialog.dismiss();
			mDrawer.animateClose();
			newsDrawer.animateOpen();
			newsLayout.setVisibility(View.VISIBLE);
			state = State.NEWS;
			toggleMenuButton.setBackgroundResource(R.drawable.open_menu_button);
			isToogle = false;
			menuShown = false;
			newsPosition = 0;
			if (Model.getInstance().getNews().size() > 0) {
				newsTitle.setText(Html.fromHtml(Model.getInstance().getNews()
						.get(newsPosition).getTitle()));
				newsText.setText(Html.fromHtml(Model.getInstance().getNews()
						.get(newsPosition).getContent()));
				newsImage.setOnClickListener(videoPlayListener);
				newsDate.setText(Model.getInstance().getNews()
						.get(newsPosition).getPubDate());
				new ImageLoaderTask(newsImage, Model.getInstance().getNews()
						.get(newsPosition).getImagePath()).execute();
				newsNextButton.setVisibility(View.VISIBLE);
			}else {
				newsNextButton.setVisibility(View.INVISIBLE);
			}
			newsBackButton.setVisibility(View.INVISIBLE);
		}

	}

	private class UpdateContentTask extends AsyncTask<Integer, Void, Void> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = ProgressDialog.show(NickiStarActivity.this, "",
					"Loading...");
		}

		@Override
		protected Void doInBackground(Integer... params) {
			getData("fact", true, true);
			getData("quote", true, true);
			getNews(true, true);
			Model.getInstance().loadVideos(params[0],
					getString(R.string.app_name), true);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			saveVideoToPhone();
			saveNewsToPhone();
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
			newsPosition = 0;
			videoPosition = 1;
			new YoutubeMetadataLodaerTask().execute();
			Toast toast = Toast.makeText(mContext, text, 1000);
			toast.show();
		}
	}

	/**
	 * task gets all data from youtube according to video tag
	 * 
	 * @author aleksandarvaricak
	 * 
	 */
	private class YoutubeMetadataLodaerTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = ProgressDialog.show(NickiStarActivity.this, "",
					"Loading...");
		}

		@Override
		protected Void doInBackground(Void... params) {
			Video video = Model.getInstance().getVideos().get(videoPosition);
			video.extractFromTag(video.getVideoTag());
			Model.getInstance().getVideos().put(videoPosition, video);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			saveVideoToPhone();
			progressDialog.dismiss();
			showCurrentVideo();
		}

	}

	/**
	 * Task for loading image
	 * 
	 * @author aleksandarvaricak
	 * 
	 */
	private class ImageLoaderTask extends AsyncTask<Void, Void, Drawable> {
		private ImageView view;
		private String url;
		Bitmap bitmap = null;

		ImageLoaderTask(ImageView view, String url) {
			this.view = view;
			this.url = url;
			this.view.setImageResource(R.drawable.loading);
		}

		@Override
		protected Drawable doInBackground(Void... params) {
			InputStream is = null;
			if (url != null && url.length() > 0) {
				url = url.replaceAll(" ", "%20");

				try {
					// is = new URL(url).openStream();
					HttpGet httpRequest = null;
					try {
						httpRequest = new HttpGet(url);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					}
					HttpClient httpclient = new DefaultHttpClient();
					HttpResponse response = (HttpResponse) httpclient
							.execute(httpRequest);
					HttpEntity entity = response.getEntity();
					BufferedHttpEntity bufHttpEntity = new BufferedHttpEntity(
							entity);
					is = bufHttpEntity.getContent();
					BitmapFactory.Options options = new BitmapFactory.Options();
					bitmap = BitmapFactory.decodeStream(is, null, options);
					Drawable drawable = new BitmapDrawable(bitmap);
					return drawable;
				} catch (Exception e) {
				} finally {
					bitmap = null;
					System.gc();
					try {
						if (is != null)
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

		@Override
		protected void onCancelled() {
			super.onCancelled();
			if (bitmap != null)
				bitmap.recycle();
		}

	}

}