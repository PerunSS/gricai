package rs.novosti.randomGenerator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rs.novosti.model.Article;
import rs.novosti.model.Category;
import rs.novosti.model.Main;

public class Generator {

	private static String loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
			"Praesent posuere velit id neque suscipit sagittis. Duis eu ante leo. " +
			"Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. " +
			"Etiam convallis metus a augue iaculis quis bibendum neque molestie. Suspendisse potenti. " +
			"Vivamus orci arcu, blandit in pharetra ac, rutrum a odio. Fusce arcu lacus, ornare ac lacinia " +
			"sollicitudin, consectetur sed tortor. Praesent tempus augue vel ligula pharetra vitae venenatis " +
			"est bibendum. Suspendisse potenti. Ut congue ultrices neque eu molestie. " +
			"Nulla ut magna eget justo sodales dictum vel quis nibh. Proin ipsum urna, " +
			"dignissim in blandit non, feugiat eget elit. Vestibulum ante ipsum primis in faucibus orci " +
			"luctus et ultrices posuere cubilia Curae; In vitae sem nisl. Morbi eleifend gravida libero " +
			"luctus adipiscing. Suspendisse sed leo vel ligula tempor condimentum. Suspendisse potenti. " +
			"Cras quis pretium libero. Nam molestie felis a sem porta convallis. " +
			"Nam vel libero posuere mi blandit sagittis. \n\nPraesent sodales malesuada nisl at auctor. " +
			"Proin euismod, leo eu adipiscing mattis, lorem lectus placerat tellus, sit amet sagittis nulla purus et neque. " +
			"Quisque congue imperdiet vehicula. Ut porttitor justo in diam vestibulum egestas vel at arcu. " +
			"Donec fringilla, enim non vehicula varius, neque erat gravida risus, id scelerisque dui orci at risus. " +
			"ed nec lacus id nibh malesuada laoreet at vel nunc. Duis at magna ut leo tempus varius eget id nunc. " +
			"Nunc est elit, tempor at interdum nec, dictum eget mauris. Ut eget nisl lacus. Donec posuere justo sit amet " +
			"felis vestibulum vehicula. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. " +
			"Curabitur turpis risus, aliquam eget iaculis non, vulputate quis tortor. Aenean at justo felis. " +
			"Duis eget sapien eu lacus ornare adipiscing. Cras urna justo, tempus ut egestas ac, feugiat " +
			"a massa. Ut aliquam elit in nisi cursus non aliquam dolor dictum. Pellentesque sodales tortor eu " +
			"nisl placerat dictum.";
	
	public static Main generate(){
		Main main = Main.getInstance();
		List<Category> categories = new ArrayList<Category>();
		for(int i=0;i<10;i++){
			Category cat = new Category();
			cat.setTitle("category "+i);
			List<Article> articles = new ArrayList<Article>();
			for(int j=0;j<30;j++){
				Article article = new Article();
				article.setTitle("article "+j+", category "+i);
				article.setText(loremIpsum);
				article.setShortText(loremIpsum.substring(0, 100)+"...");
				article.setDate(new Date());
				article.setSource("cerspri kru");
				article.setPhotoPath("testis");
				article.setOneLine(article.getTitle()+": "+article.getShortText().substring(0,20)+"...");
				articles.add(article);
			}
			cat.setArticles(articles);
			categories.add(cat);
		}
		main.setCategories(categories);
		return main;
	}
}
