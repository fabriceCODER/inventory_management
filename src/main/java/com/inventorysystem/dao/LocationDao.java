package com.inventorysystem.dao;

import com.inventorysystem.model.Location;
import com.inventorysystem.util.BaseDao;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LocationDao extends BaseDao {

    public List<Location> getAllLocations() {
        try (Session session = getSession()) {
            return session.createQuery("from Location", Location.class).list();
        }
    }

    public Location getLocation(int id) {
        try (Session session = getSession()) {
            return session.get(Location.class, id);
        }
    }

    public void saveLocation(Location location) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.save(location);
            transaction.commit();
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
        }
    }

    public void updateLocation(Location location) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.update(location);
            transaction.commit();
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
        }
    }

    public void deleteLocation(int id) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            Location location = session.get(Location.class, id);
            if (location != null) {
                session.delete(location);
            }
            transaction.commit();
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
        }
    }
}

