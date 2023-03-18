package game;


import javax.swing.JFrame;

import assignment2.ActionQueue;
import assignment2.GameState;
import assignment2.TargetQueue;
import assignment2.World;


public class CaterpillarGame extends Thread {
  
 private World world ; 
 private JFrame frame ;    
    private CaterpillarDrawer drawer ;  
    public static int width = 800, height = 600;
            
 public CaterpillarGame() {
  
  super() ;    
  
  // target positions
  String str_target_positions = "(9,9).(14,7).(10,7).(5,6).(1,9)." ;

  // directions to take    
  String str_encoded = "[E]2[2[E]1[N]]" +"2[E]1[N]" +"5[S]"+"6[W]" +"2[N]" + "6[E]" +"4[W]" +"1[N]5[W]" +"3[S]4[W]";
//     str_encoded = str_encoded + "[E]2[2[E]1[N]]" ;   // from (9,9) to (14,7)
//     str_encoded= str_encoded+"2[2[E]1[N]]";

       
     TargetQueue target_queue = new TargetQueue() ; 
     ActionQueue action_queue = new ActionQueue() ;     
     try {      
         target_queue.addTargets(str_target_positions) ;     
         action_queue.loadFromEncodedString(str_encoded) ;
     }
     catch( Exception e) {
      System.out.println(e.getMessage()) ;
     }
     
     // create the world
     world = new World( target_queue, action_queue );
     
        // make a graphical interface
        frame = new JFrame("Game");
  frame.setSize(width,height);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.setLocationRelativeTo(null);
  frame.setLayout(null);
  frame.setResizable(false);
    
  // draw
  drawer = new CaterpillarDrawer();
  drawer.setSize(width,height);
  drawer.setBounds(0,0, width, height);
  drawer.setCaterpillar(world.getCaterpillar());
  drawer.setFood(world.getFood());   
  drawer.setVisible(true);   
  
  frame.add(drawer);
  frame.requestFocus();
  frame.setVisible(true);
 }
 
    public void run(){
          
     while ( world.isRunning() ) {
      try {
                sleep(100);
                world.step() ;
                drawer.setFood(world.getFood());
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }    
        }
     
     if ( world.getState() == GameState.DONE ) {
         System.out.println("Done!") ;
        }
     else {
      System.out.println("Failed!") ;
     }
    }
    
    public static void main (String[] args) {
  CaterpillarGame game = new CaterpillarGame() ;
        game.start() ;               
 }
}