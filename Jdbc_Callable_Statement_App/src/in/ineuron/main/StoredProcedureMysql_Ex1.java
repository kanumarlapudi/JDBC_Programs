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
	
		/**
		 * 
		DELIMITER $$
		     CREATE DEFINER=`root`@`localhost` PROCEDURE `Get_Product_Details_by_id`(in id int, out name varchar(20), out price int, out quantity int)
        BEGIN
             select pname, pprice, pquantity into name, price, quantity from product where pid = id;
        END

		DELIMITER ;
		 * 
		 * 
		 *
		 */
		
		
		Connection connection = null;
		CallableStatement cstmt = null;
		ResultSet resultset = null;
		Scanner scan = new Scanner(System.in);
		
		try {
			connection = Jdbc_Util.getJdbcConnection();
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		
		if(connection != null) {
			String sqlquery = "{call Get_Product_Details_by_id (?, ?, ?, ?)}";
			try {
				cstmt = connection.prepareCall(sqlquery);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(cstmt != null) {
			try {
				System.out.println("Please enter the id: ");
				int id = scan.nextInt();
				// Setting the input values
				cstmt.setInt(1, id);
				
				// Setting the Datetype of output values
				cstmt.registerOutParameter(2, Types.VARCHAR);
				cstmt.registerOutParameter(3, Types.INTEGER);
				cstmt.registerOutParameter(4, Types.INTEGER);
				
				// run the stored procedure
				cstmt.execute();
				
				//retreive the result
				System.out.println("Product Name is :: " + cstmt.getString(2));
				System.out.println("Product Cost is :: " + cstmt.getInt(3));
				System.out.println("Product Quantity  is :: " + cstmt.getInt(4));
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					Jdbc_Util.closeUp(connection, cstmt, resultset);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    
			}
		}
	}

}
