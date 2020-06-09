//Noel Gregory
//2020-06-09
//This class is the database access object
package com.Connector;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.tribes.util.Arrays;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.Chat.UserInbox;
import com.Chat.Message;
import com.Common.Commons;
import com.Parameters.GetParameters;
import com.Post.PostInfo;
import com.User.UserInfo;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Dao {
	//Declaring Connection variables
    final String DBURL = "jdbc:mysql://localhost:3306/bytechat";
	final String DBUSER ="root";
	final String DBPASS = "Noel#1217";
	private Connection con = null;//Creating connection object
	private PreparedStatement pstm = null;
	private ResultSet rs = null;
	private Statement st = null;
	private Commons common = null;
	
	
	//This constructor creates a connection with database
	public Dao(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(DBURL,DBUSER ,DBPASS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}//end Dao
	
	/*This function  returns number of effected rows in database in integer
	 *user:String:this parameter contains username
	 *postId:String:this parameter contains postId
	 * return:int: amount of effected rows*/
	public int checkIfLiked(String user,int postId) {
		//Declare variables
		String queryCheckLikes = "select * from likes where PostId=? AND likedUser=?";
		int counter = -1 ;
		
		try {
			pstm = con.prepareStatement(queryCheckLikes);
			pstm.setInt(1, postId);
			pstm.setString(2, user);
			rs = pstm.executeQuery();
			if(rs.next()) {
				counter = 1;
			}else {
				counter = 0;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end try catch
		
		return counter;
	}//end checkIfLiked
	
	/*This function  returns number of effected rows in database in integer
	 *user:String:this parameter contains username
	 *unFollowUser:String:this parameter contains unfollowing username
	 * return:int: amount of effected rows*/
	public int unFollow(String user,String unFollowUser) {
		//declare variables
		String queryUnFollow = "UPDATE Userfollowers SET Followers = REPLACE(Followers, ?,'') where User=?";
		String queryUnFollowing = "UPDATE Userfollowers SET Following = REPLACE(Following, ?,'') where User=?";
		int rslt1 = 0;
		int rslt2 = 0;
		
		try {
			pstm = con.prepareStatement(queryUnFollow);
			pstm.setString(1, user + ",");
			pstm.setString(2, unFollowUser);
			rslt1 = pstm.executeUpdate();
			pstm = con.prepareStatement(queryUnFollowing);
			pstm.setString(1, unFollowUser + "," );
			pstm.setString(2, user);
			rslt2 = pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rslt1 + rslt2;
	}//end unFollow
	/*This function passes two parameters and returns number of effected rows in database in integer
	 * user:String:contains the username of current user
	 * followerName:String:contains username to follow
	 * returns:int: number of effected rows*/
	public int checkIfFollowing(String user,String followerName) {
		//declare varibales
		String followingQuery = "select * from Userfollowers  WHERE User = ? AND Followers LIKE ? ";
		int counter  = -1;
		
		try {
			pstm = con.prepareStatement(followingQuery);
			pstm.setString(1, followerName);
			pstm.setString(2, "%"+user+"%");
			rs = pstm.executeQuery();
			if(rs.next()) {
				counter = 1;
			}else {
				counter = 0;
			}//end if rs
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end try catch
		return counter;
	}//end checkIfFollowing
	
	
	 /*This function passes in one parameter and returns ArrayList<String> of following
	  *user:String:this parameter contains username
	  * return:ArrayList<String>: user following*/
    public ArrayList<String> getFollowingProfile(String user){
    	//Declare variables
    	ArrayList<String> following = new ArrayList<String>();
		String followingQuery = "select * from userfollowers where User= ? ";
		common = new Commons();
		
		try {
			pstm = con.prepareStatement(followingQuery);
			pstm.setString(1, user);
			rs = pstm.executeQuery();
			if(rs.next()) {
				String temp = rs.getString("Following");
				String[] tempAr = common.splitStringToAr(temp, "UserFollowing:"); //Splitting string to string[]
				if(tempAr != null) {
					for(int i = 0; i<tempAr.length; i++) {
						following.add(tempAr[i]); //Adding user to following ArrayList
					}//end for i
				}//end if tempAr
			}//end if rs
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	return following;
    }//end getRequest
    /*This function passes in two parameter and returns number of effected rows in database in integer
	  *user:String:this parameter contains username
	  *requestedUser:String:contains requested User
	  * return:ArrayList<String>: user following*/
    public int acceptFollower(String user,String requestedUser) {
    	//Declare variables
    	String query = "update userfollowers set Following=concat(Following,?,',') where User = ?";
    	int result = 0;
    	int result1 = 0;
    	String followerQuery = "update userfollowers set Followers=concat(Followers,?,',') where User = ? ";
    	
    	try {
			pstm = con.prepareStatement(query);
			pstm.setString(1, requestedUser);
			pstm.setString(2, user);
			result = pstm.executeUpdate();
			pstm = con.prepareStatement(followerQuery);
			pstm.setString(1, user);
			pstm.setString(2, requestedUser);
			result += pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end try catch
    	
    	return result;
    }//end acceptFollower
	    
	//This procedure takes in two strings and updates profile status
	//user:String: this variables hold username
	//status:String: this variable holds status
	public void userStatus(String user,String status) {
		//declare variables
		int result = 0;
		String query = "update profile set Status = ? where Username=?";
		
		try {
			pstm = con.prepareStatement(query);
			pstm.setString(1, status);
			pstm.setString(2, user);
			result = pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end try catch
		
	}//end userStatus
	
    
    /*This function  returns an requested followers
   	 *user:String:this parameter contains username
   	 * return:UserInfo: user data*/
       public UserInfo getRequestedInfo(String user){
       	//Declare variables
       	String requestedQuery = "select * from profile  WHERE Username = ? ";
       	UserInfo userData = new UserInfo();
        String status = null;
       	try {
   			pstm = con.prepareStatement(requestedQuery);
   			pstm.setString(1, user);
   			rs = pstm.executeQuery();
   			while(rs.next()) {
   				userData.setName(rs.getString("Username"));
   				status = rs.getString("Status");
   				userData.setStatus(status);
   			}//end while rs.next()
   		} catch (SQLException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}//end try catch
       	
       	return userData;
       }//end getRequestedInfo
       
       /*This function  returns an requested followers
	  	*user:String:this parameter contains username
	  	* return:UserInfo: user data*/
	   public ArrayList<String> getFollowersProfile(String user){
	    //Declare variables
    	String requestedQuery = "select * from Userfollowers where User = ? ";
    	ArrayList<String> followers = new ArrayList<String>();
    	String[] tempAr = null;
    	try {
			pstm = con.prepareStatement(requestedQuery);
			pstm.setString(1, user);
			rs = pstm.executeQuery();
			if(rs.next()) {
				String temp = rs.getString("Followers");
				String finalStr = temp.replace("UserRequest:", "");
				tempAr = finalStr.split(",");
				
				if(tempAr != null) {
						for(int i = 0; i<tempAr.length; i++) {
							followers.add(tempAr[i]);
						}//end for i
				}//end if tempAr
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end try catch
    	
    	return followers;
	    }//end getRequestedInfo
    
    /*This function  returns an totalLikes
	 *postId:Int:this parameter contains postId
	 * return:Int: number of likes*/
	public int getPostLikes(int postId) {
		//Declare variables and objects
		String queryPostLikes = "SELECT COUNT(PostId) FROM likes where PostId=?";
		int totalLikes = 0;
		
		try {
			pstm = con.prepareStatement(queryPostLikes);
			pstm.setInt(1, postId);
			rs = pstm.executeQuery();
			if(rs.next()) {
				totalLikes = rs.getInt(1);
			}//end if rs
		} catch (SQLException e) {
			e.printStackTrace();
		}//end try catch
		
		return totalLikes;
	}//end getPostLikes
	
	/*This function passes in one parameter and returns UserInfo object
	 * user:String:username of user
	 * return:UserInfo:user info*/
	public UserInfo getLikedPostUsers(String user){
		//Declare variables and objects
		String query = "select  * from likes where likedUser = ?";
		UserInfo  info = new UserInfo();

		try {
			pstm = con.prepareStatement(query);
			pstm.setString(1, user);
			rs = pstm.executeQuery();
			while(rs.next()) {
				info.setUserLikedPost(Integer.parseInt(rs.getString("PostId")), 1.0);
				info.setName(user);
			}//end while rs.next()
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end try catch
		
		return info;
	}//end getLikedPostUser
	
	/*This function  returns an List<UserInfo>
	 *user:String:this parameter hold username
	 *postId:Int:this parameter contains postId
	 * return:Int: number of effected rows*/
	public int addLike(String user, int postId) {
		//Declare objects and variables
		String likeQuery = "Insert into likes(PostId,likedUser) values(?,?)";
		int result = 0 ;
		int resultLikes = checkIfLiked(user,postId);
		
		if(resultLikes == 0) {
			try {
				pstm = con.prepareStatement(likeQuery);
				pstm.setInt(1, postId);
				pstm.setString(2, user);
				result = pstm.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//end try catch
		}//end if resultLikes
		
		return result;
	}//end addLike

	/*This function passes in one parameter returns an List<UserInfo>
	 * user:String username of Session user
	 * return:List<PostInfo>: containing all the posts*/
	public List<PostInfo> getPost(String user){
		//Declare variables and objects
		PostInfo post = null;
		List<PostInfo> postData = new ArrayList<PostInfo>();
		String query = "select * from post where Username=? ";
		int postId  = 0;

		try {
			pstm = con.prepareStatement(query);
			pstm.setString(1, user);
			rs = pstm.executeQuery();
			while(rs.next()) {
				post = new PostInfo();
				post.setPostName(rs.getString("PostTitle"));
				postId = rs.getInt("idPost");
				post.setPostId(postId);
				post.setUser(rs.getString("Username"));
				post.setPostDate(rs.getString("datePost"));
				post.setPostImageOrVideo(rs.getString("PostImageOrVideo"));
				post.setPostLikes(getPostLikes(postId));
				postData.add(post);
			}//end while rs.next()
		}catch (SQLException  e){
			e.printStackTrace();
		}//end try catch
		return postData;
	}//end getPost()
	
	/*This function takes a string and returns an byte[]
	 * user:String:this parameter holds the username
	 * return:byte[]: containing the image*/
	public byte[] getImage(String user) throws SQLException {
		//Declare varibales
		byte[] image = null;
		String query="select Profile_Image from profile where Username=?";
		
		try {
			pstm = con.prepareStatement(query);
			pstm.setString(1, user);
			rs = pstm.executeQuery();
			if(rs.next()) {
				image = rs.getBytes(1);
			}else {
				System.out.println("Image error");
			}//end if rs.next()
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end try catch
		
		return image;
	}//end getImage
	
	/*This function takes a string and returns an List<UserInfo>
	 * user:String:this parameter holds the  username
	 * return:List<UserInfo>: containing the imageUrl and postId*/
	public List<UserInfo> getProfileId(String user) {
		//Declaring variables
		List<UserInfo> postIds = new ArrayList<UserInfo>();
		UserInfo data  = null;
		String query = "select * from post where Username=?";
		
		try {
			pstm = con.prepareStatement(query);
			pstm.setString(1, user);
			rs = pstm.executeQuery();
			while(rs.next()) {
				data = new UserInfo();
				data.setPostId(rs.getString("idPost"));
				data.setImageOrVideo(rs.getString("PostImageOrVideo"));
				postIds.add(data);
			}//end while rs.next()
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end try catch
		
		return postIds;
	}//end getProfileId
	
	/*This function takes a string and returns an List<UserInfo>
	 * user:String:this parameter holds the username
	 * return:List<UserInfo>: containing the username*/
	public List<UserInfo> getUsers(String user) throws SQLException{
		//Declare variables
		UserInfo info = null;
		List<UserInfo> infoArray = new ArrayList<UserInfo>();
		String query ="SELECT * FROM profile WHERE Username LIKE '"+user+"%' LIMIT 20;";

		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				info = new UserInfo();
				info.setName(rs.getString("Username"));
				info.setStatus(rs.getString("Status"));
				infoArray.add(info);
			}//end while rs.next()
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end try catch
		
		return infoArray;
	}//end getUsers
	
	/*This function takes returns an ArrayList<String>
	 * return:ArrayList<String>: containing the username*/
	public ArrayList<String> getAllUsers() {
		//Declare variables
		UserInfo info = null;
		ArrayList<String> infoArray = new ArrayList<String>();
		String query ="SELECT * FROM profile ";
		
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {				
				infoArray.add(rs.getString("Username"));
			}//end while rs.next()
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end try catch
		
		return infoArray;	
	}//end getAllUsers
	
	/*This function takes a string and returns an List<UserInfo>
	 * user:String:this parameter hold username
	 * profileData:List<UserInfo>:this holds the profile detail in UserInfo class*/
	public List<UserInfo> getProfile(String user) {
		UserInfo info = null;
		String query = "select * from profile where Username=?";
		List<UserInfo> profileData = new ArrayList<UserInfo>();
		
		try {
			pstm = con.prepareStatement(query);
			pstm.setString(1, user);
		    rs = pstm.executeQuery();
		    if(rs.next()) {
		    	info = new UserInfo();
		    	info.setName(rs.getString("Username"));
		    	info.setPass(rs.getString("Password"));
		    	info.setBio(rs.getString("Bio"));
		    	profileData.add(info);
		    }else {
		    	System.out.println("error");
		    }//end if rs
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end try catch
		
		return profileData;
	}//end getProfile
		
	/*This function takes a strings and returns integer result 
	 * emailAddress:String:the first variable passed ,which hold email*/
	public int forgotPassword(String emailAddress) {
		//Declare variable and object
		String query="select * from users where Email=?";
		String pass = null;
		String decryptPass = null;
		common = new Commons();
		try {
			pstm = con.prepareStatement(query);
			pstm.setString(1, emailAddress);
			rs = pstm.executeQuery();
			rs.next();
			//Getting password from database
			pass = rs.getString("Password");	
			
			decryptPass = common.textDecoder(pass);//decoding pass
			common.emailForgotPass(emailAddress, decryptPass);//calling emailForgotPass to send email
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end try catch
		 return 0;
	}//end ForgotPasswordController
	/*This function passes in three parameters and returns number of effected rows in integer
	 * postName:String:contains the name of post
	 * imageOrVideo:String:contains location of file
	 * user:String:contains user that uploaded
	 * returns:int:number of effected rows in database*/
	public int uploadPost(String postName,String imageOrVideo,String user) {
		//Declaring variables
		String query = "Insert into post(Username,PostImageOrVideo,PostTitle,datePost) values(?,?,?,?)";
		int result = 0;
		Date now = new Date();
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		try {
			pstm = con.prepareStatement(query);
			pstm.setString(1, user);
			pstm.setString(2,imageOrVideo );
			pstm.setString(3, postName);
			pstm.setString(4, formatter.format(now));
			result = pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end try catch
		return result;
	}//end uploadPost
	
	/*This function takes in 4 parameters and returns a effected rows in integer form
	 * user:String:username value
	 * pass:String:encrypted password value
	 * email:String:email address value
	 * stream:InputStream:contaisn image in bytes
	 * return:int:number of effected rows*/
	public int createUser(String user,String pass , String email,InputStream stream) throws Exception {
		//Declare Variables and objects
		String query="INSERT INTO users(Username,Password,Email,Verification_State) VALUES(?,?,?,?)";
		String profileQuery ="INSERT INTO profile(Username,Password,Email,Profile_Image) VALUES(?,?,?,?)";
		String followerQuery = "INSERT INTO userfollowers(User,Followers,Following) VALUES(?,?,?)";
		int result = 0;
		Commons common = new Commons();
		String code = common.uniqueStringGenerator(5);
		String finalCode ="#" + code; //Creating verification code
		
		 try {
			pstm = con.prepareStatement(query);
			pstm.setString(1, user);
			pstm.setString(2, pass);
			pstm.setString(3, email);
			pstm.setString(4, finalCode);
			result = pstm.executeUpdate();//executing  variable to users table			
			pstm = con.prepareStatement(profileQuery);
			pstm.setString(1, user);
			pstm.setString(2, pass);
			pstm.setString(3, email);
			if(stream != null) {
				pstm.setBlob(4, stream); //Passing FileInputStream to database and setting as blob
			}//end if stream
			result = pstm.executeUpdate(); //executing  variable to profile table
			pstm = con.prepareStatement(followerQuery);
			pstm.setString(1, user);
			pstm.setString(2, "UserRequest:"+user+",");
			pstm.setString(3, "UserFollowing:"+user + ",");
			result = pstm.executeUpdate();
			common.email(email,code);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end try catch
		 
		 return result;// returning value
		
	}//end createUser
	

	/*this function takes two strings and returns an int holding number of effected rows
	 * code:String:first passed string ,holding user code
	 * user:String second variable is the username*/
	public int updateVerificationState(String code , String user) {
		//Declare variables and objects
		String dataCode = null;
		String replacedDataCode = null;
		String query ="select * from users where Username=?";
		String queryVerfiy ="update users set Verification_State=? where Username=?;";
		int result = 0;
		
		//Initiating connection
		try {
			pstm  = con.prepareStatement(query);
			pstm.setString(1,user);
			rs = pstm.executeQuery();
			while(rs.next()) {
				dataCode = rs.getString("Verification_State");  //Retrieving verification code from database
				replacedDataCode = dataCode.replace("#", "");
				if(code.equals("123")) {   //checking if the same code :for testing replacedDataCode
					pstm = con.prepareStatement(queryVerfiy);
					pstm.setString(1, "Verified");
					pstm.setString(2, user);
					result = pstm.executeUpdate();  //updating column
					return result;
				}//end if code
			}//edn while rs.next()
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end try catch
		return -1;
	}// end updateVerificationState
	
	
	/*This function takes two strings and returns integer depending on comparisons
	  user:String: Username entered by user
	  pass:String: Password entered by user
	  return:INT:retunr a number depending of comparisons*/
	public int checkCredentials(String user,String pass)  {
		//Initiating variables and methods
		String dataPass = "";
		String dataVerficationState = "";
		String query ="select * from users where Username=?";
		common = new Commons();

		try {
			pstm = con.prepareStatement(query);
			pstm.setString(1, user);
			rs = pstm.executeQuery();
			
			// checking if ResultSet is empty
		    if (rs ==null) {
				return 2;
		    }//end if rs
			
			//Looping through database
			while(rs.next()) {
				dataPass = common.textDecoder(rs.getString("Password"));//Retrieving password
				if(pass.equals(dataPass)) {
					dataVerficationState = rs.getString("Verification_State");
					if(dataVerficationState.contains("#")) {
						return 5;
					}else {
						return 1;
					}//end if dataVerficationState
					
				}// end if pass
			}//end while rs.next()
			return 3;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//edn try catch
		
		return 4; // connection not working				
	}// end checkCredentials

	//This procedure closes the PreparedStatement object
	public void closePstm() {
		
		try {
			pstm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end try catch
		
	}//end closePstm
	
	//This procedure closes the statement object
    public void closeSt() {
		
		try {
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end try catch
		
	}//end closeSt
    
	//This procedure closes the ResultSet object
    public void closeRs() {
		
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end try catch
		
	}//end closeRs
    
	//This procedure closes the Connection object
    public void closeCon() {
		
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end try catch
		
	}//end closeCon
	

	//This function takes the httpservlet request and returns a Object of GetParamters
	//request:HtttpServletRequest:first object passed,holding the http request
    //returns:GetPameters:containing user entered info
	public GetParameters getPostInfo (HttpServletRequest request) {
        //Declaring Variables
		GetParameters parameters = new GetParameters();
		Commons  download = new Commons();
		DiskFileItemFactory factory = new DiskFileItemFactory();  //To create fileItem
		File tempFile = new File("C:\\Users\\Noel Gregory\\eclipse-workspace\\ByteWeb\\WebContent\\PostTemp"); //creating area to store content
		factory.setRepository(tempFile);//setting where to store
		ServletFileUpload fileUpload = new ServletFileUpload(factory); 
		List<FileItem>items =null;
		//Parsing request
		try {
			items = fileUpload.parseRequest(request); //Storing parsed request
			//Iterating through parsed request
			for(FileItem  item : items) {
				if(item.isFormField()) {
					String field = item.getFieldName();
					if(field.equals("title")) {
						parameters.setPostName(item.getString());
					}//end if field
				}else {
					String ext =  FilenameUtils.getExtension((new File(item.getName()).getName()));
					InputStream in = item.getInputStream();
					String filePath = download.downloadStream(in,ext);
					parameters.setPostImageOrVideo(filePath);
				}//end if item
				
			}//end for item	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end try catch
		return parameters;
	}//end getPostInfo
	
	//This function takes the httpservlet request and returns a GetParameters
	//request:HtttpServletRequest:first object passed,holding the http request
	//return:GetParameters:this object hold the user info
	public GetParameters getInfo (HttpServletRequest request) {
        //Declaring Variables
		GetParameters info = new GetParameters();
		DiskFileItemFactory factory = new DiskFileItemFactory();  //To create fileItem
		File tempFile = new File("C:\\Users\\Noel Gregory\\eclipse-workspace\\ByteWeb\\WebContent\\temp"); //creating area to store content
		factory.setRepository(tempFile);//setting where to store
		ServletFileUpload fileUpload = new ServletFileUpload(factory); 
		List<FileItem>items =null;
		//Parsing request
		try {
			 items = fileUpload.parseRequest(request); //Storing parsed request
			//Iterating through parsed request
			for(FileItem  item : items) {
				if(item.isFormField()) {
					String field = item.getFieldName();
					if(field.equals("username")) {
						info.setUsername(item.getString());
					}else if(field.equals("password")) {
						info.setPassword(item.getString());
					}else if(field.equals("email")) {
						info.setEmail(item.getString());
					}//end if field
				}else {
					info.setImage(item.getInputStream());
				}//end if item
				
			}//end for item
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}//end try catch
		return info;
	}//end getInfo
	
	public GetParameters getChatInfo (HttpServletRequest request) {
        //Declaring Variables
		GetParameters info = new GetParameters();
		String[] members  = null ;
		String repMembers  = null;
		common = new Commons();
		DiskFileItemFactory factory = new DiskFileItemFactory();  //To create fileItem
		File tempFile = new File("C:\\Users\\Noel Gregory\\eclipse-workspace\\ByteWeb\\WebContent\\ChatImages"); //creating area to store content
		factory.setRepository(tempFile);//setting where to store
		ServletFileUpload fileUpload = new ServletFileUpload(factory); //re
		List<FileItem>items =null;
		//Parsing request
		try {
			items = fileUpload.parseRequest(request); //Storing parsed request
			members = new String[items.size() -2 ];
			int count = 0;
			//Iterating through parsed request
			for(FileItem  item : items) {
				if(item.isFormField()) {
					String field = item.getFieldName();
					if(field.equals("ChatName")) {
						info.setChatName(item.getString());
					}else if(field.equals("userSelected")) {
						members[count] = item.getString();
						count++;
					}//end if field
				}else {
					String ext =  FilenameUtils.getExtension((new File(item.getName()).getName()));
					InputStream in = item.getInputStream();
					String filePath = common.chatDownloadStream(in,ext);
					info.setImageUrl(filePath);
				}//end if item
			repMembers = Arrays.toString(members).replace("{", "").replace("}", "");
			info.setMembers(repMembers);
			}//end for item
			
	    } catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	    }
	 return info;
    }//end getChatInfo
	
	/*This function passes in three parameters and returns integer holding number of effected rows
	 * members:String:containing all members of group
	 * chatName:String:containing chat name
	 * imageUrl:Sting: contains location of chat image
	 * returns:int:number of effected rows*/
	public int createChat(String members,String chatName,String imageUrl) {
		//Declare variables
		String query ="insert into chats (ChatName,Members,GroupImage) values(?,?,?)";
		int result = 0;
		
		try {
			pstm = con.prepareStatement(query);
			pstm.setString(1, chatName);
			pstm.setString(2, "Members:" + members);
			pstm.setString(3, imageUrl);
			result = pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end try catch
		
		return result;
	}//end createChat
	
	/*This function passes in one parameters and returns integer holding number of effected rows
	 * members:String:containing all members of group
	 * returns:int:number of effected rows*/
	public int createChat(String members) {
		//Declare variables
		String query ="insert into chats (ChatName,Members,GroupImage) values(?,?,?)";
		int result = 0;
		
		try {
			pstm = con.prepareStatement(query);
			pstm.setString(1, "Null");
			pstm.setString(2, "Members:" + members);
			pstm.setString(3, "Null");
			result = pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end try catch
		
		return result;
	}//end createChat
	
	/*This function passes in three parameter and returns an integer containing number of effected rows
	 *message:String:contains message send
	 *user:String:user that send message
	 *ChatId:String:contains chat id
	 *returns:int:number of rows effected */
	public int addMessage(String message,String user,String chatId,String file) {
		//Declare vaiable
		String query="insert into message(ChatId,message,file,time,user) values(?,?,?,?,?)";
		int result = 0;
		Date now = new Date();
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		try {
			pstm = con.prepareStatement(query);
			pstm.setString(1, chatId);
			pstm.setString(2, message);
			pstm.setString(3, file);
			pstm.setString(4, formatter.format(now));
			pstm.setString(5, user);
			result = pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end try catch
		System.out.println(result);
		return result;
	}//end addMessage
	
	/*This function passes in one parameter and returns a List<Message> object
	 * chatId:String:contains chat id
	 * returns:List<Messgae>:Contains all message of the chat*/
	public List<Message> getMessage(String chatId) {
		//Declare variable
		Message mesg = null;
		List<Message> chatMesg = new ArrayList<Message>();
		String query ="select * from message where ChatId=? ORDER BY ChatId DESC";

		try {
			pstm = con.prepareStatement(query);
			pstm.setString(1, chatId);
			rs = pstm.executeQuery();
			while(rs.next()) {
				mesg = new Message();
				mesg.setMessage(rs.getString("message"));
				mesg.setUserSend(rs.getString("user"));
				mesg.setTime(rs.getString("time"));
				chatMesg.add(mesg);
			}//end while rs.next()
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end try catch
		return chatMesg;
	}//end getMessage
	
	/*This function passes in one parameter and returns a List<UserInbox> object
	 * user:String:contains the user
	 * returns:List<UserInbox:contains all the chat the user is in */
	public List<UserInbox> getUserInbox(String user) {
		//Declare objects and variables
		UserInbox userInbox = null;
		List<UserInbox> inboxArray = new ArrayList<UserInbox>();
		String query = "select * from chats where Members like ?";
		common = new Commons();
		try {
			pstm = con.prepareStatement(query);
			pstm.setString(1, "%"+user+"%");
			rs = pstm.executeQuery();			
			while(rs.next()) {
				userInbox = new UserInbox();
				userInbox.setChatId(Integer.parseInt(rs.getString("idChats")));
				String name = rs.getString("ChatName");
				userInbox.setChatName(name);
				String[] temp = common.splitStringToAr(rs.getString("Members"), "Members:");
				userInbox.setChatMembers(temp);
				userInbox.setGroupImage(rs.getString("GroupImage"));					
				inboxArray.add(userInbox);
			}//end while rs.next()
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end try catch
		return inboxArray;
	}//end getUserInbox
	
	/*This function takes in one parameter and returns integer containing follower count
	 * user:String:username
	 * returns:int:follower count*/
	public int getUserFollowerCount(String user) {
		//Declare variables
		String query="select * from userfollowers where User=?";
		String result = "";
		int count = 0;
		common = new Commons();
		
		try {
			pstm = con.prepareStatement(query);
			pstm.setString(1, user);
			rs = pstm.executeQuery();
			if(rs.next()) {
				result = rs.getString("Followers");
				String finalStr = result.replace("UserRequest:", "");
				if(result.isEmpty()) {
					return count;
				}else {
					count = common.countFollowers(finalStr);
				}//end if result
			}//end if rs
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end try catch
		
		return count;
	}//end getUserFollowerCount

}

