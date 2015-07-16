// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 3/30/2014 6:10:22 PM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   exhi18.java

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Test.TestFacility;

import com.eespc.tracking.util.SqlUtil;

public class exhi18 extends HttpServlet {

	public exhi18() {
	}

	public void service(HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		SqlUtil sqlutil = new SqlUtil();
		Object obj = null;
		byte abyte0[] = null;
		try {
			Connection connection = sqlutil.getConnection();
			PreparedStatement preparedstatement = connection
					.prepareStatement("select * from exhibitword where facilityid=?");
			preparedstatement.setString(1,
					String.valueOf(TestFacility.getCurrentFacilityID()));
			for (ResultSet resultset = preparedstatement.executeQuery(); resultset
					.next();) {
				byte abyte1[] = new byte[6000];
				boolean flag = false;
				InputStream inputstream = resultset.getBinaryStream("file");
				abyte0 = readData(inputstream);
			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder())
					.append("Connection Exception : ").append(exception)
					.toString());
		}
		if (abyte0 == null) {
			httpservletresponse.setContentType("text/html");
			PrintWriter printwriter = httpservletresponse.getWriter();
			printwriter.println("<html>");
			printwriter.println("<body bgcolor=\"white\">");
			printwriter.println("<span class=\"bold\">Empty response.</span>");
			printwriter.println("</body>");
			printwriter.println("</html>");
		} else {
			httpservletresponse.setContentType("application/msword");
			httpservletresponse.setContentLength(abyte0.length);
			ServletOutputStream servletoutputstream = httpservletresponse
					.getOutputStream();
			servletoutputstream.write(abyte0, 0, abyte0.length);
			servletoutputstream.flush();
			servletoutputstream.close();
		}
	}

	private byte[] readData(InputStream inputstream) {
		ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
		try {
			byte abyte0[] = new byte[6000];
			int i;
			int j;
			for (j = 0; (i = inputstream.read(abyte0)) != -1; j += i)
				bytearrayoutputstream.write(abyte0, 0, i);

			inputstream.close();
			System.out.println((new StringBuilder()).append("image size = ")
					.append(j).toString());
		} catch (IOException ioexception) {
			System.out.println((new StringBuilder()).append("read error: ")
					.append(ioexception.getMessage()).toString());
		}
		return bytearrayoutputstream.toByteArray();
	}
}