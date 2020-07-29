<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="pl">
<head>
  <meta charset="utf-8">
  <title>CodeQuest Student's Portal</title>
  <meta name="dashboard" content="main page for Codecooler">
  <link rel='icon' href='../favicon.ico' type='image/x-icon'>
  <link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@600&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="../css/codecooler_dashboard.css">
</head>
<body>
    <jsp:include page="../html-common/codecooler-header.jsp" />

    <div class="content">
        <div class="level-1">
            <img src="../assets/img/dashboard-codecooler-1.svg" alt="photo-1">
            <div class="texts">
                <div class="text blue">THE MORE</div>
                <div class="text blue">YOU WORK</div>
                <div class="text red">THE MORE</div>
                <div class="text red">YOU GET!</div>
            </div>
        </div>

        <div class="level-2">
            <img src="../assets/img/work-hard.svg" alt="photo-2">
            <div class="level-2-text">
                <h1>ACHIEVE</h1>
                <h1>CODECOOL QUESTS</h1>
                <p>Lorem ipsum – tekst składający się z łacińskich i quasi-łacińskich wyrazów, mający korzenie w klasycznej łacinie, wzorowany na fragmencie traktatu Cycerona</p> 

                <p>„O granicach dobra i zła” napisanego w 45 p.n.e. </p>
                    
                <p>Tekst jest stosowany do demonstracji krojów pisma, kompozycji kolumny itp</p>

                <button onclick="location.href='quests.jsp'" type="button" class="button">See all quests</button>
            </div>
        </div>

        <div class="level-3">
            <div class="level-3-text">
                <h1>EXCHANGE COINS</h1>
                <h1>FOR AWESOME</h1>
                <h1>ARTIFACTS</h1>
                <p>Lorem ipsum – tekst składający się z łacińskich i quasi-łacińskich wyrazów, mający korzenie w klasycznej łacinie, wzorowany na fragmencie traktatu Cycerona</p> 

                <p>„O granicach dobra i zła” napisanego w 45 p.n.e. </p>
                    
                <p>Tekst jest stosowany do demonstracji krojów pisma, kompozycji kolumny itp</p>

                <button onclick="location.href='artifacts.jsp'" type="button" class="button-red">What can you buy?</button>
            </div>
            <img src="../assets/img/dashboard-codecooler-3.svg" alt="photo-3">
        </div>

        <div class="level-4">
            <img src="../assets/img/dashboard-codecooler-4.svg"  alt="photo-4">
            <div class="level-4-text">
                <h1>SHOP</h1>
                <h1>WITH YOUR TEAM</h1>
                <p>Lorem ipsum – tekst składający się z łacińskich i quasi-łacińskich wyrazów, mający korzenie w klasycznej łacinie, wzorowany na fragmencie traktatu Cycerona</p> 

                <p>„O granicach dobra i zła” napisanego w 45 p.n.e. </p>
                    
                <p>Tekst jest stosowany do demonstracji krojów pisma, kompozycji kolumny itp</p>

                <button onclick="location.href='my-team.jsp'" type="button" class="button">See your team</button>
            </div>
        </div>
    </div>

    <jsp:include page="../html-common/footer.html" />
</body>
</html>