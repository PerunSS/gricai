package com.cerspri.star.NickiMinaj;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

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

	private DBManager manager;
	
	private enum State {
		MAIN, SECOND
	}

	private void loadData() {
		manager = new DBManager(this);
		boolean rated = false;
		SharedPreferences preferences = getPreferences(MODE_WORLD_READABLE);
		rated = preferences.getBoolean("isRated", false);
		Model.getInstance().setManager(manager);
		Model.getInstance().loadTextData(rated);
		Model.getInstance().loadVideos();
		getNews(false);
	}

	// Done on activity creation
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setMainView();
		loadData();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
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
		view.setText(Model.getInstance().nextQuote());
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
		view.setText(Model.getInstance().nextFact());
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
		if (Model.getInstance().getNews() == null || Model.getInstance().getNews().size() == 0) {
			new NewsLoaderTask().execute(newsVersion);
		}else{
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

	/*
	 * private void startApp() { buildGUI(); Model.getInstance().createMaps();
	 * if (!initial) { getData("fact", false, false); getData("quote", false,
	 * false); getVideos(); getNews(false, false); } addButtonActions(); }
	 * 
	 * private void addButtonActions() { // listener for opening/closing menu
	 * OnClickListener toggleMenuButtonListener = new View.OnClickListener() {
	 * 
	 * @Override public void onClick(View v) { if (!mDrawer.isOpened()) { if
	 * (factsDrawer.isOpened()) { factsDrawer.animateClose();
	 * textLayout.setVisibility(View.GONE); } if (videoButtonsDrawer.isOpened())
	 * { videoButtonsDrawer.animateClose();
	 * videosLayout.setVisibility(View.GONE); } if (newsDrawer.isOpened()) {
	 * newsDrawer.animateClose(); newsLayout.setVisibility(View.GONE); }
	 * mDrawer.animateOpen(); toggleMenuButton
	 * .setBackgroundResource(R.drawable.close_menu_button); menuShown = true; }
	 * else { menuShown = false; switch (state) { case FACTS: isToogle = true;
	 * factsButton.performClick(); break; case QUOTES: isToogle = true;
	 * //quotesButton.performClick(); break; case VIDEOS: isToogle = true; if
	 * (videoPosition > 0) { mDrawer.animateClose();
	 * videoButtonsDrawer.animateOpen();
	 * videosLayout.setVisibility(View.VISIBLE); state = State.VIDEOS;
	 * toggleMenuButton .setBackgroundResource(R.drawable.open_menu_button);
	 * isToogle = false; menuShown = false; } else {
	 * videosButton.performClick(); } break; case NEWS: isToogle = true;
	 * newsButton.performClick(); break; // case 3: //
	 * newsButton.performClick(); // break; default: mDrawer.animateClose();
	 * toggleMenuButton .setBackgroundResource(R.drawable.open_menu_button); } }
	 * } }; toggleMenuButton.setOnClickListener(toggleMenuButtonListener); //
	 * listener for facts button factsButton.setOnClickListener(new
	 * View.OnClickListener() {
	 * 
	 * @Override public void onClick(View v) { mDrawer.animateClose();
	 * factsDrawer.animateOpen(); state = State.FACTS; toggleMenuButton
	 * .setBackgroundResource(R.drawable.open_menu_button); if (!isToogle)
	 * scrollText.setText(Model.getInstance().getNext("fact")); final float
	 * scale = getResources().getDisplayMetrics().density; int padding_5dp =
	 * (int) (20 * scale + 0.5f); textLayout.setPadding(0, padding_5dp, 0, 0);
	 * textLayout.setVisibility(View.VISIBLE); isToogle = false; menuShown =
	 * false; } }); // listener for quotes button //
	 * quotesButton.setOnClickListener(new View.OnClickListener() { // @Override
	 * // public void onClick(View v) { // mDrawer.animateClose(); //
	 * factsDrawer.animateOpen(); // state = State.QUOTES; // toggleMenuButton
	 * // .setBackgroundResource(R.drawable.open_menu_button); // if (!isToogle)
	 * // scrollText.setText(Model.getInstance().getNext("quote")); // final
	 * float scale = getResources().getDisplayMetrics().density; // int
	 * padding_5dp = (int) (20 * scale + 0.5f); // textLayout.setPadding(0,
	 * padding_5dp, 0, 0); // textLayout.setVisibility(View.VISIBLE); //
	 * isToogle = false; // menuShown = false; // } // });
	 * 
	 * newsNextButton.setOnClickListener(new View.OnClickListener() {
	 * 
	 * @Override public void onClick(View v) { if (newsPosition <
	 * Model.getInstance().getNews().size() - 1) { newsPosition++;
	 * showCurrentNews(); } if (newsPosition + 1 ==
	 * Model.getInstance().getNews().size()) {
	 * newsNextButton.setVisibility(View.INVISIBLE); } else if (newsPosition >
	 * 0) { newsBackButton.setVisibility(View.VISIBLE); } } });
	 * 
	 * newsBackButton.setOnClickListener(new View.OnClickListener() {
	 * 
	 * @Override public void onClick(View v) { if (newsPosition > 0) {
	 * newsPosition--; showCurrentNews(); } if (newsPosition == 0) {
	 * newsBackButton.setVisibility(View.INVISIBLE); } else if (newsPosition <
	 * Model.getInstance().getNews().size() - 1) {
	 * newsNextButton.setVisibility(View.VISIBLE); } } });
	 * 
	 * // listener for news button newsButton.setOnClickListener(new
	 * View.OnClickListener() {
	 * 
	 * @Override public void onClick(View v) { // displayMessage(); // new
	 * NewsLoaderTask().execute(0); if (newsPosition >= 0) {
	 * mDrawer.animateClose(); newsDrawer.animateOpen();
	 * newsLayout.setVisibility(View.VISIBLE); state = State.NEWS;
	 * toggleMenuButton .setBackgroundResource(R.drawable.open_menu_button);
	 * isToogle = false; menuShown = false; if (newsPosition == 0) {
	 * newsBackButton.setVisibility(View.INVISIBLE); } showCurrentNews(); } else
	 * { new NewsLoaderTask().execute(newsVersion); } } });
	 * 
	 * newsReadButton.setOnClickListener(new View.OnClickListener() {
	 * 
	 * @Override public void onClick(View v) { Uri uriUrl =
	 * Uri.parse(Model.getInstance().getNews() .get(newsPosition).getNewsUrl());
	 * Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
	 * startActivity(launchBrowser); } });
	 * 
	 * newsLinkLayout.setOnClickListener(new View.OnClickListener() {
	 * 
	 * @Override public void onClick(View v) { newsReadButton.performClick(); }
	 * }); // listener for pictures button pictButton.setOnClickListener(new
	 * View.OnClickListener() {
	 * 
	 * @Override public void onClick(View v) { Uri uriUrl =
	 * Uri.parse("http://www.google.rs/search?q=" +
	 * getString(R.string.app_name).toLowerCase().replace( ' ', '+') +
	 * "&hl=sr&prmd=imvnso&source=lnms&tbm=isch"); Intent launchBrowser = new
	 * Intent(Intent.ACTION_VIEW, uriUrl); startActivity(launchBrowser); //
	 * displayMessage(); } }); // listener for videos button
	 * videosButton.setOnClickListener(new View.OnClickListener() {
	 * 
	 * @Override public void onClick(View v) { if (videoPosition > 0) {
	 * 
	 * mDrawer.animateClose(); videoButtonsDrawer.animateOpen();
	 * videosLayout.setVisibility(View.VISIBLE); state = State.VIDEOS;
	 * toggleMenuButton .setBackgroundResource(R.drawable.open_menu_button);
	 * isToogle = false; menuShown = false; if (videoPosition == 1) {
	 * videoBackButton.setVisibility(View.INVISIBLE); } showCurrentVideo(); }
	 * else { new VideosLodaerTask().execute(Model.getInstance()
	 * .getLastVideoID()); } } }); randomButton.setOnClickListener(new
	 * View.OnClickListener() {
	 * 
	 * @Override public void onClick(View v) { String text = ""; if (state ==
	 * State.FACTS) { text = Model.getInstance().getNext("fact"); } else if
	 * (state == State.QUOTES) { text = Model.getInstance().getNext("quote"); }
	 * scrollText.setText(text); } }); refreshButton.setOnClickListener(new
	 * View.OnClickListener() {
	 * 
	 * @Override public void onClick(View v) { if (state == State.FACTS) { new
	 * JSONLoaderTask().execute("fact"); } else if (state == State.QUOTES) { new
	 * JSONLoaderTask().execute("quote"); } } });
	 * 
	 * shareButton.setOnClickListener(new View.OnClickListener() {
	 * 
	 * @Override public void onClick(View v) { String text =
	 * scrollText.getText().toString(); String subject = " about " +
	 * getString(R.string.app_name); String type = ""; if (state == State.FACTS)
	 * { type = "fact"; } else if (state == State.QUOTES) { type = "quote"; }
	 * subject = type + subject; share(subject, text, type); } });
	 * 
	 * videoBackButton.setOnClickListener(new View.OnClickListener() {
	 * 
	 * @Override public void onClick(View v) { if (videoPosition > 1) {
	 * videoPosition--; showCurrentVideo(); } if (videoPosition == 1) {
	 * videoBackButton.setVisibility(View.INVISIBLE); } else if (videoPosition <
	 * Model.getInstance().getVideos() .size()) {
	 * videoNextButton.setVisibility(View.VISIBLE); } } });
	 * 
	 * videoNextButton.setOnClickListener(new View.OnClickListener() {
	 * 
	 * @Override public void onClick(View v) { if (videoPosition <
	 * Model.getInstance().getVideos().size()) { videoPosition++; if
	 * (Model.getInstance().getVideos().get(videoPosition) .getTitle() == null)
	 * { new YoutubeMetadataLodaerTask().execute(); } else { showCurrentVideo();
	 * } } if (videoPosition == Model.getInstance().getVideos().size()) {
	 * videoNextButton.setVisibility(View.INVISIBLE); } else if (videoPosition >
	 * 1) { videoBackButton.setVisibility(View.VISIBLE); } } });
	 * 
	 * videoPlayButton.setOnClickListener(videoPlayListener);
	 * 
	 * updateButton.setOnClickListener(new View.OnClickListener() {
	 * 
	 * @Override public void onClick(View v) { new
	 * UpdateContentTask().execute(Model.getInstance() .getLastVideoID()); } });
	 * 
	 * rateButton.setOnClickListener(new View.OnClickListener() {
	 * 
	 * @Override public void onClick(View v) { Intent intent = new
	 * Intent(Intent.ACTION_VIEW); intent.setData(Uri
	 * .parse("market://details?id=com.cerspri.star.NickiMinaj"));
	 * startActivity(intent); } }); }
	 * 
	 * private void buildGUI() { setContentView(R.layout.main); state =
	 * State.NULL;
	 * 
	 * // layout objects asignment toggleMenuButton = (Button)
	 * findViewById(R.id.toggle_menu); mDrawer = (MultiDirectionSlidingDrawer)
	 * findViewById(R.id.menuDrawer); factsButton = (Button)
	 * findViewById(R.id.facts_button); factsDrawer =
	 * (MultiDirectionSlidingDrawer) findViewById(R.id.factsDrawer);
	 * videoButtonsDrawer = (MultiDirectionSlidingDrawer)
	 * findViewById(R.id.videoButtonDrawer); //quotesButton = (Button)
	 * findViewById(R.id.quotes_button); newsButton = (Button)
	 * findViewById(R.id.news_button); pictButton = (Button)
	 * findViewById(R.id.pict_button); videosButton = (Button)
	 * findViewById(R.id.videos_button); scrollText = (TextView)
	 * findViewById(R.id.scroll_text); randomButton = (Button)
	 * findViewById(R.id.random_button); shareButton = (Button)
	 * findViewById(R.id.share_button); textLayout = (LinearLayout)
	 * findViewById(R.id.text_layout); newsTitle = (TextView)
	 * findViewById(R.id.news_title); newsImage = (ImageView)
	 * findViewById(R.id.news_picture_link); newsText = (TextView)
	 * findViewById(R.id.news_text); newsDate = (TextView)
	 * findViewById(R.id.news_date); newsLayout = (LinearLayout)
	 * findViewById(R.id.news_layout); newsDrawer =
	 * (MultiDirectionSlidingDrawer) findViewById(R.id.newsButtonDrawer);
	 * refreshButton = (Button) findViewById(R.id.refresh_button); videosLayout
	 * = (LinearLayout) findViewById(R.id.videos_layout); videoTitle =
	 * (TextView) findViewById(R.id.video_title); videoDescription = (TextView)
	 * findViewById(R.id.video_description); videoImage = (ImageView)
	 * findViewById(R.id.video_picture_link); videoBackButton = (Button)
	 * findViewById(R.id.video_back_button); videoNextButton = (Button)
	 * findViewById(R.id.video_next_button); videoPlayButton = (Button)
	 * findViewById(R.id.play_video_button); newsNextButton = (Button)
	 * findViewById(R.id.news_next_button); newsBackButton = (Button)
	 * findViewById(R.id.news_back_button); newsReadButton = (Button)
	 * findViewById(R.id.read_news_button); newsLinkLayout = (LinearLayout)
	 * findViewById(R.id.news_link_layout); updateButton = (Button)
	 * findViewById(R.id.update_button); rateButton = (Button)
	 * findViewById(R.id.rate_button);
	 * 
	 * mDrawer.animateOpen(); AlertDialog.Builder builder = new
	 * AlertDialog.Builder(this); builder.setMessage("Coming soon!!!"); alert =
	 * builder.create();
	 * 
	 * videoPlayListener = new View.OnClickListener() {
	 * 
	 * @Override public void onClick(View v) { Intent videoIntent = new
	 * Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" +
	 * Model.getInstance().getVideos() .get(videoPosition).getVideoTag()));
	 * List<ResolveInfo> activities = getPackageManager()
	 * .queryIntentActivities(videoIntent, PackageManager.MATCH_DEFAULT_ONLY);
	 * if (activities.size() > 0) { startActivity(videoIntent); } else {
	 * Toast.makeText( NickiStarActivity.this,
	 * "You do not have application that support playing video files!",
	 * Toast.LENGTH_LONG).show(); } } }; }
	 * 
	 * protected void displayMessage() { state = State.NULL; alert.show(); final
	 * Timer t = new Timer(); t.schedule(new TimerTask() { public void run() {
	 * alert.dismiss(); t.cancel(); } }, 2000); }
	 * 
	 * private void getData(String type, boolean shouldUpdate, boolean initial)
	 * { List<String> data = new ArrayList<String>(); try { Scanner sc = new
	 * Scanner(openFileInput(type)); while (sc.hasNext()) {
	 * data.add(sc.nextLine()); } Collections.shuffle(data); sc.close();
	 * Model.getInstance().getTexts().put(type, data); } catch
	 * (FileNotFoundException e) { e.printStackTrace(); } if (shouldUpdate) {
	 * int version = getPreferences(MODE_WORLD_READABLE).getInt( "version_" +
	 * type, 0); int newVersion = Model.getInstance().loadData(type,
	 * getString(R.string.app_name), version, initial); if (newVersion >
	 * version) { version = newVersion; saveToPhone(type, version,
	 * Model.getInstance().getTexts().get(type), true); } } }
	 * 
	 * private void getVideos() { try { ObjectInputStream ois = new
	 * ObjectInputStream( openFileInput("videos")); boolean read = true; int max
	 * = 0; while (read) { try { Video video = (Video) ois.readObject();
	 * Model.getInstance().getVideos().put(video.getId(), video); if
	 * (video.getId() > max) { max = video.getId(); } } catch (Exception e) {
	 * read = false; } } ois.close(); Model.getInstance().setLastVideoID(max);
	 * if (max > 0) videoPosition = 1; } catch (FileNotFoundException e) {
	 * e.printStackTrace(); } catch (StreamCorruptedException e) {
	 * e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); } }
	 */
	private void getNews(boolean shouldUpdate) {
		try {
			ObjectInputStream ois = new ObjectInputStream(openFileInput("news"));
			boolean read = true;
			System.out.println("READ NEWS:");
			while (read) {
				try {
					News news = (News) ois.readObject();
					Model.getInstance().getNews().add(news);
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
		//TODO remove
		System.out.println(Model.getInstance().getNews().size());
		newsVersion = getPreferences(MODE_WORLD_READABLE).getInt("newsVersion",
				0);
		if (shouldUpdate) {
			int newNewsVersion = Model.getInstance().loadNews(newsVersion,
					Constants.STAR_NAME);
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
			System.out.println("SAVING NEWS: "+Model.getInstance().getNews().size());
			for (News news : Model.getInstance().getNews()) {
				oos.writeObject(news);
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

	/*
	 * private void saveToPhone(String type, int version, List<String> data,
	 * boolean changed) { SharedPreferences preferences =
	 * getPreferences(MODE_WORLD_WRITEABLE); SharedPreferences.Editor editor =
	 * preferences.edit(); editor.putInt("version_" + type, version);
	 * editor.commit(); if (changed) { try { PrintWriter writer = new
	 * PrintWriter(openFileOutput(type, MODE_WORLD_WRITEABLE)); for (String
	 * value : data) { writer.println(value); } writer.close(); } catch
	 * (FileNotFoundException e) { e.printStackTrace(); }
	 * 
	 * } }
	 * 
	 * @Override protected void onActivityResult(int requestCode, int
	 * resultCode, Intent data) { if (requestCode == 0) { if (resultCode ==
	 * Codes.NO_DATA_LOAD_ALLOWED) { finish(); } else if (resultCode ==
	 * Codes.ACCEPT_LOAD) { initial = true; startApp(); loadData(); } else if
	 * (resultCode == Codes.NO_INTERNET_CONNECTION) { finish(); } } }
	 * 
	 * @Override public boolean onKeyDown(int keyCode, KeyEvent event) { if
	 * (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) { if
	 * (menuShown) { return super.onKeyDown(keyCode, event); } else {
	 * toggleMenuButton.performClick(); return true; } } if (keyCode ==
	 * KeyEvent.KEYCODE_MENU) { toggleMenuButton.performClick(); return true; }
	 * return super.onKeyDown(keyCode, event); }
	 * 
	 * private void updatePrefs() { SharedPreferences sPrefs =
	 * getSharedPreferences("nikiStarPrefs", MODE_WORLD_READABLE);
	 * SharedPreferences.Editor editor = sPrefs.edit();
	 * editor.putInt("isFirstTime", 0); editor.commit(); }
	 * 
	 * private void showCurrentVideo() { Video v =
	 * Model.getInstance().getVideos().get(videoPosition); if (v != null &&
	 * v.getTitle() != null) videoTitle.setText(v.getTitle()); if (v != null &&
	 * v.getDescription() != null) {
	 * videoDescription.setText(v.getDescription()); }
	 * videoImage.setOnClickListener(videoPlayListener); if (v != null &&
	 * v.getImagePath() != null) { if (imageTask != null &&
	 * imageTask.getStatus() == ImageLoaderTask.Status.RUNNING) {
	 * imageTask.cancel(true); imageTask = null; } imageTask = new
	 * ImageLoaderTask(videoImage, v.getImagePath()); imageTask.execute(); } }
	 * 
	 * private void showCurrentNews() {
	 * newsTitle.setText(Html.fromHtml(Model.getInstance().getNews()
	 * .get(newsPosition).getTitle()));
	 * newsText.setText(Html.fromHtml(Model.getInstance().getNews()
	 * .get(newsPosition).getContent()));
	 * newsImage.setOnClickListener(videoPlayListener);
	 * newsDate.setText(Model.getInstance().getNews().get(newsPosition)
	 * .getPubDate()); new ImageLoaderTask(newsImage,
	 * Model.getInstance().getNews()
	 * .get(newsPosition).getImagePath()).execute(); }
	 */

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
			saveNewsToPhone();
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
			saveNewsToPhone();
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
			System.out.println(url);
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