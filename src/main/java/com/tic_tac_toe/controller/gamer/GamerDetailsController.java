package com.tic_tac_toe.controller.gamer;

import com.google.gson.Gson;
import com.tic_tac_toe.dto.response.GamerDetailsDTO;
import com.tic_tac_toe.service.gamer.GamerService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GamerDetailsController extends HttpServlet {

    private GamerService gamerService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        gamerService = new GamerService();
        GamerDetailsDTO gamerDetailsDTO = gamerService.getGamerDetails(cookies);
        if (gamerDetailsDTO != null) {
            String json = new Gson().toJson(gamerDetailsDTO);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(json);
        }else{
            GamerLogOutController gamerLogOutController = new GamerLogOutController();
            gamerLogOutController.doGet(req,resp);
            throw new IllegalArgumentException("Gamer not found!");
        }
    }

}
