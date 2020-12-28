package com.packtpub.chapter4.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.packtpub.chapter4.entity.Property;

@Singleton
@LocalBean
@Remote(SingletonBeanRemote.class)
public class SingletonBean implements SingletonBeanRemote {

    private List<Property> cache = new ArrayList<>();

    @PersistenceContext(unitName = "persistenceUnit")
    private EntityManager em;

    @PostConstruct
    public void initCache() {
        this.cache = queryCache();
        if (cache == null) {
            cache = new ArrayList<Property>();
        }
    }

    public void deleteAll() {
        Query query = em.createQuery("delete FROM Property");
        query.executeUpdate();
        this.cache.clear();
    }

    public void save(String key, String value) {
        Property property = new Property(key, value);
        em.persist(property);
        this.cache.add(property);
    }

    private List<Property> queryCache() {
        TypedQuery<Property> query = em.createQuery("FROM Property",
                Property.class);

        List<Property> list = query.getResultList();
        return list;
    }

    public List<Property> getProperties() {
        return cache;
    }

}
