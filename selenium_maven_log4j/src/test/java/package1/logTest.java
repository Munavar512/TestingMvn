package package1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class logTest {

	public static void main(String[] args) {
		
		Logger log = LogManager.getLogger(logTest.class.getName()); //in parameter pass the class 
		
		log.debug("I am logging");
		
		log.info("Object is present"); 
		
		log.debug("Starting");
		
		if(0>2) {
			log.info("Object is present");
		}else {
			log.error("Object is not present");
		}
	}

}
