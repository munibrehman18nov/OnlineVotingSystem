package Controllers;

import JavaClasses.Candidate;
import JavaClasses.Poll;
import JavaClasses.PollDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "PartyCandidatesController", urlPatterns = {"/PartyCandidatesController"})
public class PartyCandidatesController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        PollDTO dto = new PollDTO();
        HttpSession session = request.getSession(true);

        Poll poll = (Poll) session.getAttribute("poll");
        if(poll == null)
        {
            poll = new Poll();
            session.setAttribute("poll", poll);
        }
        
        if(request.getParameter("submit").equals("Add"))
        {
            poll = (Poll) session.getAttribute("poll");
            ArrayList<Candidate> cList  = (ArrayList<Candidate>)session.getAttribute("cList");
            if(cList == null)
            {
                cList = new ArrayList<Candidate>();
            }

            String cname = request.getParameter("cname");
            int cpid = (Integer)session.getAttribute("pid");
            String const_no = request.getParameter("constCbBox").trim();

            Candidate c = new Candidate(0, cname, cpid, const_no, 0);
            cList.add(c);
            session.setAttribute("cList", cList);

            response.sendRedirect("./partycand.jsp");
        }
        else if(request.getParameter("submit").equals("Done"))
        {
            ArrayList<Candidate> cList = (ArrayList<Candidate>)session.getAttribute("cList");

            dto.insertCandidates(cList);
            //poll.insertPollToDB();
            poll = new Poll();                    
            session.setAttribute("poll", poll);
            session.invalidate();
            response.sendRedirect("./index.html");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
