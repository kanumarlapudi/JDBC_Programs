package in.ineuron.code;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import jdbc.util.jdbc_util;

public class Update_PreparedStatement {

	public static void main(String[] args) throws IOException, SQLException {
		
		Connection connection = null;
		PreparedStatement preparedstatement = null;
		Scanner scan = new Scanner(System.in);
		
		connection = jdbc_util.getJdbcConnection();
		
		if(connection != null) {
			String sqlQuery = "update student set name = ? where id = ?";
			preparedstatement = connection.prepareStatement(sqlQuery);
		}
		
		if(preparedstatement != null) {
			
			System.out.println("Please enter the name to set: ");
			String name = scan.next();
			
			System.out.println("Please enter the id to set: ");
			int id = scan.nextInt();
			
			preparedstatement.setString(1,name);
			preparedstatement.setInt(2, id);
			// execute the query
			int rowcount = preparedstatement.executeUpdate();
			System.out.println("No of rows updated is :: " + rowcount);
		}
		
		if(connection != null) {
			connection.close();
		}
		if(preparedstatement != null) {
			preparedstatement.close();
		}

	}

}
