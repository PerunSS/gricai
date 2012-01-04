package com.cerspri.star.NickiMinaj;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
	MultiDirectionSlidingDrawer mDrawer;
	MultiDirectionSlidingDrawer factsDrawer;
	MultiDirectionSlidingDrawer newsDrawer;
	Button quotesButton;
	TextView scrollText;
	TextView newsNumber;
	TextView newsHeader;
	LinearLayout textLayout;
	LinearLayout newsLayout;
	Button randomButton;
	Button shareButton;
	Button refreshButton;
	int state;
	AlertDialog alert;
	private Map<String, Integer> currents;
	private Map<String, List<String>> texts;
	private Map<Integer, NewsHolder> news;
	ProgressDialog progressDialog;
	int numberOfChanges;
	Context mContext = this;

	private static int PAGE_SIZE = 10;
	private static String NEWS_LINK_START = "<h3 class=r><a href=";
	private static String NEWS_LINK_END = "</a></h3>";
	private static String NEWS_TEXT_START = "<div class=st>";
	private static String NEWS_TEXT_END = "</div>";
	private static String NEWS_LINK_DATA_START = "class=l>";

	// Done on activity creation
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.disclaimer);
		SharedPreferences sPrefs = this.getSharedPreferences("nikiStarPrefs",
				MODE_WORLD_READABLE);
		if (sPrefs.getInt("isFirstTime", 1) == 1) {
			Intent myIntent = new Intent(this, Disclaimer.class);
			startActivityForResult(myIntent, 0);
		} else {
			checkForNetworkAndStart();
		}
	}

	private void checkForNetworkAndStart() {
		if (!isNetworkAvailable()) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage(
					"Internet connection is required for application to work. Try again?")
					.setCancelable(false)
					.setPositiveButton("Try again",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									checkForNetworkAndStart();
								}
							})
					.setNegativeButton("Close",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									NickiStarActivity.this.finish();
								}
							});
			AlertDialog alert = builder.create();
			alert.show();
		} else {
			buildGUI();
			texts = new HashMap<String, List<String>>();
			texts.put("fact",getData("fact",false));
			texts.put("quote",getData("quote",false));
			currents = new HashMap<String, Integer>();
			currents.put("fact", 0);
			currents.put("quote", 0);
			addButtonActions();
		}
	}

	private void addButtonActions() {
		// listener for opening/closing menu
		toggleMenuButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!mDrawer.isOpened()) {
					if (factsDrawer.isOpened()) {
						factsDrawer.animateClose();
						textLayout.setVisibility(View.INVISIBLE);
					}
					if (newsDrawer.isOpened()) {
						newsDrawer.animateClose();
						newsLayout.setVisibility(View.GONE);
						textLayout.setVisibility(View.INVISIBLE);
					}
					mDrawer.animateOpen();
					toggleMenuButton
							.setBackgroundResource(R.drawable.close_menu_button);
				} else {
					switch (state) {
					case 1:
						factsButton.performClick();
						break;
					case 2:
						quotesButton.performClick();
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
		});
		// listener for facts button
		factsButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mDrawer.animateClose();
				factsDrawer.animateOpen();
				state = 1;
				toggleMenuButton
						.setBackgroundResource(R.drawable.open_menu_button);
				scrollText.setText(getNext("fact"));
				final float scale = getResources().getDisplayMetrics().density;
				int padding_5dp = (int) (20 * scale + 0.5f);
				textLayout.setPadding(0, padding_5dp, 0, 0);
				textLayout.setVisibility(View.VISIBLE);

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
				scrollText.setText(getNext("quote"));
				final float scale = getResources().getDisplayMetrics().density;
				int padding_5dp = (int) (20 * scale + 0.5f);
				textLayout.setPadding(0, padding_5dp, 0, 0);
				textLayout.setVisibility(View.VISIBLE);

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
				displayMessage();
			}
		});
		randomButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String text = "";
				if (state == 1) {
					text = getNext("fact");
				} else if (state == 2) {
					text = getNext("quote");
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
	}

	private void buildGUI() {
		setContentView(R.layout.main);
		state = 0;

		// layout objects asignment
		toggleMenuButton = (Button) findViewById(R.id.toggle_menu);
		mDrawer = (MultiDirectionSlidingDrawer) findViewById(R.id.menuDrawer);
		factsButton = (Button) findViewById(R.id.facts_button);
		factsDrawer = (MultiDirectionSlidingDrawer) findViewById(R.id.factsDrawer);
		newsDrawer = (MultiDirectionSlidingDrawer) findViewById(R.id.newsDrawer);
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
		
		mDrawer.animateOpen();
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Coming soon!!!");
		alert = builder.create();
	}

	@Override
	public void onContentChanged() {
		super.onContentChanged();

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

	private String getNext(String name) {
		List<String> data = texts.get(name);
		int index = (currents.get(name) + 1) % data.size();
		currents.put(name, index);
		return data.get(index);
	}

	private StringBuilder readFromLink(String url) {
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		try {
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(builder.toString());
		return builder;
	}
	private List<String> getData(String name, boolean checkUpdates) {
		int version = getPreferences(MODE_WORLD_READABLE).getInt(
				"version_" + name, 0);
		List<String> data = new ArrayList<String>();
		try {
			Scanner sc = new Scanner(openFileInput(name));
			while (sc.hasNext()) {
				data.add(sc.nextLine());
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (checkUpdates) {
			StringBuilder builder = readFromLink("http://www.cerspri.com/api/stars/get_data.php?"
					+ "star="
					+ getString(R.string.app_name).replaceAll(" ", "%20")
					+ "&text_type=" + name + "&version=" + version);
			boolean changed = false;
			try {
				JSONObject jsonobj = new JSONObject(builder.toString());
				JSONArray elements = jsonobj.getJSONArray("data");
				numberOfChanges = elements.length();
				for (int i = 0; i < elements.length(); i++) {
					JSONObject elementobj = elements.getJSONObject(i);
					if (elementobj.getString("text_type")
							.equalsIgnoreCase(name)) {
						data.add(elementobj.getString("text_value"));
						if (version < elementobj.getInt("text_version")) {
							version = elementobj.getInt("text_version");
						}
						changed = true;
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			saveToPhone(name, version, data, changed);
		}
		return data;
	}

	private void saveToPhone(String name, int version, List<String> data,
			boolean changed) {
		SharedPreferences preferences = getPreferences(MODE_WORLD_WRITEABLE);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putInt("version_" + name, version);
		editor.commit();
		if (changed) {
			try {
				PrintWriter writer = new PrintWriter(openFileOutput(name,
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

	private boolean isNetworkAvailable() {
		try {
			ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo activeNetworkInfo = connectivityManager
					.getActiveNetworkInfo();
			return activeNetworkInfo.isConnected();
		} catch (Exception e) {
			return false;
		}
	}

	private Map<Integer, NewsHolder> getNews(int page) {
		if (news == null) {
			news = new TreeMap<Integer, NickiStarActivity.NewsHolder>();
		}
		StringBuilder builder = readFromLink("http://www.google.com/search?q="
				+ getString(R.string.app_name).toLowerCase().replace(' ', '+')
				+ "&hl=en&prmd=imvnso&source=lnms&tbm=nws");
		boolean hasNext = true;
		int linkIndex = 0;
		int index = page * PAGE_SIZE;
		while (hasNext) {

			linkIndex = builder.indexOf(NEWS_LINK_START, linkIndex + 1);
			NewsHolder holder = news.get(index);
			if (holder == null) {
				holder = new NewsHolder();
			}
			boolean shouldAdd = false;
			if (linkIndex > 0) {
				int endIndex = builder.indexOf(NEWS_LINK_END, linkIndex);
				shouldAdd = holder.extractLink(builder.substring(linkIndex,
						endIndex));
				int textIndex = builder.indexOf(NEWS_TEXT_START, linkIndex);
				if (textIndex > 0 && shouldAdd) {
					endIndex = builder.indexOf(NEWS_TEXT_END, textIndex);
					shouldAdd = holder.extractText(builder.substring(textIndex,
							endIndex));
				} else {
					hasNext = false;
				}
			} else {
				hasNext = false;
			}
			if (shouldAdd) {
				news.put(index, holder);
				index++;
			}
		}

		return news;
	}

	private class NewsHolder {
		String link;
		String linkDescription;
		String text;

		boolean extractLink(String linkData) {
			// +1 se dodaje da se preskoce znaci navoda
			System.out.println(linkData);
			try {
				int linkIndex = NEWS_LINK_START.length() + 1;
				int endIndex = linkData.indexOf("\" class");
				link = linkData.substring(linkIndex, endIndex);
				int linkDescIndex = linkData.indexOf(NEWS_LINK_DATA_START);
				linkDescIndex += NEWS_LINK_DATA_START.length();
				linkDescription = linkData.substring(linkDescIndex).replaceAll(
						"em>", "b>");
				return true;
			} catch (IndexOutOfBoundsException exception) {
				return false;
			}
		}

		boolean extractText(String textData) {
			try {
				int textStartIndex = NEWS_TEXT_START.length();
				text = textData.substring(textStartIndex).replaceAll("em>",
						"b>");
				return true;
			} catch (IndexOutOfBoundsException exception) {
				return false;
			}
		}

		@Override
		public String toString() {
			return "(" + link + ", " + linkDescription + ", " + text + ")";
		}

	}

	private class JSONLoaderTask extends AsyncTask<String, Void, Void> {

		@Override
		protected Void doInBackground(String... params) {
			for (String name : params) {
				if (currents == null)
					currents = new HashMap<String, Integer>();
				currents.put(name, 0);
				List<String> data = getData(name,true);
				Collections.shuffle(data);
				if (texts == null)
					texts = new HashMap<String, List<String>>();
				texts.put(name, data);
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
			Toast toast = Toast.makeText(mContext, new Integer(numberOfChanges).toString()+" new items loaded", 1000);
			toast.show();
		}
	}

	@SuppressWarnings("unused")
	private class NewsLoaderTask extends AsyncTask<Integer, Void, Void> {

		@Override
		protected Void doInBackground(Integer... params) {
			getNews(params[0]);
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
			System.out.println("NEWS: ");
			System.out.println(news);
			mDrawer.animateClose();
			newsDrawer.animateOpen();
			state = 3;

			toggleMenuButton.setBackgroundResource(R.drawable.open_menu_button);
			scrollText.setText(Html.fromHtml(news.get(0).text));
			newsNumber.setText("1/10");
			newsHeader.setText(Html.fromHtml("<a href = " + news.get(0).link)
					+ ">" + news.get(0).linkDescription);

			final float scale = getResources().getDisplayMetrics().density;
			int padding_50dp = (int) (90 * scale + 0.5f);

			newsLayout.setVisibility(View.VISIBLE);
			textLayout.setPadding(0, newsLayout.getHeight() + padding_50dp, 0,
					0);

			scrollText.setText(new Integer(newsLayout.getHeight()).toString());
			textLayout.setVisibility(View.VISIBLE);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 0) {
			if (resultCode == 4373) {
				finish();
			} else if (resultCode == 4372) {
				checkForNetworkAndStart();
				new JSONLoaderTask().execute("quote", "fact");
			}
		}
	}
}