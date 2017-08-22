package com.dddtraining.catalog.domain.model.product;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Brand {
	@Column(name="brand_name")
	private String name;
	@Column(name="brand_logo")
	private String logo;
	
	public Brand(){
		
	}
	public Brand(String name, String logo) {
		super();
		this.setName(name);
		this.setLogo(logo);
	}
	private void setLogo(String logo2) {
		this.logo = logo2;
	}
	private void setName(String name2) {
		this.name = name2;
	}
	public String getName() {
		return name;
	}
	public String getLogo() {
		return logo;
	}
	@Override
	public String toString() {
		return "Brand [name=" + name + ", logo=" + logo + "]";
	}
	
	
}
