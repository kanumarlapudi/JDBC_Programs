import java.sql.*;

public class JDBC4 {

	public static void main(String[] args) throws SQLException {
		
		// Performing Delete Operation

		// Step2. Establish the Connection
		String url = "jdbc:mysql:///ineuron";
		String username = "root";
		String password = "Harish16#";
		Connection connection = DriverManager.getConnection(url,username,password);
		System.out.println("connection object created...");
		
		// Step3. Create statement Object and send the Query
		Statement statement = connection.createStatement();
		System.out.println("statement object created...");
		
		// Step4. Execute the Query and Process the resultSet
		String sqlDeleteQuery = "delete from student where id = 0";
		int rowAffected = statement.executeUpdate(sqlDeleteQuery);
		System.out.println("No of rows affected is :: " + rowAffected);
		
		// Step6. Close the resources
		statement.close();
		connection.close();
		System.out.println("closing the resources...");
		
	}

}
