package com.cerSprikRu.RealQuizTemplate.db.customManager;

import android.content.Context;
import android.database.Cursor;

import com.cerSprikRu.RealQuizTemplate.db.DBAdapter;
import com.cerSprikRu.RealQuizTemplate.model.Question;
import com.cerSprikRu.RealQuizTemplate.model.Quiz;

public class DBManager {

	private static final String dbName = "quizbase";
	private static final String projectPath = "com.cerSprikRu.RealQuizTemplate";
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
					int id = q.getInt(q.getColumnIndex("_id"));
					Question qu = new Question(question);

					Cursor a = adapter.executeSql("select * from Answers where _id="+id, null);
					if (a !=null){
						if (a.moveToFirst()){
							do {
								String answer = a.getString(a.getColumnIndex("answer")).trim();
								boolean validity = a.getInt(a.getColumnIndex("validity"))==1?true:false;
								qu.putAnswer(answer, validity);
							} while (a.moveToNext());
							a.close();
							quiz.addQuestion(qu);
						}	else {
						}
					} else {
					}
				} while (q.moveToNext());
			} else {
			}
		} else {
		}
		q.close();
		adapter.close();
		return quiz;
	}
	
}
