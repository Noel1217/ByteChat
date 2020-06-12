//Noel Gregory
//2020-04-18
//This servlet class to check credentials is valid or not
package com.login;

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

import com.Connector.Dao;

/**
 * Servlet Filter implementation class LoginParameter
 */
@WebFilter("/LoginController")
public class LoginParameter implements Filter {

	//This procedure takes in a request object and response object and check credentials is valid or not
    //request:ServletRequest:containing request object from website
    //reponse:ServletResponse:containg response object to the website from server side
	//chain:FilterChain:object send data to filters and then to servlet
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//Declaring Variables and Object
	    HttpServletRequest resq = (HttpServletRequest) request;
	    HttpServletResponse resp = (HttpServletResponse) response;
		String user = resq.getParameter("Username");
		String pass = resq.getParameter("Password"); 
				
	     //Checking user inputs
		if(user.length() > 1 && pass.length() >= 6) {
		   // pass the request along the filter chain
		   chain.doFilter(request, response);
		}else {
		   resp.sendRedirect("Login.jsp?userRegistered="); //sending user	
		}//end if users and pass
	}//end doFilter
}
