package pathsim.algo;

import java.awt.Point;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static pathsim.algo.Algorithm.initialized;
import static pathsim.algo.Algorithm.state;
import pathsim.gui.panels.MainPanel;
import pathsim.map.Tile;

public class Dijkstra extends Algorithm {
    
    private LinkedList tileList, visList;
    private HashMap<Tile, Tile> prevHash;
    private int dist[][];
    private Tile current = null;
    private Point pt;
    
    public void pathfind() {
        if(!initialized) {
            map.unmarkAllTiles();
            
            dist = new int[20][20];
            dist[origin.x][origin.y] = 0;
            
            current = map.tileAt(origin);
            tileList = new LinkedList();
            visList = new LinkedList();
            prevHash = new HashMap<Tile, Tile>();
            
            for(int i = 0; i < 20; i++)
                for(int j = 0; j < 20; j++) {
                    if(map.tileAt(i, j) != current) {
                        dist[i][j] = 999999;
                        prevHash.put(map.tileAt(i, j), null);
                    }
                    
                    if(map.tileAt(i, j).passable())
                       tileList.add(map.tileAt(i, j));
                }
            
            initialized = true;
        }
        
        if(state == State.tracing) {
           if(!tileList.isEmpty()) {
               current = minDistance();
               
               if(distanceOf(current) == 999999) {
                  initialized = false;
                  MainPanel.bStart.setEnabled(true);
                  MainPanel.bPause.setEnabled(false);
                  MainPanel.bStop.setEnabled(false);
                  JOptionPane.showMessageDialog(null, "No Path Available.", "Failure", JOptionPane.INFORMATION_MESSAGE);
                  state = state.idle;
                  return;
               }

               visList.add(current);
               current.mark();

               if(current == map.tileAt(destination)) {
                  initialized = false;

                  for(Tile tile = map.tileAt(destination); tile != null; tile = prevHash.get(tile))
                      tile.markTrail();

                  MainPanel.bStart.setEnabled(true);
                  MainPanel.bPause.setEnabled(false);
                  MainPanel.bStop.setEnabled(false);
                  JOptionPane.showMessageDialog(null, "Path Found!", "Success", JOptionPane.INFORMATION_MESSAGE);
                  state = state.idle;
                  return;
               }
               
               pt = current.getLocation();
               

               if(map.tileAt(pt.x-1, pt.y) != null && map.tileAt(pt.x-1, pt.y).passable() && !map.tileAt(pt.x-1, pt.y).marked())
                  if(dist[pt.x-1][pt.y] > dist[pt.x][pt.y] + 1) {
                     dist[pt.x-1][pt.y] = dist[pt.x][pt.y] + 1;
                     prevHash.put(map.tileAt(pt.x-1, pt.y), current);
                  }

               if(map.tileAt(pt.x, pt.y-1) != null && map.tileAt(pt.x, pt.y-1).passable() && !map.tileAt(pt.x, pt.y-1).marked())
                  if(dist[pt.x][pt.y-1] > dist[pt.x][pt.y] + 1) {
                     dist[pt.x][pt.y-1] = dist[pt.x][pt.y] + 1;
                     prevHash.put(map.tileAt(pt.x, pt.y-1), current);
                  }

               if(map.tileAt(pt.x+1, pt.y) != null && map.tileAt(pt.x+1, pt.y).passable() && !map.tileAt(pt.x+1, pt.y).marked())
                  if(dist[pt.x+1][pt.y] > dist[pt.x][pt.y] + 1) {
                     dist[pt.x+1][pt.y] = dist[pt.x][pt.y] + 1;
                     prevHash.put(map.tileAt(pt.x+1, pt.y), current);
                  }

               if(map.tileAt(pt.x, pt.y+1) != null && map.tileAt(pt.x, pt.y+1).passable() && !map.tileAt(pt.x, pt.y+1).marked())
                  if(dist[pt.x][pt.y+1] > dist[pt.x][pt.y] + 1) {
                     dist[pt.x][pt.y+1] = dist[pt.x][pt.y] + 1;
                     prevHash.put(map.tileAt(pt.x, pt.y+1), current);
                  }
               
               try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(BFS.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            } else {
                  initialized = false;
                  MainPanel.bStart.setEnabled(true);
                  MainPanel.bPause.setEnabled(false);
                  MainPanel.bStop.setEnabled(false);
                  JOptionPane.showMessageDialog(null, "No Path Available.", "Failure", JOptionPane.INFORMATION_MESSAGE);
                  state = state.idle;
            } 
        }
    }
    
    private Tile minDistance() {
        Tile prev = null, curr = (Tile)tileList.getFirst(), min = null;
        int index = 0;
        
        for(int i = 1; i < tileList.size(); i++) {
            prev = curr;
            curr = (Tile)tileList.get(i);
            
            if(distanceOf(curr) < distanceOf(prev)) {
               min = curr;
               index = i;
            }
        }       
        
        return (Tile)tileList.remove(index);
    }
    
    private int distanceOf(Tile tile) {
        return dist[tile.getLocation().x][tile.getLocation().y];
    }
}
