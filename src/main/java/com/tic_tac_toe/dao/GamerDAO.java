package com.tic_tac_toe.dao;

import com.tic_tac_toe.domain.model.Gamer;
import com.tic_tac_toe.dto.response.GamerDetailsDTO;

import java.sql.*;
/**Simple dao for Gamer
 * @author  Doroshevych Vadym
 * @version 1.0
 * @since   2018-04-11
 */
public class GamerDAO {
    /**
     * static database link
     */
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/tictactoe";
    /**
     * static jdbc-driver path
     */
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    /**
     * static login to database
     */
    static final String USER = "root";
    /**
     * static password to database
     */
    static final String PASSWORD = "root";

    /**
     *Method to store users in a database
     * @param gamer - Gamer who needs to be registered
     */
    public void saveGamer(Gamer gamer) {
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

            preparedStatement.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     *A method for searching a user in a database by nickName
     * @param nickName - The name of the user you want to find
     * @return Found user
     */
    public Gamer getGamerByNickName(String nickName) {
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
                if (resultSet.next()) {
                    id = resultSet.getInt("id");
                    nick = resultSet.getString("nickName");
                    gPassword = resultSet.getString("gPassword");
                    win = resultSet.getInt("win");
                    defeat = resultSet.getInt("defeat");
                    draw = resultSet.getInt("draw");
                } else {
                    resultSet.close();
                    statement.close();
                    connection.close();
                    return null;
                }

                gamer = new Gamer(id, nick, gPassword, win, defeat, draw);

                resultSet.close();
                ps.close();
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

    /**
     * Method for updating gamer information
     * @param nickName - The nickName of the gamer whose information you want to change
     * @param gPassword - The password of the gamer whose information you want to change,
     *                  for the need to make sure that the user has the right to change information
     * @param result - The numerical value by which we determine which parameter to change.
     *               If 1 is a field(win) , if 0 is a field(draw), if -1 is a field(defeat)
     * @param number - The numeric value that is inserted in the field
     * @return - Class with changed user information
     */
    public GamerDetailsDTO setDeatails(String nickName, String gPassword, int result,int number) {
        Connection connection;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            String sSQL = "";
            if(result == 1){
                sSQL = "UPDATE Gamer SET win = ? WHERE nickName = ? AND gPassword = ?";
            }else if(result == 0){
                sSQL = "UPDATE Gamer SET draw = ? WHERE nickName = ? AND gPassword = ?";
            }else{
                sSQL = "UPDATE Gamer SET defeat = ? WHERE nickName = ? AND gPassword = ?";
            }
            PreparedStatement preparedStatement = connection.prepareStatement(sSQL);
            preparedStatement.setInt(1, number);
            preparedStatement.setString(2, nickName);
            preparedStatement.setString(3, gPassword);
            preparedStatement.executeUpdate();

            Gamer gamer = getGamerByNickName(nickName);
            preparedStatement.close();
            connection.close();
            return new GamerDetailsDTO(gamer.getNickName(),gamer.getWin(),gamer.getDefeat(),gamer.getDraw());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
