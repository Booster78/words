package fr.boost;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import fr.boost.dao.DatabaseHelper;
import fr.boost.entity.Mot;
import fr.boost.util.MotsUtil;

public class MotsActivity extends Activity {
    /** Called when the activity is first created. */
	
	private static List<String> fichierMot = null;
	private DatabaseHelper mDbHelper = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDbHelper = new DatabaseHelper(this);
        
        AssetManager mgr = getAssets();    
        
        initDB(mgr);
        
        if(fichierMot == null){
        	//fichierMot = loadListeMots(mgr);
        }
        setContentView(R.layout.main);
        
        String[] randomChar = MotsUtil.generateConsAndVoyRandomChar(4, 2);
        
        TextView txt1 = ((TextView) findViewById(R.id.lettre1));
        TextView txt2 = ((TextView) findViewById(R.id.lettre2));
        TextView txt3 = ((TextView) findViewById(R.id.lettre3));
        TextView txt4 = ((TextView) findViewById(R.id.lettre4));
        TextView txt5 = ((TextView) findViewById(R.id.lettre5));
        TextView txt6 = ((TextView) findViewById(R.id.lettre6));
        
       
        
        MyCount counter = new MyCount(60000,1000,  ((TextView) findViewById(R.id.counter)));
        counter.start();
        
        
        for(Mot m : mDbHelper.getMots()){
        	System.out.println(m.getId() + " - " +  m.getValues());
        }
        
        Mot mot = mDbHelper.getRandomMot();//getMot("1D1E1I1M1S1T");
        System.out.println("LETTRES : " + mot.getId() );
        System.out.println("MOTS A TROUVER : " + mot.getValues() );
        
        List<String> lettres = MotsUtil.getLettersFromMotId(mot.getId());
        
        txt1.setText(lettres.get(0));
        txt2.setText(lettres.get(1));
        txt3.setText(lettres.get(2));
        txt4.setText(lettres.get(3));
        txt5.setText(lettres.get(4));
        txt6.setText(lettres.get(5));
        //List<String> allRandomizedPossibilities = MotsUtil.extractAllRandomizedPossibilities(randomString);
        
       /*List<String> motsPossibles = new ArrayList<String>();
        for(String mot : fichierMot){
	        if(mot.length() <= 8 && !mot.contains("-")){
		    	 mot = MotsUtil.replaceChar(mot);
		    	 String c = MotsUtil.countByLetters(mot);
		    	 
		    	 if(c.length() > 4 && allRandomizedPossibilities.contains(c) && !motsPossibles.contains(mot)){
		    		 motsPossibles.add(mot);
		    		 System.out.println(mot);
		    	 }
	        }
        }*/
    }
    
  
    public void onClick(View v) {
    	EditText txt = (EditText)findViewById(R.id.entry);
    	txt.append(((TextView)v).getText());
    } 
    
    public void valid(View b) {
    	EditText txt = (EditText)findViewById(R.id.entry);
    	txt.setText("");
    } 
    public void clear(View b) {
    	EditText txt = (EditText)findViewById(R.id.entry);
    	txt.setText("");
    }    
   
    private void initDB(AssetManager mgr){
    	
    	System.out.println("Loading file...");
    	
    	String fileName = "parties-mots.txt";
    	
        
    	//List<String> mots = new ArrayList<String>();
    	List<Mot> mots = new ArrayList<Mot>();
    	InputStream is = null;
    	Scanner scanner = null;
		try{
			is = mgr.open(fileName);
			scanner = new Scanner(is);
	       
    	// On boucle sur chaque champ detect�
			while (scanner.hasNextLine()) {
				//System.out.println(scanner.nextLine());
				String[] line = scanner.nextLine().split("-");
				Mot mot = new Mot();
				mot.setId(line[0]);
				mot.setValues(line[1]);
				mots.add(mot);
			}
			
		} catch(IOException e){
			e.printStackTrace();
		}
		finally {
			try{
				if(is != null){
					is.close();
				}
				if(scanner != null){
					scanner.close();
				}
			}catch(Exception e){
			}
			
		}
		
		mDbHelper.initListMots(mots);
		
		System.out.println("Fin de chargement...");
		
		//return mots;
    }
    
    
}