package br.inatel.dm110.hello.api;

import java.io.Serializable;

public class ProductTO implements Serializable {

	private static final long serialVersionUID = 5130026976280738887L;
	
	private Integer id;
	private String name;
	private Integer quantity;
	
	public ProductTO() {
		
	}

	public ProductTO(Integer id, String name, Integer quantity) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
