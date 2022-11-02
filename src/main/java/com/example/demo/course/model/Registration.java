package com.example.demo.course.model;

public class Registration {
    private int id;
    private String studentID;
    private int courseID;

    public Registration(int id, String studentID, int courseID) {
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

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
}
