package fr.boost;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.boost.util.MotsUtil;

 
@Path("/words")
public class MotsRestServlet {
 
	private static List<String> fichierMot = loadListeMots();
    @GET
    //@Path("/{letters}")
    @Produces(MediaType.TEXT_HTML)
    public String getWordsByRandomLetters(/*@PathParam("letters") String letters*/) {
    	
    	Date date = new Date();
    	System.out.println("Recherche des mots...");
    	String[] randomChar = MotsUtil.generateRandomChar();
        
        String randomString = "";
        for(String c : randomChar){
        	randomString += c;
        }
        System.out.println(randomString);
        
        String randomStringLetters = MotsUtil.countByLetters(randomString);
        
    	 List<String> allRandomizedPossibilities = MotsUtil.extractAllRandomizedPossibilities(randomStringLetters);
          
    	
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
        Date dateFin = new Date();
        long temps = dateFin.getTime() - date.getTime();
        		
        System.out.println("Find de recherche en " + temps + " ms");
        /*ResponseBuilder responseBuilder;
        responseBuilder = Response.status(Status.OK);
        responseBuilder = responseBuilder.type(MediaType);
        responseBuilder = responseBuilder.entity("<body>Hello world!!</body>");
        Response response =  responseBuilder.build();*/
        String res = "<html><body>Hello!!<br>" +
        		"<b>Votre choix de lettre :</b> ";
		    	for(String lettre : randomChar){
		    		res += lettre + " ";
		    	}        		
        		res +="<br>";
        		res +="<b>Réponse :</b><br> ";
	        	for(String motPossible : motsPossibles){
	        		res += motPossible + "<br>";
	        	}
	        	res += temps + " ms";
        		res +="<br></body></html>";
        		
        return res;
    }
    
    private static List<String> loadListeMots(){
    	
    	System.out.println("Loading file...");
    	
    	String fileName = "liste.de.mots.francais.frgut.txt";
    	
        
    	List<String> mots = new ArrayList<String>();
    	InputStream is = null;
		try{
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
			Scanner scanner = new Scanner(is);
	       
    	// On boucle sur chaque champ detect�
			while (scanner.hasNextLine()) {
				mots.add(scanner.nextLine());
			}
			
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