package GenericUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;  //Compulsory import from this pkg
//add the dependency of MySQL so the Driver error ll go 

public class DataBaseUtility {
		
		Connection conn;
		public void getDBConnection(String url,String uname,String password)
		{
			try {
				Driver driver = new Driver();
				DriverManager.registerDriver(driver);
				conn=DriverManager.getConnection(url, uname, password);
			}
			catch (Exception e) {
				// TODO: handle exception
				System.out.println("Connection not established");
			}
		}
		public void closeDBConnection() {
			try {
				conn.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		//SelectQuery
		public ResultSet executeSelectQuery(String query) {
			ResultSet result=null;
			try {
				Statement stmt = conn.createStatement();
				result=stmt.executeQuery(query);
			} catch (Exception e) {
				// TODO: handle exception
			}
			return result;
		}
		
		//Non Select Query
		public int executeNonSelectQuery(String query) {
			int result=0;
			try {
				Statement stmt = conn.createStatement();
				result=stmt.executeUpdate(query);
			} catch (Exception e) {
				// TODO: handle exception
			}
			return result;
		}
	}
