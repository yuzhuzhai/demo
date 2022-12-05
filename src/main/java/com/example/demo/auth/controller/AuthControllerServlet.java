package com.example.demo.auth.controller;

import com.example.demo.auth.dao.AuthDaoImpl;
import com.example.demo.auth.model.Person;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        Person person = null;
        try {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String address = request.getParameter("address");
            String email = request.getParameter("email");
            String phoneNumber = request.getParameter("phoneNumber");
            Date DOB = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("bd"));
            String password = request.getParameter("Password");
            String role = request.getParameter("Role");
            int id = Integer.parseInt(request.getParameter("ID"));
            if (role.equals("student")) {
                person = new Person(id, firstName, role, password, 0, lastName, address, email, phoneNumber, DOB);
            }
            if (role.equals("admin")) {
                System.out.println("admin");
                person = new Person(firstName, role, password, 0, lastName, address, email, phoneNumber, DOB, id);
            }
            authDaoImpl.register(person);
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("/logInForAll.jsp");
            dispatcher.forward(request, response);
        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    private void studentLogin(HttpServletRequest request,
                              HttpServletResponse response)
            throws Exception {
        List<Person> people = new ArrayList<>();
        int studentID = Integer.parseInt(request.getParameter("StdID"));
        String name = request.getParameter("Name");
        String password = request.getParameter("Password");
        Person person = new Person(name, studentID, password);
        System.out.println("ready go to student login dao");
        if (authDaoImpl.checkStdAuth(person)) {
            people.add(person);
            request.getSession().setAttribute("studentName", request.getParameter  ("Name"));
            request.getSession().setAttribute("studentID", request.getParameter  ("StdID"));
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("/student.jsp");
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
        List<Person> people = new ArrayList<>();
        int adminID = Integer.parseInt(request.getParameter("adminID"));
        String name = request.getParameter("Name");
        String password = request.getParameter("Password");
        Person adminPerson = new Person( adminID,name, password);
        System.out.println(authDaoImpl.checkAdminAuth(adminPerson));
        System.out.println(adminPerson);
        if (authDaoImpl.checkAdminAuth(adminPerson)) {
            people.add(adminPerson);
            request.getSession().setAttribute("adminName", request.getParameter  ("Name"));
            request.getSession().setAttribute("adminID", request.getParameter  ("adminID"));
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("/admin.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("/signUp.jsp");
            dispatcher.forward(request, response);
        }
    }

}
