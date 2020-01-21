package Controllers;

import Models.Customer;
import Models.Product;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.Scanner;

public class ConsoleApplication {

    static Scanner input=new Scanner(System.in);
    static SupportMethods getSupport=new SupportMethods();

    public static void main(String[] args) {
        System.out.println("============= Winhe IT =============");

        int mainOption=0;

        while(mainOption!=4){

            mainOption=getSupport.getOption();

            switch (mainOption){
                case 1:
                    System.out.println("------- Product Controller --------");
                    controlledProduct();
                    break;

                case 2:
                    System.out.println("-------- Customer Controller ---------");
                   controlledCustomer();
                    break;

                case 3:
                    System.out.println("--------- Invoice Controller -----------");
                    break;
            }
        }

        System.out.println("================ End Winhe IT ===================");
    }


    public static void controlledProduct(){

        ProductController productController=new ProductController();
        DataIO getData=new DataIO();

        int productControlOption=0;

        while (productControlOption!=4){

            productControlOption=getSupport.productControllerOptions();

            switch (productControlOption){
                case 1:
                    try {
                        System.out.println("----------- Add Products ------------");
                        Product product=getData.getProductDetails();
                        productController.addProducts(product);
                    }catch (SQLException e){
                        System.out.println("Cannot Send data to Database");
                    }
                    break;

                case 2:
                    try {
                        System.out.println("------------- Delete Products -----------");
                        System.out.print("Input Product Id to Delete : ");
                        int productId=input.nextInt();
                        productController.deleteProducts(productId);
                    }catch (SQLException e){
                        System.out.println("Cannot Update Database");
                    }
                    break;

                case 3:
                    try{
                        System.out.println("--------------- Search Products ---------------");
                        System.out.print("Input Product Id to Search : ");
                        String productID=input.next();
                        productController.searchProducts(productID).toString();
                    }catch (SQLException e){
                        System.out.println("Cannot get Data From Database");
                    }

                    break;
            }
        }

        System.out.println("--------- Exit From Product Controller Option -------------");

    }


    public static void controlledCustomer(){

        CustomerController customerController=new CustomerController();
        DataIO getData=new DataIO();

        int customerControlOption=0;
        while (customerControlOption!=3){

            customerControlOption=getSupport.customerControllerOptions();

            switch (customerControlOption){
                case 1:
                    try {
                        System.out.println("------------- Add New Customer --------------");
                        Customer customer=getData.getCustomerDetails();
                        customerController.addCustomer(customer);
                    }catch (SQLException e){
                        System.out.println("Problem With Sending Data to Database");
                    }

                    break;

                case 2:
                    try {
                        System.out.println("-------------- Search Customer ---------------");
                        System.out.print("Input Customer Id to Search : ");
                        String customerId=input.next();
                        customerController.searchCustomers(customerId).toString();
                    }catch (SQLException e){
                        System.out.println("Cannot get Data From Database");
                    }

                    break;
            }
        }

        System.out.println("------------- Exit From Customer Control Option --------------");

    }

    public static void controlledInvoice(){

        InvoiceController invoiceController=new InvoiceController();
        DataIO getData=new DataIO();

        int invoiceControlOption=0;
        while (invoiceControlOption!=3){

            invoiceControlOption=getSupport.generateInvoiceOptions();

            switch (invoiceControlOption){

                case 1:
                    System.out.println("-------------- New Invoice -------------");
                    break;

                case 2:
                    System.out.println("---------------- Display Generated Invoice Reports -----------------");
                    break;
            }

        }

    }
}
