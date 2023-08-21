package Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

import Dao.MyDao;
import Dto.Customer;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

//This is to map the action to this class(should be same as action and this is case sensitive)

@WebServlet("/signup")
@MultipartConfig
//This is to make the class as Servlet class
public class CustomerSignup extends HttpServlet {
	@Override
	// When there is form and we get data to be secured so we will go for doPost
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Receive values from Front End
		String user = req.getParameter("user");
		String pass = req.getParameter("pass");
		String email = req.getParameter("email");
		long phno = Long.parseLong(req.getParameter("phno"));
		LocalDate dob = LocalDate.parse(req.getParameter("dob"));
		String gender = req.getParameter("gender");
		String country = req.getParameter("country");
		int age = Period.between(dob, LocalDate.now()).getYears();

		Part pic = req.getPart("picture");
		byte[] picture = null;
		picture = new byte[pic.getInputStream().available()];
		pic.getInputStream().read(picture);

		MyDao dao = new MyDao();

		if (dao.fetchByEmail(email) == null && dao.fetchByMobile(phno) == null) {
			Customer customer = new Customer();
			customer.setUsername(user);
			customer.setPassword(AES.encrypt(pass, "kvk@123"));
			customer.setEmail(email);
			customer.setPhonenumber(phno);
			customer.setDob(dob);
			customer.setGender(gender);
			customer.setCountry(country);
			customer.setAge(age);
			customer.setPicture(picture);

			dao.save(customer);
			resp.getWriter().print("<h1 style='color:green'>Account created Successfully</h1>");
			req.getRequestDispatcher("Login.html").include(req, resp);

		} else {
			resp.getWriter().print("<h1 style='color:red'>Email and mobile number already exists</h1>");
			req.getRequestDispatcher("reg.html").include(req, resp);
		}

	}

}
