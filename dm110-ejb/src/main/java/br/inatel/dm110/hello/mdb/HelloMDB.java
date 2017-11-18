package br.inatel.dm110.hello.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import br.inatel.dm110.hello.interfaces.InventoryLocal;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/dm110queue"),
		@ActivationConfigProperty(propertyName = "maxSession", propertyValue = "5")
})
public class HelloMDB implements MessageListener {

	@EJB
	private InventoryLocal inventory;
	
	@Override
	public void onMessage(Message message) {
		try {
			if (message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				String text = textMessage.getText();
				System.out.println("####### Iniciando processamento...");
				Thread.sleep(15000);
				System.out.println("####### Processando mensagem: " + text);
				inventory.createNewProduct(text);
				Thread.sleep(5000);
				System.out.println("####### Finalizando processamento...");
			}
		} catch (JMSException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
