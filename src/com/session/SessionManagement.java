//Noel Gregory
//2020-04-18
//This class add session attributes
package com.session;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.Connector.Dao;


@WebListener
public class SessionManagement implements HttpSessionAttributeListener {

    //this procedure takes in a HttpSessionBindingEvent object and add attribute
	//se:HttpSessionBindingEvent:this object holds session event
    public void attributeAdded(HttpSessionBindingEvent se)  { 
    	//Declare variables and objects
     	Dao sessionDao = new Dao();
     	String status = "Online";
     	String user =  se.getValue().toString(); // getting attribute value(username)
     	
     	 //Updating status
     	sessionDao.userStatus(user, status); //changing status
    	sessionDao.closePstm();
     	sessionDao.closeCon();
    }//attributeAdded

    //this procedure takes in a HttpSessionBindingEvent object and removes attribute
	//se:HttpSessionBindingEvent:this object holds session event
    public void attributeRemoved(HttpSessionBindingEvent se)  { 
    	//Declare variables
     	Dao sessionDao = new Dao();
     	String status = "Offline";
     	String user =  se.getValue().toString(); // getting attribute value(username)
     	
        //Updating status
     	sessionDao.userStatus(user, status); //changing status
     	sessionDao.closePstm();
     	sessionDao.closeCon();
    }//end attributeRemoved

	
	
}
