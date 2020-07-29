<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error has occured!</title>
    <link rel="stylesheet" href="../css/error.css">
</head>
<body>

<div class="header">
    <div class="left-position">
        <a href="/dashboard"> <img src="../assets/icons/codecool_logo_color.png" alt="logo" class="logo"></a>
    </div>
</div>

<div class="container">
    <div class="main-content-container">
        <h1>Oopss, something</h1>
        <h1>went wrong!</h1>
        <div class="messages">
            <p>We’re sorry but the page you’re requesting is not available or some other problem occurred.</p>
            <p>Please contact our staff to learn more.</p>
            <br>${error_message}
        </div>
        <button class="btn" onclick="location.href='/dashboard'">Back to dashboard</button>
    </div>
    <div class="graphic">
        <img src="../assets/icons/power.png" alt="logo" class="logo">
    </div>
</div>

<footer>
    <div class="footer">
        <img src="../assets/icons/codecool_logo_white.png" alt="Codecool logo">
        <p>©2020 - All rights reserved by Karolina, Michał, Michał and Rafał</p>
    </div>
</footer>

</body>
</html>