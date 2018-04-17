package com.tic_tac_toe.controller.gamer.logout;

import com.tic_tac_toe.service.gamer.GamerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @author  Doroshevych Vadym
 * @version 1.0
 * @since   2018-04-16
 * @servlet_url /gamer/logout
 */
public class GamerLogOutController extends HttpServlet {
    /**
     * Instance for access to the service
     */
    private GamerService gamerService;
    /**
     * Handles the HTTP GET method.
     * Method to exit the user page(delete cookies), and redirects to the homepage("/")
     * @param req - servlet request
     * @param resp - servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        gamerService = new GamerService();
        resp.addCookie(gamerService.deleteCookie("/","nickName",null));
        resp.addCookie(gamerService.deleteCookie("/","gPassword",null));
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
