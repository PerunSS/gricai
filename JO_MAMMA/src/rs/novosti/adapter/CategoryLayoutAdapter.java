package rs.novosti.adapter;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import rs.novosti.NovostiCela;
import rs.novosti.R;
import rs.novosti.model.Article;
import rs.novosti.model.Main;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 * Adapter class for loading data into category layout view
 * @author aleksandarvaricak
 *
 */
public class CategoryLayoutAdapter extends BaseAdapter {

	private int firstPart = 0;
	private int secondPart = 5;

	private LayoutInflater inflater;
	private List<Article> articles;
	private Drawable categoryBigDrawable;
	private Context context;
	private String categoryName;

	public CategoryLayoutAdapter(Context context, String categoryName) {
		inflater = LayoutInflater.from(context);
		this.categoryName = categoryName;
		Main.getInstance().readCategory(categoryName);
		this.articles = Main.getInstance().getCategories().get(categoryName).getArticles();
		this.context = context;

	}

	@Override
	public int getCount() {
		return articles.size();
	}

	@Override
	public Object getItem(int position) {
		return articles.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	/**
	 * Method for creating a new ImageView for each item referenced by the Adapter
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		final Article article = articles.get(position);
		if (position < firstPart) {
			FirstHolder firstHolder = null;
			// if (convertView == null) {
			convertView = inflater.inflate(R.layout.first_article_style, null);
			firstHolder = new FirstHolder();
			firstHolder.articleLayout = (LinearLayout) convertView
					.findViewById(R.id.firstStyleArticle);
			if(categoryBigDrawable == null){
				categoryBigDrawable = getResizedDrawable(article.getPhotoPath());
			}
			firstHolder.articleLayout
					.setBackgroundDrawable(categoryBigDrawable);
			firstHolder.articleTitle = (TextView) convertView
					.findViewById(R.id.firstStyleArticle_title);
			convertView.setTag(firstHolder);
			// } else {
			// firstHolder = (FirstHolder) convertView.getTag();
			// }

			// ovde bi trebalo da stvarno vadi iz artikala sa tim sto sliku
			// nisam napravio
			// posto ne znam kako od urla
			// Article article = articles.get(position);
			// firstHolder.articleTitle.setText(article.getName());
			// firstHolder.articleImage;

			firstHolder.articleTitle.setText(Html.fromHtml(article.getTitle()));
			firstHolder.articleTitle
					.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							Intent myIntent = new Intent(v.getContext(),
									NovostiCela.class);
							myIntent.putExtra("article", article);
							((Activity)context).startActivityForResult(myIntent, 0);
						}
					});
			// firstHolder.articleLayout.setBackgroundResource(R.drawable.b1);

		}
		// ovde ubacuje ako je drugog tipa artikl ( slicica ime mali tekst)
		else if (position < secondPart) {
			SecondHolder secondHolder;
			// if (convertView == null) {
			convertView = inflater.inflate(R.layout.second_article_style, null);
			secondHolder = new SecondHolder();
			secondHolder.secondStyleArticleLayout = (LinearLayout) convertView
					.findViewById(R.id.secondStyleArticle);
			secondHolder.secondStyleArticleShortText = (TextView) convertView
					.findViewById(R.id.secondStyleArticle_source);
			secondHolder.secondStyleArticleImage = (ImageView) convertView
					.findViewById(R.id.secondStyleArticle_image);
			secondHolder.secondStyleArticleTitle = (TextView) convertView
					.findViewById(R.id.secondStyleArticle_title);
			convertView.setTag(secondHolder);
			// } else {
			// secondHolder = (SecondHolder) convertView.getTag();
			// }

			secondHolder.secondStyleArticleLayout
					.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							Intent myIntent = new Intent(v.getContext(),
									NovostiCela.class);
							myIntent.putExtra("article", article);
							((Activity)context).startActivityForResult(myIntent, 0);
						}
					});
			
			// ovde treba da stvarno ubacuje iz artikala
			// secondHolder.secondStyleArticleTitle.setText(article.getName());
			// secondHolder.secondStyleArticleShortText.setText(article.getShortText());

			secondHolder.secondStyleArticleTitle.setText(Html.fromHtml(article.getTitle()));
			secondHolder.secondStyleArticleShortText.setText(android.text.format.DateFormat.format("dd.MM.yyyy hh:mm", article.getDate())+"h");
			if(article.getView() == null)
				secondHolder.secondStyleArticleImage.setImageResource(R.drawable.spinner);
			article.setView(secondHolder.secondStyleArticleImage);
			article.generateSmallPhoto();
//			secondHolder.secondStyleArticleImage
//					.setBackgroundResource(R.drawable.icon);

		}

		else {
			ThirdHolder thirdHolder = null;
			// if (convertView == null) {
			convertView = inflater.inflate(R.layout.third_article_style, null);
			thirdHolder = new ThirdHolder();
			thirdHolder.articleTitle = (TextView) convertView
					.findViewById(R.id.thirdStyleArticle_title);
			thirdHolder.thirdHolderLayout = (LinearLayout) convertView
			.findViewById(R.id.thirdStyleArticle);
			convertView.setTag(thirdHolder);
			// } else {
			// thirdHolder = (ThirdHolder) convertView.getTag();
			// }
			// i ovde naravno ovo uzima al da bi mi bilo lakse necu to sad :D
			// Article article = articles.get(position);
			// thirdHolder.articleTitle.setText(article.getName());
			thirdHolder.articleTitle.setText(Html.fromHtml(article.getTitle()));
			thirdHolder.thirdHolderLayout
					.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							Intent myIntent = new Intent(v.getContext(),
									NovostiCela.class);
							myIntent.putExtra("article", article);
							((Activity)context).startActivityForResult(myIntent, 0);
						}
					});
		}

		return convertView;
	}
	/**
	 * Method for downloading and resizing image from url
	 * @param url
	 * @return
	 */
	private Drawable getResizedDrawable(String url) {
		InputStream is = null;
		url = url.replaceAll(" ", "%20");
		if (url != null) {
		try {
			is = new URL(url).openStream();
			Bitmap bitmap = BitmapFactory.decodeStream(is);
			return new BitmapDrawable(bitmap);
		} catch (Exception e) {
			// TODO: handle exception
		}	
		} else {
			return new BitmapDrawable(BitmapFactory.decodeResource(
					context.getResources(), R.drawable.no_image));
		}
		return null;
	}

	// private Drawable imageOperations(String url) {
	// try {
	// InputStream is = (InputStream) this.fetch(url);
	// Drawable d = Drawable.createFromStream(is, "image");
	// int width = d.getIntrinsicWidth();
	// int height = d.getIntrinsicHeight();
	// int screenWidth = activity.getWindowManager().getDefaultDisplay()
	// .getWidth();
	// int screenHeight = activity.getWindowManager().getDefaultDisplay()
	// .getHeight();
	// double ratio = ((double) screenWidth) / width;
	// if (ratio < ((double) screenHeight - 100) / height) {
	// ratio = ((double) screenHeight - 100) / height;
	// }
	// d.setBounds(0, 0, );
	// return d;
	// } catch (MalformedURLException e) {
	// e.printStackTrace();
	// return null;
	// } catch (IOException e) {
	// e.printStackTrace();
	// return null;
	// }
	// }
	//
	// public Object fetch(String address) throws MalformedURLException,
	// IOException {
	// URL url = new URL(address);
	// Object content = url.getContent();
	// return content;
	// }

	private class FirstHolder {
		LinearLayout articleLayout;
		TextView articleTitle;

	}

	private class SecondHolder {
		ImageView secondStyleArticleImage;
		TextView secondStyleArticleTitle;
		TextView secondStyleArticleShortText;
		LinearLayout secondStyleArticleLayout;

	}

	private class ThirdHolder {
		LinearLayout thirdHolderLayout;
		TextView articleTitle;
	}

	public void clear(){
		for(Article article:articles)
			article.clear();
	}

	public void refresh() {
//		Main.getInstance().refreshCategory(categoryName);
//		articles = Main.getInstance().getCategories().get(categoryName).getArticles();
//		notifyDataSetChanged();
		new RefreshTask().execute();
	}
	/**
	 * AsyncTask for refreshing images
	 * @author churava
	 *
	 */
	private class RefreshTask extends AsyncTask<Void, Void, Void>{
		ProgressDialog progressDialog;
		@Override
		protected Void doInBackground(Void... params) {
			Main.getInstance().refreshCategory(categoryName);
			return null;
		}
		
		@Override
		protected void onPreExecute() {
			progressDialog = ProgressDialog.show(context, "", "Molimo sačekajte");
			super.onPreExecute();
		}
		
		@Override
		protected void onPostExecute(Void result) {
			articles = Main.getInstance().getCategories().get(categoryName).getArticles();
			notifyDataSetChanged();
			progressDialog.dismiss();
			super.onPostExecute(result);
		}
		
	}
}
