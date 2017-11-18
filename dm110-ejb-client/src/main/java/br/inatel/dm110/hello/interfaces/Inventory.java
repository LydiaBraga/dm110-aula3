package br.inatel.dm110.hello.interfaces;

import java.util.List;

import br.inatel.dm110.hello.api.ProductTO;

public interface Inventory {

	List<ProductTO> listProducts();

	void createNewProduct(ProductTO productTO);

}
