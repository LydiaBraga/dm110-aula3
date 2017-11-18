package br.inatel.dm110.hello.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import br.inatel.dm110.hello.api.ProductTO;
import br.inatel.dm110.hello.dao.ProductDAO;
import br.inatel.dm110.hello.entities.Product;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/productQueue"),
		@ActivationConfigProperty(propertyName = "maxSession", propertyValue = "5")
})
public class ProductMDB implements MessageListener {

	@EJB
	private ProductDAO productDAO;
	
	@Override
	public void onMessage(Message message) {
		try {
			if (message instanceof ObjectMessage) {
				ObjectMessage productMessage = (ObjectMessage) message;
				Object objectMessage = productMessage.getObject();
				
				if (objectMessage instanceof ProductTO) {
					productDAO.insert(convertProduct((ProductTO) objectMessage));
				} else {
					System.out.println("######## A mensagem não contem um produto!");
				}
			} else {
				System.out.println("######## A mensagem não é um objeto!");
			}
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private Product convertProduct(ProductTO productTO) {
		Product product = new Product();
		product.setName(productTO.getName());
		product.setQuantity(productTO.getQuantity());
		
		return product;
	}

}
