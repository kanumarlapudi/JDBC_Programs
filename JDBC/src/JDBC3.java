import java.sql.*;

public class JDBC3 {

	public static void main(String[] args) throws SQLException {
		
	    //performing update operation 
		
		// Step2. Establish the Connection
		String url = "jdbc:mysql:///ineuron";
		String username = "root";
		String password = "Harish16#";
		Connection connection = DriverManager.getConnection(url,username,password);
		System.out.println("Connection object created");
		
		// Step3. Create statement Object and send the Query
		Statement statement = connection.createStatement();
		System.out.println("STATEMENT object created...");
		String sqlUpdate = "Update student set name = 'suhas' where id = 1";
		int rowAffected = statement.executeUpdate(sqlUpdate);
		System.out.println("No of rows updated is :: " + rowAffected);
		
		//step 6 - close the connection
		statement.close();
		connection.close();
		System.out.println("Closing the resources...");	
	}
}
