<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE-edge">

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>AUCA | CourseDefinition</title>
<link rel="shortcut icon" href="images/favicon.png"/>
<style>
    body, html {
        margin: 0;

        padding: 0;
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        background-color: #dbd6d6;
    }

    .sidebar {
        height: 100vh;
        width: 250px;
        position: fixed;
        z-index: 10;

        top: 0;
        left: 0;
        background-color: #131E3A;
        overflow-x: hidden;
        padding-top: 20px;
    }

    .sidebar a {
        padding: 10px 15px;
        text-decoration: none;

        font-size: 18px;
        color: white;
        display: block;
    }

    .sidebar a:hover, .sidebar a.active {
        background-color: #0056b3;
    }

    .main {
        margin-left: 250px;
        padding: 20px;

        background-color: #fff;
        min-height: 100vh;
    }

    form {
        background-color: #fff;
        padding: 20px;

        border-radius: 8px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        max-width: 500px;
        margin: auto;
    }

    label {
        display: block;
        margin-bottom: 10px;
        color: #131E3A;
    }

    input[type=text], input[type=submit] {
        width: 100%;
        padding: 10px;
        margin: 8px 0;

        display: inline-block;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
    }

    input[type=submit] {
        background-color: #131E3A;
        color: white;

        cursor: pointer;
    }

    input[type=submit]:hover {
        background-color: #0d1a26;
    }

    footer {
        text-align: center;
        padding: 20px;
        background-color: #131E3A;
        color: white;
        position: fixed;
        bottom: 0;
        left: 250px;
        width: calc(100% - 250px);
    }
</style>
</head>

<body>

<div class="sidebar">
    <a href="adminDashboard.jsp">Back</a>
    <a href="Report.jsp">Report</a>
    <a href="LogoutServlet" class="active">Logout</a>
</div>

<div class="main">
    <h2>Add New Course</h2>
    <form action="coursedefinition" method="post">
        <div class="form-group">

            <label for="code">Course Code</label>
            <input type="text" id="code" placeholder="Enter Course Code" name="code">
        </div>

        <div class="form-group">
            <label for="name">Course Name</label>
            <input type="text" id="name" placeholder="Enter Course Name" name="name">
        </div>

        <div class="form-group">
            <label for="description">Description</label>
            <input type="text" id="description" placeholder="Enter Description" name="description">
        </div>            
        
        <div class="form-group submit-btn">
            <input type="submit" value="Add">
        </div>
    </form>
</div>

<footer>
    <p>Copyright (C) - 2024, with love By Ally</p>
</footer>

</body>
</html>
