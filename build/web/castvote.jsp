<%-- 
    Document   : castvote
    Created on : Jan 6, 2018, 1:00:27 PM
    Author     : Munib Rehman
--%>

<%@page import="JavaClasses.Constituency"%>
<%@page import="JavaClasses.Poll"%>
<%@page import="JavaClasses.PollDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="JavaClasses.Candidate" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Cast - Vote</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="index.css" type="text/css" rel="stylesheet">
    </head>
    
    <body>
        <div id = "container" style=" width:100%; overflow:auto;">



            <div style="margin:1px 1px 1px 0px; width:100%; height:136px; overflow:auto;">
                <div id="header">
                    <header><h2>CAST VOTE</h2></header>
                        <div id="ul1">
                            <ul>
                                <li><a href="index.html">Home</a></li>
                                <li><a href="castvote.jsp">CastVote</a></li>
                                <li><a href="register.html">Register</a></li>
                                <li><a href="candiates.html">Candidates</a></li>
                                <li class="activepg"><a href="#">Results</a></li>
                                <li><a href="contactus.html">Contact Us</a></li>
                                <li><a href="feedback.html">Feedback</a></li>
                            </ul>
                            <div style="clear:both"></div>
                        </div>
                    <div style="clear:both"></div>
                </div> 
            </div>

<% 
PollDTO dto = new PollDTO();
Poll poll = (Poll)session.getAttribute("poll");
ArrayList<Candidate> cList = (ArrayList<Candidate>)session.getAttribute("cList");
ArrayList<Constituency> constList = (ArrayList<Constituency>)session.getAttribute("constList");

%>          

            <div id = "body" style="margin:1px 5px 1px 5px; width:100%; height:1100px; overflow:auto;">
                <h1>ONLINE POLLING SYSTEM</h1>
                <div id="innerbody1" style="padding: 0px 20px 0px 0px; text-align: right; overflow:auto;">
                    <form name="loginForm" action="./Controller" style=" overflow:auto;">
                        <b><label style="padding: 0px 90px 0px 0px; font-size: 25px;">Your National Constituency Number </label><br><label style= "border: 1px; padding: 0px 20px 0px 0px;  font-size: 25px;"><% out.println(session.getAttribute("const_no")); %></label></b>
                        <br><br>
                        <b><label style="padding: 0px 300px 0px 0px; font-size: 25px;">Select Candidate </label><br>

                        <select name="cbBox" style="width: 318px; font-size: 25px; padding: 2px; margin-right: 1px;">
<% 
for(int i=0; i<cList.size(); i++)
{
    String cpname = dto.getCandidatePartyName(cList.get(i).getCpid());
    //String cpname = dto.getCandidatePartyName(cList.get(i).getCpid());
%>        
                        <option value="<% out.println(cList.get(i).getCid()); %>"><% out.println(cList.get(i).getCname()+" - "+ cpname); %></option>
<%                            
}
%>
                        </select></b>

                        <br><br>
                        <b><input style="padding: 5px; font-size: 24px; text-align: center;" type="submit" name="submit" value="VOTE"></b>
                        <input type="hidden" name="srcpg" value="castvote">
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
                                    <a href="results.html">Results</a>
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
