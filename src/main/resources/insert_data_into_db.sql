INSERT INTO cms_user
VALUES (default,'Michal Kuk','kuk.michal3@gmail.com','admin123','Warsaw',TO_DATE('2009-02-14','YYYY-MM-DD'),'../assets/img/admins-images/penelope-cruz.svg','Y');

INSERT INTO cms_user
VALUES (default,'Jan Truskolaski','jtruskolaski@gmail.com','admin123','Krakow',TO_DATE('2020-05-09','YYYY-MM-DD'),'../assets/img/admins-images/penelope-cruz.svg','Y');

INSERT INTO cms_user
VALUES (default,'Andrzej Szampan','aszampan@gmail.com','admin123','Krakow',TO_DATE('2019-05-02','YYYY-MM-DD'),'../assets/img/admins-images/penelope-cruz.svg','Y');

INSERT INTO cms_user
VALUES (default,'Dominik Nowak','dnowak@gmail.com','admin123','Warsaw',TO_DATE('2020-01-23','YYYY-MM-DD'),'../assets/img/admins-images/penelope-cruz.svg','N');

INSERT INTO cms_user
VALUES (default,'Paweł Suryk','psuryk@gmail.com','admin123','Krakow',TO_DATE('2010-07-05','YYYY-MM-DD'),'../assets/img/admins-images/penelope-cruz.svg','N');


INSERT INTO team
values(default,'Pajtonowi ogarniacze.','Krakow',TO_DATE('2020-07-07','YYYY-MM-DD'));

INSERT INTO team
values(default,'DAO dream team.','Krakow',TO_DATE('2020-07-07','YYYY-MM-DD'));

INSERT INTO team
values(default,'Clean-code team.','Krakow',TO_DATE('2020-07-07','YYYY-MM-DD'));

INSERT INTO team
values(default,'Tic Tac Toe Masters','Warszawa',TO_DATE('2020-04-07','YYYY-MM-DD'));

INSERT INTO team
values(default,'Clean-code team.','Bucharest',TO_DATE('2019-07-07','YYYY-MM-DD'));


INSERT INTO class
VALUES(default,'KRK-2020-06-Fullstack-Weekend','Krakow',TO_DATE('2020-06-01','YYYY-MM-DD'),TO_DATE('2021-06-01','YYYY-MM-DD'));

INSERT INTO class
VALUES(default,'KRK-2020-08-Fullstack-Weekdays','Krakow',TO_DATE('2019-08-01','YYYY-MM-DD'),TO_DATE('2020-08-01','YYYY-MM-DD'));

INSERT INTO class
VALUES(default,'WAW-2019-JAVA-Weekend','Warsaw',TO_DATE('2019-08-01','YYYY-MM-DD'),TO_DATE('2020-08-01','YYYY-MM-DD'));


INSERT INTO level
values(default,'Level 1 - Iron Maiden','Basic level on yuur journey','200',default,'level1.svg');

INSERT INTO level
values(default,'Level 2 - Silver Warrior','You are getting stronger..','500',default,'level2.svg');

INSERT INTO level
values(default,'Level 3 - Golden Level','Higher, higher.','1000',default,'level3.svg');

INSERT INTO level
values(default,'Level 4 - Platinium Warrior','Halfway of the journey.','2500',default,'level4.svg');

INSERT INTO level
values(default,'Level 5 - Stealth Ninja','Almost on the top.','5000',default,'level5.svg');

INSERT INTO level
values(default,'Level 6 - Codecool Buddha','There is nothing more.','10000',default,'level6.svg');



INSERT INTO quest
values(default,'Passing a checkpoint.','You have passed a checkopint.','500','basic',default,'quest_logo_01.svg');

INSERT INTO quest
values(default,'Spot a mistake in the assigment.','You fund a mistake in background materials.','500','extra',default,'quest_logo_02.svg');

INSERT INTO quest
values(default,'Do a demo for the class.','You did a demo for the class.','1000','extra',default,'quest_logo_03.svg');

INSERT INTO quest
values(default,'Take a part in student screening.','Blablabla','5000','extra',default,'quest_logo_04.svg');

INSERT INTO quest
values(default,'Attend a month without being late.','Always on time!','10000','extra',default,'quest_logo_05.svg');

INSERT INTO quest
values(default,'Set a smart Goal with your tutorial.','Blablabla','10000','extra',default,'quest_logo_06.svg');



INSERT INTO artifact
values(default,'Private mentoring.','Blablabla','500','Single',default,'artifacts_1.svg');

INSERT INTO artifact
values(default,'Spend a day on home office.','Blablabla','500','Single',default,'artifacts_2.svg');

INSERT INTO artifact
values(default,'Extend an SI deadline for a week. ','Blablabla','1000','Single',default,'artifacts_3.svg');

INSERT INTO artifact
values(default,'60 minutes workshop by mentor.','Blablabla','5000','Team',default,'artifacts_4.svg');

INSERT INTO artifact
values(default,'Mentor joins student for 1 hour.','Blablabla','10000','Team',default,'artifacts_5.svg');

INSERT INTO artifact
values(default,'Extra materials for chosen topic','Blablabla','10000','Team',default,'artifacts_6.svg');



INSERT INTO wallet
values(default);

INSERT INTO codecooler
values(default,'Paweł Byczek',
'pbyczek@gmail.com',
'pbyczek123',
'Krakow',
TO_DATE('2020-06-01','YYYY-MM-DD'),
'mojezdjecie',
1,
1,
default);

INSERT INTO wallet
values(default);

INSERT INTO codecooler
values(default,'Mariusz Ryba',
'mryba@gmail.com',
'mryba123',
'Krakow',
TO_DATE('2020-06-01','YYYY-MM-DD'),
'mryba.jpg',
1,
1,
default);


INSERT INTO wallet
values(default);

INSERT INTO codecooler
values(default,'Anna Stoch',
'astoch@gmail.com',
'astoch123',
'Krakow',
TO_DATE('2020-06-01','YYYY-MM-DD'),
'astoch.jpg',
1,
1,
default);

INSERT INTO wallet
values(default);

INSERT INTO codecooler
values(default,'Karolina Budzik',
'kbudzik@gmail.com',
'kbudzik123',
'Krakow',
TO_DATE('2020-07-01','YYYY-MM-DD'),
'astoch.jpg',
1,
1,
default);

INSERT INTO wallet
values(default);

INSERT INTO codecooler
values(default,'Patrycja Cebula',
'patacebula@gmail.com',
'pcebula123',
'Warszawa',
TO_DATE('2020-04-01','YYYY-MM-DD'),
'astoch.jpg',
1,
1,
default);

INSERT INTO team_artifacts
values(default, 1, 5, TO_DATE('2020-04-01','YYYY-MM-DD'), FALSE);

INSERT INTO team_artifacts
values(default, 1, 6, TO_DATE('2020-04-05','YYYY-MM-DD'), FALSE);

INSERT INTO team_artifacts
values(default, 1, 4, TO_DATE('2020-06-01','YYYY-MM-DD'), TRUE);

INSERT INTO team_artifacts
values(default, 2, 6, TO_DATE('2020-04-01','YYYY-MM-DD'), FALSE);

INSERT INTO team_artifacts
values(default, 3, 5, TO_DATE('2020-07-01','YYYY-MM-DD'), TRUE);

INSERT INTO team_artifacts
values(default, 3, 4, TO_DATE('2020-04-15','YYYY-MM-DD'), FALSE);
