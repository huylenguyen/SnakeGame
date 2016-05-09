import java.awt.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class handles building the panel for the game logic components.
 * 
 * @author Huy Nguyen
 * @version (a version number or a date)
 */
public class GridDisplay extends JPanel
{
    private JPanel tilePanel;
    private int size;

    /**
     * Creates a two-dimensional array of JPanels with the required size
     * @param size The size of the game grid
     */
    public GridDisplay(int size)
    {
        this.size = size;
        
        //Initialising the grid
        this.setLayout(new GridLayout(size, size, 2, 2));

        //Creating individual tiles for the grid
        for (int x = 0; x < size*size; x++) {
            tilePanel = new JPanel();
            //Painting tiles
            tilePanel.setBackground(Color.WHITE);
            tilePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

            this.add(tilePanel);
        }
    }

    /**
     * Retrieve the component at the given position in the grid and set its background colour.
     * @param row The row position of the component.
     * @param col The column position of the component.
     * @param color The color to set this component to.
     */
    public void paintComponent(int row, int col, int maxRow, Color color) {
        Component c = this.getComponent(row*maxRow + col);
        c.setBackground(color);
    }
    
    /**
     * Update a single tile with their respective colour.
     * @param tile The tile to repaint.
     * @param color The color of the tile
     */
    public void paintComponent(Tile tile, Color color)
    {
        int row = tile.getLocation().getRow();
        int col = tile.getLocation().getCol();

        paintComponent(row, col, size, color);
    }
    
    /**
     * Updates all the occupied tiles with their respective colour.
     * @param colours The list of tile types and their respective colour.
     * @param snake List of snake tiles
     * @param fruit List of fruit tile
     */
    public void paintComponents(HashMap colours, ArrayList<SnakeTile> snake, ArrayList<FruitTile> fruit)
    {
        //Paint snake
        for(SnakeTile tile : snake) {
            paintComponent(tile, (Color) colours.get("SnakeTile"));
        }

        //Paint fruit
        for(FruitTile tile : fruit) {
            paintComponent(tile, (Color) colours.get("FruitTile"));
        }
    }
}
