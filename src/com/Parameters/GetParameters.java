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
	
	public String getChatName() {
		return chatName;
	}
	public void setChatName(String chatName) {
		this.chatName = chatName;
	}
	public String getMembers() {
		return members;
	}
	public void setMembers(String members) {
		this.members = members;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public InputStream getImage() {
		return image;
	}
	public void setImage(InputStream image) {
		this.image = image;
	}
	//variables for PostToStory section
	String postName = null;
	String postImageOrVideo = null;
	//Creating setters and getters
	
	public String getPostImageOrVideo() {
		return postImageOrVideo;
	}
	public void setPostImageOrVideo(String postImageOrVideo) {
		this.postImageOrVideo = postImageOrVideo;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}

	
	
	

}
