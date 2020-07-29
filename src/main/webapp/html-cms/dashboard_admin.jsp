<%@ page import="model.SummaryAdmin" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" onclick="return hideSubMenu()">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel='icon' href='../favicon.ico' type='image/x-icon'>
    <link rel="stylesheet" href="../css/admin_dashboard.css">
    <title>Dashboard</title>
</head>

<body>
    <%
        SummaryAdmin summaryAdmin = (SummaryAdmin) request.getAttribute("summaryAdmin");
    %>

    <jsp:include page="../html-common/cms-header.jsp" />

    <div class="container">
        <jsp:include page="../html-common/cms-navigation.jsp" />

        <div class="options_container">
            <div class="options_rectangle">
                <div class="upper_section">
                    <img src="../assets/icons/admins_svg.svg" alt="admins_icon" class="admins_icon">
                    <div class="title_section">
                        <div><p>Admins in CC:</p></div>
                        <div class="number" id="admins_number"><p><%=summaryAdmin.getAdminsCount()%></p></div>
                    </div>
                </div>
                <div class="lower_section">
                    <p><a href="/user-list?type=admin">See list of admins...</a></p>
                </div>
            </div>
            <div class="options_rectangle">
                <div class="upper_section">
                    <img src="../assets/icons/mentors_svg.svg" alt="mentors_icon" class="mentors_icon">
                    <div class="title_section">
                        <div><p>Mentors in CC:</p></div>
                        <div class="number" id="mentors_number"><p><%=summaryAdmin.getMentorsCount()%></p></div>
                    </div>
                </div>
                <div class="lower_section">
                    <p><a href="/user-list?type=mentor">See list of mentors...</a></p>
                </div>
            </div>
            <div class="options_rectangle">
                <div class="upper_section">
                    <img src="../assets/icons/students_svg.svg" alt="students_icon" class="students_icon">
                    <div class="title_section">
                        <div><p>Students in CC:</p></div>
                        <div class="number" id="students_number"><p><%=summaryAdmin.getStudentsCount()%></p></div>
                    </div>
                </div>
                <div class="lower_section">
                    <p><a href="/codecoolers">See list of Codecoolers...</a></p>
                </div>
            </div>
            <div class="options_rectangle">
                <div class="upper_section">
                    <img src="../assets/icons/levels_svg.svg" alt="levels_icon" class="levels_icon">
                    <div class="title_section">
                        <div><p>Levels in CC:</p></div>
                        <div class="number" id="levels_number"><p><%=summaryAdmin.getLevelsCount()%></p></div>
                    </div>
                </div>
                <div class="lower_section">
                    <p><a href="/levels">See list of all levels...</a></p>
                </div>
            </div>
            <div class="options_rectangle">
                <div class="upper_section">
                    <img src="../assets/icons/classes_svg.svg" alt="classes_icon" class="classes_icon">
                    <div class="title_section">
                        <div><p>Classes in CC:</p></div>
                        <div class="number" id="classes_number"><p><%=summaryAdmin.getClassesCount()%></p></div>
                    </div>
                </div>
                <div class="lower_section">
                    <p><a href="/classes">See list of classes...</a></p>
                </div>
            </div>
            <div class="options_rectangle">
                <div class="upper_section">
                    <img src="../assets/icons/teams_svg.svg" alt="teams_icon" class="teams_icon">
                    <div class="title_section">
                        <div><p>Teams in CC:</p></div>
                        <div class="number" id="teams_number"><p><%=summaryAdmin.getTeamsCount()%></p></div>
                    </div>
                </div>
                <div class="lower_section">
                    <p><a href="/teams">See list of teams...</a></p>
                </div>
            </div>
            <div class="options_rectangle">
                <div class="upper_section">
                    <img src="../assets/icons/quests_svg.svg" alt="quests_icon" class="quest_icon">
                    <div class="title_section">
                        <div><p>Possible quests:</p></div>
                        <div class="number" id="quests_number"><p><%=summaryAdmin.getQuestsCount()%></p></div>
                    </div>
                </div>
                <div class="lower_section">
                    <p><a href="/quests">See list of quests...</a></p>
                </div>
            </div>
            <div class="options_rectangle">
                <div class="upper_section">
                    <img src="../assets/icons/artifacts_svg.svg" alt="artifacts_icon" class="artifacts_icon">
                    <div class="title_section">
                        <div><p>Available artifacts:</p></div>
                        <div class="number" id="artifacts_number"><p><%=summaryAdmin.getArtifactsCount()%></p></div>
                    </div>
                </div>
                <div class="lower_section">
                    <p><a href="/artifacts">See list of artifacts...</a></p>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="../html-common/footer.html" />
    <script>
        document.getElementsByClassName('dashboard-nav')[0].setAttribute('id', 'select-page');
    </script>
</body>

</html>