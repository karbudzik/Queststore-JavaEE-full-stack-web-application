<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <meta charset="utf-8">

  <title>Codecool - password reset</title>
  <meta name="login" content="LoginSite">
  <link rel='icon' href='../favicon.ico' type='image/x-icon'>
  <link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@600&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="../css/login.css">
</head>

<body onload="clickBtnResetPassw();checkIfPasswordsAreEquals()">
    <nav class="nav-bar">
       <a href="login.jsp"> <img src="../assets/icons/codecool_logo_color.png" alt="logo" class="logo"></a>
    </nav>
    <div class="content">
        <div class="login-container">
            <div class="form">

                <h2 class="form-top-text">Forgot your password? </h2>

                <h4>Don't worry - we'll send you the code to reset it. Just type in your email:</h4>

                <div class="input-icons">
                    <i><img src="../assets/icons/login_icon.png" alt="icon" class="icon"> </i>
                    <input class="input-field input email-input" type="text" placeholder="Your email">
                    <div class="warning warning-email hidden">
                        <p>Email is not valid</p>
                    </div>
                </div>
                <div class="submit-button">
                    <input class="button reset" type="submit" value="Send me the code"
                        onclick="location.href='login-set-new-password.jsp';">
                </div>
            </div>
        </div>
    </div>

    <footer>
        <div class="footer">
            <div class="footer-logo"> <img src="../assets/img/codecool_logo_white.png" alt="logo"> </div>
            <div class="footer-text">
                <p> ©2020 All rights reserved by Karolina, Michał, Michał and Rafał</p>
            </div>
        </div>
    </footer>
    <script src="../js/input-validator.js"></script>
</body>

</html>