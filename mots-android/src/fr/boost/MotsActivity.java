package fr.boost;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import fr.boost.dao.DatabaseHelper;
import fr.boost.entity.Mot;
import fr.boost.util.AndroidMotsUtil;

public class MotsActivity extends Activity {
    /** Called when the activity is first created. */
	
	private static List<String> fichierMot = null;
	private static List<TextView> lettresSaisies =  new ArrayList<TextView>();
	private DatabaseHelper mDbHelper = null;
	private static Mot currentMot = null;
	private static List<String> motsTrouves = new ArrayList<String>();
	private static List<String> motsPossibles = new ArrayList<String>();
	
	 
	private TextView txt1 = null;
	private TextView txt2 = null;
	private TextView txt3 = null;
	private TextView txt4 = null;
	private TextView txt5 = null;
	private TextView txt6 = null;
	private TextView txt7 = null;
	private TextView txt8 = null;
	private TextView txt9 = null;
	private List<TextView> buttonTxt = new ArrayList<TextView>();
    
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
        
        txt1 = ((TextView) findViewById(R.id.lettre1));
        txt2 = ((TextView) findViewById(R.id.lettre2));
        txt3 = ((TextView) findViewById(R.id.lettre3));
        txt4 = ((TextView) findViewById(R.id.lettre4));
        txt5 = ((TextView) findViewById(R.id.lettre5));
        txt6 = ((TextView) findViewById(R.id.lettre6));
        txt7 = ((TextView) findViewById(R.id.lettre7));
        txt8 = ((TextView) findViewById(R.id.lettre8));
        txt9 = ((TextView) findViewById(R.id.lettre9));
        
        buttonTxt.add(txt1);
        buttonTxt.add(txt2);
        buttonTxt.add(txt3);
        buttonTxt.add(txt4);
        buttonTxt.add(txt5);
        buttonTxt.add(txt6);
        buttonTxt.add(txt7);
        buttonTxt.add(txt8);
        buttonTxt.add(txt9);
       
        
        MyCount counter = new MyCount(60000,1000,  ((TextView) findViewById(R.id.counter)));
        counter.start();
        
        
        for(Mot m : mDbHelper.getMots()){
        	System.out.println(m.getId() + " - " +  m.getValues());
        }
        
        currentMot = mDbHelper.getRandomMot();
        motsPossibles = new ArrayList<String>(Arrays.asList(currentMot.getValues().split(",")));
        
        System.out.println("LETTRES : " + currentMot.getId() );
        System.out.println("MOTS A TROUVER : " + currentMot.getValues() );
        
        List<String> lettres = AndroidMotsUtil.getLettersFromMotId(currentMot.getId());
        
        for(int i = 0; i< buttonTxt.size(); i++){
        	buttonTxt.get(i).setText(lettres.get(i));
        }
        
    }
    
  
    public void onClick(View v) {
    	TextView currentViewClick = (TextView)v;
    	String lettreSaisie = currentViewClick.getText().toString();
    	if(!lettresSaisies.contains(lettreSaisie)){
	    	currentViewClick.setTextColor(Color.WHITE);
	    	EditText txt = (EditText)findViewById(R.id.entry);
	    	txt.append(lettreSaisie);
	    	lettresSaisies.add(currentViewClick);
    	}
    } 
    
    public void valid(View b) {
    	EditText txt = (EditText)findViewById(R.id.entry);
    	TableLayout tl=(TableLayout)findViewById(R.id.tableTrouve);  
    	String txtUser = txt.getText().toString().trim();
    	if(motsPossibles.contains(txtUser) && !motsTrouves.contains(txtUser)){
    		TextView txtView = new TextView(this);
    		txtView.setText(new StringBuilder(txtUser));
    		txtView.setTextColor(Color.GREEN);
    		tl.addView(txtView);
    		motsTrouves.add(txtUser);
    		txt.setText("");
    	} else {
    		txt.setText("");
    	}
    	
    	resetButtons();
    	//EditText txt = (EditText)findViewById(R.id.entry);
    	//txt.setText("");
    } 
    public void clear(View b) {
    	resetButtons();
    }    
   
    private void resetButtons(){
    	EditText txt = (EditText)findViewById(R.id.entry);
    	txt.setText("");
    	lettresSaisies =  new ArrayList<TextView>();
    	 for(int i = 0; i< buttonTxt.size(); i++){
         	buttonTxt.get(i).setTextColor(Color.BLACK);
         }
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