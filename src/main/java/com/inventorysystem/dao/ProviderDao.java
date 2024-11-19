package com.inventorysystem.dao;


import com.inventorysystem.model.Provider;
import com.inventorysystem.util.BaseDao;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProviderDao extends BaseDao {

    public List<Provider> getAllProviders() {
        try (Session session = getSession()) {
            return session.createQuery("from Provider", Provider.class).list();
        }
    }

    public Provider getProvider(int id) {
        try (Session session = getSession()) {
            return session.get(Provider.class, id);
        }
    }

    public void saveProvider(Provider provider) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.save(provider);
            transaction.commit();
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
        }
    }

    public void updateProvider(Provider provider) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.update(provider);
            transaction.commit();
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
        }
    }

    public void deleteProvider(int id) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            Provider provider = session.get(Provider.class, id);
            if (provider != null) {
                session.delete(provider);
            }
            transaction.commit();
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
        }
    }
}
