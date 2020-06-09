package com.Post;

import java.sql.Date;

public class PostInfo {
	
	private String postName ;
	private int postId ;
	private String user ;
	private String postImageOrVideo ;
	private int postLikes ;
	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getPostImageOrVideo() {
		return postImageOrVideo;
	}
	
	public void setPostImageOrVideo(String postImageOrVideo) {
		this.postImageOrVideo = postImageOrVideo;
	}

	public int getPostLikes() {
		return postLikes;
	}


	public void setPostLikes(int postLikes) {
		this.postLikes = postLikes;
	}

	private String postDate = null;
	private String type = null;

	public String getUser() {
		return user;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}


	

}
