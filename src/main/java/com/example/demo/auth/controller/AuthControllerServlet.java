//package com.example.demo.auth.controller;
//
//import com.example.demo.course.dao.CourseDaoImpl;
//import com.example.demo.course.model.Course;
//
//import javax.annotation.Resource;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.sql.DataSource;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.logging.Logger;
//
///**
// * Servlet implementation class StudentControllerServlet
// */
//@WebServlet("/AuthControllerServlet")
//public class AuthControllerServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//    private CourseDaoImpl authDaoImpl;
//    public Logger logger;
//
//    @Resource(name="jdbc/web_student_tracker")
//    private DataSource dataSource;
//
//    @Override
//    public void init() throws ServletException {
//        super.init();
//
//        // create course dao implementation ... and pass in the conn pool / datasource
//        try {
//            authDaoImpl = new AuthDaoImpl(dataSource);
//        }
//        catch (Exception exc) {
//            logger.info(exc.toString());
//            throw new ServletException(exc);
//        }
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        List<User> users = new ArrayList<>();
//        try {
//            int ID = Integer.parseInt(request.getParameter("ID"));
//            String name = request.getParameter("Name");
//            String password = request.getParameter("Password");
//            User user = new User(ID, name, password);
//            if(authDaoImpl.checkAuth(user)) {
//                users.add(user);
//                request.setAttribute("USER", users);
//                RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
//                dispatcher.forward(request, response);
//            } else {
//                RequestDispatcher dispatcher = request.getRequestDispatcher("/signUp.jsp");
//                dispatcher.forward(request, response);
//            }
//        }
//        catch (Exception exc) {
//            throw new ServletException(exc);
//        }
//
//    }
//
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        try {
//            int ID = Integer.parseInt(request.getParameter("ID"));
//            String name = request.getParameter("Name");
//            String password = request.getParameter("Password");
//            User user = new User(ID, name, password);
//            authDaoImpl.register(user);
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
//            dispatcher.forward(request, response);
//        }
//        catch (Exception exc) {
//            throw new ServletException(exc);
//        }
//    }
//}
