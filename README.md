LokiLibrary
===========

My Java Library for Internationalization, Logging, Properties/Preferences and some other stuff


Example for the main-Function, using the Library:

	public static void main(String[] args) {
		//Logfile Setup ---------------------------------------
		Logfile.INSTANCE.createLogfile("log/Logfile.html", //creates Logfile in "log" Folder
				"myemail@mywebsite.com");
		//-----------------------------------------------------

		//Properties setup ------------------------------------
		Properties config = PropertiesHelper
				.getProperties("resources/config.properties"); //reads Configfile from "resources" Folder
		//-----------------------------------------------------

		//Language Setup --------------------------------------
		Logfile.INSTANCE.writeTopic("Language Setup", 2); //Write Topic to Logfile
		String language = config.getProperty("language", "en_US"); //get the "language" Property from Configfile
		String[] localeStuff = language.split("_"); //with fallback to "en_US" and use it to initialize
		Lang.INSTANCE.setup(localeStuff[0], localeStuff[1], "i18n.strings", //internationalization. Setup
				true);													//internationalization with the selected langauge
		Logfile.INSTANCE.ftextOut("Language set to %s_%s<br>", localeStuff[0], //and log the language to
				localeStuff[1]);																//the Logfile
		//-----------------------------------------------------

		Logfile.INSTANCE.ftextOut(PURPLE, false, "Timestamp: %s", //Write current Timestamp to Logfile
				DateHelper.getTime());
		Logfile.INSTANCE.writeTopic(Lang.INSTANCE.getString("ApplicationName"), //Write Topic with
				2);														//Application Name from stringlist (en/de/whatever)

		//write your code here


		//cleanup work, has to be done on application exit
		Lang.INSTANCE.saveUndefinedStrings("src/i18n/strings.properties"); //saves all not configured strings
		Lang.INSTANCE.saveUndefinedStrings("src/i18n/strings_de_DE.properties"); //to the properties-files

		config.setProperty("language", language); //sets selected language (can be done somewhere else)
		PropertiesHelper.save("config", "Language setup"); //saves config file, if not exists, creates it

		Logfile.INSTANCE.closeLogfile(); //closes Logfile
	}
	
This way, the Logfile will be created in the "log" Folder, the Configfile will be created in the "resources" Folder, containing "en_US" for the selected language and two files for internationalization will be created in the sourcefolder "i18n" for german and english support. 