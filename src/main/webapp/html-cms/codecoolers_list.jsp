<%@ page import="model.Codecooler" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" onclick="return hideSubMenu()">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel='icon' href='../favicon.ico' type='image/x-icon'>
    <link rel="stylesheet" href="../css/all-codecoolers.css">
    <title>Codecooler's list</title>
</head>

<body>
    <jsp:include page="../html-common/cms-header.jsp"/>

    <div class="container">
        <jsp:include page="../html-common/cms-navigation.jsp"/>

        <div class="all-persons-container">
            <div class="upper-section">
                <div class="h1-button">
                    <h1>All Codecoolers</h1>
                    <button onclick="location.href='/codecoolers/add'" class="btn">+ Add new codecooler</button>
                </div>
                <div class="right-section">
                    <p>Items per page</p>
                </div>
            </div>
            <p class="validation-message">${message}</p>
            <div class="list-of-students">
                <div class="header-for-list">
                    <span></span>
                    <div class="name-div">
                        <span>Name:</span>
                        <div class="arrows">
                            <a href="/codecoolers?sortBy=name&order=ASC"><img src="../assets/img/arrow-up.svg" alt="^"></a>
                            <a href="/codecoolers?sortBy=name&order=DESC"><img src="../assets/img/arrow-down.svg"
                                                                           alt="v"></a>
                        </div>
                    </div>
                    <div class="email-div">
                        <span>Email:</span>
                        <div class="arrows">
                            <a href="/codecoolers?sortBy=email&order=ASC"><img src="../assets/img/arrow-up.svg" alt="^"></a>
                            <a href="/codecoolers?sortBy=email&order=DESC"><img src="../assets/img/arrow-down.svg" alt="v"></a>
                        </div>
                    </div>
                    <div class="city-div">
                        <span>City:</span>
                        <div class="arrows">
                            <a href="/codecoolers?sortBy=city&order=ASC"><img src="../assets/img/arrow-up.svg" alt="^"></a>
                            <a href="/codecoolers?sortBy=city&order=DESC"><img src="../assets/img/arrow-down.svg"
                                                                               alt="v"></a>
                        </div>
                    </div>
                    <div class="class-div">
                        <span>Class:</span>
                        <div class="arrows">
                            <a href="/codecoolers?sortBy=class&order=ASC"><img src="../assets/img/arrow-up.svg" alt="^"></a>
                            <a href="/codecoolers?sortBy=class&order=DESC"><img src="../assets/img/arrow-down.svg" alt="v"></a>
                        </div>
                    </div>
                    <div class="team-div">
                        <span>Team:</span>
                        <div class="arrows">
                            <a href="/codecoolers?sortBy=team&order=ASC"><img src="../assets/img/arrow-up.svg" alt="^"></a>
                            <a href="/codecoolers?sortBy=team&order=DESC"><img src="../assets/img/arrow-down.svg"
                                                                               alt="v"></a>
                        </div>
                    </div>
                    <div class="date-of-adding-div">
                        <span>Date of adding:</span>
                        <div class="arrows">
                            <a href="/codecoolers?sortBy=date&order=ASC"><img src="../assets/img/arrow-up.svg" alt="^"></a>
                            <a href="/codecoolers?sortBy=date&order=DESC"><img src="../assets/img/arrow-down.svg"
                                                                              alt="v"></a>
                        </div>
                    </div>
                    <div class="actions">
                        <span>Actions:</span>
                    </div>
                </div>
                <%
                    Map<Codecooler, List<String>> codecoolersMap = (Map<Codecooler, List<String>>) request.getAttribute("codecoolersMap");
                    int count = 0;
                    for (Map.Entry<Codecooler, List<String>> entry : codecoolersMap.entrySet()) {
                        Codecooler codecooler = entry.getKey();
                        List<String> detailsList = entry.getValue();
                        String className = detailsList.get(0);
                        String teamName = detailsList.get(1);
                        count++;
                %>
                <div class="person-row">
                    <div class="person-number"><%=count%>
                    </div>
                    <div class="person-name"><%=codecooler.getName()%>
                    </div>
                    <div class="person-email"><%=codecooler.getEmail()%>
                    </div>
                    <div class="person-city"><%=codecooler.getCity()%>
                    </div>
                    <div class="class"><%=className%>
                    </div>
                    <div class="team"><%=teamName%>
                    </div>
                    <div class="date-of-add"><%=codecooler.getDateOfAdding()%>
                    </div>
                    <div class="actions">
                        <a href="/codecoolers/edit?id=<%=codecooler.getId()%>"><img src="../assets/img/edit-btn.svg"
                                                                                    alt="edit-btn"></a>
                        <a href="/codecoolers/delete?id=<%=codecooler.getId()%>"><img src="../assets/img/delete-btn.svg"
                                                                                      alt="delete-btn"></a>
                    </div>
                </div>
                <%
                    }
                %>

            </div>
            <div class="selector">
                <a href="#" class="first">&lt;&lt;</a>
                <a href="#">1</a>
                <a href="#">2</a>
                <a href="#">3</a>
                <a href="#">>></a>
            </div>
        </div>
    </div>

    <jsp:include page="../html-common/footer.html"/>
    <script>
        document.getElementsByClassName('codecoolers-nav')[0].setAttribute('id', 'select-page');
    </script>
</body>

</html>