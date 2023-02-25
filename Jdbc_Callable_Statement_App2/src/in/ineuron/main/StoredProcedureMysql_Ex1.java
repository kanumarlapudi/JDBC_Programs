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
		 * DELIMITER $$
		 * 
		 * USE `octbatch`$$ DROP PROCEDURE IF EXISTS `P_GET_PRODUCT_BY_NAME`$$ CREATE
		 * DEFINER=`root`@`localhost` PROCEDURE `P_GET_PRODUCT_BY_NAME`(IN
		 * name1VARCHAR(20), IN name2 VARCHAR(20)) BEGIN SELECT pid,pname,price,qty FROM
		 * products WHERE pname IN (name1,name2); END$$
		 * 
		 * DELIMITER ;
		 *
		 */

		// Resources used
		Connection connection = null;
		CallableStatement cstmt = null;
		ResultSet resultSet = null;

		Scanner scanner = null;
		String prod1 = null;
		String prod2 = null;
		boolean flag = false;

		String storedProcedureCall = "{call Get_Product_Details_by_name(?,?)}";

		try {
			connection = Jdbc_Util.getJdbcConnection();
			if (connection != null)

				cstmt = connection.prepareCall(storedProcedureCall);

			scanner = new Scanner(System.in);
			if (scanner != null) {
				System.out.print("Enter the product name :: ");
				prod1 = scanner.next();

						System.out.print("Enter the product name :: ");
						prod2 = scanner.next();
			}

			// Setting the input values
			if (cstmt != null) {
				cstmt.setString(1, prod1);
						cstmt.setString(2, prod2);
			}

			// run the stored procedure
			if (cstmt != null) {
				cstmt.execute();
			}

			// retreive the result
			if (cstmt != null) {
				resultSet = cstmt.getResultSet();
			}

			// process the resultSet
			if (resultSet != null) {
				System.out.println("PID\tPNAME\tPCOST\tQTY");
				while (resultSet.next()) {
					System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3)
							+ "\t" + resultSet.getInt(4));
					flag = true;
				}
			}
			// Displaying the nature of the result
			if (flag) {
				System.out.println("Record available and displayed");
			} else {
				System.out.println("Record not available");
			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				Jdbc_Util.closeUp(connection, cstmt, resultSet);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			scanner.close();
		}
	}

}
