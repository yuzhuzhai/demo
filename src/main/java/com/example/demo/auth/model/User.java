package com.example.demo.auth.model;

public class User {
    private int ID;
    private String name;
    private String password;
    private int studentID;
    private int adminID;

    public User(int id, String name, String password) {
        this.name = name;
        this.password = password;
        this.studentID = studentID;
        this.adminID = adminID;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", studentID=" + studentID +
                ", adminID=" + adminID +
                '}';
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public User(String name, String password, int studentID, int adminID) {
        this.name = name;
        this.password = password;
        this.studentID = studentID;
        this.adminID = adminID;
    }
}
