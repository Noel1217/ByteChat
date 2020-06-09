package com.Post;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Connector.Dao;
import com.Parameters.GetParameters;

/**
 * Servlet implementation class PostToStory
 */
@WebServlet("/PostToStoryController")
public class PostToStoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		//Declaring variables and objects
		Dao postDao = new Dao(); //Connecting to database
		GetParameters data = null;
		HttpSession session = request.getSession();
		String user = session.getAttribute("sessionUser").toString();
		String postName=null;
		String filePath =null;
		int result = 0;
		String imageOrVideo = null;
		
		//Getting parameter
		data = postDao.getPostInfo(request);
		postName = data.getPostName();
		imageOrVideo = data.getPostImageOrVideo();
		
		//Formatiing the filepath
		filePath = imageOrVideo.replace("Post/", "");
		String path = "C:\\Users\\Noel Gregory\\eclipse-workspace\\ByteWeb\\WebContent\\Post\\"+filePath;
        File file = new File(path);
        System.out.println(imageOrVideo);
		//Uploading post
		result = postDao.uploadPost(postName, imageOrVideo, user);
		postDao.closePstm();
		postDao.closeCon();
		
		//Checking the result
		if(result > 0 ) {
			System.out.println(path);
			if(imageOrVideo.contains(".mp4")) {
				  request.setAttribute("loader", user);
		             request.getRequestDispatcher("PostBarLoader.jsp").forward(request, response);
			}else {
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("fileInput", imageOrVideo);
				request.setAttribute("orginalFile", imageOrVideo);
				request.getRequestDispatcher("ImageFilter.jsp").forward(request, response);
				
			}
			
			
		}else {
			request.setAttribute("postError", "Could not Post");
			request.getRequestDispatcher("PostToStory.jsp").forward(request, response);
					
		}
		
	}

}
