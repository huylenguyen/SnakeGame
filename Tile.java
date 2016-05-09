
/**
 * This interface should be implemented by all classes that creates interactive objects
 * on the grid.
 * 
 * @author Huy Nguyen 
 * @version (a version number or a date)
 */
public interface Tile
{
    /**
     * Returns whether or not this tile can be eaten by the snake.
     * If the tile isn't edible, it also cannot be passed through and will kill the snake.
     */
    boolean isEdible();
    
    /**
     * Returns the grid the tile is in
     */
    Grid getGrid();
    
    /**
     * Returns the location of the grid the tile is in
     */
    Location getLocation();
    
    /**
     * Places the tile at a location in the grid
     * @param location The location to set the tile in
     */
    void setLocation(Location location);
}
