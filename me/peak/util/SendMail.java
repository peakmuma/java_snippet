package me.peak.util;




import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMail {

	public static void main(String[] args) {
		String host = "mail.shangdejigou.cn";
		String from = "gaolei@shangdejigou.cn";
		String to = "rdtest4@shangdejigou.cn";
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host",host);
//		properties.setProperty("mail.port","25");
		properties.setProperty("mail.smtp.auth","true");
		Session session = Session.getDefaultInstance(properties,
				new Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("gaolei@shangdejigou.cn", "gaolei");
					}
				}
		);
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("mail from java");
			message.setText("the content");
			session.setDebug(true);
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}


}
