package assignment2;

public class Position {
    private int x;

    private int y;

    public Position(int x, int y){
        this.x=x;
        this.y=y;
    }
    public Position(Position pos){
        this.x=pos.getX();
        this.y=pos.getY();
    }


    public void reset(int x, int y){
        this.x=x;
        this.y=y;
    }

    public void reset(Position pos){
        this.x=pos.getX();
        this.y=pos.getY();
    }
    public static int getDistance(Position pos1, Position pos2){
        return Math.abs(pos1.x-pos2.x) + Math.abs(pos1.y-pos2.y);
    }
    public int getX() {return this.x;}
    public int getY(){return this.y;}
    public void moveWest(){this.x--;}
    public void moveEast(){this.x++;}
    public void moveNorth(){this.y--;}
    public void moveSouth(){this.y++;}

    public boolean equals(Object obj){
        if(!(obj instanceof Position)){return false;}
        return ((Position) obj).x == this.x && ((Position) obj).y == this.y;
    }
}
