DROP TABLE IF EXISTS level CASCADE;
DROP TABLE IF EXISTS cms_user CASCADE;
DROP TABLE IF EXISTS class CASCADE;
DROP TABLE IF EXISTS wallet CASCADE;
DROP TABLE IF EXISTS team CASCADE;
DROP TABLE IF EXISTS codecooler CASCADE;
DROP TABLE IF EXISTS quest CASCADE;
DROP TABLE IF EXISTS codecooler_quests CASCADE;
DROP TABLE IF EXISTS artifact CASCADE;
DROP TABLE IF EXISTS codecooler_artifacts CASCADE;
DROP TABLE IF EXISTS team_artifacts CASCADE;

CREATE TABLE level(
	level_id serial PRIMARY KEY UNIQUE,
	name varchar(30) NOT NULL,
	description varchar(100) NOT NULL,
	price integer NOT NULL,
	date_of_adding date NOT NULL DEFAULT CURRENT_DATE,
	picture_url varchar(100)
);

CREATE TABLE cms_user(
	cms_user_id serial PRIMARY KEY UNIQUE,
	name varchar(25) NOT NULL,
	email varchar(25) NOT NULL,
	password varchar(25) NOT NULL,
	city varchar(25) NOT NULL,
	date_of_adding date NOT NULL DEFAULT CURRENT_DATE,
	picture_url varchar(100),
	is_admin BOOLEAN NOT NULL
);

CREATE TABLE class(
	class_id serial PRIMARY KEY UNIQUE,
	name varchar(100) NOT NULL,
	city varchar(25) NOT NULL,
	start_date date NOT NULL,
	end_date date NOT NULL
);

CREATE TABLE wallet(
	wallet_id serial PRIMARY KEY UNIQUE,
	coins_total integer NOT NULL DEFAULT 0,
	coins_available integer NOT NULL DEFAULT 0
);

CREATE TABLE team(
	team_id serial PRIMARY KEY UNIQUE,
	name varchar(25) NOT NULL,
	city varchar(25) NOT NULL,
	start_date date NOT NULL
);

CREATE TABLE codecooler(
	codecooler_id serial PRIMARY KEY UNIQUE,
	name varchar(25) NOT NULL,
	email varchar(25) NOT NULL,
	password varchar(25) NOT NULL,
	city varchar(25) NOT NULL,
	date_of_adding date NOT NULL DEFAULT CURRENT_DATE,
	picture_url varchar(100),
	class_id integer DEFAULT NULL,
	team_id integer DEFAULT NULL,
	wallet_id serial NOT NULL,
	CONSTRAINT codecooler_class_id FOREIGN KEY (class_id)
	REFERENCES class (class_id)
	ON UPDATE CASCADE
	ON DELETE SET NULL,
	CONSTRAINT codecooler_team_id FOREIGN KEY (team_id)
	REFERENCES team (team_id)
	ON UPDATE CASCADE
	ON DELETE SET NULL,
	CONSTRAINT codecooler_wallet_id FOREIGN KEY (wallet_id)
	REFERENCES wallet (wallet_id)
	ON UPDATE CASCADE
);

CREATE TABLE quest(
	quest_id serial PRIMARY KEY UNIQUE,
	name varchar(50) NOT NULL,
	description varchar(100) NOT NULL,
	value integer,
	type varchar(20) NOT NULL,
	date_of_adding date NOT NULL DEFAULT CURRENT_DATE,
	picture_url varchar(100)
);


CREATE TABLE codecooler_quests(
	codecooler_quests_id serial PRIMARY KEY UNIQUE,
	codecooler_id integer,
	quest_id integer,
	date_of_achieving date NOT NULL DEFAULT CURRENT_DATE,
	CONSTRAINT codecooler_quests_codecooler_id FOREIGN KEY (codecooler_id)
	REFERENCES codecooler(codecooler_id)
	ON UPDATE CASCADE,
	CONSTRAINT codecooler_quests_quest_id FOREIGN KEY (quest_id)
 	REFERENCES quest(quest_id)
	ON UPDATE CASCADE
);

CREATE TABLE artifact(
	artifact_id serial PRIMARY KEY UNIQUE,
	name varchar(50),
	description varchar(100),
	value integer,
	type varchar(25),
	date_of_adding date NOT NULL DEFAULT CURRENT_DATE,
	picture_url varchar(100)
);

CREATE TABLE codecooler_artifacts(
	codecooler_artifacts_id serial PRIMARY KEY UNIQUE,
	codecooler_id integer,
	artifact_id integer,
	date_of_buying date NOT NULL DEFAULT CURRENT_DATE,
	is_used boolean DEFAULT FALSE,
	CONSTRAINT codecooler_artifacts_codecooler_id FOREIGN KEY(codecooler_id)
	REFERENCES codecooler(codecooler_id)
	ON UPDATE CASCADE
	ON DELETE CASCADE,
	CONSTRAINT codecooler_artifacts_artifact_id FOREIGN KEY(artifact_id)
	REFERENCES artifact(artifact_id)
	ON UPDATE CASCADE
	ON DELETE CASCADE
	);

CREATE TABLE team_artifacts(
	team_artifacts_id serial PRIMARY KEY UNIQUE,
	team_id integer,
	artifact_id integer,
	date_of_buying date NOT NULL DEFAULT CURRENT_DATE,
	is_used boolean DEFAULT FALSE,
	CONSTRAINT team_artifacts_team_id FOREIGN KEY(team_id)
	REFERENCES team(team_id)
	ON UPDATE CASCADE
	ON DELETE CASCADE,
	CONSTRAINT team_artifacts_artifact_id FOREIGN KEY(artifact_id)
 	REFERENCES artifact(artifact_id)
	ON UPDATE CASCADE
	ON DELETE CASCADE
);
