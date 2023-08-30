package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.MyDao;
import Dto.Items;

@WebServlet("/edit")
public class AdminEdit extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Getting Id from the URL
		int id = Integer.parseInt(req.getParameter("id"));
		MyDao dao = new MyDao();
		// Finding object because remove method accepts object
		Items item = dao.find(id);
		req.setAttribute("item", item);
		req.getRequestDispatcher("Edit.jsp").forward(req, resp);
	}
}
