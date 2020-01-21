package Models;

public class Date {

    private int day;
    private int month;
    private int year;

//Defualt Cinstructor
    public Date() {
    }

    //Creating Constructor
    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;

    }

    @Override   //overriding to String method
    public String toString() {
        return ""+ day +
                "-" + month +
                "-" + year ;

    }

    public int getDay() {
        return day;
    }       //get day

    public int getMonth() {
        return month;
    }  //get month

    public int getYear() {
        return year;
    }    //get year


    //set  day
    public void setDay(int day) {
        if ((day >= 1) && (day <= 31)) {   //Check Day in Range or Not
            this.day = day;
        } else {
            throw new IllegalArgumentException("You have entered an Invalid Day!"); //Throws Exception if condition falls
        }
    }

   //Set month
    public void setMonth(int month) {

        if ((month >= 1) && (month <= 12)) { //Check month in range or not
            this.month = month;
        } else {
            throw new IllegalArgumentException("You have entered an Invalid Month!"); //Throws Exception if condition falls
        }
    }

    //set  year
    public void setYear(int year) {
        if ((year >= 1800) && (year <= 2050)) {  //check year in range or not
            this.year = year;
        } else {
            throw new IllegalArgumentException("You have entered an Invalid Year!"); //Throws Exception if condition falls
        }
    }
}
