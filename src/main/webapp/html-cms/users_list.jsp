<%@ page import="model.CMSUser" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" onclick="return hideSubMenu()"></html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel='icon' href='../favicon.ico' type='image/x-icon'>
    <link rel="stylesheet" href="../css/all-persons.css">
    <title>All admins</title>
</head>

<body>
    <jsp:include page="../html-common/cms-header.jsp" />
    
    <div class="container">
        <jsp:include page="../html-common/cms-navigation.jsp" />

        <%
           List<CMSUser> allUsers = (List<CMSUser>) request.getAttribute("allUsers");
        %>
        <div class="all-persons-container">
            <div class="upper-section">
                <div class="h1-button">
                    <h1>All ${type}s</h1>
                    <button class="btn" onclick="location.href='/user/new?type=${type}'">+ Add new ${type}</button>
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
                            <a href="/user-list?type=${type}&sortBy=name&order=ASC"><img src="../assets/img/arrow-up.svg" alt="^"></a>
                            <a href="/user-list?type=${type}&sortBy=name&order=DESC"><img src="../assets/img/arrow-down.svg" alt="v"></a>
                        </div>
                    </div>
                    <div class="email-div">
                        <span>Email:</span>
                        <div class="arrows">
                            <a href="/user-list?type=${type}&sortBy=email&order=ASC"><img src="../assets/img/arrow-up.svg" alt="^"></a>
                            <a href="/user-list?type=${type}&sortBy=email&order=DESC"><img src="../assets/img/arrow-down.svg" alt="v"></a>
                        </div>
                    </div>
                    <div class="city-div">
                        <span>City:</span>
                        <div class="arrows">
                            <a href="/user-list?type=${type}&sortBy=city&order=ASC"><img src="../assets/img/arrow-up.svg" alt="^"></a>
                            <a href="/user-list?type=${type}&sortBy=city&order=DESC"><img src="../assets/img/arrow-down.svg" alt="v"></a>
                        </div>
                    </div>
                    <div class="date-of-adding-div">
                        <span>Date of adding:</span>
                        <div class="arrows">
                            <a href="/user-list?type=${type}&sortBy=date&order=ASC"><img src="../assets/img/arrow-up.svg" alt="^"></a>
                            <a href="/user-list?type=${type}&sortBy=date&order=DESC"><img src="../assets/img/arrow-down.svg" alt="v"></a>
                        </div>
                    </div>
                    <div class="actions-div">
                        <span>Actions:</span>
                    </div>
                </div>
                <%
                    int index = 1;
                    for(CMSUser user: allUsers){
                %>
                <div class="person-row">
                   <div class="person-number"><%=index%>.</div>
                   <div class="person-img-name">
                       <img src="<%=user.getPictureURL()%>" alt="person's-image" class="person-img">
                       <p class="person-name"><%=user.getName()%></p>
                   </div>
                   <div class="person-email"><%=user.getEmail()%></div>
                   <div class="person-city"><%=user.getCity()%></div>
                   <div class="date-of-add"><%=user.getDateOfAdding()%></div>
                   <div class="actions">
                       <a href="/user/edit?id=<%=user.getID()%>"><img src="../assets/img/edit-btn.svg" alt="edit-btn"></a>
                       <a href="/user/delete?id=<%=user.getID()%>&type=${type}"><img src="../assets/img/delete-btn.svg" alt="delete-btn"></a>
                   </div>
                </div>
                <%
                        index++;
                    }
                %>
            </div>
        </div>
    </div>

    <jsp:include page="../html-common/footer.html" />
    <script>
        <%
        String permissions = (String) request.getAttribute("type");
        if(permissions.equals("admin")){
        %>
        document.getElementsByClassName('admins-nav')[0].setAttribute('id', 'select-page');
           <%
        }else{
        %>
        document.getElementsByClassName('mentors-nav')[0].setAttribute('id', 'select-page');
        <%
        }
        %>
    </script>
</body>

</html>