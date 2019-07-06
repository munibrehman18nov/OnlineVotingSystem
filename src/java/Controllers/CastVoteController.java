package Controllers;

import JavaClasses.Candidate;
import JavaClasses.Constituency;
import JavaClasses.Poll;
import JavaClasses.PollDTO;
import JavaClasses.Result;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CastVoteController", urlPatterns = {"/CastVoteController"})
public class CastVoteController extends HttpServlet
{

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
        
        poll = (Poll)session.getAttribute("poll");
        String id = (String) session.getAttribute("vid");
        if(poll.isCasted(id) == false)
        {
            for(int i = 0; i<poll.getVoters().size(); i++)
            {
                if(poll.getVoters().get(i).getVid().equals(id))
                {
                    poll.getVoters().get(i).isCasted = 1;
                    dto.updateVoterVoteCast(id);
                }
            }
            String const_no = (String)session.getAttribute("const_no");
            poll = (Poll)session.getAttribute("poll");

            ArrayList<Candidate> cList = poll.getCandidates(const_no);
            session.setAttribute("cList", cList);
            ArrayList<Constituency> constList = poll.getConstituencies();
            session.setAttribute("constList", constList);

            int cid = Integer.parseInt(request.getParameter("cbBox").trim());
            int ind = -1;
            for(int i=0; i<cList.size(); i++)
            {
                if(cList.get(i).getCid()==cid)
                {
                    ind = i;
                }
            }
            int tv = poll.castVote(const_no, cList.get(ind).getCid());
            session.setAttribute("poll", poll);
            dto.updateCandidateVote(cid, tv);
            dto.updatePollVote(const_no, cid, tv);

            ArrayList<Result> rList = (ArrayList<Result>)session.getAttribute("rList");
            if(rList == null)
            {
                rList = new ArrayList<Result>();
            }
            rList = poll.getResult(const_no);
            session.setAttribute("rList", rList);


            poll = new Poll();
            session.setAttribute("poll", poll);
            poll = (Poll)session.getAttribute("poll");


            response.sendRedirect("./results.jsp");
        }
        else
        {
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
