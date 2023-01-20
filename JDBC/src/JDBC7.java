import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBC7 {

	public static void main(String[] args) throws SQLException {
		// Inserting Dynamically 2nd approach
		
		Scanner scan = new Scanner(System.in);
		
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
		System.out.println("Enter the id no to insert: ");
		int id = scan.nextInt();
		System.out.println("Enter the name to insert: ");
		String name = scan.next();
		System.out.println("Enter the branch to insert: ");
		String branch = scan.next();
		
		name = " ' " + name + " ' ";
		branch = " ' " + branch + " ' ";
		String sqlInsertQuery = "insert into student(id,name,branch) values("+id+","+name+","+branch+")";
		System.out.println(sqlInsertQuery);
		int rowAffected = statement.executeUpdate(sqlInsertQuery);
		System.out.println("No of rows affected is :: " + rowAffected);
						
		// Step6. Close the resources
		statement.close();
		connection.close();
		System.out.println("closing the resources...");


	}

}
