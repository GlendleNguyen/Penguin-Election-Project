/**
 * Represents a voting paper in the Antarctica election process. 
 *
 * @author Glendle Nguyen
 * @version 23/04/2020
 */
import java.util.ArrayList;
import java.util.Collections;
 
public class VotingPaper
{
    // the numbers marked on the paper 
    private ArrayList<Integer> marks;

     /**
     * Constructor for objects of class VotingPaper. 
     * s will be a (possibly empty) sequence of integers, separated by commas. 
     * e.g. if s is "1,22,-13,456", marks is set to <1,22,-13,456>. 
     */
    public VotingPaper(String s)
    {
        marks = new ArrayList<>();
        if (!s.isEmpty())
           for (String x : s.split(",")) 
              marks.add(Integer.parseInt(x));
    }
    
     /**
     * Returns the contents of the paper.
     */
    public ArrayList<Integer> getMarks()
    {
        return marks;
    }

     /**
     * Returns true iff the paper has the correct number of marks, 
     * i.e. one for each candidate. 
     */
    public boolean isCorrectLength(int noOfCandidates)
    {
        if (marks.size() == noOfCandidates) {
           return true; 
        } else
        return false;
    }

     /**
     * Returns true iff the sum of the marks is legal, 
     * i.e. no more than total. 
     */
    public boolean isLegalTotal(int total)
    {
        int sum = 0;
        for (int votes : marks) {
           sum += votes;
        }
       
        if (sum <= total) {
           return true; 
        } else
        return false;
    }
    
     /**
     * Returns true iff there are negative marks. 
     */
    public boolean anyNegativeMarks()
    {
         for (int i : marks)
        {
          if (i < 0) {
            return true;
          }
        } 
        return false;
    }

    /**
     * Returns true iff the paper is formal. 
     * It must be the right length with no negative marks and a legal total. 
     */
    public boolean isFormal(int noOfCandidates)
    {
       if (isCorrectLength(noOfCandidates) && isLegalTotal(noOfCandidates) == !anyNegativeMarks()) {
           return true;
        } else
       return false;
    }
    
    /**
     * Adds the appropriate number of votes to each candidate.
     * The kth number goes to the kth candidate.
     */
    public void updateVoteCounts(ArrayList<Candidate> cs)
    {
       for (int i=0; i<marks.size(); i++) {
           int vote = marks.get(i);
           cs.get(i).addToCount(vote);
        }
    }

    /**
     * Returns the indices in marks which have the highest number.
     * e.g. if marks = <4,4,1,5,2>, it returns <3> (because the highest number is at index 3).
     * e.g. if marks = <5,4,1,2,5>, it returns <0,4>.
     * e.g. if marks = <1,1,1,1,1>, it returns <0,1,2,3,4>.
     */
    public ArrayList<Integer> highestVote()
    {
       ArrayList<Integer> highestVotes = new ArrayList<Integer>();
       int max = Collections.max(marks);
       for (int i=0; i<marks.size();i++) {
         if (marks.get(i) == max) highestVotes.add(i);
        }
       return highestVotes;
    }
    
    /**
     * Adds the appropriate number of wins to each candidate.
     * If there are n equal-highest numbers, each of those 
     * candidates receives 1/n wins. 
     */
    public void updateWinCounts(ArrayList<Candidate> cs)
    {
       for (int i=0; i<highestVote().size(); i++) {
           double size = highestVote().size();
           double wins = (1 / size);
           int max = highestVote().get(i);
           cs.get(max).addToWins(wins);
        }
    }
}
