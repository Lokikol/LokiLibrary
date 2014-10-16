package eu.derloki.util.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class PropertiesHelper {

	private static HashMap<String, Properties> propertyList = new HashMap<String, Properties>();
	private static HashMap<String,String> saveingHelper = new HashMap<String,String>();
	
	public static Properties getProperties(String file){
		String key = file.substring(file.lastIndexOf("/")+1,file.length());
		if(propertyList.containsKey(key)){
			return propertyList.get(key);
		}
		
		Properties prop = new Properties();
		InputStream is = null;
		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			File f = new File(file);
			if(!f.exists()){
				try {
					f.getParentFile().mkdirs();
					f.createNewFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			try {
				is = new FileInputStream(f);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		try {
			prop.load(is);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		propertyList.put(key, prop);
		saveingHelper.put(key,file);
		
		return prop;
	}
	
	
	public static Properties getFromList(String fileName){
		if(propertyList.containsKey(fileName)){
			return propertyList.get(fileName);
		}
		
		return null;
	}
	
	public static Properties getShortV(String partFileName){
		String fileName= partFileName.concat(".properties");
		
		if(propertyList.containsKey(fileName)){
			return propertyList.get(fileName);
		}
		
		return null;
	}
	
	public static void saveLong(String fileName, String comments){
		if(propertyList.containsKey(fileName)){
			Properties p = propertyList.get(fileName);
			String path = saveingHelper.get(fileName);
			
			try {
				File f = new File(path);
				FileOutputStream os = new FileOutputStream(f);
				p.store(os, comments);
				os.flush();
				os.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void save(String partFileName, String comments){
		String fileName = partFileName.concat(".properties");
		saveLong(fileName,comments);
	}
}
