<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.demo.course.model.Course" %>

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
</head>
<body>
  <section class="intro">
    <div class="bg-image h-100" style="background-image: url('https://mdbootstrap.com/img/Photos/new-templates/tables/img3.jpg');">
      <div class="mask d-flex align-items-center h-100" style="background-color: rgba(0,0,0,.25);">
        <div class="container">
          <div class="row justify-content-center">
            <div class="col-12">
              <div class="card bg-dark shadow-2-strong overflow-auto" style="height: 35rem;">
                <div class="card-body">
                  <div class="table-responsive">
                    <h3 class="text-center mb-5" style="color: white">Your current courses list for this semester: </strong></p>
                    <table id="studentCourseTable" class="table">
                      <% List<Course> enrolledCourses = (List<Course>) request.getAttribute("ENROLLED_COURSE_LIST"); %>
                      <thead>
                      <tr>
                        <th>Title</th>
                        <th>Semester</th>
                        <th>Days</th>
                        <th>Time</th>
                        <th>Instructor</th>
                        <th>Room</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>Admin ID</th>
                      </tr>
                      </thead>
                      <% for (Course currentCourse : enrolledCourses) { %>
                      <tr>
                        <td> <%= currentCourse.getTitle() %> </td>
                        <td> <%= currentCourse.getSemester() %> </td>
                        <td> <%= currentCourse.getDays() %> </td>
                        <td> <%= currentCourse.getTime() %> </td>
                        <td> <%= currentCourse.getInstructor() %> </td>
                        <td> <%= currentCourse.getRoom() %> </td>
                        <td> <%= currentCourse.getStartDate() %> </td>
                        <td> <%= currentCourse.getEndDate() %> </td>
                        <td> <%= currentCourse.getAdminID() %> </td>
                      </tr>

                      <% } %>
                    </table>
                    <br/><br/><br/>
                    <a href="./home.jsp" class="btn btn-primary">Go Back</a>
                    </h3>
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