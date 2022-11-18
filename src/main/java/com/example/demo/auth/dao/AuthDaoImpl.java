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

    public boolean checkAuth(User theUser) throws Exception {

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            // get db connection
            myConn = dataSource.getConnection();

            // create sql for insert
            String sql = "select * from user where name = ? && password = ?";

            myStmt = myConn.createStatement();

            myRs = myStmt.executeQuery(sql);

            while (myRs.next()) {
                String hashPassword = BCrypt.hashpw(theUser.getPassword(), BCrypt.gensalt());
                String sqlPassword = myRs.getString("password");
                String sqlName = myRs.getString("name");
                String name = theUser.getName();
                if(hashPassword.equals(sqlPassword)&&sqlName.equals(name)) {
                    return true;
                } else {
                    return false;
                }
            }
            return true;
        }
        finally {
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
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public void register(User theUser) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            myConn = dataSource.getConnection();

            // create sql for insert
            String sql = "INSERT INTO user (id, name, password)" +
                    "VALUES (?,?,?)";
            myStmt = myConn.prepareStatement(sql);

            myStmt.setInt(1, theUser.getID());
            String hashPassword = BCrypt.hashpw(theUser.getPassword(), BCrypt.gensalt());
            myStmt.setString(2, theUser.getName());
            myStmt.setString(3, hashPassword);

            myStmt.execute();
        }
        finally {
            close(myConn, myStmt, null);
        }
    }
}
