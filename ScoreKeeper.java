import javax.swing.JLabel;
import javax.swing.BorderFactory;

/**
 * This class stores and display the score of the current game.
 * 
 * @author Huy Nguyen
 * @version (a version number or a date)
 */
public class ScoreKeeper extends JLabel
{
    private int highScore;

    /**
     * Creates a ScoreKeeper object and sets the score to 0
     */
    public ScoreKeeper()
    {
        super("Score: ", CENTER);
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        highScore = 0;
    }

    /**
     * Sets the value of the highscore
     * @param newScore The new highscore
     */
    public void setHighScore(int newScore)
    {
        highScore = newScore;
    }
    
    /**
     * Returns the score of the game
     */
    public int getHighScore()
    {
        return highScore;
    }
}
