package Model;

import org.json.JSONArray;

public class SimulationGrid {
    private boolean isPaused;

    private GridCell[][] gc = new GridCell[16][16];
    public SimulationGrid() {
        isPaused = false;
    }
    public GridCell getCell(int index) {
        // TODO: returns the GridCell at a given linear index
        return gc[index / 16][index % 16];
    }

    public int getNumRows() {
        // TODO: returns the number of rows in the grid
        return 16;
    }

    public int getNumCols() {
        // TODO: returns the number of columns in the grid
        return 16;
    }

    public void setCell(int index, GridCell cell) {
        // TODO: sets the GridCell at a given linear index
        gc[index/16][index%16] = cell;
    }

    public int size() {
        // TODO: returns the linear size of the grid
        return 256;
    }

    public void setUsingJSON(JSONArray arr) {
        // TODO: fills grid cells with appropriate information from a JSONArray (coming from the server)
        GridCellFactory gcf = GridCellFactory.getInstance();
        try {
            for (int i = 0; i < 16; i++) {
                // Grab 1d JSON Array via index (0-15)
                JSONArray oneD = arr.getJSONArray(i);
                for(int j = 0; j < 16; j++) {
                    gc[i][j] = gcf.makeCell(oneD.getInt(j), (16 * i) + j);
                }
            }
        } catch (Exception e) {
            System.out.println("Oops");
        }

    }

    public boolean getPauseValue() {
        return isPaused;
    }

    public void togglePause() {
        // pauseGC = gc; // pass by reference... :(
        isPaused = !isPaused;
    }
}
