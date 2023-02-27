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

public class SavePoint1 {

	public static void main(String[] args) {
		Connection connection = null;
		Statement stmt = null;
		ResultSet resultset = null;
		Scanner scanner = null;
		ResultSet rs = null;
		
		try {
			
			connection = JdbcUtil.getJdbcConnection();
			
			if(connection!=null) {
				stmt = connection.createStatement();
				
				System.out.println("Transaction begins.....");
				
				connection.setAutoCommit(false);
				stmt.executeUpdate("insert into politicians(`name`,`party`) values('MODI','BJP')");
				stmt.executeUpdate("insert into politicians(`name`,`party`) values('KCR','TRS')");
				
				Savepoint sp = connection.setSavepoint();
				System.out.println("oop's something went wrong during insertion....");
				
				connection.rollback(sp);
				System.out.println("Operations are rolled back to savepoint....");
				connection.commit();
				
				System.out.println("Transaction done....");
			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}finally {
			try {
				JdbcUtil.cleanUp(connection, stmt, resultset, rs);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
