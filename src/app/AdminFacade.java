package app;

import java.util.Set;

public class AdminFacade implements ClientFacade {

	private EmployeeDBDAO empDBDAO;
	private static final String ADMINUSER = "admin";
	private static final String ADMINPASS = "1234";
	
	
	public AdminFacade() throws SysException {
		empDBDAO = new EmployeeDBDAO();
	}
	
	public ClientFacade login(String user,String pass) throws SysException {
		if (user.equals(ADMINUSER) && pass.equals(ADMINPASS)) {
			return this;
		} else {
			return null;
		}
	}
	
	public void createEmployee(Employee employee) throws SysException {
		empDBDAO.createEmployee(employee);
	}
	
	public Set<Employee> getEmployeeList() throws SysException {
		return empDBDAO.getEmployeeList();
	}

}
