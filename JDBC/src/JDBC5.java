
import java.sql.*;

public class JDBC5 {

	public static void main(String[] args) throws SQLException {
		// Inserting Operation

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
		String sqlInsertQuery = "insert into student(id,name,branch) values(2,'harish','civil')";
		int rowAffected = statement.executeUpdate(sqlInsertQuery);
		System.out.println("No of rows affected is :: " + rowAffected);
				
		// Step6. Close the resources
		statement.close();
		connection.close();
		System.out.println("closing the resources...");
	}

}
