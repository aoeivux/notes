package com.aoeivux.servlet;

import com.aoeivux.entity.Admin;
import com.aoeivux.respository.DataRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/findAll")
public class FindAll extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataRepository dataRepository = new DataRepository();
        //返回视图+数据
        List<Admin> admins = dataRepository.selectAll();
        req.setAttribute("list", admins);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
