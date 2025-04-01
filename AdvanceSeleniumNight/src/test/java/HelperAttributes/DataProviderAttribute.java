package HelperAttributes;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderAttribute {
	@Test(dataProvider="getData")

	public void testcase(String firstName,String LastName) 
	{
	System.out.println("FirstName "+firstName+" LastName" +LastName);
	}

	@DataProvider
	public Object[][] getData()
	{
	Object[][] objArr=new Object[3][2];
	objArr[0][0]="Bhavya";
	objArr[0][1]="Sunil";
	objArr[1][0]="Vivaan";
	objArr[1][1]="SunBhavi";
	objArr[2][0]="Sunny";
	objArr[2][1]="Selva";
	return objArr;
	}
}
