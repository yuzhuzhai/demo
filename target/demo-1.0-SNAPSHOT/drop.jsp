<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.demo.student.model.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.demo.course.model.Course" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="static java.lang.System.out" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta http-equiv="x-ua-compatible" content="ie=edge" />
    <title>Assignment 2</title>
    <!-- MDB icon -->
    <link rel="icon" href="img/mdb-favicon.ico" type="image/x-icon" />
    <!-- Font Awesome -->
    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
    />
    <!-- Google Fonts Roboto -->
    <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700;900&display=swap"
    />
    <!-- MDB -->
    <link rel="stylesheet" href="css/mdb.min.css" />
    <link rel="stylesheet" href="./course.css" />
    <style type="text/css">
        body{
            color: white;
        }
    </style>
</head>
<body>
<div class="bg-image h-100" style="background-image: url('https://mdbootstrap.com/img/Photos/new-templates/tables/img3.jpg');">
    <div class="mask d-flex flex-column justify-content-center align-items-center h-100" style="background-color: rgba(0,0,0,.25);">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-12">
                    <div class="card bg-dark shadow-2-strong overflow-auto" style="height: 35rem;">
                        <div class="card-body">
                            <div class="table-responsive d-flex flex-column justify-content-center align-items-center">
                                <h3 class="text-center mb-5" style="color: white">Your current courses list for this semester: </h3>
                                <%List<Course> theCourses = (List<Course>) request.getAttribute("COURSE_LIST");%>
                                <table id="studentCourseTable" class="table">
                                    <thead>
                                    <tr>
                                        <th>Course ID</th>
                                        <th>Title</th>
                                        <th>Semester</th>
                                    </tr>
                                    </thead>
                                    <% for (Course currentCourse : theCourses) { %>
                                    <tr>
                                        <td> <%= currentCourse.getID() %> </td>
                                        <td> <%= currentCourse.getTitle() %> </td>
                                        <td> <%= currentCourse.getSemester() %> </td>
                                    </tr>
                                    <% } %>
                                </table>

                                <br/><br/><br/>
                                <a href="home.jsp" class="btn btn-primary">Go Back</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>