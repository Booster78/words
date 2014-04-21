import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import fr.boost.util.MotsUtil;


public class FileGenerator {
	
	public static List<String> fileMots = loadFile();
	public static void main(String[] args) {
		
		BufferedWriter writer = null;
				
		try{
			writer =  new BufferedWriter(new FileWriter("parties-mots.txt"));
			int i = 1;
			int nbResDemandes = 5;
			while(i <= nbResDemandes){
				String[] randomChar = MotsUtil.generateConsAndVoyRandomChar(4, 2);//generateRandomChar();
				String res = null;
		        String randomString = "";
		        for(String c : randomChar){
		        	randomString += c;
		        }
		        
		        String randomStringLetters = MotsUtil.countByLetters(randomString);
		        res = getMotsFromRandomStringLetters(randomStringLetters);
		        
				if(res != null){
					System.out.println("Pogressions =" + i + "/"+nbResDemandes);
					System.out.println("Entrée = " + randomStringLetters);
					System.out.println("Résultat = " + res);
					writer.write(randomStringLetters + "-" + res + "\n");
					i++;
				} else {
					//System.out.println("0 résultat pour : " + randomString);
				}
				
			}
		} catch(Exception e){
			if(writer != null){
				try{
					writer.close();
				} catch(Exception e1){
					e1.printStackTrace();
				}
			}
		}finally{
			if(writer != null){
				try{
					writer.close();
				} catch(Exception e1){
					e1.printStackTrace();
				}
			}
		}
	}

	
	private static String getMotsFromRandomStringLetters(String randomStringLetters){
		
		List<String> res = new ArrayList<String>();
        List<String> allRandomizedPossibilities = MotsUtil.extractAllRandomizedPossibilities(randomStringLetters);
	
		for(String mot : fileMots){
			
		    if(mot.length() <= 8 && !mot.contains("-")){
		    	 mot = MotsUtil.replaceChar(mot);
		    	 String c = MotsUtil.countByLetters(mot);
		    	 
		    	 if(c.length() > 4 && allRandomizedPossibilities.contains(c) && !res.contains(mot)){
		    		 res.add(mot);
		    	 }
		    	
		    	 
		    }
		   
		}
		int nbResult = res.size();
		if(nbResult > 8){
			
			int maxLength = 0;
					
			for(String r : res){
				if(r.length() > maxLength){
					maxLength = r.length();
				}
			}
			if(maxLength >= 6){
				return buildResult(res);
			}
			
		
		}

		
		return null;
		
	}
	
	
	private static List<String> loadFile(){
		
		System.out.println("Chargement fichier...");
		String filePath = "liste.de.mots.francais.frgut.txt";
		List<String> mots = new ArrayList<String>();
		Scanner scanner = null;
		
		try {
			scanner = new Scanner(new File(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while (scanner.hasNextLine()) {
			mots.add(scanner.nextLine());
		}
		
		System.out.println("... Fin Chargement fichier");
		
		return mots;
		
	}
	private static String buildResult(List<String> res){
		
		String r = "";
		Iterator i = res.iterator();
		while(i.hasNext()){
			r += i.next();
			if(i.hasNext()){
				r+=",";
			}
			
		}
		return r;
		
	}
}
