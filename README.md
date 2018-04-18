# Tic-Tac-Toe
#

<b>
  <i>
    The game is written in Java using JDBC, MySQL and Servlets.
Before starting the project, take a few steps:
  </i>
</b>

###

<b>
1.Create database and table in MySQL Workbench :
</b>

###

<p>
  
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

</p>


###

<b>
2.If your user and password to the database is not "Rut", 
then insert your parameters into the appropriate variables by path:
</b>

<i>src/main/java/com/tic_tac_toe/dao/GamerDAO.java</i>


    static final String USER = "Your_user";

    static final String PASSWORD = "Your_password";


###

<b>
  
3.Start-class(Main):

</b>

###

    src/main/java/com/tic_tac_toe/config/TomcatConfiguration.java > public static void main(String[] args)


<b>
  Description(UA)
</b>
  
###

Продовження гри <a href="https://github.com/DoroshevychV/tic-tac-toe">Хрестики-Нулики</a>

###

<b>
  Добавлено:
</b>

###

Новий дизайн гри,сторінка реєстрації, сторінка логінування, сторінка гри, та сторінки, які відчиняються при помилці логінування або реєстрації. Покращений алгоритм визначення вільних комірок в грі, скорочений та більш зрозумілий код js.
Перед початком гри, користувач повинен зареєструватися або увійти, всі дані аутентифікованого користувача зберігаються в Cookie(Нікнейм та пароль), це звісно найгірший спосіб тримати пароль на клієнті(а не токен), але для цього вже треба писати Security...(А это уже совсем другая история...(Л.Каневський)). Користувач не може відкрити шлях /gamer/registration або gamer/login , якщо він вже є аутентифікований, тому що на цих сервлетах стоїть перевірка на аутентифікацію і якщо програма побачить що користувач аутентифікований - його перенаправить сервлет на головну сторінку, АЛЕ є і мінус, користувач може достатись(вручну) до сторінок signUp.html та signIn.html(щоб уникнути цього, можна поставити ajax запити на цих сторінках, які при певній відповіді сервера будуть перенапрвляти клієнта на певну сторінку але це ще два зайві запити, які я не хотів впроваджувати).Також неаутентифікований користувач не має доступу до сторінки з грою, сервлет перевіряє Cookie користувача та при певному результаті віддає відповідну сторінку(якщо неаутентифікований, то видає сторінку /gamer/login, якщо аутентифікований - /gamer/game , де знаходиться сама гра, та виведені деталі користувача (нікнейм, перемоги, програші та нічиї))

###

<i>
  Back-end поділений на пакети Controller-Service-DAO-Domain-DTO-Config
</i>

###

<b>Controller</b> - Це класи-сервлети, які відповідають на певні HTTP-запити(url-path), в контроллерах відбувається прийом запитів, і в певному контроллері вже передається в "підвал" - Service

###

<b>Service</b> - Клас, який містить в собі всю бізнес-логіку проекта. Для додавання/витягування/оновлення інформації користувача в базі даних, він використовує клас DAO(<a href="https://uk.wikipedia.org/wiki/Data_access_object" target="_blank">Data Access Object(Wiki)</a>)

###

<b>DAO</b> - Клас, за допомогою якого програма здійснює доступ до бази даних(через JDBC API)

###

<b>Domain</b> - Клас-сутність(entity). Не містить в собі логіку, тільки поля, конструктор та методи доступу до даних!

###

<b>DTO</b> - Скорочений варіант класа-entity, щоб не передавати непотрібні дані, покращуючи цим продуктивність програми!

###

<b>Config</b> - Конфігурації програми. В даному випадку містить в собі метод <i>main</i> , який одночасно являється точкою входу в програму та конфігурацією Tomcat
