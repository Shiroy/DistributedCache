package bootstrap;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;

public class AgentContainer {

	public static void main(String[] args) {
		Runtime rt = Runtime.instance();
		Profile p = null;
		try
		{
			p = new ProfileImpl("conf/agent.conf");
			ContainerController cc = rt.createAgentContainer(p);
			
			AgentController memAgt = cc.createNewAgent("CentralMemory", "Agent.CentralMemory", null);
			memAgt.start();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
