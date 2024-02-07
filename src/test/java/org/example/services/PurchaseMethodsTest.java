package org.example.services;

import org.example.entities.Purchase;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class PurchaseMethodsTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    @Test
    public void insertNewPurchase(){
        emf = Persistence.createEntityManagerFactory("expManager");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        LocalDate date = LocalDate.of(2024, 2, 1);
        Purchase purchase = new Purchase("usuarioTeste", date, "health", 9.99);
        em.persist(purchase);

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}