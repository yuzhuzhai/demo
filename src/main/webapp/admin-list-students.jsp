<%@ page import="com.example.demo.student.model.Student" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8"/>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
	<meta http-equiv="x-ua-compatible" content="ie=edge"/>
	<title>Assignment 2</title>
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
	List<Student> theStudents =
			(List<Student>) request.getAttribute("STUDENT_LIST_BY_COURSE");
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

									<h2 class="text-center mb-5">Student list by course</h2>
									<table class="table">

										<thead>
										<tr>
											<th scope="col">Student First Name</th>
											<th scope="col">Student Last Name</th>
											<th scope="col">Student Email</th>
										</tr>
										</thead>
										<tbody>

										<% for (Student theStudent : theStudents) { %>

										<tr>
											<td><%= theStudent.getFirstName() %>
											</td>
											<td><%= theStudent.getLastName() %>
											</td>
											<td><%= theStudent.getEmail() %>
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








