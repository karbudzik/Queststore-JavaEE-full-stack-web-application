<%@ page import="model.CodecoolerClass" %>
<%@ page import="model.Codecooler" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="classToEdit" scope="request" type="model.CodecoolerClass"/>
<html lang="en" onclick="return hideSubMenu()">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel='icon' href='../favicon.ico' type='image/x-icon'>
    <link rel="stylesheet" href="../css/classes_add_new.css">
    <title>Class update</title>
</head>

<body>
    <jsp:include page="../html-common/cms-header.jsp" />

    <div class="container">
        <jsp:include page="../html-common/cms-navigation.jsp" />

        <div class="details-container">
            <h1>Class details</h1>
            <a href="/classes">&#60;- Back to the list</a>
            <p class="validation-message">${message}</p>
            <div class="personal-details">
                <h2>Basic details</h2>
                    <form action="/classes/edit" method="post" class ="details">
                        <input name="action" type="hidden" value="update-details"/>
                        <div class="class"> Name*: <br>
                            <input type="text" name="class-name" id="class-name" value="${classToEdit.name}">
                            <p class="validation-message">${name_validation_message}</p>
                        </div>
                        <div class="class"> City: <br>
                            <input type="text" name="class-city" id="class-city" value="${classToEdit.city}">
                            <p class="validation-message">${city_validation_message}</p>
                        </div>
                        <div class="class"> Start date*:<br>
                            <input type="text" name="class-start-date" id="class-start-date" value="${classToEdit.startDate}">
                            <p class="validation-message">${start_date_validation_message}</p>
                        </div>
                        <div class="class"> End date*:<br>
                            <input type="text" name="class-end-date" id="class-end-date" value="${classToEdit.endDate}">
                            <p class="validation-message">${end_date_validation_message}</p>
                        </div>

                        <div class="lower-section">
                            <p>*- Fields marked like these need to be filled<br></p>
                            <button type="submit" class="btn" id="update-class">Update</button>
                        </div>
                    </form>
            </div>

            <div class="personal-details">
                <div class ="details">
                    <h2>Codecoolers in the class</h2>
                </div>

                <div class="codecoolers-in-class">
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
                        int count = 1;
                        for(Codecooler codecooler: classToEdit.getMembers()){
                    %>
                <div class ="class-members">
                    <div class="class-members-number"><%=count%></div>
                    <div class="class-members-name"><%=codecooler.getName()%></div>
                    <div class="class-members-email"><%=codecooler.getEmail()%></div>
                    <div class="actions">
                        <a href="/classes/delete-codecooler?id=<%=codecooler.getId()%>&class_id=<%=classToEdit.getId()%>"><img src="../assets/img/delete-btn.svg" alt="delete-btn"></a>
                    </div>
                </div>
                    <%
                        count++;
                        }
                    %>

                <form method="post" action="/classes/edit" class="add-new-codecooler">
                    <input type="hidden" name="action" value="add-new-codecooler"/>
                    <label class="title">Add new Student</label><br>
                    <select class="student-name" name="select-new-student" id="selector">
                        <option value="" disabled selected>Select Student </option>
                       <jsp:useBean id="codecoolerList" type="java.util.List<model.Codecooler>" scope="request" />
                       <%
                           for(Codecooler codecooler: codecoolerList){
                       %>
                        <option value=<%=codecooler.getId()%>><%=codecooler.getName()%></option>
                       <%
                           }
                       %>
                    </select>
                <button type="submit" class="btn" id="add-codecooler">Add new</button>
                </form>
            </div>  
        </div>
        </div>
    </div>

    <jsp:include page="../html-common/footer.html" />
    <script>
        document.getElementsByClassName('classes-nav')[0].setAttribute('id', 'select-page');
    </script>
</body>
</html>