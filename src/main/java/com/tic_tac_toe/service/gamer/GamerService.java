package com.tic_tac_toe.service.gamer;

import com.tic_tac_toe.dao.GamerDAO;
import com.tic_tac_toe.domain.model.Gamer;
import com.tic_tac_toe.dto.response.GamerDetailsDTO;

import javax.servlet.http.Cookie;


public class GamerService {

    private GamerDAO gamerDAO;

    public boolean saveGamer(Gamer gamer){
        if (gamer != null) {
            if (gamer.getNickName().length() >= 4 && gamer.getNickName().length() <= 28) {
                 gamerDAO= new GamerDAO();
                if(gamerDAO.getGamerByNickName(gamer.getNickName())== null){
                    if (gamer.getgPassword().length() >= 8 && gamer.getgPassword().length() <= 28) {
                        gamer.setWin(0);
                        gamer.setDefeat(0);
                        gamer.setDraw(0);
                        gamerDAO.saveGamer(gamer);
                        return true;
                    } else {
                        throw new IllegalArgumentException("Password may contain from 8 to 28 characters inclusive!");
                    }
                }else{
                    throw new IllegalArgumentException("A user with such a nickname already exists!");
                }
            } else {
                throw new IllegalArgumentException("NickName may contain from 8 to 28 characters inclusive!");
            }
        } else {
            throw new NullPointerException("The Gamer can not be null!");
        }
    }

    public Gamer getGamerByNickName(String nickName){
        gamerDAO = new GamerDAO();
        return gamerDAO.getGamerByNickName(nickName);
    }

    public GamerDetailsDTO getGamerDetails(Cookie[]cookies) {
        String nickName = null;
        String gPassword = null;
        for (Cookie cook : cookies) {
            if (cook.getName().equals("nickName")) {
                nickName = cook.getValue();
            } else if (cook.getName().equals("gPassword")) {
                gPassword = cook.getValue();
            }
        }
        if (nickName != null && gPassword != null) {
            Gamer gamer = getGamerByNickName(nickName);
            if (gamer.getgPassword().equals(gPassword) && gamer.getNickName().equals(nickName)) {
                return new GamerDetailsDTO(gamer.getNickName(),gamer.getWin(), gamer.getDefeat(), gamer.getDraw());
            }
        }
        return null;
    }

    public Gamer logInGamer(String nickName, String gPassword){
        Gamer gamer = getGamerByNickName(nickName);
        if (gamer != null){
            if(gPassword.equals(gamer.getgPassword())){
                return gamer;
            }
        }
        return null;
    }

    public Cookie setGamerCookie(String path,String key, String value){
        Cookie cookie = new Cookie(key, value);
        cookie.setPath(path);
        return cookie;
    }

    public Cookie deleteCookie(String path,String key, String value){
        Cookie cookie = setGamerCookie(path,key,value);
        cookie.setMaxAge(0);
        return cookie;
    }


    public Boolean gamerIsAuthentic(Cookie [] cookies){
        GamerDetailsDTO gamerDetailsDTO = getGamerDetails(cookies);
        if (gamerDetailsDTO != null) {
           return true;
        }else{
            return false;
        }
    }
}
