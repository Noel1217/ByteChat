package com.User;

import java.util.HashMap;

public class UserInfo {
	
	private String name ;
	private String postId ;
	private HashMap<Integer,Double> userLikedPost ;
	private String pass ;
	private String bio ;
	private String imageOrVideo;
	private String status ;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public HashMap<Integer, Double> getUserLikedPost() {
		return userLikedPost;
	}
	public void setUserLikedPost(int key,Double value) {
		userLikedPost = new HashMap<Integer,Double>(); 
		userLikedPost.put(key, value);
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPostId() {
		return postId;
	}

	public String getImageOrVideo() {
		return imageOrVideo;
	}
	public void setImageOrVideo(String imageOrVideo) {
		this.imageOrVideo = imageOrVideo;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	
	

}
