package in.ineuron.code;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import jdbc.util.jdbc_util;

public class InsertDynamically_PreparedStatement {

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		Scanner scanner = null;

		try {
			connection = jdbc_util.getJdbcConnection();

			String sqlInsertQuery = "insert into student('id',`name`) values(?,?)";
			if (connection != null)
				pstmt = connection.prepareStatement(sqlInsertQuery);

			if (pstmt != null) {

				scanner = new Scanner(System.in);

				System.out.print("Enter the id of the student :: ");
			    int id = scanner.nextInt();

				System.out.print("Enter the name of the student :: ");
				String name = scanner.next();

				// use precompiled query to set the values
				pstmt.setInt(1, id);
				pstmt.setString(2, name);
		
				System.out.println(sqlInsertQuery);

				// execute the query
				int rowCount = pstmt.executeUpdate();
				System.out.println("No of rows updated is :: " + rowCount);
			}

		} catch (IOException ie) {
			ie.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				jdbc_util.cleanUp(connection, pstmt, null);
				scanner.close();
				System.out.println("Closing the resource...");
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

}
