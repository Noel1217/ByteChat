//Noel Gregory
//2020-06-5
//This class stores message details in a Message object
package com.Chat;

public class Message {
	//Declare variable
	private String userSend = null;
	private String message = null;
	private String time = null;
	
	//This function returns the String userSend
	//return:String: contains user that send message
	public String getUserSend() {
		return userSend;
	}//end getUserSend
	//This procedure passes in one parameter string and insert the value to the  userSend string
	//userSend:String:contains user that send message
	public void setUserSend(String userSend) {
		this.userSend = userSend;
	}//end setUserSend
	
	//This function returns the String message
	//return:String: contains the message
	public String getMessage() {
		return message;
	}//end getMessage
	
	//This procedure passes in one parameter string and insert the value to the  message string
	//mesage:String: user send message
	public void setMessage(String message) {
		this.message = message;
	}//end setMessage
	
	//This function returns the String time
	//return:String: Contains send message time
	public String getTime() {
		return time;
	}//end getTime
	
	//This procedure passes in one parameter string and inserts the value to the time string
	//time:String:time when user send
	public void setTime(String time) {
		this.time = time;
	}//end setTime

}
