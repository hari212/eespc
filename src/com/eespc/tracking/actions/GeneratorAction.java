package com.eespc.tracking.actions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.upload.FormFile;

import com.eespc.tracking.bo.BoilerPermitInfoVo;
import com.eespc.tracking.bo.BuildingVo;
import com.eespc.tracking.bo.EngineRunningHrsVo;
import com.eespc.tracking.bo.FacilityVo;
import com.eespc.tracking.bo.FuelConsumptionVo;
import com.eespc.tracking.bo.GeneratorCfrPermitInfoVo;
import com.eespc.tracking.bo.GeneratorVo;
import com.eespc.tracking.bo.StackVo;
import com.eespc.tracking.bo.StorageTankVo;
import com.eespc.tracking.bo.TrienialCathodicVo;
import com.eespc.tracking.bo.myenum.FurnaceOilTypeEnum;
import com.eespc.tracking.bo.myenum.GeneratorUseEnum;
import com.eespc.tracking.bo.myenum.TankOperatingStatusEnum;
import com.eespc.tracking.bo.myenum.YearEnum;
import com.eespc.tracking.entity.GeneratorEntity;
import com.eespc.tracking.entity.StackStorageChecker;
import com.eespc.tracking.entity.StorageTankEntity;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.UtilityObject;

public class GeneratorAction extends Action
{

    public GeneratorAction()
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
            
        if(s != null && s.equalsIgnoreCase("cfrPermitInfo"))
            return cfrPermitInfo(actionmapping, actionform, httpservletrequest, httpservletresponse);
        if(s != null && s.equalsIgnoreCase("editCfrPermit"))
            return editCfrPermit(actionmapping, actionform, httpservletrequest, httpservletresponse);
        if(s != null && s.equalsIgnoreCase("updateCfrPermitInfo"))
            return updateCfrPermitInfo(actionmapping, actionform, httpservletrequest, httpservletresponse);
        if(s != null && s.equalsIgnoreCase("deleteCfrPermit"))
            return deleteCfrPermit(actionmapping, actionform, httpservletrequest, httpservletresponse);                     
            
        if(s != null && s.equalsIgnoreCase("fuelConsumptionInfo"))
            return fuelConsumptionInfo(actionmapping, actionform, httpservletrequest, httpservletresponse);
        if(s != null && s.equalsIgnoreCase("deletefuelConsumptionInfo"))
            return deletefuelConsumptionInfo(actionmapping, actionform, httpservletrequest, httpservletresponse);            
        if(s != null && s.equalsIgnoreCase("fuelConsumptionInfoyearconsumtion"))
            return fuelConsumptionInfoyearconsumtion(actionmapping, actionform, httpservletrequest, httpservletresponse);            
        if(s != null && s.equalsIgnoreCase("o_fuelConsumptionInfoyearconsumtion"))
            return o_fuelConsumptionInfoyearconsumtion(actionmapping, actionform, httpservletrequest, httpservletresponse);
                            
        if(s != null && s.equalsIgnoreCase("engineRunningHrsInfo"))
            return engineRunningHrsInfo(actionmapping, actionform, httpservletrequest, httpservletresponse);
        if(s != null && s.equalsIgnoreCase("deleteEngineRunningHrsInfo"))
            return deleteEngineRunningHrsInfo(actionmapping, actionform, httpservletrequest, httpservletresponse);            
        
        if(s != null && s.equalsIgnoreCase("engineRunningHrsInfoyearconsumtion"))
            return engineRunningHrsInfoyearconsumtion(actionmapping, actionform, httpservletrequest, httpservletresponse);
        if(s != null && s.equalsIgnoreCase("o_pressureTestHrsInfoyearconsumtion"))
            return o_pressureTestHrsInfoyearconsumtion(actionmapping, actionform, httpservletrequest, httpservletresponse);
        if(s != null && s.equalsIgnoreCase("g_pressureTestHrsInfoyearconsumtion"))
            return g_pressureTestHrsInfoyearconsumtion(actionmapping, actionform, httpservletrequest, httpservletresponse);
        if(s != null && s.equalsIgnoreCase("anu_pressureTestHrsInfoyearconsumtion"))
            return anu_pressureTestHrsInfoyearconsumtion(actionmapping, actionform, httpservletrequest, httpservletresponse);
             
        
        /* if(s != null && s.equalsIgnoreCase("o_pressureTestHrsInfo"))
            return o_pressureTestHrsInfo(actionmapping, actionform, httpservletrequest, httpservletresponse);
        if(s != null && s.equalsIgnoreCase("o_deletepressureTestHrsInfo"))
            return o_deletepressureTestHrsInfo(actionmapping, actionform, httpservletrequest, httpservletresponse);        
               
        if(s != null && s.equalsIgnoreCase("g_pressureTestHrsInfo"))
            return g_pressureTestHrsInfo(actionmapping, actionform, httpservletrequest, httpservletresponse);                        
        if(s != null && s.equalsIgnoreCase("g_deletepressureTestHrsInfo"))
            return g_deletepressureTestHrsInfo(actionmapping, actionform, httpservletrequest, httpservletresponse);
       */ 
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

        if(s != null && s.equalsIgnoreCase("displayControl"))
            return displayControl(actionmapping, actionform, httpservletrequest, httpservletresponse);
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
            dynaactionform.set("G_buildingId", "");
            dynaactionform.set("G_facilityDesignatedId", "");
            dynaactionform.set("G_floor", "");
            dynaactionform.set("G_stateId", "");
            dynaactionform.set("G_primaryUse", "");
            dynaactionform.set("G_yearInstalled", "");
            dynaactionform.set("G_status", "");
            dynaactionform.set("G_disconnecteddate", "");
            dynaactionform.set("G_manufacturer", "");            
            dynaactionform.set("G_model", "");
            dynaactionform.set("G_serial", "");
            dynaactionform.set("G_burnerMake", "");
            dynaactionform.set("G_burnerModel", "");
            dynaactionform.set("G_capacity", "");
            dynaactionform.set("G_kvaText", "");
            dynaactionform.set("G_hpText", "");
            dynaactionform.set("G_mmbtuText", "");
            dynaactionform.set("G_burnerType", "");
            dynaactionform.set("G_primaryFuel", "");
            dynaactionform.set("G_secondaryFuel", "");
            dynaactionform.set("G_oilFiringRate", "");
            dynaactionform.set("G_naturalGasFiringRate", "");
            dynaactionform.set("G_fuelFrom", "");
            dynaactionform.set("G_hasDayTank", "");
            dynaactionform.set("G_dayTankFrom", "");
            dynaactionform.set("G_nycDob", "");
            dynaactionform.set("G_mea", "");
            dynaactionform.set("G_dep", "");
            dynaactionform.set("G_sechduelC", "");
            dynaactionform.set("G_planApproval", "");
            dynaactionform.set("G_stackFrom", "");
            dynaactionform.set("G_isRecordInBook", "");
            dynaactionform.set("G_decPermitObtained", "");
            dynaactionform.set("G_depPermitObtained", "");
            dynaactionform.set("G_stackTest", "");
            dynaactionform.set("G_stackTestProtSubmited", "");
            dynaactionform.set("G_testConductedBy", "");
            dynaactionform.set("G_testReportSubmited", "");
            dynaactionform.set("G_compyWithNoxRactPlan", "");
            dynaactionform.set("G_retestPlanned", "");
            dynaactionform.set("G_nextStackTestDate", "");
            dynaactionform.set("G_nextStackTestDateNote", "");
            dynaactionform.set("G_fuelCaping", "");
            dynaactionform.set("G_actiontaken", "");
            dynaactionform.set("G_compyWithPMRactPlan", "");
            dynaactionform.set("G_gasfuelgapping", "");
            dynaactionform.set("G_gasemmisionfactor", "");
            dynaactionform.set("G_oilfuelgapping", "");
            dynaactionform.set("G_oilemmisionfactor", "");
            dynaactionform.set("G_StackTestDate", "");
            dynaactionform.set("G_ProtocalSubmittalDate", "");
            dynaactionform.set("G_testPassed", "");
            dynaactionform.set("G_fuelgapping", "");
            dynaactionform.set("g_oilso2","");
			dynaactionform.set("g_gasso2","");
			dynaactionform.set("g_so2allowable","");
			dynaactionform.set("g_noxallowable","");
            dynaactionform.set("dobsignoff","");
			dynaactionform.set("firedepartmentapproval","");
			dynaactionform.set("depPermitStatus","");
			dynaactionform.set("depPermitExpire","");
			dynaactionform.set("dobfiling","");
			dynaactionform.set("capablefuel","");
			dynaactionform.set("oilfiring","");
            httpservletrequest.getSession().removeAttribute("GENERATOR_OBJECT");
            log.debug("GeneratorAction - In Execute");
            setScreenInfo(httpservletrequest);
            return actionmapping.findForward("success");
        }
    }


    public ActionForward addnewfolder(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
    	DynaActionForm dynaactionform = (DynaActionForm)actionform;
        HttpSession httpsession = httpservletrequest.getSession();
        GeneratorVo generatorvo =new GeneratorVo();
        generatorvo=(GeneratorVo)httpsession.getAttribute("GENERATOR_OBJECT");
        setFieldDetails(httpservletrequest, dynaactionform, generatorvo);
        setScreenInfo(httpservletrequest);
//		  setFormVariable(dynaactionform, generatorvo, httpservletrequest);
//        refreshShowInfo(httpservletrequest, generatorvo);
//        refreshPermitInfo(httpservletrequest);

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
        GeneratorVo generatorvo =new GeneratorVo();
        generatorvo=(GeneratorVo)httpsession.getAttribute("GENERATOR_OBJECT");
        setFieldDetails(httpservletrequest, dynaactionform, generatorvo);
        setScreenInfo(httpservletrequest);
//		  refreshShowInfo(httpservletrequest, generatorvo);
//        refreshPermitInfo(httpservletrequest);
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
        GeneratorVo generatorvo =new GeneratorVo();
        generatorvo=(GeneratorVo)httpsession.getAttribute("GENERATOR_OBJECT");
        setFieldDetails(httpservletrequest, dynaactionform, generatorvo);
        setScreenInfo(httpservletrequest);
        //refreshShowInfo(httpservletrequest, generatorvo);
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
        GeneratorVo generatorvo =new GeneratorVo();
        generatorvo=(GeneratorVo)httpsession.getAttribute("GENERATOR_OBJECT");
        setFieldDetails(httpservletrequest, dynaactionform, generatorvo);
        setScreenInfo(httpservletrequest);
        //refreshShowInfo(httpservletrequest, generatorvo);
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
	                        {
	                                // call itself to delete the contents of the current folder
	                                delete(oFileCur);
	                        }
	                        oFileCur.delete();
	                }
	        }
	}

    public ActionForward fileupload(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
    	//DynaActionForm dynaactionform = (DynaActionForm)actionform;

    	DynaActionForm dynaactionform = (DynaActionForm)actionform;


        HttpSession httpsession = httpservletrequest.getSession();
        GeneratorVo generatorvo =new GeneratorVo();
        generatorvo=(GeneratorVo)httpsession.getAttribute("GENERATOR_OBJECT");
        setFieldDetails(httpservletrequest, dynaactionform, generatorvo);
        setScreenInfo(httpservletrequest);
        //refreshShowInfo(httpservletrequest, generatorvo);
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
        GeneratorVo generatorvo =new GeneratorVo();
        generatorvo=(GeneratorVo)httpsession.getAttribute("GENERATOR_OBJECT");
        setFieldDetails(httpservletrequest, dynaactionform, generatorvo);

        httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
        httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
        httpservletrequest.setAttribute("isComponentEditable", "N");
        setScreenInfo(httpservletrequest);

    	String back="";
        FacilityVo facilityvo = (FacilityVo)httpsession.getAttribute("FACILITY_OBJECT");
        String path= (String)dynaactionform.get("pathname");
        System.out.println("path:"+path);
        if(path.equals(facilityvo.getDecId()+"/generator/"+generatorvo.getId()+"-"+generatorvo.getFacilityDesignatedId().trim()))
        {
        back=path;
        }
        else
        {
        back=path.substring(0,path.lastIndexOf("/"));
        }

        System.out.println("back:"+back);
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
        GeneratorVo generatorvo = new GeneratorVo();
        generatorvo=(GeneratorVo)httpsession.getAttribute("GENERATOR_OBJECT");
        GeneratorEntity generatorentity=new GeneratorEntity();

        List list=generatorentity.getFuelConsumptionyearList(generatorvo.getId(),year);


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

    	public ActionForward o_fuelConsumptionInfoyearconsumtion(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
    	DynaActionForm dynaactionform = (DynaActionForm)actionform;
        String s = (String)dynaactionform.get("fcyear");
        System.out.println("Current Year"+s);
        String year=String.valueOf((Integer.parseInt(s)-1));
        System.out.println("Prev Year"+year);
    	HttpSession httpsession = httpservletrequest.getSession();
        GeneratorVo generatorvo = new GeneratorVo();
        generatorvo=(GeneratorVo)httpsession.getAttribute("GENERATOR_OBJECT");
        GeneratorEntity generatorentity=new GeneratorEntity();

        List list=generatorentity.geto_FuelConsumptionyearList(generatorvo.getId(),year);


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
    	
    	
    	public ActionForward o_pressureTestHrsInfoyearconsumtion(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
    	        throws Exception
    	    {
    	    	DynaActionForm dynaactionform = (DynaActionForm)actionform;
    	        String s = (String)dynaactionform.get("oil_fcyear");
    	        System.out.println("Current Year"+s);
    	        String year=String.valueOf((Integer.parseInt(s)-1));
    	        System.out.println("Prev Year"+year);
    	    	HttpSession httpsession = httpservletrequest.getSession();
    	        GeneratorVo generatorvo = new GeneratorVo();
    	        generatorvo=(GeneratorVo)httpsession.getAttribute("GENERATOR_OBJECT");
    	        GeneratorEntity generatorentity=new GeneratorEntity();

    	        List list=generatorentity.geto_PressureTestyearList(generatorvo.getId(),year);


    	    	System.out.println("haiiiiiiiiiiiiiiii"+list.size());
    	    	httpservletresponse.setContentType("text/html");
    	        PrintWriter printwriter = httpservletresponse.getWriter();
    	        printwriter.println("0|0|0|0|0|0|0|0|0|0|0|0");
    	       /* if(list != null && list.size() > 0)
    	        {
    	            Properties properties = UtilityObject.getRollingAverageInfo(list);
    	            printwriter.println(properties.get("PREVIOUS_CONSUMPTION"));
    	        }
    	        else
    	        {
    	         printwriter.println("0|0|0|0|0|0|0|0|0|0|0|0");
    			}*/


    	        printwriter.flush();
    	        return null;

    	    }
    	
    	public ActionForward g_pressureTestHrsInfoyearconsumtion(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
    	        throws Exception
    	    {
    	    	DynaActionForm dynaactionform = (DynaActionForm)actionform;
    	        String s = (String)dynaactionform.get("gas_fcyear");
    	        System.out.println("Current Year"+s);
    	        String year=String.valueOf((Integer.parseInt(s)-1));
    	        System.out.println("Prev Year"+year);
    	    	HttpSession httpsession = httpservletrequest.getSession();
    	        GeneratorVo generatorvo = new GeneratorVo();
    	        generatorvo=(GeneratorVo)httpsession.getAttribute("GENERATOR_OBJECT");
    	        GeneratorEntity generatorentity=new GeneratorEntity();

    	        List list=generatorentity.getg_PressureTestyearList(generatorvo.getId(),year);


    	    	System.out.println("haiiiiiiiiiiiiiiiiG"+list.size());
    	    	httpservletresponse.setContentType("text/html");
    	        PrintWriter printwriter = httpservletresponse.getWriter();
    	        printwriter.println("0|0|0|0|0|0|0|0|0|0|0|0");
    	       /* if(list != null && list.size() > 0)
    	        {
    	            Properties properties = UtilityObject.getRollingAverageInfo(list);
    	            printwriter.println(properties.get("PREVIOUS_CONSUMPTION"));
    	        }
    	        else
    	        {
    	         printwriter.println("0|0|0|0|0|0|0|0|0|0|0|0");
    			}*/


    	        printwriter.flush();
    	        return null;

    	    }
    
    	public ActionForward anu_pressureTestHrsInfoyearconsumtion(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
    	        throws Exception
    	    {
    	    	DynaActionForm dynaactionform = (DynaActionForm)actionform;
    	        String s = (String)dynaactionform.get("anu_fcyear");
    	        System.out.println("Current Year"+s);
    	        String year=String.valueOf((Integer.parseInt(s)-1));
    	        System.out.println("Prev Year"+year);
    	    	HttpSession httpsession = httpservletrequest.getSession();
    	        GeneratorVo generatorvo = new GeneratorVo();
    	        generatorvo=(GeneratorVo)httpsession.getAttribute("GENERATOR_OBJECT");
    	        GeneratorEntity generatorentity=new GeneratorEntity();

    	        List list=generatorentity.getanu_PressureTestyearList(generatorvo.getId(),year);


    	    	System.out.println("haiiiiiiiiiiiiiiiiG"+list.size());
    	    	httpservletresponse.setContentType("text/html");
    	        PrintWriter printwriter = httpservletresponse.getWriter();
    	        printwriter.println("0|0|0|0|0|0|0|0|0|0|0|0");
    	       /* if(list != null && list.size() > 0)
    	        {
    	            Properties properties = UtilityObject.getRollingAverageInfo(list);
    	            printwriter.println(properties.get("PREVIOUS_CONSUMPTION"));
    	        }
    	        else
    	        {
    	         printwriter.println("0|0|0|0|0|0|0|0|0|0|0|0");
    			}*/


    	        printwriter.flush();
    	        return null;

    	    }
    public ActionForward engineRunningHrsInfoyearconsumtion(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
    	DynaActionForm dynaactionform = (DynaActionForm)actionform;
        String s = (String)dynaactionform.get("e_fcyear");
        System.out.println("Current Year"+s);
        String year=String.valueOf((Integer.parseInt(s)-1));
        System.out.println("Prev Year"+year);
    	HttpSession httpsession = httpservletrequest.getSession();
        GeneratorVo generatorvo = new GeneratorVo();
        generatorvo=(GeneratorVo)httpsession.getAttribute("GENERATOR_OBJECT");
        GeneratorEntity generatorentity=new GeneratorEntity();

        List list=generatorentity.getEngineRunningHrsyearList(generatorvo.getId(),year);


    	System.out.println("haiiiiiiiiiiiiiiiiG"+list.size());
    	httpservletresponse.setContentType("text/html");
        PrintWriter printwriter = httpservletresponse.getWriter();
        printwriter.println("0|0|0|0|0|0|0|0|0|0|0|0");
       /* if(list != null && list.size() > 0)
        {
            Properties properties = UtilityObject.getRollingAverageInfo(list);
            printwriter.println(properties.get("PREVIOUS_CONSUMPTION"));
        }
        else
        {
         printwriter.println("0|0|0|0|0|0|0|0|0|0|0|0");
		}*/


        printwriter.flush();
        return null;

    }

    public ActionForward add(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        log.debug("GeneratorAction - In Add");
        String s = "";
        String s3 = "";
        HttpSession httpsession = httpservletrequest.getSession();
        BuildingVo buildingvo = (BuildingVo)httpsession.getAttribute("BUILDING_OBJECT");
        GeneratorVo generatorvo = new GeneratorVo();
        addUpdateGenerator(dynaactionform, buildingvo, generatorvo);
        try
        {
            int i = GeneratorEntity.add(generatorvo);
            GeneratorVo generatorvo1 = GeneratorEntity.findByPrimaryKey(i);
            if(generatorvo1 != null)
            {
                httpsession.setAttribute("GENERATOR_OBJECT", generatorvo1);
                setFieldDetails(httpservletrequest, dynaactionform, generatorvo1);
            }
            httpservletrequest.setAttribute("isComponentEditable", "N");
            String s1 = "Added Generator.";
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

    private void addUpdateGenerator(DynaActionForm dynaactionform, BuildingVo buildingvo, GeneratorVo generatorvo)
    {
        generatorvo.setBuildingId(buildingvo.getId());
        generatorvo.setFacilityDesignatedId((String)dynaactionform.get("G_facilityDesignatedId"));
        generatorvo.setFloor((String)dynaactionform.get("G_floor"));
        generatorvo.setStateId((String)dynaactionform.get("G_stateId"));
        String s = (String)dynaactionform.get("G_primaryUse");
        if(UtilityObject.isNotEmpty(s))
            generatorvo.setPrimaryUse(Integer.parseInt(s));
        generatorvo.setYearInstalled((String)dynaactionform.get("G_yearInstalled"));
        generatorvo.setStatus(Integer.parseInt((String)dynaactionform.get("G_status")));
        generatorvo.setDisconnecteddate((String)dynaactionform.get("G_disconnecteddate"));
        generatorvo.setManufacturer((String)dynaactionform.get("G_manufacturer"));       
        generatorvo.setModel((String)dynaactionform.get("G_model"));
        generatorvo.setSerial((String)dynaactionform.get("G_serial"));
        generatorvo.setBurnerMake((String)dynaactionform.get("G_burnerMake"));
        generatorvo.setBurnerModel((String)dynaactionform.get("G_burnerModel"));
        generatorvo.setCapacity((String)dynaactionform.get("G_capacity"));
        s = (String)dynaactionform.get("G_burnerType");
        if(UtilityObject.isNotEmpty(s) && s.equalsIgnoreCase("Y"))
            generatorvo.setBurnerType(2);
        else
            generatorvo.setBurnerType(1);
        s = (String)dynaactionform.get("G_primaryFuel");
        log.debug((new StringBuilder()).append("G_primaryFuel=").append(s).toString());
        if(UtilityObject.isNotEmpty(s))
            generatorvo.setPrimaryFuel(Integer.parseInt(s));
        s = (String)dynaactionform.get("G_secondaryFuel");
        log.debug((new StringBuilder()).append("G_secondaryFuel=").append(s).toString());
        if(UtilityObject.isNotEmpty(s))
            generatorvo.setSecondaryFuel(Integer.parseInt(s));
        s = (String)dynaactionform.get("G_fuelFrom");
        if(UtilityObject.isNotEmpty(s))
            generatorvo.setFuelFrom(Integer.parseInt(s));
        generatorvo.setHasDayTank(UtilityObject.convertBoolean((String)dynaactionform.get("G_hasDayTank")));
        s = (String)dynaactionform.get("G_dayTankFrom");
        if(UtilityObject.isNotEmpty(s))
            generatorvo.setDayTankFrom(Integer.parseInt(s));
        generatorvo.setNycDob((String)dynaactionform.get("G_nycDob"));
        generatorvo.setMea((String)dynaactionform.get("G_mea"));
        generatorvo.setDep((String)dynaactionform.get("G_dep"));
        generatorvo.setSechduelC(UtilityObject.convertBoolean((String)dynaactionform.get("G_sechduelC")));
        generatorvo.setPlanApproval(UtilityObject.convertBoolean((String)dynaactionform.get("G_planApproval")));
        s = (String)dynaactionform.get("G_stackFrom");
        if(UtilityObject.isNotEmpty(s))
            generatorvo.setStackFrom(Integer.parseInt(s));
        generatorvo.setRecordsInBoundBook(UtilityObject.convertBoolean((String)dynaactionform.get("G_isRecordInBook")));
        generatorvo.setDecPermitObtained(UtilityObject.convertBoolean((String)dynaactionform.get("G_decPermitObtained")));
        generatorvo.setDepPermitObtained(UtilityObject.convertBoolean((String)dynaactionform.get("G_depPermitObtained")));
        generatorvo.setStackTest((String)dynaactionform.get("G_stackTest"));
        generatorvo.setStackProtSubmitted((String)dynaactionform.get("G_stackTestProtSubmited"));
        generatorvo.setTestConductedBy((String)dynaactionform.get("G_testConductedBy"));
        generatorvo.setTestRptSubmited(UtilityObject.convertBoolean((String)dynaactionform.get("G_testReportSubmited")));
        generatorvo.setComplyWithNoxPlan(UtilityObject.convertBoolean((String)dynaactionform.get("G_compyWithNoxRactPlan")));
        generatorvo.setRetestPlanned(UtilityObject.convertBoolean((String)dynaactionform.get("G_retestPlanned")));
        s = UtilityObject.convertToString(UtilityObject.convertToDate((String)dynaactionform.get("G_nextStackTestDate")), "yyyy-MM-dd");
        generatorvo.setNextStackTestDate(s);
		generatorvo.setNextStackTestDateNote((String)dynaactionform.get("G_nextStackTestDateNote"));
        generatorvo.setCompyWithPMRactPlan((String)dynaactionform.get("G_compyWithPMRactPlan"));
        generatorvo.setGasfuelgapping((String)dynaactionform.get("G_gasfuelgapping"));
        generatorvo.setGasemmisionfactor((String)dynaactionform.get("G_gasemmisionfactor"));
        generatorvo.setOilfuelgapping((String)dynaactionform.get("G_oilfuelgapping"));
        generatorvo.setOilemmisionfactor((String)dynaactionform.get("G_oilemmisionfactor"));
        s = UtilityObject.convertToString(UtilityObject.convertToDate((String)dynaactionform.get("G_StackTestDate")), "yyyy-MM-dd");
        generatorvo.setStackTestDate(s);
        s = UtilityObject.convertToString(UtilityObject.convertToDate((String)dynaactionform.get("G_ProtocalSubmittalDate")), "yyyy-MM-dd");
        generatorvo.setProtocalSubmittalDate(s);
        generatorvo.setTestPassed((String)dynaactionform.get("G_testPassed"));
        generatorvo.setFuelgapping((String)dynaactionform.get("G_fuelgapping"));
        generatorvo.setFuelCappingUnderLimit(UtilityObject.convertBoolean((String)dynaactionform.get("G_fuelCaping")));
        generatorvo.setActionTaken((String)dynaactionform.get("G_actiontaken"));
        generatorvo.setG_oilso2((String)dynaactionform.get("g_oilso2"));
		generatorvo.setG_gasso2((String)dynaactionform.get("g_gasso2"));
		generatorvo.setG_so2allowable((String)dynaactionform.get("g_so2allowable"));
		generatorvo.setG_noxallowable((String)dynaactionform.get("g_noxallowable"));
        generatorvo.setDobsignoff((String)dynaactionform.get("dobsignoff"));
        generatorvo.setFiredepartmentapproval((String)dynaactionform.get("firedepartmentapproval"));
		generatorvo.setPermitStatus((String)dynaactionform.get("depPermitStatus"));
		generatorvo.setPermitExpire((String)dynaactionform.get("depPermitExpire"));
		generatorvo.setDobFiling((String)dynaactionform.get("dobfiling"));
		generatorvo.setCapablefuel((String)dynaactionform.get("capablefuel"));
		//generatorvo.setOilFiring((String)dynaactionform.get("oilfiring"));
		
		String ot = (String)dynaactionform.get("oilfiring");
         if(ot.equalsIgnoreCase("Yes"))
        	 generatorvo.setOilFiring(true);
       else
    	    generatorvo.setOilFiring(false);
         
         
    }

    public ActionForward update(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        log.debug("GeneratorAction - In update");
        String s = "";
        String s3 = "";
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        HttpSession httpsession = httpservletrequest.getSession();
        GeneratorVo generatorvo = (GeneratorVo)httpsession.getAttribute("GENERATOR_OBJECT");
        String facilityDesignatedid=generatorvo.getFacilityDesignatedId();
        BuildingVo buildingvo = (BuildingVo)httpsession.getAttribute("BUILDING_OBJECT");
        addUpdateGenerator(dynaactionform, buildingvo, generatorvo);
        try
        {
            GeneratorEntity.update(generatorvo);
            GeneratorVo generatorvo1 = GeneratorEntity.findByPrimaryKey(generatorvo.getId());
            if(generatorvo1 != null)
            {

            			FacilityVo facilityvo = (FacilityVo)httpsession.getAttribute("FACILITY_OBJECT");
	            		try
				        {


					        File f=new File(httpservletrequest.getRealPath("/")+"/clientfolder/"+facilityvo.getDecId()+"/generator/"+generatorvo1.getId()+"-"+facilityDesignatedid.trim());


					        if(f.isDirectory())
					        {


						    	File newFileName=new File(httpservletrequest.getRealPath("/")+"/clientfolder/"+facilityvo.getDecId()+"/generator/"+generatorvo1.getId()+"-"+generatorvo1.getFacilityDesignatedId().trim());
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




                httpsession.setAttribute("GENERATOR_OBJECT", generatorvo1);
                setFieldDetails(httpservletrequest, dynaactionform, generatorvo1);
            }
            httpservletrequest.setAttribute("isComponentEditable", "N");
            String s1 = "Updated Generator.";
            String s4 = "CONFIRMATION";
        }
        catch(TrackingException trackingexception)
        {
            String s2 = trackingexception.getMessage();
            String s5 = "ERROR";
            if(log.isErrorEnabled())
                log.error("Error in update", trackingexception);
        }
        setScreenInfo(httpservletrequest);
        httpservletrequest.setAttribute("isComponentEditable", "N");
        return actionmapping.findForward("success");
    }

    public ActionForward delete(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        log.debug("GeneratorAction - In view");
        httpservletrequest.setAttribute("isComponentEditable", "N");
        HttpSession httpsession = httpservletrequest.getSession();
        String s = (String)dynaactionform.get("id");
        int i = -99;
        if(s != null && s.trim().length() > 0)
            i = Integer.parseInt(s);
        GeneratorVo generatorvo = GeneratorEntity.findByPrimaryKey(i);
        if(generatorvo != null)
            httpsession.setAttribute("GENERATOR_OBJECT", generatorvo);
        GeneratorVo generatorvo1 = (GeneratorVo)httpsession.getAttribute("GENERATOR_OBJECT");
        BuildingVo buildingvo = (BuildingVo)httpsession.getAttribute("BUILDING_OBJECT");
        try
        {
            GeneratorEntity.delete(generatorvo1);
            FacilityVo facilityvo = (FacilityVo)httpsession.getAttribute("FACILITY_OBJECT");
		        try
		        {


		        File f=new File(httpservletrequest.getRealPath("/")+"/clientfolder/"+facilityvo.getDecId()+"/generator/"+generatorvo1.getId()+"-"+generatorvo1.getFacilityDesignatedId().trim());
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



        }
        catch(TrackingException trackingexception)
        {
            if(log.isErrorEnabled())
                log.error("Error in delete", trackingexception);
        }
        return actionmapping.findForward("successgen");
    }

    public ActionForward view(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        log.debug("GeneratorAction - In view");
        httpservletrequest.setAttribute("isComponentEditable", "N");
        HttpSession httpsession = httpservletrequest.getSession();
        String s = (String)dynaactionform.get("id");
        int i = -99;
        if(s != null && s.trim().length() > 0)
            i = Integer.parseInt(s);
        GeneratorVo generatorvo = GeneratorEntity.findByPrimaryKey(i);
        if(generatorvo != null)
        {
            httpsession.setAttribute("GENERATOR_OBJECT", generatorvo);
            setFieldDetails(httpservletrequest, dynaactionform, generatorvo);
        }
        setScreenInfo(httpservletrequest);
        System.out.println("@@@@@@@@@@@@@@@@@@2");
        return actionmapping.findForward("success");
    }

    public ActionForward edit(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        log.debug("GeneratorAction - In Edit");
        httpservletrequest.setAttribute("isComponentEditable", "Y");
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        HttpSession httpsession = httpservletrequest.getSession();
        String s = (String)dynaactionform.get("id");
        int i = -99;
        if(s != null && s.trim().length() > 0)
            i = Integer.parseInt(s);
        GeneratorVo generatorvo = GeneratorEntity.findByPrimaryKey(i);
        if(generatorvo != null)
        {
            httpsession.setAttribute("GENERATOR_OBJECT", generatorvo);
            setFieldDetailsforedit(httpservletrequest, dynaactionform, generatorvo, true);
        }
        setScreenInfo(httpservletrequest);
        httpservletrequest.setAttribute("isItForEdit", "Y");
        return actionmapping.findForward("success");
    }

    public ActionForward viewexist(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        log.debug("GeneratorAction - In Edit");
        httpservletrequest.setAttribute("isComponentEditable", "Y");
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        HttpSession httpsession = httpservletrequest.getSession();
        String s = (String)dynaactionform.get("id");
        int i = -99;
        if(s != null && s.trim().length() > 0)
            i = Integer.parseInt(s);
        GeneratorVo generatorvo = GeneratorEntity.findByPrimaryKey(i);
        if(generatorvo != null)
        {
            httpsession.setAttribute("GENERATOR_OBJECT", generatorvo);
            setFieldDetailsforedit(httpservletrequest, dynaactionform, generatorvo, true);
        }
        setScreenInfoforsearch(httpservletrequest);
        httpservletrequest.setAttribute("isItForEdit", "N");
        System.out.println("@@@@@@@@@@@@@@@@@@3");
        return actionmapping.findForward("success");
    }
//*************************************dep permit*********************************************//
    public ActionForward depPermitInfo(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        log.debug("GeneratorAction - In depPermitInfo");
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        HttpSession httpsession = httpservletrequest.getSession();
        GeneratorVo generatorvo = (GeneratorVo)httpsession.getAttribute("GENERATOR_OBJECT");
        BoilerPermitInfoVo boilerpermitinfovo = new BoilerPermitInfoVo();
        boilerpermitinfovo.setBoilerId(generatorvo.getId());
        boilerpermitinfovo.setDepId(1);
        boilerpermitinfovo.setIssueDate(UtilityObject.convertToDate((String)dynaactionform.get("depIssueDate")));
        boilerpermitinfovo.setExpirationDate(UtilityObject.convertToDate((String)dynaactionform.get("depExpDate")));
        boilerpermitinfovo.setDepCompliancecomments((String)dynaactionform.get("depExpDateNote"));
        int i = GeneratorEntity.addPermit(boilerpermitinfovo);
        log.debug((new StringBuilder()).append("Added dep permit id=").append(i).toString());
        httpservletrequest.setAttribute("isComponentEditable", "N");
        setFieldDetails(httpservletrequest, dynaactionform, generatorvo);
        setScreenInfo(httpservletrequest);
        return actionmapping.findForward("success");
    }

    public ActionForward editDepPermit(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        log.debug("GeneratorAction - In editDepPermit");
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        HttpSession httpsession = httpservletrequest.getSession();
        GeneratorVo generatorvo = (GeneratorVo)httpsession.getAttribute("GENERATOR_OBJECT");
        httpservletrequest.setAttribute("isComponentEditable", "N");
        httpservletrequest.setAttribute("GNR_EDIT_DEP_PERMIT", "Y");
        setFieldDetails(httpservletrequest, dynaactionform, generatorvo);
        setScreenInfo(httpservletrequest);
        httpservletrequest.setAttribute("hdnPermitId",httpservletrequest.getParameter("hdnPermitId"));
        return actionmapping.findForward("success");
    }

    public ActionForward updateDepPermitInfo(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        log.debug("GeneratorAction - In updateDepPermitInfo");
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        HttpSession httpsession = httpservletrequest.getSession();
        GeneratorVo generatorvo = (GeneratorVo)httpsession.getAttribute("GENERATOR_OBJECT");
        String s = httpservletrequest.getParameter("hdnPermitId");
        int i = -99;
        if(UtilityObject.isNotEmpty(s))
            i = Integer.parseInt(s);
        else
            throw new Exception("DEP Permit Id is null while updating the DEP Permit");
        BoilerPermitInfoVo boilerpermitinfovo = GeneratorEntity.findPermit(i);
        boilerpermitinfovo.setIssueDate(UtilityObject.convertToDate((String)dynaactionform.get("depIssueDate")));
        boilerpermitinfovo.setExpirationDate(UtilityObject.convertToDate((String)dynaactionform.get("depExpDate")));
        boilerpermitinfovo.setDepCompliancecomments((String)dynaactionform.get("depExpDateNote"));
        GeneratorEntity.updatePermit(boilerpermitinfovo);
        httpservletrequest.setAttribute("isComponentEditable", "N");
        setFieldDetails(httpservletrequest, dynaactionform, generatorvo);
        setScreenInfo(httpservletrequest);
        return actionmapping.findForward("success");
    }

    public ActionForward deleteDepPermit(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        log.debug("GeneratorAction - In updateDepPermitInfo");
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        HttpSession httpsession = httpservletrequest.getSession();
        GeneratorVo generatorvo = (GeneratorVo)httpsession.getAttribute("GENERATOR_OBJECT");
        String s = httpservletrequest.getParameter("hdnPermitId");
        int i = -99;
        if(UtilityObject.isNotEmpty(s))
            i = Integer.parseInt(s);
        else
            throw new Exception("DEP Permit Id is null while updating the DEP Permit");
        BoilerPermitInfoVo boilerpermitinfovo = GeneratorEntity.findPermit(i);
        GeneratorEntity.deletePermit(boilerpermitinfovo);
        httpservletrequest.setAttribute("isComponentEditable", "N");
        setFieldDetails(httpservletrequest, dynaactionform, generatorvo);
        setScreenInfo(httpservletrequest);
        return actionmapping.findForward("success");
    }
//**********************************************dob permit****************************************//
    public ActionForward dobPermitInfo(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        log.debug("GeneratorAction - In dobPermitInfo");
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        HttpSession httpsession = httpservletrequest.getSession();
        GeneratorVo generatorvo = (GeneratorVo)httpsession.getAttribute("GENERATOR_OBJECT");
        BoilerPermitInfoVo boilerpermitinfovo = new BoilerPermitInfoVo();
        boilerpermitinfovo.setBoilerId(generatorvo.getId());
        boilerpermitinfovo.setDepId(2);
        boilerpermitinfovo.setIssueDate(UtilityObject.convertToDate((String)dynaactionform.get("dobIssueDate")));
        boilerpermitinfovo.setExpirationDate(UtilityObject.convertToDate((String)dynaactionform.get("dobExpDate")));
        boilerpermitinfovo.setDepCompliancecomments((String)dynaactionform.get("dobExpDateNote"));
        boilerpermitinfovo.setYear((String)dynaactionform.get("dobYear"));
        int i = GeneratorEntity.addPermit(boilerpermitinfovo);
        log.debug((new StringBuilder()).append("Added dob permit id=").append(i).toString());
        httpservletrequest.setAttribute("isComponentEditable", "N");
        dynaactionform.set("dobYear", "");
        setFieldDetails(httpservletrequest, dynaactionform, generatorvo);
        setScreenInfo(httpservletrequest);
        return actionmapping.findForward("success");
    }

    public ActionForward editDobPermit(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        log.debug("GeneratorAction - In editDobPermit");
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        HttpSession httpsession = httpservletrequest.getSession();
        GeneratorVo generatorvo = (GeneratorVo)httpsession.getAttribute("GENERATOR_OBJECT");
        httpservletrequest.setAttribute("isComponentEditable", "N");
        httpservletrequest.setAttribute("GNR_EDIT_DOB_PERMIT", "Y");
        setFieldDetails(httpservletrequest, dynaactionform, generatorvo);
        setScreenInfo(httpservletrequest);
        httpservletrequest.setAttribute("hdnPermitId",httpservletrequest.getParameter("hdnPermitId"));
        return actionmapping.findForward("success");
    }

    public ActionForward deleteDobPermit(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        log.debug("GeneratorAction - In updateDobPermitInfo");
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        HttpSession httpsession = httpservletrequest.getSession();
        GeneratorVo generatorvo = (GeneratorVo)httpsession.getAttribute("GENERATOR_OBJECT");
        String s = httpservletrequest.getParameter("hdnPermitId");
        int i = -99;
        if(UtilityObject.isNotEmpty(s))
            i = Integer.parseInt(s);
        else
            throw new Exception("DOB Permit Id is null while updating the DOB Permit");
        BoilerPermitInfoVo boilerpermitinfovo = GeneratorEntity.findPermit(i);
        GeneratorEntity.deletePermit(boilerpermitinfovo);
        httpservletrequest.setAttribute("isComponentEditable", "N");
        setFieldDetails(httpservletrequest, dynaactionform, generatorvo);
        setScreenInfo(httpservletrequest);
        return actionmapping.findForward("success");
    }

    public ActionForward updateDobPermitInfo(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        log.debug("GeneratorAction - In updateDobPermitInfo");
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        HttpSession httpsession = httpservletrequest.getSession();
        GeneratorVo generatorvo = (GeneratorVo)httpsession.getAttribute("GENERATOR_OBJECT");
        String s = httpservletrequest.getParameter("hdnPermitId");
        int i = -99;
        if(UtilityObject.isNotEmpty(s))
            i = Integer.parseInt(s);
        else
            throw new Exception("DOB Permit Id is null while updating the DOB Permit");
        BoilerPermitInfoVo boilerpermitinfovo = GeneratorEntity.findPermit(i);
        boilerpermitinfovo.setIssueDate(UtilityObject.convertToDate((String)dynaactionform.get("dobIssueDate")));
        boilerpermitinfovo.setExpirationDate(UtilityObject.convertToDate((String)dynaactionform.get("dobExpDate")));
        boilerpermitinfovo.setDepCompliancecomments((String)dynaactionform.get("dobExpDateNote"));
        boilerpermitinfovo.setYear((String)dynaactionform.get("dobYear"));
        GeneratorEntity.updatePermit(boilerpermitinfovo);
        httpservletrequest.setAttribute("isComponentEditable", "N");
        dynaactionform.set("dobYear", "");
        setFieldDetails(httpservletrequest, dynaactionform, generatorvo);
        setScreenInfo(httpservletrequest);
        return actionmapping.findForward("success");
    }
//***********************************************Cfr permit starts*************************//
    /*private GeneratorVo getGeneratorVo(HttpServletRequest httpservletrequest)
			throws Exception {
		return (GeneratorVo) httpservletrequest.getSession().getAttribute(
				"GENERATOR_OBJECT");
	}*/
    
 public ActionForward cfrPermitInfo(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        log.debug("GeneratorAction - In cfrPermitInfo");
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        HttpSession httpsession = httpservletrequest.getSession();
        GeneratorVo generatorvo = (GeneratorVo)httpsession.getAttribute("GENERATOR_OBJECT");
       // GeneratorVo generatorvo = getGeneratorVo(httpservletrequest);
        GeneratorCfrPermitInfoVo generatorcfrpermitinfovo = new GeneratorCfrPermitInfoVo();
        generatorcfrpermitinfovo.setGeneratorId(generatorvo.getId());
       // generatorcfrpermitinfovo.setDepId(1);
        generatorcfrpermitinfovo.setInitialTestDate(UtilityObject.convertToDate((String)dynaactionform.get("initialTestDate")));        
        generatorcfrpermitinfovo.setInitialPressureOil((String)dynaactionform.get("initialPressureOil"));
        generatorcfrpermitinfovo.setInitialPressureGas((String)dynaactionform.get("initialPressureGas"));
        generatorcfrpermitinfovo.setLastSubsequentTestDate(UtilityObject.convertToDate((String)dynaactionform.get("lastSubsequentTestDate")));
        generatorcfrpermitinfovo.setSubsequentPressureOil((String)dynaactionform.get("subsequentPressureOil"));
        generatorcfrpermitinfovo.setSubsequentPressureGas((String)dynaactionform.get("subsequentPressureGas"));
        
        int i = GeneratorEntity.addCfrPermit(generatorcfrpermitinfovo);
        dynaactionform.set("initialTestDate", "");
		dynaactionform.set("initialPressureOil", "");
		dynaactionform.set("initialPressureGas", "");
		dynaactionform.set("lastSubsequentTestDate", "");
		dynaactionform.set("subsequentPressureOil", "");
		dynaactionform.set("subsequentPressureGas", "");
        log.debug((new StringBuilder()).append("Added cfr permit id=").append(i).toString());
        
        setFieldDetails(httpservletrequest, dynaactionform, generatorvo);
        httpservletrequest.setAttribute("isComponentEditable", "N");
        setScreenInfo(httpservletrequest);
        return actionmapping.findForward("success");               
        
    	}
        
      

    public ActionForward editCfrPermit(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        log.debug("GeneratorAction - In editCfrPermit");
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        HttpSession httpsession = httpservletrequest.getSession();
        GeneratorVo generatorvo = (GeneratorVo)httpsession.getAttribute("GENERATOR_OBJECT");
      //  GeneratorVo generatorvo = getGeneratorVo(httpservletrequest);
        setFieldDetails(httpservletrequest, dynaactionform, generatorvo);
        httpservletrequest.setAttribute("isComponentEditable", "N");
        httpservletrequest.setAttribute("GNR_EDIT_CFR_PERMIT", "Y");       
        httpservletrequest.setAttribute("hdnPermitId",httpservletrequest.getParameter("hdnPermitId"));
        setScreenInfo(httpservletrequest);
        return actionmapping.findForward("success");        
       
    }

    public ActionForward updateCfrPermitInfo(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        log.debug("GeneratorAction - In updateCfrPermitInfo");
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        HttpSession httpsession = httpservletrequest.getSession();
        GeneratorVo generatorvo = (GeneratorVo)httpsession.getAttribute("GENERATOR_OBJECT");
        //GeneratorVo generatorvo = getGeneratorVo(httpservletrequest);
        String s = httpservletrequest.getParameter("hdnPermitId");
        int i = -99;
        if(UtilityObject.isNotEmpty(s))
            i = Integer.parseInt(s);
        else
            throw new Exception("CFR Permit Id is null while updating the CFR Permit");            
            
        GeneratorCfrPermitInfoVo generatorcfrpermitinfovo = GeneratorEntity.getCfrPermit(i);
        
        generatorcfrpermitinfovo.setInitialTestDate(UtilityObject.convertToDate((String)dynaactionform.get("initialTestDate")));       
        String s1 = (String)dynaactionform.get("initialPressureOil");
       
        generatorcfrpermitinfovo.setInitialPressureOil((String)dynaactionform.get("initialPressureOil"));
        generatorcfrpermitinfovo.setInitialPressureGas((String)dynaactionform.get("initialPressureGas"));
        generatorcfrpermitinfovo.setSubsequentPressureOil((String)dynaactionform.get("subsequentPressureOil"));
        generatorcfrpermitinfovo.setSubsequentPressureGas((String)dynaactionform.get("subsequentPressureGas"));
        
        GeneratorEntity.updateCfrPermit(generatorcfrpermitinfovo);
        dynaactionform.set("initialTestDate", "");
		dynaactionform.set("initialPressureOil", "");
		dynaactionform.set("initialPressureGas", "");
		dynaactionform.set("lastSubsequentTestDate", "");
		dynaactionform.set("subsequentPressureOil", "");
		dynaactionform.set("subsequentPressureGas", "");
		setFieldDetails(httpservletrequest, dynaactionform, generatorvo);
        httpservletrequest.setAttribute("isComponentEditable", "N");       
        setScreenInfo(httpservletrequest);
        return actionmapping.findForward("success");
       
    }

    public ActionForward deleteCfrPermit(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        log.debug("GeneratorAction - In deleteCfrPermit");
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        HttpSession httpsession = httpservletrequest.getSession();
        GeneratorVo generatorvo = (GeneratorVo)httpsession.getAttribute("GENERATOR_OBJECT");
        String s = httpservletrequest.getParameter("hdnPermitId");
        int i = -99;
        if(UtilityObject.isNotEmpty(s))
            i = Integer.parseInt(s);
        else
            throw new Exception("CFR Permit Id is null while deleting the CFR Permit");
        GeneratorCfrPermitInfoVo generatorcfrpermitinfovo = GeneratorEntity.getCfrPermit(i);
        GeneratorEntity.deleteCfrPermit(generatorcfrpermitinfovo);
        dynaactionform.set("initialTestDate", "");
		dynaactionform.set("initialPressureOil", "");
		dynaactionform.set("initialPressureGas", "");
		dynaactionform.set("lastSubsequentTestDate", "");
		dynaactionform.set("subsequentPressureOil", "");
		dynaactionform.set("subsequentPressureGas", "");
        httpservletrequest.setAttribute("isComponentEditable", "N");
        setFieldDetails(httpservletrequest, dynaactionform, generatorvo);
        setScreenInfo(httpservletrequest);
        return actionmapping.findForward("success");
    }
    
  //***********************************************fuel consumption*************************//
    public ActionForward fuelConsumptionInfo(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {


        log.debug("GeneratorAction - In fuelConsumptionInfo");
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        HttpSession httpsession = httpservletrequest.getSession();
        GeneratorVo generatorvo = (GeneratorVo)httpsession.getAttribute("GENERATOR_OBJECT");
        FuelConsumptionVo fuelconsumptionvo = new FuelConsumptionVo();
        fuelconsumptionvo.setEntityId(generatorvo.getId());
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
            GeneratorEntity.updateFuelConsumption(fuelconsumptionvo);
            log.debug("Updated fuel object");
        } else
        {
            int i = GeneratorEntity.addFuelConsumption(fuelconsumptionvo);
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
            GeneratorEntity.updateFuelConsumption(fuelconsumptionvo);
            log.debug("Updated fuel object");
        }
        else
        {
            int i = GeneratorEntity.addFuelConsumption(fuelconsumptionvo);
            log.debug((new StringBuilder()).append("Added fuel id=").append(i).toString());
        }


        }


        generatorvo.setFuelConsumptionList(null);
        generatorvo.seto_FuelConsumptionList(null);
        httpservletrequest.setAttribute("isComponentEditable", "N");
        setFieldDetails(httpservletrequest, dynaactionform, generatorvo);
        setScreenInfo(httpservletrequest);
        return actionmapping.findForward("success");
    }


    public ActionForward deletefuelConsumptionInfo(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        log.debug("GeneratorAction - In fuelConsumptionInfo");
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        HttpSession httpsession = httpservletrequest.getSession();
        GeneratorVo generatorvo = (GeneratorVo)httpsession.getAttribute("GENERATOR_OBJECT");
        FuelConsumptionVo fuelconsumptionvo = new FuelConsumptionVo();
        fuelconsumptionvo.setEntityId(generatorvo.getId());
        int tid=Integer.parseInt((String)dynaactionform.get("fueldeleteConsumptionId"));
        GeneratorEntity.deletefuelconsumption(tid);
        generatorvo.seto_FuelConsumptionList(null);
        generatorvo.setFuelConsumptionList(null);
        httpservletrequest.setAttribute("isComponentEditable", "N");
        setFieldDetails(httpservletrequest, dynaactionform, generatorvo);
        setScreenInfo(httpservletrequest);
        return actionmapping.findForward("success");
    }
    //***************************************Engine Running Hrs***************************//
    
    
    public ActionForward engineRunningHrsInfo(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {


        log.debug("GeneratorAction - In engineRunningHrsInfo");
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        HttpSession httpsession = httpservletrequest.getSession();
        GeneratorVo generatorvo = (GeneratorVo)httpsession.getAttribute("GENERATOR_OBJECT");
        EngineRunningHrsVo enginerunninghrsvo = new EngineRunningHrsVo();
        enginerunninghrsvo.setEntityId(generatorvo.getId());
        String ttype=(String)dynaactionform.get("ebctype");
        if(ttype.equals("1"))
        {
        enginerunninghrsvo.setEbctype(ttype);
        enginerunninghrsvo.setYear((String)dynaactionform.get("e_rhyear"));
        enginerunninghrsvo.setJan(getValue("e_jan", dynaactionform));
        enginerunninghrsvo.setFeb(getValue("e_feb", dynaactionform));
        enginerunninghrsvo.setMar(getValue("e_mar", dynaactionform));
        enginerunninghrsvo.setApr(getValue("e_apr", dynaactionform));
        enginerunninghrsvo.setMay(getValue("e_may", dynaactionform));
        enginerunninghrsvo.setJun(getValue("e_jun", dynaactionform));
        enginerunninghrsvo.setJul(getValue("e_jul", dynaactionform));
        enginerunninghrsvo.setAug(getValue("e_aug", dynaactionform));
        enginerunninghrsvo.setSep(getValue("e_sep", dynaactionform));
        enginerunninghrsvo.setOct(getValue("e_oct", dynaactionform));
        enginerunninghrsvo.setNov(getValue("e_nov", dynaactionform));
        enginerunninghrsvo.setDec(getValue("e_dec", dynaactionform));
      //  enginerunninghrsvo.setYearToDate(getValue("e_yearToDate", dynaactionform));
        //enginerunninghrsvo.setRollingConsumption(getValue("consumption", dynaactionform));
        //enginerunninghrsvo.setCompliant(UtilityObject.convertBoolean((String)dynaactionform.get("e_compliance")));
        enginerunninghrsvo.setLocked(UtilityObject.convertBoolean((String)dynaactionform.get("e_lock")));
        if(UtilityObject.isNotEmpty((String)dynaactionform.get("engineRunningHrsId")))
        {
            enginerunninghrsvo.setId(UtilityObject.getIntFromString((String)dynaactionform.get("engineRunningHrsId")));
            GeneratorEntity.updateEngineRunningHrs(enginerunninghrsvo);
            log.debug("Updated Engine Running Hrs object");
        } else
        {
            int i = GeneratorEntity.addEngineRunningHrs(enginerunninghrsvo);
            log.debug((new StringBuilder()).append("Added Engine Running Hrs id=").append(i).toString());
        }
        }       
        else if(ttype.equals("2"))
        {

        	enginerunninghrsvo.setEbctype(ttype);
            enginerunninghrsvo.setYear((String)dynaactionform.get("oil_fcyear"));
            enginerunninghrsvo.setJan(getValue("oil_jan", dynaactionform));
            enginerunninghrsvo.setFeb(getValue("oil_feb", dynaactionform));
            enginerunninghrsvo.setMar(getValue("oil_mar", dynaactionform));
            enginerunninghrsvo.setApr(getValue("oil_apr", dynaactionform));
            enginerunninghrsvo.setMay(getValue("oil_may", dynaactionform));
            enginerunninghrsvo.setJun(getValue("oil_jun", dynaactionform));
            enginerunninghrsvo.setJul(getValue("oil_jul", dynaactionform));
            enginerunninghrsvo.setAug(getValue("oil_aug", dynaactionform));
            enginerunninghrsvo.setSep(getValue("oil_sep", dynaactionform));
            enginerunninghrsvo.setOct(getValue("oil_oct", dynaactionform));
            enginerunninghrsvo.setNov(getValue("oil_nov", dynaactionform));
            enginerunninghrsvo.setDec(getValue("oil_dec", dynaactionform));
           // enginerunninghrsvo.setYearToDate(getValue("oil_yearToDate", dynaactionform));
            //enginerunninghrsvo.setRollingConsumption(getValue("consumption", dynaactionform));
            //enginerunninghrsvo.setCompliant(UtilityObject.convertBoolean((String)dynaactionform.get("oil_compliance")));
            enginerunninghrsvo.setLocked(UtilityObject.convertBoolean((String)dynaactionform.get("oil_lock")));
            if(UtilityObject.isNotEmpty((String)dynaactionform.get("oil_monthlyPressureId")))
            {
                enginerunninghrsvo.setId(UtilityObject.getIntFromString((String)dynaactionform.get("oil_monthlyPressureId")));
                GeneratorEntity.updateEngineRunningHrs(enginerunninghrsvo);
                log.debug("Updated Pressure Test Oil object");
            } else
            {
                int i = GeneratorEntity.addEngineRunningHrs(enginerunninghrsvo);
                log.debug((new StringBuilder()).append("Added Pressure Test Oil id=").append(i).toString());
            }
        }
        else if(ttype.equals("3"))
        {

        	enginerunninghrsvo.setEbctype(ttype);
            enginerunninghrsvo.setYear((String)dynaactionform.get("gas_fcyear"));
            enginerunninghrsvo.setJan(getValue("gas_jan", dynaactionform));
            enginerunninghrsvo.setFeb(getValue("gas_feb", dynaactionform));
            enginerunninghrsvo.setMar(getValue("gas_mar", dynaactionform));
            enginerunninghrsvo.setApr(getValue("gas_apr", dynaactionform));
            enginerunninghrsvo.setMay(getValue("gas_may", dynaactionform));
            enginerunninghrsvo.setJun(getValue("gas_jun", dynaactionform));
            enginerunninghrsvo.setJul(getValue("gas_jul", dynaactionform));
            enginerunninghrsvo.setAug(getValue("gas_aug", dynaactionform));
            enginerunninghrsvo.setSep(getValue("gas_sep", dynaactionform));
            enginerunninghrsvo.setOct(getValue("gas_oct", dynaactionform));
            enginerunninghrsvo.setNov(getValue("gas_nov", dynaactionform));
            enginerunninghrsvo.setDec(getValue("gas_dec", dynaactionform));
           // enginerunninghrsvo.setYearToDate(getValue("gas_yearToDate", dynaactionform));
            //enginerunninghrsvo.setRollingConsumption(getValue("consumption", dynaactionform));
            //enginerunninghrsvo.setCompliant(UtilityObject.convertBoolean((String)dynaactionform.get("gas_compliance")));
            enginerunninghrsvo.setLocked(UtilityObject.convertBoolean((String)dynaactionform.get("gas_lock")));
            if(UtilityObject.isNotEmpty((String)dynaactionform.get("gas_monthlyPressureId")))
            {
                enginerunninghrsvo.setId(UtilityObject.getIntFromString((String)dynaactionform.get("gas_monthlyPressureId")));
                GeneratorEntity.updateEngineRunningHrs(enginerunninghrsvo);
                log.debug("Updated Pressure Test Gas object");
            } else
            {
                int i = GeneratorEntity.addEngineRunningHrs(enginerunninghrsvo);
                log.debug((new StringBuilder()).append("Added Pressure Test Gas id=").append(i).toString());
            }
        }
        else
        {

        	enginerunninghrsvo.setEbctype(ttype);
            enginerunninghrsvo.setYear((String)dynaactionform.get("anu_fcyear"));
            enginerunninghrsvo.setJan(getValue("anu_jan", dynaactionform));
            enginerunninghrsvo.setFeb(getValue("anu_feb", dynaactionform));
            enginerunninghrsvo.setMar(getValue("anu_mar", dynaactionform));
            enginerunninghrsvo.setApr(getValue("anu_apr", dynaactionform));
            enginerunninghrsvo.setMay(getValue("anu_may", dynaactionform));
            enginerunninghrsvo.setJun(getValue("anu_jun", dynaactionform));
            enginerunninghrsvo.setJul(getValue("anu_jul", dynaactionform));
            enginerunninghrsvo.setAug(getValue("anu_aug", dynaactionform));
            enginerunninghrsvo.setSep(getValue("anu_sep", dynaactionform));
            enginerunninghrsvo.setOct(getValue("anu_oct", dynaactionform));
            enginerunninghrsvo.setNov(getValue("anu_nov", dynaactionform));
            enginerunninghrsvo.setDec(getValue("anu_dec", dynaactionform));
           // enginerunninghrsvo.setYearToDate(getValue("anu_yearToDate", dynaactionform));
            //enginerunninghrsvo.setRollingConsumption(getValue("consumption", dynaactionform));
            //enginerunninghrsvo.setCompliant(UtilityObject.convertBoolean((String)dynaactionform.get("gas_compliance")));
            enginerunninghrsvo.setLocked(UtilityObject.convertBoolean((String)dynaactionform.get("anu_lock")));
            if(UtilityObject.isNotEmpty((String)dynaactionform.get("anu_monthlyPressureId")))
            {
                enginerunninghrsvo.setId(UtilityObject.getIntFromString((String)dynaactionform.get("anu_monthlyPressureId")));
                GeneratorEntity.updateEngineRunningHrs(enginerunninghrsvo);
                log.debug("Updated Annual Maintainance object");
            } else
            {
                int i = GeneratorEntity.addEngineRunningHrs(enginerunninghrsvo);
                log.debug((new StringBuilder()).append("Added Annual Maintainance id=").append(i).toString());
            }
        }

        generatorvo.setEngineRunningHrsList(null);
        generatorvo.seto_PressureTestHrsList(null);
        generatorvo.setg_PressureTestHrsList(null);
        generatorvo.setanu_PressureTestHrsList(null);
        httpservletrequest.setAttribute("isComponentEditable", "N");
        setFieldDetails(httpservletrequest, dynaactionform, generatorvo);
        setScreenInfo(httpservletrequest);
        return actionmapping.findForward("success");
    }


    public ActionForward deleteEngineRunningHrsInfo(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        log.debug("GeneratorAction - In engineRunningHrsInfo");
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        HttpSession httpsession = httpservletrequest.getSession();
        GeneratorVo generatorvo = (GeneratorVo)httpsession.getAttribute("GENERATOR_OBJECT");
        EngineRunningHrsVo enginerunninghrsvo = new EngineRunningHrsVo();
        enginerunninghrsvo.setEntityId(generatorvo.getId());
        int tid=Integer.parseInt((String)dynaactionform.get("enginedeleteRunningHrsId"));
        GeneratorEntity.deleteEngineRunningHrs(tid);
       
        generatorvo.setEngineRunningHrsList(null);
        generatorvo.seto_PressureTestHrsList(null);
        generatorvo.setg_PressureTestHrsList(null);
        generatorvo.setanu_PressureTestHrsList(null);
        
        httpservletrequest.setAttribute("isComponentEditable", "N");
        setFieldDetails(httpservletrequest, dynaactionform, generatorvo);
        setScreenInfo(httpservletrequest);
        return actionmapping.findForward("success");
    }
    
    //*******************************Ends********************************//

    public ActionForward displayControl(ActionMapping actionmapping, ActionForm actionform, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws Exception
    {
        log.debug("GeneratorAction - In displayControl");
        DynaActionForm dynaactionform = (DynaActionForm)actionform;
        String s = (String)dynaactionform.get("G_primaryUse");
        if(s != null && s.equalsIgnoreCase("2"))
            httpservletrequest.setAttribute("isPlm", "Y");
        else
            httpservletrequest.setAttribute("isPlm", "N");
        String s1 = httpservletrequest.getParameter("hdnContext");
        if(UtilityObject.isNotEmpty(s1) && s1.equalsIgnoreCase("Y"))
        {
            httpservletrequest.setAttribute("isItForEdit", "Y");
            httpservletrequest.setAttribute("isComponentEditable", "Y");
        }
        setScreenInfo(httpservletrequest);

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
            HttpSession httpsession = httpservletrequest.getSession();
            GeneratorVo generatorvo = (GeneratorVo)httpsession.getAttribute("GENERATOR_OBJECT");
            if(generatorvo != null)
                httpservletrequest.setAttribute("showAddBtn", "Y");
           
            else
                httpservletrequest.setAttribute("showAddBtn", "N");
        }
        catch(Exception exception) { }
    }

    private void setScreenInfoforsearch(HttpServletRequest httpservletrequest)
    {
        try
        {
            boolean flag = UtilityObject.isFacilityInNy(httpservletrequest);
            if(flag)
                httpservletrequest.setAttribute("isInNy", "Y");
        }
        catch(Exception exception) { }
    }

    private void setDropDown(HttpServletRequest httpservletrequest)
    {
        com.eespc.tracking.bo.DropDown dropdown = GeneratorUseEnum.getDropDownObj();
        httpservletrequest.setAttribute("GENERATOR_PRIMARY_USE", dropdown);        
        com.eespc.tracking.bo.DropDown dropdown1 = FurnaceOilTypeEnum.getDropDownObjOther();
        httpservletrequest.setAttribute("GENERATOR_PRIMARY_FUEL", dropdown1);
        com.eespc.tracking.bo.DropDown dropdown2 = YearEnum.getDropDownObj();
        httpservletrequest.setAttribute("YEARS", dropdown2);
        com.eespc.tracking.bo.DropDown dropdown3 = TankOperatingStatusEnum.getDropDownObj();
        httpservletrequest.setAttribute("TANK_OPERATION_STATUS", dropdown3);
    }

    private void setFieldDetails(HttpServletRequest httpservletrequest, DynaActionForm dynaactionform, GeneratorVo generatorvo)
    {
        setFieldDetails(httpservletrequest, dynaactionform, generatorvo, false);

    }

    private void setFieldDetailsforedit(HttpServletRequest httpservletrequest, DynaActionForm dynaactionform, GeneratorVo generatorvo)
    {
        setFieldDetailsforedit(httpservletrequest, dynaactionform, generatorvo, false);
    }

    private void setFieldDetailsforedit(HttpServletRequest httpservletrequest, DynaActionForm dynaactionform, GeneratorVo generatorvo, boolean flag)
    {
        if(generatorvo == null)
            return;
        dynaactionform.set("G_facilityDesignatedId", generatorvo.getFacilityDesignatedId());
        dynaactionform.set("G_floor", generatorvo.getFloor());
        dynaactionform.set("G_stateId", generatorvo.getStateId());
        int i = generatorvo.getPrimaryUse();


        if(i > 0)
        {
            GeneratorUseEnum generatoruseenum = GeneratorUseEnum.get(i);
            if(generatoruseenum != null)
                if(flag)
                    dynaactionform.set("G_primaryUse", (new StringBuilder()).append(generatoruseenum.getCode()).append("").toString());
                else
                    dynaactionform.set("G_primaryUse", generatoruseenum.getName());

            if(String.valueOf(generatoruseenum.getCode()).equalsIgnoreCase("2"))
            httpservletrequest.setAttribute("isPlm", "Y");
        	else
            httpservletrequest.setAttribute("isPlm", "N");

        }
        dynaactionform.set("G_yearInstalled", generatorvo.getYearInstalled());
        dynaactionform.set("G_status", (new StringBuilder()).append(generatorvo.getStatus()).append("").toString());
        dynaactionform.set("G_disconnecteddate", (new StringBuilder()).append(checkNullAndFill(generatorvo.getDisconnecteddate(), "")).append("").toString());
        dynaactionform.set("G_manufacturer", generatorvo.getManufacturer());        
        dynaactionform.set("G_model", generatorvo.getModel());
        dynaactionform.set("G_serial", generatorvo.getSerial());
        dynaactionform.set("G_burnerMake", generatorvo.getBurnerMake());
        dynaactionform.set("G_burnerModel", generatorvo.getBurnerModel());
        dynaactionform.set("G_capacity", generatorvo.getCapacity());
        long l = -99L;
        if(UtilityObject.isNotEmpty(generatorvo.getCapacity()))
        {
            try
            {
                l = Long.parseLong(generatorvo.getCapacity());
            }
            catch(NumberFormatException numberformatexception) {
            System.out.println("In Capacity Edit:"+numberformatexception);

            	 }
            if(l > 0L)
            {
                dynaactionform.set("G_kvaText", (new StringBuilder()).append("").append((double)l * 1.25D).toString());
                dynaactionform.set("G_hpText", (new StringBuilder()).append("").append((double)l * 1.3400000000000001D).toString());
                dynaactionform.set("G_mmbtuText", (new StringBuilder()).append("").append((double)l * 0.01D).toString());
            }
        } else
        {
            dynaactionform.set("G_kvaText", "");
            dynaactionform.set("G_hpText", "");
            dynaactionform.set("G_mmbtuText", "");
        }
        if(generatorvo.getBurnerType() == 1)
            dynaactionform.set("G_burnerType", "Dual Fuel");
        else
        if(generatorvo.getBurnerType() == 5)
            dynaactionform.set("G_burnerType", "Not Dual Fuel");
        i = generatorvo.getPrimaryFuel();
        if(i > 0)
        {
            FurnaceOilTypeEnum furnaceoiltypeenum = FurnaceOilTypeEnum.get(i);
            if(flag)
                dynaactionform.set("G_primaryFuel", furnaceoiltypeenum != null ? ((Object) ((new StringBuilder()).append(furnaceoiltypeenum.getCode()).append("").toString())) : "");
            else
                dynaactionform.set("G_primaryFuel", furnaceoiltypeenum != null ? ((Object) (furnaceoiltypeenum.getName())) : "");
        }
        double d = Double.parseDouble(generatorvo.getCapacity());
        double d1 = -99D;
        log.debug((new StringBuilder()).append("Capacity from vo=").append(generatorvo.getCapacity()).append(", intCapacity=").append(d).toString());
        if(d > 0.0D)
        {
            FurnaceOilTypeEnum furnaceoiltypeenum1 = FurnaceOilTypeEnum.get(i);
            if(i==-1)
            {
            dynaactionform.set("G_naturalGasFiringRate", "");
            dynaactionform.set("G_oilFiringRate", "");
            }
            else
            {

            if(furnaceoiltypeenum1.getCode() == 1)
            {
                double d2 = round(d * 0.01D * 1000D, 2);
                dynaactionform.set("G_naturalGasFiringRate", (new StringBuilder()).append(d2).append("").toString());
                dynaactionform.set("G_oilFiringRate", "");
            } else
            {
                int j = 140;
                dynaactionform.set("G_naturalGasFiringRate", "");
                dynaactionform.set("G_oilFiringRate", (new StringBuilder()).append(round((d * 0.01D * 1000D) / (double)j, 2)).append("").toString());
            }
        }
        } else
        {
            log.debug("OOPS i am not going to if loop");
        }
        i = generatorvo.getSecondaryFuel();
        if(i > 0)
        {
            FurnaceOilTypeEnum furnaceoiltypeenum2 = FurnaceOilTypeEnum.get(i);
            if(furnaceoiltypeenum2 != null)
            {
                dynaactionform.set("G_secondaryFuel", furnaceoiltypeenum2.getName());
                byte byte0 = -99;
                int k = furnaceoiltypeenum2.getCode();
                boolean flag1 = false;
            }
        }
        StorageTankVo storagetankvo = generatorvo.getFuelFromObj();
        dynaactionform.set("G_fuelFrom", (new StringBuilder()).append(generatorvo.getFuelFrom()).append("").toString());
        dynaactionform.set("G_fuelFromName", storagetankvo != null ? ((Object) (storagetankvo.getFacilityDesignatedId())) : "");
        dynaactionform.set("G_hasDayTank", UtilityObject.booleanToString(generatorvo.isHasDayTank()));
        StorageTankVo storagetankvo1 = generatorvo.getDayTankObj();
        dynaactionform.set("G_dayTankFrom", (new StringBuilder()).append(generatorvo.getDayTankFrom()).append("").toString());
        dynaactionform.set("G_dayTankFromName", storagetankvo1 != null ? ((Object) (storagetankvo1.getFacilityDesignatedId())) : "");
        dynaactionform.set("G_nycDob", generatorvo.getNycDob());
        dynaactionform.set("G_mea", generatorvo.getMea());
        dynaactionform.set("G_dep", generatorvo.getDep());
        dynaactionform.set("G_sechduelC", UtilityObject.booleanToString(generatorvo.isSechduelC()));
        dynaactionform.set("G_planApproval", UtilityObject.booleanToString(generatorvo.isPlanApproval()));
        StackVo stackvo = generatorvo.getStackFromObj();
        dynaactionform.set("G_stackFrom", (new StringBuilder()).append(generatorvo.getStackFrom()).append("").toString());
        dynaactionform.set("G_stackFromName", stackvo != null ? ((Object) (stackvo.getFacilityStackId())) : "");
        dynaactionform.set("G_isRecordInBook", UtilityObject.booleanToString(generatorvo.isRecordsInBoundBook()));
        dynaactionform.set("G_decPermitObtained", UtilityObject.booleanToString(generatorvo.isDecPermitObtained()));
        dynaactionform.set("G_depPermitObtained", UtilityObject.booleanToString(generatorvo.isDepPermitObtained()));
        dynaactionform.set("G_stackTest", generatorvo.isStackTest());
        dynaactionform.set("G_stackTestProtSubmited", generatorvo.isStackProtSubmitted());
        dynaactionform.set("G_testConductedBy", generatorvo.getTestConductedBy());
        dynaactionform.set("G_testReportSubmited", UtilityObject.booleanToString(generatorvo.isTestRptSubmited()));
        dynaactionform.set("G_compyWithNoxRactPlan", UtilityObject.booleanToString(generatorvo.isComplyWithNoxPlan()));
        dynaactionform.set("G_retestPlanned", UtilityObject.booleanToString(generatorvo.isRetestPlanned()));
        dynaactionform.set("G_nextStackTestDate", UtilityObject.checkNullAndFill(UtilityObject.convertYyyyMmDd2MmDdYyyy(generatorvo.getNextStackTestDate()),""));
		dynaactionform.set("G_nextStackTestDateNote", UtilityObject.checkNullAndFill(generatorvo.getNextStackTestDateNote(),""));
        dynaactionform.set("G_compyWithPMRactPlan", UtilityObject.checkNullAndFill(generatorvo.getCompyWithPMRactPlan(),""));
        dynaactionform.set("G_gasfuelgapping", UtilityObject.checkNullAndFill(generatorvo.getGasfuelgapping(),""));
        dynaactionform.set("G_gasemmisionfactor", UtilityObject.checkNullAndFill(generatorvo.getGasemmisionfactor(),""));
        dynaactionform.set("G_oilfuelgapping", UtilityObject.checkNullAndFill(generatorvo.getOilfuelgapping(),""));
        dynaactionform.set("G_oilemmisionfactor", UtilityObject.checkNullAndFill(generatorvo.getOilemmisionfactor(),""));
        dynaactionform.set("G_StackTestDate", UtilityObject.checkNullAndFill(UtilityObject.convertYyyyMmDd2MmDdYyyy(generatorvo.getStackTestDate()),""));
        dynaactionform.set("G_ProtocalSubmittalDate", UtilityObject.checkNullAndFill(UtilityObject.convertYyyyMmDd2MmDdYyyy(generatorvo.getProtocalSubmittalDate()),""));
        dynaactionform.set("G_compyWithPMRactPlan", UtilityObject.checkNullAndFill(generatorvo.getCompyWithPMRactPlan(),""));
        dynaactionform.set("G_testPassed", UtilityObject.checkNullAndFill(generatorvo.getTestPassed(),""));
        dynaactionform.set("G_fuelgapping", UtilityObject.checkNullAndFill(generatorvo.getFuelgapping(),""));

        dynaactionform.set("g_oilso2",generatorvo.getG_oilso2());
		dynaactionform.set("g_gasso2",generatorvo.getG_gasso2());
		dynaactionform.set("g_so2allowable",generatorvo.getG_so2allowable());
		dynaactionform.set("g_noxallowable",generatorvo.getG_noxallowable());

        dynaactionform.set("dobsignoff",generatorvo.getDobsignoff());
		dynaactionform.set("firedepartmentapproval",generatorvo.getFiredepartmentapproval());
		dynaactionform.set("depPermitStatus",generatorvo.getPermitStatus());
		dynaactionform.set("depPermitExpire",generatorvo.getPermitExpire());
		dynaactionform.set("dobfiling",generatorvo.getDobFiling());
		dynaactionform.set("capablefuel",generatorvo.getCapablefuel());
		//dynaactionform.set("oilfiring",generatorvo.getOilFiring());
		dynaactionform.set("oilfiring",UtilityObject.booleanToString(generatorvo.isOilFiring()));
		
        //generatorvo.setFuelCappingUnderLimit(UtilityObject.convertBoolean((String)dynaactionform.get("G_fuelCaping")));
        //generatorvo.setActionTaken((String)dynaactionform.get("G_actiontaken"));

        dynaactionform.set("G_fuelCaping", UtilityObject.booleanToString(generatorvo.isFuelCappingUnderLimit()));
        dynaactionform.set("G_actiontaken", generatorvo.getActionTaken());
        HttpSession httpsession = httpservletrequest.getSession();
        generatorvo.setEngineRunningHrsList(null);
        generatorvo.seto_PressureTestHrsList(null);
        generatorvo.setg_PressureTestHrsList(null);
        generatorvo.setanu_PressureTestHrsList(null);
        
        generatorvo.setFuelConsumptionList(null);
        generatorvo.seto_FuelConsumptionList(null);
        generatorvo.setPermitList(null);
        generatorvo.setCfrPermitList(null);
        
        List list2 = generatorvo.getCfrPermitList();        
        //  log.debug((new StringBuilder()).append("Generator CFR list size is ").append(list2 != null ? (new StringBuilder()).append(list2.size()).append("").toString() : "EMPTY").toString());        
          if(list2 != null && list2.size() > 0)
          {
          	httpservletrequest.setAttribute("GNR_CFR_PERMIT", list2);
          } else
          {
          	httpservletrequest.setAttribute("GNR_CFR_PERMIT", new ArrayList());
              log.debug("No Cfr Permit info");
          }
          
          
          
          
        List list = generatorvo.getFuelConsumptionList();
        List o_list1 = generatorvo.geto_FuelConsumptionList();
        log.debug((new StringBuilder()).append("Generator fuel consumption list size is ").append(list != null ? (new StringBuilder()).append(list.size()).append("").toString() : "EMPTY").toString());
        if(list != null && list.size() > 0)
        {
            Properties properties = UtilityObject.getRollingAverageInfo(list);
            httpservletrequest.setAttribute("hdnConsumption", properties.get("TOTAL"));
            httpservletrequest.setAttribute("hdnPreviousConsumption", properties.get("PREVIOUS_CONSUMPTION"));
            httpservletrequest.setAttribute("hdnCurrentMonthIndex", properties.get("CURRENT_MONTH_INDEX"));
            httpservletrequest.setAttribute("FUEL_CONSUMPTION", list);
        } else
        {
            httpservletrequest.setAttribute("FUEL_CONSUMPTION", new ArrayList());
            httpservletrequest.setAttribute("hdnConsumption", "");
            httpservletrequest.setAttribute("hdnPreviousConsumption", "0|0|0|0|0|0|0|0|0|0|0|0");
            httpservletrequest.setAttribute("hdnCurrentMonthIndex", "");
            log.debug("No Consumption info");
        }

        if(o_list1 != null && o_list1.size() > 0)
        {
            Properties properties = UtilityObject.getRollingAverageInfo(o_list1);
            httpservletrequest.setAttribute("o_hdnConsumption", properties.get("TOTAL"));
            httpservletrequest.setAttribute("o_hdnPreviousConsumption", properties.get("PREVIOUS_CONSUMPTION"));
            httpservletrequest.setAttribute("o_hdnCurrentMonthIndex", properties.get("CURRENT_MONTH_INDEX"));
            httpservletrequest.setAttribute("O_FUEL_CONSUMPTION", o_list1);
        } else
        {
            httpservletrequest.setAttribute("O_FUEL_CONSUMPTION", new ArrayList());
            httpservletrequest.setAttribute("o_hdnConsumption", "");
            httpservletrequest.setAttribute("o_hdnPreviousConsumption", "0|0|0|0|0|0|0|0|0|0|0|0");
            httpservletrequest.setAttribute("o_hdnCurrentMonthIndex", "");
            log.debug("No Consumption info");
        }


    List list6 = generatorvo.getanu_PressureTestHrsList();
        
        log.debug((new StringBuilder()).append("Generator Annual Maintainance Test Hrs list size is ").append(list6 != null ? (new StringBuilder()).append(list6.size()).append("").toString() : "EMPTY").toString());
        if(list6 != null && list6.size() > 0)
        {
            Properties properties = UtilityObject.getEngRollingAverageInfo(list6);
            httpservletrequest.setAttribute("anu_hdnConsumption", properties.get("TOTAL"));           
            httpservletrequest.setAttribute("ANU_MONTHLYPRESSURETEST", list6);
        } else
        {
            httpservletrequest.setAttribute("ANU_MONTHLYPRESSURETEST", new ArrayList());
            httpservletrequest.setAttribute("anu_hdnConsumption", "");          
            log.debug("No Annual Maintainance Test info");
        }
        
        List list5 = generatorvo.getg_PressureTestHrsList();
        
        log.debug((new StringBuilder()).append("Generator Gas Pressure Test Hrs list size is ").append(list5 != null ? (new StringBuilder()).append(list5.size()).append("").toString() : "EMPTY").toString());
        if(list5 != null && list5.size() > 0)
        {
            Properties properties = UtilityObject.getEngRollingAverageInfo(list5);
            httpservletrequest.setAttribute("gas_hdnConsumption", properties.get("TOTAL"));           
            httpservletrequest.setAttribute("GAS_MONTHLYPRESSURETEST", list5);
        } else
        {
            httpservletrequest.setAttribute("GAS_MONTHLYPRESSURETEST", new ArrayList());
            httpservletrequest.setAttribute("gas_hdnConsumption", "");          
            log.debug("No Gas Pressure Test info");
        }
         
        
		List list4 = generatorvo.geto_PressureTestHrsList();
        
        log.debug((new StringBuilder()).append("Generator Oil Pressure Test Hrs list size is ").append(list4 != null ? (new StringBuilder()).append(list4.size()).append("").toString() : "EMPTY").toString());
        if(list4 != null && list4.size() > 0)
        {
            Properties properties = UtilityObject.getEngRollingAverageInfo(list4);
            httpservletrequest.setAttribute("oil_hdnConsumption", properties.get("TOTAL"));           
            httpservletrequest.setAttribute("OIL_MONTHLYPRESSURETEST", list4);
        } else
        {
            httpservletrequest.setAttribute("OIL_MONTHLYPRESSURETEST", new ArrayList());
            httpservletrequest.setAttribute("oil_hdnConsumption", "");          
            log.debug("No Oil Pressure Test info");
        }
		
        

List list3 = generatorvo.getEngineRunningHrsList();
        
        log.debug((new StringBuilder()).append("Generator Engine Running Hrs list size is ").append(list3 != null ? (new StringBuilder()).append(list3.size()).append("").toString() : "EMPTY").toString());
        if(list3 != null && list3.size() > 0)
        {
            Properties properties = UtilityObject.getEngRollingAverageInfo(list3);
            httpservletrequest.setAttribute("e_hdnConsumption", properties.get("TOTAL"));          
            httpservletrequest.setAttribute("ENGINERUNNING_HRS", list3);
        } else
        {
            httpservletrequest.setAttribute("ENGINERUNNING_HRS", new ArrayList());
            httpservletrequest.setAttribute("e_hdnConsumption", "");           
            log.debug("No Engine Running Hrs info");
        }
    
        
      
       
		
        List list1 = generatorvo.getPermitList();
        ArrayList arraylist = new ArrayList();
        ArrayList arraylist1 = new ArrayList();
        int i1 = list1.size();
        if(list1 != null && i1 > 0)
        {
            for(int j1 = 0; j1 < i1; j1++)
            {
                BoilerPermitInfoVo boilerpermitinfovo = (BoilerPermitInfoVo)list1.get(j1);
                int k1 = boilerpermitinfovo.getDepId();
                if(k1 == 2)
                {
                    arraylist1.add(boilerpermitinfovo);
                    continue;
                }
                if(k1 == 1)
                    arraylist.add(boilerpermitinfovo);
            }

        }
         try
        {
            reset(httpservletrequest);
        }
        catch(Exception exception) { }
        

    

        httpsession.setAttribute("GNR_DOB_PERMIT", arraylist1);
        httpsession.setAttribute("GNR_DEP_PERMIT", arraylist); 
        
       /* httpservletrequest.setAttribute("hdnPermitId",
		httpservletrequest.getParameter("hdnPermitId"));*/
    
    }

    private void setFieldDetails(HttpServletRequest httpservletrequest, DynaActionForm dynaactionform, GeneratorVo generatorvo, boolean flag)
    {

        if(generatorvo == null)
            return;
        dynaactionform.set("G_facilityDesignatedId", generatorvo.getFacilityDesignatedId());
        dynaactionform.set("G_floor", generatorvo.getFloor());
        dynaactionform.set("G_stateId", generatorvo.getStateId());
        int i = generatorvo.getPrimaryUse();
        if(i > 0)
        {
            GeneratorUseEnum generatoruseenum = GeneratorUseEnum.get(i);
            if(generatoruseenum != null)
                if(flag)
                    dynaactionform.set("G_primaryUse", (new StringBuilder()).append(generatoruseenum.getCode()).append("").toString());
                else
                    dynaactionform.set("G_primaryUse", generatoruseenum.getName());

            if(String.valueOf(generatoruseenum.getCode()).equalsIgnoreCase("2"))
            httpservletrequest.setAttribute("isPlm", "Y");
        	else
            httpservletrequest.setAttribute("isPlm", "N");

        }

        dynaactionform.set("G_yearInstalled", generatorvo.getYearInstalled());
        TankOperatingStatusEnum tankoperatingstatusenum = TankOperatingStatusEnum.get(generatorvo.getStatus());
        if(tankoperatingstatusenum != null)
            dynaactionform.set("G_status", tankoperatingstatusenum.getName());
        dynaactionform.set("G_disconnecteddate", (new StringBuilder()).append(checkNullAndFill(generatorvo.getDisconnecteddate(), "")).append("").toString());
        dynaactionform.set("G_manufacturer", generatorvo.getManufacturer());       
        dynaactionform.set("G_model", generatorvo.getModel());
        dynaactionform.set("G_serial", generatorvo.getSerial());
        dynaactionform.set("G_burnerMake", generatorvo.getBurnerMake());
        dynaactionform.set("G_burnerModel", generatorvo.getBurnerModel());
        dynaactionform.set("G_capacity", generatorvo.getCapacity());
        long l = -99L;
        if(UtilityObject.isNotEmpty(generatorvo.getCapacity()))
        {
            try
            {
                l = Long.parseLong(generatorvo.getCapacity());
            }
            catch(NumberFormatException numberformatexception) { }
            if(l > 0L)
            {
                dynaactionform.set("G_kvaText", (new StringBuilder()).append("").append((double)l * 1.25D).toString());
                dynaactionform.set("G_hpText", (new StringBuilder()).append("").append((double)l * 1.3400000000000001D).toString());
                dynaactionform.set("G_mmbtuText", (new StringBuilder()).append("").append((double)l * 0.01D).toString());
            }
        } else
        {
            dynaactionform.set("G_kvaText", "");
            dynaactionform.set("G_hpText", "");
            dynaactionform.set("G_mmbtuText", "");
        }
        if(generatorvo.getBurnerType() == 1)
            dynaactionform.set("G_burnerType", "Dual Fuel");
        else
        if(generatorvo.getBurnerType() == 5)
            dynaactionform.set("G_burnerType", "Not Dual Fuel");
        i = generatorvo.getPrimaryFuel();
        if(i > 0)
        {

            FurnaceOilTypeEnum furnaceoiltypeenum = FurnaceOilTypeEnum.get(i);
            if(flag)
                dynaactionform.set("G_primaryFuel", furnaceoiltypeenum != null ? ((Object) ((new StringBuilder()).append(furnaceoiltypeenum.getCode()).append("").toString())) : "");
            else
                dynaactionform.set("G_primaryFuel", furnaceoiltypeenum != null ? ((Object) (furnaceoiltypeenum.getName())) : "");
        }
        double d = Double.parseDouble(generatorvo.getCapacity());
        log.debug((new StringBuilder()).append("Capacity from vo=").append(generatorvo.getCapacity()).append(", intCapacity=").append(d).toString());
        double d1 = -99D;


        if(d > 0.0D)
        {
            FurnaceOilTypeEnum furnaceoiltypeenum1 = FurnaceOilTypeEnum.get(i);

            if(i==-1)
            {
            dynaactionform.set("G_naturalGasFiringRate", "");
            dynaactionform.set("G_oilFiringRate", "");
            }
            else
            {
            if(furnaceoiltypeenum1.getCode() == 1)
            {
                double d2 = round(d * 0.01D * 1000D, 2);
                dynaactionform.set("G_naturalGasFiringRate", (new StringBuilder()).append(d2).append("").toString());
                dynaactionform.set("G_oilFiringRate", "");
            } else
            {
                int j = 140;
                dynaactionform.set("G_naturalGasFiringRate", "");
                dynaactionform.set("G_oilFiringRate", (new StringBuilder()).append(round((d * 0.01D * 1000D) / (double)j, 2)).append("").toString());
            }
        }
        } else
        {
            log.debug("OOPS i am not going to if loop");
        }
        i = generatorvo.getSecondaryFuel();
        if(i > 0)
        {
            FurnaceOilTypeEnum furnaceoiltypeenum2 = FurnaceOilTypeEnum.get(i);
            if(furnaceoiltypeenum2 != null)
            {
                dynaactionform.set("G_secondaryFuel", furnaceoiltypeenum2.getName());
                byte byte0 = -99;
                int k = furnaceoiltypeenum2.getCode();
                boolean flag1 = false;
            }
        }
        StorageTankVo storagetankvo = generatorvo.getFuelFromObj();
        dynaactionform.set("G_fuelFrom", (new StringBuilder()).append(generatorvo.getFuelFrom()).append("").toString());
        dynaactionform.set("G_fuelFromName", storagetankvo != null ? ((Object) (storagetankvo.getFacilityDesignatedId())) : "");
        dynaactionform.set("G_hasDayTank", UtilityObject.booleanToString(generatorvo.isHasDayTank()));
        StorageTankVo storagetankvo1 = generatorvo.getDayTankObj();
        dynaactionform.set("G_dayTankFrom", (new StringBuilder()).append(generatorvo.getDayTankFrom()).append("").toString());
        dynaactionform.set("G_dayTankFromName", storagetankvo1 != null ? ((Object) (storagetankvo1.getFacilityDesignatedId())) : "");
        dynaactionform.set("G_nycDob", generatorvo.getNycDob());
        dynaactionform.set("G_mea", generatorvo.getMea());
        dynaactionform.set("G_dep", generatorvo.getDep());
        dynaactionform.set("G_sechduelC", UtilityObject.booleanToString(generatorvo.isSechduelC()));
        dynaactionform.set("G_planApproval", UtilityObject.booleanToString(generatorvo.isPlanApproval()));

        StackVo stackvo = generatorvo.getStackFromObj();
        dynaactionform.set("G_stackFrom", (new StringBuilder()).append(generatorvo.getStackFrom()).append("").toString());
        dynaactionform.set("G_stackFromName", stackvo != null ? ((Object) (stackvo.getFacilityStackId())) : "");
        dynaactionform.set("G_isRecordInBook", UtilityObject.booleanToString(generatorvo.isRecordsInBoundBook()));
        dynaactionform.set("G_decPermitObtained", UtilityObject.booleanToString(generatorvo.isDecPermitObtained()));
        dynaactionform.set("G_depPermitObtained", UtilityObject.booleanToString(generatorvo.isDepPermitObtained()));
        dynaactionform.set("G_stackTest", generatorvo.isStackTest());
        dynaactionform.set("G_stackTestProtSubmited", generatorvo.isStackProtSubmitted());
        dynaactionform.set("G_testConductedBy", generatorvo.getTestConductedBy());
        dynaactionform.set("G_testReportSubmited", UtilityObject.booleanToString(generatorvo.isTestRptSubmited()));
        dynaactionform.set("G_compyWithNoxRactPlan", UtilityObject.booleanToString(generatorvo.isComplyWithNoxPlan()));
        dynaactionform.set("G_retestPlanned", UtilityObject.booleanToString(generatorvo.isRetestPlanned()));
        dynaactionform.set("G_nextStackTestDate", UtilityObject.convertYyyyMmDd2MmDdYyyy(generatorvo.getNextStackTestDate()));
        dynaactionform.set("G_nextStackTestDateNote", UtilityObject.checkNullAndFill(generatorvo.getNextStackTestDateNote(),""));
        dynaactionform.set("G_fuelCaping", UtilityObject.booleanToString(generatorvo.isFuelCappingUnderLimit()));
        dynaactionform.set("G_actiontaken", generatorvo.getActionTaken());

        dynaactionform.set("G_compyWithPMRactPlan", UtilityObject.checkNullAndFill(generatorvo.getCompyWithPMRactPlan(),""));
        dynaactionform.set("G_gasfuelgapping", UtilityObject.checkNullAndFill(generatorvo.getGasfuelgapping(),""));
        dynaactionform.set("G_gasemmisionfactor", UtilityObject.checkNullAndFill(generatorvo.getGasemmisionfactor(),""));
        dynaactionform.set("G_oilfuelgapping", UtilityObject.checkNullAndFill(generatorvo.getOilfuelgapping(),""));
        dynaactionform.set("G_oilemmisionfactor", UtilityObject.checkNullAndFill(generatorvo.getOilemmisionfactor(),""));
        dynaactionform.set("G_StackTestDate", UtilityObject.checkNullAndFill(UtilityObject.convertYyyyMmDd2MmDdYyyy(generatorvo.getStackTestDate()),""));
        dynaactionform.set("G_ProtocalSubmittalDate", UtilityObject.checkNullAndFill(UtilityObject.convertYyyyMmDd2MmDdYyyy(generatorvo.getProtocalSubmittalDate()),""));
        dynaactionform.set("G_compyWithPMRactPlan", UtilityObject.checkNullAndFill(generatorvo.getCompyWithPMRactPlan(),""));
        dynaactionform.set("G_testPassed", UtilityObject.checkNullAndFill(generatorvo.getTestPassed(),""));
        dynaactionform.set("G_fuelgapping", UtilityObject.checkNullAndFill(generatorvo.getFuelgapping(),""));
        dynaactionform.set("g_oilso2",generatorvo.getG_oilso2());
		dynaactionform.set("g_gasso2",generatorvo.getG_gasso2());
		dynaactionform.set("g_so2allowable",generatorvo.getG_so2allowable());
		dynaactionform.set("g_noxallowable",generatorvo.getG_noxallowable());
        dynaactionform.set("dobsignoff",generatorvo.getDobsignoff());
		dynaactionform.set("firedepartmentapproval",generatorvo.getFiredepartmentapproval());
		dynaactionform.set("depPermitStatus",generatorvo.getPermitStatus());
		dynaactionform.set("depPermitExpire",generatorvo.getPermitExpire());
		dynaactionform.set("dobfiling",generatorvo.getDobFiling());
		dynaactionform.set("capablefuel",generatorvo.getCapablefuel());
		//dynaactionform.set("oilfiring",generatorvo.getOilFiring());
		dynaactionform.set("oilfiring", UtilityObject.booleanToString(generatorvo.isOilFiring()));
		
		
        HttpSession httpsession = httpservletrequest.getSession();
        generatorvo.setFuelConsumptionList(null);
        generatorvo.seto_FuelConsumptionList(null);        
        generatorvo.setPermitList(null);
        generatorvo.setCfrPermitList(null);  
        
        List list2 = generatorvo.getCfrPermitList();
        if(list2 != null && list2.size() > 0)
        {
        	httpservletrequest.setAttribute("GNR_CFR_PERMIT", list2);
        } else
        {
        	httpservletrequest.setAttribute("GNR_CFR_PERMIT", new ArrayList());
            log.debug("No Cfr Permit info");
        }
        
        
        generatorvo.setEngineRunningHrsList(null);
        generatorvo.seto_PressureTestHrsList(null);
        generatorvo.setg_PressureTestHrsList(null);        
        generatorvo.setanu_PressureTestHrsList(null);
        
        
        List list = generatorvo.getFuelConsumptionList();
        List o_list1 = generatorvo.geto_FuelConsumptionList();
         System.out.println("In Generatoraction G:"+list.size());
        System.out.println("In Generatoraction O:"+o_list1.size());
        log.debug((new StringBuilder()).append("Generator fuel consumption list size is ").append(list != null ? (new StringBuilder()).append(list.size()).append("").toString() : "EMPTY").toString());
        if(list != null && list.size() > 0)
        {
            Properties properties = UtilityObject.getRollingAverageInfo(list);
            httpservletrequest.setAttribute("hdnConsumption", properties.get("TOTAL"));
            httpservletrequest.setAttribute("hdnPreviousConsumption", properties.get("PREVIOUS_CONSUMPTION"));
            httpservletrequest.setAttribute("hdnCurrentMonthIndex", properties.get("CURRENT_MONTH_INDEX"));
            httpservletrequest.setAttribute("FUEL_CONSUMPTION", list);
        } else
        {
            httpservletrequest.setAttribute("FUEL_CONSUMPTION", new ArrayList());
            httpservletrequest.setAttribute("hdnConsumption", "");
            httpservletrequest.setAttribute("hdnPreviousConsumption", "0|0|0|0|0|0|0|0|0|0|0|0");
            httpservletrequest.setAttribute("hdnCurrentMonthIndex", "");
            log.debug("No Consumption info");
        }


        if(o_list1 != null && o_list1.size() > 0)
        {
            Properties properties = UtilityObject.getRollingAverageInfo(o_list1);
            httpservletrequest.setAttribute("o_hdnConsumption", properties.get("TOTAL"));
            httpservletrequest.setAttribute("o_hdnPreviousConsumption", properties.get("PREVIOUS_CONSUMPTION"));
            httpservletrequest.setAttribute("o_hdnCurrentMonthIndex", properties.get("CURRENT_MONTH_INDEX"));
            httpservletrequest.setAttribute("O_FUEL_CONSUMPTION", o_list1);
        } else
        {
            httpservletrequest.setAttribute("O_FUEL_CONSUMPTION", new ArrayList());
            httpservletrequest.setAttribute("o_hdnConsumption", "");
            httpservletrequest.setAttribute("o_hdnPreviousConsumption", "0|0|0|0|0|0|0|0|0|0|0|0");
            httpservletrequest.setAttribute("o_hdnCurrentMonthIndex", "");
            log.debug("No Consumption info");
        }
		
        
        
        List list6 = generatorvo.getanu_PressureTestHrsList();
        
        log.debug((new StringBuilder()).append("Generator Annual Maintainance Test Hrs list size is ").append(list6 != null ? (new StringBuilder()).append(list6.size()).append("").toString() : "EMPTY").toString());
        if(list6 != null && list6.size() > 0)
        {
            Properties properties = UtilityObject.getEngRollingAverageInfo(list6);
            httpservletrequest.setAttribute("anu_hdnConsumption", properties.get("TOTAL"));           
            httpservletrequest.setAttribute("ANU_MONTHLYPRESSURETEST", list6);
        } else
        {
            httpservletrequest.setAttribute("ANU_MONTHLYPRESSURETEST", new ArrayList());
            httpservletrequest.setAttribute("anu_hdnConsumption", "");          
            log.debug("No Annual Maintainance Test info");
        }
         
        
        
        
        
        List list5 = generatorvo.getg_PressureTestHrsList();
        
        log.debug((new StringBuilder()).append("Generator Gas Pressure Test Hrs list size is ").append(list5 != null ? (new StringBuilder()).append(list5.size()).append("").toString() : "EMPTY").toString());
        if(list5 != null && list5.size() > 0)
        {
            Properties properties = UtilityObject.getEngRollingAverageInfo(list5);
            httpservletrequest.setAttribute("gas_hdnConsumption", properties.get("TOTAL"));           
            httpservletrequest.setAttribute("GAS_MONTHLYPRESSURETEST", list5);
        } else
        {
            httpservletrequest.setAttribute("GAS_MONTHLYPRESSURETEST", new ArrayList());
            httpservletrequest.setAttribute("gas_hdnConsumption", "");          
            log.debug("No Gas Pressure Test info");
        }
         
         
		List list4 = generatorvo.geto_PressureTestHrsList();
        
        log.debug((new StringBuilder()).append("Generator Oil Pressure Test Hrs list size is ").append(list4 != null ? (new StringBuilder()).append(list4.size()).append("").toString() : "EMPTY").toString());
        if(list4 != null && list4.size() > 0)
        {
            Properties properties = UtilityObject.getEngRollingAverageInfo(list4);
            httpservletrequest.setAttribute("oil_hdnConsumption", properties.get("TOTAL"));           
            httpservletrequest.setAttribute("OIL_MONTHLYPRESSURETEST", list4);
        } else
        {
            httpservletrequest.setAttribute("OIL_MONTHLYPRESSURETEST", new ArrayList());
            httpservletrequest.setAttribute("oil_hdnConsumption", "");          
            log.debug("No Oil Pressure Test info");
        }
		
		
		List list3 = generatorvo.getEngineRunningHrsList();
        
        log.debug((new StringBuilder()).append("Generator Engine Running Hrs list size is ").append(list3 != null ? (new StringBuilder()).append(list3.size()).append("").toString() : "EMPTY").toString());
        if(list3 != null && list3.size() > 0)
        {
            Properties properties = UtilityObject.getEngRollingAverageInfo(list3);
            httpservletrequest.setAttribute("e_hdnConsumption", properties.get("TOTAL"));          
            httpservletrequest.setAttribute("ENGINERUNNING_HRS", list3);
        } else
        {
            httpservletrequest.setAttribute("ENGINERUNNING_HRS", new ArrayList());
            httpservletrequest.setAttribute("e_hdnConsumption", "");           
            log.debug("No Engine Running Hrs info");
        }
        
		     

        List list1 = generatorvo.getPermitList();
        ArrayList arraylist = new ArrayList();
        ArrayList arraylist1 = new ArrayList();
        int i1 = list1.size();
        if(list1 != null && i1 > 0)
        {
            for(int j1 = 0; j1 < i1; j1++)
            {
                BoilerPermitInfoVo boilerpermitinfovo = (BoilerPermitInfoVo)list1.get(j1);
                int k1 = boilerpermitinfovo.getDepId();
                if(k1 == 2)
                {
                    arraylist1.add(boilerpermitinfovo);
                    continue;
                }
                if(k1 == 1)
                    arraylist.add(boilerpermitinfovo);
            }

        }

         try
        {
            reset(httpservletrequest);
        }
        catch(Exception exception) { }
       

        httpsession.setAttribute("GNR_DOB_PERMIT", arraylist1);
        httpsession.setAttribute("GNR_DEP_PERMIT", arraylist);
    	

    }


    public void reset(HttpServletRequest httpservletrequest)
        throws IOException, ServletException
    {
	    	//DynaActionForm dynaactionform = (DynaActionForm)actionform;
	        HttpSession httpsession = httpservletrequest.getSession();
	        GeneratorVo generatorvo = (GeneratorVo)httpsession.getAttribute("GENERATOR_OBJECT");
	        FacilityVo facilityvo = (FacilityVo)httpsession.getAttribute("FACILITY_OBJECT");
	        System.out.println("Facility Id: "+facilityvo.getDecId());
	        System.out.println("Path="+httpservletrequest.getContextPath()+":"+httpservletrequest.getRealPath("/"));
	        //String s = (String)dynaactionform.get("methodToCall");
		        try
		        {
		        File f=new File(httpservletrequest.getRealPath("/")+"/clientfolder/"+facilityvo.getDecId()+"/generator/"+generatorvo.getId()+"-"+generatorvo.getFacilityDesignatedId().trim());
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
					String contextpath=httpservletrequest.getRealPath("/")+"/clientfolder/"+facilityvo.getDecId()+"/generator/"+generatorvo.getId()+"-"+generatorvo.getFacilityDesignatedId().trim();
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
		httpservletrequest.setAttribute("FILE_PATH",facilityvo.getDecId()+"/generator/"+generatorvo.getId()+"-"+generatorvo.getFacilityDesignatedId().trim());

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
    {
        String s1 = (String)dynaactionform.get(s);
        if(UtilityObject.isNotEmpty(s1))
            try
            {
                return Float.parseFloat(s1);
            }
            catch(NumberFormatException numberformatexception)
            {
                log.error(numberformatexception);
            }
        return 0f;
    }

    static Class _mthclass$(String s)
        throws Exception
    {
        return Class.forName(s);
    }

    public static String checkNullAndFill(String s, String s1)
    {
        if(isNotEmpty(s) && !"null".equalsIgnoreCase(s.trim()))
            return s;
        else
            return s1;
    }

    public static double round(double d, int i)
    {
        double d1;
        for(d1 = 1.0D; i-- > 0; d1 *= 10D);
        return (double)Math.round(d * d1) / d1;
    }

    public static boolean isNotEmpty(String s)
    {
        return s != null && s.trim().length() > 0;
    }

    private static Log log = LogFactory.getLog(com.eespc.tracking.actions.GeneratorAction.class);

}