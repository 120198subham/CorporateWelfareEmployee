import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class JavaMail {
	public static void sentmail(String recepient, String OTP) throws MessagingException {
		System.out.print("Prepring to send OTP to given email addresss\n");
		Properties properties = new Properties();//used to configure the properties for the mail
		/*
		 * mail.smtp.auth//an Autheniacation is needed for a email server will be defined by mail.smtp.auth
		 * mail.smtp.starttls.enable//this for tls ecryption
		 * host and port number for the email address 
		 * mail.smtp.host
		 * mail.smtp.port
		 * 
		 * */
		/*
		 * Warning: properties should be provided in only string values 
		 * 
		 * */
		properties.put("mail.smtp.auth", "true");//true indicated it requires a authentication 
		properties.put("mail.smtp.starttls.enable", "true");//true indicated tls encryption
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		//account details username and passowrd
		String myAccountEmail = "123csea@gmail.com";
		String myAccountPassword =  "abcd12345@";
		Session session = Session.getInstance(properties,new Authenticator() {
			/*
			 * Authentication for username and password
			 * 
			 * */
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myAccountEmail, myAccountPassword);
			}
		});
		Message message = prepareMessage(session, myAccountEmail, recepient, OTP);
		Transport.send(message);
		System.out.print("OTP to mail sent succesfully\n");
	}
	private static Message prepareMessage(Session session, String myAccountEmail, String recepient, String OTP) throws MessagingException {
		// TODO Auto-generated method stub
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject("Welcome to CorporateWelfareEmployee project");
			message.setText("Your opt is"+OTP);
			return message;
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
}