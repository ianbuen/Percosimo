package pathsim.algo;

import java.awt.Point;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pathsim.gui.panels.MainPanel;
import pathsim.map.Tile;

public class BFS extends Algorithm {
    
    public BFS() {
        super();
    }
    
    // BFS
    private Point p;
    private Tile current;
    private LinkedList tileList, visList;
    private LinkedList path;
    private HashMap<Tile, Tile> prevHash;
    
    public void pathfind() {
        if(!initialized) {
            map.unmarkAllTiles();
        
            Point p = origin;

            current = map.tileAt(p);
            tileList = new LinkedList();
            visList = new LinkedList();
            prevHash = new HashMap<Tile, Tile>();
            path = new LinkedList();

            current.mark();
            tileList.add(current);
            visList.add(current);
            
            initialized = true;
        }
        
        if(!tileList.isEmpty() && state == State.tracing) {
              current = (Tile) tileList.removeFirst();
              
              p = current.getLocation();
        
              if(current == map.tileAt(destination)) {
                 initialized = false;
                 
                 for(Tile tile = map.tileAt(destination); tile != null; tile = prevHash.get(tile))
                     tile.markTrail();
                 
                 MainPanel.bStart.setEnabled(true);
                 MainPanel.bPause.setEnabled(false);
                 MainPanel.bStop.setEnabled(false);
                 javax.swing.JOptionPane.showMessageDialog(null, "Path Found!", "Success", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                 state = state.idle;
                 return;
              }              
              
              if(map.tileAt(p.x-1, p.y) != null && !map.tileAt(p.x-1, p.y).marked() && map.tileAt(p.x-1, p.y).passable() && !visList.contains(map.tileAt(p.x-1, p.y))) {
                 tileList.add(map.tileAt(p.x-1, p.y));
                 visList.add(map.tileAt(p.x-1, p.y));
                 prevHash.put(map.tileAt(p.x-1, p.y), current);
                 map.tileAt(p.x-1, p.y).mark();
              }
              
              if(map.tileAt(p.x+1, p.y) != null && !map.tileAt(p.x+1, p.y).marked() && map.tileAt(p.x+1, p.y).passable() && !visList.contains(map.tileAt(p.x+1, p.y))) {
                 tileList.add(map.tileAt(p.x+1, p.y));
                 visList.add(map.tileAt(p.x+1, p.y));
                 prevHash.put(map.tileAt(p.x+1, p.y), current);
                 map.tileAt(p.x+1, p.y).mark();
              }
              
              if(map.tileAt(p.x, p.y-1) != null && !map.tileAt(p.x, p.y-1).marked() && map.tileAt(p.x, p.y-1).passable() && !visList.contains(map.tileAt(p.x, p.y-1))) {
                 tileList.add(map.tileAt(p.x, p.y-1));
                 visList.add(map.tileAt(p.x, p.y-1));
                 prevHash.put(map.tileAt(p.x, p.y-1), current);
                 map.tileAt(p.x, p.y-1).mark();
              }
              
              if(map.tileAt(p.x, p.y+1) != null && !map.tileAt(p.x, p.y+1).marked() && map.tileAt(p.x, p.y+1).passable() && !visList.contains(map.tileAt(p.x, p.y+1))) {
                 tileList.add(map.tileAt(p.x, p.y+1));
                 visList.add(map.tileAt(p.x, p.y+1));
                 prevHash.put(map.tileAt(p.x, p.y+1), current);
                 map.tileAt(p.x, p.y+1).mark();
              }
              
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(BFS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(tileList.isEmpty()) {
               initialized = false;
               MainPanel.bStart.setEnabled(true);
               MainPanel.bPause.setEnabled(false);
               MainPanel.bStop.setEnabled(false);
               javax.swing.JOptionPane.showMessageDialog(null, "No Path Available.", "Failure", javax.swing.JOptionPane.INFORMATION_MESSAGE);
               state = state.idle;
            }
        }            
    }
}