package Agent;

import java.util.HashMap;
import java.util.Map;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class CentralMemory extends Agent {

	private Map<String, String> m_centralMemory = new HashMap<String, String>();
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("serial")
	@Override
	protected void setup() {
		// TODO Auto-generated method stub
		super.setup();
		
		m_centralMemory.put("nom", "Wacheux");
		m_centralMemory.put("prenom", "prenom");
		
		addBehaviour(new CyclicBehaviour() {
			
			@Override
			public void action() {
				ACLMessage msg = receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));				
				if(msg == null){
					block();
					return;
				}
				
				String addr = msg.getContent();
				ACLMessage rep = msg.createReply();
				rep.setPerformative(ACLMessage.INFORM);
				rep.setContent(getMemoryValue(addr));
				send(rep);
				
			}
		});
		
		addBehaviour(new CyclicBehaviour() {
			
			@Override
			public void action() {
				ACLMessage msg = receive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));
				if(msg == null){
					block();
					return;					
				}
				
				String req = msg.getContent();
				String[] processedRequest = req.split(";");
				String addr = processedRequest[0];
				String value = processedRequest[1];
				
				m_centralMemory.put(addr, value);				
			}
		});
	}
	
	private String getMemoryValue(String addr)
	{
		if(m_centralMemory.containsKey(addr))
			return m_centralMemory.get(addr);
		else
			return "";
	}

}
