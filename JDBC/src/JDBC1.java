import java.sql.*;

public class JDBC1 {

	public static void main(String[] args) {
		Connection connection = null; //connection variable is created and connection is object so we are giving null
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			
			//Step1. Load and register the Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver class loaded succesfully");
			
			//Step2. Establish the Connection with database
			String url = "jdbc:mysql://localhost:3306/ineuron";
			String username = "root";
			String password = "Harish16#";
			connection = DriverManager.getConnection(url, username, password);    //here the result can store in connection variable only
			System.out.println("Connection is established");
			System.out.println("The implement class name is "+ connection.getClass().getName());
			
			//Step3. Create statement Object and send the query
			String sqlSelectQuery = "select id,name,branch from student";
			statement = connection.createStatement();
			System.out.println("The implementation class name is ::" + statement.getClass().getName());
			resultSet =statement.executeQuery(sqlSelectQuery);
			System.out.println("The implementation class name is ::"+resultSet.getClass().getName());
			System.out.println();
			System.out.println("ID\tNAME\tBRANCH");
			
			
			//Step4. Process the resultSet
            while(resultSet.next()) {
            	Integer id = resultSet.getInt(1);
            	String name = resultSet.getString(2);
            	String branch = resultSet.getString(3);
            	System.out.println(id+"\t"+name+"\t"+branch);
            }

		}
		
		//step-5 handling the exceptions
		catch(ClassNotFoundException ce) {
			ce.printStackTrace();
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		finally{
			//closing the resources
			if (connection!=null)
			{
			   try
			   {
			       connection.close();
			       System.out.println();
			        System.out.println("Connection closed...");
			   }
			   catch (SQLException se){
			    se.printStackTrace();
			   }
	    	}
		}		
	}
}
