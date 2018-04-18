# Tic-Tac-Toe
#

<b>
  <i>
    The game is written in Java using JDBC and Servlets.
Before starting the project, take a few steps:
  </i>
</b>

###

<b>
1.Create database and table in MySQL Workbench :
</b>

###

<small>
  
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

</small>

###

<b>
  
2.Start-class(Main):

</b>

###

src > main > config > TomcatConfiguration.java > public static void main(String[] args)


<b>Description(UA)<b>
