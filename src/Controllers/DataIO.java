package Controllers;

import Models.Customer;
import Models.Date;
import Models.Gender;
import Models.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class DataIO {


    ArrayList<Product> allProducts=new ArrayList<>();
   static Scanner input=new Scanner(System.in);

    public Product getProductDetails(){

        Product addProduct = null;

        System.out.print("Input Product Id : ");
        String productId=input.next();

        boolean availability=false;

        for (Product product:allProducts) {
            if(product.getProductId().equals(productId)){
                availability=true;
            }
        }

        if(!availability){

            String message="Invalid Input\nInput Again : ";

            System.out.print("Input Product Name : ");
            String productName=input.next();

            System.out.print("Input Product Description : ");
            String productDescription=input.next();

            System.out.print("Input Purchase Price : ");
            exceptionHandling(message);
            double purchasePrice=input.nextDouble();

            System.out.print("Input Selling Price : ");
            exceptionHandling(message);
            double sellingPrice=input.nextDouble();

            System.out.print("Input Quantity : ");
            exceptionHandling(message);
            int quantity=input.nextInt();

            addProduct=new Product(productId,productName,productDescription,purchasePrice,sellingPrice,quantity);
            return addProduct;

        }
        return addProduct;
    }


    public Customer getCustomerDetails(){

        String message="Invalid Input\nInput Again : ";

        System.out.print("Input Customer Name : ");
        String customerName=input.next();

        System.out.print("Input Customer Email : ");
        String customerEmail=input.next();

        System.out.print("Input Address : ");
        String customerAddress=input.nextLine();

        System.out.print("Input Contact Number : ");
        String customerContactNo=input.next();

        System.out.println("-- Input Customer Date Of Birth --");
        Date date=new Date();

        System.out.println("Input Day : ");
        exceptionHandling(message);
        int day=input.nextInt();
        try {
            date.setDay(day);
        }catch (IllegalArgumentException e){
            System.out.println("Invalid Day");
        }

        System.out.print("Input Month : ");
        exceptionHandling(message);
        int month=input.nextInt();
        try{
            date.setMonth(month);
        }catch (IllegalArgumentException e){
            System.out.println("Invalid Month");
        }

        System.out.print("Input Year : ");
        exceptionHandling(message);
        int year=input.nextInt();
        try {
            date.setYear(year);
        }catch (IllegalArgumentException e){
            System.out.println("Invalid Year");
        }

        Gender gender = null;

        try {
            System.out.print("Input Gender(Male/Female) : ");
            String customerGender = input.next();
            gender = Gender.valueOf(customerGender.toUpperCase());

        }catch (IllegalArgumentException e){
            System.out.println("Invalid Gender Type");
        }

        Customer customer=new Customer(customerName,customerEmail,customerAddress,customerContactNo,gender,date);
        return customer;

    }


    public static void exceptionHandling(String message) {
        while (!input.hasNextInt()) {
            System.out.print(message);
            input.next();
        }
    }
}
