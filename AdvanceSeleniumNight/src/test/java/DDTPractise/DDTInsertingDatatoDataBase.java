package DDTPractise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;  //Compulsory import from this pkg
//add the dependency of MySQL so the Driver error ll go 

public class DDTInsertingDatatoDataBase {

	public static void main(String[] args) throws SQLException {
		Driver driverRef=new Driver();           //import from java.sql.Driver
		DriverManager.registerDriver(driverRef);
		
    	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ninza_12", "root", "rmgy@9999");
		
		Statement stmt = conn.createStatement();
		
		int result = stmt.executeUpdate("insert into Ninza_CRM_Details values('safari','http://49.249.28.218:8098/','scott','tiger')");

		System.out.println(result);
			if(result!=0)
				{
					System.out.println("data stored successfully");
				}
			else
				{
					System.out.println("operation failed");
				}		
		conn.close();
			}
		}

