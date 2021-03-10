package Main;


import java.io.IOException;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import Framework.MailAPI;



public class ReadRandomMail {

	
	private static final Logger log = LogManager.getLogger(ReadRandomMail.class.getName());
	
	public static void main(String args[]) throws IOException {
		
		try {
		MailAPI ma = new MailAPI();
		ma.readMailItem(20);
		}
		
		catch(Exception e){
		  log.info("Failed to read mail item");
		  e.printStackTrace();
		}
	
	
	
	}	
}