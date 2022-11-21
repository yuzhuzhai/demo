<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.demo.student.model.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.demo.course.model.Course" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <title>Assignment 1</title>
    <!-- MDB icon -->
    <link rel="icon" href="img/mdb-favicon.ico" type="image/x-icon"/>
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
    <link rel="stylesheet" href="./css/mdb.min.css"/>
    <link rel="stylesheet" href="./css/report2.css"/>
</head>

<%
    // get the students from the request object (sent by servlet)
    List<Course> theCourses =
            (List<Course>) request.getAttribute("ALL_COURSE_LIST");
%>

<body>
<section class="intro">
    <div class="bg-image h-100"
         style="background-image: url('https://mdbootstrap.com/img/Photos/new-templates/tables/img2.jpg');">
        <div class="mask d-flex align-items-center h-100" style="background-color: rgba(0,0,0,.25);">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-12">
                        <div class="card bg-dark shadow-2-strong">
                            <div class="card-body">
                                <div class="table-responsive">

                                    <h2 class="text-center mb-5">All Course</h2>
                                    <table class="table">

                                        <thead>
                                        <tr>
                                            <th scope="col">Course Title</th>
                                            <th scope="col">Course semester</th>
                                            <th scope="col">Course days</th>
                                        </tr>
                                        </thead>
                                        <tbody>

                                        <% for (Course currentCourse : theCourses) { %>

                                        <tr>
                                            <td><%= currentCourse.getTitle() %>
                                            </td>
                                            <td><%= currentCourse.getSemester() %>
                                            </td>
                                            <td><%= currentCourse.getDays() %>
                                            </td>
                                        </tr>

                                        <% } %>
                                        </tbody>
                                    </table>
                                    <div class="text-center">
                                        <p>Go Back <a href="./admin.jsp">add course</a>
                                        or Go to <a href="./report.jsp">report</a></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>









