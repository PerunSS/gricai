package com.cerSprikRu.grind.FatInsults.db;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter extends SQLiteOpenHelper {
	// The Android's default system path of your application database.
	private String dbPath; // =
							// "/data/data/com.android.projectsforlearning.factstemplate/databases/";
	private String dbName; // = "RNBFacts";
	private SQLiteDatabase myDataBase;
	private final Context myContext;
	private static final int CURRENT_VERSION = 1;

	/**
	 * Constructor Takes and keeps a reference of the passed context in order to
	 * access to the application assets and resources.
	 * 
	 * @param context
	 */
	public DBAdapter(Context context, String dbName, String projectPath) {
		super(context, dbName, null, CURRENT_VERSION);
		this.dbName = dbName;
		dbPath = "/data/data/" + projectPath + "/databases/";
		this.myContext = context;
		try {
			createDataBase();
		} catch (IOException e) {
			Log.d("CREATE ERROR", e.getMessage(), e);
		}
	}

	/**
	 * Creates a empty database on the system and rewrites it with your own
	 * database.
	 * */
	public void createDataBase() throws IOException {
		boolean dbExist = checkDataBase();
		if (dbExist) {
			System.out.println(getDbVersion());
			if (getDbVersion() != CURRENT_VERSION) {
				this.getReadableDatabase();
				try {
					copyDataBase();
				} catch (IOException e) {
					Log.d("COPY ERROR", e.getMessage(), e);
					throw new Error("Error copying database");
				}
				
			}
			// do nothing - database already exist
		} else {
			// By calling this method and empty database will be created into
			// the default system path
			// of your application so we are gonna be able to overwrite that
			// database with our database.
			this.getReadableDatabase();
			try {
				copyDataBase();
			} catch (IOException e) {
				Log.d("COPY ERROR", e.getMessage(), e);
				throw new Error("Error copying database");
			}
		}
	}

	private void setNewVersion(int currentVersion) {
		SQLiteDatabase checkDB = null;
		try {
			String myPath = dbPath + dbName;
			checkDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READWRITE);
			checkDB.setVersion(CURRENT_VERSION);
			checkDB.close();
		} catch (SQLiteException e) {
			// database does't exist yet.
		}
		
	}

	/**
	 * Check if the database already exist to avoid re-copying the file each
	 * time you open the application.
	 * 
	 * @return true if it exists, false if it doesn't
	 */
	private boolean checkDataBase() {
		SQLiteDatabase checkDB = null;
		try {
			String myPath = dbPath + dbName;
			checkDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READONLY);
		} catch (SQLiteException e) {
			// database does't exist yet.
		}
		if (checkDB != null) {
			checkDB.close();
		}
		return checkDB != null ? true : false;
	}

	private int getDbVersion() {
		SQLiteDatabase checkDB = null;
		int version = -1;
		try {
			String myPath = dbPath + dbName;
			checkDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READONLY);
			version = checkDB.getVersion();
			checkDB.close();
		} catch (SQLiteException e) {
			// database does't exist yet.
		}
		return version;
	}

	/**
	 * Copies your database from your local assets-folder to the just created
	 * empty database in the system folder, from where it can be accessed and
	 * handled. This is done by transfering bytestream.
	 * */
	private void copyDataBase() throws IOException {
		// Open your local db as the input stream
		InputStream myInput = myContext.getAssets().open(dbName);
		// Path to the just created empty db
		String outFileName = dbPath + dbName;
		// Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);
		// transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}
		// Close the streams
		Log.d("COPIED TO PATH:", outFileName);
		myOutput.flush();
		myOutput.close();
		myInput.close();
		
		setNewVersion(CURRENT_VERSION);
	}

	public void openDataBase() throws SQLException {
		// Open the database
		String myPath = dbPath + dbName;
		Log.d("PATH", myPath);
		myDataBase = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READWRITE);
	}

	@Override
	public synchronized void close() {
		if (myDataBase != null)
			myDataBase.close();
		super.close();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		try {
			copyDataBase();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Cursor executeSql(String sql, String[] selectionArgs) {
		return myDataBase.rawQuery(sql, selectionArgs);
	}

	public void update(String sql) {
		myDataBase.execSQL(sql);
	}
}
