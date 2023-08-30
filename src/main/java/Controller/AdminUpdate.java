package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.Part;

import Dao.MyDao;
import Dto.Items;

@WebServlet("/update")
@MultipartConfig
public class AdminUpdate extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		String name=req.getParameter("name");
		double price=Double.parseDouble(req.getParameter("price"));
		int quantity=Integer.parseInt(req.getParameter("quantity"));
		String type=req.getParameter("type");
		Part part=req.getPart("picture");
		byte[] picture=null;
		MyDao dao=new MyDao();
		Items item1=dao.find(id);
		if(part==null) {
			picture=item1.getPicture();
		}
		else {
		picture=new byte[req.getPart("picture").getInputStream().available()];
		req.getPart("picture").getInputStream().read(picture);
		}
		
		Items items=new Items();
		items.setItem_id(id);
		items.setItem_name(name);
		items.setItem_price(price);
		items.setQuantity(quantity);
		items.setFood_type(type);
		items.setPicture(picture);
		dao.update(items);
		
		resp.getWriter().print("<h1 style='color:green'>Data Deleted Successfully</h1>");
		req.getRequestDispatcher("viewmenu").include(req, resp);
		
	}
}
