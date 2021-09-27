import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;

import Model.GridCell;
import Model.GridCellFactory;

public class GridCellFactoryTest {
    private GridCellFactory gcf;

    @Before
    public void makeGCF() {
        gcf = new GridCellFactory();
    }
    @Test
    public void makeCell_sentZeroValue_returnsEmptyGC() {
        GridCell gc = gcf.makeCell(0, 0);
        assertEquals(gc.getCellType(), "empty");
        String emptyGridCell = "Selected empty\nLocation: (0, 0)";
        assertEquals(gc.getCellInfo(), emptyGridCell);
    }

    @Test
    public void makeCell_sentTreeValue_returnsTreeGC() {
        GridCell gc = gcf.makeCell(1000, 0);
        assertEquals(gc.getCellType(), "tree");
        String treeGridCell = "Selected tree\nLocation: (0, 0)";
        assertEquals(gc.getCellInfo(), treeGridCell);
    }

    @Test
    public void makeCell_sentBushValue_returnsBushGC() {
        GridCell gc = gcf.makeCell(1001, 0);
        assertEquals(gc.getCellType(), "bush");
        String bushGridCell = "Selected bush\nLocation: (0, 0)";
        assertEquals(gc.getCellInfo(), bushGridCell);
    }

    @Test
    public void makeCell_sentCloverValue_returnsCloverGC() {
        GridCell gc = gcf.makeCell(2002, 0);
        assertEquals(gc.getCellType(), "clover");
        String cloverGridCell = "Selected clover\nLocation: (0, 0)";
        assertEquals(gc.getCellInfo(), cloverGridCell);
    }

    @Test
    public void makeCell_sentMushroomValue_returnsMushroomGC() {
        GridCell gc = gcf.makeCell(2003, 0);
        assertEquals(gc.getCellType(), "mushroom");
        String mushroomGridCell = "Selected mushroom\nLocation: (0, 0)";
        assertEquals(gc.getCellInfo(), mushroomGridCell);
    }

    @Test
    public void makeCell_sentSunflowerValue_returnsPlantGC() {
        GridCell gc = gcf.makeCell(3000, 0);
        assertEquals(gc.getCellType(), "sunflower");
        String sunflowerGridCell = "Selected sunflower\nLocation: (0, 0)";
        assertEquals(gc.getCellInfo(), sunflowerGridCell);
    }

    @Test
    public void makeCell_sentReservedValue_returnsReservedGC() {
        GridCell gc = gcf.makeCell(4001, 0);
        assertEquals(gc.getCellType(), "reserved");
        String reservedGridCell = "Selected reserved\nLocation: (0, 0)";
        assertEquals(gc.getCellInfo(), reservedGridCell);
    }

    @Test
    public void makeCell_sentGardenerValue_returnsGardenerGC() {
        GridCell gc = gcf.makeCell(1111000, 0);
        assertEquals(gc.getCellType(), "gardener");
        String gardenerGridCell = "Selected gardener\nLocation: (0, 0)\nGardener ID: 111";
        assertEquals(gc.getCellInfo(), gardenerGridCell);
    }

    @Test
    public void makeCell_sentShovelValue_returnsShovelGC() {
        GridCell gc = gcf.makeCell(2111000, 0);
        assertEquals(gc.getCellType(), "shovel");
        String shovelGridCell = "Selected shovel\nLocation: (0, 0)\nGardener ID: 111";
        assertEquals(gc.getCellInfo(), shovelGridCell);
    }

    @Test
    public void makeCell_sentGolfcartValue_returnsGolfcartGC() {
        GridCell gc = gcf.makeCell(11110000, 0);
        assertEquals(gc.getCellType(), "golfcart");
        String golfcartGridCell = "Selected golfcart\nLocation: (0, 0)\nGardener ID: 111";
        assertEquals(gc.getCellInfo(), golfcartGridCell);
    }
}

