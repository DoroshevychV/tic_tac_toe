package com.tic_tac_toe.service.gamer;

import com.tic_tac_toe.dao.GamerDAO;
import com.tic_tac_toe.domain.model.Gamer;
import com.tic_tac_toe.dto.response.GamerDetailsDTO;
import javax.servlet.http.Cookie;
/**
 * A service in which most logics are written
 * @author  Doroshevych Vadym
 * @version 1.0
 * @since  2018-04-09
 */
public class GamerService {
    /**
     * Instance for access to the dao
     */
    private GamerDAO gamerDAO;

    /**
     * Add new author to database
     * @param gamer - Gamer who needs to be registered
     * @return If the user has successfully added - we return the truth, the other - is not
     */
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

    /**
     *Find a gamer by nickname
     * @param nickName - The nickname of the gamer to be found
     * @return Found a gamer
     */
    public Gamer getGamerByNickName(String nickName){
        gamerDAO = new GamerDAO();
        return gamerDAO.getGamerByNickName(nickName);
    }

    /**
     * Extract gamer by cookies
     * @param cookies - gamer's cookies
     * @return DTO of the gamer
     */
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

    /**
     * This is a method for authenticating a gamer
     * @param nickName gamer`s nickname
     * @param gPassword gamer`s password
     * @return authenticated gamer
     */
    public Gamer logInGamer(String nickName, String gPassword){
        Gamer gamer = getGamerByNickName(nickName);
        if (gamer != null){
            if(gPassword.equals(gamer.getgPassword())){
                return gamer;
            }
        }
        return null;
    }

    /**
     * The method records user details in cookies
     * @param path - The path to which cookies will be delivered
     * @param key - Cookie's key
     * @param value - Cookie's value
     * @return modified cookies
     */
    public Cookie setGamerCookie(String path,String key, String value){
        Cookie cookie = new Cookie(key, value);
        cookie.setPath(path);
        return cookie;
    }

    /**
     * The method delete user details in cookies
     * @param path - The path to which cookies will be delete
     * @param key - Cookie's key
     * @param value - Cookie's value
     * @return Empty cookies
     */
    public Cookie deleteCookie(String path,String key, String value){
        Cookie cookie = setGamerCookie(path,key,value);
        cookie.setMaxAge(0);
        return cookie;
    }

    /**
     *Verify authenticated user
     * (If the user is authenticated, return the truth, if not a mistake)
     * @param cookies - cookies that contain user information
     * @return true/false
     */
    public Boolean gamerIsAuthentic(Cookie [] cookies){
        GamerDetailsDTO gamerDetailsDTO = getGamerDetails(cookies);
        if (gamerDetailsDTO != null) {
           return true;
        }else{
            return false;
        }
    }

    /**
     * Changes user information
     * @param nickName - user's nickname
     * @param gPassword - user's password
     * @param result - The numerical value by which we determine which parameter to change.
     *                   If 1 is a field(win) , if 0 is a field(draw), if -1 is a field(defeat)
     * @return GamerDetailsDTO(GamerDTO)
     */
    public GamerDetailsDTO setDetail(String nickName,String gPassword,int result){
        gamerDAO = new GamerDAO();
        Gamer gamer = getGamerByNickName(nickName);
        if(gamer.getgPassword().equals(gPassword)){
            int number;
            if(result==1){
                number = gamer.getWin();
            }else if(result == 0){
                number = gamer.getDraw();
            }else{
                number = gamer.getDefeat();
            }
            number++;
            return gamerDAO.setDeatails(nickName,gPassword,result,number);
        }else{
            throw  new IllegalArgumentException("You do not have enough rights");
        }
    }

}
