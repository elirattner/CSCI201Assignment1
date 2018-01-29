public class Student
{
    private Name name;

    private int numGrades;

    private double average;

    public Name getName ()
    {
        return name;
    }

    public void setName (Name name)
    {
        this.name = name;
    }

    public int getNumGrades ()
    {
        return numGrades;
    }

    public void setNumGrades (int numGrades)
    {
        this.numGrades = numGrades;
    }

    public double getAverage ()
    {
        return average;
    }

    public void setAverage (double average)
    {
        this.average = average;
    }

    @Override
    public String toString()
    {
        return name.getLname()+", "+name.getFname()+" "+ average;
    }
}