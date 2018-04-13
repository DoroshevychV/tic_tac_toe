package com.tic_tac_toe.domain.model;

public class Gamer {
    private int id;

    private String nickName;

    private String gPassword;

    private int win;

    private int defeat;

    private int draw;

    public Gamer() {
    }

    public Gamer(int id, String nickName, String gPassword) {
        this.id = id;
        this.nickName = nickName;
        this.gPassword = gPassword;
    }

    public Gamer(int win, int defeat, int draw) {
        this.win = win;
        this.defeat = defeat;
        this.draw = draw;
    }

    public Gamer(int id, String nickName, String gPassword, int win, int defeat, int draw) {
        this.id = id;
        this.nickName = nickName;
        this.gPassword = gPassword;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getgPassword() {
        return gPassword;
    }

    public void setgPassword(String gPassword) {
        this.gPassword = gPassword;
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
