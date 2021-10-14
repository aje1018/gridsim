package Model;

import com.example.gridsim.R;

import java.util.HashMap;

public class GridCellFactory {
    private HashMap<Integer, GardenerItem> gardeners = new HashMap<>();
    private static GridCellFactory gcf = new GridCellFactory();
    private GridCellFactory() {

    }
    public static GridCellFactory getInstance() {
        return gcf;
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
            // because this hashmap needs to be stored in GCF, now need
            // to check the exact value of val..
            GardenerItem gi;
            int key;
            if (val < 2000000) {
                key = val / 1000 - 1000;
                key = key * 10 + 1; // unique ID for gardeners
            } else if (val < 3000000) {
                key = val / 1000 - 2000;
                key = key * 10 + 2; // unique ID for shovels
            } else {
                key = val / 10000 - 1000;
                key = key * 10 + 3; // unique ID for golfcarts
            }
            if((gi = gardeners.get(key)) == null) {
                gi = new GardenerItem(val, position);
                gardeners.put(key, gi);
            } else {
                gi.updateLocation(position);
            }
            return gi;
        } else {
            // should never end up here..
            return null;
        }
    }

}
