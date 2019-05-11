package JavaClasses;

import com.sun.tools.xjc.model.CAdapter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PollDAO
{
    private Connection con;
    private ResultSet rs;
    private PreparedStatement pst;
    
    protected boolean OpenConnection()
    {
        try
        {
            //Class.forName("oracle.jdbc.driver.OracleDriver");
            Class.forName("com.mysql.jdbc.Driver");
            //con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","MYDB","1234");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?zeroDateTimeBehavior=convertToNull","root","");
            System.out.println("Hello");
            return true;
        }
         catch(ClassNotFoundException ex)
         {
             System.out.println("Connection Failed: Class NotFound");
         }
         catch(SQLException ex)
         {
             System.out.println(ex.getMessage());
         }
        return false;
    }
    protected boolean CloseConnection()
    {
        try
        {
            con.close();
            return true;
        }
         catch (SQLException ex)
         {
             System.out.println("Connection not Found or Closed");
         }
        return false;
    }
    private void Query(String q)
    {
        OpenConnection();
        pst = null;
        rs = null;
        try
        {
            pst = con.prepareStatement(q);
            rs = pst.executeQuery(q);
        }
         catch (SQLException ex)
         {
             System.out.println("Query(String q): SQLException: Statement is not created");
         }
        
    }
    
    
    
    protected boolean isPartyExist(int cpid)
    {
        String q = "select * from party where pid = "+ cpid;
        Query(q);
        try
        {
            if(rs.next())
            {
                return true;
            }
        } catch (SQLException ex)
        {
            System.err.println(ex);
        }
        finally
        {
            try {
                rs.close();
                pst.close();
                CloseConnection();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
            
        }
        return false;
    }
    protected boolean isConstExist(String const_no)
    {
        String q = "select * from constituency where const_no = '"+ const_no +"'";
        Query(q);
        try
        {
            if(rs.next())
            {
                return true;
            }
        } catch (SQLException ex)
        {
            System.err.println(ex);
        }
        finally
        {
            try {
                rs.close();
                pst.close();
                CloseConnection();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
            
        }
        return false;
    }
    protected String isVoterExist(String vid, String vpassword)
    {
        String q = "select * from voters where vid = '"+vid+"' and password = '"+vpassword+"'";
        Query(q);
        try {
            if(rs.next())
            {
                return rs.getString("const_no");
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        finally
        {
            try {
                rs.close();
                pst.close();
                CloseConnection();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
            
        }
        return null;
    }
    
    
    
    
    protected ArrayList<Candidate> getAllCandidate()
    {
        String q = "select * from candidates order by const_no, cid";
        Query(q);
        
        ArrayList<Candidate> list = new ArrayList<Candidate>();
        try {
            while(rs.next())
            {
                Candidate c1 = new Candidate(rs.getInt("cid"), rs.getString("cname"), rs.getInt("cpid"), rs.getString("const_no"), rs.getInt("total_votes"));
                list.add(c1);
            }
        } catch (SQLException ex) {
            System.err.println(ex);;
        }
        finally
        {
            try {
                rs.close();
                pst.close();
                CloseConnection();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
            
        }
        return list;
    }
    protected ArrayList<Constituency> getAllConstituencies()
    {
        String q = "select * from constituency order by const_no";
        Query(q);
        
        ArrayList<Constituency> list = new ArrayList<Constituency>();
        try {
            while(rs.next())
            {
                Constituency c = new Constituency(rs.getString("const_no"), rs.getString("city"), rs.getString("province"));
                list.add(c);
            }
        } catch (SQLException ex) {
            System.err.println(ex);;
        }
        finally
        {
            try {
                rs.close();
                pst.close();
                CloseConnection();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
            
        }
        return list;
    }
    protected ArrayList<Voter> getAllVoters()
    {
        String q = "select * from voters  order by const_no";
        Query(q);
        
        ArrayList<Voter> list = new ArrayList<Voter>();
        try {
            while(rs.next())
            {
                Voter v = new Voter(rs.getString("vid"), rs.getString("name"), rs.getString("password"), rs.getString("const_no"), rs.getInt("is_casted"));
                list.add(v);
            }
        } catch (SQLException ex) {
            System.err.println(ex);;
        }
        finally
        {
            try {
                if(rs!=null)
                    rs.close();
                pst.close();
                CloseConnection();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
            
        }
        
        return list;
    }
    protected ArrayList<Party> getAllParties()
    {
        String q = "select * from party";
        Query(q);
        
        ArrayList<Party> list = new ArrayList<Party>();
        try {
            while(rs.next())
            {
                Party p = new Party(rs.getInt("pid"), rs.getString("name"), rs.getString("email"), rs.getString("password"));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.err.println(ex);;
        }
        finally
        {
            try {
                rs.close();
                pst.close();
                CloseConnection();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
            
        }
        
        return list;
    }
    
    
    
    
    
    protected ArrayList<Candidate> getAllCandidate(String cno)
    {
        String q = "select * from candidates where const_no = '"+cno+"'" + "order by const_no, cid";
        Query(q);
        
        ArrayList<Candidate> list = new ArrayList<Candidate>();
        try {
            while(rs.next())
            {
                Candidate c1 = new Candidate(rs.getInt("cid"), rs.getString("cname"), rs.getInt("cpid"), rs.getString("const_no"), rs.getInt("total_votes"));
                list.add(c1);
            }
        } catch (SQLException ex) {
            System.err.println(ex);;
        }
        finally
        {
            try {
                rs.close();
                pst.close();
                CloseConnection();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
            
        }
        
        return list;
    }
    public String getCpartyName(int cpid)
    {
        String s = "select * from party where pid = "+cpid;
        Query(s);
        try {
            if(rs.next())
            {
                return rs.getString("name");
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        finally
        {
            try {
                rs.close();
                pst.close();
                CloseConnection();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
            
        }
        return null;
    }
    

    
    
    
    
    protected int updateVoterPassword(String id, String password)
    {
        OpenConnection();
        String q = "update voters set password=? where vid=?";
        int row = 0;
        try {
            pst = con.prepareStatement(q);
            pst.setString(1, password);
            pst.setString(2, id);
            row = pst.executeUpdate();
            
        } catch (SQLException ex) {
            
            System.err.println(ex);
        }
        finally
        {
            try {
                pst.close();
                CloseConnection();
            } catch (SQLException ex) {
                try {
                    con.rollback();
                } catch (SQLException ex1) {
                    System.err.println(ex1);
                }
                System.err.println(ex);
            }
            
        }
        if(row == 0)
            return 0;
        return row;
        
    }
    
    
    
    
    
    
    protected int insertCandidates(ArrayList<Candidate> cList)
    {
        OpenConnection();
        String q = "insert into candidates values (null, ?, ?, ?, 0)";
        int row[] = null;
        try {
            pst = con.prepareStatement(q);
            con.setAutoCommit(false);
            for(int i=0; i<cList.size(); i++)
            {
                pst.setString(1, cList.get(i).getCname());
                pst.setInt(2, cList.get(i).getCpid());
                pst.setString(3, cList.get(i).getConst_no());
                pst.addBatch();
            }
            row = pst.executeBatch();
            con.commit();
            
        } catch (SQLException ex) {
            try {
                    con.rollback();
                } catch (SQLException ex1) {
                    System.err.println(ex1);
                }
            System.err.println(ex);
        }
        finally
        {
            try {
                pst.close();
                CloseConnection();
            } catch (SQLException ex) {
                
                System.err.println(ex);
            }
            
        }
        if(row == null)
            return 0;
        return row.length;
    }
    protected int insertConstituencies(ArrayList<Constituency> constList)
    {
        OpenConnection();
        String q = "insert into constituency values (?, ?, ?)";
        int row[] = null;
        try {
            pst = con.prepareStatement(q);
            con.setAutoCommit(false);
            for(int i=0; i<constList.size(); i++)
            {
                pst.setString(1, constList.get(i).getConst_no());
                pst.setString(2, constList.get(i).getCity());
                pst.setString(3, constList.get(i).getProvince());
                pst.addBatch();
            }
            row = pst.executeBatch();
            con.commit();
            
        } catch (SQLException ex) {
            try {
                    con.rollback();
                } catch (SQLException ex1) {
                    System.err.println(ex1);
                }
            System.err.println(ex);
        }
        finally
        {
            try {
                pst.close();
                CloseConnection();
            } catch (SQLException ex) {
                
                System.err.println(ex);
            }
            
        }
        if(row == null)
            return 0;
        return row.length;
    }
    protected int insertVoters(ArrayList<Voter> vList)
    {
        OpenConnection();
        String q = "insert into voters values (?, ?, ?, ?, 0)";
        int row[] = null;
        try {
            pst = con.prepareStatement(q);
            con.setAutoCommit(false);
            for(int i=0; i<vList.size(); i++)
            {
                pst.setString(1, vList.get(i).getVid());
                pst.setString(2, vList.get(i).getName());
                pst.setString(3, vList.get(i).getPassword());
                pst.setString(4, vList.get(i).getConst_no());
                pst.addBatch();
            }
            row = pst.executeBatch();
            con.commit();
            
        } catch (SQLException ex) {
            try {
                    con.rollback();
                } catch (SQLException ex1) {
                    System.err.println(ex1);
                }
            System.err.println(ex);
        }
        finally
        {
            try {
                pst.close();
                CloseConnection();
            } catch (SQLException ex) {
                
                System.err.println(ex);
            }
            
        }
        if(row == null)
            return 0;
        return row.length;
    }
    protected int insertVoter(String name, String id, String const_no, String password)
    {
        OpenConnection();
        String q = "insert into voters values (?, ?, ?, ?, 0)";
        int row = 0;
        try {
            pst = con.prepareStatement(q);
            pst.setString(1, id);
            pst.setString(2, name);
            pst.setString(3, password);
            pst.setString(4, const_no);

            row = pst.executeUpdate();
            
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        finally
        {
            try {
                pst.close();
                CloseConnection();
            } catch (SQLException ex) {
                
                System.err.println(ex);
            }
            
        }
        return row;
    }
    protected int insertParty(String name, String email, String password)
    {
        OpenConnection();
        String q = "insert into party values (null, ?, ?, ?)";
        int row = 0;
        try {
            pst = con.prepareStatement(q);
            pst.setString(1, name);
            pst.setString(2, email);
            pst.setString(3, password);
            row = pst.executeUpdate();
            
        } catch (SQLException ex) {
            
            System.err.println(ex);
        }
        finally
        {
            try {
                pst.close();
                CloseConnection();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
        }

        return row;
    }
    protected int insertPollToDB(ArrayList<ConstNode>nodeList)
    {
        OpenConnection();
        String q = "insert into polls values (?, ?, ?)";
        int row[] = null;
        try {
            pst = con.prepareStatement(q);
            con.setAutoCommit(false);
            for(int i=0; i<nodeList.size(); i++)
            {
                for(int j=0; j<nodeList.get(i).getCList().size(); j++)
                {
                    pst.setString(1, nodeList.get(i).getCList().get(j).getConst_no());
                    pst.setInt(2, nodeList.get(i).getCList().get(j).getCid());
                    pst.setInt(3, nodeList.get(i).getCList().get(j).getTotal_votes());
                    pst.addBatch();
                }
            }
            row = pst.executeBatch();
            con.commit();
            
        } catch (SQLException ex) {
            try {
                    con.rollback();
                } catch (SQLException ex1) {
                    System.err.println(ex1);
                }
            System.err.println(ex);
        }
        finally
        {
            try {
                pst.close();
                CloseConnection();
            } catch (SQLException ex) {
                
                System.err.println(ex);
            }
            
        }
        if(row == null)
            return 0;
        return row.length;
    }
    protected int updatePollData(ArrayList<ConstNode>nodeList)
    {
        OpenConnection();
        String q = "update polls set total_votes=? where cid=? and const_no=?";
        int row = 0;
        try {
            for(int i=0; i<nodeList.size(); i++)
            {
                for(int j=0; j<nodeList.get(i).getCList().size(); j++)
                {
                    pst.close();
                    pst = con.prepareStatement(q);
                    pst.setInt(1, nodeList.get(i).getCList().get(j).getTotal_votes());
                    pst.setInt(2, nodeList.get(i).getCList().get(j).getCid());
                    pst.setString(3, nodeList.get(i).getCList().get(j).getConst_no());
                    row = row + pst.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        finally
        {
            try {
                pst.close();
                CloseConnection();
            } catch (SQLException ex) {
                
                System.err.println(ex);
            }
            
        }
        return row;

    }
    protected void updatePollListsToDB(Poll poll)
    {
        ArrayList<Candidate> cList = new ArrayList<Candidate>();
        cList = poll.getCandidates();
//        ArrayList<Voter> vList = new ArrayList<Voter>();
//       vList = poll.getVoters();
//        ArrayList<Constituency> constList = new ArrayList<Constituency>();
//        constList = poll.getConstituencies();
//        ArrayList<Party> pList = new ArrayList<Party>();
//        pList = poll.getParty();
        
        updateCandidates(cList);
        //updateVoters(vList);
        //updateParties(pList);
        //updateConstituencies(constList);
    }
    
    
    
    
    protected int updateCandidates(ArrayList<Candidate> cList)
    {
        OpenConnection();
        String q = "update candidates set total_votes=? where cid=?";
        int row = 0;
        try {
            for(int i=0; i<cList.size(); i++)
            {
                pst.close();
                pst = con.prepareStatement(q);
                pst.setInt(1, cList.get(i).getTotal_votes());
                pst.setInt(2, cList.get(i).getCpid());
                row = row + pst.executeUpdate();
            }
        } catch (SQLException ex) {
            
            System.err.println(ex);
        }
        finally
        {
            try {
                pst.close();
                CloseConnection();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
            
        }
        return row;
    }
    protected int updateVoterVoteCast(String vid)
    {
        OpenConnection();
        String q = "update voters set is_casted=1 where vid=?";
        int row = 0;
        try {
            pst = con.prepareStatement(q);
            pst.setString(1, vid);
            row = pst.executeUpdate();
        } catch (SQLException ex) {
            
            System.err.println(ex);
        }
        finally
        {
            try {
                pst.close();
                CloseConnection();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
            
        }
        return row;
    }
    
    protected int updateVoters(ArrayList<Voter> vList)
    {
        OpenConnection();
        String q = "update voters set password=?, const_no=? where vid=?";
        int row[] = null;
        try {
            pst = con.prepareStatement(q);
            con.setAutoCommit(false);
            for(int i=0; i<vList.size(); i++)
            {
                pst.setString(1, vList.get(i).getPassword());
                pst.setString(2, vList.get(i).getConst_no());
                pst.setString(3, vList.get(i).getVid());
                pst.addBatch();
            }
            row = pst.executeBatch();
            con.commit();
        } catch (SQLException ex) {
            
            System.err.println(ex);
        }
        finally
        {
            try {
                pst.close();
                CloseConnection();
            } catch (SQLException ex) {
                try {
                    con.rollback();
                } catch (SQLException ex1) {
                    System.err.println(ex1);
                }
                System.err.println(ex);
            }
            
        }
        if(row == null)
            return 0;
        return row.length;
    }
    protected int updateParties(ArrayList<Party> pList)
    {
        OpenConnection();
        String q = "update party set name=?, email=?, password=? where pid=?";
        int row[] = null;
        try {
            pst = con.prepareStatement(q);
            con.setAutoCommit(false);
            for(int i=0; i<pList.size(); i++)
            {
                pst.setString(1, pList.get(i).getName());
                pst.setString(2, pList.get(i).getEmail());
                pst.setString(3, pList.get(i).getPassword());
                pst.setInt(4, pList.get(i).getPid());
                pst.addBatch();
            }
            row = pst.executeBatch();
            con.commit();
        } catch (SQLException ex) {
            
            System.err.println(ex);
        }
        finally
        {
            try {
                pst.close();
                CloseConnection();
            } catch (SQLException ex) {
                try {
                    con.rollback();
                } catch (SQLException ex1) {
                    System.err.println(ex1);
                }
                System.err.println(ex);
            }
            
        }
        if(row == null)
            return 0;
        return row.length;
    }
    protected int updateConstituencies(ArrayList<Constituency> constList)
    {
        OpenConnection();
        String q = "update constituency set city=?, province=? where const_no=?";
        int row[] = null;
        try {
            pst = con.prepareStatement(q);
            con.setAutoCommit(false);
            for(int i=0; i<constList.size(); i++)
            {
                pst.setString(1, constList.get(i).getCity());
                pst.setString(2, constList.get(i).getProvince());
                pst.setString(3, constList.get(i).getConst_no());
                pst.addBatch();
            }
            row = pst.executeBatch();
            con.commit();
        } catch (SQLException ex) {
            
            System.err.println(ex);
        }
        finally
        {
            try {
                pst.close();
                CloseConnection();
            } catch (SQLException ex) {
                try {
                    con.rollback();
                } catch (SQLException ex1) {
                    System.err.println(ex1);
                }
                System.err.println(ex);
            }
            
        }
        if(row == null)
            return 0;
        return row.length;
    }
    
    
    
    
    
    
    
    
    
    protected int updatePollVote(String const_no, int cid, int votes)
    {        
        OpenConnection();
        String q = "update polls set total_votes=? where const_no=? and cid=?";
        int row = 0;
        try {
            pst = con.prepareStatement(q);
            pst.setInt(1, votes);
            pst.setString(2, const_no);
            pst.setInt(3, cid);
            row = pst.executeUpdate();
            
        } catch (SQLException ex) {
            
            System.err.println(ex);
        }
        finally
        {
            try {
                pst.close();
                CloseConnection();
            } catch (SQLException ex) {
                try {
                    con.rollback();
                } catch (SQLException ex1) {
                    System.err.println(ex1);
                }
                System.err.println(ex);
            }
            
        }
        if(row == 0)
            return 0;
        return row;
    }
    protected int updateCandidateVote(int cid, int votes)
    {        
        OpenConnection();
        String q = "update candidates set total_votes=? where cid=?";
        int row = 0;
        try {
            pst = con.prepareStatement(q);
            pst.setInt(1, votes);
            pst.setInt(2, cid);
            row = pst.executeUpdate();
            
        } catch (SQLException ex) {
            
            System.err.println(ex);
        }
        finally
        {
            try {
                pst.close();
                CloseConnection();
            } catch (SQLException ex) {
                try {
                    con.rollback();
                } catch (SQLException ex1) {
                    System.err.println(ex1);
                }
                System.err.println(ex);
            }
            
        }
        if(row == 0)
            return 0;
        return row;
    }
    
    
    
    
    
/*    protected int insert(Candidate c)
    {
        OpenConnection();
        String q = "insert into stdlab8 values (?, ?)";
        int row = 0;
        try {
            PreparedStatement pst = con.prepareStatement(q);
            pst.setString(1, c.getName());
            pst.setString(2, c.getPassword());
            row = pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        CloseConnection();
        return row;
    }*/
    
    /*protected int Insert(String q, ArrayList<PersonController> pl)
    {
        OpenConnection();
        
        int rows[] = null;
        if(q!=null)
        {
            try
            {   pst = con.prepareStatement(q);
                con.setAutoCommit(false);
                for(PersonController p : pl)
                {
                    pst.setString(1, p.getName());
                    pst.setString(2, p.getPhoneNo());
                    pst.setString(3, p.getEmail());
                    pst.setString(4, p.getProfession());
                    pst.setString(5, p.getCity());
                    pst.setString(6, p.getWebUrl());
                    pst.addBatch();
                }
                rows = pst.executeBatch();
                con.commit();
            }
             catch (SQLException ex)
             {
                try
                {
                    con.rollback();
                }
                 catch(SQLException sqlex)
                 {
                    System.out.println("SQLException: Insert(String q, ArrayList<Person> pl):  problem in con.rollback");
                 }
                System.out.println("SQLException: Insert(String q, ArrayList<Person> pl):  problem in connection or preparedStatement or executeBatch");
             }
        }
        if(rows != null)
        {
            System.out.println(rows.length + " Batch row(s) added");
            return rows.length;
        }
        else
        {
            System.out.println(0+ " row(s) added");
        }
        return 0;
    }*/
    
    protected int Delete(String q)
    {
        OpenConnection();
        pst = null;
        int rows = 0;
        
        try
        {
            pst = con.prepareStatement(q);
            rows = pst.executeUpdate(q);
        }
         catch(SQLException ex)
         {
             System.out.println("SQLException: Statement is not created");
         }
        return rows;
    }
    
    public void showDatabaseMetaData()
    {
        OpenConnection();
        
        try {
            DatabaseMetaData dbmd = con.getMetaData();
            System.out.println("Database Product Name: " +dbmd.getDatabaseProductName());
            System.out.println("Database Product Version Number: " +dbmd.getDatabaseProductVersion());
            System.out.println("JDBC Driver Name: " +dbmd.getDriverName());
            System.out.println("Is Database Read Only: " +dbmd.isReadOnly());
            
        }
         catch (SQLException ex)
         {
             System.out.println(ex);
         }
        finally
        {
            CloseConnection();
            
        }
    }
}
