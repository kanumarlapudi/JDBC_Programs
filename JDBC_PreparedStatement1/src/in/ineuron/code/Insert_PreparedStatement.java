package in.ineuron.code;

import java.io.IOException;
import java.sql.SQLException;

import jdbc.util.jdbc_util;

import java.sql.Connection;
import java.sql.PreparedStatement;


public class Insert_PreparedStatement {

	public static void main(String[] args) throws IOException, SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = (Connection) jdbc_util.getJdbcConnection();
			
			String sqlInsertQuery = "insert into student(`id`,`name`)values(?,?)";
			if (connection != null)
				pstmt = connection.prepareStatement(sqlInsertQuery);

			if (pstmt != null) {

				// use precompiled query to set the values
				pstmt.setInt(1,6);
				pstmt.setString(2,"priya");
				
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
				System.out.println("Closing the resource...");
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}


	}
}
