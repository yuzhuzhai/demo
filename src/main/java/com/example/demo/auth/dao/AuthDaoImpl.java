package com.example.demo.auth.dao;

import com.example.demo.auth.model.User;
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

    public boolean checkStdAuth(User theUser) throws Exception {

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
                String name = theUser.getFirstName();
                int stdId = theUser.getStudentID();

                if (BCrypt.checkpw(theUser.getPassword(), sqlPassword) &&
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

    public boolean checkAdminAuth(User adminUser) throws Exception {

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
                String name = adminUser.getFirstName();
                int adminId = adminUser.getAdminID();

                if (BCrypt.checkpw(adminUser.getPassword(), sqlPassword) &&
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

    public void register(User theUser) throws Exception {

        if (theUser.getStudentID() == 0) {
            registerAdmin(theUser);
        }
        if (theUser.getAdminID() == 0) {
            registerStudent(theUser);
        }
       registerUser(theUser);
    }

    public void registerStudent(User theUser) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            myConn = dataSource.getConnection();

            // create sql for insert
            String sql =
                    "INSERT INTO student (id, firstName, lastName, email, address, phoneNumber, DOB)" +
                            "VALUES (?,?, ?, ?, ?, ?, ?)";
            myStmt = myConn.prepareStatement(sql);

            myStmt.setInt(1, theUser.getStudentID());
            myStmt.setString(2, theUser.getFirstName());
            myStmt.setString(3, theUser.getLastName());
            myStmt.setString(4, theUser.getEmail());
            myStmt.setString(5, theUser.getAddress());
            myStmt.setString(6, theUser.getPhoneNumber());
            myStmt.setDate(7,  java.sql.Date.valueOf(
                    theUser.getDOB().toInstant().atZone(
                            ZoneId.systemDefault()).toLocalDate()));

            myStmt.execute();
        } finally {
            close(myConn, myStmt, null);
        }
    }

    public void registerAdmin(User theUser) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            myConn = dataSource.getConnection();

            // create sql for insert
            String sql =
                    "INSERT INTO administrator (id, firstName, lastName, address, email, phoneNumber, DOB)" +
                            "VALUES (?,?, ?, ?, ?, ?, ?)";
            myStmt = myConn.prepareStatement(sql);

            myStmt.setInt(1, theUser.getAdminID());
            myStmt.setString(2, theUser.getFirstName());
            myStmt.setString(3, theUser.getLastName());
            myStmt.setString(5, theUser.getEmail());
            myStmt.setString(4, theUser.getAddress());
            myStmt.setString(6, theUser.getPhoneNumber());
            myStmt.setDate(7,  java.sql.Date.valueOf(
                    theUser.getDOB().toInstant().atZone(
                            ZoneId.systemDefault()).toLocalDate()));

            myStmt.execute();
        } finally {
            close(myConn, myStmt, null);
        }
    }

    public void registerUser(User theUser) throws Exception {
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
                    BCrypt.hashpw(theUser.getPassword(), BCrypt.gensalt());
            myStmt.setString(1, theUser.getFirstName());
            myStmt.setString(2, theUser.getLastName());
            myStmt.setString(3, theUser.getAddress());
            myStmt.setString(4, theUser.getEmail());
            myStmt.setString(5, theUser.getPhoneNumber());
            myStmt.setString(6, hashPassword);
            myStmt.setInt(7, theUser.getStudentID());
            myStmt.setInt(8, theUser.getAdminID());
            myStmt.setDate(9,  java.sql.Date.valueOf(
                    theUser.getDOB().toInstant().atZone(
                            ZoneId.systemDefault()).toLocalDate()));

            myStmt.execute();
        } finally {
            close(myConn, myStmt, null);
        }
    }
}
