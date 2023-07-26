package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import repository.MessagesDAO;

@WebServlet("/write")
public class WriteController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		
		
		String target =req.getParameter("target");
		String body = req.getParameter("body");
		String pass = req.getParameter("pass");
		
		int r = MessagesDAO.createMessage(target, body, pass);
		
		resp.sendRedirect("/detail?no="+target);
		
	}
}
