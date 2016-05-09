import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;
import java.awt.KeyboardFocusManager;

import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;

/**
 * This is the main class of this project.
 * It creates the GUI and the game application, and connects them together.
 * Also acts as the centralised listener for ActionEvents and KeyEvents.
 * 
 * @author Huy Nguyen
 * @version (a version number or a date)
 */
public class GameWindow implements ActionListener
{
    //GUI components
    private int size; //The size of the game grid
    private HashMap colours; //Sets the colours of the occupied tiles
    private WindowBuilder window;
    private ScoreKeeper scDisplay;

    //Components required to run the game
    private boolean running;
    private final int delay = 100;
    private Timer timer; //Allows game to run with delay between steps
    private Direction direction;
    private Game game;

    //Occupied tiles on the grid.
    private ArrayList<SnakeTile> snake;
    private ArrayList<FruitTile> fruit;

    /**
     * Sets up the initial game state and creates all GUI components required.
     */
    public GameWindow()
    {   
        init(false, false);
    }

    /**
     * Initialises the window with all GUI and game components.
     * @param recreate If true, initialise new top-level window. If not, only initialise game application and 
     * Component objects.
     * @param resize If true, the game grid reinitialises with the size specified by user input.
     */
    private void init(boolean recreate, boolean resize){
        //Sets up GUI with the specified dimension
        if(!recreate){
            window = new WindowBuilder(25, this);
            size = window.getOptions().getGridSize();
        }
        else {
            size = window.getOptions().getGridSize();
            window.resize(size);
        }
        setColours();
        
        //Sets up the initial state of the game logic
        running = false;
        direction = Direction.WEST; //Defaults to West because Snake initialises facing West.
        game = new Game(size);

        //Displays score
        int score = game.getScore();
        scDisplay = window.getScoreDisplay();
        scDisplay.setText("Score: " + score + 
            " HighScore: " + scDisplay.getHighScore());

        //Painting occupied tiles
        snake = game.getSnake().getSnakeTiles();
        fruit = game.getFruit();
        window.getDisplay().paintComponents(colours, snake, fruit); 

        //Sets up keyboard listener
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyboardListener());
    }

    //Displaying components of the game logic-------------------------------------------------------------
    /**
     * Assigns colours to each tile type.
     */
    private void setColours() 
    {
        colours = new HashMap();

        int scheme = window.getOptions().getColourScheme();
        assert scheme == 1 || scheme == 2 || scheme == 3 : "Scheme not valid!";

        switch (scheme) {
            case 1 : colours.put("FruitTile", Color.YELLOW);
            colours.put("SnakeTile", Color.RED);
            break;
            case 2 : colours.put("FruitTile", Color.BLUE);
            colours.put("SnakeTile", Color.BLACK);
            break;
            case 3 : colours.put("FruitTile", Color.MAGENTA);
            colours.put("SnakeTile", Color.CYAN);
            break;
        }
    }

    //Gameplay and interactions
    /**
     * Mutator method for the direction variable, ensures snake cannot go back on itself.
     * @param direction The new direction
     */
    private void changeDirection(Direction direction)
    {
        switch (this.direction) {
            case NORTH : if (direction == Direction.SOUTH) {}
            else { this.direction = direction; }
            break;

            case SOUTH : if (direction == Direction.NORTH) {}
            else { this.direction = direction; }
            break;

            case EAST : if (direction == Direction.WEST) {}
            else { this.direction = direction; }
            break;

            case WEST : if (direction == Direction.EAST) {}
            else { this.direction = direction; }
            break;
        }
    }

    /**
     * Runs the game one step.
     * Moves and repaints any occupied tiles, as well as checking whether game is still running.
     * If player lost, display a dialogue box.
     */
    private void runOneStep()
    {
        if (running) {
            //Repainting the tailTile of the snake
            SnakeTile tailTile = snake.get(0);
            int row = tailTile.getLocation().getRow();
            int col = tailTile.getLocation().getCol();
            window.getDisplay().paintComponent(row, col, size, Color.WHITE);

            game.runOneStep(direction);

            //Gets new game information after the step
            snake = game.getSnake().getSnakeTiles();
            fruit = game.getFruit();
            int score = game.getScore();

            //Update GUI accordingly
            window.getDisplay().paintComponents(colours, snake, fruit);

            int highScore = scDisplay.getHighScore();
            if (score > highScore) {
                scDisplay.setHighScore(score);
            }
            window.getScoreDisplay().setText("Score: " + score + 
                " HighScore: " + scDisplay.getHighScore());

            //Checking whether the game has paused or ended after this step
            running = game.getRunning();
            if (game.isOver()) {
                new JOptionPane().showMessageDialog(window.getJFrame(), 
                    "Game Over! \nScore: " + game.getScore());
                reset();
            }
        }
        else {
            stop();
        }
    }

    /**
     * Runs the game with a delay. Sets the delay of each step in the game to 200ms.
     */
    private void start()
    {
        running = true;
        game.setRunning(true);

        //Disable buttons and spinners to prevent errorenous input
        window.startButtons();
        window.getOptions().getColourSpinner().setEnabled(false);
        window.getOptions().getSizeSpinner().setEnabled(false);

        ActionListener runGame = new ActionListener() {
                public void actionPerformed(ActionEvent e) { 
                    runOneStep();
                }
            };
            
        timer = new Timer(delay, runGame);
        timer.start();
    }

    /**
     * Stops the game and all UI components
     */
    private void stop()
    {
        timer.stop();
        timer = null;

        game.setRunning(false);
        window.stopButtons();
        window.getOptions().getColourSpinner().setEnabled(true);
        window.getOptions().getSizeSpinner().setEnabled(true);
    }

    /**
     * Resets the game to its initial state, doesn't recreate the top-level window.
     */
    private void reset() 
    {
        init(true, true);
    }

    //Listening to hand handling events from GUI interaction
    /**
     * Handles ActionEvents occuring when a game-controlling button or menu item is interacted with
     * @param e The event to handle
     */
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Start Game" : start();
            break;
            case "Stop Game"  : stop();
            break;
            case "Reset Game" : reset();
            break;
        }
    }

    /**
     * Nested class, implemented because the KeyListener objects didn't function when game starts/stop/resets.
     */
    private class KeyboardListener implements KeyEventDispatcher {
        @Override
        public boolean dispatchKeyEvent(KeyEvent event) {
            if (event.getID() == KeyEvent.KEY_PRESSED && running) {
                int x = event.getKeyCode();

                switch(x){
                    case 38:
                    changeDirection(Direction.NORTH);
                    break;
                    case 40:
                    changeDirection(Direction.SOUTH);
                    break;      
                    case 37:
                    changeDirection(Direction.WEST);
                    break;  
                    case 39:
                    changeDirection(Direction.EAST);
                    break;  
                }
            } 

            return false;
        }
    }
}
