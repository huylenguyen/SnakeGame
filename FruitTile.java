
/**
 * Write a description of class Fruit here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FruitTile implements Tile
{
    //The location of the fruit
    private Location location;
    private Grid grid;
    
    //Static fields
    private static final boolean isEdible = false;
    
    /**
     * Creates a FruitTile object in the given location in the grid.
     * @param grid The grid to assign the tile to.
     * @param location The location of the tile on the grid.
     */
    public FruitTile(Grid grid, Location location)
    {
        this.grid = grid;
        setLocation(location);
        sane();
    }
    
    /**
     * Creates a FruitTile object in the given coordinates in the grid
     * @param grid The grid to assign the tile to.
     * @param row The row the tile belongs to.
     * @param col The col the tile belongs to.
     */
    public FruitTile(Grid grid, int row, int col)
    {    
        this.grid = grid;
        location = new Location(row, col);
        setLocation(location);
        sane();
    }
    
    /**
     * Ensures the FruitTile object exists on the grid
     */
    private void sane()
    {
        assert grid != null : "Fruit not on grid";
        assert location != null: "Fruit not at a location";
    }
    
    /**
     * Places the tile at a location in the grid
     * @param location The location to set the tile in
     */
    public void setLocation(Location location)
    {
        grid.place(this, location);
    }
    
    /**
     * Returns whether or not this fruit can be passed through by the snake
     */
    public boolean isEdible()
    {
        return isEdible;
    }
    
    /**
     * Returns the grid the fruit is in
     */
    public Grid getGrid()
    {
        return grid;
    }
    
    /**
     * Returns the location of the grid the fruit is in
     */
    public Location getLocation()
    {
        return location;
    }
}
