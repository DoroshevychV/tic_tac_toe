package com.tic_tac_toe.controller.gamer.game;

import com.google.gson.Gson;
import com.tic_tac_toe.domain.model.Gamer;
import com.tic_tac_toe.dto.response.GamerDetailsDTO;
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
 * @since   2018-04-15
 * @servlet_url /gamer/game
 */
public class GameController extends HttpServlet {

    /**
     * Instance for access to the service
     */
    private GamerService gamerService;

    /**
     * Handles the HTTP GET method.
     * The method verifies whether the authenticated user and returns the corresponding page
     * @param req - servlet request
     * @param resp - servlet response
     * @return if gamer is authentic - return /gamer/game, else - redirect to /gamer/login
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        gamerService = new GamerService();
        GamerDetailsDTO gamerDetailsDTO = gamerService.getGamerDetails(cookies);
        if (gamerDetailsDTO != null) {
            req.getRequestDispatcher("/game.html").forward(req,resp);
        }else{
            resp.sendRedirect(req.getContextPath() + "/gamer/login");
        }
    }

    /**
     * Handles the HTTP POST method.
     * This method performs the enrollment of the result of the game.
     * Accepts the parameter "number" if it is 1 - a win, if 0 is a draw, and if -1 is a loss.
     * In return, we pass the object, already with the changed parameters using json.
     * @param req - servlet request
     * @param resp - servlet response
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        GamerDetailsDTO gamerDetailsDTO = gamerService.getGamerDetails(req.getCookies());
        Gamer gamer = gamerService.getGamerByNickName(gamerDetailsDTO.getNickName());
        int result = Integer.parseInt(req.getParameter("number"));
        gamerDetailsDTO = gamerService.setDetail(gamer.getNickName(),gamer.getgPassword(),result);
        if (gamerDetailsDTO != null) {
            String json = new Gson().toJson(gamerDetailsDTO);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(json);
        }else{
            throw  new IllegalArgumentException("Unknown error, reload page and try again");
        }
    }
}
