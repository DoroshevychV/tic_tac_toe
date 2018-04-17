package com.tic_tac_toe.dto.response;
/**
 * The class is a reduced version(DTO) of the class Gamer
 * It is done in order not to transfer the whole gamer to the presentation
 * (in this case, it is not necessary to pass the password)
 * @author  Doroshevych Vadym
 * @version 1.0
 * @since  2018-04-09
 */
public class GamerDetailsDTO {
    /**
     * @see com.tic_tac_toe.domain.model.Gamer - field - nickName
     */
    private String nickName;
    /**
     * @see com.tic_tac_toe.domain.model.Gamer - field - win
     */
    private int win;
    /**
     * @see com.tic_tac_toe.domain.model.Gamer - field - defeat
     */
    private int defeat;
    /**
     * @see com.tic_tac_toe.domain.model.Gamer field - draw
     */
    private int draw;

    /**
     * Empty constructor
     * To create an instance
     */
    public GamerDetailsDTO() {
    }

    /**
     * Constructor with parameters
     * The method initializes the class fields by input parameters
     * @param nickName - users nickname
     * @param win - users victories
     * @param defeat - users losers
     * @param draw - users draw
     */
    public GamerDetailsDTO(String nickName, int win, int defeat, int draw) {
        this.nickName = nickName;
        this.win = win;
        this.defeat = defeat;
        this.draw = draw;
    }
    /**
     * @see com.tic_tac_toe.domain.model.Gamer
     */
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getDefeat() {
        return defeat;
    }

    public void setDefeat(int defeat) {
        this.defeat = defeat;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }
    /**
     * Returns all user information
     * @return nickname,victories,losers,draw
     * in the form of a continuous tape
     */
    @Override
    public String toString() {
        return "GamerDetailsDTO{" +
                "nickName='" + nickName + '\'' +
                ", win=" + win +
                ", defeat=" + defeat +
                ", draw=" + draw +
                '}';
    }
}
