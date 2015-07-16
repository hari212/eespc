package com.eespc.tracking.actions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.upload.FormFile;

import com.eespc.tracking.bo.FacilityVo;
import com.eespc.tracking.entity.FacilityEntity;
import com.eespc.tracking.exceptions.FacilityException;

public class documentAction extends Action {

	public documentAction() {
		userAction = null;
	}

	static final String CLIENT_FOLDER1 = "/clientfolder/";
	static final String CLIENT_FOLDER2 = "/clientfolder_global/";
	String clientFolder = null;

	public ActionForward execute(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception,
			IOException, ServletException {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		userAction = (String) dynaactionform.get("methodToCall");
		String opr = (String) (dynaactionform.get("separateFile") != null ? dynaactionform
				.get("separateFile") : "");
		clientFolder = ("1".equals(opr)) ? CLIENT_FOLDER2 : CLIENT_FOLDER1;
		System.out.println("Accessing Folder Path " + clientFolder);
		// cleanUp(actionmapping, actionform, httpservletrequest,
		// httpservletresponse);
		try {

			if (userAction != null && userAction.equalsIgnoreCase("Exhibit32"))
				return exhibit32(actionmapping, actionform, httpservletrequest,
						httpservletresponse);
			if (userAction != null
					&& userAction.equalsIgnoreCase("backforexhibit32"))
				return backforexhibit32(actionmapping, actionform,
						httpservletrequest, httpservletresponse);

			if (userAction != null && userAction.equalsIgnoreCase("Reset"))
				return reset(actionmapping, actionform, httpservletrequest,
						httpservletresponse);

			if (userAction != null && userAction.equalsIgnoreCase("fileupload"))
				return fileupload(actionmapping, actionform,
						httpservletrequest, httpservletresponse);

			if (userAction != null && userAction.equalsIgnoreCase("view"))
				return view(actionmapping, actionform, httpservletrequest,
						httpservletresponse);

			if (userAction != null
					&& userAction.equalsIgnoreCase("viewexhibit"))
				return viewexhibit(actionmapping, actionform,
						httpservletrequest, httpservletresponse);

			if (userAction != null && userAction.equalsIgnoreCase("back"))
				return back(actionmapping, actionform, httpservletrequest,
						httpservletresponse);

			if (userAction != null
					&& userAction.equalsIgnoreCase("addnewfolder"))
				return addnewfolder(actionmapping, actionform,
						httpservletrequest, httpservletresponse);

			if (userAction != null && userAction.equalsIgnoreCase("delete"))
				return delete(actionmapping, actionform, httpservletrequest,
						httpservletresponse);

			if (userAction != null && userAction.equalsIgnoreCase("edit"))
				return edit(actionmapping, actionform, httpservletrequest,
						httpservletresponse);

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}

		/*
		 * DynaActionForm dynaactionform = (DynaActionForm)actionform;
		 * HttpSession httpsession = httpservletrequest.getSession(); FacilityVo
		 * facilityvo = (FacilityVo)httpsession.getAttribute("FACILITY_OBJECT");
		 * System.out.println("Facility Id: "+facilityvo.getDecId());
		 * System.out.println("Path="+httpservletrequest.getContextPath()+":"+
		 * httpservletrequest.getRealPath("/")); String s =
		 * (String)dynaactionform.get("methodToCall"); try {
		 * 
		 * File f=new
		 * File(httpservletrequest.getRealPath("/")+"clientFolder"+facilityvo
		 * .getDecId()); if(f.exists()==false){ f.mkdirs(); }
		 * 
		 * } catch(Exception e) { System.out.println("Exception: "+e); }
		 */
		return actionmapping.findForward("success");

	}

	public void cleanUp(ActionMapping actionmapping, ActionForm actionform,
			HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		deleteEmptyFolders(httpservletrequest.getRealPath("/") + clientFolder);
	}

	public ActionForward reset(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		System.out.println("Facility Id: " + facilityvo.getDecId());
		System.out.println("Path=" + httpservletrequest.getContextPath() + ":"
				+ httpservletrequest.getRealPath("/"));
		String s = (String) dynaactionform.get("methodToCall");

		try {
			File f = new File(httpservletrequest.getRealPath("/")
					+ clientFolder + facilityvo.getDecId());
			if (f.exists() == false) {
				f.mkdirs();
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
		ArrayList folderlist = new ArrayList();
		try {
			String contextpath = httpservletrequest.getRealPath("/")
					+ clientFolder + facilityvo.getDecId() + "/";
			File folder = new File(contextpath);
			File[] listOfFiles = folder.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				System.out.println("1");
				String arr[] = new String[5];
				if (listOfFiles[i].isFile()) {
					System.out.println("1a");
					arr[0] = "file";
					arr[1] = contextpath;
					arr[2] = listOfFiles[i].getName();
					Date lastModified = new Date(listOfFiles[i].lastModified());
					arr[3] = String.valueOf(lastModified);
					arr[4] = String.valueOf(listOfFiles[i].length()) + " Bytes";
					System.out.println("File " + listOfFiles[i].getName());
				} else if (listOfFiles[i].isDirectory()) {
					System.out.println("1b");
					arr[0] = "folder";
					arr[1] = contextpath;
					arr[2] = listOfFiles[i].getName();
					Date lastModified = new Date(listOfFiles[i].lastModified());
					arr[3] = String.valueOf(lastModified);
					System.out.println("Directory " + listOfFiles[i].getName());
				}
				folderlist.add(arr);
				System.out.println("2");
			}
		} catch (Exception e) {
			System.out.println("Find List Exception: " + e);
		}
		httpservletrequest.setAttribute("FILE_LIST", folderlist);
		httpservletrequest.setAttribute("FILE_PATH", facilityvo.getDecId());
		return actionmapping.findForward("success");
	}

	public ActionForward exhibit32(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException, FacilityException {
		FacilityEntity facilityentity = new FacilityEntity();

		FacilityVo facilityvo = (FacilityVo) httpservletrequest.getSession()
				.getAttribute("REPORT_FACILITY_VO");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		System.out.println("Facility Id: " + facilityvo.getDecId());
		System.out.println("Path=" + httpservletrequest.getContextPath() + ":"
				+ httpservletrequest.getRealPath("/"));
		String s = (String) dynaactionform.get("methodToCall");
		try {
			File f = new File(httpservletrequest.getRealPath("/")
					+ clientFolder + facilityvo.getDecId());
			if (f.exists() == false) {
				f.mkdirs();
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
		ArrayList folderlist = new ArrayList();
		try {
			String contextpath = httpservletrequest.getRealPath("/")
					+ clientFolder + facilityvo.getDecId() + "/";
			File folder = new File(contextpath);
			File[] listOfFiles = folder.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				System.out.println("1");
				String arr[] = new String[5];
				if (listOfFiles[i].isFile()) {
					arr[0] = "file";
					arr[1] = contextpath;
					arr[2] = listOfFiles[i].getName();
					Date lastModified = new Date(listOfFiles[i].lastModified());
					arr[3] = String.valueOf(lastModified);
					arr[4] = String.valueOf(listOfFiles[i].length()) + " Bytes";
					System.out.println("File " + listOfFiles[i].getName());
				} else if (listOfFiles[i].isDirectory()) {
					arr[0] = "folder";
					arr[1] = contextpath;
					arr[2] = listOfFiles[i].getName();
					Date lastModified = new Date(listOfFiles[i].lastModified());
					arr[3] = String.valueOf(lastModified);
					System.out.println("Directory " + listOfFiles[i].getName());
				}
				folderlist.add(arr);
				System.out.println("2");
			}
		} catch (Exception e) {
			System.out.println("Find List Exception: " + e);
		}
		httpservletrequest.setAttribute("FILE_LIST", folderlist);
		httpservletrequest.setAttribute("FILE_PATH", facilityvo.getDecId());
		return actionmapping.findForward("successexhibit");
	}

	/*
	 * public ActionForward exhibit35(ActionMapping actionmapping, ActionForm
	 * actionform, HttpServletRequest httpservletrequest, HttpServletResponse
	 * httpservletresponse) throws IOException,
	 * ServletException,FacilityException { FacilityEntity facilityentity=new
	 * FacilityEntity();
	 * 
	 * FacilityVo
	 * facilityvo=(FacilityVo)httpservletrequest.getSession().getAttribute
	 * ("REPORT_FACILITY_VO"); DynaActionForm dynaactionform =
	 * (DynaActionForm)actionform; HttpSession httpsession =
	 * httpservletrequest.getSession();
	 * System.out.println("Facility Id: "+facilityvo.getDecId());
	 * System.out.println
	 * ("Path="+httpservletrequest.getContextPath()+":"+httpservletrequest
	 * .getRealPath("/")); String s =
	 * (String)dynaactionform.get("methodToCall"); try { File f=new
	 * File(httpservletrequest
	 * .getRealPath("/")+clientfolder_global+facilityvo.getDecId());
	 * if(f.exists()==false){ f.mkdirs(); } } catch(Exception e) {
	 * System.out.println("Exception: "+e); } ArrayList folderlist=new
	 * ArrayList(); try { String
	 * contextpath=httpservletrequest.getRealPath("/")+
	 * clientfolder_global+facilityvo.getDecId()+"/"; File folder = new
	 * File(contextpath); File[] listOfFiles = folder.listFiles(); for (int i =
	 * 0; i < listOfFiles.length; i++) { System.out.println("1"); String
	 * arr[]=new String[5]; if (listOfFiles[i].isFile()) { arr[0]="file";
	 * arr[1]=contextpath; arr[2]=listOfFiles[i].getName(); Date lastModified =
	 * new Date(listOfFiles[i].lastModified());
	 * arr[3]=String.valueOf(lastModified);
	 * arr[4]=String.valueOf(listOfFiles[i].length())+" Bytes";
	 * System.out.println("File " + listOfFiles[i].getName()); } else if
	 * (listOfFiles[i].isDirectory()) { arr[0]="folder"; arr[1]=contextpath;
	 * arr[2]=listOfFiles[i].getName(); Date lastModified = new
	 * Date(listOfFiles[i].lastModified()); arr[3]=String.valueOf(lastModified);
	 * System.out.println("Directory " + listOfFiles[i].getName()); }
	 * folderlist.add(arr); System.out.println("2"); } } catch(Exception e) {
	 * System.out.println("Find List Exception: "+e); }
	 * httpservletrequest.setAttribute("FILE_LIST",folderlist);
	 * httpservletrequest.setAttribute("FILE_PATH",facilityvo.getDecId());
	 * return actionmapping.findForward("successexhibit"); }
	 */
	public ActionForward addnewfolder(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();

		String path = (String) dynaactionform.get("pathname");
		String foldername = (String) dynaactionform.get("foldername");

		String Pathname = httpservletrequest.getRealPath("/") + clientFolder
				+ path + "/" + foldername;

		if (httpsession.getAttribute("DELETE_PERMISSION") == null) {
			httpservletrequest.setAttribute("MESSAGE", "No Permission");
			httpservletrequest.setAttribute("MESSAGE_TYPE", "Error");
			setformvariable(path, actionmapping, actionform,
					httpservletrequest, httpservletresponse);
			return actionmapping.findForward("success");
		}

		try {
			File f = new File(Pathname);
			if (f.exists() == false) {
				f.mkdirs();
			} else {
				System.out.println("Error:Already Exist");
				httpservletrequest.setAttribute("MESSAGE", "Already Exist");
				httpservletrequest.setAttribute("MESSAGE_TYPE", "Error");
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
		setformvariable(path, actionmapping, actionform, httpservletrequest,
				httpservletresponse);
		return actionmapping.findForward("success");
	}

	public ActionForward view(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String path = (String) dynaactionform.get("pathname");
		String foldername = (String) dynaactionform.get("foldername");
		setformvariable((path + "/" + foldername), actionmapping, actionform,
				httpservletrequest, httpservletresponse);
		return actionmapping.findForward("success");
	}

	public ActionForward viewexhibit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException, FacilityException {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String path = (String) dynaactionform.get("pathname");
		String foldername = (String) dynaactionform.get("foldername");
		setformvariableexhibit((path + "/" + foldername), actionmapping,
				actionform, httpservletrequest, httpservletresponse);
		return actionmapping.findForward("successexhibit");
	}

	public ActionForward delete(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String path = (String) dynaactionform.get("pathname");
		String foldername = (String) dynaactionform.get("foldername");

		String Pathname = httpservletrequest.getRealPath("/") + clientFolder
				+ path + "/" + foldername;

		if (httpsession.getAttribute("DELETE_PERMISSION") == null) {
			httpservletrequest.setAttribute("MESSAGE", "No Permission");
			httpservletrequest.setAttribute("MESSAGE_TYPE", "Error");
			setformvariable(path, actionmapping, actionform,
					httpservletrequest, httpservletresponse);
			return actionmapping.findForward("success");
		}

		try {

			File f = new File(Pathname);
			if (f.isDirectory()) {
				delete(f);
				if (f.exists() == true) {
					f.delete();
				} else {
					System.out.println("Error:No File or Folder");
					httpservletrequest.setAttribute("MESSAGE",
							"No File or Folder");
					httpservletrequest.setAttribute("MESSAGE_TYPE", "Error");
				}
			} else {
				if (f.exists() == true) {
					f.delete();
				} else {
					System.out.println("Error:No File or Folder");
					httpservletrequest.setAttribute("MESSAGE",
							"No File or Folder");
					httpservletrequest.setAttribute("MESSAGE_TYPE", "Error");
				}
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}

		setformvariable(path, actionmapping, actionform, httpservletrequest,
				httpservletresponse);
		return actionmapping.findForward("success");
	}

	public ActionForward edit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String path = (String) dynaactionform.get("pathname");
		if (httpsession.getAttribute("DELETE_PERMISSION") == null) {
			httpservletrequest.setAttribute("MESSAGE", "No Permission");
			httpservletrequest.setAttribute("MESSAGE_TYPE", "Error");
			setformvariable(path, actionmapping, actionform,
					httpservletrequest, httpservletresponse);
			return actionmapping.findForward("success");
		}
		String foldername = (String) dynaactionform.get("foldername");
		String refoldername = (String) dynaactionform.get("renamefoldername");
		System.out.println(" Old: " + foldername + " New: " + refoldername);
		String Pathname = httpservletrequest.getRealPath("/") + clientFolder
				+ path + "/" + foldername;
		String rePathname = httpservletrequest.getRealPath("/") + clientFolder
				+ path + "/" + refoldername;
		System.out.println(" OldPath: " + Pathname + " NewPath: " + rePathname);
		try {

			File f = new File(Pathname);
			if (f.isDirectory()) {
				File newFileName = new File(rePathname);
				if (newFileName.exists() == false) {
					System.out.println("Error:GOODDDDDDDDDDDDDD");
					f.renameTo(newFileName);
				} else {
					System.out.println("Error:Already Exist");
					httpservletrequest.setAttribute("MESSAGE", "Already Exist");
					httpservletrequest.setAttribute("MESSAGE_TYPE", "Error");
				}
			} else {

				File newFileName = new File(rePathname);
				if (newFileName.exists() == false) {
					System.out.println("Error:GOODDDDDDDDDDDDDD");
					f.renameTo(newFileName);
				} else {
					System.out.println("Error:Already Exist");
					httpservletrequest.setAttribute("MESSAGE", "Already Exist");
					httpservletrequest.setAttribute("MESSAGE_TYPE", "Error");
				}
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}

		setformvariable(path, actionmapping, actionform, httpservletrequest,
				httpservletresponse);
		return actionmapping.findForward("success");
	}

	public ActionForward back(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		String back = "";
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		String path = (String) dynaactionform.get("pathname");
		System.out.println("path:" + path);
		if (path.equals(facilityvo.getDecId())) {
			back = path;
		} else {
			back = path.substring(0, path.lastIndexOf("/"));
		}

		System.out.println("back:" + back);
		String foldername = (String) dynaactionform.get("foldername");
		setformvariable(back, actionmapping, actionform, httpservletrequest,
				httpservletresponse);
		return actionmapping.findForward("success");
	}

	public ActionForward backforexhibit32(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException, FacilityException {
		String back = "";
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		FacilityEntity facilityentity = new FacilityEntity();
		FacilityVo facilityvo = (FacilityVo) httpservletrequest.getSession()
				.getAttribute("REPORT_FACILITY_VO");
		// FacilityVo facilityvo =
		// (FacilityVo)httpsession.getAttribute("FACILITY_OBJECT");
		String path = (String) dynaactionform.get("pathname");
		System.out.println("path:" + path);
		if (path.equals(facilityvo.getDecId())) {
			back = path;
		} else {
			back = path.substring(0, path.lastIndexOf("/"));
		}

		System.out.println("back:" + back);
		String foldername = (String) dynaactionform.get("foldername");
		setformvariableexhibit(back, actionmapping, actionform,
				httpservletrequest, httpservletresponse);
		return actionmapping.findForward("successexhibit");
	}

	public ActionForward fileupload(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String path = (String) dynaactionform.get("pathname");
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		// httpservletrequest.getRealPath("/")+clientFolder+facilityvo.getDecId()
		try {

			FormFile uploadFile = (FormFile) dynaactionform.get("filename");
			String uploadingFileName = uploadFile.getFileName();
			// String
			// uploadPath=getServlet().getServletContext().getRealPath("/")
			// +"/upload";
			String uploadPath = httpservletrequest.getRealPath("/")
					+ clientFolder + path;
			if (!uploadingFileName.equals("")) {

				File uploadFileObject = new File(uploadPath, uploadingFileName);
				if (uploadFileObject.exists() == false) {
					FileOutputStream fileOutStream = new FileOutputStream(
							uploadFileObject);
					fileOutStream.write(uploadFile.getFileData());
					fileOutStream.flush();
					fileOutStream.close();
				} else {
					System.out.println("Error:Already Exist");
					httpservletrequest.setAttribute("MESSAGE", "Already Exist");
					httpservletrequest.setAttribute("MESSAGE_TYPE", "Error");
				}

			} else {
				httpservletrequest.setAttribute("MESSAGE", "No File To upload");
				httpservletrequest.setAttribute("MESSAGE_TYPE", "Error");
				System.out.println("Error:No File");
			}

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}

		// httpservletrequest.getRealPath("/")+clientFolder+facilityvo.getDecId()
		setformvariable(path, actionmapping, actionform, httpservletrequest,
				httpservletresponse);
		return actionmapping.findForward("success");

	}

	public void setformvariableexhibit(String path,
			ActionMapping actionmapping, ActionForm actionform,
			HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException, FacilityException {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		FacilityEntity facilityentity = new FacilityEntity();
		FacilityVo facilityvo = (FacilityVo) httpservletrequest.getSession()
				.getAttribute("REPORT_FACILITY_VO");
		System.out.println("Facility Id: " + facilityvo.getDecId());
		System.out.println("Path=" + httpservletrequest.getContextPath() + ":"
				+ httpservletrequest.getRealPath("/"));
		String s = (String) dynaactionform.get("methodToCall");
		try {

			File f = new File(httpservletrequest.getRealPath("/")
					+ clientFolder + path);
			if (f.exists() == false) {
				f.mkdirs();
			}

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}

		ArrayList folderlist = new ArrayList();
		try {

			String contextpath = httpservletrequest.getRealPath("/")
					+ clientFolder + path + "/";
			File folder = new File(contextpath);
			File[] listOfFiles = folder.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				System.out.println("1");
				String arr[] = new String[5];
				if (listOfFiles[i].isFile()) {
					System.out.println("1a");
					arr[0] = "file";
					arr[1] = contextpath;
					arr[2] = listOfFiles[i].getName();
					Date lastModified = new Date(listOfFiles[i].lastModified());
					arr[3] = String.valueOf(lastModified);
					arr[4] = String.valueOf(listOfFiles[i].length()) + " Bytes";
					System.out.println("File " + listOfFiles[i].getName());
				} else if (listOfFiles[i].isDirectory()) {
					System.out.println("1b");
					arr[0] = "folder";
					arr[1] = contextpath;
					arr[2] = listOfFiles[i].getName();
					Date lastModified = new Date(listOfFiles[i].lastModified());
					arr[3] = String.valueOf(lastModified);
					System.out.println("Directory " + listOfFiles[i].getName());
				}
				folderlist.add(arr);
				System.out.println("2");

			}

		} catch (Exception e) {
			System.out.println("Find List Exception: " + e);
		}
		httpservletrequest.setAttribute("FILE_LIST", folderlist);
		httpservletrequest.setAttribute("FILE_PATH", path);
	}

	public void setformvariable(String path, ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		System.out.println("Facility Id: " + facilityvo.getDecId());
		System.out.println("Path=" + httpservletrequest.getContextPath() + ":"
				+ httpservletrequest.getRealPath("/"));
		String s = (String) dynaactionform.get("methodToCall");
		try {

			File f = new File(httpservletrequest.getRealPath("/")
					+ clientFolder + path);
			if (f.exists() == false) {
				f.mkdirs();
			}

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}

		ArrayList folderlist = new ArrayList();
		try {

			String contextpath = httpservletrequest.getRealPath("/")
					+ clientFolder + path + "/";
			File folder = new File(contextpath);
			File[] listOfFiles = folder.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				System.out.println("1");
				String arr[] = new String[5];
				if (listOfFiles[i].isFile()) {
					System.out.println("1a");
					arr[0] = "file";
					arr[1] = contextpath;
					arr[2] = listOfFiles[i].getName();
					Date lastModified = new Date(listOfFiles[i].lastModified());
					arr[3] = String.valueOf(lastModified);
					arr[4] = String.valueOf(listOfFiles[i].length()) + " Bytes";
					System.out.println("File " + listOfFiles[i].getName());
				} else if (listOfFiles[i].isDirectory()) {
					System.out.println("1b");
					arr[0] = "folder";
					arr[1] = contextpath;
					arr[2] = listOfFiles[i].getName();
					Date lastModified = new Date(listOfFiles[i].lastModified());
					arr[3] = String.valueOf(lastModified);
					System.out.println("Directory " + listOfFiles[i].getName());
				}
				folderlist.add(arr);
				System.out.println("2");

			}

		} catch (Exception e) {
			System.out.println("Find List Exception: " + e);
		}
		httpservletrequest.setAttribute("FILE_LIST", folderlist);
		httpservletrequest.setAttribute("FILE_PATH", path);
	}

	/*
	 * public boolean deleteDir(File dir) { boolean s=false;
	 * System.out.println("In Boolean"); dir.delete(); if (dir.isDirectory()) {
	 * 
	 * String[] children = dir.list(); for (int i=0; i<children.length; i++) {
	 * boolean success = deleteDir(new File(dir, children[i])); s=success; if
	 * (!success) { s=false; return false; }
	 * 
	 * } }
	 * 
	 * 
	 * return s; }
	 */

	public static void delete(File folder) {
		if (folder.exists()) {
			File[] files = folder.listFiles();
			for (int nFileCur = 0; nFileCur < files.length; nFileCur++) {
				File oFileCur = files[nFileCur];
				if (oFileCur.isDirectory()) {
					// call itself to delete the contents of the current folder
					delete(oFileCur);
				}
				oFileCur.delete();
			}
		}
	}

	private String userAction;

	public static void deleteEmptyFolders(String folderName)
			throws FileNotFoundException {
		File aStartingDir = new File(folderName);
		List<File> emptyFolders = new ArrayList<File>();
		findEmptyFoldersInDir(aStartingDir, emptyFolders);
		List<String> fileNames = new ArrayList<String>();
		for (File f : emptyFolders) {
			String s = f.getAbsolutePath();
			fileNames.add(s);
		}
		for (File f : emptyFolders) {
			boolean isDeleted = f.delete();
			if (isDeleted) {
				System.out.println(f.getPath() + " deleted");
			}
		}
	}

	public static boolean findEmptyFoldersInDir(File folder,
			List<File> emptyFolders) {
		boolean isEmpty = false;
		File[] filesAndDirs = folder.listFiles();
		List<File> filesDirs = Arrays.asList(filesAndDirs);
		if (filesDirs.size() == 0) {
			isEmpty = true;
		}
		if (filesDirs.size() > 0) {
			boolean allDirsEmpty = true;
			boolean noFiles = true;
			for (File file : filesDirs) {
				if (!file.isFile()) {
					boolean isEmptyChild = findEmptyFoldersInDir(file,
							emptyFolders);
					if (!isEmptyChild) {
						allDirsEmpty = false;
					}
				}
				if (file.isFile()) {
					noFiles = false;
				}
			}
			if (noFiles == true && allDirsEmpty == true) {
				isEmpty = true;
			}
		}
		if (isEmpty) {
			emptyFolders.add(folder);
		}
		return isEmpty;
	}

}