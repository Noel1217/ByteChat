//Noel Gregory
//2020-06-5
//This class stores UserInfo details in a UserInfo object
package com.User;

import java.util.HashMap;

public class UserInfo {
	//Declare variables
	private String name ;
	private String postId ;
	private HashMap<Integer,Double> userLikedPost ;
	private String pass ;
	private String bio ;
	private String imageOrVideo;
	private String status ;

	//This function returns the String name
	//return:String: contains user that name
	public String getName() {
		return name;
	}//end getName
	
	//This procedure passes in one parameter string and insert the value to the  name string
	//name:String:contains username
	public void setName(String name) {
		this.name = name;
	}//end setName
	
	//This function returns the String status
	//return:String: contains user status
	public String getStatus() {
		return status;
	}//end getSatus
	
	//This function returns the String userLikedPost
	//return:String: contains user liked post
	public HashMap<Integer, Double> getUserLikedPost() {
		return userLikedPost;
	}//end getUserLikedPost
	
	//This procedure passes in one parameter string and insert the value to the userLikedPost hashmap
	//key:int:contains user post key
	//value:Double:contaisn post value
	public void setUserLikedPost(int key,Double value) {
		userLikedPost = new HashMap<Integer,Double>(); 
		userLikedPost.put(key, value);
	}//end setUserLikedPost
	
	//This procedure passes in one parameter string and insert the value to the status string
	//status:String:contains user status
	public void setStatus(String status) {
		this.status = status;
	}//end setStatus
	
	//This function returns the String postId
	//return:String: contains user PostId
	public String getPostId() {
		return postId;
	}//end getPostId
	
	//This function returns the String imageOrVideo
	//return:String: contains user file
	public String getImageOrVideo() {
		return imageOrVideo;
	}//end getImageOrVideo
	
	//This procedure passes in one parameter string and insert the value to the imageOrVideo string
	//imageOrVideo:String:contains user file
	public void setImageOrVideo(String imageOrVideo) {
		this.imageOrVideo = imageOrVideo;
	}//end setImageOrVideo
	
	//This procedure passes in one parameter string and insert the value to the postId string
	//postId:String:contains user postId
	public void setPostId(String postId) {
		this.postId = postId;
	}//end setPostId
	
	//This function returns the String pass
	//return:String: contains user password
	public String getPass() {
		return pass;
	}//end getPass
	
	//This procedure passes in one parameter string and insert the value to the pass string
	//pass:String:contains user password
	public void setPass(String pass) {
		this.pass = pass;
	}//end setPass
	
	//This function returns the String bio
	//return:String: contains user bio
	public String getBio() {
		return bio;
	}//end getBio
	
	//This procedure passes in one parameter string and insert the value to the bio string
	//bio:String:contains user bio
	public void setBio(String bio) {
		this.bio = bio;
	}//end setBio

}
