package pathsim.algo;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import pathsim.Program;
import pathsim.gfx.Assets;
import pathsim.gui.panels.MainPanel;
import pathsim.map.Map;

public abstract class Algorithm {
    
    private Point currLocation;
    protected Point origin = null, destination = null;
    protected BufferedImage sprite;
    
    public static boolean initialized = false;
    
    protected Map map = Program.map;
    
    public enum State {
        pointA, pointB, tracing, moving, idle
    } public static State state = State.idle;
    
    public Algorithm() {
        setSprite();
    }
    
//    protected void setOrigin() {
//        if (state == State.pointA) {
//            origin.x = Assets.locX;
//            origin.y = Assets.locY;
//            this.currLocation = origin;
//
//            map.tileAt(origin).setAsOrigin();
//            javax.swing.JOptionPane.showMessageDialog(null, "(" + origin.x + ", " + origin.y + ") set as starting point.", "", javax.swing.JOptionPane.INFORMATION_MESSAGE);
//        }
//        
//        state = State.idle;
//    }
    
//    protected void setOrigin(Point origin) {
//        this.origin = origin;
//        map.tileAt(origin).setAsOrigin();
//        this.currLocation = origin;
//    }
    
    protected void setOrigin() {
        if(state == State.pointA)
           if(Assets.locX != -1 && Assets.locY != -1) {
              Point temp = origin;      
              
              origin = new Point(Assets.locX, Assets.locY);
              
              if(temp != null && map.tileAt(temp).passable())
                    map.tileAt(temp).removeOrigin();
              
              if(map.tileAt(origin).passable() && origin != destination) {
                 map.tileAt(origin).setAsOrigin();
                 currLocation = origin;
                 Assets.locX = -1; Assets.locY = -1;
                 state = State.idle;
              } else {
                  origin = null;
              }
           }
        
        if(origin != null && destination != null)
           MainPanel.bStart.setEnabled(true);
        else
           MainPanel.bStart.setEnabled(false);
    }
    
    protected void setGoal() {
        if(state == State.pointB)
           if(Assets.locX != -1 && Assets.locY != -1) {
              Point temp = destination;
              
              destination = new Point(Assets.locX, Assets.locY);
              
              if(temp != null && map.tileAt(temp).passable())
                 map.tileAt(temp).removeGoal();
              
              if(map.tileAt(destination).passable() && destination != origin) {
                 map.tileAt(destination).setAsGoal();
                 Assets.locX = -1; Assets.locY = -1;
                 state = State.idle;
              } else {
                  destination = null;
              }
           }
        
        if(origin != null && destination != null)
           MainPanel.bStart.setEnabled(true);
        else
           MainPanel.bStart.setEnabled(false);
    }
    
    protected void reset() {
        if(initialized)
           initialized = false;
    }
    
//    protected void setGoal(Point destination) {
//        this.destination = destination;
//        map.tileAt(destination).setAsGoal();
//    }
    
    private void setSprite() {
//        sprite = Assets.player;
    }
    
//    private void test() {
//        if(origin == null && destination == null)
//           System.out.println("Origin: null | Destination: null");
//        else if(origin != null && destination == null)
//           System.out.println("Origin: " + origin.x + ", " + origin.y + " | Destination: null");
//        else if(origin == null && destination != null)
//           System.out.println("Origin: null | Destination: Destination: " + destination.x + ", " + destination.y);
//        else
//           System.out.println("Origin: " + origin.x + ", " + origin.y + " | Destination: " + destination.x + ", " + destination.y);
//    }
    
    public void tick() {
        switch(state) {
            case idle: break;
            case pointA: setOrigin(); break;
            case pointB: setGoal(); break;
            case tracing: pathfind(); break;
            case moving:
        }
    }
    
    public void render(Graphics g) {
//        if(currLocation != null)
//           g.drawImage(sprite, currLocation.y * 32, currLocation.x * 32, null);
    }
    
    public abstract void pathfind();

}
