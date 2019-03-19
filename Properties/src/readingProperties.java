import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class readingProperties {

	public static void main(String[] args) throws IOException {
	

		Properties pro = new Properties();
		FileInputStream ip = new FileInputStream("/Users/mina/Selenium/testing-maven/Properties/src/employee.properties");
		pro.load(ip);
		String name = pro.getProperty("name");
		System.out.println("name");
		System.out.println(pro.getProperty("age"));

	}

}
