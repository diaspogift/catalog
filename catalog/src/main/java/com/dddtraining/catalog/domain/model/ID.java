package com.dddtraining.catalog.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ID {
	@Column(insertable = false, updatable = false)
	private String id;
	
	
	
	public ID() {
	}

	public ID(String uuid){
		this.setId(uuid);
	}
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		ID other = (ID) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id [id=" + id + "]";
	}
	
	
	
}
