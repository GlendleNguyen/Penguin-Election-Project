/**
 * Represents a candidate in the Antarctica election process.
 *
 * @author Glendle Nguyen 22575354  
 * @version 23/04/2020
 */
public class Candidate
{
    // their name
    private String name;
    // their number of votes
    private int noOfVotes;
    // their number of first places 
    private double noOfWins;

     /**
     * Constructor for objects of class Candidate.
     */
    public Candidate(String name)
    {
       this.name = name;
       noOfVotes = 0;
       noOfWins = 0;
    }

    /**
     * Returns the candidate's name.
     */
    public String getName()
    {
       return name;
    }

     /**
     * Returns the number of votes obtained by the candidate.
     */
    public int getNoOfVotes()
    {
        return noOfVotes;
    }

    /**
     * Returns the number of wins obtained by the candidate.
     */
    public double getNoOfWins()
    {
       return noOfWins;
    }
    
    /**
     * Adds n votes to the candidate. 
     */
    public void addToCount(int n)
    {
       noOfVotes = noOfVotes + n;
    }
    
    /**
     * Adds n wins to the candidate. 
     */
    public void addToWins(double n)
    {
       noOfWins = noOfWins + n;
    }

    /**
     * Returns a string reporting the candidate's result, 
     * rounding the number of wins to the nearest integer. 
     * See the sample output files for the required format. 
     */
    public String getStanding()
    {
       int rank = (int) Math.round(noOfWins); 
       return name + " got " + noOfVotes + " votes and " + rank + " wins";
    }
}
