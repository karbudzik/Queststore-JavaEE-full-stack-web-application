<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Codecooler's quests - win extra coins!</title>
    <meta name="quests" content="get quests">
    <link rel='icon' href='../favicon.ico' type='image/x-icon'>
    <link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../css/codecooler_quests.css">
</head>
<body>
    <jsp:include page="../html-common/codecooler-header.jsp" />

    <div class="content">

        <div class="level-1">
            <img src="../assets/img/quest-image.svg" alt="photo-1">
            <div class="texts">
                <div class="text red">GO ON A QUEST!</div>
                <div class="text simple">If you gained enough coins for CC quests,
                 you can exchange them for amazing artifacts.</div>
                <div class="text simple">See the list below!</div>
            </div>
        </div>

        <div class="level-2">
            <div class="level-2-text">
                <h1>Basic quests:</h1>

                <div class="items">
                    <div class="item">
                        <p class="title">Find a bug on Codecool learning platform</p>
                        <img src="../assets/img/find-bug.jpg" alt="find-bug">
                        <p class="description">If you spot the bug, please contact your mentor.
                             He will give you your points if the bug is relevant
                        </p>
                        <p class="price">100 CC</p>
                    </div>

                    <div class="item">
                        <p class="title">Taking part in the student screening process</p>
                        <img src="../assets/img/welcome.jpg" alt="welcome">
                        <p class="description">If you spot the bug, please contact your mentor.
                             He will give you your points if the bug is relevant
                        </p>
                        <p class="price">100 CC</p>
                    </div>

                    <div class="item">
                        <p class="title">Organizing a workshop for other students</p>
                        <img src="../assets/img/workshop.jpg" alt="workshop">
                        <p class="description">If you spot the bug, please contact your mentor.
                             He will give you your points if the bug is relevant
                        </p>
                        <p class="price">100 CC</p>
                    </div>

                    <div class="item">
                        <p class="title">Find a bug on Codecool learning platform</p>
                        <img src="../assets/img/work-hard-no-shadow.svg" alt="work-hard">
                        <p class="description">If you spot the bug, please contact your mentor.
                             He will give you your points if the bug is relevant
                        </p>
                        <p class="price">100 CC</p>
                    </div>
                </div>
            </div>
        </div>

        <div class="level-3">
            <div class="level-3-text">
                <h1>Extra quests:</h1>

                <div class="items">
                    <div class="item">
                        <p class="title">Find a bug on Codecool learning platform</p>
                        <img src="../assets/img/work-hard-no-shadow.svg" alt="work-hard">
                        <p class="description">If you spot the bug, please contact your mentor.
                             He will give you your points if the bug is relevant
                        </p>
                        <p class="price">100 CC</p>
                    </div>

                    <div class="item">
                        <p class="title">Find a bug on Codecool learning platform</p>
                        <img src="../assets/img/find-bug.jpg" alt="find-bug">
                        <p class="description">If you spot the bug, please contact your mentor.
                             He will give you your points if the bug is relevant
                        </p>
                        <p class="price">100 CC</p>
                    </div>

                    <div class="item">
                        <p class="title">Find a bug on Codecool learning platform</p>
                        <img src="../assets/img/work-hard-no-shadow.svg" alt="work-hard">
                        <p class="description">If you spot the bug, please contact your mentor.
                             He will give you your points if the bug is relevant
                        </p>
                        <p class="price">100 CC</p>
                    </div>

                    <div class="item">
                        <p class="title">Find a bug on Codecool learning platform</p>
                        <img src="../assets/img/workshop.jpg" alt="workshop">
                        <p class="description">If you spot the bug, please contact your mentor.
                             He will give you your points if the bug is relevant
                        </p>
                        <p class="price">100 CC</p>
                    </div>

                    <div class="item">
                        <p class="title">Find a bug on Codecool learning platform</p>
                        <img src="../assets/img/find-bug.jpg" alt="find-bug">
                        <p class="description">If you spot the bug, please contact your mentor.
                             He will give you your points if the bug is relevant
                        </p>
                        <p class="price">100 CC</p>
                    </div>

                    <div class="item">
                        <p class="title">Find a bug on Codecool learning platform</p>
                        <img src="../assets/img/work-hard-no-shadow.svg" alt="work-hard">
                        <p class="description">If you spot the bug, please contact your mentor.
                             He will give you your points if the bug is relevant
                        </p>
                        <p class="price">100 CC</p>
                    </div>

                    <div class="item">
                        <p class="title">Find a bug on Codecool learning platform</p>
                        <img src="../assets/img/workshop.jpg" alt=workshop>
                        <p class="description">If you spot the bug, please contact your mentor.
                             He will give you your points if the bug is relevant
                        </p>
                        <p class="price">100 CC</p>
                    </div>
                </div>
            </div>
        </div>

        <div class="level-4">
            <div class="level-4-text">
                <h1>QUESTS ALREADY ACHIEVED?</h1>
                <p>If you achieved the quest and contacted your mentor, 
                    the coins should appear in your account.
                </p>

                <button onclick="location.href='profile.jsp'" type="button" class="button">Go to my account</button>
            </div>
        </div>
    </div>

    <jsp:include page="../html-common/footer.html" />

    <script>
        document.getElementsByClassName('quests-nav')[0].setAttribute('id', 'red-text');
    </script>
</body>
</html>