package RetryListernerImp;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListenerImplementation implements IRetryAnalyzer {
	
	int count=0;
	int limitCount=3;
	
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(count<limitCount)
		{
			count++;
			return true;
		}
		else {
		return false;
	}
	}
}
