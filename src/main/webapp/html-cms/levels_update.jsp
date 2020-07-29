<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" onclick="return hideSubMenu()">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel='icon' href='../favicon.ico' type='image/x-icon'>
    <link rel="stylesheet" href="../css/person_add_update.css">
    <title>Update level details</title>
</head>

<body>
    <jsp:include page="../html-common/cms-header.jsp" />

    <div class="container">
        <jsp:include page="../html-common/cms-navigation.jsp" />

        <div class="details-container">
            <h1>Level's details</h1>
            <a href="/levels">&#60;- Back to the list</a>
            <form class="form" action="/levels/edit" method="POST">
            <div class="personal-details">
                <h2>Basic details:</h2>
                <label for="level-name">Name*:</label>
                <p class="error">${name_validation_message}</p>
                <input type="text" name ="level-name" id="level-name" value="${level.getName()}">

                <label for="level-description">Description*:</label>
                <p class="error">${description_validation_message}</p>
                <input id="level-description" name ="level-description" value="${level.getDescription()}">

                <div class="img-coins">
                    <div>
                        <p>Picture*:</p>
                        <img src="../assets/img/user-photo.svg" alt="Here should be uploaded image" class="thumbail-img">
                        <a href=""><img src="../assets/img/change-img.svg" alt="Change image icon" class="change-img-icon">Change picture:</a>
                    </div>
                    <div class="level-coins">
                        <label for="level-coins">Cost (number of coins to get)*:</label>
                        <p class="error">${value_validation_message}</p>
                        <input type="number"  name ="level-coins" id="level-coins" value="${level.getPrice()}">

                    </div>
                </div>
                <div class= "lower-section">
                    <p>*- Fields marked like the need to be filled to add new entry</p>
                    <button class="btn" id="update-level">Update level</button>
                </div>
            </div>
        </form>

        </div>
    </div>

    <jsp:include page="../html-common/footer.html" />
    <script>
        document.getElementsByClassName('levels-nav')[0].setAttribute('id', 'select-page');
    </script>
</body>

</html>