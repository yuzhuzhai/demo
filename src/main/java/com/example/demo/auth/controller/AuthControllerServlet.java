package com.example.demo.auth.controller;

import com.example.demo.auth.dao.AuthDaoImpl;
import com.example.demo.auth.model.User;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/AuthControllerServlet")
public class AuthControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AuthDaoImpl authDaoImpl;
    public Logger logger;

    @Resource(name = "jdbc/course_management")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();

        // create course dao implementation ... and pass in the conn pool / datasource
        try {
            authDaoImpl = new AuthDaoImpl(dataSource);
        } catch (Exception exc) {
            logger.info(exc.toString());
            throw new ServletException(exc);
        }
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // read the "command" parameter
            String theCommand = request.getParameter("command");

            // route to the appropriate method
            switch (theCommand) {

                case "ADMIN":
                    adminLogin(request, response);
                    break;

                case "STUDENT":
                    studentLogin(request, response);
                    break;
            }
        } catch (Exception exc) {
            throw new ServletException(exc);
        }

    }


    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        User user = null;
        try {
            String name = request.getParameter("Name");
            String password = request.getParameter("Password");
            String role = request.getParameter("Role");
            int id = Integer.parseInt(request.getParameter("ID"));
            System.out.println(role);
            if (role.equals("student")) {
                System.out.println("student");
                user = new User(name, password, id, 0, role);
            }
            if (role.equals("admin")) {
                System.out.println("admin");
                user = new User(name, password, 0, id, role);
            }
            System.out.println(user);
            authDaoImpl.register(user);
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    private void studentLogin(HttpServletRequest request,
                              HttpServletResponse response)
            throws Exception {
        List<User> users = new ArrayList<>();
        int studentID = Integer.parseInt(request.getParameter("StdID"));
        String name = request.getParameter("Name");
        String password = request.getParameter("Password");
        User user = new User(studentID, name, password);
        System.out.println("ready go to student login dao");
        if (authDaoImpl.checkStdAuth(user)) {
            users.add(user);
            System.out.println("ready pass student data to jap");
            request.setAttribute("USER", users);
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("/home.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("/signUp.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void adminLogin(HttpServletRequest request,
                            HttpServletResponse response)
            throws Exception {
        List<User> users = new ArrayList<>();
        int ID = Integer.parseInt(request.getParameter("ID"));
        String name = request.getParameter("Name");
        String password = request.getParameter("Password");
        User user = new User(ID, name, password);
        if (authDaoImpl.checkAdminAuth(user)) {
            users.add(user);
            request.setAttribute("USER", users);
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("/home.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("/signUp.jsp");
            dispatcher.forward(request, response);
        }
    }

}
