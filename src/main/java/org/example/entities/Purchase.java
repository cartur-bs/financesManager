package org.example.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate purchaseDate;
    private String purchaseClass;
    private double purchasePrice;
    private String userName;

    public Purchase() {
    }

    public Purchase(Integer id, String userName, LocalDate purchaseDate, String purchaseClass, double purchasePrice) {
        this.id = id;
        this.userName = userName;
        this.purchaseDate = purchaseDate;
        this.purchaseClass = purchaseClass;
        this.purchasePrice = purchasePrice;
    }

    @Override
    public String toString() {
        return "Purchase{ " + "id= " + id + ", purchaseDate= " + purchaseDate + ", purchaseClass= " +
                purchaseClass + ", purchasePrice= " + purchasePrice + '}';
    }

    public void insertPurchase(Purchase newPurchase) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("expManager");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(newPurchase);
        em.getTransaction().commit();
        em.close();
    }

    public static void getPurchaseByClass(String purchaseClass) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("expManager");
        EntityManager em = emf.createEntityManager();
        String hql = "FROM Purchase WHERE purchaseClass = :purchaseClass";
        try {
            em.getTransaction().begin();
            Query hqlQuery = em.createQuery(hql);
            hqlQuery.setParameter("purchaseClass", purchaseClass);
            List<Purchase> resultList = hqlQuery.getResultList();
            em.getTransaction().commit();
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

    public static void getPurchaseByDate(LocalDate purchaseDate) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("expManager");
        EntityManager em = emf.createEntityManager();
        String hql = "FROM Purchase WHERE purchaseDate = :purchaseDate";
        try {
            em.getTransaction().begin();
            Query hqlQuery = em.createQuery(hql);
            hqlQuery.setParameter("purchaseDate", purchaseDate);
            List<Purchase> resultList = hqlQuery.getResultList();
            em.getTransaction().commit();
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
