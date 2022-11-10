package com.example.demo.course.controller;

import com.example.demo.course.model.Course;
import com.example.demo.course.dao.CourseDaoImpl;

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
import java.util.logging.Logger;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/CourseControllerServlet")
public class CourseControllerServlet extends HttpServlet {
    private CourseDaoImpl courseDaoImpl;
    public Logger logger;

    @Resource(name="jdbc/web_student_tracker")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();

        // create course dao implementation ... and pass in the conn pool / datasource
        try {
            courseDaoImpl = new CourseDaoImpl(dataSource);
        }
        catch (Exception exc) {
            logger.info(exc.toString());
            throw new ServletException(exc);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
                    listCoursesByStudent(request, response);
                    break;
            }

        }
        catch (Exception exc) {
            throw new ServletException(exc);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            // read the "command" parameter
            String theCommand = request.getParameter("command");

            // if the command is missing, then default to listing students
            if (theCommand == null) {
                theCommand = "LIST";
            }

            // route to the appropriate method
            switch (theCommand) {

                case "ADD":
                    addCourse(request, response);
                    break;
            }

        }
        catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    private void addCourse(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String courseName = request.getParameter("courseName");

        Course theCourse = new Course(courseName);

        courseDaoImpl.addCourse(theCourse);

        // send back to main page (the student list)
        listCourses(request, response);
    }

    private void listCourses(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        List<Course> courses = courseDaoImpl.getCourses();

        request.setAttribute("COURSE_LIST", courses);

        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin-list-courses.jsp");
        dispatcher.forward(request, response);
    }

    private void listCoursesByStudent(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String courseName = request.getParameter("courseName");

        List<Course> courses = courseDaoImpl.getCoursesByStudent(courseName);

        request.setAttribute("COURSE_LIST", courses);

        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin-list-courses.jsp");
        dispatcher.forward(request, response);
    }

}
