// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 3/30/2014 6:10:22 PM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   saved.java

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;

import Test.TestFacility;

import com.eespc.tracking.util.SqlUtil;

public class saved extends HttpServlet {

	public saved() {
		utilObj = new SqlUtil();
		data = null;
		check = "";
	}

	public synchronized void doPost(HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse)
			throws org.apache.commons.fileupload.MultipartStream.MalformedStreamException {
		try {
			FileUpload fileupload = new FileUpload();
			boolean flag = FileUpload.isMultipartContent(httpservletrequest);
			DiskFileUpload diskfileupload = new DiskFileUpload();
			List list = diskfileupload.parseRequest(httpservletrequest);
			Iterator iterator = list.iterator();
			do {
				if (!iterator.hasNext())
					break;
				FileItem fileitem = (FileItem) iterator.next();
				if (!fileitem.isFormField()) {
					System.out.println("its a file");
					System.out.println(fileitem.getName());
					cfile = new File(fileitem.getName());
					File file = new File(getServletContext().getRealPath("/"),
							cfile.getName());
					data = fileitem.get();
				}
			} while (true);
		} catch (Exception exception) {
			System.out.println(exception);
		}
		try {
			con = utilObj.getConnection();
			st = con.prepareStatement("select * from exhibitword where facilityid=?");
			st.setString(1, String.valueOf(TestFacility.getCurrentFacilityID()));
			rs = st.executeQuery();
			if (rs.next())
				check = "1";
			else
				check = "2";
		} catch (Exception exception1) {
			System.out.println((new StringBuilder())
					.append("Connection Exception : ").append(exception1)
					.toString());
		}
		try {
			con = utilObj.getConnection();
			if (check.equals("1")) {
				st = con.prepareStatement("update exhibitword set file=? where facilityid=?");
				st.setBytes(1, data);
				st.setString(2,
						String.valueOf(TestFacility.getCurrentFacilityID()));
			} else {
				st = con.prepareStatement("insert into exhibitword (facilityid,file) values(?,?)");
				st.setString(1,
						String.valueOf(TestFacility.getCurrentFacilityID()));
				st.setBytes(2, data);
			}
			st.executeUpdate();
			httpservletresponse.setContentType("text/html");
			PrintWriter printwriter = httpservletresponse.getWriter();
			printwriter.println("Successfully Saved");
		} catch (Exception exception2) {
			System.out.println((new StringBuilder())
					.append("Connection Exception : ").append(exception2)
					.toString());
		}
	}

	SqlUtil utilObj;
	byte data[];
	Connection con;
	ResultSet rs;
	PreparedStatement st;
	FileInputStream fis;
	String check;
	File cfile;
}