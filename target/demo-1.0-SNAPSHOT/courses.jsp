<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.demo.course.model.Course" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
        <h1 class="justify-content-center d-flex" style="color: white"> <strong style="color: orange">
            ${THE_STUDENT.firstName}
        </strong>, Welcome to Courses page! </h1>
        <h3 class="justify-content-center d-flex" style="color: white">All courses for this &nbsp<strong style="color: orange">
            ${THE_SEMESTER}
        </strong>&nbsp semester are displayed on this page.</h3>

        <div class="mask d-flex align-items-center h-100" style="background-color: rgba(0,0,0,.25);">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-12">
                        <div class="card bg-dark shadow-2-strong overflow-auto" style="height: 35rem;">
                            <div class="card-body">
                                <div class="table-responsive">
                                    <%
                                        String theSemester= (String) request.getAttribute("THE_SEMESTER");
                                        List<Course> theCourses = (List<Course>) request.getAttribute("COURSE_LIST");
                                        List<Course> theEnrolledCourses = (List<Course>) request.getAttribute("ENROLLED_COURSE_LIST");
                                        // check if the student already enrolled the course, if so, don't add it in theSemesterCourse list
                                        for (int i = 0, len = theCourses.size(); i < len; i++) {
                                            for (int j = 0, len2 = theEnrolledCourses.size(); j < len2; j++) {
                                                if(theCourses.get(i).getID() == (theEnrolledCourses.get(j).getID())){
                                                    theCourses.remove(i);
                                                    len--;
                                                    i--;
                                                    break;
                                                }
                                            }
                                        }
                                    %>
                                    <h3 class="text-center mb-5" style="color: white">You can select at most <%=(5 - theEnrolledCourses.size())%> courses from the following list:</h3>
                                    <table id="courseTable" class="table"><thead>
                                    <%-- If student already selected 5 courses before, he/she does not allowed to select course anymore --%>
                                    <c:if test="${fn:length(ENROLLED_COURSE_LIST)>=5}">
                                        <div class="card-body align-items-center d-flex flex-column">
                                            <h5 class="card-title" style="color: grey">Adding fail</h5>
                                            <p class="card-text" style="color: grey">You have already 5 courses for this semester.</p>
                                            <a href="home.jsp" class="btn btn-primary">Go Back</a>
                                        </div>
                                    </c:if>
                                    <c:if test="${fn:length(ENROLLED_COURSE_LIST)<5}">
                                    <tr>
                                        <th></th>
                                        <th>ID</th>
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
                                        <% for (Course currentCourse : theCourses) { %>
                                        <tr>
                                            <td>
                                                <input type="checkbox" name="selectCourse" onclick="checkRegisterTime(this)"></input>
                                            </td>
                                            <td> <%= currentCourse.getID() %> </td>
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
                                        </c:if>
                                    </table>
                                    <br /><br /><br />
                                    <div>
                                        <h3 class="justify-content-center d-flex" style="color: white">Course Cart:</h3>
                                        <form class="align-items-center d-flex flex-column" id="registerForm" onsubmit="isDisabled()" method="post" action="CourseControllerServlet">
                                            <input type="hidden" name="command" value="REGISTER" />
                                            <input id="studentID" name="stdID" type="text" value="${THE_STUDENT.id}" readonly />
                                            <input type="hidden" id="semester" name="semester" type="text" value="${THE_SEMESTER}" readonly/>
                                            <input id="registerButton" type="submit" value="Register"/>
                                        </form>
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


<script>
    let courseArray = [];
    let i = 0;
    let registerForm = document.getElementById('registerForm');
    let registerButton = document.getElementById('registerButton');
    let numOfCoursesCanEnroll = <%=(5 - theEnrolledCourses.size())%>;
    function isSelect(obj){
        let rowFirstTd = obj.parentNode; // get first td node (which include <input> element)
        let row = rowFirstTd.parentNode; // get tr node
        let courseID = row.children[1].innerHTML; // get course ID
        if(obj.checked) {
            // registerButton.removeAttribute("disabled");
            if(courseArray.length < numOfCoursesCanEnroll){
                if(!courseArray.includes(courseID)){
                    courseArray[i] = courseID;
                    i++;
                    let input = document.createElement("input");
                    input.setAttribute("id",courseID);
                    input.setAttribute("type","text");
                    input.setAttribute("name","courseID");
                    input.setAttribute('value',courseID);
                    input.setAttribute('readonly', 'true');
                    registerForm.insertBefore(input, registerButton);
                } else{
                    console.log("already added!");
                }
                console.log(courseArray);
            } else {
                alert("Too many courses! You cannot register more than 5 courses for a semester.");
                console.log("Already have 5 courses.");
            }
        } else {
            if(courseArray.includes(courseID)){
                i--;
                courseArray = courseArray.filter(element => element!==courseID);
                console.log(courseArray);
                let cancelInput = document.getElementById(courseID.toString());
                registerForm.removeChild(cancelInput);
            }
            console.log("canceled.");
        }
    }

    function checkRegisterTime(obj){
        let n = "2022-09-01";
        // let d =  new Date();
        // let n = d.toLocaleDateString(); // get local time
        let rowFirstTd = obj.parentNode; // get first td node (which include <input> element)
        let row = rowFirstTd.parentNode; // get tr node
        let courseStartDate = row.children[8].innerHTML;
        let startDate = new Date(courseStartDate);
        let tempTime = startDate.getTime() + 8*(24*3600*1000);
        let allowEnrollDate = new Date(tempTime);
        if((new Date(n.replace(/-/g,"\/")))>allowEnrollDate){
            obj.click(()=>{
                return false;
            });
            alert("It is too late to register this course in the semester.");
        } else {
            isSelect(obj);
        }
    }

    function isDisabled(){
        if(courseArray.length === 0){
            event.preventDefault();
            alert("Please select course before register.");
        } else {
            return true;
        }
    }
</script>
</body>
</html>