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

    public Purchase(String userName, LocalDate purchaseDate, String purchaseClass, double purchasePrice) {
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


    public static void getPurchaseByDate(LocalDate purchaseDate, String userName) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("expManager");
        EntityManager em = emf.createEntityManager();
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
