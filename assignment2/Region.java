package assignment2;

public class Region {

    private int minX;
    private int minY;
    private int maxX;
    private int maxY;

    public Region(int sX, int sY, int bX, int bY){
        this.minX= sX;
        this.minY=sY;
        this.maxX=bX;
        this.maxY=bY;
    }

    public boolean contains(Position place){
        return !(place.getX() > this.maxX) && !(place.getX() < this.minX)
                && !(place.getY() > this.maxY) && !(place.getY() < this.minY);
    }

    public int getMaxX() {
        return this.maxX;
    }

    public int getMaxY() {
        return this.maxY;
    }

    public int getMinY() {
        return this.minY;
    }

    public int getMinX() {
        return this.minX;
    }

    public static void main(String[]args){
        Region region = new Region(1,1,11,11);
        Position goodPos = new Position(1,5);

        System.out.print(region.contains(goodPos));
    }

}
