package Model;

public class GridCell {
    protected String type;
    protected int resourceID;
    protected int position;

    public GridCell() {

    }
    public GridCell(String type, int resourceID, int position) {
        this.type = type;
        this.resourceID = resourceID;
        this.position = position;
    }
    public Integer getResourceID() {
        // TODO: returns the appropriate image resource identifer
        return resourceID;
    }

    public String getCellType() {
        // TODO: returns a string description of the objec type in the cell
        return type;
    }

    public String getCellInfo() {
        // TODO: returns a string describing other info about the object (Such as location)
        String str = "Selected " + type + "\n";
        str = str + "Location: (" + position/16 + ", " + position%16 + ")";
        return str;
    }
}
