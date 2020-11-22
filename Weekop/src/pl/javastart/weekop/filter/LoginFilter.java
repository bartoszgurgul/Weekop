package pl.javastart.weekop.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import pl.javastart.weekop.model.User;
import pl.javastart.weekop.service.UserService;

@WebFilter("/*")
public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		if (httpServletRequest.getUserPrincipal() != null && httpServletRequest.getSession().getAttribute("user") == null) {
			saveUserInSession(httpServletRequest);
		}
		
		chain.doFilter(request, response);
		
	}

	private void saveUserInSession(HttpServletRequest httpServletRequest) {
		UserService userService = new UserService();
		String username = httpServletRequest.getUserPrincipal().getName();
		User user = userService.getUserByUsername(username);
		httpServletRequest.getSession(true).setAttribute("user", user);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
