package app;

import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
//import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/admin")
public class AdminService {

	@Context
	private HttpServletRequest req;
	@Context
	private HttpServletResponse res;
	@Context
	private ServletContext ctx;
	
	String loginPage = "../../index.html";
//	String loginPage = "http://localhost:8080/CouponSystemWEB/rest/admin/adminLogin?user=admin&pass=1234";

	public AdminService(){}

	@GET
	@Consumes({MediaType.TEXT_PLAIN})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("adminLogin")
	public String login(@QueryParam("user") String user,
						@QueryParam("pass") String pass) {

		try {
			AdminFacade adminFacade = (AdminFacade) AppSystem.getInstance().login(user, pass);
			if (adminFacade != null){
				HttpSession session = req.getSession(false);
				if(session != null){
					session.invalidate();
				}
				session = req.getSession(true);
				session.setAttribute("facade", adminFacade);
				return "login as " + user + " Success!\nWelcome " + user + ".";
			}
		} catch (SysException e) {
			e.printStackTrace();
			return "Admin login Falied! " + e.getMessage();
		}
		return "Admin login Falied!";
	}

	@GET
	@Consumes({MediaType.TEXT_PLAIN})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("createEmployee")
	public String createEmployee(@QueryParam("id") long id,
								@QueryParam("name") String name,
								@QueryParam("address") String address,
								@QueryParam("email") String email) {
		
		try {
			
			HttpSession session = req.getSession(false);
			if(session == null){
				try {
					res.sendRedirect(loginPage);
				} catch (IOException e) {
					e.printStackTrace();
					return "Create Employee Failed: Invalis Session";
				}
				return "Please re-login!";
				
			} else {

				AdminFacade adminFacade = (AdminFacade) session.getAttribute("facade");
		
				Employee emp = new Employee();
				emp.setId(id);
				emp.setName(name);
				emp.setAddress(address);
				emp.setEmail(email);
				adminFacade.createEmployee(emp);
				return "Employee " + name + " Succesfully Created. \n" + emp.toString();
			}
		} catch (SysException e) {
			e.printStackTrace();
			return "Create Emp Failed: Check The Problem. " + e.getMessage();
		}
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("getEmployeeList")
	public String getEmployeeList() {

		try {
			HttpSession session = req.getSession(false);
			if(session == null){
				try {
					res.sendRedirect(loginPage);
				} catch (IOException e) {
					e.printStackTrace();
					return "Retrieve employees Failed: Invalis Session";
				}
				return "Please re-login!";
				
			} else {

				AdminFacade adminFacade = (AdminFacade) session.getAttribute("facade");
				Set<Employee> emps = adminFacade.getEmployeeList();
				return "Employee List:)\n" + emps.toString();
			}
		} catch (SysException e) {
			e.printStackTrace();
			return "Retrieve employees Failed. " + e.getMessage();
		}
	}	 	
}
