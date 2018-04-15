package com.tic_tac_toe.controller.gamer.game;

import com.tic_tac_toe.dto.response.GamerDetailsDTO;
import com.tic_tac_toe.service.gamer.GamerService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GameController extends HttpServlet {

    private GamerService gamerService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();

        gamerService = new GamerService();

        GamerDetailsDTO gamerDetailsDTO = gamerService.getGamerDetails(cookies);
        System.out.println(gamerDetailsDTO);
        if (gamerDetailsDTO != null) {
            req.getRequestDispatcher("/game.html").forward(req,resp);
        }else{
            req.getRequestDispatcher("/signIn.html").forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
