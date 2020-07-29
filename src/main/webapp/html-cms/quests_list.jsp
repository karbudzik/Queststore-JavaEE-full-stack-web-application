<%@ page import="model.Quest" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en" onclick="return hideSubMenu()">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel='icon' href='../favicon.ico' type='image/x-icon'>
    <link rel="stylesheet" href="../css/all-quests.css">
    <title>List of quests</title>
</head>

<body>
    <%
        List<Quest> questList = (List<Quest>) request.getAttribute("questsList");
        int count = 0;
    %>
    <jsp:include page="../html-common/cms-header.jsp" />

    <div class="container">
        <jsp:include page="../html-common/cms-navigation.jsp" />

        <div class="all-quests-container">
            <div class="upper-section">
                <div class="h1-button">
                    <h1>All quests</h1>
                    <button class="btn" onclick="location.href='/quests/add'">+ Add new quest</button>
                </div>
                <div class="right-section">
                    <p>Items per page</p>
                </div>
            </div>
            <p class="validation-message">${message}</p>
            <div class="list-of-quests">
                <div class="header-for-list">
                    <span></span>
                    <div class="name-div">
                        <span>Name:</span>
                        <div class="arrows">
                            <a href="/quests?sortBy=name&order=ASC"><img src="../assets/img/arrow-up.svg" alt="^"></a>
                            <a href="/quests?sortBy=name&order=DESC"><img src="../assets/img/arrow-down.svg" alt="v"></a>
                        </div>
                    </div>
                    <div class="description-div">
                        <span>Description snippet</span>
                        <div class="arrows">
                            <a href="/quests?sortBy=description&order=ASC"><img src="../assets/img/arrow-up.svg" alt="^"></a>
                            <a href="/quests?sortBy=description&order=DESC"><img src="../assets/img/arrow-down.svg" alt="v"></a>
                        </div>
                    </div>
                    <div class="value-div">
                        <span>Value:</span>
                        <div class="arrows">
                            <a href="/quests?sortBy=value&order=ASC"><img src="../assets/img/arrow-up.svg" alt="^"></a>
                            <a href="/quests?sortBy=value&order=DESC"><img src="../assets/img/arrow-down.svg" alt="v"></a>
                        </div>
                    </div>
                    <div class="type-div">
                        <span>Type:</span>
                        <div class="arrows">
                            <a href="/quests?sortBy=type&order=ASC"><img src="../assets/img/arrow-up.svg" alt="^"></a>
                            <a href="/quests?sortBy=type&order=DESC"><img src="../assets/img/arrow-down.svg" alt="v"></a>
                        </div>
                    </div>
                    <div class="date-of-adding-div">
                        <span>Date of adding:</span>
                        <div class="arrows">
                            <a href="/quests?sortBy=date&order=ASC"><img src="../assets/img/arrow-up.svg" alt="^"></a>
                            <a href="/quests?sortBy=date&order=DESC"><img src="../assets/img/arrow-down.svg" alt="v"></a>
                        </div>
                    </div>
                    <div class="actions-div">
                        <span>Actions:</span>
                    </div>
                </div>
                <%
                    for (Quest quest : questList) {
                        count++;
                %>
                <div class="quest-row">
                    <div class="quest-number"><%=count%></div>
                    <div class="quest-img-name">
                        <img src="../assets/img/quests-img/<%=quest.getPictureUrl()%>" alt="quest's-image"
                             class="quest-img">
                        <p class="quest-name"><%=quest.getName()%></p>
                    </div>
                    <div class="quest-descriptions"><%=quest.getDescription()%></div>
                    <div class="quest-value"><%=quest.getValue()%></div>
                    <div class="quest-type"><%=quest.getType().name()%></div>
                    <div class="date-of-add"><%=quest.getDateOfAdding()%></div>
                    <div class="actions">
                        <a href="/quests/edit?id=<%=quest.getID()%>"><img src="../assets/img/edit-btn.svg" alt="edit-btn"></a>
                        <a href="/quests/delete?id=<%=quest.getID()%>"><img src="../assets/img/delete-btn.svg" alt="delete-btn"></a>
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
        document.getElementsByClassName('quests-nav')[0].setAttribute('id', 'select-page');
    </script>
</body>

</html>