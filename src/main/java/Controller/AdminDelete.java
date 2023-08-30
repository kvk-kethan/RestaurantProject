package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.MyDao;
import Dto.Items;

@WebServlet("/delete")
public class AdminDelete extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			//Getting Id from the URL
			int id=Integer.parseInt(req.getParameter("id"));
			MyDao dao=new MyDao();
			//Finding object because remove method accepts object
			Items item=dao.find(id);
			dao.delete(item);
			resp.getWriter().print("<h1 style='color:green'>Data Deleted Successfully</h1>");
			req.getRequestDispatcher("viewmenu").include(req, resp);
	
}
}
