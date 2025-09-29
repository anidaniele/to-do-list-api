CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password CHAR(60) NOT NULL
);

CREATE TABLE IF NOT EXISTS `users` (

    `id` int AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(255),
    `email` varchar(255),
    `password` char(60)

)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password CHAR(60) NOT NULL
    role VARCHAR(50) NOT NULL
);
