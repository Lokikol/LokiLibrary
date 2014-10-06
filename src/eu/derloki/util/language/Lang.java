package eu.derloki.util.language;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public enum Lang {
	INSTANCE;
	
	private Locale currentLocale;
	private ResourceBundle messages;
	private String messageFiles;
	private boolean setup = false;
	private boolean save = false;
	private ArrayList<String> saveList = null;
	
	public void setLocale(String language, String country){
		currentLocale = new Locale(language,country);
		setMessages(messageFiles);
		
	}
	
	public void setMessages(String messageFiles){
		try {

			this.messages = ResourceBundle.getBundle(messageFiles,currentLocale);
			this.messageFiles = messageFiles;
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void setup(String language, String country, String messageFiles){
		setup(language,country,messageFiles,false);
	}
	
	public void setup(String language, String country, String messageFiles, boolean save){
		setLocale(language,country);
		setMessages(messageFiles);
		setup = true;
		this.save = save;
		if(save){
			saveList = new ArrayList<String>();
		}
	}
	
	public String getString(String m){
		return getString(m,null,null,null);
	}
	
	public String getString(String m, String messageFiles){
		return getString(m,null,null,messageFiles);
	}
	
	public String getString(String m, String language, String country){				
		return getString(m,language,country,null);
	}
	
	
	public String getString(String m, String language, String country, String messageFiles){
		ResourceBundle tempBundle = messages;
		if(language != null && country != null){
			Locale tempLocale = new Locale(language,country);
			if(messageFiles != null){
				tempBundle = ResourceBundle.getBundle(messageFiles,tempLocale);
			}
			else{
				tempBundle = ResourceBundle.getBundle(this.messageFiles,tempLocale);
			}
		}
		else if(messageFiles != null){
			tempBundle = ResourceBundle.getBundle(messageFiles,currentLocale);
		}
		
		String ret = "?"+m;
		if(!setup)
			return ret;
		try {
			ret = tempBundle.getString(m);
		} catch (MissingResourceException e) {
			addToList(m);
		} catch (NullPointerException e){
			addToList(m);
		}
		return ret;
	}
	
	private void addToList(String m){
		if(save && saveList != null){
			if(!saveList.contains(m)){
				saveList.add(m);
			}
		}
	}
	
	public String getUndefinedStrings(){
		String s = "";
		
		if(saveList != null){
			for (String st : saveList) {
				s += st + "\n";
			}
		}
		
		return s;
	}
	
	private String getUndefinedStringsForSave(){
		String s = "";
		
		if(saveList != null){
			for (String st : saveList) {
				s += st + "= \n";
			}
		}
		
		return s;
	}
	
	public void saveUndefinedStrings(String path){
		if(save){
			String stringsToSave = getUndefinedStringsForSave();
			if(!stringsToSave.equals("")){
				Path p = Paths.get(path);
				Path parent = p.getParent();
				boolean exists = Files.exists(p);
				if(!Files.exists(parent)){
					try {
						Files.createDirectories(parent);
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				try {
					if(exists)
						Files.write(p,stringsToSave.getBytes(),StandardOpenOption.APPEND);
					else
						Files.write(p,stringsToSave.getBytes());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public String getSentence(String m){
		return getSentence(m,null,null,null);
	}
	
	public String getSentence(String m, String messageFiles){
		return getSentence(m,null,null,messageFiles);
	}
	
	public String getSentence(String m, String language, String country){				
		return getSentence(m,language,country,null);
	}
	
	
	public String getSentence(String m, String language, String country, String messageFiles){
		String[] s = m.split(" ");
		String ret = "";
		
		for(int i = 0; i < s.length; i++){
			String cur = s[i];
			String ns = getString(cur, language, country, messageFiles);
			ret += ns + " ";
		}
		
		ret = ret.substring(0, ret.length()-1);
		return ret;
	}
	
}