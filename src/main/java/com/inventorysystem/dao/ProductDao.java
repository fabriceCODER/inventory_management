package com.inventorysystem.dao;

import com.inventorysystem.model.Product;
import com.inventorysystem.util.BaseDao;
import com.inventorysystem.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.management.Query;
import java.util.List;

public class ProductDao extends BaseDao {

    public List<Product> searchProducts(String searchTerm) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
         Query<Product> query = session.createQuery("FROM Product WHERE name LIKE :searchTerm", Product.class);
            query.setParameter("searchTerm", "%" + searchTerm + "%");
            return query.getResultList();
        } finally {
            session.close();
        }
    }

    public List<Product> getPaginatedProducts(int page, int size) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Product> query = session.createQuery("FROM Product", Product.class);
            query.setFirstResult((page - 1) * size); // Offset
            query.setMaxResults(size); // Limit
            return query.getResultList();
        } finally {
            session.close();
        }
    }




    public List<Product> getAllProducts() {
        try (Session session = getSession()) {
            return session.createQuery("from Product", Product.class).list();
        }
    }

    public Product getProduct(int id) {
        try (Session session = getSession()) {
            return session.get(Product.class, id);
        }
    }

    public void saveProduct(Product product) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
        }
    }

    public void updateProduct(Product product) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.update(product);
            transaction.commit();
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
        }
    }

    public void deleteProduct(int id) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            Product product = session.get(Product.class, id);
            if (product != null) {
                session.delete(product);
            }
            transaction.commit();
        } catch (Exception e) {
            rollbackTransaction(transaction);
            e.printStackTrace();
        }
    }
}
