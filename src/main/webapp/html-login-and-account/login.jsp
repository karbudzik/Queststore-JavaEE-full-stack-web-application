<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Codecool</title>
  <meta name="login" content="LoginSite">
  <link rel='icon' href='../favicon.ico' type='image/x-icon'>
  <link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@600&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="../css/login.css">
</head>

<body onload="passwordClick()">
    <nav class="nav-bar">
        <a href="login.jsp"><img src="../assets/icons/codecool_logo_color.png" alt="logo" class="logo"></a>
    </nav>
    <div class="content">
        <div class="login-container" >
            <form class="form" action="/CMSUserLogin" method="POST">

                <h2>Login to your account</h2>

                <div class="input-icons">
                    <i><img src="../assets/icons/login_icon.png" class="login-icon" alt="icon"> </i>
                    <input class="input-field email-input" id="email" name="email" type="text" placeholder="Your email">
                    <div class="warning-email hidden">
                        <p>Email is not valid</p>
                    </div>
                </div>

                <div class="input-icons">
                    <i><img src="../assets/icons/password_icon.png" class="login-icon" alt="icon"> </i>
                    <input class="input-field passw-input" name="password" type="password" placeholder="Password">
                </div>

                <h5><a href="login-password-reset.jsp">forgot your password?</a> </h5>

                ${wrongLogIn}

                <div class="submit-button">
                    <button  class="button" type="submit" onclick="return launchPage()">Sign in</button>
                </div>
            </form>
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

    <script>
        function launchPage() {
            // window.open("../html-cms/dashboard_admin.jsp", "_self");
        }
        // tutaj prowadzi na sztywno do konkretnego dashboardu. Później będzie to zależało od danych usera
        // jeśli chcecie wyświetlać odpowiedni header i nav (w zależności od danych usera) - musicie
        // zmienić "admin" na "mentor" w plikach cms-header.jsp i cms-navigation.jsp
    </script>
</body>

</html>