package webapp.web;

import java.io.IOException;

public class ResumeServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        response.setContentType("text/html; cahrset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(name == null? "Hello Resumes!" : "Hello " + name + "!");
    }
}
