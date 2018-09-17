package pathsim.map;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MapLoader {
    
    // PROPERTIES
        private final File mapDir = new File(System.getProperty("user.dir") + "/maps");
        
        private Map map = null;
        private char[][] charMap = null;
        private BufferedImage[][] arrImg = null;
        
        private JFileChooser fc;
        private BufferedReader r;

    // CONSTRUCTOR
        public MapLoader() {   
            if(!mapDir.exists())
               mapDir.mkdir();
                        
            fc = new JFileChooser();
            fc.setAcceptAllFileFilterUsed(false);
            fc.setCurrentDirectory(mapDir);
            fc.setDialogTitle("Load Map");
            fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            fc.setFileFilter(new FileNameExtensionFilter("MAP File", "map"));

            if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION && fc.getSelectedFile().getName().endsWith(".map") && fc.getSelectedFile().exists()) {
                try {
                    r = new BufferedReader(new FileReader(fc.getSelectedFile()));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
                }

                map = new Map();
                charMap = new char[20][20];
                arrImg = new BufferedImage[20][20];

                for(int i = 0; i < 20; i++) {
                    String line = "";

                    try {
                        line = r.readLine();
                    } catch (IOException ex) {
                        Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    for(int j = 0; j < 20; j++) {
                        map.setTile(new Tile(new Point(i, j), line.charAt(j)));
                        charMap[i][j] = line.charAt(j);
                        arrImg[i][j] = map.tileAt(i, j).getSprite();
                    }
                }

                try {
                    r.close();
                } catch (IOException ex) {
                    Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    
    // METHODS
        public char[][] getCharMap() {
            return charMap;
        }
        
        public BufferedImage[][] getArrImg() {
            return arrImg;
        }
        
        public Map getMap() {
            return map;
        }    
}