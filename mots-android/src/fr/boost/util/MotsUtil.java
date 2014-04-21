package fr.boost.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MotsUtil {

	
	private static String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
	private static String charsCons = "BCDFGHJKLMNPQRSTVWXZ"; 
	private static String charsVoy = "AEIOUY"; 
	
	/**
	 * Génère une chaine de caractère aléatoire de 6 caractères
	 * nbVoy voyelles, nbCons consonnes
	 * 
	 */
	public static String[] generateConsAndVoyRandomChar(int nbCons, int nbVoy){
		
		String[] randomChar = new String[6];
        
        for(int i = 0 ; i < nbVoy; i++){
        	randomChar[i] = "" + generateVoy();
        }
        for(int i = 0 ; i < nbCons; i++){
        	randomChar[i + nbVoy] = "" + generateCons();
        }


		
        return randomChar;
	}
	/**
	 * Génère une chaine de caractère aléatoire de 6 caractères
	 * nbVoy voyelles, nbCons consonnes
	 * 
	 */
	public static String[] generateRandomChar(){
		
		String[] randomChar = new String[6];
		
		for(int i = 0 ; i <6; i++){
			randomChar[i] = "" + generate();
    	}
		
		 return randomChar;
	}
	
	
    private static char generateVoy() {
        int i = (int) Math.floor(Math.random() * (charsVoy.length() -1));
        return charsVoy.charAt(i);
   }
    private static char generateCons() {
        int i = (int) Math.floor(Math.random() * (charsCons.length() -1));
        return charsCons.charAt(i);
   }
    private static char generate() {
        int i = (int) Math.floor(Math.random() * (chars.length() -1));
        return chars.charAt(i);
   }
    /**
     * Permet d'obtenir une chaine de caractère concatenant le nombre de lettres dans l'ordre alphabétique
     * 
     * ex : AERRTB retourne
     * 1A1B1E2R1T
     * 
     * @param mot
     * @return String
     */
	public static String countByLetters(String mot){
		
		String res = "";
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		for(int i = 0; i < mot.length(); i++){
			String currentChar = String.valueOf(mot.charAt(i));
			if(!map.containsKey(currentChar)){
				map.put(currentChar, 1);
			} else {
				map.put(currentChar, map.get(currentChar) + 1);
			}
			
		}
		
		for(int i = 0 ; i < chars.length(); i++){
			
			String currentLetter = String.valueOf(chars.charAt(i));
			if(map.containsKey(currentLetter)){
				for(int p = 0; p< map.get(currentLetter); p++){
					res += "1" + currentLetter;
				}
				
			}
			
			
		}
		
		return res;
		
		
	}
	
	public static List<String> getLettersFromMotId(String motId){
		
		List<String> res = new ArrayList<String>();
		for(int i = 0; i < motId.length(); i= i + 2){
			int nbLetters = Integer.valueOf(String.valueOf(motId.charAt(i)));
			for(int k = 0; k < nbLetters; k++){
				res.add(String.valueOf(motId.charAt(i+1)));
			}
			
		}
		
		return res;
		
		
	}
	
	/**
	 * Permet de retourner toutes les combinaisons d'une chaine de type "1A1B1C1D1E1F" retourné par la méthode
	 *  countByLetters(String mot)
	 *  
	 * @param randomizedLetters
	 * @return
	 */
	public static List<String> extractAllRandomizedPossibilities(String randomizedLetters){
		
		List<String> res = new ArrayList<String>();
		
		String[] split = new String[randomizedLetters.length() / 2];
		int j = 0;
		for(int i = 0 ; i < randomizedLetters.length(); i = i+2){
			split[j] = randomizedLetters.substring(i, i+2);
			j++;
		}
		
		int splitLength = split.length;// - 2;
		
		for(int k=0; k<splitLength; k++){
			
			for(int i = k + 1; i<splitLength; i++){
					res.add(split[k] + split[i]);
				if(i < 5 ){
					res.add(split[k] + split[i] + split[i+1]);
				}
				if(i < 4 ){
					res.add(split[k] + split[i] + split[i+1] + split[i+2]);
				}
				if(i < 3 ){
					res.add(split[k] + split[i] + split[i+1] + split[i+2] + split[i+3]);
				}
				if(i < 2 ){
					res.add(split[k] + split[i] + split[i+1] + split[i+2] + split[i+3] + split[i+4]);
				}
			}
			
		}
		
		for(int k=0; k<splitLength; k++){
			
			for(int i = k + 3; i<splitLength; i++){
					//res.add(split[k] + split[i]);
				if(i < 6 ){
					res.add(split[k] + split[k+1] + split[i]);
				}
				if(i < 5 ){
					res.add(split[k] + split[k+1] + split[i] + split[i+1]);
					
				}
				if(i< 4){
					res.add(split[k] + split[k+1] + split[i] + split[i+1] + split[i+2]);
				}	
			}
			
		}		
		
		for(int k=0; k<splitLength; k++){
			
			for(int i = k + 2; i<splitLength; i++){
					//res.add(split[k] + split[i]);
				if(i < 4 ){
					res.add(split[k] + split[i] + split[i + 2]);
				}
				if(i < 3 ){
					res.add(split[k] + split[i] + split[i + 2] + split[i + 3]);
				}
			}
			
		}
		
		for(int k=0; k<splitLength; k++){
			
			for(int i = k + 3; i<splitLength; i++){
					//res.add(split[k] + split[i]);
				if(i < 4 ){
					res.add(split[k] + split[k+1] + split[i] + split[i + 2]);
				}
			}
			
		}	
		
		for(int k=0; k<splitLength; k++){
			
			for(int i = k + 4; i<splitLength; i++){
					//res.add(split[k] + split[i]);
				if(i < 5 ){
					res.add(split[k] + split[k+1] + split[k+2] + split[i] );
					res.add(split[k] + split[k+1] + split[k+2] + split[i] + split[i +1]);
				}
			}
			
		}
		for(int k=0; k<splitLength; k++){
			
			for(int i = k + 5; i<splitLength; i++){
					//res.add(split[k] + split[i]);
				if(i < 6 ){
					res.add(split[k] + split[k+1] + split[k+2] + split[k+3] + split[i]);
				}
			}
			
		}
		
		return res;
		
	}
	
	public static String replaceChar(String mot){
		
		mot = mot.replace('é', 'e')
				.replace('é', 'e')
				.replace('è', 'e')
				.replace('ê', 'e')
				.replace('ë', 'e')
				.replace('ï', 'i')
				.replace('î', 'i')
				.replace('à', 'a')
				.replace('â', 'a')
				.replace('ä', 'a')
				.replace('û', 'u')
				.replace('ü', 'u')
				.replace('ô', 'o')
				.replace('ö', 'o');
		
		return mot.toUpperCase();
	}
	
}
