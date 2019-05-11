package JavaClasses;

public class Constituency
{
    private String const_no;
    private String city;
    private String province;

    public String getConst_no() {
        return const_no;
    }

    public void setConst_no(String const_no) {
        this.const_no = const_no;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Constituency(String const_no, String city, String province) {
        setCity(city);
        setConst_no(const_no);
        setProvince(province);
    }
    
}
