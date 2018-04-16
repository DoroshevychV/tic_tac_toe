package com.tic_tac_toe.controller.gamer;

import com.tic_tac_toe.domain.model.Gamer;
import com.tic_tac_toe.dto.response.GamerDetailsDTO;
import com.tic_tac_toe.service.gamer.GamerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GamerLoginController extends HttpServlet {

    private GamerService gamerService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        gamerService = new GamerService();
        if(gamerService.gamerIsAuthentic(req.getCookies())){
            resp.sendRedirect(req.getContextPath() + "/");
        }else{
            req.getRequestDispatcher("/signIn.html").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        gamerService = new GamerService();
        Gamer gamer = gamerService.logInGamer(req.getParameter("nickName"), req.getParameter("gPassword"));
        if(gamer != null){
            resp.addCookie(gamerService.setGamerCookie("/","nickName",gamer.getNickName()));
            resp.addCookie(gamerService.setGamerCookie("/","gPassword",gamer.getgPassword()));
//            req.getRequestDispatcher("/game.html").forward(req,resp);
            resp.sendRedirect(req.getContextPath() + "/gamer/game");
        }
    }
}
