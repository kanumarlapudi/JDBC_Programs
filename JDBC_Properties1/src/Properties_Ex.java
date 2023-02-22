import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Properties_Ex {

	
		public static void main(String[] args) throws IOException {
			FileInputStream fis = new FileInputStream("C:\\Users\\HARISH\\eclipse-workspace\\JDBC_Properties1\\src\\Properties1");
			Properties p = new Properties();
			p.load(fis);
	        
			String url = p.getProperty("url");
			String username = p.getProperty("username");
			String password = p.getProperty("password");
			
			System.out.println("URL IS ::"+url);
			System.out.println("USERNAME IS ::"+username);
			System.out.println("PWD IS ::"+password);
		}

}
