package DDTPractise;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DDTReadingbyJsonFile {

	public static void main(String[] args) throws IOException, ParseException {
	JSONParser parser = new JSONParser();
	FileReader file = new FileReader("C:\\Users\\bhavy\\eclipse-workspace\\AdvanceSeleniumNight\\src\\test\\resources\\JsonData.json");
	Object javaObj=parser.parse(file);
	
	JSONObject obj=(JSONObject)javaObj;
	String name=obj.get("name").toString();
	String id=obj.get("id").toString();
	Object id1=obj.get("id");
	String age=obj.get("Age").toString();
	String occupation=obj.get("Occupation").toString();
	String isworking=obj.get("isWorking").toString();
	Object isworking1=obj.get("isWorking");  
	//String backLog=obj.get("backLog").toString();
	Object backLog1=obj.get("backLog");
	System.out.println("name");
	System.out.println("id");
	System.out.println("id1");
	System.out.println("Age");
	System.out.println("Occupation");
	System.out.println("isWorking");
	System.out.println("isWorking1");
	//System.out.println("backLog");
	System.out.println(backLog1);
	}

}
