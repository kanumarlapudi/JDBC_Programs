package in.ineuron.code;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.PreparedStatement;

import jdbc.util.jdbc_util;

public class Select_PreparedStatement {

	public static void main(String[] args) throws IOException, SQLException {
		
		Connection connection = null;
		PreparedStatement preparedstatement = null;
		ResultSet resultset = null;
		Scanner scan = new Scanner(System.in);
		int id =0;
		
		connection = jdbc_util.getJdbcConnection();
		
		if(connection != null) {
			String sqlQuery = "Select id, name, branch from student where id = ?";
			preparedstatement = connection.prepareStatement(sqlQuery);
		}
		
		if(preparedstatement != null) {
			
			System.out.println("Please enter the value of id to get the details: ");
			id = scan.nextInt();
			
			// use precompiled query to set the values
			preparedstatement.setInt(1, id);
			// execute the query
			resultset = preparedstatement.executeQuery();
		}
		
		if(resultset != null) {
			if (resultset.next()) {
				System.out.println("id \t name");
				id = resultset.getInt(1);
				String name = resultset.getNString(2);
				
				System.out.println(id + " \t " + name);
			}
			else {
				System.out.println("Record not available for the give id :: " + id);
			}
		}
		
		if(connection != null) {
			connection.close();
		}
		if(preparedstatement != null) {
			preparedstatement.close();
		}
		if(resultset != null) {
			resultset.close();
		}
	}

}
