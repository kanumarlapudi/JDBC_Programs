package in.ineuron.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

import in.ineuron.util.Jdbc_Util;

public class StoredProcedureMysql_Ex1 {

	public static void main(String[] args) throws FileNotFoundException {

		Connection connection = null;
		PreparedStatement pstmt= null;
		Scanner scan = null;
		
		try {
			connection = Jdbc_Util.getJdbcConnection();
			
			if(connection != null) {
				String sqlquery = "insert into employees(name, age, address) values(?,?,?)";
				pstmt = connection.prepareStatement(sqlquery);
			}
			
			if(pstmt!= null) {
				scan = new Scanner(System.in);
				while(true) {
					System.out.println("Please Enter the name of employee: ");
					String name = scan.next();
					
					System.out.println("Please Enter the age of employee: ");
					int age = scan.nextInt();
					
					System.out.println("Please Enter the address of employee: ");
					String address = scan.next();
					
					//setting the values
					pstmt.setNString(1, name);
					pstmt.setInt(2, age);
					pstmt.setString(3, address);
					
					pstmt.addBatch();
					
					System.out.print("Do u want to insert one more record[YES/NO]:: ");
					String option = scan.next();

					if (option.equalsIgnoreCase("no")) {
						break;
					}
				}
				
				pstmt.executeBatch();
				
				System.out.println("Records inserted succesfully....");

			}
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				Jdbc_Util.closeUp(connection, pstmt);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}

}
