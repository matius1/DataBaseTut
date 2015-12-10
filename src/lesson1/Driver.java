// youtube luv2code

package lesson1;
import java.sql.*;
//import java.

public class Driver {
	

	public static void main(String[] args) {
		
		String host	= "jdbc:mysql://localhost/jdbcdemo";
		String username = "root";
		String password = "";
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
//			System.out.println("Dziala");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try{
			
			//1. get connection to database
		
			Connection myConn = DriverManager.getConnection(host, username, password);
			
			//2. create a statement
			Statement myStmt = myConn.createStatement();
			
			//3. Execute SQL query
			ResultSet myRs = myStmt.executeQuery("SELECT * FROM employees");
			
			//4. Process the result set
			while(myRs.next()){
				System.out.println(myRs.getString("last_name") + "\t " + myRs.getString("first_name"));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
