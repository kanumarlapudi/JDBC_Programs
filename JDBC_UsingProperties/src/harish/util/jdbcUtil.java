package harish.util;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class jdbcUtil {
	
	private jdbcUtil() {
		
	}
	
	static {
		try {
			//step-1
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Driver Class is loaded and registered");
	}
	
	//step-2
	public static Connection getJdbcConnection() throws SQLException, IOException {
		// Take the data from properties file
		//we can create properties file by right clickin on project name select file
		FileInputStream fis = new FileInputStream("C:\\Users\\HARISH\\eclipse-workspace\\JDBC_UsingProperties\\application.properties"); 
		// we will get location properties file by right clicking on file then properties
		Properties properties = new Properties();
		properties.load(fis);
		String url = properties.getProperty("url");
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");
		Connection connection = DriverManager.getConnection(url, username, password);
		System.out.println("Connection is created");
		return connection;
	}
	
	public static void cleanUp(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
		if(connection != null) {
			connection.close();
		}
		if(statement != null) {
			statement.close();
		}
		if(resultSet != null) {
			resultSet.close();
		}
	}

	
}
