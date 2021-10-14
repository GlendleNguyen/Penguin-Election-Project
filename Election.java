/**
 * Manages the Antarctica election process. 
 *
 * @author Glendle Nguyen 22575354
 * @version 23/04/2020
 */
import java.util.ArrayList;

public class Election
{
    // the list of candidates
    private ArrayList<Candidate> candidates;
    // the list of voting papers
    private ArrayList<VotingPaper> papers;
    // the file of election information
    private FileIO file;

    /**
     * Constructor for objects of class Election.
     * Creates the three field objects.
     */
    public Election(String filename)
    {
       candidates = new ArrayList<>();
       papers = new ArrayList<>();
       file = new FileIO(filename);
    }
    
    /**
     * Constructor for objects of class Election with default files.
     * It uses k to select from the sample input files.
     */
    public Election(int k)
    {
       this("election" + k + ".txt");
    }
    
     /**
     * Returns the candidates list.
     */
    public ArrayList<Candidate> getCandidates()
    {
       return candidates;
    }
    
     /**
     * Returns the papers list.
     */
    public ArrayList<VotingPaper> getPapers()
    {
       return papers;
    }
    
    /**
     * Returns the read-in file contents.
     */
    public ArrayList<String> getFile()
    {
       return file.getLines();
    }
    
     /**
     * Use the file information to initialise the other two fields.
     * Reads the candidates, then discards exactly one blank line, then reads the voting papers.
     * See the sample input files for examples of the format.
     */
    public void processFile() 
    {
        boolean blank = false;
        for (int i=0;i<getFile().size();i++) {
          String line = file.getLines().get(i);
          if (line.isEmpty() && blank == false) {
             blank = true;
          } else 
           if (blank == false) {
             candidates.add(new Candidate(line));
          } else
           if (blank == true) {
             papers.add(new VotingPaper(line));
          }
        }
    }
    
    /**
     * Adds each formal vote to each candidate, both numbers of votes and numbers of wins.
     * Returns the number of informal votes.
     */
    public int conductCount()
    {
       int informal = 0;
       int size = candidates.size();
       for (int i=0; i<papers.size(); i++) {
         if (papers.get(i).isFormal(size) == true){
            papers.get(i).updateVoteCounts(candidates);
            papers.get(i).updateWinCounts(candidates); 
         } else
         informal += 1;
       } 
       return informal;
    }

    /**
     * Returns and prints a summary of the election result. 
     * See the sample output files for the required format. 
     */
    public String getStandings()
    {
       String standings = "";
       for (Candidate c : candidates) {
        String w = String.valueOf(Math.round(c.getNoOfWins()));
        standings += c.getName() + " got " + c.getNoOfVotes() + " votes and " + w + " wins\n";
       } 
       System.out.println(standings);
       return standings;
    }
    
    /**
     * Returns the winner of the election. 
     * Selects the candidate with the highest number of votes; if multiple 
     * candidates are equal, selects the one with the highest number of wins. 
    */
    public Candidate winner()
    {
       Candidate winner = null;
       int wins = (int)candidates.get(1).getNoOfWins();
       int votes = candidates.get(1).getNoOfVotes();
       for (int i=0; i<candidates.size(); i++) {
           if (candidates.get(i).getNoOfVotes() > votes) {
             winner = candidates.get(i); 
             wins = (int)candidates.get(i).getNoOfWins();
             votes = candidates.get(i).getNoOfVotes();
            } else
            if ((int)candidates.get(i).getNoOfVotes() == votes) {
              if (candidates.get(i).getNoOfWins() > wins) {
                 winner = candidates.get(i); 
                } 
            }
        } 
       return winner;
    }
}