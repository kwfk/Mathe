package bomanthis.mathe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class History extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "history.db";
    public static final String TABLE_NAME = "history_table";
    public static final String COL1 = "DATE";
    public static final String COL2 = "LESSON";
    public static final String COL3 = "PROBLEMS_SOLVED";
    public static final String COL4 = "RESULT";


    //constructor
    public History(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    //creates database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,LESSON TEXT,PROBLEMS_SOLVED TEXT,RESULT TEXT);");
    }

    //upgrade not used
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    //inserts data given all the parameters
    public boolean insertData(String date, String lesson, String problem_solved, String score){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, date);
        contentValues.put(COL2, lesson);
        contentValues.put(COL3, problem_solved);
        contentValues.put(COL4, score);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    //retrieves and returns all data in database
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME, null);
        return res;
    }

    //clears all of database
    public Integer clear(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, null, null);
    }
}
