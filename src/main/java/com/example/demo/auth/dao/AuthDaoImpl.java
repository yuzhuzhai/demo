package com.example.demo.auth.dao;

import com.example.demo.auth.model.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
                String sqlName = myRs.getString("name");
                int sqlStdId = myRs.getInt("studentId");
                String name = theUser.getName();
                int stdId = theUser.getStudentID();

                if (BCrypt.checkpw(theUser.getPassword(), sqlPassword) &&
                        sqlName.equals(name) && sqlStdId == stdId) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        } finally {
            // clean up JDBC objects
            close(myConn, myStmt, myRs);
        }
    }

    public boolean checkAdminAuth(User theUser) throws Exception {

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

            while (myRs.next()) {
                String sqlPassword = myRs.getString("password");
                String sqlName = myRs.getString("name");
                int sqlAdminId = myRs.getInt("adminId");
                String name = theUser.getName();
                int adminId = theUser.getAdminID();

                if (BCrypt.checkpw(theUser.getPassword(), sqlPassword) &&
                        sqlName.equals(name) && sqlAdminId == adminId) {
                    return true;
                } else {
                    return false;
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

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            myConn = dataSource.getConnection();

            // create sql for insert
            String sql =
                    "INSERT INTO user (name, password,studentId, adminId)" +
                            "VALUES (?,?, ?, ?)";
            myStmt = myConn.prepareStatement(sql);


            String hashPassword =
                    BCrypt.hashpw(theUser.getPassword(), BCrypt.gensalt());
            System.out.println(hashPassword);
            myStmt.setString(1, theUser.getName());
            myStmt.setString(2, hashPassword);
            myStmt.setInt(3, theUser.getStudentID());
            myStmt.setInt(4, theUser.getAdminID());

            myStmt.execute();
        } finally {
            close(myConn, myStmt, null);
        }
    }
}
