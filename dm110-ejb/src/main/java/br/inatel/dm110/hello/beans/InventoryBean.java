package br.inatel.dm110.hello.beans;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.inatel.dm110.hello.api.ProductTO;
import br.inatel.dm110.hello.dao.ProductDAO;
import br.inatel.dm110.hello.interfaces.InventoryLocal;
import br.inatel.dm110.hello.interfaces.InventoryRemote;

@Stateless
@Remote(InventoryRemote.class)
@Local(InventoryLocal.class)
public class InventoryBean implements InventoryRemote, InventoryLocal {

	@EJB
	private ProductDAO productDAO;
	
	@EJB
	private ProductMessageSender productMessageSender;

	@Override
	public List<ProductTO> listProducts() {
		return productDAO.listAll().stream().map(p -> new ProductTO(p.getId(), p.getName(), p.getQuantity())).collect(Collectors.toList());
	}

	@Override
	public void createNewProduct(ProductTO productTO) {
		productMessageSender.sendMessage(productTO);
	}

}
