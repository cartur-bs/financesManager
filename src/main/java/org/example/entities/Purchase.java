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
}
