package com.bridgelabz;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(
        description = "Login Servlet Testing",
        urlPatterns = { "/LoginServlet" },
        initParams = {
                @WebInitParam(name = "user", value = "Srinadh"),
                @WebInitParam(name = "password", value = "Minimum@8")
        }
)
public class LoginServlet  extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         String user = req.getParameter("user");
        String pwd = req.getParameter("pwd");
        String name=req.getParameter("name");
        String userID = getServletConfig().getInitParameter("user");
        String password = getServletConfig().getInitParameter("password");
        String nameRegex =  "^[A-Z]{1}[a-zA-Z]{2,}$";
        String passRegex="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@$!%*?&])[A-Za-z0-9@$!%*?&]{8,}$";
        if(userID.equals(user) && password.equals(pwd) && name.matches(nameRegex) && password.matches(passRegex)) {
            req.setAttribute("user",user);
            req.getRequestDispatcher("LoginSuccess.jsp").forward(req, resp);
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out  = resp.getWriter();
            out.println("<font color = red> Entered username or password is wrong</font>");
            rd.include(req, resp);
        }

    }
}