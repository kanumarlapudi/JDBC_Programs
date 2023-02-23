package in.ineuron.code;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import in.ineuron.util.Jdbc_Util;

public class Blob_InsertionVideo {

	public static void main(String[] args) throws FileNotFoundException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultset = null;
		Scanner scan = new Scanner(System.in);
		
		try {
			connection = Jdbc_Util.getJdbcConnection();
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(connection != null) {
			String sqlQuery = "insert into image(name,image) values(?, ?)";
			try {
				pstmt = connection.prepareStatement(sqlQuery);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(pstmt != null) {
			try {
				System.out.println("Please enter the name: ");
				String name = scan.next();
				System.out.println("Please Enter the location of video: ");
				String imgloc = scan.next();
				
				pstmt.setNString(1, name);
				
				FileInputStream fis = new FileInputStream(imgloc);
				pstmt.setBinaryStream(2, fis);
				
				int rowAffected = pstmt.executeUpdate();
				System.out.println("No of rows inserted inserted is :: " + rowAffected);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					Jdbc_Util.closeUp(connection, pstmt, resultset);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		}
	}
}
