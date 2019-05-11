<%-- 
    Document   : castvote
    Created on : Jan 6, 2018, 1:00:27 PM
    Author     : Munib Rehman
--%>

<%@page import="JavaClasses.Result"%>
<%@page import="JavaClasses.Constituency"%>
<%@page import="JavaClasses.Poll"%>
<%@page import="JavaClasses.PollDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="JavaClasses.Candidate" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Party - Candidates</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="index.css" type="text/css" rel="stylesheet">
    </head>
    
    <body>
        <div id = "container" style=" width:100%; overflow:auto;">

            <div style="margin:1px 1px 1px 0px; width:100%; height:135px; overflow:auto;">
                <div id="header">
                    <header><h2>Party Candidates</h2></header>
                        <div id="ul1" style=" overflow:auto;">
                            <ul>
                                <li><a href="index.html">Home</a></li>
                                <li class="activepg"><a href="#">Candidates</a></li>
                                <li><a href="./consts.jsp">Constituencies</a></li>
                                <li><a href="contactus.html">Contact Us</a></li>
                                <li><a href="feedback.html">Feedback</a></li>
                            </ul>
                            <div style="clear:both"></div>
                        </div>
                    <div style="clear:both"></div>
                </div> 
            </div>

<% 

Poll poll = (Poll)session.getAttribute("poll");
ArrayList<Constituency> constList = (ArrayList<Constituency>)session.getAttribute("constList");
int pid = (Integer)session.getAttribute("pid");

//session.setAttribute("const_no", cno);
%>          

            <div id = "body" style="margin:1px 5px 1px 5px; width:100%; height:1100px; overflow:auto;">
                <div id="innerbody1" style="padding: 20px 40px 0px 0px; text-align: right; overflow:auto;">
                    <form name="candForm" action="./Controller" style=" overflow:auto;">
                          
                        <label style="padding: 0px 26px 0px 20px; font-size: 22px; text-align: left;"><b>Candidate Name</b></label>
                        <input style="margin: 0px 0px 5px 0px; padding: 5px; font-size: 20px; text-align: left;" type="text" name= "cname" placeholder="Enter Candidate Name Here"><br>

                        <label style="padding: 0px 26px 0px 20px; font-size: 22px; text-align: left;"><b>Party ID</b></label>
                        <label style="margin: 0px 0px 5px 0px; padding: 0px 210px 10px 0px; font-size: 22px; text-align: left;"><b><% out.println(pid); %></b></label><br>
                        

                        <label style="padding: 0px 20px 0px 0px; font-size: 22px;"><b>Select Constituency</b></label>
                        <select name="constCbBox" style="margin-top: 5px; width: 255px; font-size: 22px; padding: 5px; margin-right: 1px;">
<% 
for(int i=0; i<constList.size(); i++)
{
%>        
                        <option value="<% out.println(constList.get(i).getConst_no()); %>"><% out.println(constList.get(i).getConst_no()); %></option>
<%                            
}
%>
                        </select>
                        <br>
                        
                        <input type="hidden" name="srcpg" value="partycand">
                        <b><input style=" margin: 5px 0px 5px 0px; padding: 8px; font-size: 24px; text-align: center;" type="submit" name="submit" value="Add"></b>
                        <br><br>
                        <b><input style="padding: 5px; font-size: 24px; text-align: center;" type="submit" name="submit" value="Done"></b>

                    </form>

                </div>
            </div>

            <div id="footer" style="margin:1px 5px 1px 5px; overflow:auto; width:100%; height:450px;">
                
                <footer class="footer-distributed">
			<div class="footer-left">
				<h3>Online-Poll</h3>
				<p class="footer-links">
                                    <a href="index.html">Home</a>
                                    <a href="login.html">Login</a>
                                    <a href="register.html">Register</a>
                                    <a href="./results.jsp">Results</a>
                                    <a href="contactus.html">Contact-Us</a>
                                    <a href="feedback.html">Feedback</a>
				</p>

				<p class="footer-company-name">Online Polling System</p>

				<div class="footer-icons">

					<a href="https://www.facebook.com/munibrehman.18nov"><i class="fa fa-facebook"></i></a>
					<a href="https://www.instagram.com/munib.18nov/"><i class="fa fa-twitter"></i></a>
					<a href="https://www.linkedin.com/in/muhammad-munib-rehman-08aa8814b"><i class="fa fa-linkedin"></i></a>
				</div>

			</div>

			<div class="footer-right">

				<p>Contact Us</p>

				<form action="#" method="post">

					<input type="text" name="email" placeholder="Email" />
					<textarea name="message" placeholder="Message"></textarea>
					<button>Send</button>

				</form>

			</div>

		</footer>
                
                
                
                
            </div>
            <div style="clear:both"></div>
        </div>

    </body>
</html>
