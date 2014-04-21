package fr.boost.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import fr.boost.dao.MotsEntityContract.MotsEntity;
import fr.boost.entity.Mot;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	 // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "motsManager.db";
     
    private static final String SQL_DELETE_TABLE =
    	    "DROP TABLE IF EXISTS " + MotsEntity.TABLE_NAME;
 
    private static final String SQL_TRUNCATE_TABLE =
    	    "DELETE FROM " + MotsEntity.TABLE_NAME;
    
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
    	// Drop older table if existed
        db.execSQL(SQL_DELETE_TABLE);
    	
        String CREATE_MOTS_TABLE = "CREATE TABLE " + MotsEntity.TABLE_NAME + "("
                + MotsEntity.COLUMN_NAME_MOTS_ID + " STRING PRIMARY KEY," + MotsEntity.COLUMN_NAME_MOTS_VALUES + " TEXT)";
        db.execSQL(CREATE_MOTS_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        
    	// Drop older table if existed
        db.execSQL(SQL_DELETE_TABLE);
        // Create tables again
        onCreate(db);
    }
    
    public void deleteTable(){
    	SQLiteDatabase db = this.getWritableDatabase();
    	db.execSQL(SQL_DELETE_TABLE);
    }
    
    
 // Adding new entry mot
    public void addMotValues(Mot mot) {
    	
    	   SQLiteDatabase db = this.getWritableDatabase();
    	    ContentValues values = new ContentValues();
    	    values.put(MotsEntity.COLUMN_NAME_MOTS_ID, mot.getId());
    	    values.put(MotsEntity.COLUMN_NAME_MOTS_VALUES, mot.getValues()); 
    	 
    	    // Inserting Row
    	    db.insert(MotsEntity.TABLE_NAME, null, values);
    	    db.close(); // Closing database connection
    	    
    }
    
    public void initListMots(List<Mot> mots) {
    	
 	   SQLiteDatabase db = this.getWritableDatabase();
 	   
 	  db.execSQL(SQL_TRUNCATE_TABLE);
 	  
 	    for(Mot mot : mots){
 	    	ContentValues values = new ContentValues();
 	 	    values.put(MotsEntity.COLUMN_NAME_MOTS_ID, mot.getId());
    	    values.put(MotsEntity.COLUMN_NAME_MOTS_VALUES, mot.getValues()); 
    	    db.insert(MotsEntity.TABLE_NAME, null, values);
 	    }
 	    db.close(); // Closing database connection
 	    
 }
     
    // Getting single contact
    public Mot getMot(String key) {
    	
    	SQLiteDatabase db = this.getReadableDatabase();
    	 
        Cursor cursor = db.query(MotsEntity.TABLE_NAME, new String[] { MotsEntity.COLUMN_NAME_MOTS_ID,
        		MotsEntity.COLUMN_NAME_MOTS_VALUES },  MotsEntity.COLUMN_NAME_MOTS_ID + "=?",
                new String[] { String.valueOf(key) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
     
        Mot mot = new Mot();
        mot.setId(cursor.getString(0));
        mot.setValues(cursor.getString(1));
        	// return mot
        return mot;
    	
    }
    
    public Mot getRandomMot() {
    	//SELECT * FROM table ORDER BY RANDOM() LIMIT 1;
    	
    	SQLiteDatabase db = this.getReadableDatabase();
   	 
        Cursor cursor = db.query(MotsEntity.TABLE_NAME, new String[] { MotsEntity.COLUMN_NAME_MOTS_ID,
        		MotsEntity.COLUMN_NAME_MOTS_VALUES }, null,
                null, null, null, "RANDOM()", "1");
        
        Mot mot = new Mot();
        if (cursor != null && cursor.moveToFirst()){
        	mot.setId(cursor.getString(0));
        	mot.setValues(cursor.getString(1));
        }
        	// return mot
        return mot;
        
    }
    // Getting single contact
    public List<Mot> getMots() {
    	SQLiteDatabase db = this.getReadableDatabase();
    	List<Mot> mots = new ArrayList<Mot>();
    	Cursor cursor = db.query(MotsEntity.TABLE_NAME, new String[] { MotsEntity.COLUMN_NAME_MOTS_ID,
        		MotsEntity.COLUMN_NAME_MOTS_VALUES }, null,
               null, null, null, null, null);
    	   if (cursor != null){
    		   while (cursor.moveToNext()) {
    			   Mot mot = new Mot();
    		        mot.setId(cursor.getString(0));
    		        mot.setValues(cursor.getString(1));
    		        mots.add(mot);
    		   }
    	   }
    	   
    	   return mots;
    }
	
}