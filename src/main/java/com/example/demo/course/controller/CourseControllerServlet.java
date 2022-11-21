package com.example.demo.course.controller;

import com.example.demo.course.dao.CourseDaoImpl;
import com.example.demo.course.model.Course;
import com.example.demo.student.dao.StudentDaoImpl;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/CourseControllerServlet")
public class CourseControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CourseDaoImpl courseDaoImpl;

    private StudentDaoImpl studentDaoImpl;
    public Logger logger;

    @Resource(name="jdbc/course_management")
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
                case "REGISTER":
                    addRegistrationCourse(request, response);
                    break;
                case "DROP":
                    dropCourse(request, response);
                    break;
            }

        }
        catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    private void addCourse(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int ID = Integer.parseInt(request.getParameter("ID"));
        String title = request.getParameter("Title");
        String semester = request.getParameter("Semester");
        String days = request.getParameter("Days");
        String time = request.getParameter("Time");
        String instructor = request.getParameter("Instructor");
        String room = request.getParameter("Room");
        System.out.println(request.getParameter("StartDate"));
        System.out.println(new SimpleDateFormat("yyyy/MM/dd").parse(request.getParameter("StartDate")));
        Date startDate = new SimpleDateFormat("yyyy/MM/dd").parse(request.getParameter("StartDate"));
        System.out.println(startDate);
        Date endDate = new SimpleDateFormat("yyyy/MM/dd").parse(request.getParameter("EndDate"));

        int adminID = Integer.parseInt(request.getParameter("AdminId"));


        Course theCourse = new Course(ID, title,semester,days,time,instructor,room,startDate,endDate,adminID);

        courseDaoImpl.addCourse(theCourse);

        // send back to main page (the student list)
        listCourses(request, response);
    }

    private void dropCourse(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String studentIDStr = request.getParameter("stdID");
        int studentID = Integer.parseInt(studentIDStr);
        String theSemester = request.getParameter("semester");

        String[] courseIDArr = request.getParameterValues("courseID");

        if(courseIDArr.length != 0){
            for(String courseIDStr: courseIDArr){
                int courseID = Integer.parseInt(courseIDStr.trim());
                courseDaoImpl.dropCourse(studentID, courseID);
            }
        }

        List<Course> enrolledCoursesForTheStudent = courseDaoImpl.getStudentEnrollCourseOnSemester(studentID);
        for (int i = 0, len = enrolledCoursesForTheStudent.size(); i < len; i++) {
            if(!enrolledCoursesForTheStudent.get(i).getSemester().equals(theSemester)){
                enrolledCoursesForTheStudent.remove(i);
                len--;
                i--;
            }
        }

        request.setAttribute("COURSE_LIST", enrolledCoursesForTheStudent);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/drop.jsp");
        dispatcher.forward(request, response);
    }

    private void addRegistrationCourse(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // get current time
//        Timestamp date = new Timestamp(System.currentTimeMillis());
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        String dateNow = df.format(date);
//        System.out.println("System time: "+dateNow);
//        String theCourseStartDate = "";

        // read semester id from form data
        String theSemester = request.getParameter("semester");
        // read student info from form data
        int studentID = Integer.parseInt(request.getParameter("stdID"));
        String[] courseIDArr = request.getParameterValues("courseID");
        for(String courseIDStr: courseIDArr){
            int courseID = Integer.parseInt(courseIDStr.trim());
//            Course theCourse = courseDaoImpl.getCourse(courseID);
//            theCourseStartDate = df.format(theCourse.getStartDate());
//            if(df.parse(dateNow).getTime() < df.parse(theCourseStartDate).getTime()){}
            courseDaoImpl.addRegistrationCourse(studentID,courseID);
        }

        List<Course> enrolledCoursesForTheStudent = courseDaoImpl.getStudentEnrollCourseOnSemester(studentID);
        for (int i = 0, len = enrolledCoursesForTheStudent.size(); i < len; i++) {
            if(!enrolledCoursesForTheStudent.get(i).getSemester().equals(theSemester)){
                enrolledCoursesForTheStudent.remove(i);
                len--;
                i--;
            }
        }
        request.setAttribute("ENROLLED_COURSE_LIST", enrolledCoursesForTheStudent);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/register.jsp");
        dispatcher.forward(request, response);
    }

    private void listCourses(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        List<Course> courses = courseDaoImpl.getCourses();

        request.setAttribute("ALL_COURSE_LIST", courses);

        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin-list-all-course.jsp");
        dispatcher.forward(request, response);
    }

    private void listCoursesByStudent(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        int studentId = Integer.parseInt(request.getParameter("id"));

        List<Course> courses = courseDaoImpl.getCoursesByStudent(studentId);

        request.setAttribute("COURSE_LIST_BY_STUDENT", courses);

        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin-list-student-courses.jsp");
        dispatcher.forward(request, response);
    }

}
