import java.util.Random;
import java.util.ArrayList;

/**
 * This class builds and stores all necessary components of the game.
 * 
 * @author Huy Nguyen 
 * @version (a version number or a date)
 */
public class Game
{
    //This variable lets the game know when to stop
    private boolean running;
    private boolean gameOver;
    private int size;
    private int score;

    private Grid grid;
    private Snake snake;
    private ArrayList<FruitTile> fruits;

    //Setting up and running the game
    /**
     * Sets up a game with a given size
     * Doesn't start the game.
     * @param size The size of the grid
     */
    public Game(int size)
    {
        this.size = size;
        init(size);
        score = 0;
    }

    /**
     * Resets the game to its initial state.
     * @param row The size of the grid
     */
    public void init(int size) 
    {
        running = false;
        gameOver = false;

        //Creates the grid with dimension parameters
        grid = new Grid(size);

        //Sets up the snake in the middle of the grid
        int x = size/2;
        SnakeTile headTile = new SnakeTile(grid, x, x);
        SnakeTile middleTile = new SnakeTile(grid, x, x+1);
        SnakeTile tailTile = new SnakeTile(grid, x, x+2);
        snake = new Snake(grid, headTile, middleTile, tailTile);

        //Create a fruit at a random location on the grid
        fruits = new ArrayList<FruitTile>();
        fruits = placeRandomFruit();
    }

    /**
     * Runs the game one step in a given direction.
     * If the snake ate a fruit in this step, places a new random fruit on the grid
     * If the snake ran into itself or the edge of the grid, stops game and sets gameOver to true.
     * @param direction The direction desired
     */
    public void runOneStep(Direction direction)
    {
        assert direction != null : "Game gets null direction";
        if (snake.canMove(direction) && running){
            if (snake.move() == true) {
                fruits.removeAll(fruits);
                score += 10;
                placeRandomFruit();
            }
        }
        else {
            running = false;
            gameOver = true;
        }
    }

    //Creating a fruit
    /**
     * Places a FruitTile at a random location on the grid.
     * Location must not already be occupied
     * @return The collection of FruitTile objects
     */
    public ArrayList<FruitTile> placeRandomFruit()
    {
        if (!fruits.isEmpty()) {
            fruits.remove(0);
        }
        
        FruitTile fruit = createRandomFruit();

        boolean found = false;
        while (!found) {
            if (isFruitValid(fruit) == false) {
                fruit = createRandomFruit();
            }
            else {
                found = true;
            }
        }

        fruits.add(fruit);
        return fruits;
    }

    /**
     * Creates a FruitTile at a random location on the grid.
     */
    public FruitTile createRandomFruit()
    {
        Random r = new Random();
        int row = r.nextInt(size);
        int col = r.nextInt(size);

        assert row >= 0 && row <= size : "Row out of range";
        assert col >= 0 && col <= size : "Column out of range";

        return new FruitTile(grid, row, col);
    }

    /**
     * Checks whether a fruit is at a valid location.
     * Cannot overlap another tile.
     * @return Whether the location is valid
     */
    public boolean isFruitValid(FruitTile fruit)
    {
        boolean valid = true;

        for (SnakeTile snakeTile : snake.getSnakeTiles()) {
            Location fLocation = fruit.getLocation();
            Location sLocation = snakeTile.getLocation();
            if (fLocation.getRow() == sLocation.getRow() 
            && fLocation.getCol() == sLocation.getCol()) {
                valid = false;
            }
        }

        return valid;
    }

    //Other accessor & mutator methods ------------------------------------------------------------
    /**
     * Returns the snake in the grid
     */
    public Snake getSnake()
    {
        return snake;
    }
    
    /**
     * Returns the score of the game
     */
    public int getScore()
    {
        return score;
    }

    /**
     * Returns the fruit in the grid
     */
    public ArrayList<FruitTile> getFruit()
    {
        return fruits;
    }

    /**
     * Set the value of the running variable
     * @param b The value to set the variable to
     */
    public void setRunning(boolean b)
    {
        running = b;
    }

    /**
     * Returns the whether or not the game is still running
     */
    public boolean getRunning()
    {
        return running;
    }

    /**
     * Returns whether or not the player has lost.
     */
    public boolean isOver()
    {
        return gameOver;
    }
}
