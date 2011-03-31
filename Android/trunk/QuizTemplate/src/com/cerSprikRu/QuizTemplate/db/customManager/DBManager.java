package com.cerSprikRu.QuizTemplate.db.customManager;

import android.content.Context;
import android.database.Cursor;

import com.cerSprikRu.QuizTemplate.db.DBAdapter;
import com.cerSprikRu.QuizTemplate.model.Question;
import com.cerSprikRu.QuizTemplate.model.Quiz;

public class DBManager {

	private static final String dbName = "quizbase";
	private static final String projectPath = "com.cerSprikRu.QuizTemplate";
	DBAdapter adapter;

	public DBManager(Context context) {
		adapter = new DBAdapter(context, dbName, projectPath);
	}

	public Quiz read() {
		System.out.println("otvaram bazu pre vadjenja iz baze");
		adapter.openDataBase();
		Quiz quiz = new Quiz();
		System.out.println("pociunjem sa vadjenjem iz baze mrtve");
		Cursor q = adapter.executeSql("select * from Question", null);
		System.out.println("nesto i izvadio");
		if (q != null) {
			if (q.moveToFirst()) {
				do {
					System.out.println("kao ovde sam ovo ono");
					String question = q.getString(q.getColumnIndex("question")).trim();
					System.out.println("hmmmm kveshcn" + question);
					int id = q.getInt(q.getColumnIndex("_id"));
					Question qu = new Question(question);
					System.out.println("shatro mozda imam question"+question);

					Cursor a = adapter.executeSql("select * from Answers where _id="+id, null);
					System.out.println("e sad ovaj uspeo valjda il ne ?");
					if (a !=null){
						if (a.moveToFirst()){
							System.out.println("znaci uspeo drugi");
							do {
								String answer = a.getString(a.getColumnIndex("answer")).trim();
								System.out.println("i answer je  " + answer);
								boolean validity = a.getInt(a.getColumnIndex("validity"))==1?true:false;
								System.out.println("tacnost je " + validity);
								qu.putAnswer(answer, validity);
								System.out.println("ubacio u kveschn");
							} while (a.moveToNext());
							a.close();
							quiz.addQuestion(qu);
						}	else {
							System.out.println("drugi prazan");
						}
					} else {
						System.out.println("drugi null");
					}
				} while (q.moveToNext());
			} else {
				System.out.println("nece na prvi ?");
			}
		} else {
			System.out.println("prazna baza ?");
		}
		q.close();
		adapter.close();
		return quiz;
	}
	
}
