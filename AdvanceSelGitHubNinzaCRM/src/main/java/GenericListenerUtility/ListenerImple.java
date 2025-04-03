package GenericListenerUtility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import genericBaseClassUtility_ConfigAttributes.BaseClass;

public class ListenerImple implements ITestListener,ISuiteListener {

	@Override
	public void onStart(ISuite suite) {
		System.out.println("Report configuration");
		Date date = new Date();
		SimpleDateFormat sim=new SimpleDateFormat("HH:mm:ss");
		String time = sim.format(date);
		System.out.println(time);
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Report backup");
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println(result.getMethod().getMethodName()+" Starts ");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println(result.getMethod().getMethodName()+" ends ");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testcaseName=result.getMethod().getMethodName();
		Date date = new Date();
		SimpleDateFormat sim=new SimpleDateFormat("HH-mm-ss");
		String time = sim.format(date);
		TakesScreenshot ts=(TakesScreenshot)BaseClass.sdriver;
		File temp = ts.getScreenshotAs(OutputType.FILE);
		File dest=new File("./screenshots/"+testcaseName+"_"+time+".png");
		try {
			FileHandler.copy(temp, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	@Override
	public void onStart(ITestContext context) {
	
	}

	@Override
	public void onFinish(ITestContext context) {
		
	}

	
}
