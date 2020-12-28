package com.packtpub.chapter4.ejb;

import java.util.List;
import com.packtpub.chapter4.entity.Property;

public interface SingletonBeanRemote {
	
	public void deleteAll();

	public void save(String key, String value);

	public List<Property> getProperties();

}
