package com.tic_tac_toe.domain.model;
/**
 * This class is entity
 * @author  Doroshevych Vadym
 * @version 1.0
 * @since  2018-04-09
 */
public class Gamer {
    /**
     * Users id(authoincrement in database)
     */
    private int id;
    /**
     * Users nickname(Unique user field. Users not having the same nickname)
     */
    private String nickName;
    /**
     * Secret password
     */
    private String gPassword;
    /**
     * Counter Wins
     */
    private int win;
    /**
     * Counter Defeat
     */
    private int defeat;
    /**
     * Counter Draw
     */
    private int draw;

    /**
     * Empty constructor
     * To create an instance
     */
    public Gamer() {
    }

    /**
     * Constructor with parameters
     * the method initializes the class fields by input parameters
     * @param id - users id
     * @param nickName - users nickname
     * @param gPassword - users password
     * @param win - users victories
     * @param defeat - users losers
     * @param draw - users draw
     */
    public Gamer(int id, String nickName, String gPassword, int win, int defeat, int draw) {
        this.id = id;
        this.nickName = nickName;
        this.gPassword = gPassword;
        this.win = win;
        this.defeat = defeat;
        this.draw = draw;
    }

    /**
     * Returns the nickname of the player
     * @return nickname
     */
    public String getNickName() {
        return nickName;
    }

    /**
     *Changes the player's nickname
     * @param nickName - Nickname, which will be instead of the previous one
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * Returns id of the player
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     *Changes the player's id
     * @param id - id, which will be instead of the previous one
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Returns password of the player
     * @return password
     */
    public String getgPassword() {
        return gPassword;
    }
    /**
     *Changes the player's password
     * @param gPassword - password, which will be instead of the previous one
     */
    public void setgPassword(String gPassword) {
        this.gPassword = gPassword;
    }
    /**
     * Returns victories of the player
     * @return victories
     */
    public int getWin() {
        return win;
    }
    /**
     *Changes the player's victories
     * @param win - victories value, which will be instead of the previous one
     */
    public void setWin(int win) {
        this.win = win;
    }
    /**
     * Returns losers of the player
     * @return losers
     */
    public int getDefeat() {
        return defeat;
    }
    /**
     *Changes the player's losers
     * @param defeat - losers, which will be instead of the previous one
     */
    public void setDefeat(int defeat) {
        this.defeat = defeat;
    }
    /**
     * Returns draw of the player
     * @return draw
     */
    public int getDraw() {
        return draw;
    }
    /**
     *Changes the player's draw
     * @param draw - draw, which will be instead of the previous one
     */
    public void setDraw(int draw) {
        this.draw = draw;
    }

    /**
     * Returns all user information
     * @return id,nickname,password,victories,losers,draw
     * in the form of a continuous tape
     */
    @Override
    public String toString() {
        return "Gamer{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", gPassword='" + gPassword + '\'' +
                ", win=" + win +
                ", defeat=" + defeat +
                ", draw=" + draw +
                '}';
    }
}
