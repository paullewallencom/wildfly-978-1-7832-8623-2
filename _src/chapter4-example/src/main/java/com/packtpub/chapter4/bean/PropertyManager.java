package com.packtpub.chapter4.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.jboss.logging.Logger;

import com.packtpub.chapter4.ejb.SingletonBean;
import com.packtpub.chapter4.entity.Property;

@Named("manager")
@RequestScoped
public class PropertyManager {

    private Logger logger = Logger.getLogger(getClass());
    
    @EJB
    private SingletonBean ejb;

    private String key;
    private String value;

    public void save(ActionEvent e) {

        try {
            ejb.save(key, value);
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Property Saved!", null));
        } catch (Exception ex) {
            logger.error("Error saving property", ex);
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error Saving!", ex.getMessage()));
        }
    }

    public void clear(ActionEvent e) {
        logger.info("Called clear");
        ejb.deleteAll();
    }

    public List<Property> getCacheList() {
        return ejb.getProperties();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
