import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import fr.boost.util.MotsUtil;


public class FileGenerator {
	
	public static List<String> fileMots = loadFile();
	public static void main(String[] args) {
		
		BufferedWriter writer = null;
				
		try{
			writer =  new BufferedWriter(new FileWriter("parties-mots.txt"));
			int i = 1;
			int nbResDemandes = 50;
	
			System.out.println("loading map");
			 String randomStringLetters = "";
			 Map<String, List<String>> map = new HashMap<String, List<String>>();
			for(String mot : fileMots){
				randomStringLetters = MotsUtil.countByLetters(mot.toUpperCase(),false);
				if(!map.containsKey(randomStringLetters)){
					List<String> mots = new ArrayList<String>();
					mots.add(mot);
					map.put(randomStringLetters,mots);
				} else {
					map.get(randomStringLetters).add(mot);
				}
								
			}
			
			System.out.println("map size " + map.keySet().size());
			
			System.out.println("writing file...");
			
		
			
			while(i <= nbResDemandes){
				String[] randomChar = MotsUtil.generateConsAndVoyRandomChar(4, 2);//generateRandomChar();
		        String generateChars = "";
		        for(String c : randomChar){
		        	generateChars += c;
		        }
		        		        
		        String randomGenerateChars = MotsUtil.countByLetters(generateChars, false);
		        
		        //list 1A 1B 1I générés aléatoirement
		        List<String> generateCharsList =  MotsUtil.converteGenerateCharsToList(randomGenerateChars);
		        List<String> outPutWord = new ArrayList<String>();
		        System.out.println("lettres generees : " + generateCharsList);
		        //On parcours la map des mots français
		        for(String randomStringLettersFound : map.keySet()){
		        	//Calcul des 1B, 1C, 1I... du mot du dictionnaire		        	
		        	List<String> dicoCharsList = MotsUtil.converteGenerateCharsToList(randomStringLettersFound);
		        	//Est-ce que ces 1B, 1C ... matchent avec les 1A, 1B... générés aléatoirement ?
	
		        	//parcours des 1B, 1C... du mot du dictionnaire
		        	Iterator itDico = dicoCharsList.iterator();
		        	boolean letterOK = false;
		        	do{
		        		String dico = (String)itDico.next();
		        		letterOK = false;
		        		//par cours de 1B, 1C... généré
		        		for(String gen : generateCharsList){
		        			if(gen.charAt(1) == (dico.charAt(1)) && Integer.valueOf(dico.substring(0,1)) <= Integer.valueOf(gen.substring(0,1))){
		        				letterOK = true;
			        			break;
		        			}
		        		}
		        		
		        	} while(itDico.hasNext() && letterOK);
		        	
		        	 
		        	//
		        	if(letterOK){
		        		outPutWord.addAll(map.get(randomStringLettersFound));
		        	}
		        			        	
		        }
		        
		        writer.write(randomGenerateChars + "-");
				Iterator<String> it = outPutWord.iterator();
				while(it.hasNext()) {
					writer.write(((String)it.next()).toUpperCase());
					if(it.hasNext()){
						writer.write(",");
					}
				}
				writer.write("\n");
				
				i++;
				
			}
			
			System.out.println("end file...");
		} catch(Exception e){
			if(writer != null){
				try{
					writer.close();
				} catch(Exception e1){
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
			
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
		
		scanner.close();
		
		System.out.println("... Fin Chargement fichier");
		
		return mots;
		
	}
}
