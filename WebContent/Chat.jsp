<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
    <%@ page import="com.Chat.UserInbox" %>
    <%@ page import="com.Connector.Dao" %>
    <%@ page import="com.Chat.Message" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <title>Chat</title>
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
 <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
 <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
 <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
 <link type="text/css" href="css/style.css" rel="stylesheet">
 <link rel="stylesheet" href="css/chatStyleSheet.css">
 <link href="css/styleSheet.css" rel="stylesheet" type="text/css">
 <link rel="shortcut icon" href="logo.png">
 <script type="text/javascript">

    $(document).ready(function(){
    setInterval(function(){
          $("#displayarea").load("ChatScreen.jsp" );
    }, 1000);
    });

    var element = document.getElementById("ex3");

    element.scrollTop = element.scrollHeight ;
    
    var scale = Math.min( 
    		  availableWidth / contentWidth, 
    		  availableHeight / contentHeight 
    		);
</script>
<style>
	.container {
	width:auto;
	  overflow-y: scroll;
	  overscroll-behavior-y: contain;
	  scroll-snap-type: y mandatory;
	}
	
	.container > div > div:last-child {
	  scroll-snap-align: end;
	}
</style>
<title>Chat</title>
</head>
<body >
<%@ include file="Navbar.jsp" %>  
<%@ include file="sessionRedirecting.jsp" %>  
<%
//Declare variables and objects
List<UserInbox> inboxData = null;
String sessionUser =session.getAttribute("sessionUser").toString().trim();
ArrayList<String> following = (ArrayList<String>) request.getAttribute("following");
String chatId  = null;
UserInbox data = null;
%>
<div class="messaging">
  <div class="inbox_msg">
	<div class="inbox_people">
	  <div class="headind_srch">		
		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalLong">
		  Add Chat
		</button>
		
		<!-- Modal -->
		<div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
		  <div class="modal-dialog modal-dialog-centered" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLongTitle">Add Friends</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <form action="CreateChat?type=message" method="POST">
		      <div class="modal-body">		  	
		        <%
		        if(!following.isEmpty()){
		        	for(String name:following){
		        		if(!name.equals(sessionUser)){
		        		%>		       
		        		<input type="checkbox" name="userSelected" value="<%=name %>">&nbsp;<%=name %>
		        		<%
		        		}//end if name
		        	}//end for name
		        	%>
		        	<input type="checkbox" style="opacity:0;"  name="userSelected" value="<%=sessionUser%>" checked>
		        	<%
		        	
		        }else{
		        	%>
		        	<h5>No Users Available</h5>
		        	
		        	<%
		        }//end if following
		        %>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#exampleModalCenter">Create Group</button>
		        <button type="submit" class="btn btn-primary">Message</button>
		      </div>
		      </form>
		    </div>
		  </div>
		</div>
		
		<!-- Modal 2-->
		<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
		  <div class="modal-dialog modal-dialog-centered" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLongTitle">Add Friends</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <form action="CreateChat?type=group" method="POST" enctype= multipart/form-data>
		      <div class="modal-body">		  
		       <div class="input-group input-group-sm mb-3">
				  <div class="input-group-prepend">
				    <span class="input-group-text" id="inputGroup-sizing-sm">Enter Chat Name</span>
				  </div>
				  <input type="text" class="form-control" aria-label="Small" name="ChatName" aria-describedby="inputGroup-sizing-sm">
				</div>
		        <%
		        if(!following.isEmpty()){
		        	for(String name:following){
		        		if(!name.equals(sessionUser)){
		        		%>
		        		<input type="checkbox" name="userSelected" value="<%=name %>">&nbsp;<%=name %>
		        		<%
		        		}//end if name
		        	}//end for name
		        	%>
		        	<input type="checkbox" style="opacity:0;"  name="userSelected" value="<%=sessionUser %>" checked>
		        	<%
		        	
		        }else{
		        	%>
		        	<h5>No Users Available</h5>
		        	
		        	<%
		        }//end if following
		        %>
		         <div class="custom-file">
                   <input type="file" name="image" class="custom-file-input" accept="image/*"  >
                   <label class="custom-file-label">Upload Image </label>
                 </div>
		      </div>
		      
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
		        <button type="submit" class="btn btn-primary">Add</button>
		      </div>
		      </form>
		    </div>
		  </div>
		</div>
	  </div>
	   <div class="inbox_chat scroll">
	     <%
	  if(request.getAttribute("inboxData") != null){
		  inboxData = (ArrayList<UserInbox>) request.getAttribute("inboxData");
		  for(int i = 0; i<inboxData.size(); i++){
			  data = inboxData.get(i);
			  if(data.getChatMembers().length == 2){
				  System.out.println(Arrays.toString(data.getChatMembers()));
				  for(int j = 0; j <data.getChatMembers().length ; j++){
					  String[] temp = data.getChatMembers();
					  System.out.println(sessionUser);
					  if(!temp[j].trim().equals(sessionUser)){
						  String name = temp[j];
						  %>
							<div class="chat_list">
		                       <div class="chat_people">
								<img class="chat_img" style="border-raduis:50%;" src="./getImage?User=<%=name.trim() %>" alt="error"> 
								<div class="chat_ib">
								  <a  href="./ChatInboxController?Chat=<%=data.getChatId()%>"><h5><%=name%></h5></a>
								  <p>Last message</p>
								</div>
							  </div>
							</div>	
								
						  <%
					  }//end if temp[j]			
				  }//end for j		
				  
				  
			  }else{
				  %>
					<div class="chat_list">
		             <div class="chat_people">
						<div class="chat_img"> <img src="<%=data.getGroupImage() %>" alt="error"> </div>
						<div class="chat_ib">
						
						  <a  href="./ChatInboxController?Chat=<%=data.getChatId() %>"><h5><%=data.getChatName() %></h5></a>
						  <p><%=Arrays.toString(data.getChatMembers()).replace("[", "").replace("]","").replace(sessionUser,"") %></p>
						</div>
					  </div>
					</div>		
				  <%
			  }//end if data
			
		  }//end for i
		  
	  }//end if request
	  %>
		
	  </div>
	</div>	  
	<div class="mesgs">
	  <div class="msg_history">
		<div class="incoming_msg">
		<div class="container" style="height:77vh" >
		<%
		  if(!request.getAttribute("Chat").toString().equals("Null")){
			  chatId  = request.getAttribute("Chat").toString();
			  %>
			  <div id="displayarea"></div>
			  <%
		  }//end if rquest
		%>
		</div>
	  </div>
	  <div class="type_msg">
		<div class="input_msg_write">
		<form action ="SendMessageController" method="POST">
		<input type="hidden" name="chatId" value="<%=chatId %>">
		 <input type="text" class="write_msg" name="message" placeholder="Type a message" />
		  <button class="msg_send_btn" type="submit"><i class="fa fa-paper-plane" aria-hidden="true"></i></button>
		  </form>
		</div>
		</div>
	  </div>
	</div>
  </div>
</div>
</body>
</html>