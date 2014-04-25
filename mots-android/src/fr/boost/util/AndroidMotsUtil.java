package fr.boost.util;

import java.util.ArrayList;
import java.util.List;

public class AndroidMotsUtil {

	
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
	
	
}
