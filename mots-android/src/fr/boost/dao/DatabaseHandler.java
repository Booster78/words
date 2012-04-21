package fr.boost.dao;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import fr.boost.entity.Mot;

public class DatabaseHandler extends SQLiteOpenHelper {
	
	 // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "motsManager";
 
    // Contacts table name
    private static final String TABLE_MOTS = "mots";
 
    // Contacts Table Columns names
    private static final String ID = "key";
    private static final String MOT_VALUE = "mot_value";
 
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_MOTS + "("
                + ID + " STRING PRIMARY KEY," + MOT_VALUE + " TEXT)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOTS);
 
        // Create tables again
        onCreate(db);
    }
    
    
 // Adding new contact
    public void addContact(Mot mot) {
    	
    	   SQLiteDatabase db = this.getWritableDatabase();
    	   
    	    ContentValues values = new ContentValues();
    	    values.put(ID, mot.getId()); // Contact Name
    	    values.put(MOT_VALUE, mot.getMot()); // Contact Phone Number
    	 
    	    // Inserting Row
    	    db.insert(TABLE_MOTS, null, values);
    	    db.close(); // Closing database connection
    	    
    }
    
    public void addListContact(List<Mot> mots) {
    	
 	   SQLiteDatabase db = this.getWritableDatabase();
 	   
 	    ContentValues values = new ContentValues();
 	    for(Mot mot : mots){
 	    	values.put(ID, mot.getId()); // Contact Name
 	    	values.put(MOT_VALUE, mot.getMot()); // Contact Phone Number
 	    }
 	    // Inserting Row
 	    db.insert(TABLE_MOTS, null, values);
 	    db.close(); // Closing database connection
 	    
 }
     
    // Getting single contact
    public Mot getContact(String key) {
    	
    	SQLiteDatabase db = this.getReadableDatabase();
    	 
        Cursor cursor = db.query(TABLE_MOTS, new String[] { ID,
                MOT_VALUE }, ID + "=?",
                new String[] { String.valueOf(key) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
     
        Mot mot = new Mot();
        mot.setId(cursor.getString(0));
        mot.setMot(cursor.getString(1));
        	// return mot
        return mot;
    	
    }
    
	
	
}