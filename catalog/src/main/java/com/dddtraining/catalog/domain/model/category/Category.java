package com.dddtraining.catalog.domain.model.category;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity 
public class Category implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer     nativeId;

	private String      id;
	private String      name;
	private String      description;
	private String      image;
	
	
	public Category() {
		
	}

	public Category(String id, String name, String description, String image) {
		super();
		this.setId(id);
		this.setName(name);
		this.setDescription(description);
		this.setImage(image);
	}
	

	
	public Integer getNativeId() {
		return nativeId;
	}

	@SuppressWarnings("unused")
	private void setNativeId(Integer nativeId) {
		this.nativeId = nativeId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	private void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	private void setImage(String image) {
		this.image = image;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Category [nativeId=" + nativeId + ", id=" + id + ", name=" + name + ", description=" + description
				+ ", image=" + image + "]";
	}

	
   public void changeName(String newName){
	   this.setName(newName);
   }

	public void changeImage(String newImage) {
		this.setImage(newImage);
	}
	
	public void changeDescription(String newDescription) {
		this.setDescription(newDescription);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nativeId == null) ? 0 : nativeId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nativeId == null) {
			if (other.nativeId != null)
				return false;
		} else if (!nativeId.equals(other.nativeId))
			return false;
		return true;
	}
	
	
	
	
}
