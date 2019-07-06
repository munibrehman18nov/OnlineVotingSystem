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
        <title>Poll - Results</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="index.css" type="text/css" rel="stylesheet">
    </head>
    
    <body>
        <div id = "container" style=" width:100%; overflow:auto;">

            <div style="margin:1px 1px 1px 0px; width:100%; height:135px; overflow:auto;">
                <div id="header">
                    <header><h2>RESULTS</h2></header>
                        <div id="ul1" style=" overflow:auto;">
                            <ul>
                                <li><a href="index.html">Home</a></li>
                                <li><a href="./castvote.jsp">CastVote</a></li>
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

Poll poll = (Poll)session.getAttribute("poll");
ArrayList<Constituency> constList = (ArrayList<Constituency>)session.getAttribute("constList");

//session.setAttribute("const_no", cno);
%>          

            <div id = "body" style="margin:1px 5px 1px 5px; width:100%; height:1100px; overflow:auto;">
                <div id="innerbody1" style="padding: 0px 20px 0px 0px; text-align: right; overflow:auto;">
                    <form name="loginForm" action="./ResultsController" style=" overflow:auto;">
                        <br>   
                        <b><label style="padding: 0px 20px 0px 0px; font-size: 24px;">Select Constituency</label>
                        <select name="rscbBox" style="width: 318px; font-size: 22px; padding: 2px; margin-right: 1px;">
<% 
for(int i=0; i<constList.size(); i++)
{
%>        
                        <option value="<% out.println(constList.get(i).getConst_no()); %>"><% out.println(constList.get(i).getConst_no()); %></option>
<%                            
}
%>
                        </select></b>
                        <br>
                        <b><input style="padding: 5px; font-size: 20px; text-align: center;" type="submit" name="submit" value="Search"></b>
                        <br><br>

                        <table border="1" align="right" width="40%" height="20%" style="padding: 1px; margin: 0px 3px 0px 0px; overflow:auto;">
                            <tr>
                                <th style="padding: 0px 0px 0px 20px; font-size: 24px; text-align: center;"><b><% out.println(session.getAttribute("const_no")); %></b></th>
                            </tr>
                            <tr>
                                <th style="padding: 0px 0px 0px 20px; font-size: 18px; text-align: center;"><b>Candidate Name</b></th>
                                <th style="padding: 0px 0px 0px 20px; font-size: 18px; text-align: center;"><b>Party </b></th>
                                <th style="padding: 0px 0px 0px 20px; font-size: 18px; text-align: center;"><b>Votes </b></th>
                            </tr>
<%
ArrayList<Result> rl = new ArrayList<Result>();
rl = (ArrayList<Result>)session.getAttribute("rList");

for(int i=0; i<rl.size(); i++)
{
%>
                            <tr>
                                <td  style="padding: 0px 0px 0px 20px; font-size: 16px; text-align: center;"><b><% out.println(rl.get(i).getCname()); %></b></td>
                                <td  style="padding: 0px 0px 0px 20px; font-size: 16px; text-align: center;"><b><% out.println(rl.get(i).getCpname()); %></b></td>
                                <td  style="padding: 0px 0px 0px 20px; font-size: 16px; text-align: center;"><b><% out.println(rl.get(i).getCvotes()); %></b></td>
                            </tr>
<%
}
%>
                        </table>

                        <br><br>
                        <input type="hidden" name="srcpg" value="results">
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
