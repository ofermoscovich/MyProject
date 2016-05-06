# MyProject
This is a very basic Java 1.8 Template Web Server Starting Project, using: basic Facade pattern, with one Web service, basic connection to DB, login method, Insert and retrieve (SELECT SQL) methods - all in 9 java files included in one package - Easy installation.

	AdminFacade.java 
	AdminService.java 
	AppSystem.java 	
	ClientFacade.java 
	ConnectionPool.java 
	Employee.java 
	EmployeeDBDAO.javao
	SysException.java 
	Test.java

Was tested on JBoss and Tomcat 7 with Derby DB 10.10.2.0 jar.

See links command to active *methods (Login, SELECT, INSERT) using the brawser, basic create table command.

Following the important strings/links:
-------------------------------------
 connect 'jdbc:derby://localhost:1527/oferdb;create=true';
 
 CREATE TABLE app.employee(id int,name varchar(20),address varchar(20), email varchar(20), primary key(id));
 
 http://localhost:8080/MyProject/rest/admin/adminLogin?user=admin&pass=1234
 
 http://localhost:8080/MyProject/rest/admin/createEmployee?id=1&name=ofer&address=12namirtl&email=aaa@dfdf.com
 
 http://localhost:8080/MyProject/rest/admin/getEmployeeList
 
------------------------------------- 
 * In order to active the SELECT, INSERT links you need first to login using the Login link.
 * web.xml need to include the "app" package as follow: 
 <param-value app /param-value>

Take it from hear any way you want...

Good luck.
