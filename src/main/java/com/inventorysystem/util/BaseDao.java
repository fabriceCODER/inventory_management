package com.inventorysystem.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

//BaseDao: Handling repetetive tasks like opening and closing sessions and transactions
public class BaseDao {
    protected SessionFactory sessionFactory;

    public BaseDao() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    protected Session getSession() {
        return sessionFactory.openSession();
    }

    protected void closeSession(Session session) {
        if (session != null) {
            session.close();
        }
    }

    protected void rollbackTransaction(Transaction transaction) {
        if (transaction != null) {
            transaction.rollback();
        }
    }
}
