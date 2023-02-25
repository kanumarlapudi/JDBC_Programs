package in.neuron.main;

import in.neuron.util.JDBCUtil;
import java.sql.*;

public class insert {

	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = JDBCUtil.getJdbcConnection();
			if(connection != null) {
			    statement = connection.createStatement();
			}
			if(statement != null) {
				String selectQuery = "select * from student";
				resultSet = statement.executeQuery(selectQuery);
			}
			if(resultSet != null) {
				System.out.println("ID\t\tNAME\tBRANCH");
				while(resultSet.next()){
					Integer id = resultSet.getInt(1);
					String name = resultSet.getString(2);
					String branch = resultSet.getString(3);
					System.out.println(id+"\t" + name + "\t" + branch);
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				JDBCUtil.cleanUp(connection, statement, resultSet);
				System.out.println("Closing the resources");
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	}

}
