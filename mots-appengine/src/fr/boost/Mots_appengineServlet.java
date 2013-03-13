package fr.boost;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;

@SuppressWarnings("serial")
public class Mots_appengineServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		/*Entity word = new Entity("Word", "1A1D1E1M1M1S");
		word.setProperty("words", "ERG,GRE,GRUE,GUE,PERÇU,PEU,PEUR,PRE,PREVU,PUE,PUER,PUR,PURE,PURGE,REÇU,REG,REPU,REVU,RUE,URE,URGE,VER,VUE");
        word.isUnindexedProperty("words");
        
        DatastoreService datastore =
                DatastoreServiceFactory.getDatastoreService();
        datastore.put(word);
		
		
        Query query = new Query("Word");
        List<Entity> words = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(5));
        
        for(Entity w : words){
        	System.out.println("key = " + w.getKey());
        	System.out.println(w.toString());
        }
        
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world TOTO");*/
		
		
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		
		
	}


	public void storeFile(){
		
	}
}
