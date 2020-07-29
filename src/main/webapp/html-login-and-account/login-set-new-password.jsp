<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Codecool - set new password</title>
    <meta name="login" content="LoginSite">
    <link rel='icon' href='../favicon.ico' type='image/x-icon'>
    <link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../css/login.css">
</head>

<body onload="checkPasswordForRegEx(); checkIfPasswordsAreEquals()">
    <nav class="nav-bar">
        <a href="login.jsp"> <img src="../assets/icons/codecool_logo_color.png" alt="logo" class="logo"></a>
    </nav>
    <div class="content">
        <div class="login-container">
            <div class="form">

                <h2>You're almost there!</h2>
                <h4>Enter the code we sent you and your new password:</h4>

                <div class="input-icons">
                    <i><img src="../assets/icons/account_icon.png" alt="icon" class="icon"> </i>
                    <input class="input-field input" type="text" placeholder="Enter the code">
                </div>


                <div class="input-icons">
                    <i><img src="../assets/icons/password_icon.png" alt="icon" class="icon"> </i>
                    <input class="input-field input passw-input" type="password" placeholder="Your password">
                    <div class="warning-password hidden">
                        <p>Your password is not valid.</p>
                    </div>
                </div>
                <div class="input-icons">
                    <i><img src="../assets/icons/password_icon.png" alt="icon" class="icon"> </i>
                    <input class="input-field input sec-passw" type="password" placeholder="Repeat your password">
                    <div class="warning-equals hidden">
                        <p>Your passwords are not equals.</p>
                    </div>
                </div>

                <div class="submit-button">
                    <input class="button reset" type="submit" value="Save and go to log in"
                        onclick="location.href='login.jsp';">
                </div>
            </div>
        </div>
    </div>
    <footer class="footer">

        <div class="footer-logo"> <img src="../assets/img/codecool_logo_white.png" alt="logo"> </div>

        <div class="footer-text">
            <p> ©2020 All rights reserved by Karolina, Michał, Michał and Rafał</p>

        </div>
    </footer>
    <script src="../js/input-validator.js"></script>

</body>

</html>