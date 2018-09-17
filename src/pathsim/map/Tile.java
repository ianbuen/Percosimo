package pathsim.map;

import java.awt.Graphics;
import pathsim.gfx.Assets;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class Tile {
    
    //  PROPERTIES
        private Point location;
        private boolean passable = true;
        private BufferedImage sprite;
        private String name;
        private char type;
        
        private boolean marked = false;
        private boolean trail = false;
        private boolean current = false;
        private boolean origin = false;
        private boolean destination = false;
        
    // CONSTRUCTOR
        public Tile(Point p, char type) {
            // This packs up the sprite, tile type and location of the tile instance.
            location = p;
            this.type = type;
            
            setSprite();
        }
    
    //  METHODS        
        public boolean passable() {
            // method to check if this tile is passable
            return passable;
        }
        
        public String getName() {
            return name;
        }
        
        public void setName(String newName) {
            name = newName;
        }
        
        public Point getLocation() {
            // points the location of the tile
            return location;
        }
        
        private void setSprite() {            
            // This portion a tile image out of the sprite sheet.
            switch(type) {
                case '1': sprite = Assets.tree;
                        name = "Tree";
                        passable = false;
                        break;
                case '2': sprite = Assets.boulder;
                        name = "Boulder";
                        passable = false;
                        break;
                case '3': sprite = Assets.fence;
                        name = "Fence";
                        passable = false;
                        break;
                case '4': sprite = Assets.fenceUL;
                        name = "Fence (NW)";
                        passable = false;
                        break;
                case '5': sprite = Assets.fenceL;
                        name = "Fence (W)";
                        passable = false;
                        break;
                case '6': sprite = Assets.fenceDL;
                        name = "Fence (SW)";
                        passable = false;
                        break;  
                case '7': sprite = Assets.fenceR;
                        name = "Fence (E)";
                        passable = false;
                        break;  
                case '8': sprite = Assets.fenceUR;
                        name = "Fence (NE)";
                        passable = false;
                        break;  
                case '9': sprite = Assets.fenceDR;
                        name = "Fence (SE)";
                        passable = false;
                        break; 
                    
                case 'a': sprite = Assets.dirt;
                        name = "Dirt";
                        break; 
                case 'b': sprite = Assets.mud;
                        name = "Mud";
                        break; 
                case 'c': sprite = Assets.lava;
                        name = "Lava";
                        passable = false;
                        break; 
                case 'd': sprite = Assets.water;
                        name = "Water";
                        passable = false;
                        break; 
                case 'e': sprite = Assets.crater;
                        name = "Crater";
                        passable = false;
                        break; 
                case 'f': sprite = Assets.sand;
                        name = "Sand";
                        break; 
                case 'g': sprite = Assets.sandstone;
                        name = "Sandstone";
                        passable = false;
                        break; 
                case 'h': sprite = Assets.sandstone2;
                        name = "Sandstone 2";
                        passable = false;
                        break; 
                case 'i': sprite = Assets.sandstone3;
                        name = "Smooth Sand";
                        break; 
                case 'j': sprite = Assets.cactus;
                        name = "Cactus";
                        passable = false;
                        break;
                    
                case 'k': sprite = Assets.plank1;
                        name = "Wood 1";
                        break; 
                case 'l': sprite = Assets.plank2;
                        name = "Wood 2";
                        break; 
                case 'm': sprite = Assets.plank3;
                        name = "Wood 3";
                        break; 
                case 'n': sprite = Assets.plank4;
                        name = "Wood 4";
                        break; 
                case 'o': sprite = Assets.wall;
                        name = "Brick Wall";
                        passable = false;
                        break; 
                case 'p': sprite = Assets.stone;
                        name = "Stone";
                        break; 
                case 'q': sprite = Assets.stonebrick;
                        name = "Stone Brick";
                        passable = false;
                        break; 
                case 'r': sprite = Assets.stonebrick2;
                        name = "Stone Brick 2";
                        passable = false;
                        break; 
                case 's': sprite = Assets.stonebrick3;
                        name = "Stone Brick 3";
                        passable = false;
                        break; 
                case 't': sprite = Assets.stonebrick4;
                        name = "Stone Brick 4";
                        passable = false;
                        break;
                    
                case 'u': sprite = Assets.snow;
                        name = "Snow";
                        break;
                case 'v': sprite = Assets.snowrock;
                        name = "Snow Rock";
                        passable = false;
                        break;
                case 'w': sprite = Assets.snowstump;
                        name = "Snow Stump";
                        passable = false;
                        break;
                case 'x': sprite = Assets.snowtree;
                        name = "Snow Tree";
                        passable = false;
                        break;
                case 'y': sprite = Assets.ice;
                        name = "Ice";
                        break;
                case 'z': sprite = Assets.log;
                        name = "Log";
                        passable = false;
                        break;
                case 'A': sprite = Assets.plant;
                        name = "Shrub";
                        passable = false;
                        break;
                case 'B': sprite = Assets.plant2;
                        name = "Flowers";
                        passable = false;
                        break;
                case 'C': sprite = Assets.plant3;
                        name = "Flowers 2";
                        passable = false;
                        break;
                case 'D': sprite = Assets.waterlily;
                        name = "Water Lily";
                        passable = false;
                        break;
                    
                case 'E': sprite = Assets.bridge;
                        name = "Bridge";
                        break;
                case 'F': sprite = Assets.metalfloor;
                        name = "Metal Floor";
                        break;
                case 'G': sprite = Assets.metalwall;
                        name = "Metal Wall";
                        passable = false;
                        break;
                case 'H': sprite = Assets.metalfloor2;
                        name = "Metal Floor 2";
                        break;
                case 'I': sprite = Assets.metalwall2;
                        name = "Metal Wall 2";
                        passable = false;
                        break;
                case 'J': sprite = Assets.icewall;
                        name = "Ice Wall";
                        passable = false;
                        break;
                case 'K': sprite = Assets.icepile;
                        name = "Ice Pile";
                        passable = false;
                        break;
                case 'L': sprite = Assets.icepit;
                        name = "Ice Pit";
                        passable = false;
                        break;
                case 'M': sprite = Assets.icefloor;
                        name = "Ice Floor";
                        break;
                    
                default: sprite = Assets.grass;
                         name = "Grass";
            }
        }
        
        public void setSprite(BufferedImage sprite) {
            this.sprite = sprite;
        }
        
        public BufferedImage getSprite() {
            return sprite;
        }
        
        public void mark() {
            if(!marked)
               marked = true;
        }
        
        public void unMark() {
            if(marked)
               marked = false;
        }
        
        public void markTrail() {
            if(!trail)
               trail = true;
        }
        
        public void unMarkTrail() {
            if(trail)
               trail = false;
        }
        
        public boolean marked() {
            return marked;
        }
        
        public void drawSprite(Graphics g, int i, int j) {
            g.drawImage(sprite, i, j, null);
            
            if(destination)
               g.drawImage(Assets.finish, i, j, null);
            if(marked)
               g.drawImage(Assets.marker, i, j, null);
            if(origin)
               g.drawImage(Assets.origin, i, j, null); 
            if(trail)
               g.drawImage(Assets.trail, i, j, null); 
        }
        
        public void setAsOrigin() {
            if(!origin)
               origin = true;
        }
        
        public void removeOrigin() {
            if(origin)
               origin = false;
        }
        
        public void setAsGoal() {
            if(!destination)
               destination = true;
        }
        
        public void removeGoal() {
            if(destination)
               destination = false;
        }
        
        public void toggleCurrent() {
            if(!current)
               current = true;
            else
                current = false;
        }
}