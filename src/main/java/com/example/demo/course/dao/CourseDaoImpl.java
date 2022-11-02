package com.example.demo.course.dao;

import com.example.demo.course.model.Course;
import com.example.demo.student.model.Student;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl {
    private DataSource dataSource;

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
                String time = myRs.getString("time");
                String instructor = myRs.getString("instructor");

                Course tempCourse = new Course(id, title, semester, days, time, instructor);

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
                Course tempCourse = new Course(id, title, semester, days, time, instructor);

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
                Course tempCourse = new Course(id, title, semester);

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
}
