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
import fr.boost.util.MotsUtil;

public class MotsActivity extends Activity {
    /** Called when the activity is first created. */
	
	private static List<String> fichierMot = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AssetManager mgr = getAssets();    
        
        if(fichierMot == null){
        	fichierMot = loadListeMots(mgr);
        }
        setContentView(R.layout.main);
        
        String[] randomChar = MotsUtil.generateRandomChar();
        
        TextView txt1 = ((TextView) findViewById(R.id.lettre1));
        TextView txt2 = ((TextView) findViewById(R.id.lettre2));
        TextView txt3 = ((TextView) findViewById(R.id.lettre3));
        TextView txt4 = ((TextView) findViewById(R.id.lettre4));
        TextView txt5 = ((TextView) findViewById(R.id.lettre5));
        TextView txt6 = ((TextView) findViewById(R.id.lettre6));
        
        txt1.setText(randomChar[0]);
        txt2.setText(randomChar[1]);
        txt3.setText(randomChar[2]);
        txt4.setText(randomChar[3]);
        txt5.setText(randomChar[4]);
        txt6.setText(randomChar[5]);
        
        MyCount counter = new MyCount(60000,1000,  ((TextView) findViewById(R.id.counter)));
        counter.start();
        
        
        
        String randomString = "";
        for(String c : randomChar){
        	randomString += c;
        }
        List<String> allRandomizedPossibilities = MotsUtil.extractAllRandomizedPossibilities(randomString);
        
        List<String> motsPossibles = new ArrayList<String>();
        for(String mot : fichierMot){
	        if(mot.length() <= 8 && !mot.contains("-")){
		    	 mot = MotsUtil.replaceChar(mot);
		    	 String c = MotsUtil.countByLetters(mot);
		    	 
		    	 if(c.length() > 4 && allRandomizedPossibilities.contains(c) && !motsPossibles.contains(mot)){
		    		 motsPossibles.add(mot);
		    		 System.out.println(mot);
		    	 }
	        }
        }
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
   
    private List<String> loadListeMots(AssetManager mgr){
    	
    	System.out.println("Loading file...");
    	
    	String fileName = "liste.de.mots.francais.frgut.txt";
    	
        
    	List<String> mots = new ArrayList<String>();
    	InputStream is = null;
		try{
			is = mgr.open(fileName);
			Scanner scanner = new Scanner(is);
	       
    	// On boucle sur chaque champ detecté
			while (scanner.hasNextLine()) {
				System.out.println(scanner.nextLine());
				//mots.add(scanner.nextLine());
			}
			
		} catch(IOException e){
			e.printStackTrace();
		}
		finally {
			try{
				if(is != null){
					is.close();
				}
			}catch(Exception e){
			}
			
		}
		
		System.out.println("Fin de chargement...");
		
		return mots;
    }
    
}