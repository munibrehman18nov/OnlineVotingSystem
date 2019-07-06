package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import JavaClasses.Constituency;
import JavaClasses.Poll;
import JavaClasses.PollDTO;
import java.util.ArrayList;
import JavaClasses.Candidate;

public final class castvote_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>Cast - Vote</title>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("        <link href=\"index.css\" type=\"text/css\" rel=\"stylesheet\">\n");
      out.write("    </head>\n");
      out.write("    \n");
      out.write("    <body>\n");
      out.write("        <div id = \"container\" style=\" width:100%; overflow:auto;\">\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("            <div style=\"margin:1px 1px 1px 0px; width:100%; height:136px; overflow:auto;\">\n");
      out.write("                <div id=\"header\">\n");
      out.write("                    <header><h2>CAST VOTE</h2></header>\n");
      out.write("                        <div id=\"ul1\">\n");
      out.write("                            <ul>\n");
      out.write("                                <li><a href=\"index.html\">Home</a></li>\n");
      out.write("                                <li><a href=\"castvote.jsp\">CastVote</a></li>\n");
      out.write("                                <li><a href=\"register.html\">Register</a></li>\n");
      out.write("                                <li><a href=\"candiates.html\">Candidates</a></li>\n");
      out.write("                                <li class=\"activepg\"><a href=\"#\">Results</a></li>\n");
      out.write("                                <li><a href=\"contactus.html\">Contact Us</a></li>\n");
      out.write("                                <li><a href=\"feedback.html\">Feedback</a></li>\n");
      out.write("                            </ul>\n");
      out.write("                            <div style=\"clear:both\"></div>\n");
      out.write("                        </div>\n");
      out.write("                    <div style=\"clear:both\"></div>\n");
      out.write("                </div> \n");
      out.write("            </div>\n");
      out.write("\n");
 
PollDTO dto = new PollDTO();
Poll poll = (Poll)session.getAttribute("poll");
ArrayList<Candidate> cList = (ArrayList<Candidate>)session.getAttribute("cList");
ArrayList<Constituency> constList = (ArrayList<Constituency>)session.getAttribute("constList");


      out.write("          \n");
      out.write("\n");
      out.write("            <div id = \"body\" style=\"margin:1px 5px 1px 5px; width:100%; height:1100px; overflow:auto;\">\n");
      out.write("                <h1>ONLINE POLLING SYSTEM</h1>\n");
      out.write("                <div id=\"innerbody1\" style=\"padding: 0px 20px 0px 0px; text-align: right; overflow:auto;\">\n");
      out.write("                    <form name=\"loginForm\" action=\"./Controller\" style=\" overflow:auto;\">\n");
      out.write("                        <b><label style=\"padding: 0px 90px 0px 0px; font-size: 25px;\">Your National Constituency Number </label><br><label style= \"border: 1px; padding: 0px 20px 0px 0px;  font-size: 25px;\">");
 out.println(session.getAttribute("const_no")); 
      out.write("</label></b>\n");
      out.write("                        <br><br>\n");
      out.write("                        <b><label style=\"padding: 0px 300px 0px 0px; font-size: 25px;\">Select Candidate </label><br>\n");
      out.write("\n");
      out.write("                        <select name=\"cbBox\" style=\"width: 318px; font-size: 25px; padding: 2px; margin-right: 1px;\">\n");
 
for(int i=0; i<cList.size(); i++)
{
    String cpname = dto.getCandidatePartyName(cList.get(i).getCpid());
    //String cpname = dto.getCandidatePartyName(cList.get(i).getCpid());

      out.write("        \n");
      out.write("                        <option value=\"");
 out.println(cList.get(i).getCid()); 
      out.write('"');
      out.write('>');
 out.println(cList.get(i).getCname()+" - "+ cpname); 
      out.write("</option>\n");
                            
}

      out.write("\n");
      out.write("                        </select></b>\n");
      out.write("\n");
      out.write("                        <br><br>\n");
      out.write("                        <b><input style=\"padding: 5px; font-size: 24px; text-align: center;\" type=\"submit\" name=\"submit\" value=\"VOTE\"></b>\n");
      out.write("                        \n");
      out.write("                        <input type=\"hidden\" name=\"srcpg\" value=\"castvote\">\n");
      out.write("                    </form>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("                        \n");
      out.write("            <div id=\"footer\" style=\"margin:1px 5px 1px 5px; overflow:auto; width:100%; height:450px;\">\n");
      out.write("                \n");
      out.write("                <footer class=\"footer-distributed\">\n");
      out.write("\t\t\t<div class=\"footer-left\">\n");
      out.write("\t\t\t\t<h3>Online-Poll</h3>\n");
      out.write("\t\t\t\t<p class=\"footer-links\">\n");
      out.write("                                    <a href=\"index.html\">Home</a>\n");
      out.write("                                    <a href=\"login.html\">Login</a>\n");
      out.write("                                    <a href=\"register.html\">Register</a>\n");
      out.write("                                    <a href=\"results.html\">Results</a>\n");
      out.write("                                    <a href=\"contactus.html\">Contact-Us</a>\n");
      out.write("                                    <a href=\"feedback.html\">Feedback</a>\n");
      out.write("\t\t\t\t</p>\n");
      out.write("\n");
      out.write("\t\t\t\t<p class=\"footer-company-name\">Online Polling System</p>\n");
      out.write("\n");
      out.write("\t\t\t\t<div class=\"footer-icons\">\n");
      out.write("\n");
      out.write("\t\t\t\t\t<a href=\"https://www.facebook.com/munibrehman.18nov\"><i class=\"fa fa-facebook\"></i></a>\n");
      out.write("\t\t\t\t\t<a href=\"https://www.instagram.com/munib.18nov/\"><i class=\"fa fa-twitter\"></i></a>\n");
      out.write("\t\t\t\t\t<a href=\"https://www.linkedin.com/in/muhammad-munib-rehman-08aa8814b\"><i class=\"fa fa-linkedin\"></i></a>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\n");
      out.write("\t\t\t</div>\n");
      out.write("\n");
      out.write("\t\t\t<div class=\"footer-right\">\n");
      out.write("\n");
      out.write("\t\t\t\t<p>Contact Us</p>\n");
      out.write("\n");
      out.write("\t\t\t\t<form action=\"#\" method=\"post\">\n");
      out.write("\n");
      out.write("\t\t\t\t\t<input type=\"text\" name=\"email\" placeholder=\"Email\" />\n");
      out.write("\t\t\t\t\t<textarea name=\"message\" placeholder=\"Message\"></textarea>\n");
      out.write("\t\t\t\t\t<button>Send</button>\n");
      out.write("\n");
      out.write("\t\t\t\t</form>\n");
      out.write("\n");
      out.write("\t\t\t</div>\n");
      out.write("\n");
      out.write("\t\t</footer>\n");
      out.write("                \n");
      out.write("                \n");
      out.write("                \n");
      out.write("                \n");
      out.write("            </div>\n");
      out.write("            <div style=\"clear:both\"></div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
