package com.aoeivux.servlet;

import com.aoeivux.entity.Admin;
import com.aoeivux.respository.DataRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add")
public class Add extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataRepository dataRepository = new DataRepository();
        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        dataRepository.add(new Admin(name, gender));

        resp.sendRedirect("/findAll");
    }
}
