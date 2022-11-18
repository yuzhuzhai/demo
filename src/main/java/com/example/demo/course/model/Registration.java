package com.example.demo.course.model;

public class Registration {
    private int id;
    private int studentID;
    private int courseID;

    public Registration(int id, int studentID, int courseID) {
        this.id = id;
        this.studentID = studentID;
        this.courseID = courseID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
}
