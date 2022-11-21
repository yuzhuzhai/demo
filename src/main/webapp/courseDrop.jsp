<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.demo.course.model.Course" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta http-equiv="x-ua-compatible" content="ie=edge" />
    <title>Assignment 1</title>
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
        <h3 class="justify-content-center d-flex" style="color: white">Your current courses list for this &nbsp<strong style="color: orange">
            ${THE_SEMESTER}
        </strong>&nbsp semester are displayed on this page.</h3>

        <div class="mask d-flex align-items-center h-100" style="background-color: rgba(0,0,0,.25);">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-12">
                        <div class="card bg-dark shadow-2-strong overflow-auto" style="height: 35rem;">
                            <div class="card-body">
                                <div class="table-responsive">
                                    <h3 class="text-center mb-5" style="color: white">Your current courses list for this semester: </h3>
                                        <%
                                            String theSemester= (String) request.getAttribute("THE_SEMESTER");
                                            List<Course> theCourses = (List<Course>) request.getAttribute("COURSE_LIST");
                                            for (int i = 0, len = theCourses.size(); i < len; i++) {
                                                if(!theCourses.get(i).getSemester().equals(theSemester)){
                                                    theCourses.remove(i);
                                                    len--;
                                                    i--;
                                                }
                                            }
                                        %>
                                        <table id="studentCourseTable" class="table">
                                            <thead>
                                                <tr>
                                                <th></th>
                                                <th>Course ID</th>
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
                                                    <input type="checkbox" name="selectCourse" onclick="isSelect(this)"></input>
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
                                        </table>

                                        <br /><br /><br />
                                        <div>
                                            <h3 class="justify-content-center d-flex" style="color: white">Drop Course:</h3>
                                            <form class="align-items-center d-flex flex-column" id="dropForm" onsubmit="isDisabled()" method="post" action="CourseControllerServlet">
                                                <input type="hidden" name="command" value="DROP" />
                                                <input type="hidden" name="semester" value="${THE_SEMESTER}" />
                                                <input id="studentID" name="stdID" type="text" value="${THE_STUDENT.id}" readonly />
                                                <input id="dropButton" type="submit" value="Drop"/>
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
    let registrationIDArray = [];
    let i = 0;
    let dropForm = document.getElementById('dropForm');
    let dropButton = document.getElementById('dropButton');
    function isSelect(obj){
        let rowFirstTd = obj.parentNode; // get first td node (which include <input> element)
        let row = rowFirstTd.parentNode; // get tr node
        let courseID = row.children[1].innerHTML; // get course ID
        let endDate = new Date(row.children[9].innerHTML); // get end date
        let today = new Date();
        console.log(endDate)

        if(obj.checked) {
            dropButton.removeAttribute("disabled");
            if(registrationIDArray.length < 5){
                if(!registrationIDArray.includes(courseID) && today < endDate){
                    registrationIDArray[i] = courseID;
                    i++;
                    let input = document.createElement("input");
                    input.setAttribute("id",courseID);
                    input.setAttribute("type","text");
                    input.setAttribute("name","courseID");
                    input.setAttribute('value',courseID);
                    input.setAttribute('readonly', 'true');
                    dropForm.insertBefore(input, dropButton);
                } else{
                    alert("Course ended, cannot be dropped!");
                }
                console.log(registrationIDArray);
            } else {
                console.log("Already have 5 courses.");
            }
        } else {
            if(registrationIDArray.includes(courseID)){
                i--;
                registrationIDArray = registrationIDArray.filter(element => element!==courseID);
                console.log(registrationIDArray);
                let cancelInput = document.getElementById(courseID.toString());
                dropForm.removeChild(cancelInput);
            }
            console.log("canceled.");
        }
    }

    function isDisabled(){
        if(registrationIDArray.length === 0){
            event.preventDefault();
            alert("Please select course before drop.");
        } else {
            return true;
        }
    }
</script>
</body>
</html>