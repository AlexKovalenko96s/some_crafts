package ua.kas.main;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Main {

	public static void main(String[] args) throws AddressException, MessagingException {
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.yandex.ru");
		prop.put("mail.smtp.socketFactory.port", 465);
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.port", 465);

		Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("VKspy.clientBot@yandex.ru", "1qw21qw2");
			}
		});

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("VKspy.clientBot@yandex.ru"));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("VKspy.serverBot@yandex.ru"));
		message.setSubject("test");
		message.setText("test test");

		Transport.send(message);
	}
}
