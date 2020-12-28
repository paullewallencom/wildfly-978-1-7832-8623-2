package com.packtpub.chapter4.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Property implements Serializable {

	@Id
	@Column(name = "id")
	private String key;

	@Column(name = "value")
	private String value;

	protected Property() {
		super();
	}
	
	public Property(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "Property [key=" + key + ", value=" + value + "]";
	}

}
