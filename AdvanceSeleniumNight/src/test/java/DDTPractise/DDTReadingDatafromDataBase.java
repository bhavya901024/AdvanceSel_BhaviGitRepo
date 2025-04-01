package DDTPractise;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;

	import com.mysql.cj.jdbc.Driver;  //Compulsory import from this pkg
	//add the dependency of MySQL so the Driver error ll go 
	
	public class DDTReadingDatafromDataBase {

		public static void main(String[] args) throws SQLException {
			Driver driverRef=new Driver();           //import from java.sql.Driver
			DriverManager.registerDriver(driverRef);
			
	    	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ninza_12", "root", "rmgy@9999");
			
			Statement stmt = conn.createStatement();
			
			ResultSet result = stmt.executeQuery("select * from Ninza_CRM_Details");
			
			while(result.next())
			{
				String browser = result.getString(1);
				String url = result.getString(2);
				String uname = result.getString(3);
				String pwd = result.getString(4);
				System.out.print(browser+" ");
				System.out.print(url+" ");
				System.out.print(uname+" ");
				System.out.println(pwd);
			}
			
			conn.close();
			
		}

	}
