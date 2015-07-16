package com.eespc.tracking.actions;

import com.eespc.tracking.bo.*;
import com.eespc.tracking.bo.myenum.*;
import com.eespc.tracking.entity.BoilerEntity;
import com.eespc.tracking.entity.StackStorageChecker;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.UtilityObject;
import java.util.*;
import javax.servlet.http.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.*;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.*;
import javax.swing.JOptionPane;
import org.apache.struts.upload.FormFile;
import javax.servlet.ServletException;
import java.util.Date;

public class BoilerAction extends Action
{

    public BoilerAction()
    {
    }

    public ActionForward execute(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        setDropDown(httpservletrequest);
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        String s = (String)dynaactionform.get("methodToCall");
        if(s != null && s.equalsIgnoreCase("ADD"))
            return add(actionmapping, actionform, httpservletrequest, httpservletresponse);
        if(s != null && s.equalsIgnoreCase("UPDATE"))
            return update(actionmapping, actionform, httpservletrequest, httpservletresponse);
        if(s != null && s.equalsIgnoreCase("DELETE"))
            return delete(actionmapping, actionform, httpservletrequest, httpservletresponse);
        if(s != null && s.equalsIgnoreCase("VIEW"))
            return view(actionmapping, actionform, httpservletrequest, httpservletresponse);
        if(s != null && s.equalsIgnoreCase("VIEWEXIST"))
            return viewexist(actionmapping, actionform, httpservletrequest, httpservletresponse);
        if(s != null && s.equalsIgnoreCase("EDIT"))
            return edit(actionmapping, actionform, httpservletrequest, httpservletresponse);
        if(s != null && s.equalsIgnoreCase("depPermitInfo"))
            return depPermitInfo(actionmapping, actionform, httpservletrequest, httpservletresponse);
        if(s != null && s.equalsIgnoreCase("editDepPermit"))
            return editDepPermit(actionmapping, actionform, httpservletrequest, httpservletresponse);
        if(s != null && s.equalsIgnoreCase("deleteDepPermit"))
            return deleteDepPermit(actionmapping, actionform, httpservletrequest, httpservletresponse);
        if(s != null && s.equalsIgnoreCase("updateDepPermitInfo"))
            return updateDepPermitInfo(actionmapping, actionform, httpservletrequest, httpservletresponse);
        if(s != null && s.equalsIgnoreCase("dobPermitInfo"))
            return dobPermitInfo(actionmapping, actionform, httpservletrequest, httpservletresponse);
        if(s != null && s.equalsIgnoreCase("editDobPermit"))
            return editDobPermit(actionmapping, actionform, httpservletrequest, httpservletresponse);
        if(s != null && s.equalsIgnoreCase("deleteDobPermit"))
            return deleteDobPermit(actionmapping, actionform, httpservletrequest, httpservletresponse);
        if(s != null && s.equalsIgnoreCase("updateDobPermitInfo"))
            return updateDobPermitInfo(actionmapping, actionform, httpservletrequest, httpservletresponse);
        if(s != null && s.equalsIgnoreCase("annualTuneUpInfo"))
            return annualTuneUpInfo(actionmapping, actionform, httpservletrequest, httpservletresponse);
        if(s != null && s.equalsIgnoreCase("editAnnualTuneUp"))
            return editAnnualTuneUp(actionmapping, actionform, httpservletrequest, httpservletresponse);
        if(s != null && s.equalsIgnoreCase("deleteAnnualTuneUp"))
            return deleteAnnualTuneUp(actionmapping, actionform, httpservletrequest, httpservletresponse);
        if(s != null && s.equalsIgnoreCase("updateAnnualTuneUpInfo"))
            return updateAnnualTuneUpInfo(actionmapping, actionform, httpservletrequest, httpservletresponse);
            
        if(s != null && s.equalsIgnoreCase("valveTestInfo"))
            return valveTestInfo(actionmapping, actionform, httpservletrequest, httpservletresponse);
        if(s != null && s.equalsIgnoreCase("editValveTest"))
            return editValveTest(actionmapping, actionform, httpservletrequest, httpservletresponse);
        if(s != null && s.equalsIgnoreCase("deleteValveTest"))
            return deleteValveTest(actionmapping, actionform, httpservletrequest, httpservletresponse);
        if(s != null && s.equalsIgnoreCase("updateValveTestInfo"))
            return updateValveTestInfo(actionmapping, actionform, httpservletrequest, httpservletresponse);
            
            
        if(s != null && s.equalsIgnoreCase("fuelConsumptionInfo"))
            return fuelConsumptionInfo(actionmapping, actionform, httpservletrequest, httpservletresponse);
        if(s != null && s.equalsIgnoreCase("fuelConsumptionInfoyearconsumtion"))
            return fuelConsumptionInfoyearconsumtion(actionmapping, actionform, httpservletrequest, httpservletresponse);
        if(s != null && s.equalsIgnoreCase("o_fuelConsumptionInfoyearconsumtion"))
            return o_fuelConsumptionInfoyearconsumtion(actionmapping, actionform, httpservletrequest, httpservletresponse);
        if(s != null && s.equalsIgnoreCase("deletefuelConsumptionInfo"))
            return deletefuelConsumptionInfo(actionmapping, actionform, httpservletrequest, httpservletresponse);
        if(s != null && s.equalsIgnoreCase("displayControl"))
            return displayControl(actionmapping, actionform, httpservletrequest, httpservletresponse);
        if(s != null && s.equalsIgnoreCase("fileupload"))
            return fileupload(actionmapping, actionform, httpservletrequest, httpservletresponse);
        if(s != null && s.equalsIgnoreCase("back"))
            return back(actionmapping, actionform, httpservletrequest, httpservletresponse);
        if(s != null && s.equalsIgnoreCase("addnewfolder"))
            return addnewfolder(actionmapping, actionform, httpservletrequest, httpservletresponse);
        if(s != null && s.equalsIgnoreCase("deletefile"))
            return deletefile(actionmapping, actionform, httpservletrequest, httpservletresponse);
        if(s != null && s.equalsIgnoreCase("editfile"))
            return editfile(actionmapping, actionform, httpservletrequest, httpservletresponse);
        if(s != null && s.equalsIgnoreCase("viewfile"))
            return viewfile(actionmapping, actionform, httpservletrequest, httpservletresponse);


        StackStorageChecker stackstoragechecker = new StackStorageChecker();
        FacilityVo facilityvo = (FacilityVo)httpservletrequest.getSession().getAttribute("FACILITY_OBJECT");
        if(!stackstoragechecker.isAvailable(facilityvo.getId()))
        {
            if(null != stackstoragechecker.getMessage())
                httpservletrequest.setAttribute("STACK_STORAGE_VALIDATION_MESSAGE", stackstoragechecker.getMessage());
            return actionmapping.findForward("showNoStackStorageMessagePage");
        } else
        {
            log.debug((new StringBuilder()).append("BoilerAction - Stack / Storage Tank validated for Facility Id =").append(facilityvo.getId()).append(".").toString());
            dynaactionform.set("B_buildingId", "");
            dynaactionform.set("B_facilityDesignatedId", "");
            dynaactionform.set("B_floor", "");
            dynaactionform.set("B_stateId", "");
            dynaactionform.set("B_primaryUse", "");
            dynaactionform.set("B_yearInstalled", "");
            dynaactionform.set("B_manufacturer", "");
            dynaactionform.set("B_make", "");
            dynaactionform.set("B_model", "");
            dynaactionform.set("B_serial", "");
            dynaactionform.set("B_burnerMake", "");
            dynaactionform.set("B_burnerModel", "");
            dynaactionform.set("B_capacity", "");
            dynaactionform.set("B_burnerType", "");
            dynaactionform.set("B_primaryFuel", "");
            dynaactionform.set("B_secondaryFuel", "");
            dynaactionform.set("B_oilFiringRate", "");
            dynaactionform.set("B_naturalGasFiringRate", "");
            dynaactionform.set("B_fuelFrom", "");
            dynaactionform.set("B_nycDob", "");
            dynaactionform.set("B_mea", "");
            dynaactionform.set("B_dep", "");
            dynaactionform.set("B_sechduelC", "");
            dynaactionform.set("B_planApproval", "");
            dynaactionform.set("B_stackFrom", "");
            dynaactionform.set("B_stackTestRequired", "");
            dynaactionform.set("B_otherBoilersCombined", "");
            dynaactionform.set("B_parameters1", "");
            dynaactionform.set("B_parameters2", "");
            dynaactionform.set("B_parameters3", "");
            dynaactionform.set("B_testOnFuel", "");
            dynaactionform.set("B_stackTestProtSubmited", "");
            dynaactionform.set("B_testConductedBy", "");
            dynaactionform.set("B_testReportSubmited", "");
            dynaactionform.set("B_protocolSubmitDate", "");
            dynaactionform.set("B_boilerTestPassed", "");
            dynaactionform.set("B_retestPlanned", "");
            dynaactionform.set("B_nextStackTestDate", "");
            dynaactionform.set("B_StackTestDate", "");
            dynaactionform.set("B_isBoilerInCompliance", "");
            dynaactionform.set("B_isBoilerSubjectToNspc", "");
            dynaactionform.set("B_blrModifiedInPast", "");
            dynaactionform.set("B_blrModifiedOn", "");
            dynaactionform.set("B_isModifyPermitSub", "");
            dynaactionform.set("B_anyEmission", "");
            dynaactionform.set("B_blrSubjectToFederal", "");
            dynaactionform.set("B_fuelCaping", "");
            dynaactionform.set("B_isRollingAvg", "");
            dynaactionform.set("B_isOpacityMonInst", "");
            dynaactionform.set("B_prfmTestProtSub", "");
            dynaactionform.set("B_prfmTestProtSubDate", "");
            dynaactionform.set("B_prfmTestRptSub", "");
            dynaactionform.set("B_prfmTestRptSubDate", "");
            dynaactionform.set("B_isSulpContentAnaly", "");
            dynaactionform.set("B_sulphurContent", "");
            dynaactionform.set("B_fuelLimitsForBlr", "");
            dynaactionform.set("B_fuelLimitQnty", "");
            dynaactionform.set("B_nitrogenContent", "");
            dynaactionform.set("B_nitrogenContentQnty", "");
            dynaactionform.set("ModifiedInPast", "");
            dynaactionform.set("DisconnectedYear", "");
            dynaactionform.set("B_comments", "");
            dynaactionform.set("B_footnote", "");
            dynaactionform.set("B_oilemmisionfactor", "");
            dynaactionform.set("B_gasemmisionfactor", "");
            dynaactionform.set("B_gasfuelgapping", "");
            dynaactionform.set("B_oilfuelgapping", "");
            dynaactionform.set("B_primaryuseother", "");
            dynaactionform.set("B_oilso2", "");
            dynaactionform.set("B_gasso2", "");
            dynaactionform.set("B_so2allowable","");
			dynaactionform.set("B_noxallowable","");
			dynaactionform.set("dobsignoff","");
			dynaactionform.set("firedepartmentapproval","");
			dynaactionform.set("depPermit","");
			dynaactionform.set("depPermitExpire","");
			dynaactionform.set("dobfiling","");
			dynaactionform.set("boilerTuneup","");
            log.debug("BoilerAction - In Execute");
            httpservletrequest.getSession().removeAttribute("BOILER_OBJECT");
            setScreenInfo(httpservletrequest);
            return actionmapping.findForward("success");
        }
    }



    public ActionForward addnewfolder(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
    	DynaActionForm dynaactionform = (DynaActionForm)actionform;
        HttpSession httpsession = httpservletrequest.getSession();
        BoilerVo boilervo =new BoilerVo();
        boilervo=(BoilerVo)httpsession.getAttribute("BOILER_OBJECT");
        setFieldDetails(httpservletrequest, dynaactionform, boilervo);
        setScreenInfo(httpservletrequest);
        //setFormVariable(dynaactionform, boilervo, httpservletrequest);
		//refreshShowInfo(httpservletrequest, boilervo);
		//refreshPermitInfo(httpservletrequest);
        httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
        httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
        httpservletrequest.setAttribute("isComponentEditable", "N");

        String path= (String)dynaactionform.get("pathname");
        String foldername= (String)dynaactionform.get("foldername");

        String Pathname=httpservletrequest.getRealPath("/")+"/clientfolder/"+path+"/"+foldername;

        if(httpsession.getAttribute("DELETE_PERMISSION")==null)
        {
        httpservletrequest.setAttribute("MESSAGE", "No Permission");
        httpservletrequest.setAttribute("MESSAGE_TYPE", "Error");
        setformvariable(path,actionmapping,actionform,httpservletrequest,httpservletresponse);
        return actionmapping.findForward("success");
        }

        try
        {
		        File f=new File(Pathname);
				if(f.exists()==false)
				{
				    f.mkdirs();
				}
				else
				{
				System.out.println("Error:Already Exist");
			    httpservletrequest.setAttribute("MESSAGE", "Already Exist");
            	httpservletrequest.setAttribute("MESSAGE_TYPE", "Error");
				}
        }
		catch(Exception e)
		{
		System.out.println("Exception: "+e);
		}
        setformvariable(path,actionmapping,actionform,httpservletrequest,httpservletresponse);
        return actionmapping.findForward("success");
    }


    public ActionForward editfile(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
    	DynaActionForm dynaactionform = (DynaActionForm)actionform;
        HttpSession httpsession = httpservletrequest.getSession();
        BoilerVo boilervo =new BoilerVo();
        boilervo=(BoilerVo)httpsession.getAttribute("BOILER_OBJECT");
        setFieldDetails(httpservletrequest, dynaactionform, boilervo);
        setScreenInfo(httpservletrequest);
        //refreshShowInfo(httpservletrequest, boilervo);
		//refreshPermitInfo(httpservletrequest);
        httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
        httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
        httpservletrequest.setAttribute("isComponentEditable", "N");

        String path= (String)dynaactionform.get("pathname");
        if(httpsession.getAttribute("DELETE_PERMISSION")==null)
        {
        httpservletrequest.setAttribute("MESSAGE", "No Permission");
        httpservletrequest.setAttribute("MESSAGE_TYPE", "Error");
        setformvariable(path,actionmapping,actionform,httpservletrequest,httpservletresponse);
        return actionmapping.findForward("success");
        }
        String foldername= (String)dynaactionform.get("foldername");
        String refoldername= (String)dynaactionform.get("renamefoldername");
        System.out.println(" Old: "+foldername+" New: "+refoldername);
        String Pathname=httpservletrequest.getRealPath("/")+"/clientfolder/"+path+"/"+foldername;
        String rePathname=httpservletrequest.getRealPath("/")+"/clientfolder/"+path+"/"+refoldername;
        System.out.println(" OldPath: "+Pathname+" NewPath: "+rePathname);
        try
        {

		        File f=new File(Pathname);
		        if(f.isDirectory())
		        {
			    	File newFileName=new File(rePathname);
				    if(newFileName.exists()==false)
					{
					System.out.println("Error:");
				    f.renameTo(newFileName);
					}
					else
					{
					 System.out.println("Error:Already Exist");
					 httpservletrequest.setAttribute("MESSAGE", "Already Exist");
            		 httpservletrequest.setAttribute("MESSAGE_TYPE", "Error");
					}
				}
				else
				{

			    	File newFileName=new File(rePathname);
				    if(newFileName.exists()==false)
					{
					System.out.println("Error:GOODDDDDDDDDDDDDD");
				    f.renameTo(newFileName);
					}
					else
					{
					 System.out.println("Error:Already Exist");
					 httpservletrequest.setAttribute("MESSAGE", "Already Exist");
            		 httpservletrequest.setAttribute("MESSAGE_TYPE", "Error");
					}
				}
        }
		catch(Exception e)
		{
		System.out.println("Exception: "+e);
		}

        setformvariable(path,actionmapping,actionform,httpservletrequest,httpservletresponse);
        return actionmapping.findForward("success");
    }

    public ActionForward viewfile(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {

    	DynaActionForm dynaactionform = (DynaActionForm)actionform;
        HttpSession httpsession = httpservletrequest.getSession();
        BoilerVo boilervo =new BoilerVo();
        boilervo=(BoilerVo)httpsession.getAttribute("BOILER_OBJECT");
        setFieldDetails(httpservletrequest, dynaactionform, boilervo);
        setScreenInfo(httpservletrequest);
        //refreshShowInfo(httpservletrequest, boilervo);
        //refreshPermitInfo(httpservletrequest);
        httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
        httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
        httpservletrequest.setAttribute("isComponentEditable", "N");
        String path= (String)dynaactionform.get("pathname");
        String foldername= (String)dynaactionform.get("foldername");
        setformvariable((path+"/"+foldername),actionmapping,actionform,httpservletrequest,httpservletresponse);
        return actionmapping.findForward("success");
    }

    public ActionForward deletefile(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
    	DynaActionForm dynaactionform = (DynaActionForm)actionform;

        HttpSession httpsession = httpservletrequest.getSession();
        BoilerVo boilervo =new BoilerVo();
        boilervo=(BoilerVo)httpsession.getAttribute("BOILER_OBJECT");
        setFieldDetails(httpservletrequest, dynaactionform, boilervo);
        setScreenInfo(httpservletrequest);
        //refreshShowInfo(httpservletrequest, boilervo);
        //refreshPermitInfo(httpservletrequest);
        httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
        httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
        httpservletrequest.setAttribute("isComponentEditable", "N");

        String path= (String)dynaactionform.get("pathname");
        String foldername= (String)dynaactionform.get("foldername");
        String Pathname=httpservletrequest.getRealPath("/")+"/clientfolder/"+path+"/"+foldername;
        if(httpsession.getAttribute("DELETE_PERMISSION")==null)
        {
        httpservletrequest.setAttribute("MESSAGE", "No Permission");
        httpservletrequest.setAttribute("MESSAGE_TYPE", "Error");
        setformvariable(path,actionmapping,actionform,httpservletrequest,httpservletresponse);
        return actionmapping.findForward("success");
        }

        try
        {
		        File f=new File(Pathname);
		        if(f.isDirectory())
		        {
			        delete(f);
					if(f.exists()==true)
					{
					    f.delete();
					}
					else
					{
					System.out.println("Error:No File or Folder");
				    httpservletrequest.setAttribute("MESSAGE", "No File or Folder");
	            	httpservletrequest.setAttribute("MESSAGE_TYPE", "Error");
					}
				}
				else
				{
					if(f.exists()==true)
					{
					    f.delete();
					}
					else
					{
					System.out.println("Error:No File or Folder");
				    httpservletrequest.setAttribute("MESSAGE", "No File or Folder");
	            	httpservletrequest.setAttribute("MESSAGE_TYPE", "Error");
					}
				}
        }
		catch(Exception e)
		{
		System.out.println("Exception: "+e);
		}

        setformvariable(path,actionmapping,actionform,httpservletrequest,httpservletresponse);
        return actionmapping.findForward("success");
    }


    public static void delete(File folder)
	{
	        if(folder.exists())
	        {
	                File[] files = folder.listFiles();
	                for(int nFileCur=0; nFileCur < files .length; nFileCur++)
	                {
	                        File oFileCur = files [nFileCur];
	                        if(oFileCur.isDirectory())
	                        {   // call itself to delete the contents of the current folder
	                                delete(oFileCur);
	                        }
	                        oFileCur.delete();
	                }
	        }
	}

    public ActionForward fileupload(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
       	DynaActionForm dynaactionform = (DynaActionForm)actionform;

        HttpSession httpsession = httpservletrequest.getSession();
        BoilerVo boilervo =new BoilerVo();
        boilervo=(BoilerVo)httpsession.getAttribute("BOILER_OBJECT");
        setFieldDetails(httpservletrequest, dynaactionform, boilervo);
        setScreenInfo(httpservletrequest);
        //refreshShowInfo(httpservletrequest, boilervo);
        //refreshPermitInfo(httpservletrequest);
        httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
        httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
        httpservletrequest.setAttribute("isComponentEditable", "N");

        String path= (String)dynaactionform.get("pathname");
        FacilityVo facilityvo = (FacilityVo)httpsession.getAttribute("FACILITY_OBJECT");
        //httpservletrequest.getRealPath("/")+"/clientfolder/"+facilityvo.getDecId()
		try
        {

				FormFile uploadFile   = (FormFile)dynaactionform.get("filename");
				String uploadingFileName    = uploadFile.getFileName();
				//String uploadPath=getServlet().getServletContext().getRealPath("/") +"/upload";
				String uploadPath=httpservletrequest.getRealPath("/")+"/clientfolder/"+path;
				if(!uploadingFileName.equals("")){

				File uploadFileObject = new File(uploadPath, uploadingFileName);
					if(uploadFileObject.exists()==false)
					{
					FileOutputStream fileOutStream = new FileOutputStream(uploadFileObject);
					fileOutStream.write(uploadFile.getFileData());
					fileOutStream.flush();
					fileOutStream.close();
					}
					else
					{
					 System.out.println("Error:Already Exist");
					 httpservletrequest.setAttribute("MESSAGE", "Already Exist");
            		 httpservletrequest.setAttribute("MESSAGE_TYPE", "Error");
					}

				}
				else
				{
				httpservletrequest.setAttribute("MESSAGE", "No File To upload");
                httpservletrequest.setAttribute("MESSAGE_TYPE", "Error");
				System.out.println("Error:No File");
				}

		}
		catch(Exception e)
		{
		System.out.println("Exception: "+e);
		}
		//httpservletrequest.getRealPath("/")+"/clientfolder/"+facilityvo.getDecId()
		setformvariable(path,actionmapping,actionform,httpservletrequest,httpservletresponse);
        return actionmapping.findForward("success");

    }

    public ActionForward back(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {

    	DynaActionForm dynaactionform = (DynaActionForm)actionform;

        HttpSession httpsession = httpservletrequest.getSession();
        BoilerVo boilervo =new BoilerVo();
        boilervo=(BoilerVo)httpsession.getAttribute("BOILER_OBJECT");
        setFieldDetails(httpservletrequest, dynaactionform, boilervo);

        httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
        httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
        httpservletrequest.setAttribute("isComponentEditable", "N");
        setScreenInfo(httpservletrequest);

    	String back="";
        FacilityVo facilityvo = (FacilityVo)httpsession.getAttribute("FACILITY_OBJECT");
        String path= (String)dynaactionform.get("pathname");
        System.out.println("path:"+path);
        if(path.equals(facilityvo.getDecId()+"/boiler/"+boilervo.getId()+"-"+boilervo.getFacilityDesignatedId().trim()))
        {
        back=path;
        }
        else
        {
        back=path.substring(0,path.lastIndexOf("/"));
        }
        //System.out.println("back:"+back);
        String foldername= (String)dynaactionform.get("foldername");
        setformvariable(back,actionmapping,actionform,httpservletrequest,httpservletresponse);
        return actionmapping.findForward("success");
    }

	public ActionForward fuelConsumptionInfoyearconsumtion(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
    	DynaActionForm dynaactionform = (DynaActionForm)actionform;
        String s = (String)dynaactionform.get("fcyear");
        System.out.println("Current Year"+s);
        String year=String.valueOf((Integer.parseInt(s)-1));
        System.out.println("Prev Year"+year);
    	HttpSession httpsession = httpservletrequest.getSession();
        BoilerVo boilervo = new BoilerVo();
        boilervo=(BoilerVo)httpsession.getAttribute("BOILER_OBJECT");
        BoilerEntity boilerentity=new BoilerEntity();

        List list=boilerentity.getFuelConsumptionyearList(boilervo.getId(),year);
    //	System.out.println("ees........"+list.size());
    	httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();

        if(list != null && list.size() > 0)
        {
            Properties properties = UtilityObject.getRollingAverageInfo(list);
            printwriter.println(properties.get("PREVIOUS_CONSUMPTION"));
        }
        else
        {
         printwriter.println("0|0|0|0|0|0|0|0|0|0|0|0");
		}
        printwriter.flush();
        return null;

    }

    	public ActionForward o_fuelConsumptionInfoyearconsumtion(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
    	DynaActionForm dynaactionform = (DynaActionForm)actionform;
        String s = (String)dynaactionform.get("fcyear");
        System.out.println("Current Year"+s);
        String year=String.valueOf((Integer.parseInt(s)-1));
        System.out.println("Prev Year"+year);
    	HttpSession httpsession = httpservletrequest.getSession();
        BoilerVo boilervo = new BoilerVo();
        boilervo=(BoilerVo)httpsession.getAttribute("BOILER_OBJECT");
        BoilerEntity boilerentity=new BoilerEntity();

        List list=boilerentity.geto_FuelConsumptionyearList(boilervo.getId(),year);


    	System.out.println("hai senthil"+list.size());
    	httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();

        if(list != null && list.size() > 0)
        {
            Properties properties = UtilityObject.getRollingAverageInfo(list);
            printwriter.println(properties.get("PREVIOUS_CONSUMPTION"));
        }
        else
        {
         printwriter.println("0|0|0|0|0|0|0|0|0|0|0|0");
		}
        printwriter.flush();
        return null;

    }


    public ActionForward add(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        log.debug("BoilerAction - In Add");
        String s = "";
        String s3 = "";
        HttpSession httpsession = httpservletrequest.getSession();
        BuildingVo buildingvo = (BuildingVo)httpsession.getAttribute("BUILDING_OBJECT");
        BoilerVo boilervo = new BoilerVo();
        boilervo.setBuildingId(buildingvo.getId());
        addUpdateBoilerInfo(dynaactionform, boilervo);
        try
        {
            int i = BoilerEntity.add(boilervo);
            BoilerVo boilervo1 = BoilerEntity.findByPrimaryKey(i);
            if(boilervo1 != null)
            {
                httpsession.setAttribute("BOILER_OBJECT", boilervo1);
                setFieldDetails(httpservletrequest, dynaactionform, boilervo1);
            }
            httpservletrequest.setAttribute("isComponentEditable", "N");
            String s1 = "Added Boiler.";
            String s4 = "CONFIRMATION";
        }
        catch(TrackingException trackingexception)
        {

            String s2 = trackingexception.getMessage();
            String s5 = "ERROR";
            if(log.isErrorEnabled())
                log.error("Error in add", trackingexception);
        }
        setScreenInfo(httpservletrequest);
        return actionmapping.findForward("success");
    }

    private void addUpdateBoilerInfo(DynaActionForm dynaactionform, BoilerVo boilervo)
    {
        boilervo.setFacilityDesignatedId((String)dynaactionform.get("B_facilityDesignatedId"));
        boilervo.setFloor((String)dynaactionform.get("B_floor"));
        boilervo.setStateId((String)dynaactionform.get("B_stateId"));
        String s = (String)dynaactionform.get("B_primaryUse");
        if(UtilityObject.isNotEmpty(s))
            boilervo.setPrimaryUse(Integer.parseInt(s));
        boilervo.setYearInstalled((String)dynaactionform.get("B_yearInstalled"));
        boilervo.setManufacturer((String)dynaactionform.get("B_manufacturer"));
        boilervo.setMake((String)dynaactionform.get("B_make"));
        boilervo.setModel((String)dynaactionform.get("B_model"));
        boilervo.setSerial((String)dynaactionform.get("B_serial"));
        boilervo.setComments((String)dynaactionform.get("B_comments"));

        boilervo.setFootnote((String)dynaactionform.get("B_footnote"));
        boilervo.setOilemmisionfactor((String)dynaactionform.get("B_oilemmisionfactor"));
        boilervo.setGasemmisionfactor((String)dynaactionform.get("B_gasemmisionfactor"));
        boilervo.setGasfuelgapping((String)dynaactionform.get("B_gasfuelgapping"));
        boilervo.setOilfuelgapping((String)dynaactionform.get("B_oilfuelgapping"));
        boilervo.setPrimaryuseother((String)dynaactionform.get("B_primaryuseother"));
        boilervo.setBurnerMake((String)dynaactionform.get("B_burnerMake"));
        boilervo.setBurnerModel((String)dynaactionform.get("B_burnerModel"));
        boilervo.setCapacity((String)dynaactionform.get("B_capacity"));
        s = (String)dynaactionform.get("B_burnerType");
        log.debug((new StringBuilder()).append("Burner Type=").append(dynaactionform.get("B_burnerType")).toString());
        if(UtilityObject.isNotEmpty(s) && s.equalsIgnoreCase("Y"))
            boilervo.setBurnerType(2);
        else
            boilervo.setBurnerType(1);
        s = (String)dynaactionform.get("B_primaryFuel");
        if(UtilityObject.isNotEmpty(s))
            boilervo.setPrimaryFuel(Integer.parseInt(s));
        s = (String)dynaactionform.get("B_secondaryFuel");
        log.debug((new StringBuilder()).append("B_secondaryFuel=").append(dynaactionform.get("B_secondaryFuel")).toString());
        if(UtilityObject.isNotEmpty(s))
            boilervo.setSecondaryFuel(Integer.parseInt(s));
        s = (String)dynaactionform.get("B_fuelFrom");
        if(UtilityObject.isNotEmpty(s))
            boilervo.setFuelFrom(Integer.parseInt(s));
        boilervo.setNycDob((String)dynaactionform.get("B_nycDob"));
        boilervo.setMea((String)dynaactionform.get("B_mea"));
        boilervo.setDep((String)dynaactionform.get("B_dep"));
        boilervo.setSechduelC(UtilityObject.convertBoolean((String)dynaactionform.get("B_sechduelC")));
        boilervo.setPlanApproval(UtilityObject.convertBoolean((String)dynaactionform.get("B_planApproval")));
        s = (String)dynaactionform.get("B_stackFrom");
        if(UtilityObject.isNotEmpty(s))
            boilervo.setStackFrom(Integer.parseInt(s));
        boilervo.setStackTestRequired((String)dynaactionform.get("B_stackTestRequired"));
        boilervo.setOtherBoilersCombined(UtilityObject.convertBoolean((String)dynaactionform.get("B_otherBoilersCombined")));
        String s1 = (String)dynaactionform.get("B_parameters1");
        String s2 = (String)dynaactionform.get("B_parameters2");
        String s3 = (String)dynaactionform.get("B_parameters3");
        log.debug((new StringBuilder()).append("param1=").append(s1).toString());
        log.debug((new StringBuilder()).append("param2=").append(s2).toString());
        log.debug((new StringBuilder()).append("param3=").append(s3).toString());
        int i = 0;
        if(UtilityObject.isNotEmpty(s1) && s1.equalsIgnoreCase("Y"))
            i++;
        if(UtilityObject.isNotEmpty(s2) && s1.equalsIgnoreCase("Y"))
            i += 2;
        if(UtilityObject.isNotEmpty(s3) && s1.equalsIgnoreCase("Y"))
            i += 4;
        log.debug((new StringBuilder()).append(i).append("").toString());
        boilervo.setParameters(i);
        s = (String)dynaactionform.get("B_testOnFuel");
        int j = 0;
        if(UtilityObject.isNotEmpty(s) && s.equalsIgnoreCase("Y"))
            j = 10;
        boolean flag = false;
        s = (String)dynaactionform.get("B_testOnFuelOilType");
        if(UtilityObject.isNotEmpty(s))
        {
            int k = Integer.parseInt(s);
            if(k > 0)
                j += k;
        }
        boilervo.setTestOnFuel(j);
        boilervo.setStackTestProtSubmited(UtilityObject.convertBoolean((String)dynaactionform.get("B_stackTestProtSubmited")));
        boilervo.setTestConductedBy((String)dynaactionform.get("B_testConductedBy"));
        boilervo.setTestReportSubmited((String)dynaactionform.get("B_testReportSubmited"));
        s = UtilityObject.convertToString(UtilityObject.convertToDate((String)dynaactionform.get("B_protocolSubmitDate")), "yyyy-MM-dd");
        boilervo.setProtocolSubmitDate(s);
        String s4 = (String)dynaactionform.get("ModifiedInPast");
        String s5 = (String)dynaactionform.get("DisconnectedYear");
        boilervo.setDisconnectedYear(s5);
        boilervo.setModifiedInPast(Integer.parseInt(s4));
        boilervo.setBoilerTestPassed(UtilityObject.convertBoolean((String)dynaactionform.get("B_boilerTestPassed")));
        boilervo.setRetestPlanned(UtilityObject.convertBoolean((String)dynaactionform.get("B_retestPlanned")));
        s = UtilityObject.convertToString(UtilityObject.convertToDate((String)dynaactionform.get("B_nextStackTestDate")), "yyyy-MM-dd");
        boilervo.setNextStackTestDate(s);
        s = UtilityObject.convertToString(UtilityObject.convertToDate((String)dynaactionform.get("B_StackTestDate")), "yyyy-MM-dd");
        boilervo.setStackTestDate(s);
        boilervo.setBoilerInCompliance(UtilityObject.convertBoolean((String)dynaactionform.get("B_isBoilerInCompliance")));
        boilervo.setBoilerSubjectToNspc(UtilityObject.convertBoolean((String)dynaactionform.get("B_isBoilerSubjectToNspc")));
        boilervo.setBlrModifiedInPast(UtilityObject.convertBoolean((String)dynaactionform.get("B_blrModifiedInPast")));
        s = UtilityObject.convertToString(UtilityObject.convertToDate((String)dynaactionform.get("B_blrModifiedOn")), "yyyy-MM-dd");
        boilervo.setBlrModifiedOn(s);
        boilervo.setModifyPermitSub(UtilityObject.convertBoolean((String)dynaactionform.get("B_isModifyPermitSub")));
        boilervo.setAnyEmission(UtilityObject.convertBoolean((String)dynaactionform.get("B_anyEmission")));
        boilervo.setBlrSubjectToFederal(UtilityObject.convertBoolean((String)dynaactionform.get("B_blrSubjectToFederal")));
        boilervo.setFuelCaping((String)dynaactionform.get("B_fuelCaping"));
        boilervo.setRollingAvg(UtilityObject.convertBoolean((String)dynaactionform.get("B_isRollingAvg")));
        boilervo.setOpacityMonInst(UtilityObject.convertBoolean((String)dynaactionform.get("B_isOpacityMonInst")));
        boilervo.setPrfmTestProtSub(UtilityObject.convertBoolean((String)dynaactionform.get("B_prfmTestProtSub")));
        s = UtilityObject.convertToString(UtilityObject.convertToDate((String)dynaactionform.get("B_prfmTestProtSubDate")), "yyyy-MM-dd");
        boilervo.setPrfmTestProtSubDate(s);
        boilervo.setPrfmTestRptSub(UtilityObject.convertBoolean((String)dynaactionform.get("B_prfmTestRptSub")));
        s = UtilityObject.convertToString(UtilityObject.convertToDate((String)dynaactionform.get("B_prfmTestRptSubDate")), "yyyy-MM-dd");
        boilervo.setPrfmTestRptSubDate(s);
        boilervo.setSulpContentAnaly(UtilityObject.convertBoolean((String)dynaactionform.get("B_isSulpContentAnaly")));
        boilervo.setSulphurContent((String)dynaactionform.get("B_sulphurContent"));
        boilervo.setFuelLimitsForBlr(UtilityObject.convertBoolean((String)dynaactionform.get("B_fuelLimitsForBlr")));
        boilervo.setFuelLimitQnty((String)dynaactionform.get("B_fuelLimitQnty"));
        boilervo.setNitrogenContent(UtilityObject.convertBoolean((String)dynaactionform.get("B_nitrogenContent")));
        boilervo.setNitrogenContentQnty((String)dynaactionform.get("B_nitrogenContentQnty"));
        boilervo.setB_oilso2((String)dynaactionform.get("B_oilso2"));
        boilervo.setB_gasso2((String)dynaactionform.get("B_gasso2"));
        boilervo.setB_so2allowable((String)dynaactionform.get("B_so2allowable"));
		boilervo.setB_noxallowable((String)dynaactionform.get("B_noxallowable"));
		boilervo.setDobsignoff((String)dynaactionform.get("dobsignoff"));
		boilervo.setFiredepartmentapproval((String)dynaactionform.get("firedepartmentapproval"));
		String ss = (String)dynaactionform.get("depPermit");
        boilervo.setDepPermit(ss);
        boilervo.setDepPermitExpire((String)dynaactionform.get("depPermitExpire"));        
        boilervo.setDobFiling((String)dynaactionform.get("dobfiling"));
        boilervo.setBoilerTuneup((String)dynaactionform.get("boilerTuneup"));
    }

    public ActionForward view(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {

        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        log.debug("BoilerAction - In view");
        httpservletrequest.setAttribute("isComponentEditable", "N");
        HttpSession httpsession = httpservletrequest.getSession();
        String s = (String)dynaactionform.get("id");
        int i = -99;
        if(s != null && s.trim().length() > 0)
            i = Integer.parseInt(s);
        BoilerVo boilervo = BoilerEntity.findByPrimaryKey(i);
        if(boilervo != null)
        {
            httpsession.setAttribute("BOILER_OBJECT", boilervo);
            setFieldDetails(httpservletrequest, dynaactionform, boilervo);
        }
        setScreenInfo(httpservletrequest);
        return actionmapping.findForward("success");
    }

    public ActionForward edit(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        log.debug("BoilerAction - In Edit");
        httpservletrequest.setAttribute("isComponentEditable", "Y");
        httpservletrequest.setAttribute("isItForEdit", "Y");
        HttpSession httpsession = httpservletrequest.getSession();
        String s = (String)dynaactionform.get("id");
        int i = -99;
        if(s != null && s.trim().length() > 0)
            i = Integer.parseInt(s);
        BoilerVo boilervo = BoilerEntity.findByPrimaryKey(i);
        if(boilervo != null)
        {
            httpsession.setAttribute("BOILER_OBJECT", boilervo);
            setFieldDetailsforedit(httpservletrequest, dynaactionform, boilervo, true);
        }
        setScreenInfo(httpservletrequest);
        return actionmapping.findForward("success");
    }

    public ActionForward viewexist(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        log.debug("BoilerAction - In Edit");
        httpservletrequest.setAttribute("isComponentEditable", "Y");
        httpservletrequest.setAttribute("isItForEdit", "N");
        HttpSession httpsession = httpservletrequest.getSession();
        String s = (String)dynaactionform.get("id");
        int i = -99;
        if(s != null && s.trim().length() > 0)
            i = Integer.parseInt(s);
        BoilerVo boilervo = BoilerEntity.findByPrimaryKey(i);
        if(boilervo != null)
        {
            httpsession.setAttribute("BOILER_OBJECT", boilervo);
            setFieldDetailsforedit(httpservletrequest, dynaactionform, boilervo, true);
        }
        setScreenInfoforsearch(httpservletrequest);
        return actionmapping.findForward("success");
    }

    public ActionForward update(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        log.debug("BoilerAction - In update");
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        HttpSession httpsession = httpservletrequest.getSession();
        BoilerVo boilervox=new BoilerVo();
        boilervox = (BoilerVo)httpsession.getAttribute("BOILER_OBJECT");
        String facilityDesignatedid=boilervox.getFacilityDesignatedId();

        BoilerVo boilervo = (BoilerVo)httpsession.getAttribute("BOILER_OBJECT");
        BoilerVo boilervo1 = boilervo;
        addUpdateBoilerInfo(dynaactionform, boilervo1);
        BoilerEntity.update(boilervo1);
        BoilerVo boilervo2 = BoilerEntity.findByPrimaryKey(boilervo.getId());
        if(boilervo2 != null)
        {
            FacilityVo facilityvo = (FacilityVo)httpsession.getAttribute("FACILITY_OBJECT");
            try
        {

		        File f=new File(httpservletrequest.getRealPath("/")+"/clientfolder/"+facilityvo.getDecId()+"/boiler/"+boilervox.getId()+"-"+facilityDesignatedid.trim());


		        if(f.isDirectory())
		        {

			    	File newFileName=new File(httpservletrequest.getRealPath("/")+"/clientfolder/"+facilityvo.getDecId()+"/boiler/"+boilervo2.getId()+"-"+boilervo2.getFacilityDesignatedId().trim());
				   	f.renameTo(newFileName);

				}
				else
				{

				}
        }
		catch(Exception e)
		{
		System.out.println("Exception: "+e);
		}
        httpsession.setAttribute("BOILER_OBJECT", boilervo2);
        setFieldDetails(httpservletrequest, dynaactionform, boilervo2);

        }
        setScreenInfo(httpservletrequest);
        httpservletrequest.setAttribute("isComponentEditable", "N");
        return actionmapping.findForward("success");
    }

    public ActionForward delete(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        log.debug("BoilerAction - In update");
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        HttpSession httpsession = httpservletrequest.getSession();
        String s = (String)dynaactionform.get("id");
        int i = -99;
        if(s != null && s.trim().length() > 0)
            i = Integer.parseInt(s);
        BoilerVo boilervo = BoilerEntity.findByPrimaryKey(i);
        if(boilervo != null)
            httpsession.setAttribute("BOILER_OBJECT", boilervo);
        BoilerVo boilervo1 = (BoilerVo)httpsession.getAttribute("BOILER_OBJECT");
        BoilerVo boilervo2 = boilervo1;
        BoilerVo boilervo3 = boilervo1;
        BoilerEntity.delete(boilervo2);

        FacilityVo facilityvo = (FacilityVo)httpsession.getAttribute("FACILITY_OBJECT");
        try
        {

		        File f=new File(httpservletrequest.getRealPath("/")+"/clientfolder/"+facilityvo.getDecId()+"/boiler/"+boilervo.getId()+"-"+boilervo.getFacilityDesignatedId().trim());
		        if(f.isDirectory())
		        {
			        delete(f);
					if(f.exists()==true)
					{
					    f.delete();
					}
					else
					{
					System.out.println("Error:No File or Folder");
					}
				}
				else
				{

				}
        }
		catch(Exception e)
		{
		System.out.println("Exception: "+e);
		}
        return actionmapping.findForward("success1");
    }

    public ActionForward depPermitInfo(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        log.debug("BoilerAction - In depPermitInfo");
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        HttpSession httpsession = httpservletrequest.getSession();
        BoilerVo boilervo = (BoilerVo)httpsession.getAttribute("BOILER_OBJECT");
        BoilerPermitInfoVo boilerpermitinfovo = new BoilerPermitInfoVo();
        boilerpermitinfovo.setBoilerId(boilervo.getId());
        boilerpermitinfovo.setDepId(1);
        boilerpermitinfovo.setIssueDate(UtilityObject.convertToDate((String)dynaactionform.get("depIssueDate")));
        boilerpermitinfovo.setExpirationDate(UtilityObject.convertToDate((String)dynaactionform.get("depExpDate")));
        boilerpermitinfovo.setInspecSubmittedDate(UtilityObject.convertToDate((String)dynaactionform.get("depInspSubDate")));
      //boilerpermitinfovo.setDepCompliancecomments((String)dynaactionform.get("depCompliancecomments"));
        boilerpermitinfovo.setDepExpirationNote((String)dynaactionform.get("depExpirationNote"));

        int i = BoilerEntity.addPermit(boilerpermitinfovo);
        log.debug((new StringBuilder()).append("Added dep permit id=").append(i).toString());
        httpservletrequest.setAttribute("isComponentEditable", "N");
        setFieldDetails(httpservletrequest, dynaactionform, boilervo);
        setScreenInfo(httpservletrequest);
        return actionmapping.findForward("success");
    }

    public ActionForward deleteDepPermit(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        log.debug("BoilerAction - In updateDepPermitInfo");
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        HttpSession httpsession = httpservletrequest.getSession();
        BoilerVo boilervo = (BoilerVo)httpsession.getAttribute("BOILER_OBJECT");
        String s = httpservletrequest.getParameter("hdnPermitId");
        int i = -99;
        if(UtilityObject.isNotEmpty(s))
            i = Integer.parseInt(s);
        else
            throw new Exception("DEP Permit Id is null while updating the DEP Permit");
        BoilerPermitInfoVo boilerpermitinfovo = BoilerEntity.findPermit(i);
        BoilerEntity.deletePermit(boilerpermitinfovo);
        httpservletrequest.setAttribute("isComponentEditable", "N");
        setFieldDetails(httpservletrequest, dynaactionform, boilervo);
        setScreenInfo(httpservletrequest);
        return actionmapping.findForward("success");
    }

    public ActionForward editDepPermit(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        log.debug("BoilerAction - In editDepPermit");
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        HttpSession httpsession = httpservletrequest.getSession();
        BoilerVo boilervo = (BoilerVo)httpsession.getAttribute("BOILER_OBJECT");
        httpservletrequest.setAttribute("isComponentEditable", "N");
        httpservletrequest.setAttribute("EDIT_DEP_PERMIT", "Y");
        setFieldDetails(httpservletrequest, dynaactionform, boilervo);
        setScreenInfo(httpservletrequest);
        httpservletrequest.setAttribute("hdnPermitId",httpservletrequest.getParameter("hdnPermitId"));
        return actionmapping.findForward("success");
    }

    public ActionForward updateDepPermitInfo(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        log.debug("BoilerAction - In updateDepPermitInfo");
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        HttpSession httpsession = httpservletrequest.getSession();
        BoilerVo boilervo = (BoilerVo)httpsession.getAttribute("BOILER_OBJECT");
        String s = httpservletrequest.getParameter("hdnPermitId");
        int i = -99;
        if(UtilityObject.isNotEmpty(s))
            i = Integer.parseInt(s);
        else
            throw new Exception("DEP Permit Id is null while updating the DEP Permit");
        BoilerPermitInfoVo boilerpermitinfovo = BoilerEntity.findPermit(i);
        boilerpermitinfovo.setIssueDate(UtilityObject.convertToDate((String)dynaactionform.get("depIssueDate")));
        boilerpermitinfovo.setExpirationDate(UtilityObject.convertToDate((String)dynaactionform.get("depExpDate")));
        boilerpermitinfovo.setInspecSubmittedDate(UtilityObject.convertToDate((String)dynaactionform.get("depInspSubDate")));
//      boilerpermitinfovo.setDepCompliancecomments((String)dynaactionform.get("depCompliancecomments"));
        boilerpermitinfovo.setDepExpirationNote((String)dynaactionform.get("depExpirationNote"));
        BoilerEntity.updatePermit(boilerpermitinfovo);
        httpservletrequest.setAttribute("isComponentEditable", "N");
        setFieldDetails(httpservletrequest, dynaactionform, boilervo);
        setScreenInfo(httpservletrequest);
        return actionmapping.findForward("success");
    }

    public ActionForward dobPermitInfo(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        log.debug("BoilerAction - In dobPermitInfo");
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        HttpSession httpsession = httpservletrequest.getSession();
        BoilerVo boilervo = (BoilerVo)httpsession.getAttribute("BOILER_OBJECT");
        BoilerPermitInfoVo boilerpermitinfovo = new BoilerPermitInfoVo();
        boilerpermitinfovo.setBoilerId(boilervo.getId());
        boilerpermitinfovo.setDepId(2);
        boilerpermitinfovo.setIssueDate(UtilityObject.convertToDate((String)dynaactionform.get("dobIssueDate")));
        boilerpermitinfovo.setExpirationDate(UtilityObject.convertToDate((String)dynaactionform.get("dobExpDate")));
        boilerpermitinfovo.setInspecSubmittedDate(UtilityObject.convertToDate((String)dynaactionform.get("dobInspSubDate")));
        boilerpermitinfovo.setDobExpirationNote((String)dynaactionform.get("dobExpirationNote"));
        boilerpermitinfovo.setLastinspectionDate(UtilityObject.convertToDate((String)dynaactionform.get("dobLastinspectionDate")));        
        
     //   boilerpermitinfovo.setInspectionType((String)dynaactionform.get("dobInspectionType"));        
      //  if(UtilityObject.convertBoolean((String)dynaactionform.get("dobInspectionType")))
     //       boilerpermitinfovo.setInspectionType(true);
     //   else
     //       boilerpermitinfovo.setInspectionType(false);
     
         String typ = (String)dynaactionform.get("dobInspectionType");
         if(typ.equalsIgnoreCase("Internal"))
            boilerpermitinfovo.setInspectionType(true);
       else
            boilerpermitinfovo.setInspectionType(false);
     
            
        int i = BoilerEntity.addPermit(boilerpermitinfovo);
        log.debug((new StringBuilder()).append("Added dob permit id=").append(i).toString());
        httpservletrequest.setAttribute("isComponentEditable", "N");
        setFieldDetails(httpservletrequest, dynaactionform, boilervo);
        setScreenInfo(httpservletrequest);
        return actionmapping.findForward("success");
    }

    public ActionForward deleteDobPermit(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        log.debug("BoilerAction - In updateDobPermitInfo");
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        HttpSession httpsession = httpservletrequest.getSession();
        BoilerVo boilervo = (BoilerVo)httpsession.getAttribute("BOILER_OBJECT");
        String s = httpservletrequest.getParameter("hdnPermitId");
        int i = -99;
        if(UtilityObject.isNotEmpty(s))
            i = Integer.parseInt(s);
        else
            throw new Exception("DOB Permit Id is null while updating the DOB Permit");
        BoilerPermitInfoVo boilerpermitinfovo = BoilerEntity.findPermit(i);
        BoilerEntity.deletePermit(boilerpermitinfovo);
        httpservletrequest.setAttribute("isComponentEditable", "N");
        setFieldDetails(httpservletrequest, dynaactionform, boilervo);
        setScreenInfo(httpservletrequest);
        return actionmapping.findForward("success");
    }

    public ActionForward editDobPermit(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        log.debug("BoilerAction - In editDobPermit");
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        HttpSession httpsession = httpservletrequest.getSession();
        BoilerVo boilervo = (BoilerVo)httpsession.getAttribute("BOILER_OBJECT");
        httpservletrequest.setAttribute("isComponentEditable", "N");
        httpservletrequest.setAttribute("EDIT_DOB_PERMIT", "Y");
        setFieldDetails(httpservletrequest, dynaactionform, boilervo);
        setScreenInfo(httpservletrequest);
        httpservletrequest.setAttribute("hdnPermitId",httpservletrequest.getParameter("hdnPermitId"));
        return actionmapping.findForward("success");
    }

    public ActionForward updateDobPermitInfo(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        log.debug("BoilerAction - In updateDobPermitInfo");
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        HttpSession httpsession = httpservletrequest.getSession();
        BoilerVo boilervo = (BoilerVo)httpsession.getAttribute("BOILER_OBJECT");
        String s = httpservletrequest.getParameter("hdnPermitId");
        int i = -99;
        if(UtilityObject.isNotEmpty(s))
            i = Integer.parseInt(s);
        else
            throw new Exception("DOB Permit Id is null while updating the DOB Permit");
        BoilerPermitInfoVo boilerpermitinfovo = BoilerEntity.findPermit(i);
        boilerpermitinfovo.setIssueDate(UtilityObject.convertToDate((String)dynaactionform.get("dobIssueDate")));
        boilerpermitinfovo.setExpirationDate(UtilityObject.convertToDate((String)dynaactionform.get("dobExpDate")));
        boilerpermitinfovo.setInspecSubmittedDate(UtilityObject.convertToDate((String)dynaactionform.get("dobInspSubDate")));
//      boilerpermitinfovo.setDobCompliancecomments((String)dynaactionform.get("dobCompliancecomments"));
        boilerpermitinfovo.setDobExpirationNote((String)dynaactionform.get("dobExpirationNote"));
        boilerpermitinfovo.setLastinspectionDate(UtilityObject.convertToDate((String)dynaactionform.get("dobLastinspectionDate")));
       // boilerpermitinfovo.setInspectionType((String)dynaactionform.get("dobInspectionType"));
        
                
        /* if(UtilityObject.convertBoolean((String)dynaactionform.get("dobInspectionType")))
            boilerpermitinfovo.setInspectionType(true);
        else
            boilerpermitinfovo.setInspectionType(false);*/
            
            String typup = (String)dynaactionform.get("dobInspectionType");
         if(typup.equalsIgnoreCase("Internal"))
            boilerpermitinfovo.setInspectionType(true);
       else
            boilerpermitinfovo.setInspectionType(false);
            
        BoilerEntity.updatePermit(boilerpermitinfovo);
        httpservletrequest.setAttribute("isComponentEditable", "N");
        setFieldDetails(httpservletrequest, dynaactionform, boilervo);
        setScreenInfo(httpservletrequest);
        return actionmapping.findForward("success");
                
    }

    public ActionForward annualTuneUpInfo(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        log.debug("BoilerAction - In annualTuneUpInfo");
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        HttpSession httpsession = httpservletrequest.getSession();
        BoilerVo boilervo = (BoilerVo)httpsession.getAttribute("BOILER_OBJECT");
        BoilerAnnualTuneUpVo boilerannualtuneupvo = new BoilerAnnualTuneUpVo();
        boilerannualtuneupvo.setBoilerId(boilervo.getId());

        boilerannualtuneupvo.setDate(UtilityObject.convertToDate((String)dynaactionform.get("tuneUpDate")));



        boilerannualtuneupvo.setPerformedBy((String)dynaactionform.get("tuneUpPerfBy"));
        if(UtilityObject.convertBoolean((String)dynaactionform.get("tuneUpRecKept")))
            boilerannualtuneupvo.setRecordsKept(true);
        else
            boilerannualtuneupvo.setRecordsKept(false);
        boilerannualtuneupvo.setYear((String)dynaactionform.get("tuneUpYear"));
        int i = BoilerEntity.addAnnualTuneup(boilerannualtuneupvo);
        log.debug((new StringBuilder()).append("Added tune up id=").append(i).toString());
        httpservletrequest.setAttribute("isComponentEditable", "N");
        setFieldDetails(httpservletrequest, dynaactionform, boilervo);
        setScreenInfo(httpservletrequest);
        return actionmapping.findForward("success");
    }

    public ActionForward editAnnualTuneUp(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        log.debug("BoilerAction - In editAnnualTuneUp");
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        HttpSession httpsession = httpservletrequest.getSession();
        BoilerVo boilervo = (BoilerVo)httpsession.getAttribute("BOILER_OBJECT");
        httpservletrequest.setAttribute("isComponentEditable", "N");
        httpservletrequest.setAttribute("EDIT_ANNUAL_TUNE_UP", "Y");
        setFieldDetails(httpservletrequest, dynaactionform, boilervo);
        setScreenInfo(httpservletrequest);
        httpservletrequest.setAttribute("hdnPermitId",httpservletrequest.getParameter("hdnPermitId"));
        return actionmapping.findForward("success");
    }

    public ActionForward deleteAnnualTuneUp(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        log.debug("BoilerAction - In updateAnnualTuneUpInfo");
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        HttpSession httpsession = httpservletrequest.getSession();
        BoilerVo boilervo = (BoilerVo)httpsession.getAttribute("BOILER_OBJECT");
        String s = httpservletrequest.getParameter("hdnPermitId");
        int i = -99;
        if(UtilityObject.isNotEmpty(s))
            i = Integer.parseInt(s);
        else
            throw new Exception("DOB Permit Id is null while updating the DOB Permit");
        BoilerAnnualTuneUpVo boilerannualtuneupvo = BoilerEntity.findAnnualTuneUp(i);
        BoilerEntity.deleteAnnualTuneup(boilerannualtuneupvo);
        httpservletrequest.setAttribute("isComponentEditable", "N");
        setFieldDetails(httpservletrequest, dynaactionform, boilervo);
        setScreenInfo(httpservletrequest);
        return actionmapping.findForward("success");
    }

    public ActionForward updateAnnualTuneUpInfo(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        log.debug("BoilerAction - In updateAnnualTuneUpInfo");
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        HttpSession httpsession = httpservletrequest.getSession();
        BoilerVo boilervo = (BoilerVo)httpsession.getAttribute("BOILER_OBJECT");
        String s = httpservletrequest.getParameter("hdnPermitId");
        int i = -99;
        if(UtilityObject.isNotEmpty(s))
            i = Integer.parseInt(s);
        else
            throw new Exception("DOB Permit Id is null while updating the DOB Permit");
        BoilerAnnualTuneUpVo boilerannualtuneupvo = BoilerEntity.findAnnualTuneUp(i);
        boilerannualtuneupvo.setDate(UtilityObject.convertToDate((String)dynaactionform.get("tuneUpDate")));
        boilerannualtuneupvo.setPerformedBy((String)dynaactionform.get("tuneUpPerfBy"));
        if(UtilityObject.convertBoolean((String)dynaactionform.get("tuneUpRecKept")))
            boilerannualtuneupvo.setRecordsKept(true);
        else
            boilerannualtuneupvo.setRecordsKept(false);
        boilerannualtuneupvo.setYear((String)dynaactionform.get("tuneUpYear"));
        BoilerEntity.updateAnnualTuneup(boilerannualtuneupvo);
        httpservletrequest.setAttribute("isComponentEditable", "N");
        setFieldDetails(httpservletrequest, dynaactionform, boilervo);
        setScreenInfo(httpservletrequest);
        return actionmapping.findForward("success");
    }
//*************************Pressure valve test**************************************//

	public ActionForward valveTestInfo(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        log.debug("BoilerAction - In valvetestinfo");
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        HttpSession httpsession = httpservletrequest.getSession();
        BoilerVo boilervo = (BoilerVo)httpsession.getAttribute("BOILER_OBJECT");
        BoilerValveTestVo boilervalvetestvo = new BoilerValveTestVo();
        boilervalvetestvo.setBoilerId(boilervo.getId());
        
		boilervalvetestvo.setYear((String)dynaactionform.get("valveTestYear"));
        boilervalvetestvo.setDate(UtilityObject.convertToDate((String)dynaactionform.get("valveTestDate")));
        boilervalvetestvo.setPerformedBy((String)dynaactionform.get("valveTestPerfBy"));               
        int i = BoilerEntity.addValveTest(boilervalvetestvo);
        log.debug((new StringBuilder()).append("Added valvetest id=").append(i).toString());
        httpservletrequest.setAttribute("isComponentEditable", "N");
        setFieldDetails(httpservletrequest, dynaactionform, boilervo);
        setScreenInfo(httpservletrequest);
        return actionmapping.findForward("success");
    }

    public ActionForward editValveTest(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        log.debug("BoilerAction - In editvalvetest");
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        HttpSession httpsession = httpservletrequest.getSession();
        BoilerVo boilervo = (BoilerVo)httpsession.getAttribute("BOILER_OBJECT");
        httpservletrequest.setAttribute("isComponentEditable", "N");
        httpservletrequest.setAttribute("EDIT_VALVE_TEST", "Y");
        setFieldDetails(httpservletrequest, dynaactionform, boilervo);
        setScreenInfo(httpservletrequest);
        httpservletrequest.setAttribute("hdnPermitId",httpservletrequest.getParameter("hdnPermitId"));
        return actionmapping.findForward("success");
    }

    public ActionForward deleteValveTest(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        log.debug("BoilerAction - In deleteValveTest");
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        HttpSession httpsession = httpservletrequest.getSession();
        BoilerVo boilervo = (BoilerVo)httpsession.getAttribute("BOILER_OBJECT");
        String s = httpservletrequest.getParameter("hdnPermitId");
        int i = -99;
        if(UtilityObject.isNotEmpty(s))
            i = Integer.parseInt(s);
        else
            throw new Exception("Pressure test Id is null while updating");
        BoilerValveTestVo boilervalvetestvo = BoilerEntity.findValveTest(i);
        BoilerEntity.deleteValveTest(boilervalvetestvo);
        httpservletrequest.setAttribute("isComponentEditable", "N");
        setFieldDetails(httpservletrequest, dynaactionform, boilervo);
        setScreenInfo(httpservletrequest);
        return actionmapping.findForward("success");
    }

    public ActionForward updateValveTestInfo(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        log.debug("BoilerAction - In updatevalvetest");
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        HttpSession httpsession = httpservletrequest.getSession();
        BoilerVo boilervo = (BoilerVo)httpsession.getAttribute("BOILER_OBJECT");
        String s = httpservletrequest.getParameter("hdnPermitId");
        int i = -99;
        if(UtilityObject.isNotEmpty(s))
            i = Integer.parseInt(s);
        else
            throw new Exception("Pressure test Id is null while updating");
        BoilerValveTestVo boilervalvetestvo = BoilerEntity.findValveTest(i);
        
        boilervalvetestvo.setYear((String)dynaactionform.get("valveTestYear"));
        boilervalvetestvo.setDate(UtilityObject.convertToDate((String)dynaactionform.get("valveTestDate")));
        boilervalvetestvo.setPerformedBy((String)dynaactionform.get("valveTestPerfBy")); 
        
        BoilerEntity.updateValveTest(boilervalvetestvo);
        httpservletrequest.setAttribute("isComponentEditable", "N");
        setFieldDetails(httpservletrequest, dynaactionform, boilervo);
        setScreenInfo(httpservletrequest);
        return actionmapping.findForward("success");
    }
    
    //*********************************************************End*****************************************************//
    public ActionForward fuelConsumptionInfo(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        log.debug("BoilerAction - In fuelConsumptionInfo");
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        HttpSession httpsession = httpservletrequest.getSession();
        BoilerVo boilervo = (BoilerVo)httpsession.getAttribute("BOILER_OBJECT");
        FuelConsumptionVo fuelconsumptionvo = new FuelConsumptionVo();
        fuelconsumptionvo.setEntityId(boilervo.getId());
        String ttype=(String)dynaactionform.get("bctype");
        if(ttype.equals("1"))
        {
        fuelconsumptionvo.setBctype(ttype);
        fuelconsumptionvo.setYear((String)dynaactionform.get("fcyear"));
        fuelconsumptionvo.setJan(getValue("jan", dynaactionform));
        fuelconsumptionvo.setFeb(getValue("feb", dynaactionform));
        fuelconsumptionvo.setMar(getValue("mar", dynaactionform));
        fuelconsumptionvo.setApr(getValue("apr", dynaactionform));
        fuelconsumptionvo.setMay(getValue("may", dynaactionform));
        fuelconsumptionvo.setJun(getValue("jun", dynaactionform));
        fuelconsumptionvo.setJul(getValue("jul", dynaactionform));
        fuelconsumptionvo.setAug(getValue("aug", dynaactionform));
        fuelconsumptionvo.setSep(getValue("sep", dynaactionform));
        fuelconsumptionvo.setOct(getValue("oct", dynaactionform));
        fuelconsumptionvo.setNov(getValue("nov", dynaactionform));
        fuelconsumptionvo.setDec(getValue("dec", dynaactionform));
        fuelconsumptionvo.setRollingConsumption(getValue("consumption", dynaactionform));
        fuelconsumptionvo.setCompliant(UtilityObject.convertBoolean((String)dynaactionform.get("compliance")));
        fuelconsumptionvo.setLocked(UtilityObject.convertBoolean((String)dynaactionform.get("lock")));
        if(UtilityObject.isNotEmpty((String)dynaactionform.get("fuelConsumptionId")))
        {
            fuelconsumptionvo.setId(UtilityObject.getIntFromString((String)dynaactionform.get("fuelConsumptionId")));
            BoilerEntity.updateFuelConsumption(fuelconsumptionvo);
            log.debug("Updated fuel object");
        } else
        {
            int i = BoilerEntity.addFuelConsumption(fuelconsumptionvo);
            log.debug((new StringBuilder()).append("Added fuel id=").append(i).toString());
        }

       }
       else
       {
       	fuelconsumptionvo.setBctype(ttype);
        fuelconsumptionvo.setYear((String)dynaactionform.get("o_fcyear"));
        fuelconsumptionvo.setJan(getValue("o_jan", dynaactionform));
        fuelconsumptionvo.setFeb(getValue("o_feb", dynaactionform));
        fuelconsumptionvo.setMar(getValue("o_mar", dynaactionform));
        fuelconsumptionvo.setApr(getValue("o_apr", dynaactionform));
        fuelconsumptionvo.setMay(getValue("o_may", dynaactionform));
        fuelconsumptionvo.setJun(getValue("o_jun", dynaactionform));
        fuelconsumptionvo.setJul(getValue("o_jul", dynaactionform));
        fuelconsumptionvo.setAug(getValue("o_aug", dynaactionform));
        fuelconsumptionvo.setSep(getValue("o_sep", dynaactionform));
        fuelconsumptionvo.setOct(getValue("o_oct", dynaactionform));
        fuelconsumptionvo.setNov(getValue("o_nov", dynaactionform));
        fuelconsumptionvo.setDec(getValue("o_dec", dynaactionform));
        fuelconsumptionvo.setRollingConsumption(getValue("o_consumption", dynaactionform));
        fuelconsumptionvo.setCompliant(UtilityObject.convertBoolean((String)dynaactionform.get("o_compliance")));
        fuelconsumptionvo.setLocked(UtilityObject.convertBoolean((String)dynaactionform.get("o_lock")));
        if(UtilityObject.isNotEmpty((String)dynaactionform.get("o_fuelConsumptionId")))
        {
            fuelconsumptionvo.setId(UtilityObject.getIntFromString((String)dynaactionform.get("o_fuelConsumptionId")));
            BoilerEntity.updateFuelConsumption(fuelconsumptionvo);
            log.debug("Updated fuel object");
        } else
        {
            int i = BoilerEntity.addFuelConsumption(fuelconsumptionvo);
            log.debug((new StringBuilder()).append("Added fuel id=").append(i).toString());
        }

       }

        boilervo.setFuelConsumptionList(null);
        boilervo.seto_FuelConsumptionList(null);
        httpservletrequest.setAttribute("isComponentEditable", "N");
        setFieldDetails(httpservletrequest, dynaactionform, boilervo);
        setScreenInfo(httpservletrequest);
        return actionmapping.findForward("success");
    }
    public ActionForward deletefuelConsumptionInfo(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        log.debug("BoilerAction - In fuelConsumptionInfo");
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        HttpSession httpsession = httpservletrequest.getSession();
        BoilerVo boilervo = (BoilerVo)httpsession.getAttribute("BOILER_OBJECT");
        FuelConsumptionVo fuelconsumptionvo = new FuelConsumptionVo();
        fuelconsumptionvo.setEntityId(boilervo.getId());
        int tid=Integer.parseInt((String)dynaactionform.get("fueldeleteConsumptionId"));
        BoilerEntity.deletefuelconsumption(tid);
        boilervo.seto_FuelConsumptionList(null);
        boilervo.setFuelConsumptionList(null);
        httpservletrequest.setAttribute("isComponentEditable", "N");
        setFieldDetails(httpservletrequest, dynaactionform, boilervo);
        setScreenInfo(httpservletrequest);
        return actionmapping.findForward("success");
    }

    public ActionForward displayControl(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        log.debug("BoilerAction - In displayControl");
        String s = httpservletrequest.getParameter("hdnContext");
        if(UtilityObject.isNotEmpty(s) && s.equalsIgnoreCase("Y"))
        {
            httpservletrequest.setAttribute("showAddBtn", "Y");
            httpservletrequest.setAttribute("isItForEdit", "Y");
            httpservletrequest.setAttribute("isComponentEditable", "Y");
        }
        else
        {
        	httpservletrequest.setAttribute("showAddBtn", "N");
        }
        //setScreenInfo(httpservletrequest);4543

        try
        {
            reset(httpservletrequest);
        }
        catch(Exception exception)
        {
        System.out.println(exception);
        }
        return actionmapping.findForward("success");
    }


    private void setScreenInfo(HttpServletRequest httpservletrequest)

    {

        try
        {

        boolean flag = UtilityObject.isBoroughValidInNyc(httpservletrequest);
        if(flag)
            httpservletrequest.setAttribute("is5Borough", "Y");
        boolean flag1 = UtilityObject.isFacilityInNy(httpservletrequest);
        if(flag1)
            httpservletrequest.setAttribute("isInNy", "Y");
        }

        catch(Exception e)
        {
        	System.out.println(e);
        }

        HttpSession httpsession = httpservletrequest.getSession();
        BoilerVo boilervo = (BoilerVo)httpsession.getAttribute("BOILER_OBJECT");
        if(boilervo != null)
            httpservletrequest.setAttribute("showAddBtn", "Y");
        else
            httpservletrequest.setAttribute("showAddBtn", "N");
    }

    private void setScreenInfoforsearch(HttpServletRequest httpservletrequest)
        throws Exception
    {
        boolean flag = UtilityObject.isFacilityInNy(httpservletrequest);
        if(flag)
            httpservletrequest.setAttribute("isInNy", "Y");
    }

    private void setDropDown(HttpServletRequest httpservletrequest)
    {
        com.eespc.tracking.bo.DropDown dropdown = BoilerUseEnum.getDropDownObj();
        httpservletrequest.setAttribute("BOILER_PRIMARY_USE", dropdown);
        com.eespc.tracking.bo.DropDown dropdown1 = FurnaceOilTypeEnum.getDropDownObj(false);
        httpservletrequest.setAttribute("BOILER_PRIMARY_FUEL", dropdown1);
        com.eespc.tracking.bo.DropDown dropdown2 = FurnaceOilTypeEnum.getDropDownObj(false);
        httpservletrequest.setAttribute("BOILER_SECONDARY_FUEL", dropdown2);
        com.eespc.tracking.bo.DropDown dropdown3 = FurnaceOilTypeEnum.getDropDownObj(true);
        httpservletrequest.setAttribute("BOILER_OIL_TYPE", dropdown3);
        com.eespc.tracking.bo.DropDown dropdown4 = YearEnum.getDropDownObj();
        httpservletrequest.setAttribute("YEARS", dropdown4);
        com.eespc.tracking.bo.DropDown dropdown5 = TankOperatingStatusEnum.getDropDownObj();
        httpservletrequest.setAttribute("TANK_OPERATION_STATUS", dropdown5);
    }

    private void setFieldDetails(HttpServletRequest httpservletrequest, DynaActionForm dynaactionform, BoilerVo boilervo)
    {
        setFieldDetails(httpservletrequest, dynaactionform, boilervo, false);
    }

    private void setFieldDetailsforedit(HttpServletRequest httpservletrequest, DynaActionForm dynaactionform, BoilerVo boilervo, boolean flag)
    {
        if(boilervo == null)
            return;
        dynaactionform.set("B_facilityDesignatedId", boilervo.getFacilityDesignatedId());
        dynaactionform.set("B_floor", boilervo.getFloor());
        dynaactionform.set("B_stateId", boilervo.getStateId());
        int i = boilervo.getPrimaryUse();
        if(i > 0)
        {
            BoilerUseEnum boileruseenum = BoilerUseEnum.get(i);
            if(boileruseenum != null)
                if(flag)
                    dynaactionform.set("B_primaryUse", (new StringBuilder()).append(boileruseenum.getCode()).append("").toString());
                else
                    dynaactionform.set("B_primaryUse", boileruseenum.getName());
        }
        dynaactionform.set("B_yearInstalled", boilervo.getYearInstalled());
        dynaactionform.set("B_manufacturer", boilervo.getManufacturer());
        dynaactionform.set("B_make", boilervo.getMake());
        dynaactionform.set("B_model", boilervo.getModel());
        dynaactionform.set("B_comments", boilervo.getComments());

        dynaactionform.set("B_footnote", boilervo.getFootnote());
        dynaactionform.set("B_oilemmisionfactor", boilervo.getOilemmisionfactor());
        dynaactionform.set("B_gasemmisionfactor", boilervo.getGasemmisionfactor());
        dynaactionform.set("B_gasfuelgapping", boilervo.getGasfuelgapping());
        dynaactionform.set("B_oilfuelgapping", boilervo.getOilfuelgapping());
        dynaactionform.set("B_primaryuseother", boilervo.getPrimaryuseother());

        dynaactionform.set("B_serial", boilervo.getSerial());
        dynaactionform.set("B_burnerMake", boilervo.getBurnerMake());
        dynaactionform.set("B_burnerModel", boilervo.getBurnerModel());
        dynaactionform.set("B_capacity", boilervo.getCapacity());
        if(boilervo.getBurnerType() == 2)
        {
            if(flag)
                dynaactionform.set("B_burnerType", "Y");
            else
                dynaactionform.set("B_burnerType", "Dual Fuel");
        } else
        if(boilervo.getBurnerType() == 1)
            if(flag)
                dynaactionform.set("B_burnerType", "N");
            else
                dynaactionform.set("B_burnerType", "Not Dual Fuel");
        i = boilervo.getPrimaryFuel();
        if(i > 0)
            if(flag)
            {
                dynaactionform.set("B_primaryFuel", (new StringBuilder()).append(i).append("").toString());
            } else
            {
                FurnaceOilTypeEnum furnaceoiltypeenum = FurnaceOilTypeEnum.get(i);
                dynaactionform.set("B_primaryFuel", furnaceoiltypeenum != null ? ((Object) (furnaceoiltypeenum.getName())) : "");
            }
        double d = Double.parseDouble(boilervo.getCapacity());
        double d1 = -99D;
        if(d > 0.0D)
        {
            int l = boilervo.getPrimaryFuel();
            if(boilervo.getBurnerType() == 1)
                if(l == 1)
                {
                    double d2 = d * 1000D;
                    dynaactionform.set("B_naturalGasFiringRate", (new StringBuilder()).append(d2).append("").toString());
                } else
                {
                    int i1 = 0;
                    if(l == 2)
                        i1 = 140;
                    else
                    if(l == 3)
                        i1 = 145;
                    else
                    if(l == 4)
                        i1 = 150;
                    dynaactionform.set("B_oilFiringRate", (new StringBuilder()).append(round((d * 1000D) / (double)i1, 2)).append("").toString());
                    dynaactionform.set("B_naturalGasFiringRate", "");
                }
            if(boilervo.getBurnerType() == 2)
            {
                double d3 = d * 1000D;
                dynaactionform.set("B_naturalGasFiringRate", (new StringBuilder()).append(d3).append("").toString());
                int j = boilervo.getSecondaryFuel();
                if(j > 0)
                {
                    FurnaceOilTypeEnum furnaceoiltypeenum2 = FurnaceOilTypeEnum.get(j);
                    if(furnaceoiltypeenum2 != null)
                    {
                        dynaactionform.set("B_secondaryFuel", furnaceoiltypeenum2.getName());
                        if(flag)
                            dynaactionform.set("B_secondaryFuel", (new StringBuilder()).append(furnaceoiltypeenum2.getCode()).append("").toString());
                        byte byte0 = -99;
                        int k1 = furnaceoiltypeenum2.getCode();
                        int i2 = 0;
                        if(k1 == 2)
                            i2 = 140;
                        else
                        if(k1 == 3)
                            i2 = 145;
                        else
                        if(k1 == 4)
                            i2 = 150;
                        if(i2 > 0)
                            dynaactionform.set("B_oilFiringRate", (new StringBuilder()).append(round((d * 1000D) / (double)i2, 2)).append("").toString());
                    } else
                    {
                        dynaactionform.set("B_secondaryFuel", "");
                    }
                } else
                {
                    dynaactionform.set("B_secondaryFuel", "");
                }
            }
        } else
        {
            int k = boilervo.getSecondaryFuel();
            if(k > 0)
            {
                FurnaceOilTypeEnum furnaceoiltypeenum1 = FurnaceOilTypeEnum.get(k);
                if(furnaceoiltypeenum1 != null)
                {
                    dynaactionform.set("B_secondaryFuel", furnaceoiltypeenum1.getName());
                    if(flag)
                        dynaactionform.set("B_secondaryFuel", (new StringBuilder()).append(furnaceoiltypeenum1.getCode()).append("").toString());
                } else
                {
                    dynaactionform.set("B_secondaryFuel", "");
                }
            } else
            {
                dynaactionform.set("B_secondaryFuel", "");
            }
        }
        StorageTankVo storagetankvo = boilervo.getFuelFromObj();
        if(storagetankvo != null)
        {
            dynaactionform.set("B_fuelFrom", (new StringBuilder()).append(storagetankvo.getId()).append("").toString());
            dynaactionform.set("B_fuelFromName", storagetankvo.getFacilityDesignatedId());
        }
        dynaactionform.set("B_nycDob", boilervo.getNycDob());
        dynaactionform.set("B_mea", boilervo.getMea());
        dynaactionform.set("B_dep", boilervo.getDep());
        dynaactionform.set("B_comments", boilervo.getComments());
        dynaactionform.set("B_footnote", boilervo.getFootnote());
        dynaactionform.set("B_oilemmisionfactor", boilervo.getOilemmisionfactor());
        dynaactionform.set("B_gasemmisionfactor", boilervo.getGasemmisionfactor());
        dynaactionform.set("B_gasfuelgapping", boilervo.getGasfuelgapping());
        dynaactionform.set("B_oilfuelgapping", boilervo.getOilfuelgapping());
        dynaactionform.set("B_primaryuseother", boilervo.getPrimaryuseother());
        dynaactionform.set("B_sechduelC", UtilityObject.booleanToString(boilervo.isSechduelC()));
        dynaactionform.set("B_planApproval", UtilityObject.booleanToString(boilervo.isPlanApproval()));
        StackVo stackvo = boilervo.getStackFromObj();
        if(stackvo != null)
        {
            dynaactionform.set("B_stackFrom", (new StringBuilder()).append(stackvo.getStackId()).append("").toString());
            dynaactionform.set("B_stackFromName", stackvo.getFacilityStackId());
        }
        dynaactionform.set("B_stackTestRequired", boilervo.getStackTestRequired());
        dynaactionform.set("B_otherBoilersCombined", UtilityObject.booleanToString(boilervo.isOtherBoilersCombined()));
        int j1 = boilervo.getParameters();
        setParameters(dynaactionform, j1);
        int l1 = boilervo.getTestOnFuel();
        if(l1 >= 10)
            dynaactionform.set("B_testOnFuel", "Y");
        setTestOnFuel(dynaactionform, l1, flag);
        dynaactionform.set("B_stackTestProtSubmited", UtilityObject.booleanToString(boilervo.isStackTestProtSubmited()));
        dynaactionform.set("B_testConductedBy", boilervo.getTestConductedBy());
        dynaactionform.set("B_testReportSubmited", boilervo.getTestReportSubmited());
        Object obj = null;
        String s = boilervo.getProtocolSubmitDate();
        dynaactionform.set("B_protocolSubmitDate", UtilityObject.convertYyyyMmDd2MmDdYyyy(s));
        dynaactionform.set("B_boilerTestPassed", UtilityObject.booleanToString(boilervo.isBoilerTestPassed()));
        dynaactionform.set("B_retestPlanned", UtilityObject.booleanToString(boilervo.isRetestPlanned()));
        dynaactionform.set("DisconnectedYear", boilervo.getDisconnectedYear());
        dynaactionform.set("ModifiedInPast", (new StringBuilder()).append(boilervo.getModifiedInPast()).append("").toString());
        String s1 = boilervo.getNextStackTestDate();
        dynaactionform.set("B_nextStackTestDate", UtilityObject.convertYyyyMmDd2MmDdYyyy(s1));
        String s2 = boilervo.getStackTestDate();
        dynaactionform.set("B_StackTestDate", UtilityObject.convertYyyyMmDd2MmDdYyyy(s2));
        dynaactionform.set("B_isBoilerInCompliance", UtilityObject.booleanToString(boilervo.isBoilerInCompliance()));
        dynaactionform.set("B_isBoilerSubjectToNspc", UtilityObject.booleanToString(boilervo.isBoilerSubjectToNspc()));
        dynaactionform.set("B_blrModifiedInPast", UtilityObject.booleanToString(boilervo.isBlrModifiedInPast()));
        s = boilervo.getBlrModifiedOn();
        dynaactionform.set("B_blrModifiedOn", UtilityObject.convertYyyyMmDd2MmDdYyyy(s));
        dynaactionform.set("B_isModifyPermitSub", UtilityObject.booleanToString(boilervo.isModifyPermitSub()));
        dynaactionform.set("B_anyEmission", UtilityObject.booleanToString(boilervo.isAnyEmission()));
        dynaactionform.set("B_blrSubjectToFederal", UtilityObject.booleanToString(boilervo.isBlrSubjectToFederal()));
        dynaactionform.set("B_fuelCaping", boilervo.getFuelCaping());
        dynaactionform.set("B_isRollingAvg", UtilityObject.booleanToString(boilervo.isRollingAvg()));
        dynaactionform.set("B_isOpacityMonInst", UtilityObject.booleanToString(boilervo.isOpacityMonInst()));
        dynaactionform.set("B_prfmTestProtSub", UtilityObject.booleanToString(boilervo.isPrfmTestProtSub()));
        s = boilervo.getPrfmTestProtSubDate();
        dynaactionform.set("B_prfmTestProtSubDate", UtilityObject.convertYyyyMmDd2MmDdYyyy(s));
        dynaactionform.set("B_prfmTestRptSub", UtilityObject.booleanToString(boilervo.isPrfmTestProtSub()));
        s = boilervo.getPrfmTestRptSubDate();
        dynaactionform.set("B_prfmTestRptSubDate", UtilityObject.convertYyyyMmDd2MmDdYyyy(s));
        dynaactionform.set("B_isSulpContentAnaly", UtilityObject.booleanToString(boilervo.isSulpContentAnaly()));
        dynaactionform.set("B_sulphurContent", boilervo.getSulphurContent());
        dynaactionform.set("B_fuelLimitsForBlr", UtilityObject.booleanToString(boilervo.isFuelLimitsForBlr()));
        dynaactionform.set("B_fuelLimitQnty", boilervo.getFuelLimitQnty());
        dynaactionform.set("B_nitrogenContent", UtilityObject.booleanToString(boilervo.isNitrogenContent()));
        dynaactionform.set("B_nitrogenContentQnty", boilervo.getNitrogenContentQnty());
        dynaactionform.set("B_oilso2", boilervo.getB_oilso2());
        dynaactionform.set("B_gasso2", boilervo.getB_gasso2());
        dynaactionform.set("B_so2allowable",boilervo.getB_so2allowable());
		dynaactionform.set("B_noxallowable",boilervo.getB_noxallowable());
		dynaactionform.set("dobsignoff",boilervo.getDobsignoff());
		dynaactionform.set("firedepartmentapproval",boilervo.getFiredepartmentapproval());
		dynaactionform.set("depPermit",boilervo.isDepPermit());
		dynaactionform.set("depPermitExpire",boilervo.isDepPermitExpire());
		dynaactionform.set("dobfiling",boilervo.getDobFiling());
		dynaactionform.set("boilerTuneup",boilervo.getBoilerTuneup());
        HttpSession httpsession = httpservletrequest.getSession();
        boilervo.setAnnualTuneUpList(null);
        boilervo.setValveTestList(null);
        boilervo.setFuelConsumptionList(null);
        boilervo.setPermitList(null);
        List list = boilervo.getAnnualTuneUpList();
        if(list != null && list.size() > 0)
        {
            httpservletrequest.setAttribute("BLR_ANNUAL_TUNE_UP", list);
        } else
        {
            httpservletrequest.setAttribute("BLR_ANNUAL_TUNE_UP", new ArrayList());
            log.debug("No Annual Tune Up info");
        }
		
		List listx1 = boilervo.getValveTestList();
        if(listx1 != null && listx1.size() > 0)
        {
            httpservletrequest.setAttribute("BLR_VALVE_TEST", listx1);
        } else
        {
            httpservletrequest.setAttribute("BLR_VALVE_TEST", new ArrayList());
            log.debug("No Valve Test info");
        }
        
        List list1 = boilervo.getFuelConsumptionList();
        List o_list1 = boilervo.geto_FuelConsumptionList();
        System.out.println("In Boileraction G:"+list1.size());
        System.out.println("In Boileraction O:"+o_list1.size());
        if(list1 != null && list1.size() > 0)
        {
            Properties properties = UtilityObject.getRollingAverageInfo(list1);
            httpservletrequest.setAttribute("hdnConsumption", properties.get("TOTAL"));
            httpservletrequest.setAttribute("hdnPreviousConsumption", properties.get("PREVIOUS_CONSUMPTION"));
            httpservletrequest.setAttribute("hdnCurrentMonthIndex", properties.get("CURRENT_MONTH_INDEX"));
            httpservletrequest.setAttribute("FUEL_CONSUMPTION", list1);
        }
        else
        {
            httpservletrequest.setAttribute("FUEL_CONSUMPTION", new ArrayList());
            httpservletrequest.setAttribute("hdnConsumption", "");
            httpservletrequest.setAttribute("hdnPreviousConsumption", "0|0|0|0|0|0|0|0|0|0|0|0");
            httpservletrequest.setAttribute("hdnCurrentMonthIndex", "");
            log.debug("No Consumption info");
         }


         if(o_list1 != null && o_list1.size() > 0)
        {
            Properties o_properties = UtilityObject.getRollingAverageInfo(o_list1);
            httpservletrequest.setAttribute("o_hdnConsumption", o_properties.get("TOTAL"));
            httpservletrequest.setAttribute("o_hdnPreviousConsumption", o_properties.get("PREVIOUS_CONSUMPTION"));
            httpservletrequest.setAttribute("o_hdnCurrentMonthIndex", o_properties.get("CURRENT_MONTH_INDEX"));
            httpservletrequest.setAttribute("O_FUEL_CONSUMPTION", o_list1);
        }
         else
         {
            httpservletrequest.setAttribute("O_FUEL_CONSUMPTION", new ArrayList());
            httpservletrequest.setAttribute("o_hdnConsumption", "");
            httpservletrequest.setAttribute("o_hdnPreviousConsumption", "0|0|0|0|0|0|0|0|0|0|0|0");
            httpservletrequest.setAttribute("o_hdnCurrentMonthIndex", "");
            log.debug("No Consumption info");
          }

        List listx2 = boilervo.getPermitList();
        ArrayList arraylistx = new ArrayList();
        ArrayList arraylistx1 = new ArrayList();
        try{

        int jx2 = listx2.size();
        if(listx2 != null && jx2 > 0)
        {
            for(int k2 = 0; k2 < jx2; k2++)
            {
                BoilerPermitInfoVo boilerpermitinfovo = (BoilerPermitInfoVo)listx2.get(k2);
                int lx2 = boilerpermitinfovo.getDepId();
                if(lx2 == 2)
                {
                    arraylistx1.add(boilerpermitinfovo);
                    continue;
                }
                if(lx2 == 1)
                    arraylistx.add(boilerpermitinfovo);
            }

        }
       }
       catch(Exception e)
       {
       	System.out.println("New Old Exception:"+e);
       }

        try
        {
            reset(httpservletrequest);
        }
        catch(Exception exception) { }

        httpservletrequest.setAttribute("BLR_DOB_PERMIT", arraylistx1);
        httpservletrequest.setAttribute("BLR_DEP_PERMIT", arraylistx);

    }

    private void setFieldDetails(HttpServletRequest httpservletrequest, DynaActionForm dynaactionform, BoilerVo boilervo, boolean flag)
    {
        if(boilervo == null)
            return;
        dynaactionform.set("B_facilityDesignatedId", boilervo.getFacilityDesignatedId());
        dynaactionform.set("B_floor", boilervo.getFloor());
        dynaactionform.set("B_stateId", boilervo.getStateId());
        int i = boilervo.getPrimaryUse();
        if(i > 0)
        {
            BoilerUseEnum boileruseenum = BoilerUseEnum.get(i);
            if(boileruseenum != null)
                if(flag)
                    dynaactionform.set("B_primaryUse", (new StringBuilder()).append(boileruseenum.getCode()).append("").toString());
                else
                {
                	String x=""+boileruseenum.getName();
                	if(x.equals(-1))
                	{
                	x="";
                	}
                    dynaactionform.set("B_primaryUse", x);
                }
        }
        dynaactionform.set("B_yearInstalled", boilervo.getYearInstalled());
        dynaactionform.set("B_manufacturer", boilervo.getManufacturer());
        dynaactionform.set("B_make", boilervo.getMake());
        dynaactionform.set("B_model", boilervo.getModel());
        dynaactionform.set("B_comments", boilervo.getComments());
        dynaactionform.set("B_serial", boilervo.getSerial());
        dynaactionform.set("B_burnerMake", boilervo.getBurnerMake());
        dynaactionform.set("B_burnerModel", boilervo.getBurnerModel());
        dynaactionform.set("B_capacity", boilervo.getCapacity());
        if(boilervo.getBurnerType() == 2)
        {
            if(flag)
                dynaactionform.set("B_burnerType", "Y");
            else
                dynaactionform.set("B_burnerType", "Dual Fuel");
        } else
        if(boilervo.getBurnerType() == 1)
            if(flag)
                dynaactionform.set("B_burnerType", "N");
            else
                dynaactionform.set("B_burnerType", "Not Dual Fuel");
        i = boilervo.getPrimaryFuel();
        if(i > 0)
            if(flag)
            {
                dynaactionform.set("B_primaryFuel", (new StringBuilder()).append(i).append("").toString());
            } else
            {
                FurnaceOilTypeEnum furnaceoiltypeenum = FurnaceOilTypeEnum.get(i);
                dynaactionform.set("B_primaryFuel", furnaceoiltypeenum != null ? ((Object) (furnaceoiltypeenum.getName())) : "");
            }
        double d = Double.parseDouble(boilervo.getCapacity());
        double d1 = -99D;
        if(d > 0.0D)
        {
            int l = boilervo.getPrimaryFuel();
            if(boilervo.getBurnerType() == 1)
                if(l == 1)
                {
                    double d2 = d * 1000D;
                    dynaactionform.set("B_naturalGasFiringRate", (new StringBuilder()).append(d2).append("").toString());
                } else
                {
                    int i1 = 0;
                    if(l == 2)
                        i1 = 140;
                    else
                    if(l == 3)
                        i1 = 145;
                    else
                    if(l == 4)
                        i1 = 150;
                    dynaactionform.set("B_oilFiringRate", (new StringBuilder()).append(round((d * 1000D) / (double)i1, 2)).append("").toString());
                    dynaactionform.set("B_naturalGasFiringRate", "");
                }
            if(boilervo.getBurnerType() == 2)
            {
                double d3 = d * 1000D;
                dynaactionform.set("B_naturalGasFiringRate", (new StringBuilder()).append(d3).append("").toString());
                int j = boilervo.getSecondaryFuel();
                if(j > 0)
                {
                    FurnaceOilTypeEnum furnaceoiltypeenum2 = FurnaceOilTypeEnum.get(j);
                    if(furnaceoiltypeenum2 != null)
                    {
                        dynaactionform.set("B_secondaryFuel", furnaceoiltypeenum2.getName());
                        if(flag)
                            dynaactionform.set("B_secondaryFuel", (new StringBuilder()).append(furnaceoiltypeenum2.getCode()).append("").toString());
                        byte byte0 = -99;
                        int k1 = furnaceoiltypeenum2.getCode();
                        int i2 = 0;
                        if(k1 == 2)
                            i2 = 140;
                        else
                        if(k1 == 3)
                            i2 = 145;
                        else
                        if(k1 == 4)
                            i2 = 150;
                        if(i2 > 0)
                            dynaactionform.set("B_oilFiringRate", (new StringBuilder()).append(round((d * 1000D) / (double)i2, 2)).append("").toString());
                    } else
                    {
                        dynaactionform.set("B_secondaryFuel", "");
                    }
                } else
                {
                    dynaactionform.set("B_secondaryFuel", "");
                }
            }
        } else
        {
            int k = boilervo.getSecondaryFuel();
            if(k > 0)
            {
                FurnaceOilTypeEnum furnaceoiltypeenum1 = FurnaceOilTypeEnum.get(k);
                if(furnaceoiltypeenum1 != null)
                {
                    dynaactionform.set("B_secondaryFuel", furnaceoiltypeenum1.getName());
                    if(flag)
                        dynaactionform.set("B_secondaryFuel", (new StringBuilder()).append(furnaceoiltypeenum1.getCode()).append("").toString());
                } else
                {
                    dynaactionform.set("B_secondaryFuel", "");
                }
            } else
            {
                dynaactionform.set("B_secondaryFuel", "");
            }
        }
        StorageTankVo storagetankvo = boilervo.getFuelFromObj();
        if(storagetankvo != null)
        {
            dynaactionform.set("B_fuelFrom", (new StringBuilder()).append(storagetankvo.getId()).append("").toString());
            dynaactionform.set("B_fuelFromName", storagetankvo.getFacilityDesignatedId());
        }
        dynaactionform.set("B_footnote", boilervo.getFootnote());
        dynaactionform.set("B_oilemmisionfactor", boilervo.getOilemmisionfactor());
        dynaactionform.set("B_gasemmisionfactor", boilervo.getGasemmisionfactor());
        dynaactionform.set("B_gasfuelgapping", boilervo.getGasfuelgapping());
        dynaactionform.set("B_oilfuelgapping", boilervo.getOilfuelgapping());
        dynaactionform.set("B_primaryuseother", boilervo.getPrimaryuseother());

        dynaactionform.set("B_nycDob", boilervo.getNycDob());
        dynaactionform.set("B_mea", boilervo.getMea());
        dynaactionform.set("B_dep", boilervo.getDep());
        dynaactionform.set("B_comments", boilervo.getComments());
        dynaactionform.set("B_sechduelC", UtilityObject.booleanToString(boilervo.isSechduelC()));
        dynaactionform.set("B_planApproval", UtilityObject.booleanToString(boilervo.isPlanApproval()));
        StackVo stackvo = boilervo.getStackFromObj();
        if(stackvo != null)
        {
            dynaactionform.set("B_stackFrom", (new StringBuilder()).append(stackvo.getStackId()).append("").toString());
            dynaactionform.set("B_stackFromName", stackvo.getFacilityStackId());
        }
        dynaactionform.set("B_stackTestRequired", boilervo.getStackTestRequired());
        dynaactionform.set("B_otherBoilersCombined", UtilityObject.booleanToString(boilervo.isOtherBoilersCombined()));
        int j1 = boilervo.getParameters();
        setParameters(dynaactionform, j1);
        int l1 = boilervo.getTestOnFuel();
        if(l1 >= 10)
            dynaactionform.set("B_testOnFuel", "Y");
        setTestOnFuel(dynaactionform, l1, flag);
        dynaactionform.set("B_stackTestProtSubmited", UtilityObject.booleanToString(boilervo.isStackTestProtSubmited()));
        dynaactionform.set("B_testConductedBy", boilervo.getTestConductedBy());
        dynaactionform.set("B_testReportSubmited", boilervo.getTestReportSubmited());
        Object obj = null;
        String s = boilervo.getProtocolSubmitDate();
        dynaactionform.set("B_protocolSubmitDate", UtilityObject.convertYyyyMmDd2MmDdYyyy(s));
        dynaactionform.set("B_boilerTestPassed", UtilityObject.booleanToString(boilervo.isBoilerTestPassed()));
        dynaactionform.set("B_retestPlanned", UtilityObject.booleanToString(boilervo.isRetestPlanned()));
        dynaactionform.set("DisconnectedYear", boilervo.getDisconnectedYear());
        TankOperatingStatusEnum tankoperatingstatusenum = TankOperatingStatusEnum.get(boilervo.getModifiedInPast());
        if(tankoperatingstatusenum != null)
            dynaactionform.set("ModifiedInPast", tankoperatingstatusenum.getName());
        String s1 = boilervo.getNextStackTestDate();
        dynaactionform.set("B_nextStackTestDate", UtilityObject.convertYyyyMmDd2MmDdYyyy(s1));
        String s2 = boilervo.getStackTestDate();
        dynaactionform.set("B_StackTestDate", UtilityObject.convertYyyyMmDd2MmDdYyyy(s2));
        dynaactionform.set("B_isBoilerInCompliance", UtilityObject.booleanToString(boilervo.isBoilerInCompliance()));
        dynaactionform.set("B_isBoilerSubjectToNspc", UtilityObject.booleanToString(boilervo.isBoilerSubjectToNspc()));
        dynaactionform.set("B_blrModifiedInPast", UtilityObject.booleanToString(boilervo.isBlrModifiedInPast()));
        s = boilervo.getBlrModifiedOn();
        dynaactionform.set("B_blrModifiedOn", UtilityObject.convertYyyyMmDd2MmDdYyyy(s));
        dynaactionform.set("B_isModifyPermitSub", UtilityObject.booleanToString(boilervo.isModifyPermitSub()));
        dynaactionform.set("B_anyEmission", UtilityObject.booleanToString(boilervo.isAnyEmission()));
        dynaactionform.set("B_blrSubjectToFederal", UtilityObject.booleanToString(boilervo.isBlrSubjectToFederal()));
        dynaactionform.set("B_fuelCaping", boilervo.getFuelCaping());
        dynaactionform.set("B_isRollingAvg", UtilityObject.booleanToString(boilervo.isRollingAvg()));
        dynaactionform.set("B_isOpacityMonInst", UtilityObject.booleanToString(boilervo.isOpacityMonInst()));
        dynaactionform.set("B_prfmTestProtSub", UtilityObject.booleanToString(boilervo.isPrfmTestProtSub()));
        s = boilervo.getPrfmTestProtSubDate();
        dynaactionform.set("B_prfmTestProtSubDate", UtilityObject.convertYyyyMmDd2MmDdYyyy(s));
        dynaactionform.set("B_prfmTestRptSub", UtilityObject.booleanToString(boilervo.isPrfmTestProtSub()));
        s = boilervo.getPrfmTestRptSubDate();
        dynaactionform.set("B_prfmTestRptSubDate", UtilityObject.convertYyyyMmDd2MmDdYyyy(s));
        dynaactionform.set("B_isSulpContentAnaly", UtilityObject.booleanToString(boilervo.isSulpContentAnaly()));
        dynaactionform.set("B_sulphurContent", boilervo.getSulphurContent());
        dynaactionform.set("B_fuelLimitsForBlr", UtilityObject.booleanToString(boilervo.isFuelLimitsForBlr()));
        dynaactionform.set("B_fuelLimitQnty", boilervo.getFuelLimitQnty());
        dynaactionform.set("B_nitrogenContent", UtilityObject.booleanToString(boilervo.isNitrogenContent()));
        dynaactionform.set("B_nitrogenContentQnty", boilervo.getNitrogenContentQnty());
        dynaactionform.set("B_oilso2", boilervo.getB_oilso2());
        dynaactionform.set("B_gasso2", boilervo.getB_gasso2());
        dynaactionform.set("B_so2allowable",boilervo.getB_so2allowable());
		dynaactionform.set("B_noxallowable",boilervo.getB_noxallowable());
		dynaactionform.set("dobsignoff",boilervo.getDobsignoff());
		dynaactionform.set("firedepartmentapproval",boilervo.getFiredepartmentapproval());
		dynaactionform.set("depPermit",boilervo.isDepPermit());
		dynaactionform.set("depPermitExpire",boilervo.isDepPermitExpire());
		dynaactionform.set("dobfiling",boilervo.getDobFiling());
		dynaactionform.set("boilerTuneup",boilervo.getBoilerTuneup());
        HttpSession httpsession = httpservletrequest.getSession();
        boilervo.setAnnualTuneUpList(null);        	
		boilervo.setValveTestList(null);
        boilervo.setFuelConsumptionList(null);
        boilervo.setPermitList(null);
        List list = boilervo.getAnnualTuneUpList();
        if(list != null && list.size() > 0)
        {
            httpservletrequest.setAttribute("BLR_ANNUAL_TUNE_UP", list);
        } else
        {
            httpservletrequest.setAttribute("BLR_ANNUAL_TUNE_UP", new ArrayList());
            log.debug("No Annual Tune Up info");
        }
		
	           
		List listx1 = boilervo.getValveTestList();
        if(listx1 != null && listx1.size() > 0)
        {
            httpservletrequest.setAttribute("BLR_VALVE_TEST", listx1);
        } else
        {
            httpservletrequest.setAttribute("BLR_VALVE_TEST", new ArrayList());
            log.debug("No Valve Test info");
        }
        
        
        List list1 = boilervo.getFuelConsumptionList();
        List o_list1 = boilervo.geto_FuelConsumptionList();
        System.out.println("In Boileraction G:"+list1.size());
        System.out.println("In Boileraction O:"+o_list1.size());
        if(list1 != null && list1.size() > 0)
        {
            Properties properties = UtilityObject.getRollingAverageInfo(list1);
            httpservletrequest.setAttribute("hdnConsumption", properties.get("TOTAL"));
            httpservletrequest.setAttribute("hdnPreviousConsumption", properties.get("PREVIOUS_CONSUMPTION"));
            httpservletrequest.setAttribute("hdnCurrentMonthIndex", properties.get("CURRENT_MONTH_INDEX"));
            httpservletrequest.setAttribute("FUEL_CONSUMPTION", list1);
        }
        else
        {
            httpservletrequest.setAttribute("FUEL_CONSUMPTION", new ArrayList());
            httpservletrequest.setAttribute("hdnConsumption", "");
            httpservletrequest.setAttribute("hdnPreviousConsumption", "0|0|0|0|0|0|0|0|0|0|0|0");
            httpservletrequest.setAttribute("hdnCurrentMonthIndex", "");
            log.debug("No Consumption info");
         }


         if(o_list1 != null && o_list1.size() > 0)
        {
            Properties o_properties = UtilityObject.getRollingAverageInfo(o_list1);
            httpservletrequest.setAttribute("o_hdnConsumption", o_properties.get("TOTAL"));
            httpservletrequest.setAttribute("o_hdnPreviousConsumption", o_properties.get("PREVIOUS_CONSUMPTION"));
            httpservletrequest.setAttribute("o_hdnCurrentMonthIndex", o_properties.get("CURRENT_MONTH_INDEX"));
            httpservletrequest.setAttribute("O_FUEL_CONSUMPTION", o_list1);
        }
         else
         {
            httpservletrequest.setAttribute("O_FUEL_CONSUMPTION", new ArrayList());
            httpservletrequest.setAttribute("o_hdnConsumption", "");
            httpservletrequest.setAttribute("o_hdnPreviousConsumption", "0|0|0|0|0|0|0|0|0|0|0|0");
            httpservletrequest.setAttribute("o_hdnCurrentMonthIndex", "");
            log.debug("No Consumption info");
          }

        List list2 = boilervo.getPermitList();
        ArrayList arraylist = new ArrayList();
        ArrayList arraylist1 = new ArrayList();
        int j2 = list2.size();
        if(list2 != null && j2 > 0)
        {
            for(int k2 = 0; k2 < j2; k2++)
            {
                BoilerPermitInfoVo boilerpermitinfovo = (BoilerPermitInfoVo)list2.get(k2);
                int l2 = boilerpermitinfovo.getDepId();
                if(l2 == 2)
                {
                    arraylist1.add(boilerpermitinfovo);
                    continue;
                }
                if(l2 == 1)
                    arraylist.add(boilerpermitinfovo);
            }

        }
         try
        {
            reset(httpservletrequest);
        }
        catch(Exception exception) { }

        httpservletrequest.setAttribute("BLR_DOB_PERMIT", arraylist1);
        httpservletrequest.setAttribute("BLR_DEP_PERMIT", arraylist);

    }

    private void setParameters(DynaActionForm dynaactionform, int i)
    {
        switch(i)
        {
        case 0: // '\0'
            dynaactionform.set("B_parameters1", "N");
            dynaactionform.set("B_parameters2", "N");
            dynaactionform.set("B_parameters3", "N");
            break;

        case 1: // '\001'
            dynaactionform.set("B_parameters1", "Y");
            dynaactionform.set("B_parameters2", "N");
            dynaactionform.set("B_parameters3", "N");
            break;

        case 2: // '\002'
            dynaactionform.set("B_parameters1", "N");
            dynaactionform.set("B_parameters2", "Y");
            dynaactionform.set("B_parameters3", "N");
            break;

        case 3: // '\003'
            dynaactionform.set("B_parameters1", "Y");
            dynaactionform.set("B_parameters2", "Y");
            dynaactionform.set("B_parameters3", "N");
            break;

        case 4: // '\004'
            dynaactionform.set("B_parameters1", "N");
            dynaactionform.set("B_parameters2", "N");
            dynaactionform.set("B_parameters3", "Y");
            break;

        case 5: // '\005'
            dynaactionform.set("B_parameters1", "Y");
            dynaactionform.set("B_parameters2", "N");
            dynaactionform.set("B_parameters3", "Y");
            break;

        case 6: // '\006'
            dynaactionform.set("B_parameters1", "N");
            dynaactionform.set("B_parameters2", "Y");
            dynaactionform.set("B_parameters3", "Y");
            break;

        case 7: // '\007'
            dynaactionform.set("B_parameters1", "Y");
            dynaactionform.set("B_parameters2", "Y");
            dynaactionform.set("B_parameters3", "Y");
            break;
        }
    }

    private void setTestOnFuel(DynaActionForm dynaactionform, int i, boolean flag)
    {
        Object obj = null;
        if(i == 2 || i == 12)
        {
            if(flag)
            {
                dynaactionform.set("B_testOnFuelOilType", "2");
            } else
            {
                FurnaceOilTypeEnum furnaceoiltypeenum = FurnaceOilTypeEnum.get(2);
                dynaactionform.set("B_testOnFuelOilType", furnaceoiltypeenum != null ? ((Object) (furnaceoiltypeenum.getName())) : "2");
            }
        } else
        if(i == 3 || i == 13)
        {
            if(flag)
            {
                dynaactionform.set("B_testOnFuelOilType", "3");
            } else
            {
                FurnaceOilTypeEnum furnaceoiltypeenum1 = FurnaceOilTypeEnum.get(3);
                dynaactionform.set("B_testOnFuelOilType", furnaceoiltypeenum1 != null ? ((Object) (furnaceoiltypeenum1.getName())) : "3");
            }
        } else
        if(i == 4 || i == 14)
            if(flag)
            {
                dynaactionform.set("B_testOnFuelOilType", "4");
            } else
            {
                FurnaceOilTypeEnum furnaceoiltypeenum2 = FurnaceOilTypeEnum.get(4);
                dynaactionform.set("B_testOnFuelOilType", furnaceoiltypeenum2 != null ? ((Object) (furnaceoiltypeenum2.getName())) : "4");
            }
    }

       public void reset(HttpServletRequest httpservletrequest)
        throws IOException, ServletException
    {
	    	//DynaActionForm dynaactionform = (DynaActionForm)actionform;
	        HttpSession httpsession = httpservletrequest.getSession();
	        BoilerVo boilervo = (BoilerVo)httpsession.getAttribute("BOILER_OBJECT");
	        FacilityVo facilityvo = (FacilityVo)httpsession.getAttribute("FACILITY_OBJECT");
	        System.out.println("Facility Id: "+facilityvo.getDecId());
	        System.out.println("Path="+httpservletrequest.getContextPath()+":"+httpservletrequest.getRealPath("/"));
	        //String s = (String)dynaactionform.get("methodToCall");
		        try
		        {
		        File f=new File(httpservletrequest.getRealPath("/")+"/clientfolder/"+facilityvo.getDecId()+"/boiler/"+boilervo.getId()+"-"+boilervo.getFacilityDesignatedId().trim());
		        if(f.exists()==false){
		    	f.mkdirs();
				}
				}
				catch(Exception e)
				{
				System.out.println("Exception: "+e);
				}
				ArrayList folderlist=new ArrayList();
				try
		        {
					String contextpath=httpservletrequest.getRealPath("/")+"/clientfolder/"+facilityvo.getDecId()+"/boiler/"+boilervo.getId()+"-"+boilervo.getFacilityDesignatedId().trim();
					File folder = new File(contextpath);
			    	File[] listOfFiles = folder.listFiles();
			    	for (int i = 0; i < listOfFiles.length; i++)
			    	{
			    		System.out.println("1");
			    		   String arr[]=new String[5];
					      	if (listOfFiles[i].isFile())
					      	{

						      	arr[0]="file";
						      	arr[1]=contextpath;
						      	arr[2]=listOfFiles[i].getName();
								Date lastModified = new Date(listOfFiles[i].lastModified());
								arr[3]=String.valueOf(lastModified);
								arr[4]=String.valueOf(listOfFiles[i].length())+" Bytes";
						        System.out.println("File " + listOfFiles[i].getName());
					      	}
					      	else if (listOfFiles[i].isDirectory())
					      	{

					      		arr[0]="folder";
						      	arr[1]=contextpath;
						      	arr[2]=listOfFiles[i].getName();
						      	Date lastModified = new Date(listOfFiles[i].lastModified());
								arr[3]=String.valueOf(lastModified);
					            System.out.println("Directory " + listOfFiles[i].getName());
					      	}
					      	folderlist.add(arr);
					    System.out.println("2");
			    	}
				}
				catch(Exception e)
				{
					System.out.println("Find List Exception: "+e);
				}
		httpservletrequest.setAttribute("FILE_LIST",folderlist);
		httpservletrequest.setAttribute("FILE_PATH",facilityvo.getDecId()+"/boiler/"+boilervo.getId()+"-"+boilervo.getFacilityDesignatedId().trim());

    }


    public void setformvariable(String path,ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
	    	DynaActionForm dynaactionform = (DynaActionForm)actionform;
	        HttpSession httpsession = httpservletrequest.getSession();
	        FacilityVo facilityvo = (FacilityVo)httpsession.getAttribute("FACILITY_OBJECT");
	        System.out.println("Facility Id: "+facilityvo.getDecId());
	        System.out.println("Path="+httpservletrequest.getContextPath()+":"+httpservletrequest.getRealPath("/"));
	        String s = (String)dynaactionform.get("methodToCall");
		        try
		        {

		        File f=new File(httpservletrequest.getRealPath("/")+"/clientfolder/"+path);
		        if(f.exists()==false){
		    	f.mkdirs();
				}

				}
				catch(Exception e)
				{
				System.out.println("Exception: "+e);
				}


				ArrayList folderlist=new ArrayList();
				try
		        {

					String contextpath=httpservletrequest.getRealPath("/")+"/clientfolder/"+path+"/";
					File folder = new File(contextpath);
			    	File[] listOfFiles = folder.listFiles();
			    	for (int i = 0; i < listOfFiles.length; i++)
			    	{
			    		System.out.println("1");
			    		   String arr[]=new String[5];
					      	if (listOfFiles[i].isFile())
					      	{
					      		System.out.println("1a");
						      	arr[0]="file";
						      	arr[1]=contextpath;
						      	arr[2]=listOfFiles[i].getName();
								Date lastModified = new Date(listOfFiles[i].lastModified());
								arr[3]=String.valueOf(lastModified);
								arr[4]=String.valueOf(listOfFiles[i].length())+" Bytes";
						        System.out.println("File " + listOfFiles[i].getName());
					      	}
					      	else if (listOfFiles[i].isDirectory())
					      	{
					      		System.out.println("1b");
					      		arr[0]="folder";
						      	arr[1]=contextpath;
						      	arr[2]=listOfFiles[i].getName();
						      	Date lastModified = new Date(listOfFiles[i].lastModified());
								arr[3]=String.valueOf(lastModified);
					            System.out.println("Directory " + listOfFiles[i].getName());
					      	}
					      	folderlist.add(arr);
					    System.out.println("2");

			    	}

				}
				catch(Exception e)
				{
					System.out.println("Find List Exception: "+e);
				}
		httpservletrequest.setAttribute("FILE_LIST",folderlist);
		httpservletrequest.setAttribute("FILE_PATH",path);
    }



    private float getValue(String s, DynaActionForm dynaactionform)
        throws NumberFormatException
    {
        String s1 = "0";
        s1 = (String)dynaactionform.get(s);
        if(s1.equals(""))
            s1 = "0";
        if(UtilityObject.isNotEmpty(s1))
            return Float.parseFloat(s1);
        else
            return 0f;
    }

    public static double round(double d, int i)
    {
        double d1;
        for(d1 = 1.0D; i-- > 0; d1 *= 10D);
        return (double)Math.round(d * d1) / d1;
    }

    private static Log log = LogFactory.getLog(com.eespc.tracking.actions.BoilerAction.class);

}