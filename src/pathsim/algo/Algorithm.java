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
    
    public void tick() {
        switch(state) {
            case idle: break;
            case pointA: setOrigin(); break;
            case pointB: setGoal(); break;
            case tracing: pathfind(); break;
            case moving:
        }
    }
    
    public abstract void pathfind();

}
