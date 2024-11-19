package com.inventorysystem.dao;

import com.inventorysystem.model.Order;
import com.inventorysystem.util.BaseDao;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OrderDao extends BaseDao {

    public List<Order> getAllOrders() {
        try (Session session = getSession()) {
            return session.createQuery("from Order", Order.class).list();
        }
    }

    public Order getOrder(int id) {
        try (Session session = getSession()) {
            return session.get(Order.class, id);
        }
    }

    public void saveOrder(Order order) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
        }
    }

    public void updateOrder(Order order) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.update(order);
            transaction.commit();
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
        }
    }

    public void deleteOrder(int id) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            Order order = session.get(Order.class, id);
            if (order != null) {
                session.delete(order);
            }
            transaction.commit();
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
        }
    }
}
