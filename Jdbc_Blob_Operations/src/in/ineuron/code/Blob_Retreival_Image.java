package in.ineuron.code;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

import in.ineuron.util.Jdbc_Util;

public class Blob_Retreival_Image {

	/*
	 * apache jar link ::  https://mvnrepository.com/artifact/commons-io/commons-io/2.8.0
	 */
	public static void main(String[] args) throws IOException {
		
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
				String sqlQuery = "select id, name, image from image where id = ?";
				try {
					pstmt = connection.prepareStatement(sqlQuery);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(pstmt != null) {
				try {
					System.out.println("Please enter the id number: ");
					int id = scan.nextInt();
					
					// setting input values
					pstmt.setInt(1, id);
					
					// executing the query
					resultset = pstmt.executeQuery();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
				
			try {
				if(resultset.next()) {
						System.out.println("id\tname\timage");
						int iid = resultset.getInt(1);
						String name = resultset.getString(2);
						InputStream is = resultset.getBinaryStream(3);
						
						File f = new File("copied.jpg");
						FileOutputStream fos = new FileOutputStream(f);
						
					/*	First approach it is very slow 
						int i = is.read();
						while(i!=-1) {
							fos.write(i);
							i = is.read();
						}
						
					*/
						
					/* Second Approach it is fast but difficult to write	
						byte[] b = new byte[1024];
						while(is.read(b)>0) {
							fos.write(b);
						}
					*/
						
						// copying the data from is to fos
						//here using apache api to get easily
						
						IOUtils.copy(is, fos);
						
						fos.close();
						System.out.println(iid + "\t" + name + "\t" + f.getAbsolutePath());
					}
			} catch (SQLException | IOException e) {
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
