package com.tic_tac_toe.controller.gamer.registration;

import com.tic_tac_toe.domain.model.Gamer;
import com.tic_tac_toe.service.gamer.GamerService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author  Doroshevych Vadym
 * @version 1.0
 * @since   2018-04-12
 * @servlet_url /gamer/registration
 */
public class GamerRegistrationController extends HttpServlet {
    /**
     * Instance for access to the service
     */
    private GamerService gamerService;
    /**
     * Handles the HTTP GET method.
     *
     * The method responds to the HTTP GET request,
     * which in return shows the user the registration page
     * (signUp.html)
     * If the user is authenticated - redirect to the main page
     * (index.html(path"/"))
     * @param req - servlet request
     * @param resp - servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        gamerService = new GamerService();

        try {
            if (gamerService.gamerIsAuthentic(req.getCookies())) {
                resp.sendRedirect(req.getContextPath() + "/");
            } else {
                req.getRequestDispatcher("/signUp.html").forward(req, resp);
            }
        }catch (NullPointerException e){
            req.getRequestDispatcher("/signUp.html").forward(req, resp);
        }
    }
    /**
     * Handles the HTTP POST method.
     *
     * The method responds to the HTTP POST request,
     * and sends registration parameters to the service().
     *
     * @param req - servlet request(In it we pass the Gamer who must register)
     * @param resp - servlet response(In this we return the Ga (if it has been successfully registered))
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
        if(saveGamer){
            resp.addCookie(gamerService.setGamerCookie("/","nickName",gamer.getNickName()));
            resp.addCookie(gamerService.setGamerCookie("/","gPassword",gamer.getgPassword()));
            resp.sendRedirect(req.getContextPath() + "/gamer/game");
        }else{
            req.getRequestDispatcher("/gamer/registration/error").forward(req, resp);
        }
    }
}
