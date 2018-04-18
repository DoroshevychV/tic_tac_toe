# Tic-Tac-Toe
#

The game is written in Java using JDBC and Servlets.
Before starting the project, take a few steps:
###

1.Create database and table in MySQL Workbench :
###

create database tictactoe;

use tictactoe;

create table Gamer(

id int primary key auto_increment,

nickName varchar(28),

gPassword varchar(28),

win INT,

defeat INT,

draw INT

);

###

2.Start-class(Main):
src > main > config > TomcatConfiguration.java > public static void main(String[] args)
