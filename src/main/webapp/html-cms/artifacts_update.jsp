<%@ page import="model.Artifact" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" onclick="return hideSubMenu()">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel='icon' href='../favicon.ico' type='image/x-icon'>
    <link rel="stylesheet" href="../css/artifact_update.css">
    <title>Update artifacts</title>
</head>

<body>
<%
    Artifact artifact = (Artifact) request.getAttribute("artifact");
%>
<jsp:include page="../html-common/cms-header.jsp"/>

<div class="container">
    <jsp:include page="../html-common/cms-navigation.jsp"/>

    <div class="details-container">
        <h1>Artifact’s details</h1>
        <a href="/artifacts">&#60;- Back to the list</a>
        <p class="validation-message">${message}</p>
        <form action="/artifacts/update" method="post">
            <div class="artifacts-details">
                <h2>Basic details</h2>
                <label for="artifacts-name">Name*:</label>
                <input type="text" id="artifacts-name" value="<%=artifact.getName()%>" name="artifact-name">
                <p class="validation-message">${name_validation_message}</p>
                <label for="artifacts-descripton">Description*:</label>
                <textarea id="artifacts-descripton"
                          name="artifact-description"><%=artifact.getDescription()%></textarea>
                <p class="validation-message">${description_validation_message}</p>
                <div class="proporties-section">
                    <div class="picture">
                        <p>Picture: </p>
                        <img id="artifacts-logo" src="../assets/img/artifacts-images/<%=artifact.getPictureUrl()%>"
                             alt="artifact logo"><br>
                        <a href="#"><img src="../assets/icons/change_picture.svg" alt=" ">Change picture</a>
                    </div>
                    <div class=proporties>
                        <label>Value (Number of coins student will get for the artifacts)*:</label><br>
                        <input type="text" value="<%=artifact.getValue()%>" name="artifact-value"><br>
                        <p class="validation-message">${value_validation_message}</p>
                        <label>Type (Single or Team)*:</label><br>
                        <select class="type-selector" id="type-selector" name="type-selector">
                            <option selected hidden>${artifact.getType()}</option>
                            <option name="artifact-type">Single</option>
                            <option name="artifact-type">Team</option>
                        </select>
                        <p class="validation-message">${type_validation_message}</p>
                    </div>
                </div>
                <div class="lower-section">
                    <p>*- Fields marked like the need to be filled to add new entry</p>
                    <button class="btn" id="update-admin">Update</button>
                </div>
            </div>
        </form>
    </div>

</div>

<jsp:include page="../html-common/footer.html"/>
<script>
    document.getElementsByClassName('artifacts-nav')[0].setAttribute('id', 'select-page');
</script>
</body>

</html>