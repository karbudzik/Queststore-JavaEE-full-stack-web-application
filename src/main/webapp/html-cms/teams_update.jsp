<%@ page import="model.Team" %>
<%@ page import="model.Codecooler" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Artifact" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" onclick="return hideSubMenu()">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel='icon' href='../favicon.ico' type='image/x-icon'>
    <link rel="stylesheet" href="../css/teams_add_new.css">
    <title>Team update</title>
</head>

<body>
    <jsp:include page="../html-common/cms-header.jsp" />

    <div class="container">
        <jsp:include page="../html-common/cms-navigation.jsp" />

        <div class="details-container"> 

            <h1>Team details</h1>
            <a href="/teams">&#60;- Back to the list</a>
            <p class="validation-message">${message}</p>

            <form class="form" action="" method="post">
                <input type="hidden" name="action" value="basic-information">
                <div class="personal-details">
                    <h2>Basic details</h2>
                    <div class ="details">
                        <label for="team-name">Name*: </label><br>
                        <input type="text" id="team-name" name="team-name" value="${team.getName()}">
                        <p class="validation-message">${name_validation_message}</p>
                        <label for="team-name">City*: </label><br>
                        <input type="text" id="team-city" name="team-city" value="${team.getCity()}">
                        <p class="validation-message">${city_validation_message}</p>
                        <label for="team-name">Start date*: </label><br>
                        <input type="text" id="team-start-date" name="team-start-date" value="${team.getStartDate()}">
                        <p class="validation-message">${start_date_validation_message}</p>
                        <div class="lower-section">
                            <p>*- Fields marked like these need to be filled to edit the team.</p>
                            <button class="btn" id="update-admin">Update</button>
                        </div>
                    </div>
                </div>
            </form>

            <form class="form" action="" method="post">
                <input type="hidden" name="action" value="team-members">
                <div class="personal-details">
                    <div class ="details">
                        <h2>Codecoolers in the team</h2>
                    </div>
                    <div class="codecoolers-in-team">
                        <div class="header-for-list">
                            <div> </div>
                            <div class="name-div">
                                <span>Name:</span>
                            </div>
                            <div class="email-div">
                                <span>Email</span>
                            </div>
                            <div class="actions-div">
                                <span>Actions:</span>
                            </div>
                        </div>
                        <%
                            List<Codecooler> teamCodecoolersList = (List<Codecooler>) request.getAttribute("teamCodecoolersList");
                            Team team = (Team) request.getAttribute("team");
                            int count = 0;
                            for (Codecooler codecooler : teamCodecoolersList) {
                                count++;
                        %>
                        <div class ="team-members">
                            <div class="team-members-number"><%=count%></div>
                            <div class="team-members-name"><%=codecooler.getName()%></div>
                            <div class="team-members-email"><%=codecooler.getEmail()%></div>
                            <div class="actions">
                                <a href="/teams/edit/delete_codecooler?team_id=<%=team.getId()%>&codecooler_id=<%=codecooler.getId()%>"><img src="../assets/img/delete-btn.svg" alt="delete-btn"></a>
                            </div>
                        </div>
                        <%
                            }
                        %>
                        <div class="add-new-codecooler">
                            <label for="selector" class="title">Add new Student</label><br>
                            <select name="student" class="student-name" id="selector">
                                <option value="" disabled selected>Select Student </option>
                                <%
                                    List<Codecooler> allCodecoolersList = (List<Codecooler>) request.getAttribute("allRemainingCodecoolersList");
                                    for (Codecooler codecooler : allCodecoolersList) {
                                %>
                                <option value=<%=codecooler.getId()%>><%=codecooler.getName()%></option>
                                <%
                                    }
                                %>
                            </select>
                        </div>
                        <button class="btn" id="add-codecooler">Add new</button>
                    </div>
                </div>
            </form>

            <form class="form" action="" method="post">
                <input type="hidden" name="action" value="team-artifacts">
                <div class="personal-details">
                    <h2>Bought artifacts</h2>
                    <div class="list-of-artifacts">
                        <div class="header-for-list">
                            <span></span>
                            <div class="name-div"><span>Name:</span></div>
                            <div class="type-div"><span>Type:</span></div>
                            <div class="when-div"><span>When:</span></div>
                            <div class="earned-div"><span>Cost:</span></div>
                            <div class="actions"><span>Used/Not Used:</span></div>
                        </div>
                    </div>
                    <%
                        List<Artifact> artifactsList = (List<Artifact>) request.getAttribute("teamArtifactsList");
                        count = 0;
                        for (Artifact artifact : artifactsList) {
                            count++;
                            String selectedValue = String.valueOf(artifact.isUsed());
                    %>
                    <div class="quest-details">
                        <div class="quest-number"><%=count%></div>
                        <div class="quest-name"><%=artifact.getName()%></div>
                        <div class="quest-type"><%=artifact.getType()%></div>
                        <div class="quest-date"><%=artifact.getDateOfAdding()%></div>
                        <div class="class"><%=artifact.getValue()%></div>
                        <div class="used-not">
                            <select class="selector" name="is-used">
                                <option selected><%=artifact.isUsedtoString()%></option>
                                <option>Used</option>
                                <option>Not Used</option>
                            </select>
                        </div>
                    </div>
                    <%
                        }
                    %>
                    <div class="button-position"> <button class="btn" id="update-quests">Save changes</button></div>
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