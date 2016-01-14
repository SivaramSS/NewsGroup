package org.sivaram.newsgroup.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.sivaram.newsgroup.models.User;

public class ProfileAction extends Action {
    
	public ActionForward getProfile(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) 
	        throws Exception {
	 
			List<User> listUsers = new ArrayList<User>();
             System.out.println("Parameters "+request.getParameter("id"));
			request.setAttribute("listUsers", listUsers);
			
			return mapping.findForward("success");
		}
	
	public String getProfile()
	{   
		System.out.println("In Profile Action");
		
		return "success";
	}
}
