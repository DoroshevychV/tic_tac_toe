package com.tic_tac_toe.controller.gamer.registration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @author  Doroshevych Vadym
 * @version 1.0
 * @since   2018-04-17
 * @servlet_url /gamer/registration/error
 */
public class RegistrationErrorController extends HttpServlet {
    /**
     * Handles the HTTP GET method.
     * The method returns a page with a registration error
     * @param req - servlet request
     * @param resp - servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/registrationErrorPage.html").forward(req,resp);
    }
}
