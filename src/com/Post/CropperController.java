//Noel Gregory
//2020-04-18
//This servlet class to crop image
package com.Post;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import com.Connector.Dao;
import com.Parameters.GetParameters;

/**
 * Servlet implementation class CropperController
 */
@WebServlet("/CropperController")
public class CropperController extends HttpServlet {
	//Declare Variables
	private static final long serialVersionUID = 1L;

	//This procedure takes in a request object and response object and crop image to inputed size
    //request:HttpServletRequest:containing request object from website
    //reponse:HttpServletResponse:containg response object to the website from server side
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Declaring variables and objects
		HttpSession session = request.getSession();
		String user = session.getAttribute("sessionUser").toString();
		String typeFile = request.getParameter("video/image");
		String image = request.getParameter("imageFilter");
		String orginalImage = request.getParameter("orginalFile");
		String filePath = null;
		String orginalFilePath = null;
		String type = null;
		double x = 0;
		double y = 0;
		double w = 0;
		double h = 0;
		
		
		//checking file type
		if(typeFile.equals("image")) {
			x = Double.parseDouble(request.getParameter("x"));
			y = Double.parseDouble(request.getParameter("y"));
			w = Double.parseDouble(request.getParameter("w"));
			h = Double.parseDouble(request.getParameter("h"));
			filePath = image.replace("Post/", "");
			orginalFilePath = orginalImage.replace("Post/", "");
			String path = "C:\\Users\\Noel Gregory\\eclipse-workspace\\ByteWeb\\WebContent\\Post\\"+filePath;
			String orginalPath = "C:\\Users\\Noel Gregory\\eclipse-workspace\\ByteWeb\\WebContent\\Post\\"+orginalFilePath;
	        File file = new File(path);
	        File imageFile = new File(orginalPath);
	        type = FilenameUtils.getExtension(file.getName());
	        
	        try {
	        	 BufferedImage orginal = ImageIO.read(file);
	             BufferedImage finalImage = orginal.getSubimage((int) x,(int) y,(int) w,(int) h);
	             ImageIO.write(finalImage, type, imageFile);
	             
	             file.delete();  //Deleting original file
	
	             request.setAttribute("loader", user);
	             request.getRequestDispatcher("PostBarLoader.jsp").forward(request, response);
	        }catch(Exception e) {
	        	e.printStackTrace();
	        	request.setAttribute("postError", "Error ,please try again uploading");
	        	request.getRequestDispatcher("PostToStory.jsp").forward(request, response);
	        	
	        }//end try catch
	        
		}else {
			request.setAttribute("loader", user);
            request.getRequestDispatcher("PostBarLoader.jsp").forward(request, response);
		}//end if typeFile
		
	}//end doPost

}
