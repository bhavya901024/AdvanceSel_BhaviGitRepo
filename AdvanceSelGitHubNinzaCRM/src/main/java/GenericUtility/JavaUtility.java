package GenericUtility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	public int getRandomNum(int limit) {
		Random ran = new Random();
		int randomNum = ran.nextInt(limit);
		return randomNum;
	}
	
	//today's date
	public String generateSystemDate() {
		Date dateObj = new Date();
		SimpleDateFormat sim=new SimpleDateFormat("dd-MM-yyyy");  //MM should be in caps
		String todayDate=sim.format(dateObj);
		return todayDate;
	}
	
	//to generate Required Date
	public String generateReqDate(int days) {
	Date dateObj = new Date();
	SimpleDateFormat sim=new SimpleDateFormat("dd-MM-yyyy");  //MM should be in caps
	String todayDate=sim.format(dateObj);
	
	Calendar cal=sim.getCalendar();
	cal.add(Calendar.DAY_OF_MONTH, 30);
	String closeDate=sim.format(cal.getTime());
	return closeDate;
	}
}
