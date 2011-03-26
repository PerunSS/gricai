package com.cerSprikRu.QuizTemplate.db.customManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		adapter.openDataBase();
		Quiz quiz = new Quiz();
		
		Cursor q = adapter.executeSql("select * from Question", null);
		if (q != null) {
			if (q.moveToFirst()) {
				do {
					String question = q.getString(q.getColumnIndex("question")).trim();
					int id = q.getInt(q.getColumnIndex("ID"));
//					try{
//						fav = c.getInt(c.getColumnIndex("fav"))==1?true:false;
//					}catch (Exception e) {
//						fav = false;
//					}
					Cursor a = adapter.executeSql("select * from Answers where ID="+id, null);
					Map<String, Boolean> answers;
					if (a.moveToFirst()){
						answers = new HashMap<String, Boolean>();
						boolean validity = false;
						do {
							String answer = a.getString(a.getColumnIndex("answer")).trim();
							validity = a.getInt(a.getColumnIndex("validity"))==1?true:false;
							answers.put(answer, new Boolean(validity));
						} while (a.moveToNext());
						a.close();
						quiz.addQuestion(new Question(question, answers));
					}
				} while (q.moveToNext());
			}
		}
		q.close();
		adapter.close();
		return quiz;
	}
	
}
