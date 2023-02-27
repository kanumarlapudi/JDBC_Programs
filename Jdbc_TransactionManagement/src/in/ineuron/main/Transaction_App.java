package in.ineuron.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import in.ineuron.util.JdbcUtil;

public class Transaction_App {

	public static void main(String[] args) {
		Connection connection = null;
		Statement stmt = null;
		ResultSet resultset = null;
		Scanner scanner = null;
		ResultSet rs = null;
		
		try {
			System.out.println("Data before transaction...");
			connection = JdbcUtil.getJdbcConnection();
			
			if(connection != null) {
				stmt = connection.createStatement();
			}
			
			if(stmt!=null) {
				String sqlquery = "select name, balance from accounts";
				resultset = stmt.executeQuery(sqlquery);
			}
			
			if(resultset != null) {
				System.out.println("name\tbalance");
				while(resultset.next()) {
					System.out.println(resultset.getNString(1) + "\t" + resultset.getInt(2));
				}
			}
			System.out.println("\nTransaction Begins");
			connection.setAutoCommit(false);
			
			// Prepare the operations as a single unit
			stmt.executeUpdate("update accounts set balance = balance-2000 where name = 'Sachin'");
			stmt.executeLargeUpdate("update accounts set balance = balance+2000 where name = 'Dhoni'");
			System.out.print("Can u please confirm the transaction  of 2000INR...[YES/NO]");
			scanner = new Scanner(System.in);
			String option = scanner.next();
			if (option.equalsIgnoreCase("yes")) {
				connection.commit();
			} else {
				connection.rollback();
			}
			
			System.out.println("\nData after transaction....");
			rs = stmt.executeQuery("select name,balance from accounts");
			if (rs != null) {
				System.out.println("NAME\tBALANCE");
				while (rs.next()) {
					System.out.println(rs.getString(1) + "\t" + rs.getInt(2));
				}
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
