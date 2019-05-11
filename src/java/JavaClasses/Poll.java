package JavaClasses;

import java.util.ArrayList;

public class Poll
{
    private PollDTO dto;
    private ArrayList<ConstNode> nodeList;
    
    private ArrayList<Candidate> cList;
    private ArrayList<Voter> vList;
    private ArrayList<Party> pList;
    private ArrayList<Constituency> constList;
    
    public Poll()
    {
        init();
        
    }
    
    private void init()
    {
        dto = new PollDTO();
        
        loadVoters();
        loadCandidates();
        loadParties();
        loadConstituencies();
        
        nodeList = new ArrayList<ConstNode>();
        
        for(int i=0; i<constList.size(); i++)
        {
            String const_no = constList.get(i).getConst_no();
            ArrayList<Candidate> cl = new ArrayList<Candidate>();
            for(int j=0; j<cList.size(); j++)
            {
                if(cList.get(j).getConst_no().equals(const_no))
                {
                    cl.add(cList.get(j));
                }
            }
            ConstNode node = new ConstNode(const_no, cl);
            nodeList.add(node);
        }
        insertPollToDB();
    }
    
    
    
    public int castVote(String const_no, int cid)
    {
        boolean flag = false;
        int v = 0;
        for(int i=0; i<nodeList.size() && flag==false; i++)
        {
            if(nodeList.get(i).getConst_no().equals(const_no) && flag==false)
            {
                for(int j=0; j<nodeList.get(i).getCList().size() && flag==false; j++)
                {
                    if(nodeList.get(i).getCList().get(j).getCid()==cid && flag==false)
                    {
//                        nodeList.get(i).newVote(cid);
                        v = nodeList.get(i).getCList().get(j).getTotal_votes()+1;
                        nodeList.get(i).getCList().get(j).setTotal_votes(v);
                        flag = true;
                    }
                }
            }
        }
        return v;
    }
    
    
    
    
    
    
    
    public ArrayList<Result> getResult(String const_no)
    {
        ArrayList<Result> rl= new ArrayList<Result>();
        for(int i=0; i<nodeList.size(); i++)
        {
            if(nodeList.get(i).getConst_no().equals(const_no))
            {
                ArrayList<Candidate> cl = new ArrayList<Candidate>();
                cl = nodeList.get(i).getCList();
                for(int j=0; j<cl.size(); j++)
                {
                    String cn = cl.get(j).getCname();
                    int cv = cl.get(j).getTotal_votes();
                    String cpname = dto.getCandidatePartyName(cl.get(j).getCpid());
                    Result r = new Result(cn, cpname, cv);
                    rl.add(r);
                    //cs = cs + cl.get(j).getCname() +" - "+cpname +" TOTAL_VOTES: "+ vl.get(j).intValue() +"<br>";
                }
            }
        }
        
        return rl;
    }
    
    
    
    
    // getting/setting Data to/from ArrayLists
    public ArrayList<ConstNode> getNodeList()
    {
        return nodeList;
    }
    public ArrayList<Candidate> getCandidates()
    {
        return cList;
    }
    public ArrayList<Voter> getVoters()
    {
        return vList;
    }
    public ArrayList<Constituency> getConstituencies()
    {
        return constList;
    }
    public ArrayList<Party> getParty()
    {
        return pList;
    }
    
    
    public String getCandidatePartyName(int cid)
    {
        boolean flag = false;
        int cpid = 0;
        for(int i=0; i<cList.size(); i++)
        {
            if(cList.get(i).getCid() == cid)
            {
                cpid = cList.get(i).getCpid();
                flag = true;
            }
        }
        for(int i=0; i<pList.size(); i++)
        {
            if(cpid == pList.get(i).getPid())
            {
                return pList.get(i).getName();
            }
        }
        return null;
    }
    public ArrayList<Candidate> getCandidates(String const_no)
    {
        ArrayList<Candidate> cl = new ArrayList<Candidate>();
        for(int i=0; i<cList.size(); i++)
        {
            if(cList.get(i).getConst_no().equals(const_no))
            {
                cl.add(cList.get(i));
            }
        }
        return cl;
    }
    
    
    public String isValidVoter(String id, String password)
    {
        for(int i=0; i<vList.size(); i++)
        {
            if(vList.get(i).getVid().equals(id) && vList.get(i).getPassword().equals(password))
            {
                return vList.get(i).getConst_no();
            }
        }
        return null;
    }
    public boolean isCasted(String voter_id)
    {
        for(int i=0; i<vList.size(); i++)
        {
            if(vList.get(i).getVid().equals(voter_id))
            {
                if(vList.get(i).isCasted == 0)
                {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean isPartyExist(int id, String password)
    {
        for(int i=0; i<pList.size(); i++)
        {
            if(pList.get(i).getPid()==id && pList.get(i).getPassword().equals(password))
            {
                return true;
            }
        }
        return false;
    }
    
        public boolean setVoterPassword(String id, String password)
    {
        for(int i=0; i<vList.size(); i++)
        {
            if(vList.get(i).getVid().equals(id))
            {
                vList.get(i).setPassword(password);
                return true;
            }
        }
        return false;
    }
        public boolean isVoterIDExist(String id)
        {
            for(int i=0; i<vList.size(); i++)
            {
                if(vList.get(i).getVid().equals(id))
                {
                    return true;
                }
            }
            return false;
        }
    
    
    
    
    
    
    
    // Loading Data into ArrayLists from DB 
    private void loadCandidates()
    {
        cList = new ArrayList<Candidate>();
        cList = dto.getCandidates();
    }  
    private void loadVoters()
    {
        vList = new ArrayList<Voter>();
        vList = dto.getVoters();
    }
    private void loadParties()
    {
        pList = new ArrayList<Party>();
        pList = dto.getParties();
    }
    private void loadConstituencies()
    {
        constList = new ArrayList<Constituency>();
        constList = dto.getConstituencies();
    }
    
    
    
    
    
    
    
    // save ArrayLists to DB
    public void updatePoll()
    {
        dto.updatePollData(this.nodeList);
    }
    public void updatePollListsToDB(Poll poll)
    {
        dto.updatePollListsToDB(poll);
    }
    public void insertPollToDB()
    {
        dto.insertPollToDB(this.nodeList);
    }
    
    
    // single Data Entry to DB
    public int insertParty(String name, String email, String password)
    {
        if(dto.insertParty(name, email, password)==1)
        {
            loadParties();
        }
        for(int i=0; i<pList.size(); i++)
        {
            if(pList.get(i).getEmail().equals(email))
            {
                return pList.get(i).getPid();
            }
        }
        return -1;
    }
}
