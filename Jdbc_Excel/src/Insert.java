import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Insert {

	public static void main(String[] args) throws SQLException {
		
		String url = "jdbc:Excel:///C:\\Users\\HARISH\\Desktop";
		Connection connection = DriverManager.getConnection(url);
		
		// workbook.<sheetname> represents the table name
		String excelQuery = "insert into Jdbc_Excel.Harish values(?,?,?,?,?)";
		PreparedStatement pstmt = connection.prepareStatement(excelQuery);
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Please Enter the id: ");
		int i = scan.nextInt();
		
		System.out.println("Please Enter the name: ");
		String name = scan.next();
		
		System.out.println("Please Enter the age: ");
		int i1 = scan.nextInt();
		
		System.out.println("Please Enter the branch: ");
		String branch = scan.next();
		
		System.out.println("Please Enter the salary: ");
		int i2 = scan.nextInt();
		
	    pstmt.setInt(1, i);
	    pstmt.setNString(2, name);
	    pstmt.setInt(3, i1);
	    pstmt.setNString(4, branch);
	    pstmt.setInt(5, i2);
	    
	    int rowaffected = pstmt.executeUpdate();
	    
		System.out.println("the rows affetcted " + rowaffected );
		

	}

}
