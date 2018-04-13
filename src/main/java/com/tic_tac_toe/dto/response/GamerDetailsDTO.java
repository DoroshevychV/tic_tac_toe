package com.tic_tac_toe.dto.response;

public class GamerDetailsDTO {

    private String nickName;

    private int win;

    private int defeat;

    private int draw;

    public GamerDetailsDTO() {
    }

    public GamerDetailsDTO(String nickName, int win, int defeat, int draw) {
        this.nickName = nickName;
        this.win = win;
        this.defeat = defeat;
        this.draw = draw;
    }

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
