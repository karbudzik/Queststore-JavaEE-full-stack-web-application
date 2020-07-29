<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" onclick="return hideSubMenu()">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel='icon' href='../favicon.ico' type='image/x-icon'>
    <link rel="stylesheet" href="../css/classes_add_new.css">
    <title>Add new class</title>
</head>

<body>
    <jsp:include page="../html-common/cms-header.jsp" />

    <div class="container">
        <jsp:include page="../html-common/cms-navigation.jsp" />

        <div class="details-container">
            <h1>Add new class</h1>
            <a href="/classes">&#60;- Back to the list</a>
            <p class="validation-message">${message}</p>
            <div class="personal-details">
                <h2>Basic details</h2>
                    <form action="/classes/add" method="post" class ="details">
                        <div class="class"> Name*: <br>
                            <input type="text" name="class-name" id="class-name">
                            <p class="validation-message">${name_validation_message}</p>
                        </div>
                        <div class="class"> City*: <br>
                            <input type="text" name="class-city" id="class-city">
                            <p class="validation-message">${city_validation_message}</p>
                        </div>
                        <div class="class"> Start date*:<br>
                            <input type="text" name="class-start-date" id="class-start-date">
                            <p class="validation-message">${start_date_validation_message}</p>
                        </div>
                        <div class="class"> End date*:<br>
                            <input type="text" name="class-end-date" id="class-end-date">
                            <p class="validation-message">${end_date_validation_message}</p>
                        </div>
                        <div class="lower-section">
                            <p>*- Fields marked like these need to be filled to add new class<br></p>
                            <button type="submit" class="btn" id="update-class">Add class</button>
                        </div>
                    </form>
            </div>
        </div>    
     </div>

    <jsp:include page="../html-common/footer.html" />
    <script>
        document.getElementsByClassName('classes-nav')[0].setAttribute('id', 'select-page');
    </script>
</body>
</html>