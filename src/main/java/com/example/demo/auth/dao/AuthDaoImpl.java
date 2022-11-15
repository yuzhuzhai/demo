//package com.example.demo.auth.dao;
//
//import com.example.demo.course.model.Course;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.util.logging.Logger;
//
//public class AuthDaoImpl {
//    private DataSource dataSource;
//    public Logger logger;
//
//    public AuthDaoImpl(DataSource theDataSource) {
//        dataSource = theDataSource;
//    }
//
//    public void checkAuth(User theUser) throws Exception {
//
//        Connection myConn = null;
//        PreparedStatement myStmt = null;
//
//        try {
//            // get db connection
//            myConn = dataSource.getConnection();
//
//            // create sql for insert
//            String sql = "INSERT INTO course (ID,title,semester,days,time,instructor,room,startDate,endDate,adminID) " +
//                    "VALUES (?,?,?,?,?,?,?,?,?,?)";
//            myStmt = myConn.prepareStatement(sql);
//
////            myStmt.setInt(1, theCourse.getID());
////            myStmt.setString(2, theCourse.getTitle());
////            myStmt.setString(3, theCourse.getSemester());
////            myStmt.setString(4, theCourse.getDays());
////            myStmt.setString(5, theCourse.getTime());
////            myStmt.setString(6, theCourse.getInstructor());
////            myStmt.setString(7, theCourse.getRoom());
////            myStmt.setString(8, theCourse.getStartDate());
////            myStmt.setString(9, theCourse.getEndDate());
////            myStmt.setInt(10, theCourse.getAdminID());
//
//
//
//
//
//            // execute sql insert
//            myStmt.execute();
//        }
//        finally {
//            // clean up JDBC objects
//            close(myConn, myStmt, null);
//        }
//    }
//}
