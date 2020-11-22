package pl.javastart.weekop.controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	public static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if(request.getUserPrincipal() != null) {
			response.sendRedirect(request.getContextPath() + "/");
		} else {
			response.sendError(403);
		}
	}
}
