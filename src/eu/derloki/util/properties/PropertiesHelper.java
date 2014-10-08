package eu.derloki.util.properties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public enum PropertiesHelper {

	INSTANCE;
	
	public Properties getProperties(String fileName) throws IOException{
		Properties prop = new Properties();
		InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
		if(is == null){
			throw new FileNotFoundException("property file '" + fileName + "' not found in the classpath"); 
		}
		prop.load(is);		
		return prop;		
	}
	
}
