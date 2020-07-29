<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" onclick="return hideSubMenu()">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel='icon' href='../favicon.ico' type='image/x-icon'>
    <link rel="stylesheet" href="../css/teams_add_new.css">
    <title>Add new team</title>
</head>

<body>
    <jsp:include page="../html-common/cms-header.jsp" />

    <div class="container">
        <jsp:include page="../html-common/cms-navigation.jsp" />

        <div class="details-container">
            <h1>Add new team</h1>
            <a href="/teams">&#60;- Back to the list</a>
            <p class="validation-message">${message}</p>
            <form class="form" action="" method="POST">
                <div class="personal-details">
                    <h2>Basic details</h2>
                        <div class ="details">
                            <label for="team-name">Name*: </label><br>
                            <input type="text" id="team-name" name="team-name" placeholder="">
                            <p class="validation-message">${name_validation_message}</p>
                            <label for="team-name">City*: </label><br>
                            <input type="text" id="team-city" name="team-city" placeholder="">
                            <p class="validation-message">${city_validation_message}</p>
                            <label for="team-name">Start date*: </label><br>
                            <input type="text" id="team-start-date" name="team-start-date" placeholder="">
                            <p class="validation-message">${start_date_validation_message}</p>
                            <div class="lower-section">
                                <p>*- Fields marked like these need to be filled to add new entry</p>
                                <button class="btn" id="update-admin">Add new</button>
                            </div>
                        </div>
                </div>
            </form>
        </div>
    </div>

    <jsp:include page="../html-common/footer.html" />
    <script>
        document.getElementsByClassName('teams-nav')[0].setAttribute('id', 'select-page');
    </script>
</body>
</html>