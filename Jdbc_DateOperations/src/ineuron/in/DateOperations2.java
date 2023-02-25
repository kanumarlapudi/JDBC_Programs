package ineuron.in;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateOperations2 {

	public static void main(String[] args) throws ParseException {
		
		//Read the input from the user
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the Date int the format dd-MM-yyyy : ");
		String sdate = scan.next();
		
		//convert the string to java.util.date format
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");   //simpledateformat is a helper class to convert string into util date
		java.util.Date udate = sdf.parse(sdate);    //here we uses help of parse method
		
		//convert java.util.date to java.sql.date
		long value = udate.getTime();
		java.sql.Date sqldate = new java.sql.Date(value);
		
		//printing all the 3 formats of date
		System.out.println("String date format is " + sdate);
		System.out.println("Util date format is " + udate);
		System.out.println("Sql date format is " + sqldate);
		
		
		//if we enter the date in the int the format of yyyy-MM-dd no need to convert the dates we can access directly
		System.out.println("Enter the Date int the format yyyy-MM-dd : ");
		String sdate1 = scan.next();
		
		java.sql.Date sqldate1 = java.sql.Date.valueOf(sdate1);
		System.out.println(sqldate1);
		
		scan.close();

	}

}
