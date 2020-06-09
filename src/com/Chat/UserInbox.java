package com.Chat;

import java.io.InputStream;

public class UserInbox {
	
	private int chatId;
	private String[] chatMembers;
	private String chatName;
	private String groupImage; 
	public int getChatId() {
		return chatId;
	}
	public void setChatId(int chatId) {
		this.chatId = chatId;
	}
	public String[] getChatMembers() {
		return chatMembers;
	}
	public String getGroupImage() {
		return groupImage;
	}
	public void setGroupImage(String groupImage) {
		this.groupImage = groupImage;
	}
	public void setChatMembers(String[] chatMembers) {
		this.chatMembers = chatMembers;
	}
	public String getChatName() {
		return chatName;
	}
	public void setChatName(String chatName) {
		this.chatName = chatName;
	}


}
