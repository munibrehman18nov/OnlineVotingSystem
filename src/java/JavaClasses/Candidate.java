package JavaClasses;

public class Candidate
{
    private PollDAO dao;
    private int cid;
    private String cname;
    private int cpid;
    private String const_no;
    private int total_votes;

    public int getTotal_votes() {
        return total_votes;
    }

    public void setTotal_votes(int total_votes) {
        this.total_votes = total_votes;
    }

    public int getCid() {
        return this.cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return this.cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public int getCpid() {
        return this.cpid;
    }

    public void setCpid(int cpid) {
        if(dao.isPartyExist(cpid))
        {
            this.cpid = cpid;
        }
        else
        {
            this.cpid = -1;
        }
    }

    public String getConst_no() {
        return this.const_no;
    }

    public void setConst_no(String const_no) {
        this.const_no = const_no;
    }

    public Candidate(int cid, String cname, int cpid, String const_no, int total_votes) {
        dao = new PollDAO();
        setCid(cid);
        setCname(cname);
        setConst_no(const_no);
        setCpid(cpid);
        setTotal_votes(total_votes);
    }

    
    
}
