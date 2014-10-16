package eu.derloki.util.log;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public enum Logfile {
	INSTANCE;
	
	public static enum FONTCOLORS{
		BLACK,RED,GREEN,BLUE,PURPLE
	}
	
	public static boolean L_FAIL = false;
	public static boolean L_OK = true;
	
	private String fileName;
	private Path path;
	private BufferedWriter writer = null;
	
	public void createLogfile(String fileName){
		this.fileName = fileName;
		this.path = Paths.get(this.fileName);
		Path parent = this.path.getParent();
		if(parent!=null)
			try {
				Files.createDirectories(this.path.getParent());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		try {
			writer = Files.newBufferedWriter(path, Charset.defaultCharset());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		textOut("<html><head><title>Logfile</title></head>");
		textOut("<body><font face='courier new'>");
		writeTopic("Logfile",3);
		
		textOut("<a href='mailto:gianluca.zurolo@web.de?subject=Logfile'>");
		textOut("Send E-Mail to me");
		textOut("</a><br><br>");
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			writer = Files.newBufferedWriter(path, Charset.defaultCharset(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
		
	public void writeTopic(String topic, int size){
		textOut("<table cellspacing='0' cellpadding='0' width='100%%'");
		textOut("bgcolor='#DFDFE5'>\n<tr>\n<td>\n<font face='arial' ");
		ftextOut("size='+%d'>\n",size);
		textOut(topic);
		textOut("</font>\n</td>\n</tr>\n</table>\n<br>");
		try {
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void textOut(String text){
			try {
				writer.write(text);
				writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	public void textOut(FONTCOLORS color,String text){
		textOut(color,false,text);
	}
	public void textOut(FONTCOLORS color, boolean list, String text){
		if(list){
			textOut("<li>");
		}
		
		switch(color){
		case BLACK:
			textOut("<font color=black>");
			break;
		case RED:
			textOut("<font color=red>");
			break;
		case GREEN:
			textOut("<font color=green>");
			break;
		case BLUE:
			textOut("<font color=blue>");
			break;
		case PURPLE:
			textOut("<font color=purple>");
			break;
		default:
			textOut("<font color=black>");
				break;
		
		}
		
		textOut(text);
		textOut("</font>");
		
		if(list){
			textOut("</li>");
		}
		else{
			textOut("<br>");
		}
	}
	
	public void ftextOut(String text, Object... args){
		String s = String.format(text,args);
		textOut(s);
	}
	
	public void ftextOut(FONTCOLORS color, String text, Object... args){
		String s = String.format(text,args);
		textOut(color,s);
	}
	
	public void ftextOut(FONTCOLORS color, boolean list, String text, Object... args){
		String s = String.format(text,args);
		textOut(color,list,s);
	}
	public void functionResult(String name, boolean result){
		functionResult(name, result, "");
	}
	
	public void functionResult(String name, boolean result, String message){
		String m = "-/-";
		if(message != null && !message.equals("")){
			m = message;
		}
		if(L_OK == result){
			textOut("<table width='100%%' cellSpacing='1' cellPadding='5'");
			textOut(" border='0' bgcolor='#C0C0C0'><tr><td bgcolor=");
			ftextOut("'#FFFFFF' width='35%%'>%s</td>",name);
			textOut("<td bgcolor='#FFFFFF' width='30%%'><font color=");
			textOut("'green'>OK</font></td><td bgcolor='#FFFFFF' ");
			ftextOut("width='35%%'>%s</td></tr></table>",m);
		}
		else{
			textOut("<table width='100%%' cellSpacing='1' cellPadding='5'");
			textOut(" border='0' bgcolor='#C0C0C0'><tr><td bgcolor=");
			ftextOut("'#FFFFFF' width='35%%'>%s</td>",name);
			textOut("<td bgcolor='#FFFFFF' width='30%%'><font color=");
			textOut("'red'>ERROR</font></td><td bgcolor='#FFFFFF' ");
			ftextOut("width='35%%'>%s</td></tr></table>",m);
		}
		
	}
	
	public void closeLogfile(){
		textOut("<br><br>End of logfile</font></body></html>");
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}