package JavaClasses;

public class Voter
{
    private PollDAO dao;
    private String vid;
    private String name;
    private String password;
    private String const_no;
    public int isCasted;

    public Voter(String vid, String name, String password, String const_no, int is_casted) {
        dao = new PollDAO();
        setConst_no(const_no);
        setName(name);
        setPassword(password);
        setVid(vid);
        isCasted = is_casted;
    }
    public Voter() {
        
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConst_no() {
        return const_no;
    }

    public void setConst_no(String const_no) {
        if(dao.isConstExist(const_no))
        {
            this.const_no = const_no;
        }
        else
        {
            this.const_no = null;
        }
    }
    
}
