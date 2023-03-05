import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Select_App {

	public static void main(String[] args) throws SQLException {
		String url = "jdbc:Excel:///C:\\\\Users\\\\HARISH\\\\Desktop";
		Connection connection = DriverManager.getConnection(url);
		
		// workbook.<sheetname> represents the table name
		String excelQuery = "select * from Jdbc_Excel.Harish";
		PreparedStatement pstmt = connection.prepareStatement(excelQuery);
		
		ResultSet resultSet = pstmt.executeQuery();
		System.out.println("id\tname\tage\tbranch\tsalary");
		while(resultSet.next()) {
			System.out.println(resultSet.getInt(1) + "\t" + resultSet.getNString(2) + "\t" + resultSet.getInt(3) + "\t" + resultSet.getNString(4) + "\t" + resultSet.getInt(5));
		}
	}

}
