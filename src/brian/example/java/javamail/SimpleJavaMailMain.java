package brian.example.java.javamail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SimpleJavaMailMain {

	public static void main(String[] args) {
		
		// Setup Properties
		Properties props = new Properties();
//		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", "smtp server domain/IP");
		props.put("mail.smtp.port", "25 or 465 or 587 ");	// smtp | SSL | TLS
//		props.put("mail.smtp.auth", "true");				// Depending on server config may/may not need it
//	    props.put("mail.smtp.socketFactory.port", "465");	// For SSL
//	    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLCoketFactory");	// For SSL
//	    props.put("mail.smtp.starttls.enable", "true");  	// For TLS - Depending on server config may/may not need it
		
		Session session = Session.getDefaultInstance(props, new Authenticator() {
										@Override
										protected PasswordAuthentication getPasswordAuthentication() {
											return new PasswordAuthentication("your user id","your password");
										}
									});
		
		try {
			// Message (Email subject/contents/...)
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(" From email address "));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("To email addresses separated by comma"));
			msg.setSubject("Email Subject");
		
			// Simple contents
			String contents = "This is <b>java mail</b> test";
			// msg.setText(contents);					
			
			// or complicated contents (can add attachement as well )
			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			mimeBodyPart.setContent( contents, "text/html");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(mimeBodyPart);
			msg.setContent(multipart);
			
			// Send
			Transport.send(msg);
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
