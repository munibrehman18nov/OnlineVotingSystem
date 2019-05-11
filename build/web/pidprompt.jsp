<%-- 
    Document   : pidprompt
    Created on : Jan 21, 2018, 11:44:00 AM
    Author     : Munib Rehman
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Party - Register</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="index.css" type="text/css" rel="stylesheet">
        <script src="pregister.js"></script>
    </head>
    
    <body>
        
        <div id = "container" style=" width:100%; overflow:auto;">

            <div style="margin:1px 1px 1px 1px; overflow:auto; width:100%; height:135px;">
                <div id="header">
                    <header><h2>PARTY REGISTER</h2></header>
                        <div id="ul1" style=" overflow:auto;">
                            <ul>
                                <li><a href="index.html">Home</a></li>
                                <li><a href="login.html">Login</a></li>
                                <li class="activepg"><a href="#">Party Signup</a></li>
                                
                                <li><a href="./Controller">Results</a></li>
                                <li><a href="contactus.html">Contact Us</a></li>
                                <li><a href="feedback.html">Feedback</a></li>
                            </ul>
                            <div style="clear:both"></div>
                        </div>
                        <div style="clear:both"></div>
                </div> 
            </div>



            <div id = "body" style="margin:1px 5px 1px 5px; overflow:auto; width:100%; height:1100px;">
                <div id="signupdiv" style="padding: 30px 0px 0px 700px; overflow:auto; text-align: center;">
                    <form name="pidform" action="./Controller">
<%
    int pid = (Integer)session.getAttribute("pid");
%>

                        <b><label style="padding: 0px 0px 0px 0px; font-size: 3vw;">Congratulations </label><br></b>
                        <b><label style="padding: 0px 0px 0px 0px; font-size: 3vw;">Your Party ID:</label><br></b>
                        <b><label style="padding: 0px 0px 0px 0px; font-size: 5vw;"><% out.println(pid); %> </label><br></b>
                        <b><label style="padding: 0px 0px 0px 50px; font-size: 2vw;">To Add Candidates Click </label></b>
                        <input type="hidden" name="srcpg" value="pidprompt">
                        <b><input style="padding: 5px; margin-left: 10px; font-size: 24px; text-align: center;" type="submit" name="submit3"></b>
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

