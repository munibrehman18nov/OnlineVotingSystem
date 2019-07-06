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

@WebServlet(name = "ResultsController", urlPatterns = {"/ResultsController"})
public class ResultsController extends HttpServlet {

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
        
        poll = (Poll) session.getAttribute("poll");

        String const_no = request.getParameter("rscbBox").trim();

        ArrayList<Result> rList = new ArrayList<Result>();
        ArrayList<Candidate> cList = new ArrayList<Candidate>();
        cList = poll.getCandidates(const_no);
        ArrayList<Constituency> constList = new ArrayList<Constituency>();
        constList = poll.getConstituencies();
        rList = poll.getResult(const_no);

        session.setAttribute("constList", constList);
        session.setAttribute("cList", cList);
        session.setAttribute("rList", rList);
        session.setAttribute("const_no", const_no);
        response.sendRedirect("./results.jsp");
    
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
