import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This class creates a top-level window with all the GUI components required.
 * 
 * @author Huy Nguyen 
 * @version (a version number or a date)
 */
public class WindowBuilder
{
    //Game information
    private int currentScore;
    private int size;
    
    //GUI objects
    private JFrame frame;
    private Container contentPane;
    private GridDisplay grid;
    private JPanel buttonPanel;
    private ScoreKeeper score;
    private DisplayOptions options;

    private JButton bStart;
    private JButton bStop;

    /**
     * Creates a top-level frame, assembling all the components necessary
     * @param size The size of the GamePanel object to create
     * @param window The centralised ActionListener to handle ActionEvent objects from components
     */
    public WindowBuilder(int size, GameWindow window)
    {
        frame = new JFrame("Snake - Huy Nguyen");
        this.size = size;
        makeMenuBar(window);

        //Sets up the layout
        contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout(7, 7));

        //Creates and adds all necessary components
        grid = new GridDisplay(size); //The game grid
        contentPane.add(grid, BorderLayout.CENTER);
        
        score = new ScoreKeeper();
        contentPane.add(score, BorderLayout.NORTH);
        
        options = new DisplayOptions(window);
        contentPane.add(options, BorderLayout.EAST);
        
        initButtonPanel(window);

        //Ready and display the window
        frame.setMinimumSize(new Dimension(550, 550));
        frame.pack();
        frame.setVisible(true);
    }

    //GUI component building methods
    /**
     * Creates the menubar with all the required menus and menu items
     * @param window The centralised ActionListener to handle ActionEvent objects 
     *      from this component
     */
    private void makeMenuBar(GameWindow window)
    {
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);

        JMenu menu;
        JMenuItem item;

        //Create the Option menu
        menu = new JMenu("Game Option");
        menubar.add(menu);

        item = new JMenuItem("Reset Game");
        item.addActionListener(window);
        menu.add(item);

        //Create the Help menu
        menu = new JMenu("Help");
        menubar.add(menu);

        item = new JMenuItem("How to play");
        item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { helpMenu(); }
            });
        menu.add(item);

        item = new JMenuItem("Project Details");
        item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { projectMenu(); }
            });
        menu.add(item);
    }

    /**
     * Initialise the JPanel handling buttons inside the container
     * @param window The centralised ActionListener to handle ActionEvent objects 
     *      from this component
     */
    private void initButtonPanel(GameWindow window)
    {
        buttonPanel = new JPanel(new GridLayout(1,3));

        //Start button
        bStart = new JButton("Start Game");
        bStart.addActionListener(window);
        buttonPanel.add(bStart);

        //Stop button
        bStop = new JButton("Stop Game");
        bStop.setEnabled(false);
        bStop.addActionListener(window);
        buttonPanel.add(bStop);

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
    }
    
    /**
     * Replaces the current GridDisplay object in the frame with a new one of a certain size
     * @param size The size of the new GridDisplay object.
     */
    public void resize(int size)
    {
        contentPane.remove(grid);
        grid = new GridDisplay(size);
        contentPane.add(grid, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }
    
    //Accessor methods
    /**
     * Returns the JFrame object
     */
    public JFrame getJFrame()
    {
        return frame;
    }

    /**
     * Returns the gamePanel object
     */
    public GridDisplay getDisplay()
    {
        return grid;
    }

    /**
     * Returns the buttonPanel object
     */
    public JPanel getButtonPanel() 
    {
        return buttonPanel;
    }
    
    /**
     * Returns the ScoreKeeper object
     */
    public ScoreKeeper getScoreDisplay()
    {
        return score;
    }
    
    /**
     * Returns the DisplayOptions object
     */
    public DisplayOptions getOptions()
    {
        return options;
    }

    //Button state handling
    /**
     * Sets the "start" button to disabled and "stop" button to enabled
     */
    public void startButtons()
    {
        bStart.setEnabled(false);
        bStop.setEnabled(true);
    }

    /**
     * Sets the "start" button to enabled and "stop" button to disabled
     */
    public void stopButtons()
    {
        bStart.setEnabled(true);
        bStop.setEnabled(false);
    }

    //ActionEvent handling methods
    /**
     * Prompt a dialogue box with the information about how to control the game
     */
    public void helpMenu()
    {
        JOptionPane dialogueBox = new JOptionPane();
        String message = "To start the game, press start. To stop the game, press stop." + 
                         "\nTo control the snake, use the keyboard arrow keys!" +
                         "\nTo change the size of the grid or the colours of the tiles," + 
                         "\nchange the numerical values in the input options and press Reset Game!";
        dialogueBox.showMessageDialog(frame, message);
    }

    /**
     * Prompt a dialogue box with the project details.
     */
    public void projectMenu()
    {
        JOptionPane dialogueBox = new JOptionPane();
        String message = "CO520 Assessment 3 - Huy Nguyen";
        dialogueBox.showMessageDialog(frame, message);
    }
}
