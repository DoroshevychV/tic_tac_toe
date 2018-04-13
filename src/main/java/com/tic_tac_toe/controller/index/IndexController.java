package com.tic_tac_toe.controller.index;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @author  Doroshevych Vadym
 * @version 1.0
 * @since   2018-04-13
 * @servlet_url /
 */
public class IndexController extends HttpServlet {

    /**
     * Handles the HTTP GET method.
     * The method works on the GET request and returns the homepage of the program
     * (index.html)
     *
     * @param req  - servlet request
     * @param resp - servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("NOT FOUND");
        req.getRequestDispatcher("/index.html").forward(req, resp);
    }
}
