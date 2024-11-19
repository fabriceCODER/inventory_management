package com.inventorysystem.dao;

import com.inventorysystem.model.OrderDetails;
import com.inventorysystem.util.BaseDao;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OrderDetailDao extends BaseDao {

    public List<OrderDetails> getAllOrderDetails() {
        try (Session session = getSession()) {
            return session.createQuery("from OrderDetails", OrderDetails.class).list();
        }
    }

    public OrderDetails getOrderDetail(int id) {
        try (Session session = getSession()) {
            return session.get(OrderDetails.class, id);
        }
    }

    public void saveOrderDetail(OrderDetails orderDetail) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.save(orderDetail);
            transaction.commit();
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
        }
    }

    public void updateOrderDetail(OrderDetails orderDetail) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.update(orderDetail);
            transaction.commit();
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
        }
    }

    public void deleteOrderDetail(int id) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            OrderDetails orderDetail = session.get(OrderDetails.class, id);
            if (orderDetail != null) {
                session.delete(orderDetail);
            }
            transaction.commit();
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
        }
    }
}
