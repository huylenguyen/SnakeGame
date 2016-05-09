import javax.swing.*;
import javax.swing.SwingConstants;
import java.awt.*;

/**
 * This class takes user input and stores them, which are used by the
 * GUI to change the size or the colour options of the game gruid.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DisplayOptions extends JPanel
{
    private JSpinner colourScheme;
    private JSpinner size;
    private JButton bReset;

    /**
     * Creates a new JPanel that contains the input fields and a reset button to update the game
     * @param window The centralised ActionListener to handle ActionEvent objects 
     *      from this component
     */
    public DisplayOptions(GameWindow window)
    {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        //Grid size options
        JLabel sizeLabel = new JLabel("Set grid size: ");
        this.add(sizeLabel);
        
        size = new JSpinner(new SpinnerNumberModel(25, 10, 40, 1));
        size.setMaximumSize(new Dimension(120, 50));
        size.setAlignmentX(SwingConstants.CENTER);
        this.add(size);
        
        //Tile colours options
        JLabel colourLabel = new JLabel("Set colour scheme: ");
        this.add(colourLabel);
        
        colourScheme = new JSpinner(new SpinnerNumberModel(1, 1, 3, 1));
        colourScheme.setMaximumSize(new Dimension(120, 50));
        colourScheme.setAlignmentX(SwingConstants.CENTER);
        this.add(colourScheme);
        
        //Button to implement the setting changes
        bReset = new JButton("Reset Game");
        bReset.addActionListener(window);
        this.add(bReset);
        
        this.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 10));
    }
    
    /**
     * Returns the value of the colour scheme input
     */
    public int getColourScheme()
    {
        return Integer.parseInt(colourScheme.getValue().toString());
    }
    
    /**
     * Returns the value of the grid size input
     */
    public int getGridSize()
    {
        return Integer.parseInt(size.getValue().toString());
    }
    
    /**
     * Returns the size JSpinner
     */
    public JSpinner getSizeSpinner()
    {
        return size;
    }

    /**
     * Returns the colourScheme JSpinner
     */
    public JSpinner getColourSpinner()
    {
        return colourScheme;
    }
}
