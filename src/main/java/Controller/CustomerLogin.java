package Controller;

import java.io.IOException;

import Dao.MyDao;
import Dto.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class CustomerLogin extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String pass = req.getParameter("pass");
		MyDao dao = new MyDao();
		if (email.equals("admin@jsp.com") && pass.equals("admin")) {
			resp.getWriter().print("<h1 style='color:green'>Login Success</h1>");
			req.getRequestDispatcher("AdminHome.html").include(req, resp);
		} else {
			Customer customer = dao.fetchByEmail(email);
			if (customer == null) {
				resp.getWriter().print("<h1 style='color:red'>Invalid Email</h1>");
				req.getRequestDispatcher("Login.html").include(req, resp);
			} else {
				String decrypt=AES.decrypt(customer.getPassword(), "kvk@123");
				if (pass.equals(decrypt)) {
					resp.getWriter().print("<h1 style='color:green'>Login Success</h1>");
					req.getRequestDispatcher("CustomerHome.html").include(req, resp);
				} else {
					resp.getWriter().print("<h1 style='color:red'>Invalid Password</h1>");
					req.getRequestDispatcher("Login.html").include(req, resp);
				}
			}
		}

	}

}
