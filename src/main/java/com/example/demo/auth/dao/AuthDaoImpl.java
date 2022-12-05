package com.example.demo.auth.dao;

import com.example.demo.auth.model.Person;
import org.mindrot.jbcrypt.BCrypt;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.logging.Logger;


public class AuthDaoImpl {
    private DataSource dataSource;
    public Logger logger;

    public AuthDaoImpl(DataSource theDataSource) {
        dataSource = theDataSource;
    }

    public boolean checkStdAuth(Person thePerson) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            // get db connection
            myConn = dataSource.getConnection();
            System.out.println("go to ath dao impl");

            // create sql for insert
            String sql = "select * from user ";
            myStmt = myConn.prepareStatement(sql);

            myRs = myStmt.executeQuery();

            while (myRs.next()) {
                String sqlPassword = myRs.getString("password");
                String sqlName = myRs.getString("firstName");
                int sqlStdId = myRs.getInt("studentId");
                String name = thePerson.getFirstName();
                int stdId = thePerson.getStudentID();

                if (BCrypt.checkpw(thePerson.getPassword(), sqlPassword) &&
                        sqlName.equals(name) && sqlStdId == stdId) {
                    return true;
                }
            }
            return false;
        } finally {
            // clean up JDBC objects
            close(myConn, myStmt, myRs);
        }
    }

    public boolean checkAdminAuth(Person adminPerson) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            // get db connection
            myConn = dataSource.getConnection();

            // create sql for insert
            String sql = "select * from user ";
            myStmt = myConn.prepareStatement(sql);

            myRs = myStmt.executeQuery();
            System.out.println(myRs);

            while (myRs.next()) {
                String sqlPassword = myRs.getString("password");
                String sqlName = myRs.getString("firstName");
                int sqlAdminId = myRs.getInt("adminId");
                String name = adminPerson.getFirstName();
                int adminId = adminPerson.getAdminID();

                if (BCrypt.checkpw(adminPerson.getPassword(), sqlPassword) &&
                        sqlName.equals(name) && sqlAdminId == adminId) {
                    return true;
                }
            }
            return false;
        } finally {
            // clean up JDBC objects
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
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public void register(Person thePerson) throws Exception {

        if (thePerson.getStudentID() == 0) {
            registerAdmin(thePerson);
        }
        if (thePerson.getAdminID() == 0) {
            registerStudent(thePerson);
        }
       registerUser(thePerson);
    }

    public void registerStudent(Person thePerson) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            myConn = dataSource.getConnection();

            // create sql for insert
            String sql =
                    "INSERT INTO student (id, firstName, lastName, email, address, phoneNumber, DOB)" +
                            "VALUES (?,?, ?, ?, ?, ?, ?)";
            myStmt = myConn.prepareStatement(sql);

            myStmt.setInt(1, thePerson.getStudentID());
            myStmt.setString(2, thePerson.getFirstName());
            myStmt.setString(3, thePerson.getLastName());
            myStmt.setString(4, thePerson.getEmail());
            myStmt.setString(5, thePerson.getAddress());
            myStmt.setString(6, thePerson.getPhoneNumber());
            myStmt.setDate(7,  java.sql.Date.valueOf(
                    thePerson.getDOB().toInstant().atZone(
                            ZoneId.systemDefault()).toLocalDate()));

            myStmt.execute();
        } finally {
            close(myConn, myStmt, null);
        }
    }

    public void registerAdmin(Person thePerson) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            myConn = dataSource.getConnection();

            // create sql for insert
            String sql =
                    "INSERT INTO administrator (id, firstName, lastName, address, email, phoneNumber, DOB)" +
                            "VALUES (?,?, ?, ?, ?, ?, ?)";
            myStmt = myConn.prepareStatement(sql);

            myStmt.setInt(1, thePerson.getAdminID());
            myStmt.setString(2, thePerson.getFirstName());
            myStmt.setString(3, thePerson.getLastName());
            myStmt.setString(5, thePerson.getEmail());
            myStmt.setString(4, thePerson.getAddress());
            myStmt.setString(6, thePerson.getPhoneNumber());
            myStmt.setDate(7,  java.sql.Date.valueOf(
                    thePerson.getDOB().toInstant().atZone(
                            ZoneId.systemDefault()).toLocalDate()));

            myStmt.execute();
        } finally {
            close(myConn, myStmt, null);
        }
    }

    public void registerUser(Person thePerson) throws Exception {
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            myConn = dataSource.getConnection();

            // create sql for insert
            String sql =
                    "INSERT INTO user (firstName, lastName, address, email, " +
                            "phoneNumber, password,studentId, adminId, DOB)" +
                            "VALUES (?,?, ?, ?, ?, ?, ?, ?, ?)";
            myStmt = myConn.prepareStatement(sql);


            String hashPassword =
                    BCrypt.hashpw(thePerson.getPassword(), BCrypt.gensalt());
            myStmt.setString(1, thePerson.getFirstName());
            myStmt.setString(2, thePerson.getLastName());
            myStmt.setString(3, thePerson.getAddress());
            myStmt.setString(4, thePerson.getEmail());
            myStmt.setString(5, thePerson.getPhoneNumber());
            myStmt.setString(6, hashPassword);
            myStmt.setInt(7, thePerson.getStudentID());
            myStmt.setInt(8, thePerson.getAdminID());
            myStmt.setDate(9,  java.sql.Date.valueOf(
                    thePerson.getDOB().toInstant().atZone(
                            ZoneId.systemDefault()).toLocalDate()));

            myStmt.execute();
        } finally {
            close(myConn, myStmt, null);
        }
    }
}
