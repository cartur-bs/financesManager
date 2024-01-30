package org.example;

import net.bytebuddy.asm.Advice;
import org.example.entities.Purchase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
            System.out.println("Insert purchase date(dd/MM/yyyy)");
            String purchaseDateScanner = sc.next();
            LocalDate formattedDate = LocalDate.parse(purchaseDateScanner, dateFormat);
            if (formattedDate.isAfter(LocalDate.now())) {
                System.out.println("Please, insert a date past or equal today!");
                return;
            }
            System.out.println("Insert the class of your purchase:");
            String purchaseClassScanner = sc.next();
            System.out.println("Insert the value of your purchase");
            double purchaseValueScanner = sc.nextDouble();

            try {
                Purchase newPurchase = new Purchase(null, formattedDate, purchaseClassScanner, purchaseValueScanner);
                newPurchase.insertPurchase(newPurchase);
                return;
            } catch (InputMismatchException e) {
                e.printStackTrace();
            }
        }
        if (responseAction.equals("consult")) {
            System.out.println("Consult by class or date?");
            String consultResponse = sc.next().toLowerCase();
            if(consultResponse.equals("class")){
                System.out.println("Insert purchase class");
                String classPurchase = sc.next();
                Purchase.getPurchaseByClass(classPurchase);
            }
            if (consultResponse.equals("date")){
                System.out.println("Insert the date(dd/MM/yyyy)");
                String consultDate = sc.next();
                LocalDate consultDateFormatted = LocalDate.parse(consultDate, dateFormat);
                Purchase.getPurchaseByDate(consultDateFormatted);
            }
        } else {
            System.out.println("Please, only insert the informed options!");
        }
        sc.close();
    }
}