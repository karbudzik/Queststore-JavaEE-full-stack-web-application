<%@ page import="model.CodecoolerClass" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Team" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" onclick="return hideSubMenu()">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel='icon' href='../favicon.ico' type='image/x-icon'>
    <link rel="stylesheet" href="../css/codecoolers_update.css">
    <title>Add new Codecooler</title>
</head>

    <body>
    <jsp:include page="../html-common/cms-header.jsp"/>

    <div class="container">
        <jsp:include page="../html-common/cms-navigation.jsp" />

        <div class="details-container">

            <h1>Add new Codecooler</h1>
            <a href="/codecoolers">&#60;- Back to the list</a>
            <p class="validation-message">${message}</p>
            <div class="personal-details">
                <form class="basic-details" action="/codecoolers/add" method="post">
                    <h2>Basic details</h2>

                    <div class="details">

                        <div class="person"> Name*: <br>
                            <input name="person-name" type="text" id="person-name" placeholder="">
                            <p class="validation-message">${name_validation_message}</p>
                        </div>
                        <div class="person"> Class:<br>
                            <select name="person-class" class="class-team-seletor" id="class-selector">
                                <option value="" disabled selected>Choose...</option>
                                <%
                                    List<CodecoolerClass> classList = (List<CodecoolerClass>) request.getAttribute("classList");
                                    for (CodecoolerClass codecoolerClass : classList) {
                                %>
                                <option value=<%=codecoolerClass.getId()%>><%=codecoolerClass.getName()%></option>
                                <%
                                    }
                                %>
                            </select>
                        </div>
                        <div class="person"> Email*:<br>
                            <input name="person-email" type="text" id="person-email" placeholder="">
                            <p class="validation-message">${email_validation_message}</p>
                        </div>
                        <div class="person"> Team:<br>
                            <select name="person-team" class="class-team-seletor" id="team-selector">
                                <option value="" disabled selected>Choose...</option>
                                <%
                                    List<Team> teamsList = (List<Team>) request.getAttribute("teamsList");
                                    for (Team team : teamsList) {
                                %>
                                <option value=<%=team.getId()%>><%=team.getName()%></option>
                                <%
                                    }
                                %>
                            </select>
                        </div>
                        <div class="person"> City:<br>
                            <input name="person-city" type="text" id="person-city" placeholder="">
                            <p class="validation-message">${city_validation_message}</p>
                        </div>
                        <div class="lower-section">
                            <p>*- Fields marked like these need to be filled to add new entry<br></p>
                            <button class="btn" id="update-student">Add new Codecooler</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <jsp:include page="../html-common/footer.html"/>
    <script>
        document.getElementsByClassName('codecoolers-nav')[0].setAttribute('id', 'select-page');
    </script>
</body>
</html>