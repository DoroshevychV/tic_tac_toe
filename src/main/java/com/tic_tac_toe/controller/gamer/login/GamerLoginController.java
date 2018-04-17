package com.tic_tac_toe.controller.gamer.login;

import com.google.gson.Gson;
import com.tic_tac_toe.domain.model.Gamer;
import com.tic_tac_toe.service.gamer.GamerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Doroshevych Vadym
 * @version 1.0
 * @servlet_url /gamer/login
 * @since 2018-04-13
 */
public class GamerLoginController extends HttpServlet {
    /**
     * Instance for access to the service
     */
    private GamerService gamerService;

    /**
     * Handles the HTTP GET method.
     * <p>
     * The method responds to the HTTP GET request,
     * which in return shows the user the login page
     * (signIn.html)
     * If the user is authenticated - redirect to the main page
     * (index.html(path"/"))
     *
     * @param req  - servlet request
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
                req.getRequestDispatcher("/signIn.html").forward(req, resp);
            }
        }catch (NullPointerException e){
            req.getRequestDispatcher("/signIn.html").forward(req, resp);
        }
    }

    /**
     * Handles the HTTP POST method.
     * (Add new author to database)
     * Method for user authentication
     * The method responds to the HTTP POST request,
     * and sends login parameters to the service().
     *
     * @param req  - servlet request(In it we pass the Gamer who must register)
     * @param resp - servlet response(In this we return the UserLoginDTO (if it has been successfully registered))
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        gamerService = new GamerService();
        Gamer gamer = gamerService.logInGamer(req.getParameter("nickName"), req.getParameter("gPassword"));
        if (gamer != null) {
            resp.addCookie(gamerService.setGamerCookie("/", "nickName", gamer.getNickName()));
            resp.addCookie(gamerService.setGamerCookie("/", "gPassword", gamer.getgPassword()));
            resp.sendRedirect(req.getContextPath() + "/gamer/game");
        }else{
            PrintWriter out = resp.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Nickname or password is incorrect.');");
            out.println("</script>");
            resp.sendRedirect(req.getContextPath() + "/gamer/login");
        }
    }
}
