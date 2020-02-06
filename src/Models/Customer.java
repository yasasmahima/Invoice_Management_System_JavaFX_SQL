package Models;

import java.time.LocalDate;

public class Customer {

    private String customerID;
    private String customerName;
    private String customerEmail;
    private String customerAddress;
    private String customerContactNo;
    private LocalDate customerDOB;
    private Gender gender;
    private Date dob;

    public Customer(String customerName, String customerEmail, String customerAddress, String customerContactNo, Gender gender, Date dob) {

        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerAddress = customerAddress;
        this.customerContactNo = customerContactNo;
        this.gender = gender;
        this.dob = dob;
    }

    public Customer(String customerID, String customerName, String customerEmail, String customerAddress, String customerContactNo, LocalDate customerDOB, Gender gender) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerAddress = customerAddress;
        this.customerContactNo = customerContactNo;
        this.customerDOB = customerDOB;
        this.gender = gender;
    }

    public Customer(String customerName, String customerEmail, String customerAddress, String customerContactNo, LocalDate customerDOB, Gender gender) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerAddress = customerAddress;
        this.customerContactNo = customerContactNo;
        this.customerDOB = customerDOB;
        this.gender = gender;
    }

    public Customer(String customerID, String customerName, String customerEmail, String customerAddress, String customerContactNo,Gender gender) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerAddress = customerAddress;
        this.customerContactNo = customerContactNo;
        this.gender=gender;
    }

    public Customer() {
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerContactNo() {
        return customerContactNo;
    }

    public void setCustomerContactNo(String customerContactNo) {
        this.customerContactNo = customerContactNo;
    }

    public LocalDate getCustomerDOB() {
        return customerDOB;
    }

    public void setCustomerDOB(LocalDate customerDOB) {
        this.customerDOB = customerDOB;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Customer ID : " + customerID + '\n' +
                "Customer Name : " + customerName + '\n' +
                "Customer Email='" + customerEmail + '\n' +
                "Customer Address='" + customerAddress + '\n' +
                "Customer ContactNo='" + customerContactNo + '\n' +
                "Customer DOB=" + customerDOB +'\n'+
                "Gender=" + gender;
    }
}
