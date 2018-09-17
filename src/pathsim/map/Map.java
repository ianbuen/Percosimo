package pathsim.map;

import java.awt.Graphics;
import java.awt.Point;

public class Map {
    
    // PROPERTIES
       private Tile[][] tiles;
    
       public Map() {
            tiles = new Tile[20][20];
       }
        
    // METHODS
        public Tile[][] getTiles() {
            return tiles;
        }
        
        public void setTile(Tile tile) {
            Point p = tile.getLocation();
            tiles[p.x][p.y] = tile;
        }
        
        public Tile tileAt(Point p) {
            if(p.x < 0 || p.x > 19 || p.y < 0 || p.y > 19)
               return null;
            
            return tiles[p.x][p.y];
        }
        
        public Tile tileAt(int x, int y) {
            if(x < 0 || x > 19 || y < 0 || y > 19)
               return null;
            
            return tiles[x][y];
        }
        
        public void drawMap(Graphics g) {
            for(int i = 0; i < 640; i+=32)
                       for(int j = 0; j < 640; j+=32)
                           this.tileAt(j/32, i/32).drawSprite(g, i, j);
        }
        
        public void unmarkAllTiles() {
            for(int i = 0; i < 20; i++)
                for(int j = 0; j < 20; j++) {
                    this.tileAt(i, j).unMark();
                    this.tileAt(i, j).unMarkTrail();
                }
        }        
        
        public void resetMap() {
            for(int i = 0; i < 20; i++)
                for(int j = 0; j < 20; j++) {
                    this.tileAt(i, j).unMark();
                    this.tileAt(i, j).unMarkTrail();
                    this.tileAt(i, j).removeGoal();
                    this.tileAt(i, j).removeOrigin();
                }
        }
}