package com.aoeivux.servlet;

import com.aoeivux.entity.Admin;
import com.aoeivux.respository.DataRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update")
public class Update extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataRepository dataRepository = new DataRepository();
        String id = req.getParameter("id");
        int i = Integer.parseInt(id);
        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        dataRepository.update(i, name, gender);

        resp.sendRedirect("/findAll");
    }
}
