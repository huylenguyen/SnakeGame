import java.util.Random;

/**
 * A representation of each of the locations in the grid.
 * This class takes concept from the Location class in the 'foxes and rabbit' project 
 * in chapter 10 of the BlueJ textbook.
 * 
 * @author Huy Nguyen, David J. Barnes, Michael Kolling and Olaf Chitil
 * @version (a version number or a date)
 */
public class Location
{
    //Location of the tile
    private int row;
    private int col;
    
    /**
     * Creates a Location with the given coordinates.
     * @param row The row of this location in the grid
     * @param col The column of this location in the grid
     */
    public Location(int row, int col)
    {
        assert row >= 0 : "Row is not positive";
        assert col >= 0 : "Column is not positive";
        
        this.row = row;
        this.col = col;
    }
    
    /**
     * Return the row value of the location
     * @return The row variable
     */
    public int getRow()
    {
        return row;
    }
    
    /**
     * Return the column value of the location
     * @return The col variable
     */
    public int getCol()
    {
        return col;
    }
    
    /**
     * Returns a random location in a grid
     * @param size The size of the grid
     * @return The Location object
     */
    public Location getRandomLocation(int size)
    {
        Random r = new Random();
        int row = r.nextInt(size);
        int col = r.nextInt(size);
        
        assert row >= 0 && row <= size : "Row out of range";
        assert col >= 0 && col <= size : "Column out of range";
        
        Location location = new Location(row, col);
        return location;
    }
}
