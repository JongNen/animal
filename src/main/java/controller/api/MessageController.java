package controller.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import data.vo.Message;
import repository.MessagesDAO;

@WebServlet("/api/message")
public class MessageController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String no = req.getParameter("no");
		List<Message> li =MessagesDAO.readMessages(no);
		
		resp.setContentType("text/json;charset=utf-8");
		PrintWriter out =resp.getWriter();
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("result", true);
		map.put("items", li);
		map.put("total", li.size());
		Gson gson = new Gson();
		out.println(gson.toJson(map));
	}
}





