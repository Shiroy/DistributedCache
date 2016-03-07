package bootstrap;

import jade.core.AgentContainer;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;

public class MainContainer {

	private static String MAIN_PROPERTIES_FILES = "conf/main.conf";
	
	public static void main(String[] args)
	{
		Runtime rt = Runtime.instance();
		Profile p = null;
		try
		{
			p = new ProfileImpl(MAIN_PROPERTIES_FILES);
			AgentContainer mc = (AgentContainer) rt.createMainContainer(p);
		}
		catch(Exception e)
		{
			
		}
	}
	
}
