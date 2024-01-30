package org.example;

import org.example.entities.Purchase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in).useLocale(Locale.US);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Would you like to add a new purchase or consult one?(add/consult)");
        String responseAction = sc.next().toLowerCase();

        if (responseAction.equals("add")) {
            try {
                System.out.println("Insert purchase date(dd/MM/yyyy)");
                String purchaseDateScanner = sc.next();
                LocalDate formattedDate = LocalDate.parse(purchaseDateScanner, dateFormat);
                //verifies that the user doesn't insert a date passed today, only past dates
                if (formattedDate.isAfter(LocalDate.now())) {
                    System.out.println("Please, insert a date past or equal today!");
                    return;
                }
                System.out.println("Insert the class of your purchase:(ex.: food, health, home)");
                String purchaseClassScanner = sc.next();
                System.out.println("Insert the value of your purchase(ex.: 10.99)");
                double purchaseValueScanner = sc.nextDouble();
                Purchase newPurchase = new Purchase(null, formattedDate, purchaseClassScanner, purchaseValueScanner);
                newPurchase.insertPurchase(newPurchase);
                System.out.println("Insertion completed successfully!");
                return; //exits the function and doesn't display the else
            } catch (InputMismatchException e) {
                System.out.println("Insert a valid value");
                e.printStackTrace();
            }}

            if (responseAction.equals("consult")) {
                System.out.println("Consult by class or date?(class/date)");
                String consultResponse = sc.next().toLowerCase();
                if (consultResponse.equals("class")) {
                    System.out.println("Insert purchase class");
                    String classPurchase = sc.next();
                    Purchase.getPurchaseByClass(classPurchase);
                    return;
                }
                if (consultResponse.equals("date")) {
                    System.out.println("Insert the date(dd/MM/yyyy)");
                    String consultDate = sc.next();
                    try{
                        LocalDate consultDateFormatted = LocalDate.parse(consultDate, dateFormat);
                        Purchase.getPurchaseByDate(consultDateFormatted);
                    }catch (DateTimeParseException e){
                        System.out.println("Insert a valid value");
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Please, only insert valid inputs!");
                }
            } else {
                System.out.println("Please, only insert the informed options!");
            }

        sc.close();
    }
}