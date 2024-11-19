package com.inventorysystem.dao;

import com.inventorysystem.model.Inventory;
import com.inventorysystem.util.BaseDao;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class InventoryDao extends BaseDao {

    public List<Inventory> getAllInventories() {
        try (Session session = getSession()) {
            return session.createQuery("from Inventory", Inventory.class).list();
        }
    }

    public Inventory getInventory(int id) {
        try (Session session = getSession()) {
            return session.get(Inventory.class, id);
        }
    }

    public void saveInventory(Inventory inventory) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.save(inventory);
            transaction.commit();
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
        }
    }

    public void updateInventory(Inventory inventory) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.update(inventory);
            transaction.commit();
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
        }
    }

    public void deleteInventory(int id) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            Inventory inventory = session.get(Inventory.class, id);
            if (inventory != null) {
                session.delete(inventory);
            }
            transaction.commit();
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
        }
    }
}

