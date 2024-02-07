package org.example.services;

import org.example.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class UserMethods {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("expManager");
    private static EntityManager em = emf.createEntityManager();
    public static void createUser(User user) {
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public static boolean gettingUser(String userName, String password) {
        String hql = "FROM User WHERE userName = :userName AND password = :password";
        boolean teste = false;
        try {
            em.getTransaction().begin();
            Query hqlQuery = em.createQuery(hql);
            hqlQuery.setParameter("userName", userName);
            hqlQuery.setParameter("password", password);
            List<User> resultList = hqlQuery.getResultList();
            em.getTransaction().commit();

            for (User e : resultList) {
                System.out.println(e);
                teste = true;
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return teste;
    }
}
