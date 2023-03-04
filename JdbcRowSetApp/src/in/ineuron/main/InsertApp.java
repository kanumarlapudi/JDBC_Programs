package in.ineuron.main;

import java.util.Scanner;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class InsertApp {

	public static void main(String[] args) throws Exception {

		RowSetFactory rsf = RowSetProvider.newFactory();
		JdbcRowSet jrs = rsf.createJdbcRowSet();// same as resultSet(connected),but it is scrollable and updatable.

		// setting url,username,password and getting connection object..
		jrs.setUrl("jdbc:mysql:///ineuron");
		jrs.setUsername("root");
		jrs.setPassword("Harish16#");

		// setting a command for execution
		jrs.setCommand("select id,name,age,address,salary from employees");
		jrs.execute();

		Scanner scanner = new Scanner(System.in);
		jrs.moveToInsertRow();
		while (true) {
			System.out.print("Enter the name::");
			String name = scanner.next();

			System.out.print("Enter the age::");
			Integer age = scanner.nextInt();

			System.out.print("Enter the address::");
			String address = scanner.next();

			System.out.print("Enter the salary::");
			Integer salary = scanner.nextInt();
			
			jrs.updateString(2, name);
			jrs.updateInt(3, age);
			jrs.updateString(4, address);
			jrs.updateInt(5, salary);

			jrs.insertRow();

			System.out.println("Record inserted succesfully.....");
			System.out.print("Do u want to insert one more record [Yes/No]::  ");
			String option = scanner.next();
			if (option.equalsIgnoreCase("No")) {
				break;
			}

		}
		scanner.close();
		jrs.close();

	}
}
