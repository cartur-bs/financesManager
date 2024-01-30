package org.example;

import org.example.entities.Purchase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Would you like to add a new purchase or consult one?(add/consult)");

        String responseAction = sc.next().toLowerCase();
        if (responseAction.equals("add")) {
            System.out.println("Insert purchase date(dd/MM/yyyy)");
            String purchaseDateScanner = sc.next();
            LocalDate formatedDate = LocalDate.parse(purchaseDateScanner, dateFormat);
            if (formatedDate.isAfter(LocalDate.now())) {
                System.out.println("Please, insert a date past or equal today!");
                return;
            }
            System.out.println("Insert the class of your purchase:");
            String purchaseClassScanner = sc.next();
            System.out.println("Insert the value of your purchase");
            double purchaseValueScanner = sc.nextDouble();

            try {
                Purchase newPurchase = new Purchase(null, formatedDate, purchaseClassScanner, purchaseValueScanner);
                newPurchase.insertPurchase(newPurchase);
            } catch (InputMismatchException e) {
                e.printStackTrace();
            }
        }
        if (responseAction.equals("consult")) {
            System.out.println("Insert purchase class");
            String classPurchase = sc.next();
            Purchase.getPurchase(classPurchase);
        } else System.out.println("Please, only insert the informed options!");
        sc.close();
    }
}