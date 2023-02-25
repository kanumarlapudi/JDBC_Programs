package ineuron.in;

public class DateOperations1 {

	public static void main(String[] args) {
		
		//java.util.Date -> used to store date and time information
		java.util.Date udate = new java.util.Date();
		System.out.println(udate);

		long value = udate.getTime();     //get time gives o/p in long
		System.out.println("Information about date in milliseconds: " + value);
		
		//java.util.Date -> used to store date and time information
		java.sql.Date sqldate = new java.sql.Date(value);
		System.out.println(sqldate);
	}
}
