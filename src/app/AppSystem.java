package app;

// SINGLETON

public class AppSystem {
	

	private static AppSystem instance;
	public AppSystem() {}
	
	public static AppSystem getInstance() throws SysException {
		if(instance==null) instance = new AppSystem();
		return instance;
	}
	

	public ClientFacade login(String user,String pass) throws SysException {
	
		ClientFacade clientFacade ;
		
		clientFacade = new AdminFacade();
		
		clientFacade = clientFacade.login(user,pass);
		if (clientFacade != null) {
			return clientFacade;
		} else {
			throw new SysException("STOP! Login Falied! Invalid User or Password!");
		}
	}
}
