package org.example.entities;

import org.example.DB.DBProperties;

import javax.persistence.*;

import java.sql.*;


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
        Connection con = DBProperties.getConnection();
        String sql = "SELECT * FROM User WHERE userName = '" + userName + "' AND password = '" + password + "'";
        Statement st = con.createStatement();
        ResultSet rst;

        boolean teste = false;
        try {
            rst = st.executeQuery(sql);
            while( rst.next()){
               String userNameReturned = rst.getString("userName");
               String passwordReturned = rst.getString("password");
               int idR = rst.getInt("id");
                System.out.println(userNameReturned + passwordReturned +idR);
                teste = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(con != null){
                con.close();
            }
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
