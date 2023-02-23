package in.ineuron.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class Jdbc_Util {
	
	private Jdbc_Util(){
		
	}
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public static Connection getJdbcConnection() throws IOException, SQLException {
		FileInputStream fis = new FileInputStream("C:\\Users\\HARISH\\eclipse-workspace\\Jdbc_Blob_Operations\\src\\in\\ineuron\\util\\Properties");
		Properties p = new Properties();
		p.load(fis);
		String url = p.getProperty("url");
		String username = p.getProperty("username");
		String password = p.getProperty("password");
		
		Connection connection = DriverManager.getConnection(url, username , password);
		
		return connection;
	}
	
	public static void closeUp(Connection connection, PreparedStatement pstmt, ResultSet resultset) throws SQLException {
		if(connection != null) {
			connection.close();
		}
		if(pstmt != null) {
			pstmt.close();
		}
		if(resultset != null) {
			resultset.close();
		}
	}
}
