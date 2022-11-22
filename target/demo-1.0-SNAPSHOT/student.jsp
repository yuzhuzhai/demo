<%--
  Created by IntelliJ IDEA.
  User: toby
  Date: 2022-11-20
  Time: 21:53
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

                                    <%
                                        String studentName =
                                                request.getSession().getAttribute("studentName").toString();
                                        if (request.getSession().getAttribute("studentName") != null) {
                                    %>
                                    <h2 class="text-center mb-5">Welcome, <%= studentName %>
                                    </h2>

                                    <div class="tab-pane" id="Login" role="tabpanel" aria-labelledby="tab-login">

                                        <form action="" method="GET" name="studentForm" id="form1">

                                            <p class="text-center" id="studentid">Enter student ID</p>

                                            <!-- Student ID input -->
                                            <div class="form-outline mb-4">
                                                <input type="number" id="loginName" name="stdID"
                                                       class="form-control" value="${Integer.parseInt(sessionScope.studentID)}" />
                                                <label class="form-label" for="loginName">Student ID</label>
                                            </div>
                                            <p class="text-center" id="errorMessage"></p>
                                            <p class="text-center">Select the semester</p>
                                            <link rel=" stylesheet" href="alignadd.css"/>
                                            <body>
                                            <div class=" main" id="radiobox">
                                                <input type="radio" id="first" name="semester" value="Fall">
                                                <div class="first" label for="first" p style="color:black;">Fall
                                                    Term
                                                </div>
                                                <input type="radio" id="second" name="semester" value="Winter">
                                                <div class="second" label for="second" p style="color:black;">Winter
                                                    Term
                                                </div>
                                                <input type="radio" id="third" name="semester" value="Summer">
                                                <div class="third" label for="third" p style="color:black;">Summer
                                                    Term
                                                </div>
                                            </div>
                                            </body>
                                            <button type="button" class="btn btn-primary btn-block mb-4"
                                                    onclick="addCourse()">Add a course
                                            </button>
                                            <button type="button" class="btn btn-primary btn-block mb-4"
                                                    onClick="dropCourse()">Drop a course
                                            </button>
                                        </form>
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
        </div>
    </div>
    </div>
</section>

</body>
<script type="text/javascript">
    function addCourse() {
        let form = document.getElementById("form1");
        let a = form.firstElementChild;
        if (a.id === "studentid") {
            document.studentForm.action = "StudentControllerServlet";
            document.studentForm.method = "GET";
            let p = document.getElementById("studentid");
            let input = document.createElement("input");
            input.setAttribute("type", "hidden");
            input.setAttribute("name", "command");
            input.setAttribute("value", "LOAD");
            input.setAttribute("id", "hiddenInput");
            form.insertBefore(input, p);
            console.log(form.firstElementChild);
        }
        document.studentForm.submit();
    }

    function dropCourse() {
        let form = document.getElementById("form1");
        let a = form.firstElementChild;
        if (a.id === "studentid") {
            document.studentForm.action = "StudentControllerServlet";
            document.studentForm.method = "GET";
            let p = document.getElementById("studentid");
            let input = document.createElement("input");
            input.setAttribute("type", "hidden");
            input.setAttribute("name", "command");
            input.setAttribute("value", "DROP");
            input.setAttribute("id", "hiddenInput");
            form.insertBefore(input, p);
            console.log(form.firstElementChild);
        }
        document.studentForm.submit();
    }

    function validation(action) {
        var input = document.getElementById("loginName");
        var form = document.getElementById("form1");
        var radiobox1 = document.getElementById("first");
        var radiobox2 = document.getElementById("second");
        var radiobox3 = document.getElementById("third");
        var radioboxArray = [radiobox1, radiobox2, radiobox3];
        var checked = false;
        for (var i = 0; i < radioboxArray.length; i++) {
            if (radioboxArray[0].checked) {
                checked = true;
            }
        }
        if (!checked) {
            document.getElementById("errorMessage").innerHTML = "Please select a semester";
            return;
        }

        if (!input.checkValidity()) {
            document.getElementById("errorMessage").innerHTML = input.validationMessage;
        } else {
            form.action = action;
            form.submit();
        }
    }
</script>
</html>
