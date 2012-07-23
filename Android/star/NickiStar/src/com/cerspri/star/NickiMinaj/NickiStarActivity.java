package com.cerspri.star.NickiMinaj;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cerspri.star.NickiMinaj.db.DBManager;
import com.cerspri.star.NickiMinaj.model.Constants;
import com.cerspri.star.NickiMinaj.model.Model;
import com.cerspri.star.NickiMinaj.model.News;
import com.cerspri.star.NickiMinaj.model.Video;

/**
 * main application activity
 * 
 * @author churava, sale
 * 
 */

public class NickiStarActivity extends Activity {

	private State state;
	private ProgressDialog progressDialog;
	private Context mContext = this;

	private int newsVersion = 0;
	private ImageLoaderTask imageTask;

	private ImageView videoMediaView;
	private ImageView newsMediaView;

	//private DBManager manager;

	private enum State {
		MAIN, SECOND
	}

	private void loadData() {
		//manager = new DBManager(this);
		// boolean rated = false;
		// SharedPreferences preferences = getPreferences(MODE_WORLD_READABLE);
		// rated = preferences.getBoolean("isRated", false);
		//Model.getInstance().setManager(manager);
		Model.getInstance().setTextData("facts", getData("facts"));
		Model.getInstance().setTextData("quotes", getData("quotes"));
		// Model.getInstance().loadTextData(rated);
		Model.getInstance().setVideos(getVideos());
		//Model.getInstance().loadVideos();
		Model.getInstance().loadNews();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setMainView();
		loadData();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			switch (state) {
			case MAIN:
				return super.onKeyDown(keyCode, event);
			case SECOND:
				setMainView();
				return false;
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	private void setMainView() {
		state = State.MAIN;
		setContentView(R.layout.main);

		final Button quotesButton = (Button) findViewById(R.id.quotes);
		quotesButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setQuotesView();
			}
		});
		final Button factsButton = (Button) findViewById(R.id.facts);
		factsButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setFactsView();
			}
		});
		final Button videosButton = (Button) findViewById(R.id.videos);
		videosButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				setVideoView();

			}
		});
		final Button photosButton = (Button) findViewById(R.id.photos);
		photosButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				setPhotoView();
			}
		});

		final Button newsButton = (Button) findViewById(R.id.news);
		newsButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				setNewsView();
			}
		});

		final Button rateUsButton = (Button) findViewById(R.id.rate_us);
		rateUsButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				setRateView();

			}
		});

		final Button refreshButton = (Button) findViewById(R.id.refresh);
		refreshButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				refreshData();
			}
		});
	}

	private void setQuotesView() {
		state = State.SECOND;
		setContentView(R.layout.fq_view);
		final TextView view = (TextView) findViewById(R.id.text);
		view.setText(Model.getInstance().currentQuote());
		final Button randomButton = (Button) findViewById(R.id.random_button);
		randomButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				view.setText(Model.getInstance().nextQuote());
			}
		});
		final Button shareButton = (Button) findViewById(R.id.share_button);
		shareButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				share(view.getText().toString(), "quote");
			}
		});
	}

	private void setFactsView() {
		state = State.SECOND;
		setContentView(R.layout.fq_view);
		final TextView view = (TextView) findViewById(R.id.text);
		view.setText(Model.getInstance().currentFact());
		final Button randomButton = (Button) findViewById(R.id.random_button);
		randomButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				view.setText(Model.getInstance().nextFact());
			}
		});
		final Button shareButton = (Button) findViewById(R.id.share_button);
		shareButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				share(view.getText().toString(), "fact");
			}
		});
	}

	private void share(String text, String type) {
		String subject = " about " + getString(R.string.app_name);
		subject = type + subject;
		final Intent intent = new Intent(Intent.ACTION_SEND);

		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_SUBJECT, subject);
		intent.putExtra(Intent.EXTRA_TEXT, text);

		startActivity(Intent.createChooser(intent, type + " about "
				+ getString(R.string.app_name)));
	}

	private void setVideoView() {
		state = State.SECOND;
		setContentView(R.layout.video_view);
		Video v = Model.getInstance().currentVideo();
		showVideo(v);
		final Button nextVideoButton = (Button) findViewById(R.id.next_button);
		nextVideoButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Video video = Model.getInstance().nextVideo();
				showVideo(video);
			}
		});
		final Button previousVideoButton = (Button) findViewById(R.id.prev_button);
		previousVideoButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Video video = Model.getInstance().previousVideo();
				showVideo(video);
			}
		});
		final Button playVideoButton = (Button) findViewById(R.id.play_button);
		playVideoButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri
						.parse("http://www.youtube.com/watch?v="
								+ Model.getInstance().currentVideo()
										.getVideoTag())));
			}
		});
	}

	private void showVideo(Video v) {
		if (v != null && v.getTitle() != null) {
			TextView title = (TextView) findViewById(R.id.video_title);
			title.setText(v.getTitle().toUpperCase());
		}
		if (v != null && v.getDescription() != null) {
			TextView text = (TextView) findViewById(R.id.video_text);
			text.setText(v.getDescription());
		}
		videoMediaView = (ImageView) findViewById(R.id.video_image);
		videoMediaView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri
						.parse("http://www.youtube.com/watch?v="
								+ Model.getInstance().currentVideo()
										.getVideoTag())));
			}
		});
		if (v != null && v.getImagePath() != null) {
			if (imageTask != null
					&& imageTask.getStatus() == ImageLoaderTask.Status.RUNNING) {
				imageTask.cancel(true);
				imageTask = null;
			}
			imageTask = new ImageLoaderTask(videoMediaView, v.getImagePath());
			imageTask.execute();
		}
	}

	private void setPhotoView() {
		Uri uriUrl = Uri.parse("http://www.google.rs/search?q="
				+ getString(R.string.app_name).toLowerCase().replace(' ', '+')
				+ "&hl=sr&prmd=imvnso&source=lnms&tbm=isch");
		Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
		startActivity(launchBrowser);
	}

	private void setNewsView() {
		state = State.SECOND;
		setContentView(R.layout.news_view);
		if (Model.getInstance().getNews() == null
				|| Model.getInstance().getNews().size() == 0) {
			new NewsLoaderTask().execute(newsVersion);
		} else {
			showNews(Model.getInstance().currentNews());
		}
		final Button nextVideoButton = (Button) findViewById(R.id.next_button);
		nextVideoButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showNews(Model.getInstance().nextNews());
			}
		});
		final Button previousVideoButton = (Button) findViewById(R.id.prev_button);
		previousVideoButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showNews(Model.getInstance().prevoiusNews());
			}
		});
		final Button readNewsButton = (Button) findViewById(R.id.read_button);
		readNewsButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Uri uriUrl = Uri.parse(Model.getInstance().currentNews()
						.getNewsUrl());
				Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
				startActivity(launchBrowser);
			}
		});

	}

	private void showNews(News news) {
		if (news != null && news.getTitle() != null) {
			TextView newsTitle = (TextView) findViewById(R.id.news_title);
			newsTitle.setText(Html.fromHtml(news.getTitle()));
		}
		if (news != null && news.getPubDate() != null) {
			TextView newsPubDate = (TextView) findViewById(R.id.news_date);
			newsPubDate.setText(news.getPubDate());
		}
		if (news != null && news.getContent() != null) {
			TextView newsText = (TextView) findViewById(R.id.news_text);
			newsText.setText(Html.fromHtml(news.getContent()));
		}
		newsMediaView = (ImageView) findViewById(R.id.news_image);
		if (news != null && news.getImagePath() != null) {
			if (imageTask != null
					&& imageTask.getStatus() == ImageLoaderTask.Status.RUNNING) {
				imageTask.cancel(true);
				imageTask = null;
			}
			imageTask = new ImageLoaderTask(newsMediaView, news.getImagePath());
			imageTask.execute();
		}
	}

	private void setRateView() {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("market://" + Constants.APPLICATION_QUERY));
		startActivity(intent);
	}

	private void refreshData() {
		new UpdateContentTask().execute(newsVersion);
	}

	private void getNews(boolean shouldUpdate) {
		Model.getInstance().loadNews();
		newsVersion = getPreferences(MODE_WORLD_READABLE).getInt("newsVersion",
				0);
		if (shouldUpdate) {
			int newNewsVersion = Model.getInstance().loadNews(newsVersion,
					Constants.STAR_NAME);
			if (newNewsVersion > newsVersion) {
				newsVersion = newNewsVersion;
				SharedPreferences.Editor editor = getPreferences(
						MODE_WORLD_WRITEABLE).edit();
				editor.putInt("newsVersion", newNewsVersion);
				editor.commit();
			}
		}
	}

	private List<String> getData(String type) {
		List<String> data = new ArrayList<String>();
		int id = R.array.facts;
		if (type.equalsIgnoreCase("quotes")) {
			id = R.array.quotes;
		}
		for (String str : getResources().getStringArray(id)) {
			data.add(str);
		}
		Collections.shuffle(data);
		return data;
	}
	
	private List<Video> getVideos(){
		List<Video> data = new ArrayList<Video>();
		int i = 0;
		for (String str:getResources().getStringArray(R.array.videos)){
			String tmp[] = str.split("|"); //tag|title|description|image_path
			if(tmp.length!=4)
				continue;
			Video video = new Video();
			video.setVideoTag(tmp[0]);
			video.setTitle(tmp[1]);
			video.setDescription(tmp[2]);
			video.setImagePath(tmp[3]);
			data.add(video);
			i++;
		}
		System.out.println("Loaded videos: "+i);
		return data;
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
			getNews(true);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			progressDialog.dismiss();
			showNews(Model.getInstance().currentNews());
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
			getNews(true);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			progressDialog.dismiss();
			String text = "Nothing to fetch";
			if (Model.getInstance().getNewNews() > 0)
				text = "Fetched " + Model.getInstance().getNewNews() + " news";
			Toast toast = Toast.makeText(mContext, text, 1000);
			toast.show();
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