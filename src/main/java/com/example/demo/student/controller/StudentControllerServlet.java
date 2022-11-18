package com.example.demo.student.controller;

import com.example.demo.student.dao.StudentDaoImpl;
import com.example.demo.student.model.Student;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private StudentDaoImpl studentDaoImpl;

    @Resource(name = "jdbc/products")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();

        // create student dao implementation ... and pass in the conn pool / datasource
        try {
            studentDaoImpl = new StudentDaoImpl(dataSource);
        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // read the "command" parameter
            String theCommand = request.getParameter("command");

            // if the command is missing, then default to listing students
            if (theCommand == null) {
                theCommand = "LIST";
            }

            // route to the appropriate method
            switch (theCommand) {

                case "LIST":
                    listStudents(request, response);
                    break;

                case "LIST_COURSE_BY_STUDENT":
                    listStudentsByCourse(request, response);
                    break;

                case "ADD":
                    addStudent(request, response);
                    break;

                case "LOAD":
                    loadStudent(request, response);
                    break;

                case "UPDATE":
                    updateStudent(request, response);
                    break;

                case "DELETE":
                    deleteStudent(request, response);
                    break;

                default:
                    listStudents(request, response);
            }

        } catch (Exception exc) {
            throw new ServletException(exc);
        }

    }

    private void deleteStudent(HttpServletRequest request,
                               HttpServletResponse response)
            throws Exception {

        // read student id from form data
        String theStudentId = request.getParameter("studentId");

        // delete student from database
        studentDaoImpl.deleteStudent(theStudentId);

        // send them back to "list students" page
        listStudents(request, response);
    }

    private void updateStudent(HttpServletRequest request,
                               HttpServletResponse response)
            throws Exception {

        // read student info from form data
        int id = Integer.parseInt(request.getParameter("studentId"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        // create a new student object
        Student theStudent = new Student(id, firstName, lastName, email);

        // perform update on database
        studentDaoImpl.updateStudent(theStudent);

        // send them back to the "list students" page
        listStudents(request, response);

    }

    private void loadStudent(HttpServletRequest request,
                             HttpServletResponse response)
            throws Exception {

        // read student id from form data
        String theStudentId = request.getParameter("studentId");

        // get student from database (db util)
        Student theStudent = studentDaoImpl.getStudent(theStudentId);

        // place student in the request attribute
        request.setAttribute("THE_STUDENT", theStudent);

        // send to jsp page: update-student-form.jsp
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/update-student-form.jsp");
        dispatcher.forward(request, response);
    }

    private void addStudent(HttpServletRequest request,
                            HttpServletResponse response) throws Exception {

        // read student info from form data
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        // create a new student object
        Student theStudent = new Student(firstName, lastName, email);

        // add the student to the database
        studentDaoImpl.addStudent(theStudent);

        // send back to main page (the student list)
        listStudents(request, response);
    }

    private void listStudents(HttpServletRequest request,
                              HttpServletResponse response)
            throws Exception {

        // get students from dao implementation
        List<Student> students = studentDaoImpl.getStudents();

        // add students to the request
        request.setAttribute("STUDENT_LIST", students);

        // send to JSP page (view)
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/admin-list-students.jsp");
        dispatcher.forward(request, response);
    }

    private void listStudentsByCourse(HttpServletRequest request,
                                      HttpServletResponse response)
            throws Exception {

        int courseID = Integer.parseInt(request.getParameter("courseID"));

        List<Student> students = studentDaoImpl.getStudentsByCourse(courseID);

        request.setAttribute("STUDENT_LIST_BY_COURSE", students);

        // send to JSP page (view)
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/admin-list-student.jsp");
        dispatcher.forward(request, response);
    }

}













