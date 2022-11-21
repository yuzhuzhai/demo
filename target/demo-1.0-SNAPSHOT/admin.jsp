<%@ page import="com.example.demo.auth.model.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <title>Assignment 2</title>
    <!-- Font Awesome -->
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"/>
    <!-- Google Fonts Roboto -->
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700;900&display=swap"/>
    <!-- MDB -->
    <link rel="stylesheet" href="./css/mdb.min.css"/>
    <link rel="stylesheet" href="./css/style2.css"/>
    <style type="text/css">
        .nav-item {
            flex-basis: 0;
            flex-grow: 1;
            text-align: center;
            justify-content: space-around;
            display: flex;
        }
    </style>
</head>

<%
    List<User> theUser =
            (List<User>) request.getAttribute("USER");
%>
<!-- MDB -->
<script type="text/javascript" src="./js/mdb.min.js"></script>
<!-- Custom scripts -->
<script type="text/javascript"></script>
<body>
<section class="intro">
    <div class="bg-image h-100"
         style="background-image: url('https://mdbootstrap.com/img/Photos/new-templates/glassmorphism-article/img5.jpg');">
        <div class="mask d-flex align-items-center h-100">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-12 col-md-10 col-lg-7 col-xl-6">
                        <div class="card mask-custom">
                            <div class="card-body p-5 text-white">
                                <div class="my-4">
                                    <%
                                        if (request.getSession().getAttribute("adminName") != null )
                                            { %>
                                    <h2 class="text-center mb-5">Welcome, <%=request.getSession().getAttribute("adminName") %></h2>

                                        <div class="tab-pane" id="Register" role="tabpanel"
                                             aria-labelledby="tab-register">
                                            <form action="CourseControllerServlet" method="POST">
                                                <input type="hidden" name="command" value="ADD"/>

                                                <p class="text-center">Add new Course Information</p>


                                                <div class="form-outline mb-4">
                                                    <div class="form-outline form-white">
                                                        <input name="ID" type="text" class="form-control"/>
                                                        <label class="form-label">CourseID</label>
                                                    </div>
                                                </div>

                                                <!-- Name input -->
                                                <div class="form-outline mb-4">
                                                    <div class="form-outline form-white">
                                                        <input name="Title" type="text" class="form-control"/>
                                                        <label class="form-label">Title</label>
                                                    </div>
                                                </div>

                                                <!-- Username input -->
                                                <div class="form-outline mb-4">
                                                    <div class="form-outline form-white">
                                                        <input name="Semester" type="text" class="form-control"/>
                                                        <label class="form-label"
                                                        >Semester</label>
                                                    </div>
                                                </div>

                                                <!-- Email input -->
                                                <div class="form-outline mb-4">
                                                    <div class="form-outline form-white">
                                                        <input name="Days" type="text" class="form-control"/>
                                                        <label class="form-label">Days</label>
                                                    </div>
                                                </div>

                                                <!-- Password input -->
                                                <div class="form-outline mb-4">
                                                    <div class="form-outline form-white">
                                                        <input name="Time" type="text" id="registerPassword"
                                                               class="form-control"/>
                                                        <label class="form-label" for="registerPassword">Time</label>
                                                    </div>
                                                </div>

                                                <!-- Repeat Password input -->
                                                <div class="form-outline mb-4">
                                                    <div class="form-outline form-white">
                                                        <input name="Instructor" type="text" class="form-control"/>
                                                        <label class="form-label">Instructor</label>
                                                    </div>
                                                </div>

                                                <div class="form-outline mb-4">
                                                    <div class="form-outline form-white">
                                                        <input name="Room" type="text" class="form-control"/>
                                                        <label class="form-label"
                                                        >Room</label>
                                                    </div>
                                                </div>

                                                <div class="form-outline mb-4">
                                                    <div class="form-outline form-white">
                                                        <input name="StartDate" type="text" class="form-control"/>
                                                        <label class="form-label">Start
                                                            Date</label>
                                                    </div>
                                                </div>

                                                <div class="form-outline mb-4">
                                                    <div class="form-outline form-white">
                                                        <input name="EndDate" type="text" class="form-control"/>
                                                        <label class="form-label">End
                                                            Date</label>
                                                    </div>
                                                </div>

                                                <div class="form-outline mb-4">
                                                    <div class="form-outline form-white">
                                                        <input name="AdminId" type="text" class="form-control"/>
                                                        <label class="form-label">Admin
                                                            ID</label>
                                                    </div>
                                                </div>

                                                <!-- Submit button -->
                                                <button type="submit" class="btn btn-primary btn-block mb-3">Add
                                                    course
                                                </button>
                                                <div class="text-center">
                                                    <p>Go to <a href="./report.jsp">Report
                                                        System</a></p>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                            </div>
                            <% } else { %>
                            <div class="text-center">
                                <p>Go Back <a href="./logInForAll.jsp">Login page</a></p>
                            </div>

                            <% } %>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>

