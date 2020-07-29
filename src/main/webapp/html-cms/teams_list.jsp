<%@ page import="model.Team" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en" onclick="return hideSubMenu()">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel='icon' href='../favicon.ico' type='image/x-icon'>
    <link rel="stylesheet" href="../css/all-teams.css">
    <title>All teams</title>
</head>

<body>
    <%
        List<Team> teamsList = (List<Team>) request.getAttribute("teamsList");
        int count = 0;
    %>
    <jsp:include page="../html-common/cms-header.jsp" />

    <div class="container">
        <jsp:include page="../html-common/cms-navigation.jsp" />

        <div class="all-teams-container">
            <div class="upper-section">
                <div class="h1-button">
                    <h1>All Teams</h1>
                    <button onclick="location.href='/teams/add'" type="button" class="btn">+ Add new team</button>
                </div>
                <div class="right-section">
                    <p>Items per page</p>
                </div>
            </div>
            <p class="validation-message">${message}</p>
            <div class="list-of-teams">
                <div class="header-for-list">
                    <span></span>
                    <div class="name-div">
                        <span>Name:</span>
                        <div class="arrows">
                            <a href="/teams?sortBy=name&order=ASC"><img src="../assets/img/arrow-up.svg" alt=""></a>
                            <a href="/teams?sortBy=name&order=DESC"><img src="../assets/img/arrow-down.svg" alt=""></a>
                        </div>
                    </div>
                    <div class="city-div">
                        <span>City:</span>
                        <div class="arrows">
                            <a href="/teams?sortBy=city&order=ASC"><img src="../assets/img/arrow-up.svg" alt=""></a>
                            <a href="/teams?sortBy=city&order=DESC"><img src="../assets/img/arrow-down.svg" alt=""></a>
                        </div>
                    </div>
                    <div class="date-of-start-div">
                        <span>Start date:</span>
                        <div class="arrows">
                            <a href="/teams?sortBy=date&order=ASC"><img src="../assets/img/arrow-up.svg" alt=""></a>
                            <a href="/teams?sortBy=date&order=DESC"><img src="../assets/img/arrow-down.svg" alt=""></a>
                        </div>
                    </div>
                    <div class="actions-div">
                        <span>Actions:</span>
                    </div>
                </div>
                <%
                    for (Team team : teamsList) {
                        count++;
                %>
                <div class="team-row">
                    <div class="team-number"><%=count%></div>
                    <div class="team-img-name">
                        <p class="team-name"><%=team.getName()%></p>
                    </div>
                    <div class="team-city"><%=team.getCity()%></div>
                    <div class="date-of-add"><%=team.getStartDate()%></div>
                    <div class="actions">
                        <a href="/teams/edit?id=<%=team.getId()%>"><img src="../assets/img/edit-btn.svg" alt="edit-btn"></a>
                        <a href="/teams/delete?id=<%=team.getId()%>"><img src="../assets/img/delete-btn.svg" alt="delete-btn"></a>
                    </div>
                </div>
                <%
                    }
                %>
            </div>
        </div>
    </div>

    <jsp:include page="../html-common/footer.html" />
    <script>
        document.getElementsByClassName('teams-nav')[0].setAttribute('id', 'select-page');
    </script>
</body>

</html>