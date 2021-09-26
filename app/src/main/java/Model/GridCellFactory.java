package Model;

import com.example.gridsim.R;

public class GridCellFactory {
    public GridCellFactory() {

    }
    public GridCell makeCell(int val, int position) {
        if(val == 0) {
            // empty cell
            return new GridCell("empty", R.mipmap.blank, position);
        } else if (val == 1000 || (val > 1000 && val < 2000) || val == 2002 || val == 2003 || val == 3000) {
            // plants
            return new Plant(val, position);
        } else if (val > 4000 && val < 1000000) {
            // reserved
            return new GridCell("reserved", R.mipmap.blank, position);
        } else if ( (val >= 1000000 && val < 2000000) || (val >= 2000000 && val < 3000000) || (val >= 10000000 && val < 20000000)) {
            // gardener items
            return new GardenerItem(val, position);
        } else {
            // should never end up here..
            return null;
        }
    }

}
