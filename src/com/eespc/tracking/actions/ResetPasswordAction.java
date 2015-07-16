package com.eespc.tracking.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eespc.tracking.util.SqlUtil;
import com.eespc.tracking.util.UtilityObject;

// Referenced classes of package com.eespc.tracking.actions:
//         ControllerAction

public class ResetPasswordAction extends HttpServlet {
	private class Authenticator extends javax.mail.Authenticator {
		protected PasswordAuthentication getPasswordAuthentication() {
			return authentication;
		}

		private PasswordAuthentication authentication;
		final ResetPasswordAction this$0;

		public Authenticator() {
			super();
			this$0 = ResetPasswordAction.this;
			String s = "keerthi@eespc.com";
			String s1 = "keerthi123456";
			authentication = new PasswordAuthentication(s, s1);
		}
	}

	public ResetPasswordAction() {
		// ee = new ExpirationEntity();
		uo = new UtilityObject();
		userid = "";
		mail_to = "";
	}

	public void doPost(HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws ServletException,
			IOException {
		userid = (String) httpservletrequest.getParameter("loginid");
		String sendcommand = gettotal(userid);
		PrintWriter out = httpservletresponse.getWriter();
		httpservletresponse.setContentType("text/html");
		if (sendcommand.equals("")) {
			out.println("Login Id Does Not Match.:- <font face=\"Verdana\" size=\"2\"><a href=\"/eespc\">Login Page</a></font>");
		} else {
			try {
				run();
				out.println("Your Password has been Sent to Your Mail Id:"
						+ mail_to
						+ " :- <font face=\"Verdana\" size=\"2\"><a href=\"/eespc\">Login Page</a></font>");
			} catch (Exception exception) {
				String s = (new StringBuilder()).append("Error in Send Mail:")
						.append(exception).toString();
				System.out.println(s);
				httpservletrequest.setAttribute("MESSAGE", s);
			}
		}
	}

	private Session getSession() {
		Authenticator authenticator = new Authenticator();
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.submitter", authenticator
				.getPasswordAuthentication().getUserName());
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.host", "smtp.eespc.com");
		properties.setProperty("mail.smtp.port", "25");
		return Session.getInstance(properties, authenticator);
	}

	private void run() throws MessagingException {
		MimeMessage mimemessage = new MimeMessage(getSession());
		InternetAddress ainternetaddress[] = { new InternetAddress(mail_to) };
		String sendcommand = gettotal(userid);
		mimemessage.setRecipients(javax.mail.Message.RecipientType.TO,
				ainternetaddress);
		mimemessage.addFrom(new InternetAddress[] { new InternetAddress(
				"keerthi@eespc.com") });
		mimemessage.setSubject("EESPC Software Tracking Expiration Report");
		MimeMultipart mimemultipart = new MimeMultipart("related");
		MimeBodyPart mimebodypart = new MimeBodyPart();
		mimebodypart.setContent(sendcommand, "text/html");
		mimemultipart.addBodyPart(mimebodypart);
		mimemessage.setContent(mimemultipart);
		mimemessage.setContent(mimemultipart);
		Transport.send(mimemessage);
	}

	public String gettotal(String user_id) {
		String s2 = "";
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;
		try {
			SqlUtil sqlutil = new SqlUtil();
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("select a.USERNAME,a.PASSWORD,b.EMAIL from (user a,person b) where a.personid=b.personid and a.username=?");
			preparedstatement.setString(1, userid);
			resultset = preparedstatement.executeQuery();
			while (resultset.next()) {
				s2 = "Your UserName: " + resultset.getString("USERNAME")
						+ " and Your Password: "
						+ resultset.getString("PASSWORD");
				mail_to = resultset.getString("EMAIL");
			}
			connection.close();
			resultset.close();
		} catch (Exception exception) {
			System.out.println(exception);
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		return s2;
	}

	// ExpirationEntity ee;
	UtilityObject uo;
	String userid;
	String mail_to;
}