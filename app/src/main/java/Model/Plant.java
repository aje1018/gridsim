package Model;

import com.example.gridsim.R;

public class Plant extends GridCell {

    private final int tree = R.mipmap.tree;
    private final int bush = R.mipmap.bushes;
    private final int clover = R.mipmap.clover;
    private final int mushroom = R.mipmap.mushroom;
    private final int sunflower = R.mipmap.sunflower;


    public Plant(int val, int position) {
        this.position = position;
        if (val == 1000) {
            // tree
            this.type = "tree";
            this.resourceID = tree;
        } else if (val > 1000 && val < 2000) {
            // bushes
            this.type = "bush";
            this.resourceID = bush;
        } else if (val == 2002) {
            // clover
            this.type = "clover";
            this.resourceID = clover;
        } else if (val == 2003) {
            // mushroom
            this.type = "mushroom";
            this.resourceID = mushroom;
        } else if (val == 3000) {
            // sunflower
            this.type = "sunflower";
            this.resourceID = sunflower;
        } else {
            this.type = "bad";
            this.resourceID = -1;
        }
    }
}
