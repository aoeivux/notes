package com.aoeivux.servlet;

import com.aoeivux.respository.DataRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class Delete extends HttpServlet {
    private DataRepository dataRepository = new DataRepository();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        int i = Integer.parseInt(id);
        dataRepository.delete(i);
        resp.sendRedirect("/findAll");
    }
}
