package rs.novosti.adapter;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import rs.novosti.NovostiCela;
import rs.novosti.R;
import rs.novosti.model.Article;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CategoryLayoutAdapter extends BaseAdapter {

	
	private int firstPart = 1;
	private int secondPart = 4;
	
	
	private LayoutInflater inflater;
	private List<Article> articles;
	private Activity activity;

	public CategoryLayoutAdapter(Context context, List<Article> articles, Activity activity) {
		inflater = LayoutInflater.from(context);
		this.articles = articles;
		this.activity=activity;
		
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		final Article article = articles.get(position);
		if (position < firstPart){
			FirstHolder firstHolder = null;
//			if (convertView == null) {
				convertView = inflater.inflate(R.layout.first_article_style, null);
				firstHolder = new FirstHolder();
				firstHolder.articleLayout = (LinearLayout) convertView
						.findViewById(R.id.firstStyleArticle);
				
				firstHolder.articleLayout.setBackgroundDrawable(imageOperations(article.getPhotoPath()));
				firstHolder.articleTitle = (TextView) convertView
						.findViewById(R.id.firstStyleArticle_title);
				convertView.setTag(firstHolder);
//			} else {
//				firstHolder = (FirstHolder) convertView.getTag();
//			}
			
			// ovde bi trebalo da stvarno vadi iz artikala sa tim sto sliku nisam napravio
			// posto ne znam kako od urla
//			Article article = articles.get(position);
//			firstHolder.articleTitle.setText(article.getName());
//			firstHolder.articleImage;
			
			firstHolder.articleTitle.setText(article.getName());
			firstHolder.articleTitle.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent myIntent = new Intent(v.getContext(),
							NovostiCela.class);
					myIntent.putExtra("article", article);
					activity.startActivityForResult(myIntent, 0);
				}
			});
			//firstHolder.articleLayout.setBackgroundResource(R.drawable.b1);
			
		} 		
		//ovde ubacuje ako je drugog tipa artikl ( slicica ime mali tekst)
		 else if (position < secondPart) {
			SecondHolder secondHolder;
//			if (convertView == null) {
				convertView = inflater.inflate(R.layout.second_article_style, null);
				secondHolder = new SecondHolder();
				secondHolder.secondStyleArticleLayout = (RelativeLayout)convertView.findViewById(R.id.secondStyleArticle_layout);
				secondHolder.secondStyleArticleShortText = (TextView) convertView
						.findViewById(R.id.secondStyleArticle_shortText);
				secondHolder.secondStyleArticleImage = (ImageView) convertView
						.findViewById(R.id.secondStyleArticle_image);
				secondHolder.secondStyleArticleTitle = (TextView) convertView
						.findViewById(R.id.secondStyleArticle_title);
				convertView.setTag(secondHolder);
//			} else {
//				secondHolder = (SecondHolder) convertView.getTag();
//			}
			
			secondHolder.secondStyleArticleLayout.setOnClickListener(new View.OnClickListener() {
					
				@Override
				public void onClick(View v) {
					Intent myIntent = new Intent(v.getContext(),
							NovostiCela.class);
					myIntent.putExtra("article", article);
					activity.startActivityForResult(myIntent, 0);
				}
			});
				
			//ovde treba da stvarno ubacuje iz artikala
//			secondHolder.secondStyleArticleTitle.setText(article.getName());
//			secondHolder.secondStyleArticleShortText.setText(article.getShortText());
				
			secondHolder.secondStyleArticleTitle.setText(article.getName());
			secondHolder.secondStyleArticleShortText.setText(article.getShortText());
				
			secondHolder.secondStyleArticleImage.setBackgroundResource(R.drawable.icon);
			
		 }
			
			else {
				ThirdHolder thirdHolder = null;
//				if (convertView == null) {
					convertView = inflater.inflate(R.layout.third_article_style, null);
					thirdHolder = new ThirdHolder();
					thirdHolder.articleTitle = (TextView) convertView
							.findViewById(R.id.thirdStyleArticle_title);
					convertView.setTag(thirdHolder);
//				} else {
//					thirdHolder = (ThirdHolder) convertView.getTag();
//				}
				// i ovde naravno ovo uzima al da bi mi bilo lakse necu to sad :D
//				Article article = articles.get(position);
//				thirdHolder.articleTitle.setText(article.getName());
				thirdHolder.articleTitle.setText(article.getOneLine());
				thirdHolder.articleTitle.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Intent myIntent = new Intent(v.getContext(),
								NovostiCela.class);
						myIntent.putExtra("article", article);
						activity.startActivityForResult(myIntent, 0);
					}
				});
		}
		
		
			return convertView;
		}
	
	
	private Drawable imageOperations(String url) {
		try {
			InputStream is = (InputStream) this.fetch(url);
			Drawable d = Drawable.createFromStream(is, "image");
			return d;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Object fetch(String address) throws MalformedURLException,IOException {
		URL url = new URL(address);
		Object content = url.getContent();
		return content;
	}
	
	private class FirstHolder {
		LinearLayout articleLayout;
		TextView articleTitle;
		
	}
	
	private class SecondHolder {
		ImageView secondStyleArticleImage;
		TextView secondStyleArticleTitle;
		TextView secondStyleArticleShortText;
		RelativeLayout secondStyleArticleLayout;
		
	}
	
	private class ThirdHolder {
		TextView articleTitle;
	}
	
}
