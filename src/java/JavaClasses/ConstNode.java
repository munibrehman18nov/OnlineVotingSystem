package JavaClasses;

import java.util.ArrayList;

public class ConstNode
{
    private String const_no;
    private ArrayList<Candidate> cList;
    
    public void newVote(int cid)
    {
        for(int i=0; i<cList.size(); i++)
        {
            if(cList.get(i).getCid()==cid)
            {
                cList.get(i).setTotal_votes(cList.get(i).getTotal_votes()+1);
                //saveToPollDB(const_no, cList.get(i).getCid(), cList.get(i).getTotal_votes());
                //saveCanidatesData();
            }
        }
    }
    
    public String getConst_no() {
        return const_no;
    }
    public ArrayList<Candidate> getCList() {
        return this.cList;
    }
    public void setCList(ArrayList<Candidate> cList, String const_no) {
        this.cList = new ArrayList<Candidate>();
        for(int i=0; i<cList.size(); i++)
        {
            if(cList.get(i).getConst_no().equals(const_no))
            {
                this.cList.add(cList.get(i));
            }
        }
    }
    private void setConst_no(String const_no) {
        this.const_no = const_no;
    }
    
    
    
    
    
    public ConstNode(String const_no, ArrayList<Candidate> cList) {
        setConst_no(const_no);
        setCList(cList, const_no);
    }
    public ConstNode(){
        
    }
    
}
