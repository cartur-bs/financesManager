package org.example.services;

import org.example.entities.Purchase;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

public class PurchaseMethods {
   private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("expManager");
    private static EntityManager em = emf.createEntityManager();
    public static void insertPurchase(Purchase newPurchase) {
        em.getTransaction().begin();
        em.persist(newPurchase);
        em.getTransaction().commit();
        em.close();
    }

    public static void getPurchaseByClass(String purchaseClass, String userName) {
        String hql = "FROM Purchase WHERE purchaseClass = :purchaseClass and userName = :userName";
        try {
            em.getTransaction().begin();
            Query hqlQuery = em.createQuery(hql);
            hqlQuery.setParameter("purchaseClass", purchaseClass);
            hqlQuery.setParameter("userName", userName);
            List<Purchase> resultList = hqlQuery.getResultList();
            em.getTransaction().commit();
            if(resultList.isEmpty()){
                System.out.println("No purchases with this class were found.");
            }
            for (Purchase entity : resultList) {
                System.out.println(entity);
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void getPurchaseByDate(LocalDate purchaseDate, String userName) {
        String hql = "FROM Purchase WHERE purchaseDate = :purchaseDate and userName = :userName";
        try {
            em.getTransaction().begin();
            Query hqlQuery = em.createQuery(hql);
            hqlQuery.setParameter("purchaseDate", purchaseDate);
            hqlQuery.setParameter("userName", userName);
            List<Purchase> resultList = hqlQuery.getResultList();
            em.getTransaction().commit();
            if(resultList.isEmpty()){
                System.out.println("No purchases with this date were found.");
            }
            for (Purchase entity : resultList) {
                System.out.println(entity);
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }

    }
}
