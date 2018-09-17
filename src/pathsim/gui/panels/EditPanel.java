package pathsim.gui.panels;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.filechooser.FileNameExtensionFilter;
import pathsim.Display;
import pathsim.gfx.Assets;
import pathsim.gui.editor.Palette;
import pathsim.map.MapLoader;
import pathsim.map.Tile;

public class EditPanel extends JPanel {
    
    private int width, height;
    private Canvas drawspace, palette;
    
    private BufferedImage arrImg[][];
    private Tile arrPalette[][];
    private BufferedImage arrHighlight[][];  
    private Tile selPalette;
    
    private char charMap[][];
    
    public EditPanel(int w, int h) {
        width = w;
        height = h;
        
        initArrays();
        initComponents();
    }
    
    private void initComponents() {
        setPreferredSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        setBackground(Color.DARK_GRAY);
        setLayout(null);
        
        initDrawspace();
        initPalette();
        initButtons();
    }
    
    private void initDrawspace() {
        drawspace = new Canvas();
        drawspace.setBackground(Color.white);
        drawspace.setBounds(5, 5, 640, 640);
        
        drawspace.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Assets.locY = evt.getX() / 32; Assets.locX = evt.getY() / 32;
                
                if(fill.isSelected())
                   fillArea(new Point(Assets.locX, Assets.locY), arrImg[Assets.locX][Assets.locY]);
                else
                   updateMapArray();
            }
        });
        
        drawspace.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                if(pen.isSelected()) {
                    Assets.locY = evt.getX() / 32; Assets.locX = evt.getY() / 32;
                    updateMapArray();
                }
            }
        });
        
        JPanel drawspaceBorder = new JPanel();
        drawspaceBorder.setBackground(new Color(20, 20, 20));
        drawspaceBorder.setBounds(5, 5, 650, 650);
        drawspaceBorder.add(drawspace);
        drawspaceBorder.setLayout(null);
        add(drawspaceBorder);
    }
    
    private void initPalette() {
        palette = new Canvas();
        palette.setBackground(Color.white);
        palette.setBounds(5, 5, 32 * 10, 32 * 5);
        
        palette.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Assets.locY = evt.getX() / 32; Assets.locX = evt.getY() / 32;
                setHighlight();
            }
        });
        
        JPanel paletteborder = new JPanel();
        paletteborder.setLayout(null);
        paletteborder.setBackground(new Color(20, 20, 20));
        paletteborder.setBounds(663, 5, palette.getWidth() + 10, palette.getHeight() + 10);
        paletteborder.add(palette);
        add(paletteborder);
        
        initPaletteContents();
    }
    
    private void initPaletteContents() {
        arrPalette = new Palette().arrPalette;
        
        arrHighlight[0][0] = Assets.highlight;
        selPalette = arrPalette[0][0];
    }
    
    private void initArrays() {
        arrImg = new BufferedImage[20][20];
        charMap = new char[20][20];
        arrHighlight = new BufferedImage[5][10];

        // initialize canvas array
        for(int i = 0; i < 20; i++)
            for(int j = 0; j < 20; j++)
                arrImg[i][j] = Assets.grid;
        
        // initialize char map array
        for(int i = 0; i < 20; i++)
            for(int j = 0; j < 20; j++)
                charMap[i][j] = '0';
        
        // initalize highlight array
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 10; j++)
                arrHighlight[i][j] = null;
    }
    
    private JButton clear, save, back, open;
    private JToggleButton fill, pen;
    private JLabel labTile, labPassable, labTool;
    
    private void initButtons() {        
        clear = new JButton();
        save = new JButton();
        back = new JButton();
        open = new JButton();
        fill = new JToggleButton();
        pen = new JToggleButton();
        
        InputStream is = getClass().getResourceAsStream("/gui/editor/8-bit Madness.ttf");
        Font myFont = null;
        try {
                 myFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException ex) {
            Logger.getLogger(EditPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EditPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        labTile = new JLabel("SELECTED:  " + arrPalette[0][0].getName().toUpperCase());
        labTile.setForeground(Color.white);
        labTile.setFont(myFont.deriveFont(Font.PLAIN, 28));
        labTile.setBounds(25, 25, 330, 40);
        labPassable = new JLabel("PASSABLE:  " + String.valueOf(arrPalette[0][0].passable()).toUpperCase());
        labPassable.setFont(myFont.deriveFont(Font.PLAIN, 28));
        labPassable.setForeground(Color.white);
        labPassable.setBounds(25, 75, 330, 40);
        labTool = new JLabel("SELECTED TOOL: PENCIL");
        labTool.setFont(myFont.deriveFont(Font.PLAIN, 28));
//        labTool.setFont(new Font("8-Bit Madness", Font.PLAIN, 28));
        labTool.setForeground(Color.white);
        labTool.setBounds(25, 25, 330, 40);
        
        open.setBounds(10, 10, 96, 80);
        save.setBounds(118, 10, 96, 80);
        back.setBounds(224, 10, 96, 80);
        pen.setBounds(10, 10, 96, 80);
        fill.setBounds(118, 10, 96, 80);
        clear.setBounds(224, 10, 96, 80);
        
        clear.setOpaque(false);
        clear.setContentAreaFilled(false);
        clear.setBorderPainted(false);
        clear.setToolTipText("Clear All");
        save.setOpaque(false);
        save.setContentAreaFilled(false);
        save.setBorderPainted(false);
        save.setToolTipText("Save Map");
        back.setOpaque(false);
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setToolTipText("Back to Main Menu");
        open.setOpaque(false);
        open.setContentAreaFilled(false);
        open.setBorderPainted(false);
        open.setToolTipText("Open Map");
        
        fill.setOpaque(false);
        fill.setContentAreaFilled(false);
        fill.setBorderPainted(false);
        fill.setToolTipText("Fill Tool");
        pen.setOpaque(false);
        pen.setContentAreaFilled(false);
        pen.setBorderPainted(false);
        pen.setSelected(true);
        pen.setToolTipText("Pen Tool");
        
        clear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                    for(int i = 0; i < 20; i++)
                        for(int j = 0; j < 20; j++) {
                            arrImg[i][j] = Assets.grid;
                            charMap[i][j] = '0';
                        }
            }
        });
        
        open.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openMap();
            }
        });
        
        save.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exportCharMap();
            }
        });
        
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Display.currentPanel = Display.menuPanel;
                Display.updatePanelState();
                resetMenu();
            }
        });
        
        fill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                toggleButton(fill);
            }
        });
        
        pen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                toggleButton(pen);
            }
        });
        
        try {
            clear.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/gui/editor/clear.png"))));
            clear.setPressedIcon(new ImageIcon(ImageIO.read(getClass().getResource("/gui/editor/clear2.png"))));
            open.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/gui/editor/open.png"))));
            open.setPressedIcon(new ImageIcon(ImageIO.read(getClass().getResource("/gui/editor/open2.png"))));
            save.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/gui/editor/save.png"))));
            save.setPressedIcon(new ImageIcon(ImageIO.read(getClass().getResource("/gui/editor/save2.png"))));
            back.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/gui/editor/back.png"))));
            back.setPressedIcon(new ImageIcon(ImageIO.read(getClass().getResource("/gui/editor/back2.png"))));
            fill.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/gui/editor/fill.png"))));
            fill.setSelectedIcon(new ImageIcon(ImageIO.read(getClass().getResource("/gui/editor/fill2.png"))));
            pen.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/gui/editor/pen.png"))));
            pen.setSelectedIcon(new ImageIcon(ImageIO.read(getClass().getResource("/gui/editor/pen2.png"))));
        } catch (IOException ex) {
            Logger.getLogger(EditPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JPanel tileInfo = new JPanel();
        tileInfo.setBackground(new Color(20, 20, 20));
        tileInfo.setBounds(663, 185, 330, 140);
        tileInfo.setLayout(null);
        tileInfo.add(labTile);
        tileInfo.add(labPassable);
        
        JPanel toolPanel = new JPanel();
        toolPanel.setBackground(new Color(20, 20, 20));
        toolPanel.setBounds(663, 335, 330, 100);
        toolPanel.setLayout(null);
        toolPanel.add(pen);
        toolPanel.add(clear);
        toolPanel.add(fill);
        
        JPanel toolInfo = new JPanel();
        toolInfo.setBackground(new Color(20, 20, 20));
        toolInfo.setBounds(663, 445, 330, 95);
        toolInfo.setLayout(null);
        toolInfo.add(labTool);
        
        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(new Color(20, 20, 20));
        btnPanel.setLayout(null);
        btnPanel.setBounds(663, 550, 330, 100);
        btnPanel.add(save);
        btnPanel.add(back);
        btnPanel.add(open);
        
        add(btnPanel);
        add(toolPanel);
        add(tileInfo);
        add(toolInfo);
    }
    
    private void toggleButton(JToggleButton button) {
        if(button == pen) {
           pen.setSelected(true);
           fill.setSelected(false);
           labTool.setText("SELECTED TOOL: PENCIL");
        } else if(button == fill) {
            fill.setSelected(true);
            pen.setSelected(false);
            labTool.setText("SELECTED TOOL: FILL");
        }
    }
    
    public Canvas getDrawspace() {
        return drawspace;
    }
    
    public Canvas getPalette() {
        return palette;
    }
    
    private void setHighlight() {
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 10; j++) {
                if(i == Assets.locX && j == Assets.locY) {
                   arrHighlight[i][j] = Assets.highlight;
                   selPalette = arrPalette[i][j];
                }
                else
                   arrHighlight[i][j] = null;
            }
        
        labTile.setText("SELECTED:  " + selPalette.getName().toUpperCase());
        labPassable.setText("PASSABLE:  " + String.valueOf(selPalette.passable()).toUpperCase());
        if(selPalette.getName().equals("Empty"))
           labPassable.setText("");
    }
    
    private char tileToChar(BufferedImage img) {
        if(img == Assets.tree)
           return '1';
        else if(img == Assets.boulder)
           return '2';
        else if(img == Assets.fence)
           return '3';
        else if(img == Assets.fenceUL)
           return '4';
        else if(img == Assets.fenceL)
           return '5';
        else if(img == Assets.fenceDL)
           return '6';
        else if(img == Assets.fenceR)
           return '7';
        else if(img == Assets.fenceUR)
           return '8';
        else if(img == Assets.fenceDR)
           return '9';
        else if(img == Assets.dirt)
           return 'a';
        else if(img == Assets.mud)
           return 'b';
        else if(img == Assets.lava)
           return 'c';
        else if(img == Assets.water)
            return 'd';
        else if(img == Assets.crater)
            return 'e';
        else if(img == Assets.sand)
            return 'f';
        else if(img == Assets.sandstone)
            return 'g';
        else if(img == Assets.sandstone2)
            return 'h';
        else if(img == Assets.sandstone3)
            return 'i';
        else if(img == Assets.cactus)
            return 'j';
        else if(img == Assets.plank1)
            return 'k';
        else if(img == Assets.plank2)
            return 'l';
        else if(img == Assets.plank3)
            return 'm';
        else if(img == Assets.plank4)
            return 'n';
        else if(img == Assets.wall)
            return 'o';
        else if(img == Assets.stone)
            return 'p';
        else if(img == Assets.stonebrick)
            return 'q';
        else if(img == Assets.stonebrick2)
            return 'r';
        else if(img == Assets.stonebrick3)
            return 's';
        else if(img == Assets.stonebrick4)
            return 't';
        else if(img == Assets.snow)
            return 'u';
        else if(img == Assets.snowrock)
            return 'v';
        else if(img == Assets.snowstump)
            return 'w';
        else if(img == Assets.snowtree)
            return 'x';
        else if(img == Assets.ice)
            return 'y';
        else if(img == Assets.log)
            return 'z';
        else if(img == Assets.plant)
            return 'A';
        else if(img == Assets.plant2)
            return 'B';
        else if(img == Assets.plant3)
            return 'C';
        else if(img == Assets.waterlily)
            return 'D';
        else if(img == Assets.bridge)
            return 'E';
        else if(img == Assets.metalfloor)
            return 'F';
        else if(img == Assets.metalwall)
            return 'G';
        else if(img == Assets.metalfloor2)
            return 'H';
        else if(img == Assets.metalwall2)
            return 'I';
        else if(img == Assets.icewall)
            return 'J';
        else if(img == Assets.icepile)
            return 'K';
        else if(img == Assets.icepit)
            return 'L';
        else if(img == Assets.icefloor)
            return 'M';
        else
            return '0';
    }
    
    private void updateMapArray() {
        if(Assets.locX > -1 && Assets.locY > -1 && Assets.locX < 20 && Assets.locY < 20) {
            arrImg[Assets.locX][Assets.locY] = selPalette.getSprite();
            charMap[Assets.locX][Assets.locY] = tileToChar(selPalette.getSprite());
        }
    }
    
    private void fillArea(Point src, BufferedImage old) {
        if(pointOutOfBounds(src) || arrImg[src.x][src.y] != old || arrImg[src.x][src.y] == selPalette.getSprite())
           return;
         
        charMap[src.x][src.y] = tileToChar(selPalette.getSprite());
        arrImg[src.x][src.y] = selPalette.getSprite();
        
        fillArea(new Point(src.x-1, src.y), old);
        fillArea(new Point(src.x+1, src.y), old);
        fillArea(new Point(src.x, src.y-1), old);
        fillArea(new Point(src.x, src.y+1), old);
    }
    
    private boolean pointOutOfBounds(Point point) {
        if(point.x > 19 || point.x < 0 || point.y > 19 || point.y < 0)
           return true;
        
        return false;
    }
    
    private void resetMenu() {
        for(int i = 0; i < 20; i++)
            for(int j = 0; j < 20; j++) {
                charMap[i][j] = '0';
                arrImg[i][j] = Assets.grid;
            }
        
        selPalette = arrPalette[0][0];
        Assets.locX = 0; Assets.locY = 0;
        setHighlight();
    }
    
    private void exportCharMap() {
        File mapDir = new File(System.getProperty("user.dir") + "/maps");
        
        JFileChooser fc = new JFileChooser();
        FileWriter fw;
        
        if(!mapDir.exists())
           mapDir.mkdir();
        
        fc.setCurrentDirectory(mapDir);
        fc.setDialogTitle("Save Map");
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fc.setAcceptAllFileFilterUsed(false);
        fc.setFileFilter(new FileNameExtensionFilter("MAP File", "map"));
        
        if(fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
           if(!fc.getSelectedFile().getName().endsWith(".map"))
              fc.setSelectedFile((new File(mapDir.getName() + "/" + fc.getSelectedFile().getName() + ".map")));
           
           if(fc.getSelectedFile().exists())
              if(JOptionPane.showConfirmDialog(null, "File already exists. Overwrite?", "Overwrite", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
                 return;
           
            try {
                if(fc.getSelectedFile().getName().endsWith(".map"))
                   fw = new FileWriter(fc.getSelectedFile());
                else {
                   fw = new FileWriter(fc.getSelectedFile() + ".map");
                }
                
                BufferedWriter bw = new BufferedWriter(fw);
                
                for(int i = 0; i < 20; i++)
                    bw.write(String.valueOf(charMap[i]) + "\n");
                
                bw.close();
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(EditPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void openMap() {
        MapLoader loader = new MapLoader();
        if(loader.getArrImg() != null && loader.getCharMap() != null) {
            arrImg = loader.getArrImg();
            charMap = loader.getCharMap();
        }
    }
    
    public void render(Graphics g, Graphics g2) {
        for(int i = 0; i < 20; i++)
            for(int j = 0; j < 20; j++)
                g.drawImage(arrImg[i][j], 32*j, 32*i, null);
        
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 10; j++) {
                g2.drawImage(arrPalette[i][j].getSprite(), 32*j, 32*i, null);
                if(arrHighlight[i][j] == Assets.highlight)
                   g2.drawImage(arrHighlight[i][j], 32*j, 32*i, null);
            }
    }
}