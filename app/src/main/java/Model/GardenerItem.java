package Model;

import com.example.gridsim.R;

public class GardenerItem extends GridCell {
    private int GID;

    private final int gardener = R.mipmap.gardender_icon;
    private final int shovel = R.mipmap.shovel_icon;
    private final int golfcart = R.mipmap.golfcart_icon;

    public GardenerItem(int val, int position) {
        this.position = position;
        if (val >= 1000000 && val < 2000000) {
            // gardener 1GIDXXX
            this.resourceID = gardener;
            this.type = "gardener";
            this.GID = (val/1000) - 1000;
        } else if (val >= 2000000 && val < 3000000) {
            // shovel 2GIDXXX
            this.resourceID = shovel;
            this.type = "shovel";
            this.GID = (val/1000) - 2000;
        } else if (val >= 10000000 && val < 20000000) {
            // cart 1GIDXXXX
            this.resourceID = golfcart;
            this.type = "golfcart";
            this.GID = (val/10000) - 1000;
        } else {
            // bad
            this.resourceID = -1;
            this.type = "bad";
            this.GID = -1;
        }
    }

    @Override
    public String getCellInfo() {
        // TODO: returns a string describing other info about the object (Such as location)
        String str = "Selected " + type + "\n";
        str = str + "Location: (" + position/16 + ", " + position%16 + ")\n";
        str = str + "Gardener ID: " + this.GID;
        return str;
    }
}
