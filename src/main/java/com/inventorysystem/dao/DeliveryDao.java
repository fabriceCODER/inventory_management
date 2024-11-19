package com.inventorysystem.dao;

import com.inventorysystem.model.Delivery;
import com.inventorysystem.util.BaseDao;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DeliveryDao extends BaseDao {

    public List<Delivery> getAllDeliveries() {
        try (Session session = getSession()) {
            return session.createQuery("from Delivery", Delivery.class).list();
        }
    }

    public Delivery getDelivery(int id) {
        try (Session session = getSession()) {
            return session.get(Delivery.class, id);
        }
    }

    public void saveDelivery(Delivery delivery) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.save(delivery);
            transaction.commit();
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
        }
    }

    public void updateDelivery(Delivery delivery) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.update(delivery);
            transaction.commit();
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
        }
    }

    public void deleteDelivery(int id) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            Delivery delivery = session.get(Delivery.class, id);
            if (delivery != null) {
                session.delete(delivery);
            }
            transaction.commit();
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
        }
    }
}
