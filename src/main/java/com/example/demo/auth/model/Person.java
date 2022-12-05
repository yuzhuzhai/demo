package com.example.demo.auth.model;

import java.util.Date;

public class Person {
    private int ID;
    private String firstName;
    private String role;
    private String password;
    private int studentID;
    private int adminID;
    private String lastName;
    private String address;
    private String email;
    private String phoneNumber;
    private Date DOB;

    public Person(String firstName, int studentID, String password) {
        this.firstName = firstName;
        this.studentID = studentID;
        this.password = password;
    }
    public Person(int adminID, String firstName, String password) {
        this.firstName = firstName;
        this.adminID = adminID;
        this.password = password;
    }

    public Person(String firstName, String role, String password,
                  int studentID, String lastName,
                  String address, String email, String phoneNumber,
                  Date DOB, int adminID) {
        this.firstName = firstName;
        this.role = role;
        this.password = password;
        this.studentID = studentID;
        this.adminID = adminID;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.DOB = DOB;
    }

    public Person(int studentID, String firstName, String role, String password,
                  int adminID, String lastName,
                  String address, String email, String phoneNumber,
                  Date DOB) {
        this.firstName = firstName;
        this.role = role;
        this.password = password;
        this.studentID = studentID;
        this.adminID = adminID;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.DOB = DOB;
    }

    public Person() {

    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", firstName='" + firstName + '\'' +
                ", role='" + role + '\'' +
                ", password='" + password + '\'' +
                ", studentID=" + studentID +
                ", adminID=" + adminID +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", DOB=" + DOB +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

}
