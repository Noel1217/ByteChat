//Noel Gregory
//2020-05-1
//This class hold all parameter given in from website
package com.Parameters;

import java.io.InputStream;

public class GetParameters {
	
	//Variables for registration section
	private String username ;
	private String password ;
	private String email ;
	private InputStream image ;
	//Chat section
	private String chatName;
	private String members;
	private String imageUrl;
	//variables for PostToStory section
	String postName = null;
	String postImageOrVideo = null;
	
	//This function returns the String chatName
	//return:String: contains chat Name
	public String getChatName() {
		return chatName;
	}//end getChatName
	
	//This procedure passes in one parameter string and insert the value to the  chatName string
	//chatName:String:contains chat name
	public void setChatName(String chatName) {
		this.chatName = chatName;
	}//end setChatName
	
	//This function returns the String members
	//return:String: contains chat members
	public String getMembers() {
		return members;
	}//end getMembers
	
	//This procedure passes in one parameter string and insert the value to the  member string
	//members:String:contains chat members
	public void setMembers(String members) {
		this.members = members;
	}//end setMembers
	
	//This function returns the String imageUrl
	//return:String: contains image url
	public String getImageUrl() {
		return imageUrl;
	}//end getImageUrl
	
	//This procedure passes in one parameter string and insert the value to the  imageUrl string
	//imageUrl:String:contains image url
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}//end setImageUrl
	
	//This function returns the String username
	//return:String: contains username of user
	public String getUsername() {
		return username;
	}//end getUsername
	
	//This procedure passes in one parameter string and insert the value to the username string
	//username:String:contains username
	public void setUsername(String username) {
		this.username = username;
	}//end setUsername
	
	//This function returns the String password
	//return:String: contains user password
	public String getPassword() {
		return password;
	}//end getPassword
	
	//This procedure passes in one parameter string and insert the value to the  password string
	//password:String:contains user password
	public void setPassword(String password) {
		this.password = password;
	}//edn setPassword
	
	//This function returns the String email
	//return:String: contains user email
	public String getEmail() {
		return email;
	}//end getEmail
	
	//This procedure passes in one parameter string and insert the value to the  email string
	//email:String:contains user email
	public void setEmail(String email) {
		this.email = email;
	}//end setEmail
	
	//This function returns the InputStream image
	//return:String: contains user image
	public InputStream getImage() {
		return image;
	}//end getImage
	
	//This procedure passes in one parameter string and insert the value to the  image InputStream
	//image:InputStream:contains user image
	public void setImage(InputStream image) {
		this.image = image;
	}//end setImage
	
	//This function returns the String postImageOrVideo
	//return:String: contains user uploaded file
	public String getPostImageOrVideo() {
		return postImageOrVideo;
	}//end getPostImageOrVideo
	
	//This procedure passes in one parameter string and insert the value to the  postImageOrVideo string
	//postImageOrVideo:String:contains user uploaded file
	public void setPostImageOrVideo(String postImageOrVideo) {
		this.postImageOrVideo = postImageOrVideo;
	}//end setPostImageOrVideo
	
	//This function returns the String postName
	//return:String: contains user uploaded post name
	public String getPostName() {
		return postName;
	}//end getPostName
	
	//This procedure passes in one parameter string and insert the value to the  postName string
	//postName:String:contains user uploaded post name
	public void setPostName(String postName) {
		this.postName = postName;
	}//end setPostName

	
	
	

}
