package com.tic_tac_toe.dao;

import com.tic_tac_toe.domain.model.Gamer;

import java.sql.*;

public class GamerDAO {

    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/tictactoe";

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    static final String USER = "root";

    static final String PASSWORD = "root";


    public void saveGamer(Gamer gamer){

        Connection connection;

        try {
            Class.forName(JDBC_DRIVER);

            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Gamer(nickName,gPassword,win,defeat,draw)" +
                    " VALUE (?,?,?,?,?)");
            preparedStatement.setString(1, gamer.getNickName());
            preparedStatement.setString(2, gamer.getgPassword());
            preparedStatement.setInt(3, gamer.getWin());
            preparedStatement.setInt(4, gamer.getDefeat());
            preparedStatement.setInt(5, gamer.getDraw());
            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Gamer getGamerByNickName(String nickName){

        Connection connection;
        Statement statement;
        Gamer gamer;
        try {
            Class.forName(JDBC_DRIVER);

            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            ResultSet resultSet;

            try {
                statement = connection.createStatement();

                String sSQL = "SELECT * FROM Gamer WHERE nickName = ?";
                PreparedStatement ps = connection.prepareStatement(sSQL);
                ps.setObject(1, nickName);
                resultSet = ps.executeQuery();


                int id, win, defeat, draw;
                String nick, gPassword;
                if(resultSet.next()){
                        id = resultSet.getInt("id");
                        nick = resultSet.getString("nickName");
                        gPassword = resultSet.getString("gPassword");
                        win = resultSet.getInt("win");
                        defeat = resultSet.getInt("defeat");
                        draw = resultSet.getInt("draw");
                }else{
                    resultSet.close();
                    statement.close();
                    connection.close();
                    return null;
                }

                gamer = new Gamer(id, nick, gPassword, win, defeat, draw);


                resultSet.close();
                statement.close();
                connection.close();

                return gamer;
            } finally {
                connection.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
