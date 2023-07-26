package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import data.animal.AnimalResponse;
import util.AnimalAPI;

@WebServlet("/chart")
public class ChartController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		List<String> de = new ArrayList<>();
		for(int i=4; i>=0; i--) {
			Date target = new Date(System.currentTimeMillis() - 1000*60*60*24*i);
			String s = sdf.format(target);
			de.add(s);
		}
		Gson gson = new Gson();
		
		req.setAttribute("labels", gson.toJson(de));	// -------왜 이런짓을 하는가?
		req.setAttribute("labelsData", de);
		
		List<Integer> counts = new ArrayList<>();
		for (String d : de) {
			AnimalResponse response = AnimalAPI.getAnimals(null, null, null, d, d);
			int total = response.getBody().getTotalCount();
			counts.add(total);
		}
		req.setAttribute("counts", gson.toJson(counts));
		req.setAttribute("countsData", counts);
		
		req.getRequestDispatcher("/WEB-INF/views/chart.jsp").forward(req, resp);

	}
}


