package in.ineuron.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.Scanner;

import in.ineuron.util.JdbcUtil;

public class DeleteRecordApp {

	public static void main(String[] args) {
		Connection connection = null;
		Statement stmt = null;
		ResultSet resultSet = null;
		Scanner scanner = null;
		
		try {
			
			connection = JdbcUtil.getJdbcConnection();

			if (connection != null)
				// Expecting ResultSet to be SCROLLABLE AND UPDATABLE
				
		    	stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			String sqlQuery = "select id,name,age,address from employees";

			if (stmt != null)
				resultSet = stmt.executeQuery(sqlQuery);

			if (resultSet != null) {

				System.out.println("Records Before Updation");
				System.out.println("ID\tNAME\tAGE\tADDRESS");
				while (resultSet.next()) {
					System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3)
							+ "\t" + resultSet.getString(4));
				}
				
				System.out.println();

			    resultSet.last();
			    resultSet.deleteRow();
				System.out.println("After Deletion");

				if (resultSet != null) {

					System.out.println("ID\tNAME\tAGE\tADDRESS");
					resultSet.beforeFirst();
					while (resultSet.next()) {
						System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3)
								+ "\t" + resultSet.getString(4));
					}
			
				}
			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}finally {
			try {
				JdbcUtil.cleanUp(connection, stmt, resultSet, null);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
