package eu.derloki.util.log;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;



public enum Log {
	INSTANCE;
	
	public Logger logger;
	
	public Log createLog(String loggerName, String path){
		logger = Logger.getLogger(loggerName);  
	    FileHandler fh;  

	    try {  

	        // This block configure the logger with handler and formatter  
	    	/*File f = new File(path);
	    	
	    	if(!f.exists())
	    		f.createNewFile();*/
	    	
	        fh = new FileHandler(path);  
	        logger.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);   

	    } catch (SecurityException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }
		return INSTANCE;  
	}
	
	
	
}
