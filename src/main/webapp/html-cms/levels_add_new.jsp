<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" onclick="return hideSubMenu()">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel='icon' href='../favicon.ico' type='image/x-icon'>
    <link rel="stylesheet" href="../css/person_add_update.css">
    <title>Levels add new</title>
</head>

<body>
    <jsp:include page="../html-common/cms-header.jsp" />

    <div class="container">
        <jsp:include page="../html-common/cms-navigation.jsp" />

        <div class="details-container">
            <h1>Add new level</h1>
      -      <a href="/levels">&#60;- Back to the list</a>
            <form class="form" action="/levels/add" method="POST">
                <input type="hidden" id = "insert" name = "insert" action = "insert" value="insert">

            <div class="personal-details">
                <h2>Basic details:</h2>
                <label for="level-name">Name*:</label>
                <div class="error"> <p>${name_validation_message}</p> </div>
                <input type="text" id="level-name" name="level-name">
                <label for="level-description">Description*:</label>
                <p class="error">${description_validation_message}</p>
                <input type="text" id="level-description" name="level-description">
                <div class="img-coins">
                    <div>
                        <p>Picture*:</p>
                        <a href=""><img src="../assets/img/change-img.svg" alt="Add image icon" class="change-img-icon">Add picture:</a>
                    </div>
                    <div class="level-coins">
                        <label for="level-coins">Cost (number of coins to get)*:</label>
                        <p class="error">${value_validation_message}</p>
                        <input type="number" id="level-coins" name="level-coins">
                    </div>
                </div>
                <div class="lower-section">
                    <p>*- Fields marked like the need to be filled to add new entry</p>
                    <button class="btn" id="update-level">Add new level</button>
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