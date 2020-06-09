package com.Common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import com.Connector.Dao;

public class Commons {
	
	//Declare private variables
	private final String USERNAME = "tech71047@gmail.com"; //Mailing address
	private final String PASSWORD = "Chungath1217"; 
	
	//This function take in a inputstream and string and returns the path of file uploaded
	//in:InputStream:the image 
	//ext:String:holds what type of file it is
	//return:string:path of stored image or video
	public String downloadStream(InputStream in,String ext) throws Exception {
		OutputStream outputStream = null;
		String filePath = "";
		Random rand = new Random(5);
	    try
	    {
	    	filePath = uniqueStringGenerator(rand.nextInt(50),ext);
	        File file = new File("C:\\Users\\Noel Gregory\\eclipse-workspace\\ByteWeb\\WebContent\\Post\\"+filePath);
	        
	        outputStream = new FileOutputStream(file);
	        IOUtils.copy(in, outputStream);
	    }catch (Exception e) {
	    	e.printStackTrace();
	    }
	    
		return "Post/"+filePath;
		
	}//end DownloadStream
	
	//This function take in a inputstream and string and returns the path of file uploaded
		//in:InputStream:the image 
		//ext:String:holds what type of file it is
		//return:string:path of stored image or video
		public String chatDownloadStream(InputStream in,String ext) throws Exception {
			OutputStream outputStream = null;
			String filePath = "";
			Random rand = new Random(5);
		    try
		    {
		    	filePath = uniqueStringGenerator(rand.nextInt(50),ext);
		        File file = new File("C:\\Users\\Noel Gregory\\eclipse-workspace\\ByteWeb\\WebContent\\ChatImages\\"+filePath);
		        
		        outputStream = new FileOutputStream(file);
		        IOUtils.copy(in, outputStream);
		    }catch (Exception e) {
		    	e.printStackTrace();
		    }
		    
			return "ChatImages/"+filePath;
			
		}//end DownloadStream
		
	
	//This function takes in a number and extension and returns a generated string for file name
	//num:int:size of string returned
	//exention:String:add the extension to generated string
	//return:String: generated file name string
	public String uniqueStringGenerator(int num,String extension) {
		  // chose a Character random from this String 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"; 
  
        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(num); 
  
        for (int i = 0; i < num; i++) { 
  
            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index = (int)(AlphaNumericString.length()  * Math.random()); 
  
            // add Character one by one in end of sb 
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 
         sb.append("." +extension);
        return sb.toString(); 
	}//end uniqueStringGenerator
	
	//This fucntion takes in a int and returns generated string
	//num:int:the size of returning string
	//return:String: generated string
	public String uniqueStringGenerator(int num) {
		  // chose a Character random from this String 
      String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                  + "0123456789"
                                  + "abcdefghijklmnopqrstuvxyz"; 

      // create StringBuffer size of AlphaNumericString 
      StringBuilder sb = new StringBuilder(num); 

      for (int i = 0; i < num; i++) { 

          // generate a random number between 
          // 0 to AlphaNumericString variable length 
          int index = (int)(AlphaNumericString.length()  * Math.random()); 

          // add Character one by one in end of sb 
          sb.append(AlphaNumericString 
                        .charAt(index)); 
      } 
      return sb.toString(); 
	}//end uniqueStringGenerator
	
	//This function takes two strings and returns result
	//email(String),this string take the email address
	//email(int),this int takes the verification number to send
	public void email(String emailAddress,String verfiyNum) {
		Properties props = new Properties();
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.port", "587");
	          
	    Session session1 = Session.getInstance(props,
	              new javax.mail.Authenticator() {  //logging intotech71047@gmail.com account
	                  protected PasswordAuthentication getPasswordAuthentication() {
	                      return new PasswordAuthentication(USERNAME, PASSWORD);
	                  }
	              });
	     try {
	        Message message = new MimeMessage(session1);
	        message.setFrom(new InternetAddress(USERNAME)); //email address
	        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailAddress)); //Recipient
	        message.setSubject("Verfication Code"); //Text
	        message.setText("Verfication Code:" +"#"+ verfiyNum);  //Verification Code
	        Transport.send(message); //Sending the email
	        System.out.println("succesfully sended..");
	     } catch (Exception ex) {
	         ex.printStackTrace();
	     }
    }//end email
	
	//This function takes two strings and returns result
	//emailForgotPass(String),this string take the email address
	//emailForgotPass(String),this String takes the password of user to send
	public void emailForgotPass(String emailAddress,String decryptPass) {
		Properties props = new Properties();
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.port", "587");
	          
	    Session session1 = Session.getInstance(props,
	              new javax.mail.Authenticator() {  //logging intotech71047@gmail.com account
	                  protected PasswordAuthentication getPasswordAuthentication() {
	                      return new PasswordAuthentication(USERNAME, PASSWORD);
	                  }
	              });
	     try {
	    	  Message message = new MimeMessage(session1);
		      message.setFrom(new InternetAddress(USERNAME)); //email address
		      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailAddress)); //Recipient
		      message.setSubject("Forgot Password"); //Text
		      message.setText("Your Password is:" + decryptPass +" To Change password please go to the profile settings");  //Verification Code
		      Transport.send(message); //Sending the email
		      System.out.println("succesfully sended..");
	     } catch (Exception ex) {
	         ex.printStackTrace();
	     }
    }//end emailForgotPass
	
	//This function take a string and decodes the string and return it
	//text:String:encoded text
	public String textDecoder(String text) throws UnsupportedEncodingException {
		byte[] decodedTextByte = Base64.getDecoder().decode(text);
		String  decodedText = new String(decodedTextByte,"UTF-8");
		return decodedText;
	}//end textDecoder
	
	//This function take a string and decodes the string and return it
	//text:String:Dencoded text
	public String textEncoder(String text) throws UnsupportedEncodingException {
		String encodedText = Base64.getEncoder().encodeToString(text.getBytes("UTF-8"));
		return encodedText;
	}//end if textEncoder
	
	/*This function takes in two HashMap<Intger,Double> and returns a double
	 *otherUser : HashMap<Integer,Double>: contains the postId with likes
	 *sessionUser: HashMap<Integer,Double>: contains the postId with likes for sessionUsers*/
	public double Similarity(HashMap<Integer,Double> otherUser , HashMap<Integer,Double> sessionUser){
       //declare variables
	   double diff = 0;
       double totalDiff = 0;
       double otherVar = 0;
       double totalSqrt = 0;
       double invert = 0;
       
       //Initiating loop
       for(int id :  sessionUser.keySet()){
           if(otherUser.containsKey(id)){
        	   otherVar = otherUser.get(id);
           }else{
        	   otherVar = 0;
           }
           diff =  sessionUser.get(id) - otherVar;
           totalDiff +=diff*diff;

       }//end for id

       totalSqrt = Math.sqrt(totalDiff);//Sqrt totalDiff
       
       //Inverting the double ,so the range is from 0 to 1
       invert = 1/(1+totalSqrt);
       return invert;
   }//end Similarity
	
	public HashMap<Double,String> haspMapSort(HashMap<Double,String> unSortedHashMap){
		
		ArrayList<Double> keys = new ArrayList<Double>(unSortedHashMap.keySet());
		int n = keys.size();
		HashMap<Double,String> sortedHashMap = new HashMap<Double,String>();
		double temp = 0;
		double temp1 = 0;
		int midPos = 0;
		for(int i  = 0; i< n-1; i++) {
			midPos = i;
			for(int j = i + 1 ;j <n; j++) {
				if(keys.get(j) < keys.get(midPos)){
					midPos = j;
					
				}
			}
			temp =  keys.get(midPos);
			temp1 = keys.get(i);
			keys.set(midPos, temp1); 
			keys.set(i, temp);
			
		}
		
		for(double key : keys) {
			sortedHashMap.put(key, unSortedHashMap.get(key));
		}
		return sortedHashMap;
		
	}
	
	public int greyScaleFilter(String input) {
        int width = 0;
        int height = 0;
		File fileInput = new File("C:\\Users\\Noel Gregory\\eclipse-workspace\\ByteWeb\\WebContent\\Post\\"+input);
		String ext = FilenameUtils.getExtension(fileInput.getName());
		File fileOut = new File("C:\\Users\\Noel Gregory\\eclipse-workspace\\ByteWeb\\WebContent\\Post\\Out"+input);
       	try {
       		BufferedImage img = ImageIO.read(fileInput);//Reading image
   	     
	       //Getting dimensions
	       height = img.getHeight();
	       width = img.getWidth();
	       
	       //Setting image dimension with Type Int to be RBG values
           BufferedImage greyScale = new BufferedImage(width,height,img.TYPE_INT_RGB);
           
           //Iteration through image pixels
           for(int y = 0; y< height; y++){
            for(int x = 0; x<width; x++) {                    
                Color c = new Color(img.getRGB(x, y));
                int red = (int) (c.getRed() * 0.3); // multiplying by coefficient to turn darker
	            int green = (int) (c.getGreen() * 0.59); // multiplying by coefficient to turn darker
		        int blue = (int) (c.getBlue() * 0.11);  // multiplying by coefficient to turn darker
                
                int val = red+ green+ blue;//Adding all the rgb  values together
                
                Color newColor = new Color(val,val,val); //Creating a color from pixel
                greyScale.setRGB(x, y, newColor.getRGB()); //Replacing initial pixel when new color
               }//end for x
            }//end for y
           
           //Writing the  GreyScaled image
           ImageIO.write(greyScale, ext, fileOut);
	     return 1;
       	}catch(Exception e) {
           e.printStackTrace();
        }
       	return  0 ;
	}
	
	public int negativeFilter(String input) {
        int width = 0;
        int height = 0;
		File fileInput = new File("C:\\Users\\Noel Gregory\\eclipse-workspace\\ByteWeb\\WebContent\\Post\\"+input);
		String ext = FilenameUtils.getExtension(fileInput.getName());
		File fileOut = new File("C:\\Users\\Noel Gregory\\eclipse-workspace\\ByteWeb\\WebContent\\Post\\Out"+input);
       	try {
	       BufferedImage img = ImageIO.read(fileInput);//Reading image
	     
	       //Getting dimensions
	       height = img.getHeight();
	       width = img.getWidth();
	       
	       //Setting image dimension with Type Int to be RBG values
           BufferedImage greyScale = new BufferedImage(width,height,img.TYPE_INT_RGB);
           
           //Iteration through image pixels
           for(int y = 0; y< height; y++){
            for(int x = 0; x<width; x++) {                    
                Color c = new Color(img.getRGB(x, y));
               
                int red = (c.getRed());
        		int green =  (c.getGreen());
        		int blue =  (c.getBlue() );
                        
                int r = 255 - red;
                int g = 255 -green;
                int b = 255 - blue;
                
                Color newColor = new Color(r,g,b); //Creating a color from pixel
                greyScale.setRGB(x, y, newColor.getRGB()); //Replacing initial pixel when new color
               }//end for x
            }//end for y
           
           //Writing the  GreyScaled image
           ImageIO.write(greyScale, ext, fileOut);
	     return 1;
       	}catch(Exception e) {
           e.printStackTrace();
        }
       	return  0 ;
	}
	
	public String[] splitStringToAr(String str,String replaceStr) {
		//Declare vairbales
		String[] ar = null;
		String finalStr = null;
		
		//Check if str is empty
		if(!str.isEmpty()) {
			finalStr = str.replace(replaceStr, "");
		}//end if str

		
		//check if finalStr is empty
		if(!finalStr.isEmpty()) {
			ar = finalStr.split(",");
		}//end if finalStr
		
		return ar;
	}
	
	public String[] splitStringToAr(String str) {
		//Declare vairbales
		String[] ar = null;
		
		
		//check if finalStr is empty
		if(!str.isEmpty()) {
			ar = str.split(",");
		}//end if finalStr
		
		return ar;
	}
	
	public int countFollowers(String str) {
		//Declare varibales
		String[] ar = null;

		ar = str.split(",");

		return ar.length;
	
	}
	
	public static int getFollowerCount(String user) {
		//Declare var
		Dao dao = new Dao();
		int result = 0;
	    result = dao.getUserFollowerCount(user);
	    dao.closePstm();
	    dao.closeRs();
	    dao.closeCon();
	    return result;
	}
}
