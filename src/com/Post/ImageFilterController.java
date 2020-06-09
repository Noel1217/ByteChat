package com.Post;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Common.Commons;

/**
 * Servlet implementation class ImageFilterController
 */
@WebServlet("/ImageFilterController")
public class ImageFilterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Declare variables
		Commons common = new Commons();
		String OrginalUrl = request.getParameter("orginalFile");
		String url = request.getParameter("imageFilter");
		String greyScaleFilter= request.getParameter("G");
		String negativeFilter = request.getParameter("N");
		String finalUrl = OrginalUrl.replaceAll("Post/", "");
		
		int result  = 0;
		
		if(greyScaleFilter.equals("true")) {
			//changing image to GreyScale
			result = common.greyScaleFilter(finalUrl);
		}else if(negativeFilter.equals("true")) {
			result = common.negativeFilter(finalUrl);
		}else {
			result = 0;
		}
		System.out.println(result);
		if(result > 0 ) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("fileInput", "Post/Out"+finalUrl);
			request.setAttribute("orginalFile", OrginalUrl);
			request.getRequestDispatcher("ImageFilter.jsp").forward(request, response);
			
		}else {
			request.setAttribute("postError", "Could not Post");
			request.getRequestDispatcher("PostToStory.jsp").forward(request, response);
				
		}
	}

	

}
