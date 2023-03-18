package assignment2;


public class Caterpillar extends MyDoublyLinkedList<Position>{

    public Caterpillar(){
        addFirst(new Position(7,7));
    }
    public Position getHead(){
       return this.peekFirst();
    }

    public boolean isOrtho(Position pos){
        return pos.getY()==getHead().getY() || pos.getX()==getHead().getX();}

    public void eat(Position pos){
        if(!isOrtho(pos))
        {throw new IllegalArgumentException();}
        else{this.addFirst(pos);}
    }
    public void move(Position pos){
        if(!isOrtho(pos))
        {throw new IllegalArgumentException();}
        else{this.addFirst(pos);
        this.removeLast();}
    }
    public boolean selfCollision(Position pos){
        for(Position p: this){
            if(!p.equals(this.getHead()) && p.equals(pos))
            {return true;}
        }
        return false;
    }

    public static void main(String[]args){
        Caterpillar caterpillar = new Caterpillar();
        Position t= new Position(6,7);
        caterpillar.eat(t);
        System.out.print(caterpillar.getSize());
        System.out.print(caterpillar.getHead().getX());
        System.out.print(caterpillar.selfCollision(new Position(7, 7)));
    }
}