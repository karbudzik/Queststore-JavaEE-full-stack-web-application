<%@ page import="model.CodecoolerClass" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" onclick="return hideSubMenu()">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel='icon' href='../favicon.ico' type='image/x-icon'>
    <link rel="stylesheet" href="../css/all-classes.css">
    <title>All classes</title>
</head>

<body>
    <jsp:include page="../html-common/cms-header.jsp" />
    
    <div class="container">
        <jsp:include page="../html-common/cms-navigation.jsp" />
        <jsp:useBean id="allClassList" scope="request" type="java.util.List<model.CodecoolerClass>" />

        <div class="all-classes-container">
            <div class="upper-section">
                <div class="h1-button">
                    <h1>All classes</h1>
                    <button onclick="location.href='/classes/add'" class="btn">+ Add new class</button>
                </div>
                <div class="right-section">
                    <p>Items per page</p>
                </div>
            </div>
            <p class="validation-message">${message}</p>
            <div class="list-of-classes">
                <div class="header-for-list">
                    <span></span>
                    <div class="name-div">
                        <span>Name:</span>
                        <div class="arrows">
                            <a href="/classes?sortBy=name&order=ASC"><img src="../assets/img/arrow-up.svg" alt="^"></a>
                            <a href="/classes?sortBy=name&order=DESC"><img src="../assets/img/arrow-down.svg" alt="v"></a>
                        </div>
                    </div>
                    <div class="city-div">
                        <span>City:</span>
                        <div class="arrows">
                            <a href="/classes?sortBy=city&order=ASC"><img src="../assets/img/arrow-up.svg" alt="^"></a>
                            <a href="/classes?sortBy=city&order=DESC"><img src="../assets/img/arrow-down.svg" alt="v"></a>
                        </div>
                    </div>
                    <div class="start-date-div">
                        <span>Start date:</span>
                        <div class="arrows">
                            <a href="/classes?sortBy=start-date&order=ASC"><img src="../assets/img/arrow-up.svg" alt="^"></a>
                            <a href="/classes?sortBy=start-date&order=DESC"><img src="../assets/img/arrow-down.svg" alt="v"></a>
                        </div>
                    </div>
                    <div class="end-date-div">
                        <span>End-date:</span>
                        <div class="arrows">
                            <a href="/classes?sortBy=end-date&order=ASC"><img src="../assets/img/arrow-up.svg" alt="^"></a>
                            <a href="/classes?sortBy=end-date&order=DESC"><img src="../assets/img/arrow-down.svg" alt="v"></a>
                        </div>
                    </div>
                    <div class="actions-div">
                        <span>Actions:</span>
                    </div>
                </div>
                <%
                    int count = 0;
                    for(CodecoolerClass codecoolerClass :allClassList){
                       count++;
                %>
                <div class="class-row">
                   <div class="class-number"><%=count%></div>
                   <div class="class-name"><%=codecoolerClass.getName()%></div>
                   <div class="class-city"><%=codecoolerClass.getCity()%></div>
                   <div class="class-start-date"><%=codecoolerClass.getStartDate()%></div>
                   <div class="class-end-date"><%=codecoolerClass.getEndDate()%></div>
                   <div class="actions">
                       <a href="/classes/edit?id=<%=codecoolerClass.getId()%>"><img src="../assets/img/edit-btn.svg" alt="edit-btn"></a>
                       <a href="/classes/delete?id=<%=codecoolerClass.getId()%>"><img src="../assets/img/delete-btn.svg" alt="delete-btn"></a>
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
        document.getElementsByClassName('classes-nav')[0].setAttribute('id', 'select-page');
    </script>
</body>

</html>