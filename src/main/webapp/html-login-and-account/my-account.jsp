<%@ page import="model.CMSUser" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" onclick="return hideSubMenu()">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My account</title>
    <link rel='icon' href='../favicon.ico' type='image/x-icon'>
    <link rel="stylesheet" href="../css/my-account.css">
</head>

<body onload="checkName();checkEmail();checkPasswordForRegEx();checkIfPasswordsAreEquals();">
    <jsp:include page="../html-common/cms-header.jsp" />

    <div class="container">
        <jsp:include page="../html-common/cms-navigation.jsp" />

        <div id="content">
            <h1 id="label">My Account</h1>
            <div id="information"> 
                <h2 id="label-profil">Profile Information</h2>
                <form  action="/cms-user/my-account" method="post" >
                    <input type="hidden" name="action" value="personal-information">
                    <label>Name*:</label><br>
                    <input name="person-name" type="text" id="name-and-surname" class="name-surname" value="${sessionScope['user'].getName()}"><br>
                    <div class="warning hidden">
                        <p>Your name is invalid</p>
                    </div>
                    <label>Email*:</label><br>
                    <input name="person-mail" type="text" id="e-mail" class="email-input" value="${sessionScope['user'].getEmail()}" ><br>
                    <div class="warning-email hidden">
                        <p>Your mail is invalid</p>
                    </div>

                <div id="information-bottom">
                    <div id="picture">
                        <p>Picture:</p>
                        <img src="${sessionScope['user'].getPictureURL()}" alt="profile photo"><br>
                    </div>
                    <a href="#"> <img src="../assets/icons/change_picture.svg" alt="change photo">Change picture</a>
                    <p id="role-content">Role: ${sessionScope['user'].getRole()}</p>
                    <button type="submit" id="btn-update">Update</button>
                </div>
                </form>
            </div>

                <div id="change-password">
                <h2 id="label-password">Change Password</h2>
                <form action="/cms-user/my-account" method="post">
                    <input type="hidden" name="action" value="change-password">
                    <label>Old password*:</label><br>
                    <input type="password" id="old-password" name="old-password"><br>
                    <div class="warning-old-password${invalidOldPassword}">
                        <p>You inserted invalid password</p>
                    </div>
                    <label>New password*:</label><br>
                    <input name = "new-password" type="password" id="new-password" class="passw-input"><br>
                    <div class="warning-password hidden">
                        <p>Your password is invalid. The password have to contain 8 characters, minimum one special symbol,
                            minimum one small and big letter and minimum one digit</p>
                    </div>
                    <label>Repeat new password*:</label><br>
                    <input type="password" id="repeat-new-password" class="sec-passw"><br>
                    <div class="warning-equals hidden">
                        <p>Your passwords are not equals.</p>
                    </div>

                <div id="change-password-button" class="button">
                    <button disabled id="password-change">Change password</button>
                </div>
                </form>
            </div>
        </div>
    </div>

    <jsp:include page="../html-common/footer.html" />
    <script src="../js/input-validator.js"></script>
</body>

</html>