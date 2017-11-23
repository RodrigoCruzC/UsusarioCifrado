 drop database if exists CIFRADO;
 create database CIFRADO;
use CIFRADO;

create table usuario
(usuauioCifrado varchar(255),
contraseñaCifrada varchar(255),
usuauio varchar(255),
contraseña varchar(255)
);

select * from usuario;