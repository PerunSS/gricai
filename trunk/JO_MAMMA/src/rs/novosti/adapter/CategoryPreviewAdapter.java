package rs.novosti.adapter;

import java.util.List;

import rs.novosti.MyGallery;
import rs.novosti.NovostiCela;
import rs.novosti.R;
import rs.novosti.model.Article;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CategoryPreviewAdapter extends BaseAdapter {
	
	private int first=1;
	private int second=4;

	private LayoutInflater inflater;
	private List<Article> articles;
	private Context context;
	private List<Article> sliderArticles;
	private LatestNewsGalleryAdapter latestNewsGalleryAdapter;
	
	public CategoryPreviewAdapter(Context context, List<Article> articles, List<Article> sliderArticles) {
		this.context = context;
		inflater = LayoutInflater.from(context);
		this.articles = articles;
		this.sliderArticles = sliderArticles;
	}

	@Override
	public int getCount() {
		return 1+articles.size();
	}

	@Override
	public Object getItem(int position) {
		if (position == 0 ){
			return sliderArticles;
		} else {
			return articles.get(position);
		}
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		
		if(position < first)	{
			GalleryHolder holder = null;
				convertView = inflater.inflate(R.layout.gallery, null);
				holder = new GalleryHolder();
				holder.gallery = (MyGallery)convertView
						.findViewById(R.id.latestNewsGallery);
				convertView.setTag(holder);
				if(latestNewsGalleryAdapter == null){
					latestNewsGalleryAdapter = new LatestNewsGalleryAdapter(context, sliderArticles);
				}
				holder.gallery.setAdapter(latestNewsGalleryAdapter);
				holder.gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, 
			                View v, int position, long id) {
						Intent myIntent = new Intent(v.getContext(),
								NovostiCela.class);
						Article article = sliderArticles.get(position);
						myIntent.putExtra("article", article);
						((Activity)context).startActivityForResult(myIntent, 0);
					}
				});
		} else if (position<second){
			final Article article = articles.get(position-1);
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
			final Article article = articles.get(position-1);
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
	private class GalleryHolder{
		MyGallery gallery;
	}
	
	public void clear(){
		for(Article cat:articles)
			cat.clear();
		latestNewsGalleryAdapter.clearAll();
	}

}
