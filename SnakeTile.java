/**
 * Represents a single tile that consitutes the Snake object.
 * Snake tiles cannot be passed through by the snake
 * 
 * @author Huy Nguyen
 * @version (a version number or a date)
 */
public class SnakeTile implements Tile
{
    //The location of the tile
    private Location location;
    private Grid grid;
    
    //Static fields
    private static final boolean isEdible = true;
    
    /**
     * Creates a SnakeTile object in the given location in the grid.
     * @param grid The grid to assign the tile to.
     * @param location The location of the tile on the grid.
     */
    public SnakeTile(Grid grid, Location location)
    {    
        this.grid = grid;
        setLocation(location);
        sane();
    }
    
    /**
     * Creates a SnakeTile object in the given coordinates in the grid
     * @param grid The grid to assign the tile to.
     * @param row The row the tile belongs to.
     * @param col The col the tile belongs to.
     */
    public SnakeTile(Grid grid, int row, int col)
    {    
        this.grid = grid;
        location = new Location(row, col);
        setLocation(location);
        sane();
    }
    
    /**
     * Ensures the SnakeTile object exists on the grid
     */
    private void sane()
    {
        assert grid != null : "SnakeTile not on grid";
        assert location != null: "SnakeTile not at a location";
    }
    
    /**
     * Places the tile at a location in the grid
     * @param location The location to set the tile in
     */
    public void setLocation(Location location)
    {
        this.location = location;
        grid.place(this, location);
    }
    
    /**
     * Returns whether or not this tile can be passed through by the snake
     */
    public boolean isEdible()
    {
        return isEdible;
    }
    
    /**
     * Returns the grid the tile is in
     */
    public Grid getGrid()
    {
        return grid;
    }
    
    /**
     * Returns the location of the grid the tile is in
     */
    public Location getLocation()
    {
        return location;
    }
}
