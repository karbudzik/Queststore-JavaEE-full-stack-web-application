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
                <h2>You're almost there!</h2>
                <h4>Enter the code we sent you and your new password:</h4>

                <div class="input-icons"> 
                    <i><img src="../assets/icons/account_icon.png" alt="icon" class="login-icon icon"> </i> 
                    <input class="input-field input" type="text" placeholder="Enter the code"> 
                </div> 


                <div class="input-icons"> 
                    <i><img src="../assets/icons/password_icon.png" alt="icon" class="login-icon icon"> </i> 
                    <input class="input-field input" type="password" placeholder="Your password"> 
                </div> 
                <div class="input-icons"> 
                    <i><img src="../assets/icons/password_icon.png" alt="icon" class="login-icon icon"> </i> 
                    <input class="input-field input" type="password" placeholder="Repeat your password"> 
                </div> 
                
                <div class ="submit-button">
                    <input class="button reset" type ="submit" onClick="location.href='codecooler_login.jsp'" value ="Save and go to log in">
                </div>
            </div>	
        </div>
    </div>

    <jsp:include page="../html-common/footer.html" />
</body>
</html>