CREATE DATABASE IF NOT EXISTS pomodoro_db;

USE pomodoro_db;

CREATE TABLE IF NOT EXISTS pomodoro_db.USERS (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS pomodoro_db.PROJECTS(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    user_id INT NOT NULL
);

CREATE TABLE IF NOT EXISTS pomodoro_db.PROJECTS_SESSIONS(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    start_time VARCHAR(255) NOT NULL,
    end_time VARCHAR(255),
    counter INT NOT NULL,
    user_id INT NOT NULL,
    project_id INT NOT NULL
);