package Framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;

import javax.mail.Session;
import javax.mail.Store;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MailAPI {
	
	
	private String m_decodedPassString;
	private String m_username;
	private Properties m_props;
	
	
	private static final Logger log = LogManager.getLogger(MailAPI.class.getName());
	public MailAPI() throws FileNotFoundException, IOException {
		//load properties
		m_props = new Properties();
		m_props.load(new FileInputStream(new File(GeneralUtility.getUserDir(System.getProperty("user.dir"))+"\\src\\smtp.properties")));
		
		
		//get user+pass
		byte[] b_decoded_pass = Base64.getDecoder().decode(m_props.getProperty("mail.smtp.pass"));
		m_decodedPassString = new String(b_decoded_pass);
		m_username = m_props.getProperty("mail.smtp.user");
	}
	
	public MailAPI(String propFilePath) throws FileNotFoundException, IOException {
		//load properties
		m_props = new Properties();
		m_props.load(new FileInputStream(new File(propFilePath)));
		
		//get user+pass
		byte[] b_decoded_pass = Base64.getDecoder().decode(m_props.getProperty("mail.smtp.pass"));
		m_decodedPassString = new String(b_decoded_pass);
		m_username = m_props.getProperty("mail.smtp.user");
	}
	
	public void readMailItem(int itemNum) throws Exception {
		//connect to store
		Session session = Session.getDefaultInstance(m_props, null);
		Store store = session.getStore("imaps");			
		store.connect("smtp.gmail.com", m_username,m_decodedPassString);			
		System.out.println("Connected to mailbox successfully");
		
		//read a certain mail item from inbox 
		Folder inbox = store.getFolder("Inbox");
		inbox.open(Folder.READ_ONLY);
		Message messages[] = inbox.getMessages();
		
		/** if we wanna go random we can do it like this:
		int messageCount = inbox.getMessageCount(); 						
		int n = (int) (Math.random() * inbox.getMessageCount());**/
		
		//print subject and contents
		log.info("Subject is: " + messages[itemNum].getSubject());
		log.info("Contents is: " + GeneralUtility.getTextFromMessage(messages[itemNum]));
		
		//close the resources
		inbox.close();
		store.close();
		
		
	}
	
	
	
}
