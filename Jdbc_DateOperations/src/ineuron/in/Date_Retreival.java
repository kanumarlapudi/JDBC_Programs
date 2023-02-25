package ineuron.in;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import ineuron.util.jdbc_util;

public class Date_Retreival {

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement psmt = null;
		ResultSet resultset = null;
		
		try {
			connection = jdbc_util.getJdbcConnection();
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(connection != null) {
			
			try {
				String sqlquery = "select name,dob,dom,id from user where id = ?";
				psmt = connection.prepareStatement(sqlquery);
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		if(psmt != null) {
			try {
				psmt.setInt(1, 1);
				resultset = psmt.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(resultset != null) {
			try {
				if(resultset.next()) {
					System.out.println("name \t dob \t\t dom \t\t id");
					String name = resultset.getNString(1);
					java.sql.Date dob = resultset.getDate(2);
					java.sql.Date dom = resultset.getDate(3);
					int id = resultset.getInt(4);
					
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					String strdob = sdf.format(dob);
					String strdom = sdf.format(dom);
					
					System.out.println(name + "\t" + strdob + "\t" + strdom + "\t" + id );
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					jdbc_util.cleanUp(connection, psmt, resultset);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
