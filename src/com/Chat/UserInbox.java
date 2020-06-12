//Noel Gregory
//2020-06-5
//This class stores each chat information
package com.Chat;

import java.io.InputStream;

public class UserInbox {
	//Declare variable
	private int chatId;
	private String[] chatMembers;
	private String chatName;
	private String groupImage; 
	//This function returns the String chatId
	//return:int: contains chat id
	public int getChatId() {
		return chatId;
	}//end getChatId
	
	//This procedure passes in one parameter string and insert the value to the  chatId string
    //mesage:String: chat id
	public void setChatId(int chatId) {
		this.chatId = chatId;
	}//end setChatId
	
	//This function returns the String userSend
	//return:String[]: contains chat members
	public String[] getChatMembers() {
		return chatMembers;
	}//end getChatName
	
	//This function returns the String userSend
	//return:String[]: contains chat image
	public String getGroupImage() {
		return groupImage;
	}//end getGroupImage
	
	//This procedure passes in one parameter string and insert the value to the  groupImage string
    //mesage:String: group image of chat
	public void setGroupImage(String groupImage) {
		this.groupImage = groupImage;
	}//end setGroupImage
	
	//This procedure passes in one parameter string and insert the value to the  chatMembers
    //mesage:String: chat members
	public void setChatMembers(String[] chatMembers) {
		this.chatMembers = chatMembers;
	}//end setChatMembers
	
	//This function returns the String userSend
	//return:String[]: contains chat name
	public String getChatName() {
		return chatName;
	}//end getChatName
	
	//This procedure passes in one parameter string and insert the value to the chatName string
    //mesage:String: chat name
	public void setChatName(String chatName) {
		this.chatName = chatName;
	}//end setChatName


}
