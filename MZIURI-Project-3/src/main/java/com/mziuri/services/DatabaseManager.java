package com.mziuri.services;

import com.mziuri.Product.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class DatabaseManager {
    private static DatabaseManager instance;
    private final EntityManagerFactory entityManagerFactory;

    private DatabaseManager() {
        entityManagerFactory = Persistence.createEntityManagerFactory("YourPersistenceUnitName");
    }

    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public void addProducts(Product[] products) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            for (Product product : products) {
                entityManager.persist(product);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public Product getProductByName(String productName) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
            Root<Product> root = criteriaQuery.from(Product.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("prod_name"), productName));
            return entityManager.createQuery(criteriaQuery).getSingleResult();
        } finally {
            entityManager.close();
        }
    }
}
