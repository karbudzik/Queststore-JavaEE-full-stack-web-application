<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Level" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" onclick="return hideSubMenu()">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel='icon' href='../favicon.ico' type='image/x-icon'>
    <link rel="stylesheet" href="../css/all-persons.css">
    <title>Levels list</title>
</head>

<body>
    <%
        ArrayList<Level> allLevels = (ArrayList<Level>) request.getAttribute("levelsList");
    %>
    <jsp:include page="../html-common/cms-header.jsp" />
    
    <div class="container">
        <jsp:include page="../html-common/cms-navigation.jsp" />

        <div class="all-persons-container">
            <div class="upper-section">
                <div class="h1-button">
                    <h1>All levels</h1>
                    <button class="btn" onclick="location.href='/levels/add'">+ Add new level</button>
                </div>
                <div class="right-section">
                    <p>Items per page</p>
                </div>
            </div>
            <div class="list-of-persons">
                <div class="header-for-list">
                    <span></span>
                    <div class="name-div">
                        <span>Name:</span>
                        <div class="arrows">
                            <a href="/levels?sortBy=name&order=ASC"><img src="../assets/img/arrow-up.svg" alt="^"></a>
                            <a href="/levels?sortBy=name&order=DESC"><img src="../assets/img/arrow-down.svg" alt="v"></a>
                        </div>
                    </div>
                    <div class="description-div">
                        <span>Description snippet:</span>
                        <div class="arrows">
                            <a href="/levels?sortBy=description&order=ASC"><img src="../assets/img/arrow-up.svg" alt="^"></a>
                            <a href="/levels?sortBy=description&order=DESC"><img src="../assets/img/arrow-down.svg" alt="v"></a>
                        </div>
                    </div>
                    <div class="coins-div">
                        <span>No. coins to get:</span>
                        <div class="arrows">
                            <a href="/levels?sortBy=coins&order=ASC"><img src="../assets/img/arrow-up.svg" alt="^"></a>
                            <a href="/levels?sortBy=coins&order=DESC"><img src="../assets/img/arrow-down.svg" alt="v"></a>
                        </div>
                    </div>
                    <div class="date-of-adding-div">
                        <span>Date of adding:</span>
                        <div class="arrows">
                            <a href="/levels?sortBy=date&order=ASC"><img src="../assets/img/arrow-up.svg" alt="^"></a>
                            <a href="/levels?sortBy=date&order=DESC"><img src="../assets/img/arrow-down.svg" alt="v"></a>
                        </div>
                    </div>
                    <div class="actions-div">
                        <span>Actions:</span>
                    </div>
                </div>
                    <%
                        int levelId = 1;
                        for(Level level : allLevels){

                    %>
                 <div class="person-row">
                    <div class="person-number"><%= levelId%></div>
                    <div class="person-img-name">
                        <img src="../assets/img/levels-img/<%=level.getPictureUrl()%>" alt="person's-image" class="person-img">
                        <p class="person-name"><%= level.getName() %></p>
                    </div>
                    <div class="level-description"><%= level.getDescription()%> </div>
                    <div class="coins-number"><%= level.getPrice()%></div>
                    <div class="date-of-add"><%= level.getDateOfAdding()%></div>
                    <div class="actions">
                        <a href="/levels/edit?id=<%= level.getId()%>"><img src="../assets/img/edit-btn.svg" alt="edit-btn"></a>
                        <form class="form" action="/levels/delete" method="GET">
                        <a href="/levels/delete/?id=<%= level.getId()%>"><img src="../assets/img/delete-btn.svg" alt="delete-btn"></a>
                        </form>
                    </div>
                 </div>
                <%
                        levelId++;
                        }
                %>

                 </div>
            </div>
        </div>
    </div>


    <jsp:include page="../html-common/footer.html" />
    <script>
        document.getElementsByClassName('levels-nav')[0].setAttribute('id', 'select-page');
    </script>

</body>

</html>