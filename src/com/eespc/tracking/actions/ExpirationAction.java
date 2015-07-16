package com.eespc.tracking.actions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.eespc.tracking.entity.ExpirationEntity;
import com.eespc.tracking.util.SqlUtil;
import com.eespc.tracking.util.UtilityObject;

// Referenced classes of package com.eespc.tracking.actions:
//           ControllerAction

public class ExpirationAction extends Action {
	private class Authenticator extends javax.mail.Authenticator {

		protected PasswordAuthentication getPasswordAuthentication() {
			return authentication;
		}

		private PasswordAuthentication authentication;
		final ExpirationAction this$0;

		public Authenticator() {
			super();
			this$0 = ExpirationAction.this;
			String s = "neeraj@eespc.com";
			String s1 = "neeraj17";
			authentication = new PasswordAuthentication(s, s1);
		}
	}

	public ExpirationAction() {
		ee = new ExpirationEntity();
		uo = new UtilityObject();
		mail_from = "";
		mail_to = "";
	}

	public ActionForward execute(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		// org.apache.commons.logging.Log log =
		// LogFactory.getLog(com.eespc.tracking.actions.ControllerAction.getName());
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		String s = (String) dynaactionform.get("methodToCall");
		if (s != null && s.equalsIgnoreCase("html"))
			return html(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (s != null && s.equalsIgnoreCase("xls"))
			return xls(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (s != null && s.equalsIgnoreCase("mail")) {
			return mail(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		} else {
			httpservletrequest.setAttribute("from", "xxx");
			httpservletrequest.setAttribute("to", "yyy");
			return actionmapping.findForward("success");
		}
	}

	public ActionForward html(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		String s = null;
		String s1 = null;
		s = ((String) dynaactionform.get("from")).trim();
		s1 = ((String) dynaactionform.get("to")).trim();
		System.out.println((String) dynaactionform.get("from"));
		System.out.println((new StringBuilder()).append("1")
				.append((String) dynaactionform.get("to")).toString());
		System.out.println(httpservletrequest.getParameter("from"));
		if (s != null && s1 != null) {
			if (s.equals("") && s1.equals("")) {
				httpservletrequest.setAttribute("from", "xxx");
				httpservletrequest.setAttribute("to", "yyy");
			} else {
				httpservletrequest.setAttribute("from",
						uo.getDateStringForDB(s, true));
				httpservletrequest.setAttribute("to",
						uo.getDateStringForDB(s1, true));
			}
		} else {
			httpservletrequest.setAttribute("from", "xxx");
			httpservletrequest.setAttribute("to", "yyy");
		}
		return actionmapping.findForward("success");
	}

	public ActionForward mail(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		mail_from = ((String) dynaactionform.get("from")).trim();
		mail_to = ((String) dynaactionform.get("to")).trim();
		try {
			run();
			httpservletrequest.setAttribute("MESSAGE", "Sucessfully Sent");
		} catch (Exception exception) {
			String s = (new StringBuilder()).append("Error in Send Mail:")
					.append(exception).toString();
			System.out.println(s);
			httpservletrequest.setAttribute("MESSAGE", s);
		}
		httpservletrequest.setAttribute("from", mail_from);
		httpservletrequest.setAttribute("to", mail_to);
		return actionmapping.findForward("success");
	}

	public ActionForward xls(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		String s = null;
		String s1 = null;
		s = ((String) dynaactionform.get("from")).trim();
		s1 = ((String) dynaactionform.get("to")).trim();
		System.out.println((new StringBuilder()).append("from").append(s)
				.append(s1).toString());
		String s2 = "";
		try {
			s2 = gettotal(s, s1);
		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("Exception:")
					.append(exception).toString());
		}
		httpservletrequest.setAttribute("total", s2);
		httpservletrequest.setAttribute("from", uo.convertYyyyMmDd2MmDdYyyy(s));
		httpservletrequest.setAttribute("to", uo.convertYyyyMmDd2MmDdYyyy(s1));
		return actionmapping.findForward("successxls");
	}

	private Session getSession() {
		Authenticator authenticator = new Authenticator();
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.submitter", authenticator
				.getPasswordAuthentication().getUserName());
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.host", "mail.eespc.com");
		properties.setProperty("mail.smtp.port", "2525");
		return Session.getInstance(properties, authenticator);
	}

	private void run() throws MessagingException {
		MimeMessage mimemessage = new MimeMessage(getSession());
		InternetAddress ainternetaddress[] = {
				new InternetAddress("gsenthil83@yahoo.co.in"),
				new InternetAddress("sdathi83@gmail.com"),
				new InternetAddress("sdathi83@gmail.com") };
		mimemessage.setRecipients(javax.mail.Message.RecipientType.TO,
				ainternetaddress);
		mimemessage.addFrom(new InternetAddress[] { new InternetAddress(
				"neeraj@eespc.com") });
		mimemessage.setSubject("EESPC Software Tracking Expiration Report");
		MimeMultipart mimemultipart = new MimeMultipart("related");
		MimeBodyPart mimebodypart = new MimeBodyPart();
		String s = (new StringBuilder())
				.append("This is Permit Expiration Report for Date Between From :")
				.append(uo.convertYyyyMmDd2MmDdYyyy(mail_from)).append(" To :")
				.append(uo.convertYyyyMmDd2MmDdYyyy(mail_to)).toString();
		mimebodypart.setContent(s, "text/html");
		mimemultipart.addBodyPart(mimebodypart);
		mimemessage.setContent(mimemultipart);
		File file = new File("report.xls");
		Object obj = null;
		Object obj1 = null;
		PrintWriter printwriter = null;
		try {
			file.createNewFile();
			FileWriter filewriter = new FileWriter(file);
			BufferedWriter bufferedwriter = new BufferedWriter(filewriter);
			printwriter = new PrintWriter(bufferedwriter, false);
			printwriter.println("<html>");
			printwriter.println("<body bgcolor=\"white\">");
			printwriter
					.println("<table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: collapse\" bordercolor=\"#111111\" width=\"1200\" id=\"AutoNumber1\">");
			printwriter
					.println((new StringBuilder())
							.append("<tr><td width=\"83%\"  colspan=\"5\"><font size=\"5\">Permit Expiration Report / Date Between From :")
							.append(uo.convertYyyyMmDd2MmDdYyyy(mail_from))
							.append(" To :")
							.append(uo.convertYyyyMmDd2MmDdYyyy(mail_to))
							.append("</font></td></tr>").toString());
			printwriter
					.println("<tr><td width=\"21%\" bgcolor=\"#006699\"><font color=\"#FFFFFF\">Facility (Client Name)</font></td><td width=\"17%\" bgcolor=\"#006699\"><font color=\"#FFFFFF\">Building Name</font></td><td width=\"19%\" bgcolor=\"#006699\"><font color=\"#FFFFFF\">Source Type</font></td><td width=\"9%\" bgcolor=\"#006699\"><font color=\"#FFFFFF\">Agency</font></td><td width=\"100\" bgcolor=\"#006699\"><font color=\"#FFFFFF\">Expiration Date</font></td><td width=\"19%\" bgcolor=\"#006699\"><font color=\"#FFFFFF\">Notes</font></td></tr>");
			printwriter.println(gettotal(mail_from, mail_to));
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

	/*
	 * public String gettotal(String s, String s1) throws Exception { String s2
	 * = ""; try { SqlUtil sqlutil = new SqlUtil(); Connection connection =
	 * sqlutil.getConnection(); PreparedStatement preparedstatement =
	 * connection.
	 * prepareStatement("select clientname,facilityid from facility"); ResultSet
	 * resultset = preparedstatement.executeQuery(); do { if(!resultset.next())
	 * break; String facid=resultset.getString("facilityid"); String st
	 * =ee.gethtml(facid, s, s1); String s3=ee.getStatehtml(facid, s,
	 * s1)+ee.getcertificateoffitnessrenewaldatehtml(facid, s,
	 * s1)+ee.getcertificateoffitnesshtml(facid, s,
	 * s1)+ee.getStatenextproposalhtml(facid, s, s1)+st;
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * if(!s3.equals("")) { String s4 = (new StringBuilder()).append(
	 * "<tr><td width=\"21%\" bordercolor=\"#FF0000\" bgcolor=\"#0066CC\"><p align=\"left\"><span style=\"font-variant: small-caps\"><font size=\"1\" color=\"#FFFFFF\">"
	 * ).append(resultset.getString("Clientname")).append(
	 * "</font></span></td><td width=\"17%\" bgcolor=\"#E4E8E3\">&nbsp;</td><td width=\"19%\" bgcolor=\"#E4E8E3\">&nbsp;</td><td width=\"9%\" bgcolor=\"#E4E8E3\">&nbsp;</td><td width=\"17%\" bgcolor=\"#E4E8E3\">&nbsp;</td></tr>"
	 * ).toString(); s2 = (new
	 * StringBuilder()).append(s2).append(s4).append(s3).toString(); } }
	 * while(true); connection.close(); resultset.close(); } catch(Exception
	 * exception) { System.out.println(exception); } return s2; }
	 */
	public String gettotal(String s, String s1) throws Exception {
		String s2 = "";
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;
		try {
			SqlUtil sqlutil = new SqlUtil();
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("select clientname,facilityid from facility");
			resultset = preparedstatement.executeQuery();
			do {
				if (!resultset.next())
					break;
				String s3 = ee
						.gethtml(resultset.getString("facilityid"), s, s1);
				if (!s3.equals("")) {
					String s4 = (new StringBuilder())
							.append("<tr><td width=\"21%\" bordercolor=\"#FF0000\" bgcolor=\"#0066CC\"><p align=\"left\"><span style=\"font-variant: small-caps\"><font size=\"1\" color=\"#FFFFFF\">")
							.append(resultset.getString("Clientname"))
							.append("</font></span></td><td width=\"17%\" bgcolor=\"#E4E8E3\">&nbsp;</td><td width=\"19%\" bgcolor=\"#E4E8E3\">&nbsp;</td><td width=\"9%\" bgcolor=\"#E4E8E3\">&nbsp;</td><td width=\"17%\" bgcolor=\"#E4E8E3\">&nbsp;</td><td width=\"17%\" bgcolor=\"#E4E8E3\">&nbsp;</td></tr>")
							.toString();
					s2 = (new StringBuilder()).append(s2).append(s4).append(s3)
							.toString();
				}
			} while (true);

		} catch (Exception exception) {
			System.out.println(exception);
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		return s2;
	}

	ExpirationEntity ee;
	UtilityObject uo;
	String mail_from;
	String mail_to;
}