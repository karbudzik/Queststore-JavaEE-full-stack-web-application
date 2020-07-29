<%@ page import="model.Quest" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" onclick="return hideSubMenu()">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel='icon' href='../favicon.ico' type='image/x-icon'>
    <link rel="stylesheet" href="../css/quest_update.css">
    <title>Update quest</title>
</head>

<body>
    <jsp:include page="../html-common/cms-header.jsp" />

    <div class="container">
        <jsp:include page="../html-common/cms-navigation.jsp" />
        <div class="details-container">
            <h1>Quest's details</h1>
            <a href="/quests">&#60;- Back to the list</a>
            <p class="validation-message">${message}</p>

            <form class="quest-details" action="/quests/edit" method="post">
                <h2>Basic details</h2>
                <label for="quest-name">Name*:</label>
                <input name="quest-name" type="text" id="quest-name" value="${quest.getName()}">
                <p class="validation-message">${name_validation_message}</p>
                <label for="quest-description">Description*:</label>
                <textarea name="quest-description" id="quest-description">${quest.getDescription()}</textarea>
                <p class="validation-message">${description_validation_message}</p>
                <div class="properties-section">
                    <div class="picture">
                        <p>Picture: </p>
                        <img id="quest-logo" src="../assets/img/quests-img/${quest.getPictureUrl()}" alt="logo of quest"><br>
                        <a href="#"><img src="../assets/icons/change_picture.svg" alt=" ">Change picture</a> 
                    </div>
                    <div class=properties>
                        <label for="quest-value">Value (Number of coins student will get for the quest)*:</label><br>
                        <input name="quest-value" id="quest-value" type="text" value="${quest.getValue()}"><br>
                        <p class="validation-message">${value_validation_message}</p>
                        <label for="quest-type">Type (Basic or Extra):</label><br>
                        <select class= "type-selector" id="quest-type" name="quest-type">
                            <option value=${quest.getType().name()} selected>${quest.getType().name()}</option>
                            <option>Basic</option>
                            <option>Extra</option>
                        </select>
                        <p class="validation-message">${type_validation_message}</p>
                    </div>
                </div>
                <div class="lower-section">
                    <p>*- Fields marked like the need to be filled to add new entry</p>
                    <button class="btn" id="update-admin">Update</button>
                </div>
            </form>
        </div>
    </div>

    <jsp:include page="../html-common/footer.html" />
    <script>
        document.getElementsByClassName('quests-nav')[0].setAttribute('id', 'select-page');
    </script>
</body>

</html>