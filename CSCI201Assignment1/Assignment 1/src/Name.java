public class Name
{
    private String lname;

    private String fname;

    public String getLname ()
    {
        return lname;
    }

    public void setLname (String lname)
    {
        this.lname = lname;
    }

    public String getFname ()
    {
        return fname;
    }

    public void setFname (String fname)
    {
        this.fname = fname;
    }

    @Override
    public String toString()
    {
        return "Name [lname = "+lname+", fname = "+fname+"]";
    }
}