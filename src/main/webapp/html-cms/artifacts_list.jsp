<%@ page import="model.Artifact" %>
<%@ page import="java.util.List" %>
<%@ page import="DAO.ArtifactJDBCDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" onclick="return hideSubMenu()">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel='icon' href='../favicon.ico' type='image/x-icon'>
    <link rel="stylesheet" href="../css/all-artifacts.css">
    <title>All artifacts</title>
</head>

<body>

<%
    List<Artifact> allArtifacts = (List<Artifact>) request.getAttribute("allArtifacts");
%>
<jsp:include page="../html-common/cms-header.jsp"/>

<div class="container">
    <jsp:include page="../html-common/cms-navigation.jsp"/>

    <div class="all-artifacts-container">
        <div class="upper-section">
            <div class="h1-button">
                <h1>All artifacts</h1>
                <button onclick="location.href='/artifacts/add'" class="btn">+ Add new artifact</button>
            </div>
            <div class="right-section">
                <p>Items per page</p>
            </div>
        </div>
        <div class="list-of-artifacts">
            <div class="header-for-list">
                <span></span>
                <div class="name-div">
                    <span>Name:</span>
                    <div class="arrows">
                        <a href="/artifacts?sortBy=name&order=ASC"><img src="../assets/img/arrow-up.svg" alt="^"></a>
                        <a href="/artifacts?sortBy=name&order=DESC"><img src="../assets/img/arrow-down.svg" alt="v"></a>
                    </div>
                </div>
                <div class="description-div">
                    <span>Description snippet</span>
                    <div class="arrows">
                        <a href="/artifacts?sortBy=description&order=ASC"><img src="../assets/img/arrow-up.svg" alt="^"></a>
                        <a href="/artifacts?sortBy=description&order=DESC"><img src="../assets/img/arrow-down.svg" alt="v"></a>
                    </div>
                </div>
                <div class="value-div">
                    <span>Value:</span>
                    <div class="arrows">
                        <a href="/artifacts?sortBy=value&order=ASC"><img src="../assets/img/arrow-up.svg" alt="^"></a>
                        <a href="/artifacts?sortBy=value&order=DESC"><img src="../assets/img/arrow-down.svg" alt="v"></a>
                    </div>
                </div>
                <div class="type-div">
                    <span>Type:</span>
                    <div class="arrows">
                        <a href="/artifacts?sortBy=type&order=ASC"><img src="../assets/img/arrow-up.svg" alt="^"></a>
                        <a href="/artifacts?sortBy=type&order=DESC"><img src="../assets/img/arrow-down.svg" alt="v"></a>
                    </div>
                </div>
                <div class="date-of-adding-div">
                    <span>Date of adding:</span>
                    <div class="arrows">
                        <a href="/artifacts?sortBy=date&order=ASC"><img src="../assets/img/arrow-up.svg" alt="^"></a>
                        <a href="/artifacts?sortBy=date&order=DESC"><img src="../assets/img/arrow-down.svg" alt="v"></a>
                    </div>
                </div>
                <div class="actions-div">
                    <span>Actions:</span>
                </div>
            </div>

            <%
                int rowId = 1;
                for (Artifact artifact : allArtifacts){
            %>


            <div class="artifact-row">
                <div class="artifact-number"><%=rowId%></div>
                <div class="artifact-img-name">
                    <img src="../assets/img/artifacts-images/<%=artifact.getPictureUrl()%>" alt="artifact's-image"
                         class="artifact-img">
                    <p class="artifact-name"><%=artifact.getName()%></p>
                </div>
                <div class="artifact-descriptions"><%=artifact.getDescription()%></div>
                <div class="artifact-value"><%=artifact.getValue()%></div>
                <div class="artifact-type"><%=artifact.getType()%></div>
                <div class="date-of-add"><%=artifact.getDateOfAdding()%></div>
                <div class="actions">
                        <a href="/artifacts/update?id=<%=artifact.getId()%>"><img src="../assets/img/edit-btn.svg" alt="edit-btn"></a>
                    <form action="/artifacts/delete/" method="get">
                        <a href="/artifacts/delete/?id=<%=artifact.getId()%>"><img src="../assets/img/delete-btn.svg" alt="delete-btn"></a>
                    </form>
                </div>
            </div>
            <%
                    rowId++;
                }%>

        </div>
    </div>
</div>
</div>

<jsp:include page="../html-common/footer.html"/>
<script>
    document.getElementsByClassName('artifacts-nav')[0].setAttribute('id', 'select-page');
</script>
</body>

</html>