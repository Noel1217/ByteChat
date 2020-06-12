//Noel Gregory
//2020-04-18
//This SessionFilter check if session has been destroyed or not
package com.session;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class SessionFilter
 */
@WebFilter(
		urlPatterns = { "/SessionFilter" }, 
		servletNames = { 
				"AddLikeController", 
				"FindUsersController", 
				"CropperController", 
				"ForgotPasswordController", 
				"getImage", 
				"ImageFilterController", 
				"LogoutController", 
				"PostController", 
				"PostToStoryController", 
				"ProfileController", 
				"FollowController", 
				"SuggestedUserController", 
				"UnFollowController"
		})
public class SessionFilter implements Filter {

	//This procedure takes in a request object and response object and check if session is alive
    //request:ServletRequest:containing request object from website
    //reponse:ServletResponse:containg response object to the website from server side
	//chain:FilterChain:object send data to filters and then to servlet
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//Declaring objects
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session  = req.getSession();
		
		//Check Session
		if(session.getAttribute("sessionUser") == null) {
			resp.sendRedirect("Login.jsp?userRegistered=");
		}//emd session

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}//end doFilter

	

}
