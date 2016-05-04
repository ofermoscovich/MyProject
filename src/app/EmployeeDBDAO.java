package app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class EmployeeDBDAO {
	
	private ConnectionPool pool;

	public EmployeeDBDAO() throws SysException {
		pool = ConnectionPool.getInstance();
	}

	public Set<Employee> getEmployeeList() throws SysException {

			String sql = "SELECT id,name,address,email FROM app.employee ";
			Set<Employee> empList = new HashSet<Employee>();
			Employee emp;
		try {
			Connection conn = pool.getConnection();
			
			Statement pstm = conn.createStatement();
			ResultSet rs = pstm.executeQuery(sql);

			while (rs.next()) {
				emp = new Employee();
				emp.setId(rs.getLong("id"));
				emp.setName(rs.getString("name"));	
				emp.setAddress(rs.getString("address"));	
				emp.setEmail(rs.getString("email"));
				empList.add(emp);
			}
			return empList;
		} catch (Exception e){
			e.printStackTrace();
			throw new SysException("Select failed!");
		}
	}


	public void createEmployee(Employee emp) throws SysException {

			String sql = "INSERT INTO app.Employee(id, name, address, email) VALUES(?,?,?,?) ";
		try {
			Connection conn = pool.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setLong(1, emp.getId());
			pstm.setString(2, emp.getName());
			pstm.setString(3, emp.getAddress());
			pstm.setString(4, emp.getEmail());
			pstm.executeUpdate();
	
		} catch (Exception e){
			e.printStackTrace();
			throw new SysException("Update failed!");
		}
	}
}

