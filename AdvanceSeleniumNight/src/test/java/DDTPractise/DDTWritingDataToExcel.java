package DDTPractise;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class DDTWritingDataToExcel {
//no need to create a sheet in excel, the below code will automatically create sheet
	public static void main(String[] args) throws IOException {
		FileInputStream fis=new FileInputStream("C:\\Users\\bhavy\\eclipse-workspace\\AdvanceSeleniumNight\\src\\test\\resources\\createCampaginDDT.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		wb.createSheet("WriteData").createRow(1).createCell(2).setCellValue("Bhavya");
		//wb.getSheet("CreateCampaignData").createRow(2).createCell(0).setCellValue(2000);
		
	//Writing data
		FileOutputStream fos = new FileOutputStream("C:\\Users\\bhavy\\eclipse-workspace\\AdvanceSeleniumNight\\src\\test\\resources\\createCampaginDDT.xlsx");
		wb.write(fos);
		wb.close();
		System.out.println("Data written successfully");
	}

}
