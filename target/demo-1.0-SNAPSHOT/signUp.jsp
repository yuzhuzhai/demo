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

                                    <h2 class="text-center mb-5">Sign Up</h2>

                                    <form action="AuthControllerServlet" method="POST">

                                        <div class="form-outline mb-4">
                                            <div class="form-outline form-white">
                                                <input type="text" name="Name" class="form-control"/>
                                                <label class="form-label"
                                                >name</label>
                                            </div>
                                        </div>


                                        <div class="form-outline mb-4">
                                            <div class="form-outline form-white">
                                                <input type="text" name="Password" class="form-control"/>
                                                <label class="form-label"
                                                >password</label>
                                            </div>
                                        </div>

                                        <div class="form-outline mb-4">
                                            <div class="form-outline form-white">
                                                <input type="text" name="Role" class="form-control"/>
                                                <label class="form-label"
                                                >role</label>
                                            </div>
                                        </div>

                                        <div class="form-outline mb-4">
                                            <div class="form-outline form-white">
                                                <input type="int" name="ID" class="form-control"/>
                                                <label class="form-label"
                                                >id</label>
                                            </div>
                                        </div>
                                        <button type="submit" class="btn btn-primary btn-block mb-3">
                                            Sign Up
                                        </button>
                                    </form>
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

