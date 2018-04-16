package com.tic_tac_toe.controller.gamer;

import com.tic_tac_toe.service.gamer.GamerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GamerLogOutController extends HttpServlet {

    private GamerService gamerService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        gamerService = new GamerService();
        resp.addCookie(gamerService.deleteCookie("/","nickName",null));
        resp.addCookie(gamerService.deleteCookie("/","gPassword",null));
//        req.getRequestDispatcher("/").forward(req,resp);
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
