package com.tic_tac_toe.controller.gamer;

import com.tic_tac_toe.domain.model.Gamer;
import com.tic_tac_toe.service.gamer.GamerService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @author  Doroshevych Vadym
 * @version 1.0
 * @since   2018-04-12
 * @servlet_url /gamer/registration
 */
public class GamerRegistrationController extends HttpServlet {

    private GamerService gamerService;
    /**
     * Handles the HTTP GET method.
     *
     * The method responds to the HTTP GET request,
     * which in return shows the user the registration page
     * (signUp.html)
     * @param req - servlet request
     * @param resp - servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/signUp.html").forward(req,resp);
    }

    /**
     * Handles the HTTP POST method.
     *
     * The method responds to the HTTP POST request,
     * and sends registration parameters to the service().
     *
     * @param req - servlet request(In it we pass the GamerRegistrationDTO who must register)
     * @param resp - servlet response(In this we return the UserLoginDTO (if it has been successfully registered))
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        Gamer gamer = new Gamer();
        gamer.setNickName(req.getParameter("nickName"));
        gamer.setgPassword(req.getParameter("gPassword"));
        gamerService = new GamerService();
        boolean saveGamer = gamerService.saveGamer(gamer);
        gamerService = null;
        if(saveGamer){

            Cookie nickNameCookie = new Cookie( "nickName", gamer.getNickName());
            nickNameCookie.setPath("/");
            Cookie gPasswordCookie = new Cookie( "gPassword", gamer.getgPassword());
            gPasswordCookie.setPath("/");
            resp.addCookie(nickNameCookie);
            resp.addCookie(gPasswordCookie);
            req.getRequestDispatcher("/game.html").forward(req,resp);
        }else{
            throw new IllegalArgumentException("Unknown error, reload page and try again");
        }
    }
}
