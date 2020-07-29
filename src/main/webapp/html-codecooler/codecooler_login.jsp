<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="pl">
<head>
  <meta charset="utf-8">

  <title>Codecool student's page - login</title>
  <meta name="login" content="LoginSite">
  <link rel='icon' href='../favicon.ico' type='image/x-icon'>
  <link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@600&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="../css/login.css">

</head>
<body>
    <nav class="nav-bar nav-bar-codecooler">
        <img src="../assets/img/codequest_logo.svg" alt="logo" class="logo">
    </nav>
    <div class="content">
        <div class="login-container">
            <div class="form">

                <h2>Login to your account</h2>

                <div class="input-icons"> 
                    <i><img src="../assets/icons/login_icon.png" alt="icon" class="login-icon"> </i> 
                    <input class="input-field" type="text" placeholder="Your email"> 
                </div> 
                
                <div class="input-icons"> 
                    <i><img src="../assets/icons/password_icon.png" alt="icon" class="login-icon"> </i> 
                    <input class="input-field" type="password" placeholder="Password"> 
                </div> 

                <h5><a href="codecooler_reset.jsp">forgot your password?</a> </h5>
                
                <div class ="submit-button">
                    <input class="button" type ="submit" value ="Sign in" onClick="location.href='dashboard.jsp'">
                </div>
            </div>	
        </div>
    </div>

    <jsp:include page="../html-common/footer.html" />
</body>
</html>