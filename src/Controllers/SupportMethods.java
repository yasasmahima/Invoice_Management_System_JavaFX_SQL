package Controllers;

import java.util.Scanner;

public class SupportMethods {


    static Scanner input=new Scanner(System.in);

    //Printing Console Menu
    public static int getOption(){
        System.out.println("Select Your Option \n" +
                "01) Product Controller \n" +
                "02) Customer Controller \n" +
                "03) Invoice Controller \n"+
                "04) Exit From Main Menu"
        );

        System.out.print("Input Your Option\t:\t");
        exceptionHandling();               //calling exception handling method
        int option=input.nextInt();        //Get Option again when user input an invalid option
        option = invalidOption(option,1,4);
        System.out.println("---------------------------");

        return option;
    }

    //Exception handling (TypeMisMatch Exception)
    public static void exceptionHandling() {
        String message="You have entered an Invalid option\n" +
                "Input Your Option\t:\t";
        while (!input.hasNextInt()) {
            System.out.print(message);
            input.next();
        }
    }


    //Check Valid Option ( Check Range )
    public static int invalidOption(int option,int first,int last) {
        while (!(first <= option && option <= last)) {
            System.out.print("You have entered an Invalid option\n" +
                    "Input Your Option\t:\t");
            exceptionHandling();
            option = input.nextInt();
        }
        return option;
    }

    public static int productControllerOptions(){
        System.out.println("Select Your Option \n" +
                "01) Add New Product \n" +
                "02) Delete Product \n" +
                "03) Search Product \n"+
                "04) Exit Product Controller"
        );

        System.out.print("Input Your Option\t:\t");
        exceptionHandling();               //calling exception handling method
        int option=input.nextInt();        //Get Option again when user input an invalid option
        option = invalidOption(option,1,4);
        System.out.println("---------------------------");

        return option;
    }

    public static int customerControllerOptions(){
        System.out.println("Select Your Option \n" +
                "01) Add New Customer \n" +
                "02) Search Customer \n" +
                "03) Exit Customer Controller"
        );

        System.out.print("Input Your Option\t:\t");
        exceptionHandling();               //calling exception handling method
        int option=input.nextInt();        //Get Option again when user input an invalid option
        option = invalidOption(option,1,3);
        System.out.println("---------------------------");

        return option;

    }

    public static int generateInvoiceOptions(){
        System.out.println("Select Your Option \n" +
                "01) Create New Invoice \n" +
                "02) View Generated Invoice \n" +
                "03) Exit form Invoice Controller"
        );

        System.out.print("Input Your Option\t:\t");
        exceptionHandling();
        int option=input.nextInt();
        option=invalidOption(option,1,3);
        System.out.println("--------------------------");

        return option;
    }
}
