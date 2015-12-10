package lesson8_MetaData;
import java.sql.*;


public class MetaDataBasicInfo {

	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		String hostname = "jdbc:mysql://localhost/jdbcdemo";
		String username = "root";
		String password = "";
	
		Connection myConn = null;
		
		
		try {
			//1. get a connection
			myConn = DriverManager.getConnection(hostname, username, password);
			
			//2. get Metadata
			DatabaseMetaData databaseMetaData = myConn.getMetaData();
			
			//3. Display info about database
			System.out.println("Product name: " + databaseMetaData.getDatabaseProductName());
			System.out.println("Product version: " + databaseMetaData.getDatabaseProductVersion());
			System.out.println();
			
			//4. Display info about JDBC Driver
			System.out.println("JDBC Driver name: " + databaseMetaData.getDriverName());
			System.out.println("JDBC Driver version: " + databaseMetaData.getDriverVersion());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(myConn);
		}

		
	}//end main
	
	public static void close(Connection myConn) throws SQLException{
		if(myConn != null){
			myConn.close();
		}
	}

}
