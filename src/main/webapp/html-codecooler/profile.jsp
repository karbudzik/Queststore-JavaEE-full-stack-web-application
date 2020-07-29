<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
  <meta charset="utf-8">

  <title>Codecool - your profile</title>
  <meta name="profile" content="profile">
  <link rel='icon' href='../favicon.ico' type='image/x-icon'>
  <link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@600&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="../css/codecooler_profile.css">
  <script src="../js/profile.js" defer></script>
</head>
<body>
    <jsp:include page="../html-common/codecooler-header.jsp" />

    <div class="content">
        
        <h1><span id="label">Magdalena Cielecka</span></h1>

        <div class="level-1">  
            <div id="information"> 
                <h2 id="label-profil">Basic info:</h2>
                <form class="form" oninput="return checkData()">
                    <label>Email*:</label><br>
                    <input type="text" id="e-mail" placeholder="mateusz@gmail.com" ><br>
                </form>
                <div id="information-bottom">
                    <div id="picture">
                        <p>Picture:</p>
                        <img id ="student-photo" src="../assets/icons/profile_photo.svg" alt="profile photo"><br>
                        <a href="#"> <img src="../assets/icons/change_picture.svg" alt="change picture">Change picture</a>
                    </div>
                    <div class="info-student">
                        <p id="city">City: Warsaw</p>
                        <p id="class-name">Class: WAW-2020-FULLSTACK</p>
                    </div>
                    
                </div>
                <button id="button-update" class="button-red" onclick="return displayMessage()" disabled >Update</button>
            </div>

            <div id="change-password">
                <h2 id="label-password">Password change:</h2>
                <form class="form">
                    <label>Old password*:</label><br>
                    <input type="password" id="old-password"><br>
                    <label>New password*:</label><br>
                    <input type="password" id="new-password"><br>
                    <label>Repeat new password*:</label><br>
                    <input type="password" id="repeat-new-password"><br>
                </form>
                <div id="change-password-button">
                    <button class="button-red">Change password</button>
                </div>
                
            </div>
        </div>

        <div class="my-level">
            <div class="my-level-text">
                <h1>Your level:</h1>
                <div id="acctual-level"><img src="../assets/img/levels-img/level4.svg" alt="level-photo"><span>Level IV - platinum cool</span></div>
                <h2>Description:</h2>
                <p class="description">You have been awesome! You are better than a clean gold!</p>
                <div id="next-level">
                    <h2 class="description">You need:</h2>
                    <div id="to-achieve-for-next-level"><img src="../assets/icons/money_icon_red.svg" alt="money-icon">2400 CC</div>
                    <h2 class="description">only, to achieve the next level 'Stealth Ninja'!</h2>
                </div> 
            </div>
        </div>

        <div class="level-2">
            <div class="level-2-text">
                <h1>Your Wallet:</h1>
                <h2>Your total ammount of coins:</h2>
                <div id="acctual-account"><img src="../assets/icons/money_icon_blue.svg" alt="money-icon"> 2600 CC</div>
                <h2>The quests you’ve completed:</h2>
                <div class="items">
                    <div class="item">
                        <p class="title">Find a bug in codecool learning portal</p>
                        <img src="../assets/img/work-hard-no-shadow.svg" alt="work-hard">
                        <p>If you spot the bug, please contact your mentor.
                            He will give you your points if the bug is relevant
                        </p>
                        <p class="price">100 CC</p>
                        <p class="date-buy">10.06.2019</p>
                    </div>
                    
                    <div class="item">
                        <p class="title">Set a SMART goal and achieve it with tutor’s help</p>
                        <img src="../assets/img/workshop.jpg" alt="workshop">
                        <p>If you spot the bug, please contact your mentor.
                            He will give you your points if the bug is relevant
                        </p>
                        <p class="price">100 CC</p>
                        <p class="date-buy">10.06.2019</p>
                    </div>
                </div>

                <h2>The personal artifacts you’ve bought:</h2>
                <div class="items">
                    <div class="item">
                        <p class="title">Your team works from home for a day</p>
                        <img src="../assets/img/welcome.jpg" alt="welcome">
                        <p>If you spot the bug, please contact your mentor.
                             He will give you your points if the bug is relevant
                        </p>
                        <p class="price">100 CC</p>
                        <p class="date-buy">10.06.2019</p>
                        <p class="available">AVAILABLE</p>
                    </div>

                    <div class="item">
                        <p class="title">Your Mentors come to school dressed as pirates</p>
                        <img src="../assets/img/find-bug.jpg" alt="find-bug">
                        <p>If you spot the bug, please contact your mentor.
                             He will give you your points if the bug is relevant
                        </p>
                        <p class="price">100 CC</p>
                        <p class="date-buy">10.06.2019</p>
                        <p class="used">USED</p>
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

                    <div class="item">
                        <p class="title">Your Mentors come to school dressed as pirates</p>
                        <img src="../assets/img/workshop.jpg" alt="workshop">
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

        <div class="level-3">
            <div class="level-3-text">
                <h1>LOOKING FOR YOUR GROUPS STATS?</h1>
                <p>To find your group details, go to “My team” section. 
                    You’ll find your coin statistics there and the artifacts you’ve bought together.
                </p>

                <button onclick="location.href='my-team.jsp'" type="button" class="button-red">Go to "My team"</button>
            </div>
        </div>
    </div>

    <jsp:include page="../html-common/footer.html" />
</body>
</html>