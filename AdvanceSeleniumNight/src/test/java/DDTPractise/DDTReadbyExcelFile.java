package DDTPractise;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class DDTReadbyExcelFile {

	public static void main(String[] args) throws IOException {
		FileInputStream fis=new FileInputStream("C:\\Users\\bhavy\\eclipse-workspace\\AdvanceSeleniumNight\\src\\test\\resources\\createCampaginDDT.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		//used single Step and for SHEET,ROW,CELL- i have collaborated
		String Campaign = wb.getSheet("CreateCampaignData").getRow(1).getCell(2).getStringCellValue();
		double targetSize=wb.getSheet("CreateCampaignData").getRow(1).getCell(3).getNumericCellValue();
		System.out.println(Campaign);
		System.out.println(targetSize);
	}

}
