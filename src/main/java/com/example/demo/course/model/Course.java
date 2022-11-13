package com.example.demo.course.model;

public class Course {
    private int ID;
    private String title;
    private String semester;
    private String days;
    private String time;
    private String instructor;
    private String room;
    private String startDate;
    private String endDate;
    private int adminID;

    public String getTitle() {
        return title;
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public Course(int ID, String title, String semester, String days, String time,
                  String instructor, String room, String startDate,
                  String endDate, int adminID) {
        this.ID=ID;        this.title = title;
        this.semester = semester;
        this.days = days;
        this.time = time;
        this.instructor = instructor;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.adminID = adminID;
    }

    @Override
    public String toString() {
        return "Course{" +
                "ID=" + ID +
                ", title='" + title + '\'' +
                ", semester='" + semester + '\'' +
                ", days='" + days + '\'' +
                ", time='" + time + '\'' +
                ", instructor='" + instructor + '\'' +
                ", room='" + room + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", adminID=" + adminID +
                '}';
    }
}
