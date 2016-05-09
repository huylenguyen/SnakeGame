/**
 * A square grid of all the locations in the game. 
 * This class takes concept from the Field class in the 'foxes and rabbit' project
 * in chapter 10 of the BlueJ textbook.
 * 
 * @author Huy Nguyen, David J. Barnes, Michael Kolling and Olaf Chitil
 * @version (a version number or a date)
 */
public class Grid
{
    //Memory storage for all squares
    private Tile[][] grid;
    //the size of the grid
    private int size;

    /**
     * Creates a Grid with the given size.
     * @param depth The depth of the grid, must be larger than 5
     * @param width The width of the grid, must be larger than 5
     */
    public Grid(int size)
    {
        assert size > 5 : "Size is too small!";
        
        this.size = size;
        grid = new Tile[size][size];
    }
    
    /**
     * Places a tile at a given location in the grid.
     * @param tile The tile to place
     * @param location The location for the tile
     */
    public void place(Tile tile, Location location)
    {
        assert location != null : "Location is null";
        assert tile != null: "Tile is null";

        grid[location.getRow()][location.getCol()] = tile;
    }
    
    /**
     * Clears the tile at the given location, works even if the tile is null
     * @param location The location to clear
     */
    public void clearTileAt(Location location)
    {
        assert location != null : "Location is null";
        
        grid[location.getRow()][location.getCol()] = null;
    }
    
    //Accessor methods for snake movement
    /**
     * Returns the location to the north of the given location.
     * @param location The location needed
     * @return The location to return
     */
    public Location getNorthLocation(Location location)
    {
        assert location != null : "Location is null";
        
        int row = location.getRow();
        int col = location.getCol();
        Location loc = new Location (row-1, col);
        return loc;
    }
    
    /**
     * Returns the location to the south of the given location.
     * @param location The location needed
     * @return The location to return
     */
    public Location getSouthLocation(Location location)
    {
        assert location != null : "Location is null";
        
        int row = location.getRow();
        int col = location.getCol();
        Location loc = new Location (row+1, col);
        return loc;
    }
    
    /**
     * Returns the location to the east of the given location.
     * @param location The location needed
     * @return The location to return
     */
    public Location getEastLocation(Location location)
    {
        assert location != null : "Location is null";
        
        int row = location.getRow();
        int col = location.getCol();
        Location loc = new Location (row, col+1);
        return loc;
    }
    
    /**
     * Returns the location to the west of the given location.
     * @param location The location needed
     * @return The location to return
     */
    public Location getWestLocation(Location location)
    {
        assert location != null : "Location is null";
        
        int row = location.getRow();
        int col = location.getCol();
        Location loc = new Location (row, col-1);
        return loc;
    }
    
    //Accessor methods.
    /**
     * Returns the tile at the given location
     * @param The location of the tile to return
     * @return Returns the tile, or null if there is none
     */
    public Tile getTileAt(Location location)
    {
        assert location != null : "Location is null";
        
        return grid[location.getRow()][location.getCol()];
    }
     
    /**
     * Returns the grid
     */
    public Tile[][] getGrid()
    {
        assert grid != null : "Grid is null";
        return grid;
    }

    /**
     * Returns the width of the grid.
     */
    public int getSize()
    {
        return size;
    }
}
