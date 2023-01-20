
import java.sql.*;
public class JDBC2 {

	public static void main(String[] args) throws SQLException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			//step 1 - to load and register the driver class
			//From JDBC4.X version onwards,there is a facility of "autoloading". no need to write step1

		
			            /* Class.forName("com.mysql.cj.jdbc.Driver");
			               System.out.println("Class loaded succesfully");   */
			
			//step 2 - establish the connection with database
			//String url = "jdbc:mysql://localhost:3306/ineuron";
			//If java pgm and database engine is running in the same program with the default port no for database then url can be of the following type
			String url = "jdbc:mysql:///ineuron";
			String username = "root";
			String password = "Harish16#";
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("established connection succesfully");
			System.out.println("The implement class name is " + connection.getClass().getName());
			
			//step 3 - create statement object and send the query
			String sqlSelectQuery = "select id,name,branch from student";
			statement = connection.createStatement();
			System.out.println("The implement class name is " + statement.getClass().getName());
			resultSet = statement.executeQuery(sqlSelectQuery);
			System.out.println("The implement class name is " + resultSet.getClass().getName());
			System.out.println();
			System.out.println("ID\tNAME\tBRANCH");
			
			//step 4 - process the result set
			while(resultSet.next()){
				Integer id = resultSet.getInt("id");           // Using resultSet object, we can retrieve the records based on the column names also.
				String name = resultSet.getString("name");     // Using resultSet object, we can retrieve the records based on the column names also.
				String branch = resultSet.getString("branch"); // Using resultSet object, we can retrieve the records based on the column names also.
				System.out.println(id + "\t" + name + "\t" + branch);
			}
			
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		//Step 6 - closing the resources
		resultSet.close();
		statement.close();
		connection.close();
        System.out.println("Resources are closed");
	}

}
