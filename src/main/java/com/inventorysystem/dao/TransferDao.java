package com.inventorysystem.dao;

import com.inventorysystem.model.Transfer;
import com.inventorysystem.util.BaseDao;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TransferDao extends BaseDao {

    public List<Transfer> getAllTransfers() {
        try (Session session = getSession()) {
            return session.createQuery("from Transfer", Transfer.class).list();
        }
    }

    public Transfer getTransfer(int id) {
        try (Session session = getSession()) {
            return session.get(Transfer.class, id);
        }
    }

    public void saveTransfer(Transfer transfer) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.save(transfer);
            transaction.commit();
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
        }
    }

    public void updateTransfer(Transfer transfer) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.update(transfer);
            transaction.commit();
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
        }
    }

    public void deleteTransfer(int id) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            Transfer transfer = session.get(Transfer.class, id);
            if (transfer != null) {
                session.delete(transfer);
            }
            transaction.commit();
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
        }
    }
}

