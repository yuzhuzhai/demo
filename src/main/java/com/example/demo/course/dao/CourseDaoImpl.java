package com.example.demo.course.dao;

import com.example.demo.course.model.Course;

import javax.sql.DataSource;
import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class CourseDaoImpl {
    private DataSource dataSource;
    public Logger logger;

    public CourseDaoImpl(DataSource theDataSource) {
        dataSource = theDataSource;
    }

    public List<Course> getCourses() throws Exception {

        List<Course> courses = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            // get a connection
            myConn = dataSource.getConnection();

            // create sql statement
            String sql = "select * from course order by title";

            myStmt = myConn.createStatement();

            // execute query
            myRs = myStmt.executeQuery(sql);

            // process result set
            while (myRs.next()) {

                // retrieve data from result set row
                int id = myRs.getInt("id");
                String title = myRs.getString("title");
                String semester = myRs.getString("semester");
                String days = myRs.getString("days");
                Timestamp time = myRs.getTimestamp("time");
                String instructor = myRs.getString("instructor");
                String room = myRs.getString("room");
                Date startDate = myRs.getDate("startDate");
                Date endDate = myRs.getDate("endDate");
                int adminID = myRs.getInt("adminID");




                Course tempCourse = new Course(id, title,semester,days,time,instructor,room,startDate,endDate,adminID);

                // add it to the list of students
                courses.add(tempCourse);
            }

            return courses;
        }
        finally {
            // close JDBC objects
            close(myConn, myStmt, myRs);
        }
    }


    public List<Course> getCoursesByStudent(int studentId) throws Exception {

        List<Course> courses = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            // get a connection
            myConn = dataSource.getConnection();

            // create sql statement
            String sql = "select * from course order by title";

            myStmt = myConn.createStatement();

            // execute query
            myRs = myStmt.executeQuery(sql);

            // process result set
            while (myRs.next()) {

                // retrieve data from result set row
                int id = myRs.getInt("id");
                String title = myRs.getString("title");
                String semester = myRs.getString("semester");
                String days = myRs.getString("days");
                Timestamp time = myRs.getTimestamp("time");
                String instructor = myRs.getString("instructor");
                String room = myRs.getString("room");

                Date startDate = myRs.getDate("startDate");
                Date endDate = myRs.getDate("endDate");
                int adminID = myRs.getInt("adminID");

                Course tempCourse = new Course(id, title,semester,days,time,instructor,room,startDate,endDate,adminID);

                // add it to the list of students
                courses.add(tempCourse);
            }

            return courses;
        }
        finally {
            // close JDBC objects
            close(myConn, myStmt, myRs);
        }
    }

    private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

        try {
            if (myRs != null) {
                myRs.close();
            }

            if (myStmt != null) {
                myStmt.close();
            }

            if (myConn != null) {
                myConn.close();   // doesn't really close it ... just puts back in connection pool
            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public void addRegistrationCourse(int studentIdValue, int courseIdValue) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            // get db connection
            myConn = dataSource.getConnection();

            // create sql for insert
            String sql = "INSERT INTO registration (ID, studentID, courseID) " +
                    "VALUES (NULL," +
                    studentIdValue + "," +
                    courseIdValue +
                    ")";

            myStmt = myConn.prepareStatement(sql);

            // execute sql insert
            myStmt.execute();
        }
        finally {
            // clean up JDBC objects
            close(myConn, myStmt, null);
        }
    }

    public void addCourse(Course theCourse) throws Exception {
        System.out.println(theCourse.getID());
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            // get db connection
            myConn = dataSource.getConnection();

            // create sql for insert
            String sql = "INSERT INTO course (ID,title,semester,days,time,instructor,room,startDate,endDate,adminID) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?)";
            myStmt = myConn.prepareStatement(sql);

            myStmt.setInt(1, theCourse.getID());
            myStmt.setString(2, theCourse.getTitle());
            myStmt.setString(3, theCourse.getSemester());
            myStmt.setString(4, theCourse.getDays());
            myStmt.setTimestamp(5, theCourse.getTime());
            myStmt.setString(6, theCourse.getInstructor());
            myStmt.setString(7, theCourse.getRoom());
            myStmt.setDate(8,
                    java.sql.Date.valueOf(theCourse.getStartDate().toInstant().atZone(
                            ZoneId.systemDefault()).toLocalDate()));
            myStmt.setDate(9, java.sql.Date.valueOf(theCourse.getEndDate().toInstant().atZone(
                    ZoneId.systemDefault()).toLocalDate()));
            myStmt.setInt(10, theCourse.getAdminID());

            // execute sql insert
            myStmt.execute();
        }
        finally {
            // clean up JDBC objects
            close(myConn, myStmt, null);
        }
    }

    public void DropRegistrationCourse(int courseIdValue) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            // get connection to database
            myConn = dataSource.getConnection();

            // create sql to delete student
            String sql = "delete from student where id=?";

            // prepare statement
            myStmt = myConn.prepareStatement(sql);

            // set params
            myStmt.setInt(1, courseIdValue);

            // execute sql statement
            myStmt.execute();
        }
        finally {
            // clean up JDBC code
            close(myConn, myStmt, null);
        }
    }


    public List<Course> getStudentCourseOnSemester(String semesterValue, String stdIdValue) throws Exception {

        List<Course> courses = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            // get a connection
            myConn = dataSource.getConnection();

            // create sql statement
            String sql = "SELECT c.* FROM course c WHERE c.semester = " + semesterValue +
                    "AND c.ID NOT IN (SELECT courseID FROM registration WHERE studentID = " + stdIdValue
                    + ")";

            myStmt = myConn.createStatement();

            // execute query
            myRs = myStmt.executeQuery(sql);

            // process result set
            while (myRs.next()) {

                // retrieve data from result set row
                int id = myRs.getInt("id");
                String title = myRs.getString("title");
                String semester = myRs.getString("semester");
                String days = myRs.getString("days");
                String time = myRs.getString("time");
                String instructor = myRs.getString("instructor");

                // create new student object
                Course tempCourse = null;

                // add it to the list of students
                //courses.add(tempCourse);
            }

            return courses;
        }
        finally {
            // close JDBC objects
            close(myConn, myStmt, myRs);
        }
    }

    public List<Course> getStudentEnrollCourseOnSemester(String studentIdValue) throws Exception {

        List<Course> courses = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            // get a connection
            myConn = dataSource.getConnection();

            // create sql statement
            String sql = "SELECT c.ID, c.title, c.semester, r.id " +
                    "FROM course c, registration r WHERE r.courseID = c.ID" +
                    " AND r.studentID = " +
                    studentIdValue ;

            myStmt = myConn.createStatement();

            // execute query
            myRs = myStmt.executeQuery(sql);

            // process result set
            while (myRs.next()) {

                // retrieve data from result set row
                int id = myRs.getInt("id");
                String title = myRs.getString("title");
                String semester = myRs.getString("semester");

                // create new student object
                //Course tempCourse = new Course(id, title, semester);

                // add it to the list of students
                //courses.add(tempCourse);
            }

            return courses;
        }
        finally {
            // close JDBC objects
            close(myConn, myStmt, myRs);
        }
    }
}
