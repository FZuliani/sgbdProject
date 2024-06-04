CREATE DATABASE Hollywood;

-- Create actors table
CREATE TABLE actors (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    birthdate DATE NOT NULL
);

-- Insert actor records
INSERT INTO actors (id, name, surname, birthdate) VALUES
(1, 'Leonardo', 'DiCaprio', '1974-11-11'),
(2, 'Joseph', 'Gordon-Levitt', '1981-02-17'),
(3, 'Elliot', 'Page', '1987-02-21'),
(4, 'Tom', 'Hardy', '1977-09-15');

-- Create films table
CREATE TABLE films (
    id INT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    releaseDate DATE NOT NULL,
    duration VARCHAR(50) NOT NULL,
    genre VARCHAR(255) NOT NULL,
    director VARCHAR(255) NOT NULL,
    description TEXT NOT NULL
);

-- Insert film records
INSERT INTO films (id, title, releaseDate, duration, genre, director, description) VALUES
(1, 'Inception', '2010-07-16', '148 min', 'Science Fiction', 'Christopher Nolan', 'A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.'),
(2, 'The Matrix', '1999-03-31', '136 min', 'Action, Science Fiction', 'Lana Wachowski, Lilly Wachowski', 'A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.'),
(3, 'Interstellar', '2014-11-07', '169 min', 'Adventure, Drama, Science Fiction', 'Christopher Nolan', 'A team of explorers travel through a wormhole in space in an attempt to ensure humanity\'s survival.');

-- Create film_actors join table
CREATE TABLE film_actors (
    film_id INT,
    actor_id INT,
    character_name VARCHAR(255),
    PRIMARY KEY (film_id, actor_id),
    FOREIGN KEY (film_id) REFERENCES films(id),
    FOREIGN KEY (actor_id) REFERENCES actors(id)
);

-- Insert film-actor relationships
INSERT INTO film_actors (film_id, actor_id, character_name) VALUES
(1, 1, 'Cobb'),
(1, 2, 'Arthur'),
(1, 3, 'Ariadne'),
(1, 4, 'Eames'),
(2, 1, 'Neo'),
(2, 2, 'Morpheus'),
(2, 3, 'Trinity'),
(2, 4, 'Agent Smith'),
(3, 1, 'Cooper'),
(3, 2, 'Brand'),
(3, 3, 'Murph'),
(3, 4, 'Professor Brand');
