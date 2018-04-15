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
        req.getRequestDispatcher("/signIn.html").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POST POST POST POST POST");
        gamerService = new GamerService();
        GamerDetailsDTO gamerDetailsDTO = gamerService.getGamerByNickName(req.getParameter("nickName"), req.getParameter("gPassword"));
    }
}
