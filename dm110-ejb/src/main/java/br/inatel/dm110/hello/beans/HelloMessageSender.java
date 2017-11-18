package br.inatel.dm110.hello.beans;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

@Stateless
public class HelloMessageSender {
	
	@Resource(lookup = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;
	
	@Resource(lookup = "java:/jms/queue/dm110queue")
	private Queue queue;
	
	public void sendTextMessage(String text) {
		try (
			Connection connection = connectionFactory.createConnection();
			Session session = connection.createSession();
			MessageProducer producer = session.createProducer(queue);
		) {
			TextMessage textMessage = session.createTextMessage(text);		
			producer.send(textMessage);
			System.out.println("####### Mensagem enviada...");
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	

}
