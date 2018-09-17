package pathsim.gui.editor;

import java.awt.Point;
import pathsim.gfx.Assets;
import pathsim.map.Tile;

public class Palette {
    
    public Tile[][] arrPalette;
    
    public Palette() {
        arrPalette = new Tile[5][10];
        
        arrPalette[0][0] = new Tile(new Point(0, 0), '0');
        arrPalette[0][1] = new Tile(new Point(0, 1), '1');
        arrPalette[0][2] = new Tile(new Point(0, 2), '2');
        arrPalette[0][3] = new Tile(new Point(0, 3), '3');
        arrPalette[0][4] = new Tile(new Point(0, 4), '4');
        arrPalette[0][5] = new Tile(new Point(0, 5), '5');
        arrPalette[0][6] = new Tile(new Point(0, 6), '6');
        arrPalette[0][7] = new Tile(new Point(0, 7), '7');
        arrPalette[0][8] = new Tile(new Point(0, 8), '8');
        arrPalette[0][9] = new Tile(new Point(0, 9), '9');
        arrPalette[1][0] = new Tile(new Point(0, 0), 'a');
        arrPalette[1][1] = new Tile(new Point(0, 1), 'b');
        arrPalette[1][2] = new Tile(new Point(0, 2), 'c');
        arrPalette[1][3] = new Tile(new Point(0, 3), 'd');
        arrPalette[1][4] = new Tile(new Point(0, 4), 'e');
        arrPalette[1][5] = new Tile(new Point(0, 5), 'f');
        arrPalette[1][6] = new Tile(new Point(0, 6), 'g');
        arrPalette[1][7] = new Tile(new Point(0, 7), 'h');
        arrPalette[1][8] = new Tile(new Point(0, 8), 'i');
        arrPalette[1][9] = new Tile(new Point(0, 9), 'j');
        arrPalette[2][0] = new Tile(new Point(0, 0), 'k');
        arrPalette[2][1] = new Tile(new Point(0, 1), 'l');
        arrPalette[2][2] = new Tile(new Point(0, 2), 'm');
        arrPalette[2][3] = new Tile(new Point(0, 3), 'n');
        arrPalette[2][4] = new Tile(new Point(0, 4), 'o');
        arrPalette[2][5] = new Tile(new Point(0, 5), 'p');
        arrPalette[2][6] = new Tile(new Point(0, 6), 'q');
        arrPalette[2][7] = new Tile(new Point(0, 7), 'r');
        arrPalette[2][8] = new Tile(new Point(0, 8), 's');
        arrPalette[2][9] = new Tile(new Point(0, 9), 't');
        arrPalette[3][0] = new Tile(new Point(0, 0), 'u');
        arrPalette[3][1] = new Tile(new Point(0, 1), 'v');
        arrPalette[3][2] = new Tile(new Point(0, 2), 'w');
        arrPalette[3][3] = new Tile(new Point(0, 3), 'x');
        arrPalette[3][4] = new Tile(new Point(0, 4), 'y');
        arrPalette[3][5] = new Tile(new Point(0, 5), 'z');
        arrPalette[3][6] = new Tile(new Point(0, 6), 'A');
        arrPalette[3][7] = new Tile(new Point(0, 7), 'B');
        arrPalette[3][8] = new Tile(new Point(0, 8), 'C');
        arrPalette[3][9] = new Tile(new Point(0, 9), 'D');
        arrPalette[4][0] = new Tile(new Point(0, 0), 'E');
        arrPalette[4][1] = new Tile(new Point(0, 1), 'F');
        arrPalette[4][2] = new Tile(new Point(0, 2), 'G');
        arrPalette[4][3] = new Tile(new Point(0, 3), 'H');
        arrPalette[4][4] = new Tile(new Point(0, 4), 'I');
        arrPalette[4][5] = new Tile(new Point(0, 5), 'J');
        arrPalette[4][6] = new Tile(new Point(0, 6), 'K');
        arrPalette[4][7] = new Tile(new Point(0, 7), 'L');
        arrPalette[4][8] = new Tile(new Point(0, 8), 'M');
        arrPalette[4][9] = new Tile(new Point(0, 9), '0');
        arrPalette[4][9].setSprite(Assets.grid);
        arrPalette[4][9].setName("Empty");
        
        
    }
}
