package com.inventorysystem.dao;

import com.inventorysystem.model.Customer;
import com.inventorysystem.util.BaseDao;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CustomerDao extends BaseDao {

    public List<Customer> getAllCustomers() {
        try (Session session = getSession()) {
            return session.createQuery("from Customer", Customer.class).list();
        }
    }

    public Customer getCustomer(int id) {
        try (Session session = getSession()) {
            return session.get(Customer.class, id);
        }
    }

    public void saveCustomer(Customer customer) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
        }
    }

    public void updateCustomer(Customer customer) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.update(customer);
            transaction.commit();
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
        }
    }

    public void deleteCustomer(int id) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            Customer customer = session.get(Customer.class, id);
            if (customer != null) {
                session.delete(customer);
            }
            transaction.commit();
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
        }
    }
}
