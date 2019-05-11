package JavaClasses;

import java.util.ArrayList;

public class PollDTO
{
    private PollDAO dao;
    
    public PollDTO()
    {
        dao = new PollDAO();
    }
    
    public ArrayList<Candidate> getCandidates()
    {
        return dao.getAllCandidate();
    }
    public ArrayList<Voter> getVoters()
    {
        return dao.getAllVoters();
    }
    public ArrayList<Constituency> getConstituencies()
    {
        return dao.getAllConstituencies();
    }
    public ArrayList<Party> getParties()
    {
        return dao.getAllParties();
    }   
    public ArrayList<Candidate> getCandidates(String cno)
    {
        return dao.getAllCandidate(cno);
    }
    
    public String getCandidatePartyName(int cpid)
    {
        return dao.getCpartyName(cpid);
    }
    
    
    /*public String isValidVoter(String vid, String vpassword)
    {
        String cname = dao.isVoterExist(vid, vpassword);
        if(cname == null)
        {
            return null;
        }
        return cname;
    }*/
    
    
    
/*    protected int saveVote(String const_no, int cid, int votes)
    {
        return dao.saveVote(const_no, cid, votes);
    }*/
    
/*    protected int updateVoterPassword(String id, String password)
    {
        return dao.setVoterPassword(id, password);
    }*/
    
    protected int insertParty(String name, String email, String password)
    {
        return dao.insertParty(name, email, password);
    }
    public int insertCandidates(ArrayList<Candidate> cList)
    {
        return dao.insertCandidates(cList);
    }
    public int insertVoter(String name, String id, String const_no, String password)
    {
        return dao.insertVoter(name, id, const_no, password);
    }
    
/*    public int insertCandidates(ArrayList<Candidate> cList)
    {
        return dao.insertCandidates(cList);
    }*/
    
    
    protected void updatePollData(ArrayList<ConstNode>nodeList)
    {
        if(!nodeList.isEmpty())
        {
            dao.updatePollData(nodeList);
        }
    }
    protected void insertPollToDB(ArrayList<ConstNode>nodeList)
    {
        if(!nodeList.isEmpty())
        {
            dao.insertPollToDB(nodeList);
        }
    }
    public int updateVoterVoteCast(String vid)
    {
        return dao.updateVoterVoteCast(vid);
    }
    protected void updatePollListsToDB(Poll poll)
    {
        dao.updatePollListsToDB(poll);
    }
    
    public int updatePollVote(String const_no, int cid, int votes)
    {
        return dao.updatePollVote(const_no, cid, votes);
    }
    public int updateCandidateVote(int cid, int votes)
    {
        return dao.updateCandidateVote(cid, votes);
    }
}
