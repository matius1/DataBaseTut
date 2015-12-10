package lesson6_callingStoredProcedures;
import java.sql.*;
//IN PARAMETERS
public class IncreaseSalariesForDepartment {

	public static void main(String[] args) throws SQLException {
		Connection myConn = null;
		CallableStatement myStmt = null;

		try{
			//get a connection to database
			String host	= "jdbc:mysql://localhost/jdbcdemo";
			String username = "root";
			String password = "";
			
			myConn = DriverManager.getConnection(host, username, password);
			System.out.println("Connected to: " + host);
			
			String theDepartment = "Engineering";
			int theIncreaseAmount = 10000;
			
			//show salaries BEFORE
			System.out.println("Salaries BEFORE\n");
			showSalaries(myConn, theDepartment);
			
			//prepare the store procedure call
			myStmt = myConn.prepareCall("{call increase_salaries_for_department(?,?)}");
			
			//set the parameters
			myStmt.setString(1, theDepartment);
			myStmt.setDouble(2, theIncreaseAmount);
			
			//call stored procedure
			System.out.println("\n\nCalling stored procedure.  increase_salaries_for_department('" + theDepartment + "', " + theIncreaseAmount + ")");
			myStmt.execute();
			System.out.println("Finished calling stored procedure");
			
			//show salaries AFTER
			System.out.println("\n\nSalaries AFTER\n");
			showSalaries(myConn, theDepartment);
			
		}catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			close(myConn, myStmt, null);
		}
		
	}
	//end main
	
	public static void showSalaries(Connection myConn, String theDepartment) throws SQLException{
		
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try{
			//prepared statement
			myStmt = myConn.prepareStatement("select * from employees where department = ?");
			
			myStmt.setString(1, theDepartment);
			
			//execute sql query
			myRs = myStmt.executeQuery();
			
			//process result set
			while(myRs.next()){
				String lastName = myRs.getString("last_name");
				String firtsName = myRs.getString("first_name");
				double salary = myRs.getDouble("salary");
				String department = myRs.getString("department");
				
				System.out.println(lastName + ", " + firtsName + ", " + salary + ", " + department);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(myStmt, myRs);
		}
	}//end showSalaries
	
	public static void close(Connection myConn, Statement myStmt, ResultSet myRs) throws SQLException{
		if(myRs != null){
			myRs.close();
		}
		if(myStmt != null){
			myStmt.close();
		}
		if(myConn != null){
			myConn.close();
		}
		
	}//end close
	
	public static void close(Statement myStmt, ResultSet myRs) throws SQLException{
		close(null, myStmt, myRs);
		
	}//end close

}
