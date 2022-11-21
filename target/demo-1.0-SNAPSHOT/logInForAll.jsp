<%--
  Created by IntelliJ IDEA.
  User: zfq11
  Date: 11/11/2022
  Time: 5:43 PM
  To change this template use File | Settings | File Templates.
--%>
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

                                    <h2 class="text-center mb-5">Log In</h2>

                                    <!-- Pills navs -->
                                    <ul class="nav nav-pills nav-justified mb-3" id="ex1" role="tablist">
                                        <li class="nav-item" role="presentation">
                                            <button class="nav-link active"
                                                    id="tab-login"
                                                    data-mdb-toggle="pill"
                                                    role="tab"
                                                    aria-controls="pills-login"
                                                    aria-selected="true"
                                                    onclick="openTab(event, 'Login')">
                                                Student
                                            </button>
                                        </li>
                                        <li class="nav-item" role="presentation">
                                            <button class="nav-link"
                                                    id="tab-register"
                                                    data-mdb-toggle="pill"
                                                    role="tab"
                                                    aria-controls="pills-register"
                                                    aria-selected="false"
                                                    onclick="openTab(event, 'Register')">
                                                Admin
                                            </button>
                                        </li>
                                    </ul>
                                    <!-- Pills navs -->
                                    <!-- Pills content -->
                                    <div class="tab-content">
                                        <div class="tab-pane" id="Login" role="tabpanel" aria-labelledby="tab-login">

                                            <form action="AuthControllerServlet" method="GET" name="studentForm"
                                                  id="form1">
                                                <input type="hidden" name="command" value="STUDENT"/>
                                                <p class="text-center">Login as a student</p>

                                                <!-- Student ID input -->
                                                <div class="form-outline mb-4">
                                                    <input type="number" name="StdID"
                                                           class="form-control" required/>
                                                    <label class="form-label">Student ID</label>
                                                </div>

                                                <div class="form-outline mb-4">
                                                    <input type="text" name="Name"
                                                           class="form-control" required/>
                                                    <label class="form-label">Student Name</label>
                                                </div>

                                                <div class="form-outline mb-4">
                                                    <input type="text" name="Password"
                                                           class="form-control" required/>
                                                    <label class="form-label">Password</label>
                                                </div>

                                                <button type="submit" class="btn btn-primary btn-block mb-3">Add
                                                    course
                                                </button>
                                                <div class="text-center">
                                                    <p>Don't have account? <a href="./signUp.jsp">Sign Up</a></p>
                                                </div>
                                            </form>
                                        </div>

                                        <div class="tab-pane" id="Register" role="tabpanel"
                                             aria-labelledby="tab-register">
                                            <form action="AuthControllerServlet" method="GET">
                                                <input type="hidden" name="command" value="ADMIN"/>

                                                <p class="text-center">Log In as a admin</p>


                                                <div class="form-outline mb-4">
                                                    <div class="form-outline form-white">
                                                        <input name="adminID" type="number" class="form-control"/>
                                                        <label class="form-label">admin ID</label>
                                                    </div>
                                                </div>

                                                <!-- Name input -->
                                                <div class="form-outline mb-4">
                                                    <div class="form-outline form-white">
                                                        <input name="Name" type="text" class="form-control"/>
                                                        <label class="form-label">Name</label>
                                                    </div>
                                                </div>

                                                <!-- Username input -->
                                                <div class="form-outline mb-4">
                                                    <div class="form-outline form-white">
                                                        <input name="Password" type="text" class="form-control"/>
                                                        <label class="form-label"
                                                        >Password</label>
                                                    </div>
                                                </div>

                                                <!-- Submit button -->
                                                <button type="submit" class="btn btn-primary btn-block mb-3">
                                                    LogIn
                                                </button>
                                                <div class="text-center">
                                                    <p>Do't have account? <a href="./signUp.jsp">Sign Up</a></p>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                    <!-- Pills content -->

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
<script type="text/javascript">

    function openTab(evt, name) {
        var i, tabcontent, tablinks;
        tabcontent = document.getElementsByClassName("tab-pane");
        for (i = 0; i < tabcontent.length; i++) {
            tabcontent[i].style.display = "none";
        }
        tablinks = document.getElementsByClassName("nav-link");
        for (i = 0; i < tablinks.length; i++) {
            tablinks[i].className = tablinks[i].className.replace(" active", "");
        }
        document.getElementById(name).style.display = "block";
        evt.currentTarget.className += " fade show active";
    }
</script>
</html>

