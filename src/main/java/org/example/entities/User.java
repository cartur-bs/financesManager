package org.example.entities;

import javax.persistence.*;
import java.sql.*;
import java.util.List;


@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String userName;
    private String password;


    public User() {
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public static boolean gettingUser(String userName, String password) throws SQLException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("expManager");
        EntityManager em = emf.createEntityManager();
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

        }catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return teste;
    }

    public static void createUser(User user) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("expManager");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
