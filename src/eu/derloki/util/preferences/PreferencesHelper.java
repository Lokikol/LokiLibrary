package eu.derloki.util.preferences;

import java.util.HashMap;
import java.util.prefs.Preferences;

public class PreferencesHelper {

	private static HashMap<String, Preferences> systemPreferences = new HashMap<String, Preferences>();
	private static HashMap<String, Preferences> userPreferences = new HashMap<String, Preferences>();
	
	public static Preferences getUserPreferences(String name){
		Preferences pref = null;
		
		if(userPreferences.containsKey(name)){
			return userPreferences.get(name);
		}
		
		pref = Preferences.userRoot().node(name);
		userPreferences.put(name,pref);
		
		return pref;
	}
	
	public static Preferences getSystemPreferences(String name){
		Preferences pref = null;
		
		if(systemPreferences.containsKey(name)){
			return systemPreferences.get(name);
		}
		
		pref = Preferences.systemRoot().node(name);
		systemPreferences.put(name,pref);
		
		return pref;
	}
	
}
