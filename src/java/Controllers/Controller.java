package Controllers;

import JavaClasses.Candidate;
import JavaClasses.Constituency;
import JavaClasses.Party;
import JavaClasses.Poll;
import JavaClasses.PollDTO;
import JavaClasses.Result;
import JavaClasses.Voter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Controller extends HttpServlet
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
        
        
        if(request.getParameter("srcpg") != null)
        {
            if(request.getParameter("srcpg").equals("index") && request.getParameter("submit").equals("GO Voter"))
            {
                poll = (Poll)session.getAttribute("poll");
                String id = (String) request.getParameter("vname");
                String password = (String) request.getParameter("vpassword");
                
                String cno = poll.isValidVoter(id, password);
                if(cno != null)
                {
                    if(poll.isCasted(id) == false)
                    {
                        session.setAttribute("vid", id);
                        session.setAttribute("const_no", cno);
                        ArrayList<Candidate> cList  = new ArrayList<Candidate>();
                        cList = poll.getCandidates(cno);
                        session.setAttribute("cList", cList);

                        ArrayList<Constituency> constList = new ArrayList<Constituency>();
                        constList = poll.getConstituencies();
                        session.setAttribute("constList", constList);
                        response.sendRedirect("./castvote.jsp");
                    }
                    else
                    {
                        response.sendRedirect("index.html");
                    }
                }
                else
                {
                    response.sendRedirect("./register.html");
                }               

            }

            else if(request.getParameter("srcpg").equals("index") && request.getParameter("submit").equals("GO Party"))
            {
                int id = Integer.parseInt(request.getParameter("pid"));
                String password = request.getParameter("ppassword");

                poll = (Poll)session.getAttribute("poll");

                if(poll.isPartyExist(id, password))
                {
                    session.setAttribute("pid", id);
                    
                    ArrayList<Constituency> constList = (ArrayList<Constituency>)session.getAttribute("constList");
                    if(constList == null)
                    {
                        constList = new ArrayList<Constituency>();
                        poll = (Poll) session.getAttribute("poll");
                        constList = poll.getConstituencies();
                        session.setAttribute("constList", constList);
                    }
                                        
                    response.sendRedirect("./partycand.jsp");
                }
                else
                {
                    response.sendRedirect("./pregister.html");
                }
            }
            
            else if(request.getParameter("srcpg").equals("signup"))
            {
                String vname = request.getParameter("vname");
                String vid = request.getParameter("vid");
                String const_no = request.getParameter("vconst_no");
                String vpassword = request.getParameter("vpassword");
                
                
                poll = (Poll)session.getAttribute("poll");
                
                if(poll.isVoterIDExist(vid))
                {
                    if(poll.setVoterPassword(vid, vpassword))
                    {
                        session.setAttribute("poll", poll);
                        response.sendRedirect("./index.html");
                    }
                    else
                    {
                        response.sendRedirect("./register.html");
                    }
                }
                else
                {
                    dto.insertVoter(vname, vid, const_no, vpassword);
                    poll = new Poll();
                    session.setAttribute("poll", poll);
                    response.sendRedirect("./index.html");
                }
            }

            /*else if(request.getParameter("srcpg").equals("login"))
            {
                String id = (String) request.getParameter("vname");
                String password = (String) request.getParameter("vpassword");

                ArrayList<Candidate> cList  = new ArrayList<Candidate>();
                ArrayList<Constituency> constList = new ArrayList<Constituency>();
                poll = (Poll) session.getAttribute("poll");
                if(poll == null)
                {
                    poll = new Poll();
                    session.setAttribute("poll", poll);
                    //out.println(p.toString(const_no));   
                }
                String cno = poll.isValidVoter(id, password);
                //String cno = dto.isValidVoter(id, password);
                constList = (ArrayList<Constituency>)session.getAttribute("constList");
                if(constList==null)
                {
                    constList = poll.getConstituencies();
                }

                if(cno != null)
                {
                    cList = poll.getCandidates(cno);
                    //cList = dto.getCandidates(cno);
                    //out.println(session.getId()+"<br>");
                    //out.println("2fc30d6f9014b76250e2aec96e02"+"<br>");
                    session.setAttribute("cList", cList);
                    session.setAttribute("const_no", cno);
                    session.setAttribute("poll", poll);
                    session.setAttribute("constList", constList);
                    response.sendRedirect("./castvote.jsp");
                }
                else
                {
                    response.sendRedirect("./register.html");
                }

            }*/

            else if(request.getParameter("srcpg").equals("castvote"))
            {
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
            
            else if(request.getParameter("srcpg").equals("pregister"))
            {
                String name = request.getParameter("pname");
                String password = request.getParameter("ppassword");
                String email = request.getParameter("pmail");
                int pid = poll.insertParty(name, email, password);
                if(pid != -1)
                {
                    session.setAttribute("pid", pid);
                    session.setAttribute("poll", poll);
                    response.sendRedirect("./pidprompt.jsp");
                }
                else
                {
                    response.sendRedirect("./pregister.html");
                }

            }
            
            else if(request.getParameter("srcpg").equals("pidprompt"))
            {
                poll = (Poll)session.getAttribute("poll");
                ArrayList<Constituency> constList = (ArrayList<Constituency>)session.getAttribute("const_List");
                if(constList == null)
                {
                    constList = new ArrayList<Constituency>();
                    constList = poll.getConstituencies();
                    session.setAttribute("constList", constList);
                }

                response.sendRedirect("./partycand.jsp");

            }

            else if(request.getParameter("srcpg").equals("partycand"))
            {
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
            else if(request.getParameter("srcpg").equals("results"))
            {
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
        }
        
        else if(request.getParameter("srcpg") == null)
        {
            ArrayList<Candidate> cList = new ArrayList<Candidate>();
            ArrayList<Constituency> constList = new ArrayList<Constituency>();
            ArrayList<Result> rList = new ArrayList<Result>();
            
            poll = (Poll) session.getAttribute("poll");
            String const_no;
            const_no= poll.getConstituencies().get(0).getConst_no();
            cList = poll.getCandidates(const_no);
            constList = poll.getConstituencies();
            rList = poll.getResult(const_no);
            
            session.setAttribute("constList", constList);
            session.setAttribute("cList", cList);
            session.setAttribute("rList", rList);


            session.setAttribute("const_no", const_no);
            session.setAttribute("constList", constList);
            response.sendRedirect("./results.jsp");

        }
        

        //session.setMaxInactiveInterval(300);
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
