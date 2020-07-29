<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Codecool portal - your team's details</title>
  <meta name="my-team" content="Team page">
  <link rel='icon' href='../favicon.ico' type='image/x-icon'>
  <link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@600&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="../css/codecooler_my-team.css">
</head>
<body>
    <jsp:include page="../html-common/codecooler-header.jsp" />

    <div class="content">

        <div class="level-1">
            <img src="../assets/img/mountains.svg" alt="photo-1">
            <div class="texts">
                <div class="text blue">ENJOY IT TOGETHER!</div>
                <div class="text simple">If you gained enough coins for CC quests,
                 you can exchange them for amazing artifacts. See the list below!</div>
            </div>
        </div>

        <div class="level-2">
            <div class="level-2-text">
                <h1>Basic info:</h1>
                <div class="team-name">Super_team_name</div>
                
                <h2>Team members:</h2>
                <div class="users">
                    <div class="user">
                        <p class="name">Magda</p>
                        <p class="surname">Fiołek</p>
                        <img src="../assets/img/user-photo.svg" alt="user-photo">
                        <div class="description">
                            <p>Balance:</p>
                            <p class="price"><img  src="../assets/icons/money_icon.svg" alt="money-icon">1500 CC</p>
                        </div>
                    </div>
                    
                    <div class="user">
                        <p class="name">Magda</p>
                        <p class="surname">Fiołek</p>
                        <img src="../assets/img/user-photo.svg" alt="user-photo">
                        <div class="description">
                            <p>Balance:</p>
                            <p class="price"><img  src="../assets/icons/money_icon.svg" alt="money-icon">1000 CC</p>
                        </div>
                    </div>

                    <div class="user">
                        <p class="name">Magda</p>
                        <p class="surname">Fiołek</p>
                        <img src="../assets/img/user-photo.svg" alt="user-photo">
                        <div class="description">
                            <p>Balance:</p>
                            <p class="price"><img  src="../assets/icons/money_icon.svg" alt="money-icon">100 CC</p>
                        </div>
                    </div>

                    <div class="user">
                        <p class="name">Magda</p>
                        <p class="surname">Fiołek</p>
                        <img src="../assets/img/user-photo.svg" alt="user-photo">
                        <div class="description">
                            <p>Balance:</p>
                            <p class="price"><img  src="../assets/icons/money_icon.svg" alt="money-icon">0 CC</p>
                        </div>
                    </div>
                </div>

                <h2>Your total ammount of coins:</h2>
                <div id="acctual-account"><img src="../assets/icons/money_icon_white.svg" alt="money-icon"> 2600 CC</div>

            </div>
        </div>

        <div class="level-3">
            <div class="level-3-text">
                <h1>Bought artifacts:</h1>

                <div class="items">
                    <div class="item">
                        <p class="title">Your team works from home for a day</p>
                        <img src="../assets/img/work-hard-no-shadow.svg" alt="work-hard">
                        <p>If you spot the bug, please contact your mentor.
                             He will give you your points if the bug is relevant
                        </p>
                        <p class="price">100 CC</p>
                        <p class="date-buy">10.06.2019</p>
                        <p class="available">AVAILABLE</p>
                    </div>

                    <div class="item">
                        <p class="title">Your Mentors come to school dressed as pirates</p>
                        <img src="../assets/img/work-hard-no-shadow.svg" alt="work-hard">
                        <p>If you spot the bug, please contact your mentor.
                             He will give you your points if the bug is relevant
                        </p>
                        <p class="price">100 CC</p>
                        <p class="date-buy">10.06.2019</p>
                        <p class="used">USED</p>
                    </div>

                </div>
            </div>
        </div>

        <div class="level-4">
            <div class="level-4-text">
                <h1>CAN YOU AFFORD MORE?</h1>
                <p>Th price of each artifact is splitted equally between all group members,
                     so each member needs to have enough coins co contribute.
                </p>

                <button onclick="location.href='artifacts.jsp'" type="button" class="button-red">See available artifacts</button>
            </div>
        </div>
    </div>

    <jsp:include page="../html-common/footer.html" />

    <script>
        document.getElementsByClassName('teams-nav')[0].setAttribute('id', 'light-blue-text');
    </script>
</body>
</html>