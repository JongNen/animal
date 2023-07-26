package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.animal.AnimalResponse;
import data.sido.SidoResponse;
import util.AnimalAPI;
import util.SidoAPI;

@WebServlet("/index")
public class IndexController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SidoResponse sidoResponse = SidoAPI.getSidos();
		if (sidoResponse != null) {
			req.setAttribute("sidos", sidoResponse.getBody().getItems().getItem());
		}

		// 축종코드 : 개=> "417000" , 고양이=>"422400", 기타 => "429900"
		String upkind = req.getParameter("upkind");
		String upr_cd = req.getParameter("upr_cd");
		String pageNo = req.getParameter("pageNo");
		String bgnde = req.getParameter("bgnde");
		if (bgnde != null && bgnde.matches("\\d{4}-\\d{2}-\\d{2}")) {
			// 2024-03-11
			bgnde = bgnde.substring(0, 4) + bgnde.substring(5, 7) + bgnde.substring(8);
		}
		String endde = req.getParameter("endde");
		if (endde != null && endde.matches("\\d{4}-\\d{2}-\\d{2}")) {
			endde = endde.replaceAll("-", "");
		}

		AnimalResponse animalResponse = AnimalAPI.getAnimals(upkind, upr_cd, pageNo, bgnde, endde);

		if (animalResponse != null) {
			req.setAttribute("datas", animalResponse.getBody().getItems().getItem());
			req.setAttribute("total", animalResponse.getBody().getTotalCount());
			int tot = animalResponse.getBody().getTotalCount();

			req.setAttribute("lastPageNo", tot / 12 + (tot % 12 > 0 ? 1 : 0));
		}
		req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
	}
}



