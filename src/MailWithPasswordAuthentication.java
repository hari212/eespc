// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 3/31/2014 11:04:29 PM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   MailWithPasswordAuthentication.java

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.eespc.tracking.entity.ExpirationEntity;

public class MailWithPasswordAuthentication {
	private class Authenticator extends javax.mail.Authenticator {

		protected PasswordAuthentication getPasswordAuthentication() {
			return authentication;
		}

		private PasswordAuthentication authentication;

		public Authenticator() {
			String s = "clientdeadline@eespc.com";
			String s1 = "deadline";
			authentication = new PasswordAuthentication(s, s1);
		}
	}

	public MailWithPasswordAuthentication() {
		ee = new ExpirationEntity();
	}

	public static void main(String args[]) throws MessagingException {
		(new MailWithPasswordAuthentication()).run();
	}

	private void run() throws MessagingException {
		MimeMessage mimemessage = new MimeMessage(getSession());
		mimemessage.addRecipient(
				javax.mail.internet.MimeMessage.RecipientType.TO,
				new InternetAddress("gsenthil83@yahoo.co.in"));
		mimemessage.addFrom(new InternetAddress[] { new InternetAddress(
				"clientdeadline@eespc.com") });
		mimemessage.setSubject("Expiration Report");
		MimeMultipart mimemultipart = new MimeMultipart("related");
		MimeBodyPart mimebodypart = new MimeBodyPart();
		String s = "This is Report For Permit";
		mimebodypart.setContent(s, "text/html");
		mimemultipart.addBodyPart(mimebodypart);
		mimemessage.setContent(mimemultipart);
		File file = new File("test.xls");
		Object obj = null;
		Object obj1 = null;
		PrintWriter printwriter = null;
		try {
			file.createNewFile();
			FileWriter filewriter = new FileWriter(file);
			BufferedWriter bufferedwriter = new BufferedWriter(filewriter);
			printwriter = new PrintWriter(bufferedwriter, false);
			String s1 = "";
			String s2 = "ABCDEFFGHIJKLMNOPQRSTUVWXYZ";
			for (int i = 0; i < 10; i++)
				s1 = s1 + s2;

			printwriter.println(s1);
			printwriter.println("<html>");
			printwriter.println("<body bgcolor=\"red\">");
			printwriter.println("Empty response");
			printwriter
					.println("<table border=\"1\" ><tr><td >good</td><td >good</td><td >good</td></tr></table>");
			printwriter
					.println("<table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: collapse\" bordercolor=\"#111111\" width=\"1200\" id=\"AutoNumber1\"><tr><td width=\"21%\" bgcolor=\"#006699\"><font color=\"#FFFFFF\">Facility (Client Name)</font></td><td width=\"17%\" bgcolor=\"#006699\"><font color=\"#FFFFFF\">Building Name</font></td><td width=\"19%\" bgcolor=\"#006699\"><font color=\"#FFFFFF\">Resource Type</font></td><td width=\"9%\" bgcolor=\"#006699\"><font color=\"#FFFFFF\">Permit Name</font></td><td width=\"17%\" bgcolor=\"#006699\"><font color=\"#FFFFFF\">Test Date</font></td><td width=\"100\" bgcolor=\"#006699\"><font color=\"#FFFFFF\">Expiration Date</font></td></tr>");
			printwriter.println(ee.gethtml("1", "1997-01-01", "2010-01-01"));
			printwriter.println("</table>");
			printwriter.println("</body>");
			printwriter.println("</html>");
		} catch (Throwable throwable) {
		}
		printwriter.close();
		MimeBodyPart mimebodypart1 = new MimeBodyPart();
		FileDataSource filedatasource = new FileDataSource(file);
		mimebodypart1.setDataHandler(new DataHandler(filedatasource));
		mimebodypart1.setFileName(filedatasource.getName());
		mimemultipart.addBodyPart(mimebodypart1);
		mimemessage.setContent(mimemultipart);
		Transport.send(mimemessage);
	}

	private Session getSession() {
		Authenticator authenticator = new Authenticator();
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.submitter", authenticator
				.getPasswordAuthentication().getUserName());
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.host", "mail.eespc.com");
		properties.setProperty("mail.smtp.port", "25");
		return Session.getInstance(properties, authenticator);
	}

	ExpirationEntity ee;
}
