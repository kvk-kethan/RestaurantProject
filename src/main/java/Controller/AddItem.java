package Controller;

import java.io.IOException;

import Dao.MyDao;
import Dto.Items;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/additem")
@MultipartConfig
public class AddItem extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name=req.getParameter("name");
		double price=Double.parseDouble(req.getParameter("price"));
		int quantity=Integer.parseInt(req.getParameter("quantity"));
		String type=req.getParameter("type");
		byte[] picture=new byte[req.getPart("picture").getInputStream().available()];
		req.getPart("picture").getInputStream().read(picture);
		Items items=new Items();
		items.setItem_name(name);
		items.setItem_price(price);
		items.setQuantity(quantity);
		items.setFood_type(type);
		items.setPicture(picture);
		MyDao dao = new MyDao();
		dao.item(items);
		resp.getWriter().print("<h1 style='color:green'>Added Successfully</h1>");
		req.getRequestDispatcher("AdminHome.html").include(req, resp);
	}
}
