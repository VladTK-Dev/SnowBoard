package org.example.snowboard.rent.database.impl.hibernate;

import org.example.snowboard.rent.database.IOrderDAO;
import org.example.snowboard.rent.model.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class IOrderDAOImpl implements IOrderDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addOrder(Order order) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(order);
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
    public List<Order> getOrdersByUserId(int userId) {
        Session session = this.sessionFactory.openSession();
        Query<Order> query = session.createQuery("FROM org.example.snowboard.rent.model.Order WHERE user_id = :userId");
        query.setParameter("userId", userId);
        List<Order> result = query.getResultList();
        session.close();
        return result;
    }
}
