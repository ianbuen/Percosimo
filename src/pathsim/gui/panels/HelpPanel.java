package pathsim.gui.panels;

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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import pathsim.Display;

public class HelpPanel extends JPanel {
    
    // Main Properties
    private int width, height;
    
    public HelpPanel(int w, int h) {
        width = w;
        height = h;
        
        initHelpPanel();
    }
    
    // GUI
    
    private void initHelpPanel() {
        setPreferredSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        setBackground(new Color(20, 20, 20));
        setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
        setLayout(null);
        
        initHelpItems();
        initContent();
        
//        JButton b = new JButton("TESTING... PRESS TO GO BACK");
//        b.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent evt) {
//                Display.currentPanel = Display.menuPanel;
//                Display.updatePanelState();
//            }
//        });
//        add(b);
    }
    
    JButton bSimu, bEdit, bBack;
    JPanel helpItems;
    private void initHelpItems() {        
        bSimu = new JButton();
        bEdit = new JButton();
        bBack = new JButton();
        
        bSimu.setOpaque(false);
        bSimu.setContentAreaFilled(false);
        bSimu.setBorderPainted(false);
        bEdit.setOpaque(false);
        bEdit.setContentAreaFilled(false);
        bEdit.setBorderPainted(false);
        bBack.setOpaque(false);
        bBack.setContentAreaFilled(false);
        bBack.setBorderPainted(false);
        
        try {
            bSimu.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/gui/help/simulation.png"))));
            bSimu.setPressedIcon(new ImageIcon(ImageIO.read(getClass().getResource("/gui/help/simulation2.png"))));
            bEdit.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/gui/help/mapping.png"))));
            bEdit.setPressedIcon(new ImageIcon(ImageIO.read(getClass().getResource("/gui/help/mapping2.png"))));
            bBack.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/gui/help/back.png"))));
            bBack.setPressedIcon(new ImageIcon(ImageIO.read(getClass().getResource("/gui/help/back2.png"))));
        } catch (IOException ex) {
            Logger.getLogger(HelpPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        bSimu.setBounds(10, 15, 285, 200);
        bEdit.setBounds(10, 265, 285, 200);
        bBack.setBounds(10, 525, 285, 100);
        
        addButtonListeners();
        
        helpItems = new JPanel();
        helpItems.setLayout(null);
        helpItems.setBounds(10, 10, 305, 640);
        helpItems.setBackground(new Color(30, 30, 30));
        helpItems.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
        
        helpItems.add(bSimu);
        helpItems.add(bEdit);
        helpItems.add(bBack);
        add(helpItems);
    }
    
    private void addButtonListeners() {
        bSimu.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
                    showGuide("simulation");
                } catch (IOException ex) {
                    Logger.getLogger(HelpPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        bEdit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
                    showGuide("mapping");
                } catch (IOException ex) {
                    Logger.getLogger(HelpPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        bBack.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Display.currentPanel = Display.menuPanel;
                Display.updatePanelState();
                contentPane.setViewportView(startup);
            }
        });
    }
    
    JScrollPane contentPane;
    JLabel helpSim, helpMap, startup;
    private void initContent() {
        contentPane = new JScrollPane();
        contentPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        contentPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
        contentPane.setBackground(new Color(20, 20, 20));
        contentPane.getViewport().setBackground(new Color(30, 30, 30));
        contentPane.getVerticalScrollBar().setBackground(Color.darkGray);
        contentPane.setBounds(325, 10, 665, 640);
        
        try {
            helpSim = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource("/gui/help/simHelp.png"))));
            helpMap = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource("/gui/help/mapHelp.png"))));
            startup = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource("/gui/help/startup.png"))));
        } catch (IOException ex) {
            Logger.getLogger(HelpPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        contentPane.setViewportView(startup);
        
        add(contentPane);
    }
    
    
    private void showGuide(String guide) throws IOException {
        if(guide.equals("simulation")) {
           contentPane.setViewportView(helpSim);
        } else if(guide.equals("mapping")) {
           contentPane.setViewportView(helpMap);
        } else
            contentPane.setViewportView(startup);
    }
}