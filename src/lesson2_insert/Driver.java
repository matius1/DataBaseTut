package lesson2_insert;
import java.sql.*;
//import java.

public class Driver {
	

	public static void main(String[] args) {
		
		String host	= "jdbc:mysql://localhost/jdbcdemo";
		String username = "root";
		String password = "";
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try{
			
			//1. get connection to database
		
			Connection myConn = DriverManager.getConnection(host, username, password);
			
			//2. create a statement
			Statement myStmt = myConn.createStatement();
			
			//3. Execute SQL query
			String sql = "insert into employees"
					+ " (last_name, first_name, email)"
					+ " values ('Brown', 'David', 'david.brBBBBown@foo.com')";
			
			myStmt.executeUpdate(sql);
			System.out.println("Insert complete");
			
			//4. Process the result set
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
