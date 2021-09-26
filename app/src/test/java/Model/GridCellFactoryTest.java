package Model;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;

public class GridCellFactoryTest {

    @Test
    public void makeCell_sentZeroValue_returnsEmptyGC() {
        GridCellFactory gcf = new GridCellFactory();

        assertEquals(gcf.makeCell(0, 0).getCellType(), "empty");
    }
}
