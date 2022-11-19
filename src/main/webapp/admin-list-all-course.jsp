
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.demo.student.model.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.demo.course.model.Course" %>
<!DOCTYPE html>
<html>

<head>
  <title>Course List</title>

  <link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<%
  // get the students from the request object (sent by servlet)
  List<Course> theCourses =
          (List<Course>) request.getAttribute("ALL_COURSE_LIST");
%>

<body>
<h1> Add Course Successful</h1>
<div id="wrapper">
  <div id="header">
    <h2>Concordia University</h2>
  </div>
</div>

<div id="container">

  <div id="content">

    <table>

      <tr>
        <th>title</th>
        <th>semester</th>
        <th>days</th>
      </tr>

      <% for (Course currentCourse : theCourses) { %>

      <tr>
        <td> <%= currentCourse.getTitle() %> </td>
        <td> <%= currentCourse.getSemester() %> </td>
        <td> <%= currentCourse.getDays() %> </td>
      </tr>

      <% } %>

    </table>

  </div>

</div>
</body>


</html>









