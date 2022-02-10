package org.example.snowboard.rent.database.impl.hibernate;

import org.example.snowboard.rent.database.ISnowboardDAO;
import org.example.snowboard.rent.model.Snowboard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class SnowboardDAOImpl implements ISnowboardDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addSnowboard(Snowboard snowboard) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(snowboard);
            tx.commit();
        } catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteSnowboard(Snowboard snowboard) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(snowboard);
            tx.commit();
        } catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }


    @Override
    public List<Snowboard> getSnowboards() {
        Session session = this.sessionFactory.openSession();
        Query<Snowboard> query = session.createQuery("FROM org.example.snowboard.rent.model.Snowboard");
        List<Snowboard> tempRes = query.getResultList();
        session.close();

        List<Snowboard> result = tempRes.stream().filter(object -> !object.isRent()).collect(Collectors.toList());

        return result;
    }

    @Override
    public Optional<Snowboard> getSnowboardById(int snowboardId) {
        Session session = this.sessionFactory.openSession();
        Query<Snowboard> query = session.createQuery("FROM org.example.snowboard.rent.model.Snowboard WHERE id = :id");
        query.setParameter("id", snowboardId);
        try {
            Snowboard snowboard = query.getSingleResult();
            session.close();
            return Optional.of(snowboard);
        } catch (NoResultException e) {
            session.close();
            return Optional.empty();
        }
    }

    @Override
    public void updateSnowboard(Snowboard snowboard) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(snowboard);
            tx.commit();
        } catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }
}
