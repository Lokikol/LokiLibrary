package eu.derloki.util.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public enum PropertiesHelper {

	INSTANCE;
	
	private HashMap<String, Properties> propertyList;
	
	private PropertiesHelper(){
		propertyList = new HashMap<String, Properties>();
	}
	
	public Properties getProperties(String fileName) throws IOException{
			
		
		if(propertyList.containsKey(fileName)){
			return propertyList.get(fileName);
		}
		
		Properties prop = new Properties();
		InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
		if(is == null){
			throw new FileNotFoundException("property file '" + fileName + "' not found in the classpath"); 
		}
		prop.load(is);
		
		propertyList.put(fileName, prop);
		
		return prop;		
	}
	
	public Properties loadProperties(String file) throws Exception{
		String key = file.substring(file.lastIndexOf("/")+1,file.length());
		if(propertyList.containsKey(key)){
			return propertyList.get(key);
		}
		
		Properties prop = new Properties();
		InputStream is = new FileInputStream(file);
		if(is == null){
			throw new Exception("property file '" + file + "' not found in the classpath"); 
		}
		prop.load(is);
		
		propertyList.put(key, prop);
		
		return prop;		
	}
	
	
	public Properties getFromList(String fileName){
		if(propertyList.containsKey(fileName)){
			return propertyList.get(fileName);
		}
		
		return null;
	}
	
	public Properties getShortV(String partFileName){
		String fileName= partFileName.concat(".properties");
		
		if(propertyList.containsKey(fileName)){
			return propertyList.get(fileName);
		}
		
		return null;
	}
}
