package BloodBankSystem.dbtask;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseOperation {
	

		private static Connection con;
		
		
		public static void closeConnection()
		{
			if(con!=null)
				try {
					con.close();
					System.out.println("Connection is getting closed");
				}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
		
		
		
		public static Connection openConnection()
		{
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");// factory method->to create the object of a class
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bloodbanksystemmis","root","root");
				//protocol //local host->is the name or ip address of the machine where RDBMS has been installed
			}
			catch(ClassNotFoundException|SQLException cse)
			{
				cse.printStackTrace();
			}
			return con;
		}

}
