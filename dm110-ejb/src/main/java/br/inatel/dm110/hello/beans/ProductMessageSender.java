package br.inatel.dm110.hello.beans;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import br.inatel.dm110.hello.api.ProductTO;

@Stateless
public class ProductMessageSender {

	@Resource(lookup = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;
	
	@Resource(lookup = "java:/jms/queue/productQueue")
	private Queue queue;
	
	public void sendMessage(ProductTO productTO) {
		try (
			Connection connection = connectionFactory.createConnection();
			Session session = connection.createSession();
			MessageProducer producer = session.createProducer(queue);
		) {
			ObjectMessage productMessage = session.createObjectMessage(productTO);		
			producer.send(productMessage);
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}
	
}
