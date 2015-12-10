package lesson5_prepareStatements;
import java.sql.*;
//import java.

public class Driver {
	

	public static void main(String[] args) throws SQLException {
		
		String host	= "jdbc:mysql://localhost/jdbcdemo";
		String username = "root";
		String password = "";
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try{
			
			//1. get connection to database
		
			myConn = DriverManager.getConnection(host, username, password);
			
			//2. prepare statement
			myStmt = myConn.prepareStatement("select * from employees where salary > ? and department = ?");
			
			//3. set the parameters
			myStmt.setDouble(1, 80000);
			myStmt.setString(2, "Legal");
			
			//4. execute sql query
			myRs = myStmt.executeQuery();
			
			//5. Display the result set
			display(myRs);
			
			//
			// Reuse the prepared statement: salary > 25000, department  = HR
			//
			
			System.out.println("\n\nReuse the prepared statement: salary > 25000, department = HR");
			
			//6. set the parameters
			myStmt.setDouble(1, 25000);
			myStmt.setString(2, "HR");
			
			//7. Execute sql query
			myRs= myStmt.executeQuery();
			
			//8. display the result
			display(myRs);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();
			}
		}
	}
	
	private static void display(ResultSet myRs) throws SQLException {
		while (myRs.next()) {
			String lastName = myRs.getString("last_name");
			String firstName = myRs.getString("first_name");
			double salary = myRs.getDouble("salary");
			String department = myRs.getString("department");
			
			System.out.printf(lastName + ", " + firstName+ ", " + salary + ", " + department + "\n");
		}
	}

}
