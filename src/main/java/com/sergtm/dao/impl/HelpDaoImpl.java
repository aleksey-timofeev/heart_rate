package com.sergtm.dao.impl;

import com.sergtm.dao.IHelpDao;
import com.sergtm.entities.Help;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Repository
@Transactional
public class HelpDaoImpl implements IHelpDao{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Collection<Help> findAll() {
        String sql = "FROM Help";
        Query query = sessionFactory.getCurrentSession().createQuery(sql);
        return query.getResultList();
    }

    @Override
    public List<Help> getHelpByName(String name) {
        String sql = "FROM Help h WHERE upper(h.name) LIKE :name";
        Query query = sessionFactory.getCurrentSession().createQuery(sql);
        query.setParameter("name", name.toUpperCase());
        return query.getResultList();
    }

    @Override
    public Collection<Help> getByTopic(String name, String topicName) {
        String sql = "FROM Help h WHERE upper(h.name) LIKE :name AND h.topic.name = :topicName";
        Query query = sessionFactory.getCurrentSession().createQuery(sql);
        query.setParameter("name", name.toUpperCase());
        query.setParameter("topicName", topicName);
        return query.getResultList();
    }
}
