package in.neuron.util;
import java.sql.*;

public class JDBCUtil {

	private JDBCUtil(){
		
	}
	
	static {
		//Step1: loading and register the Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getJdbcConnection() throws SQLException {
		
			//Step2. Establish the Connection with database
			String url = "jdbc:mysql://localhost:3306/ineuron";
			String username = "root";
			String password = "Harish16#";
			Connection connection = DriverManager.getConnection(url, username, password);    //here the result can store in connection variable only
			System.out.println("Connection object created");
			return connection;	
		
	}
	public static  void cleanUp(Connection connection, Statement statement, ResultSet resultset) throws SQLException {
		//step6 - closing the resources
		if(connection != null) {
			connection.close();
		}
		if(statement != null) {
			statement.close();
		}
		if(resultset != null) {
			resultset.close();
		}
	}
	
	
}
