<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" onclick="return hideSubMenu()">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel='icon' href='../favicon.ico' type='image/x-icon'>
    <link rel="stylesheet" href="../css/person_add_update.css">
    <title>Add new Admin</title>
</head>

<body>
    <jsp:include page="../html-common/cms-header.jsp" />

    <div class="container">
        <jsp:include page="../html-common/cms-navigation.jsp" />

        <div class="details-container">
            <h1>Add new ${type}</h1>
            <a href="/user-list?type=${type}">&#60;- Back to the list</a>
            <p class="validation-message">${message}</p>

            <form class="personal-details" action="/user/new" method="post">
                <h2>Personal details</h2>
                <label for="person-name">Name*:</label>
                <input type="text" name="person-name" id="person-name" placeholder="Enter your name">
                <p class="validation-message">${name_validation_message}</p>
                <label for="person-mail">Email*:</label>
                <input type="text" name="person-mail" id="person-mail" placeholder="Enter your email">
                <p class="validation-message">${email_validation_message}</p>
                <label for="person-city">City*:</label>
                <input type="text" name="person-city" id="person-city" placeholder="Enter your city">
                <p class="validation-message">${city_validation_message}</p>
                <div class="lower-section">
                    <p>*- Fields marked like that need to be filled to add new entry</p>
                    <button class="btn" id="add-new-admin">Add new ${type}</button>
                </div>
            </form>
        </div>
    </div>

    <jsp:include page="../html-common/footer.html" />
    <script>
        <%
        String permissions = (String) request.getAttribute("type");
        if(permissions.equals("admin")){
        %>
        document.getElementsByClassName('admins-nav')[0].setAttribute('id', 'select-page');
        <%
     }else{
     %>
        document.getElementsByClassName('mentors-nav')[0].setAttribute('id', 'select-page');
        <%
        }
        %>
    </script>
</body>

</html>