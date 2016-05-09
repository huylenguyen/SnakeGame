import java.util.ArrayList;

/**
 * Represents the snake object in the game.
 * Made up of SnakeTiles.
 * 
 * @author Huy Nguyen
 * @version (a version number or a date)
 */
public class Snake
{
    private Grid grid;
    private Location newLocation;

    private ArrayList<SnakeTile> snake;
    private Direction direction;

    /**
     * Creates a snake object with 3 tiles
     * @param headTile The head tile of the snake
     * @param middleTile The body tile of the snake
     * @param tailTile The tail tile of the snake
     */
    public Snake(Grid grid, SnakeTile headTile, SnakeTile middleTile, SnakeTile tailTile)
    {
        this.grid = grid;
        newLocation = null;

        snake = new ArrayList<SnakeTile>();
        
        snake.add(tailTile);
        snake.add(middleTile);
        snake.add(headTile);
        
        sane();
    }
    
    /**
     * Ensures the snake is not empty, and exists on the grid
     */
    public void sane()
    {
        assert snake.isEmpty() != true : "Snake is empty";
        assert grid != null: "Grid is null";
    }

    /**
     * Check the location from the given direction to the snake for movement.
     * If the required location has a tile that is not edible, return false.
     * @return Returns false if location cannot be moved to, true otherwise.
     */
    public boolean canMove(Direction direction)
    {
        sane();
        Location headLocation = snake.get(snake.size()-1).getLocation();
        assert headLocation != null : "headLocation is null";

        newLocation = null;
        
        //Get the location to a given direction of the head of the snake
        switch(direction)
        {
            case NORTH: if(headLocation.getRow() != 0) {
                newLocation = grid.getNorthLocation(headLocation);
                direction = Direction.NORTH;
            }
            else {
                return false;
            }
            break;
            case SOUTH: if(headLocation.getRow() != grid.getSize()-1) {
                newLocation = grid.getSouthLocation(headLocation);
                direction = Direction.SOUTH;
            }
            else {
                return false;
            }
            break;
            case EAST:  if(headLocation.getCol() != grid.getSize()-1) {
                newLocation = grid.getEastLocation(headLocation);
                direction = Direction.EAST;
            }
            else {
                return false;
            }
            break;
            case WEST:  if(headLocation.getCol() != 0) {
                newLocation = grid.getWestLocation(headLocation);
                direction = Direction.WEST;
            }
            else {
                return false;
            }
            break;
        }

        //Determines whether the snake can move to specified tile
        if(newLocation != null) {
            Tile tile = grid.getTileAt(newLocation);

            if(tile == null){
                return true;
            }
            else if(tile instanceof FruitTile){
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    /**
     * Move the snake to the next location
     * @return If the snake eats a fruit during this step, return true. Otherwise false.
     */
    public boolean move()
    {
        sane();
        
        Tile tile = grid.getTileAt(newLocation);
        
        //Blank space
        if(tile == null) {
            //Make new head tile of snake
            SnakeTile newHeadTile = new SnakeTile(grid, newLocation);
            snake.add(newHeadTile);
            
            //Remove old tail tile of snake
            SnakeTile tailTile = snake.get(0);
            Location loc = tailTile.getLocation();
            grid.clearTileAt(loc);
            snake.remove(0);
            
            return false;
        }
        
        //Finds a fruit
        else if (tile instanceof FruitTile) {
            //Clear the fruit from grid
            grid.clearTileAt(tile.getLocation());
            
            //Replace fruit with snake tile
            SnakeTile newHeadTile = new SnakeTile(grid, newLocation);
            snake.add(newHeadTile);
            
            return true;
        }
        return false;
    }

    /**
     * Returns the snake as an ArrayList of SnakeTiles
     */
    public ArrayList<SnakeTile> getSnakeTiles()
    {
        sane();
        return snake;
    }
}
