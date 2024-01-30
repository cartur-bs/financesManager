package org.example.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate purchaseDate;
    private String purchaseClass;
    private double purchasePrice;

    public Purchase() {
    }

    public Purchase(Integer id, LocalDate purchaseDate, String purchaseClass, double purchasePrice) {
        this.id = id;
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
}
