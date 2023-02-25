package harish.insert;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import harish.util.jdbcUtil;

public class insetQuery {

	public static void main(String[] args) throws IOException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		 
		try {
			
			connection = jdbcUtil.getJdbcConnection();
			
			if(connection != null) {
				statement = connection.createStatement();
				System.out.println("Statement is created");
			}
			if(statement != null) {
				String selectQuery = "select * from student";
				resultSet = statement.executeQuery(selectQuery);
				System.out.println("resultset is created");
				System.out.println();
				System.out.println("ID\tNAME\tBranch");
				while(resultSet.next()) {
					Integer id = resultSet.getInt(1);
					String name = resultSet.getString(2);
					String branch = resultSet.getString(3);
					System.out.println(id + "\t" + name + "\t" + branch);
			}
			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		finally {
			try {
				jdbcUtil.cleanUp(connection, statement, resultSet);
				System.out.println("Resources are closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
