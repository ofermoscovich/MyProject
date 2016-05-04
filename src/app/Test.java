package app;


import java.util.HashSet;
import java.util.Set;

public class Test {

	public static void main(String[] args) {
		
	
		Set<Employee> emps = new HashSet<Employee>();
		ClientFacade facade;

		try {
			AppSystem appSystem = AppSystem.getInstance();
			facade = appSystem.login("admin","1234");
			if (facade instanceof AdminFacade) {
				Employee emp = new Employee(15,"ramy","110 bavli","ttt@mmm.com");
				((AdminFacade) facade).createEmployee(emp);
				emps = ((AdminFacade) facade).getEmployeeList();
				System.out.println("Employee List:)\n" + emps.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}

// connect 'jdbc:derby://localhost:1527/oferdb;create=true';
// CREATE TABLE app.employee(id int,name varchar(20),address varchar(20), email varchar(20), primary key(id));
// http://localhost:8080/MyProject/rest/admin/adminLogin?user=admin&pass=1234
// http://localhost:8080/MyProject/rest/admin/createEmployee?id=1&name=ofer&address=12namirtl&email=aaa@dfdf.com
// http://localhost:8080/MyProject/rest/admin/getEmployeeList