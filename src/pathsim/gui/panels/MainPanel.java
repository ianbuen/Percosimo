package pathsim.gui.panels;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import pathsim.Display;
import pathsim.Program;
import pathsim.algo.Algorithm;
import pathsim.gfx.Assets;
import pathsim.map.Map;
import pathsim.map.MapLoader;

public class MainPanel extends JPanel {
    
    // Main Properties
    private int width, height;
    
    public MainPanel(int w, int h) {
        width = w;
        height = h;
        
        initComponents();
    }
    
    // Main GUI Component(s)
    private Canvas canvas;
    private JButton bLoadMap, bOrigin, bTarget;
    public static JButton bStart, bPause, bStop;
    private JLabel descImage;
    private JComboBox comboSol;
    
    private void initComponents() {
        canvas = new Canvas();
        canvas.setBackground(Color.black);
        canvas.setBounds(5, 5, 640, 640);
        canvas.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if(Algorithm.state == Algorithm.State.pointA || Algorithm.state == Algorithm.State.pointB) {
                   Assets.locX = evt.getY() / 32; Assets.locY = evt.getX() / 32;
                }
            }
        });
        
        JPanel border = new JPanel();
        border.setBounds(5, 5, 650, 650);
        border.setBackground(new Color(50, 50, 50));
        border.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        border.setLayout(null);
        border.add(canvas);
        
        bLoadMap = new JButton();
        bLoadMap.setOpaque(false);
        bLoadMap.setContentAreaFilled(false);
        bLoadMap.setBorderPainted(false);
        bLoadMap.setBounds(16, 16, 300, 60);
        bLoadMap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resetMenu();
                Map m = new MapLoader().getMap();
                if(m != null) {
                   Program.map = m;
                   comboSol.setEnabled(true);
                }
            }
        });
        
        comboSol = new JComboBox();
        comboSol.setEnabled(false);
        comboSol.setEditable(false);
        comboSol.setFocusable(false);
        comboSol.setBounds(16, 96, 300, 60);
        comboSol.setBackground(new java.awt.Color(16, 16, 16));
        comboSol.setForeground(new java.awt.Color(255, 255, 255));
        comboSol.setMaximumRowCount(4);
//        comboSol.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "  SELECT ALGORITHM", "DEPTH-FIRST SEARCH", "BREADTH-FIRST SEARCH", "DJIKSTRA'S ALGORITHM" }));
//        comboSol.setFont(new Font("Sickness", Font.PLAIN, 30));
//        comboSol.setFont(new Font("8-Bit Madness", Font.PLAIN, 32));
        comboSol.setModel(new javax.swing.DefaultComboBoxModel(new ImageIcon[] { new ImageIcon(getClass().getResource("/gui/main/combo/select.png")), new ImageIcon(getClass().getResource("/gui/main/combo/dfs.png")), new ImageIcon(getClass().getResource("/gui/main/combo/bfs.png")), new ImageIcon(getClass().getResource("/gui/main/combo/dijkstra.png")) }));
        comboSol.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Algorithm.state = Algorithm.State.idle;
                Program.map.resetMap();
                Program.algo = null;
                bPause.setEnabled(false);
                bStart.setEnabled(false);
                bStop.setEnabled(false);
                
                switch(comboSol.getSelectedIndex()) {
                    case 1: descImage.setIcon(new ImageIcon(getClass().getResource("/gui/main/descDFS.png"))); 
                            bOrigin.setEnabled(true); bTarget.setEnabled(true); 
                            Program.algo = new pathsim.algo.DFS(); break;
                    case 2: descImage.setIcon(new ImageIcon(getClass().getResource("/gui/main/descBFS.png")));
                            bOrigin.setEnabled(true); bTarget.setEnabled(true); 
                            Program.algo = new pathsim.algo.BFS(); break;
                    case 3: descImage.setIcon(new ImageIcon(getClass().getResource("/gui/main/descDijkstra.png")));
                            bOrigin.setEnabled(true); bTarget.setEnabled(true); 
                            Program.algo = new pathsim.algo.Dijkstra(); break;
                    default: descImage.setIcon(new ImageIcon(getClass().getResource("/gui/main/descBlank.png")));
                             bOrigin.setEnabled(false); bTarget.setEnabled(false);
                             Program.algo = null;
                }
            }
        });
        
        bOrigin = new JButton();
        bOrigin.setEnabled(false);
        bOrigin.setOpaque(false);
        bOrigin.setContentAreaFilled(false);
        bOrigin.setBorderPainted(false);
        bOrigin.setBounds(16, 444, 143, 51);
        bOrigin.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if(bOrigin.isEnabled()) {
                   Algorithm.initialized = false;
                   Algorithm.state = Algorithm.State.pointA;
                   Program.map.unmarkAllTiles();
                }
            }
        });
        
        bTarget = new JButton();
        bTarget.setEnabled(false);
        bTarget.setOpaque(false);
        bTarget.setContentAreaFilled(false);
        bTarget.setBorderPainted(false);
        bTarget.setBounds(173, 444, 143, 51);
        bTarget.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if(bTarget.isEnabled()) {
                   Algorithm.initialized = false;
                   Algorithm.state = Algorithm.State.pointB;
                   Program.map.unmarkAllTiles();
                }
            }
        });
        
        bStart = new JButton();
        bStart.setEnabled(false);
        bStart.setOpaque(false);
        bStart.setContentAreaFilled(false);
        bStart.setBorderPainted(false);
        bStart.setBounds(16, 510, 93, 51);
        bStart.setToolTipText("Start");
        bStart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(bStart.isEnabled()) {
                   Algorithm.state = Algorithm.State.tracing;
                   bPause.setEnabled(true);
                   bStart.setEnabled(false);
                   bStop.setEnabled(true);
                }
            }
        });
        
        bPause = new JButton();
        bPause.setEnabled(false);
        bPause.setOpaque(false);
        bPause.setContentAreaFilled(false);
        bPause.setBorderPainted(false);
        bPause.setBounds(119, 510, 93, 51);
        bPause.setToolTipText("Pause");
        bPause.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(bPause.isEnabled()) {
                   Algorithm.state = Algorithm.State.idle;
                   bPause.setEnabled(false);
                   bStart.setEnabled(true);
                }
            }
        });
        
        bStop = new JButton();
        bStop.setEnabled(false);
        bStop.setOpaque(false);
        bStop.setContentAreaFilled(false);
        bStop.setBorderPainted(false);
        bStop.setBounds(222, 510, 93, 51);
        bStop.setToolTipText("Stop");
        bStop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if(bStop.isEnabled()) {
                   Algorithm.state = Algorithm.State.idle;
                   Algorithm.initialized = false;
                   Program.map.unmarkAllTiles();
                   bStop.setEnabled(false);
                   bPause.setEnabled(false);
                   bStart.setEnabled(true);
                }
            }
        });
        
        JButton bBack = new JButton();
        bBack.setOpaque(false);
        bBack.setContentAreaFilled(false);
        bBack.setBorderPainted(false);
        bBack.setBounds(16, 575, 300, 60);
        bBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resetMenu();
                Display.currentPanel = Display.menuPanel;
                Display.updatePanelState();
            }
        });
        
        //Set Icons
        try {
            bLoadMap.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/gui/main/bLoadMap.png"))));
            bLoadMap.setPressedIcon(new ImageIcon(ImageIO.read(getClass().getResource("/gui/main/bLoadMap2.png"))));
            bOrigin.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/gui/main/bOrigin.png"))));
            bOrigin.setPressedIcon(new ImageIcon(ImageIO.read(getClass().getResource("/gui/main/bOrigin2.png"))));
            bTarget.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/gui/main/bTarget.png"))));
            bTarget.setPressedIcon(new ImageIcon(ImageIO.read(getClass().getResource("/gui/main/bTarget2.png"))));
            bStart.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/gui/main/bStart.png"))));
            bStart.setPressedIcon(new ImageIcon(ImageIO.read(getClass().getResource("/gui/main/bStart2.png"))));
            bPause.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/gui/main/bPause.png"))));
            bPause.setPressedIcon(new ImageIcon(ImageIO.read(getClass().getResource("/gui/main/bPause2.png"))));
            bStop.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/gui/main/bStop.png"))));
            bStop.setPressedIcon(new ImageIcon(ImageIO.read(getClass().getResource("/gui/main/bStop2.png"))));
            bBack.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/gui/main/bBack.png"))));
            bBack.setPressedIcon(new ImageIcon(ImageIO.read(getClass().getResource("/gui/main/bBack2.png"))));
        } catch (IOException ex) {
            Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        descImage = new JLabel();
        descImage.setIcon(new ImageIcon(getClass().getResource("/gui/main/descBlank.png")));
        descImage.setBounds(16, 176, 300, 250);
        descImage.setVisible(true);
        
        JPanel ctrPanel = new JPanel();
        ctrPanel.setBounds(660, 5, 335, 650);
        ctrPanel.setBackground(new Color(50, 50, 50));
        ctrPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        ctrPanel.setLayout(null);
        ctrPanel.add(bLoadMap);
//        ctrPanel.add(bLoadSol);
        ctrPanel.add(comboSol);
        ctrPanel.add(bBack);
        ctrPanel.add(bStart);
        ctrPanel.add(bPause);
        ctrPanel.add(bStop);
        ctrPanel.add(bOrigin);
        ctrPanel.add(bTarget);
        ctrPanel.add(descImage);
               
        setPreferredSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        setLayout(null);
        add(border);
        add(ctrPanel);
    }
    
    private void resetMenu() {
        comboSol.setSelectedIndex(0);
        comboSol.setEnabled(false);
        bStart.setEnabled(false);
        bStop.setEnabled(false);
        bPause.setEnabled(false);
        Program.map = null;
        Program.algo = null;
        Assets.locX = -1; Assets.locY = -1;
    }
    
    public Canvas getCanvas() {
        return canvas;
    }
}