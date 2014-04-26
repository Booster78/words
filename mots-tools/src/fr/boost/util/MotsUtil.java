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
	 * Génère une chaine de caractère aléatoire de X caractères
	 * nbVoy voyelles, nbCons consonnes
	 * 
	 */
	public static String[] generateConsAndVoyRandomChar(int nbLettres, int maxVoy, int minVoy){
		
		int nbVoyRandom =(int) (minVoy + Math.floor(Math.random() * (maxVoy - minVoy + 1)));
		
		String[] randomChar = new String[nbLettres];
        
        for(int i = 0 ; i < nbVoyRandom; i++){
        	randomChar[i] = "" + generateVoy();
        }
        for(int i = 0 ; i < (nbLettres - nbVoyRandom); i++){
        	randomChar[i + nbVoyRandom] = "" + generateCons();
        }


		
        return randomChar;
	}
	
	public static List<String> converteGenerateCharsToList(String randomChars){
		List<String> str = new ArrayList<String>();
		for(int i = 0; i < randomChars.length(); i = i + 2){
			str.add(String.valueOf(randomChars.charAt(i)) + String.valueOf(randomChars.charAt(i+1)));
		}
		return str;
		
	}
	/**
	 * Génère une chaine de caractère aléatoire de X caractères
	 * nbVoy voyelles, nbCons consonnes
	 * 
	 */
	public static String[] generateRandomChar(int nbChars){
		
		String[] randomChar = new String[nbChars];
		
		for(int i = 0 ; i <nbChars; i++){
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
	public static String countByLetters(String mot, boolean oneByone){
		
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
				if(oneByone){
					for(int p = 0; p< map.get(currentLetter); p++){
						res += "1" + currentLetter;
					}
				} else {
					res += map.get(currentLetter) + currentLetter;
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
	
	
	public static int countLettersFromMotId(String motId){
		
		int sum = 0;
		for(int i = 0; i < motId.length(); i= i + 2){
			int nbLetters = Integer.valueOf(String.valueOf(motId.charAt(i)));
			sum = sum + nbLetters;			
		}
		
		return sum;
		
		
	}
	
	
}
