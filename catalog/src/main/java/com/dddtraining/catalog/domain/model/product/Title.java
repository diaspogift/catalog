package com.dddtraining.catalog.domain.model.product;

import javax.persistence.Embeddable;

@Embeddable
public class Title {
	
	String title;
	
	public Title(){}
	
	public Title(String... args){
		this.setTitle(args);
	}
	private void setTitle(String[] args) {
		String tmpTitle = "";
		for(int i = 0 ; i<args.length; i++){
			if(i == 0){
				tmpTitle += args[i];
			}else{
				tmpTitle += " " + args[i];
			}
			
		}
		this.setTitle(tmpTitle);
	}
	private void setTitle(String tmpTitle) {
		this.title = tmpTitle;
	}
	public String getTitle() {
		return title;
	}
	@Override
	public String toString() {
		return "Title [title=" + title + "]";
	}
	
	
	
	
}
