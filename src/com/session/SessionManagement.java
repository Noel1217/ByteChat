package com.session;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.Connector.Dao;

/**
 * Application Lifecycle Listener implementation class SessionManagement
 *
 */
@WebListener
public class SessionManagement implements HttpSessionAttributeListener {

    /**
     * Default constructor. 
     */
    public SessionManagement() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent se)  { 
    	//Declare variables
     	Dao sessionDao = new Dao();
     	String status = "Online";
     	String user =  se.getValue().toString(); // getting attribute value(username)
     	System.out.println("Online");
     	 //Updating status
     	sessionDao.userStatus(user, status); //changing status
    	sessionDao.closePstm();
     	sessionDao.closeCon();
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent se)  { 
    	//Declare variables
     	Dao sessionDao = new Dao();
     	String status = "Offline";
     	String user =  se.getValue().toString(); // getting attribute value(username)
     	
        //Updating status
     	sessionDao.userStatus(user, status); //changing status
     	sessionDao.closePstm();
     	sessionDao.closeCon();
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent se)  { 
         // TODO Auto-generated method stub
    }
	
}
