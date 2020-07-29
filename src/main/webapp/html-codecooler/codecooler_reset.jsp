<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="pl">
<head>
  <meta charset="utf-8">

  <title>Codecool password reset</title>
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

                <h2 class="form-top-text" >Forgot your password? </h2>

                <h4>Don't worry - we'll send you the code to reset it. Just type in your email:</h4>

                <div class="input-icons"> 
                    <i><img src="../assets/icons/login_icon.png" alt="icon" class="login-icon icon"> </i> 
                    <input class="input-field input" type="text" placeholder="Your email"> 
                </div> 
                <div class ="submit-button">
                    <input class="button reset" type ="submit" onClick="location.href='codecooler_reset_finalization.jsp'" value ="Send me the code">
                </div>
            </div>	
        </div>
    </div>

    <jsp:include page="../html-common/footer.html" />
</body>

</html>