<%--
  Created by IntelliJ IDEA.
  User: zfq11
  Date: 11/17/2022
  Time: 8:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <link rel="stylesheet" href="./css/mdb.min.css" />
    <link rel="stylesheet" href="./css/style2.css" />
    <style type="text/css">
        .nav-item{
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
    <div class="bg-image h-100" style="background-image: url('https://mdbootstrap.com/img/Photos/new-templates/glassmorphism-article/img5.jpg');">
        <div class="mask d-flex align-items-center h-100">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-12 col-md-10 col-lg-7 col-xl-6">
                        <div class="card mask-custom">
                            <div class="card-body p-5 text-white">

                                <div class="my-4">

                                    <h2 class="text-center mb-5">Report System</h2>

                                    <!-- Pills navs -->
                                    <ul class="nav nav-pills nav-justified mb-3" id="ex1" role="tablist">
                                        <li class="nav-item" role="presentation">
                                            <button
                                                    class="nav-link active"
                                                    id="tab-login"
                                                    data-mdb-toggle="pill"
                                                    role="tab"
                                                    aria-controls="pills-login"
                                                    aria-selected="true"
                                                    onclick="openTab(event, 'Login')">
                                                Report 1
                                            </button>
                                        </li>
                                        <li class="nav-item" role="presentation">
                                            <button
                                                    class="nav-link"
                                                    id="tab-register"
                                                    data-mdb-toggle="pill"

                                                    role="tab"
                                                    aria-controls="pills-register"
                                                    aria-selected="false"
                                                    onclick="openTab(event, 'Register')">
                                                Report 2
                                            </button>
                                        </li>
                                    </ul>
                                    <!-- Pills navs -->

                                    <!-- Pills content -->
                                    <div class="tab-content">
                                        <div class="tab-pane" id="Login" role="tabpanel" aria-labelledby="tab-login">

                                            <form  method = "GET" action = "CourseControllerServlet">
                                                <input type="hidden" name="command" value="LIST" />

                                                <p class="text-center">Enter student ID</p>

                                                <!-- Email input -->
                                                <div class="form-outline mb-4">
                                                    <input type="number" name="studentID" id="loginName" class="form-control" />
                                                    <label class="form-label" for="loginName">ID</label>
                                                </div>

                                                <!-- Submit button -->
                                                <button type="submit" class="btn btn-primary btn-block mb-4">Check Student's courses</button>

                                                <!-- Register buttons -->
                                                <div class="text-center">
                                                    <p>Power by <a href="https://github.com/limoben/SOEN387_Assignment_1">Group</a></p>
                                                </div>
                                            </form>
                                        </div>
                                        <div class="tab-pane" id="Register" role="tabpanel" aria-labelledby="tab-register">
                                            <form method = "GET" action = "StudentControllerServlet">
                                                <input type="hidden" name="command" value="LIST_COURSE_BY_STUDENT" />

                                                <p class="text-center">Enter Course ID</p>

                                                <!-- Name input -->
                                                <div class="form-outline mb-4">
                                                    <input name="courseID" type="number" id="registerName" class="form-control" />
                                                    <label class="form-label" for="registerName">ID</label>
                                                </div>

                                                <!-- Submit button -->
                                                <button type="submit" class="btn btn-primary btn-block mb-3">Check Course' students</button>
                                                <div class="text-center">
                                                    <p>Power by <a href="https://github.com/limoben/SOEN387_Assignment_1">Group</a></p>
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
<script>
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
