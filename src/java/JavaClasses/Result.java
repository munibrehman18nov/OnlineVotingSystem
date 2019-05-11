package JavaClasses;

public class Result
{
    
    private String cname;
    private String cpname;
    private int cvotes;

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCpname() {
        return cpname;
    }

    public void setCpname(String cpname) {
        this.cpname = cpname;
    }

    public int getCvotes() {
        return cvotes;
    }

    public void setCvotes(int cvotes) {
        this.cvotes = cvotes;
    }

    public Result(String cname, String cpname, int cvotes) {
        setCname(cname);
        setCpname(cpname);
        setCvotes(cvotes);
    }
    
    public String toString()
    {
        return cname +" "+ cpname +" "+ cvotes;
    }
}
