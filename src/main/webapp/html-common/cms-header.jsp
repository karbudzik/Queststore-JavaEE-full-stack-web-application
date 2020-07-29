<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Header</title>
    <link rel="stylesheet" href="../css/header.css">
    <script src="../js/dropdown.js" ></script>
</head>
<body>

<div class="header">
    <div class="left-position">
        <a href="/dashboard"> <img src="../assets/icons/codecool_logo_color.png" alt="logo" class="logo"></a>
    </div>
    <div class="right-position">
        <a id="name">${sessionScope['user'].getName()}</a>
        <a>&nbsp;&nbsp;|&nbsp;&nbsp;</a>
        <a id="role">${sessionScope['user'].getRole()}</a>
        <img class="right-position image" src="../assets/icons/user_icon.png" alt="user icon" onmouseover="return displayDropdown()">
        <div id="arrow-up">
        </div>
    </div>
    <div id="dropdown-content">
        <ul>
            <li>
                <a href="/cms-user/my-account">My account
                    <img src="../assets/icons/my_account_icon.svg" alt="my account icon"></a>
            </li>
            <li>
                <a href="/logout">Log out
                    <img src="../assets/icons/log_out_icon.svg" alt="log out icon"></a>
            </li>
        </ul>
    </div>
</div>
</body>
</html>