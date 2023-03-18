package assignment2;


public class World {

    private Caterpillar caterpillar;

    private Position food;

    private Region reg;

    private ActionQueue directions;

    private TargetQueue foodPos;

    private GameState state;

    public World(TargetQueue food, ActionQueue acts) {

        this.foodPos = food;

        this.directions = acts;

        this.state = GameState.MOVE;

        this.reg = new Region(0, 0, 15, 15);

        this.caterpillar = new Caterpillar();

        this.food= foodPos.dequeue();

    }

    public boolean isRunning() {
        return this.state == GameState.MOVE || this.state == GameState.EAT;
    }

    public void step() {

        Direction next = null;

        Position cur = getCaterpillar().getHead();

        if (!this.directions.isEmpty()) {
            next = this.directions.dequeue();
        }else{
            this.state = GameState.NO_MORE_ACTION;
        }

        if(this.state==GameState.MOVE) {

            if (next == (Direction.NORTH)) {
                cur.moveNorth();
            }

            if (next == Direction.SOUTH) {
                cur.moveSouth();
            }

            if (next == Direction.EAST) {
                cur.moveEast();
            }

            if (next == Direction.WEST) {
                cur.moveWest();
            }
        }

        if (this.atFood()) {
            this.caterpillar.eat(cur);
            this.state=GameState.EAT;

            if (this.foodPos.isEmpty()) {
                this.state = GameState.DONE;
            } else {
                this.food=this.foodPos.dequeue();
                this.state=GameState.MOVE;


            }
        }

        else if(!reg.contains(cur) || cur.getX()==reg.getMaxX() || cur.getX()==reg.getMinX()
        || cur.getY()==reg.getMaxY() || cur.getY()==reg.getMinY())
        {this.state = GameState.WALL_COLLISION;

        }

        else if(this.getCaterpillar().selfCollision(cur)) {this.state = GameState.SELF_COLLISION;}

        if (this.state != GameState.MOVE && this.state != GameState.EAT) {return;}

        this.caterpillar.move(cur);

    }


        public Caterpillar getCaterpillar () {
            return this.caterpillar;
        }

        public GameState getState () {
            return this.state;
        }
        public Position getFood () {
            return this.food;
        }

        private boolean atFood(){
            return this.getCaterpillar().getHead().equals(this.getFood());
        }



    public static void main(String[]args){

        TargetQueue targetQueue = new TargetQueue();
        ActionQueue actionQueue = new ActionQueue();

        String food = "(9,9).(14,7).(10,7).(5,6).(1,9)." ;
        String direction = "[E]2[2[E]1[N]]" +"2[E]1[N]" +"5[S]"+"6[W]" +"2[N]" + "6[E]" +"4[W]" +"1[N]5[W]" +"3[S]4[W]";

        actionQueue.loadFromEncodedString(direction);
        targetQueue.addTargets(food);
        World world = new World(targetQueue, actionQueue);

        world.step();


        System.out.println(world.getCaterpillar().getSize());

    }


//    package assignment2;
//
//
//public class World {
//
//    private Caterpillar caterpillar;
//
//    private Position food;
//
//    private Region reg;
//
//    private ActionQueue directions;
//
//    private TargetQueue foodPos;
//
//    private GameState state;
//
//    public World(TargetQueue food, ActionQueue acts) {
//
//        this.foodPos = food;
//
//        this.directions = acts;
//
//        this.state = GameState.MOVE;
//
//        this.reg = new Region(0, 0, 15, 15);
//
//        this.caterpillar = new Caterpillar();
//
//        this.food= foodPos.dequeue();
//
//    }
//
//    public boolean isRunning() {
//        return this.state == GameState.MOVE || this.state == GameState.EAT;
//    }
//
//    public void step() {
//
//        Direction next = null;
//
//        Position cur = getCaterpillar().getHead();
//
//        if (!this.directions.isEmpty()) {
//            next = this.directions.dequeue();
//
//        } else {
//            this.state = GameState.NO_MORE_ACTION;
//        }
//
//        if(this.state==GameState.MOVE) {
//
//            if (next == (Direction.NORTH)) {
//                cur.moveNorth();
//            }
//
//            if (next == Direction.SOUTH) {
//                cur.moveSouth();
//            }
//
//            if (next == Direction.EAST) {
//                cur.moveEast();
//            }
//
//            if (next == Direction.WEST) {
//                cur.moveWest();
//            }
//        }
//
//        if (this.atFood()) {
//            this.caterpillar.eat(cur);
//            this.state=GameState.EAT;
//
//            if (this.foodPos.isEmpty()) {
//                this.state = GameState.DONE;
//            } else {
//                this.food=this.foodPos.dequeue();
//            }
//        }
//        else if(!reg.contains(cur)) {this.state = GameState.WALL_COLLISION;}
//
//        else if(this.getCaterpillar().selfCollision(cur)) {this.state = GameState.SELF_COLLISION;}
//
//        if (this.state != GameState.MOVE && this.state != GameState.EAT) {return;}
//
//        this.caterpillar.move(cur);
//        }
//
//
//
//        public Caterpillar getCaterpillar () {
//            return this.caterpillar;
//        }
//
//        public GameState getState () {
//            return this.state;
//        }
//        public Position getFood () {
//            return this.food;
//        }
//
//        private boolean atFood(){
//            return this.getCaterpillar().getHead().equals(this.getFood());
//        }
//
//
//
//    public static void main(String[]args){
//
//        TargetQueue targetQueue = new TargetQueue();
//        ActionQueue actionQueue = new ActionQueue();
//
//        String food = "(5,9)";
//        String direction = "10[E]";
//
//        actionQueue.loadFromEncodedString(direction);
//        targetQueue.addTargets(food);
//        World world = new World(targetQueue, actionQueue);
//
//        //move 9 steps E, wall collision
//        for (int i = 0; i < 9; i++) {  // move 9 steps E from (7,7) to (15,7)
//            world.step();
//        }
//
//        System.out.println(world.getState());
//
//        System.out.println(world.getCaterpillar().getHead().getX()+" "+world.getCaterpillar().getHead().getY());
//
//    }
//
//}

}
