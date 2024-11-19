package com.inventorysystem.dao;


import com.inventorysystem.model.Warehouse;
import com.inventorysystem.util.BaseDao;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class WarehouseDao extends BaseDao {

    public List<Warehouse> getAllWarehouses() {
        try (Session session = getSession()) {
            return session.createQuery("from Warehouse", Warehouse.class).list();
        }
    }

    public Warehouse getWarehouse(int id) {
        try (Session session = getSession()) {
            return session.get(Warehouse.class, id);
        }
    }

    public void saveWarehouse(Warehouse warehouse) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.save(warehouse);
            transaction.commit();
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
        }
    }

    public void updateWarehouse(Warehouse warehouse) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.update(warehouse);
            transaction.commit();
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
        }
    }

    public void deleteWarehouse(int id) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            Warehouse warehouse = session.get(Warehouse.class, id);
            if (warehouse != null) {
                session.delete(warehouse);
            }
            transaction.commit();
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
        }
    }
}
