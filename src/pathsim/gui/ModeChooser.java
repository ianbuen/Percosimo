package pathsim.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import pathsim.Display;

public class ModeChooser extends JDialog {
    
    JButton sim, back, map;
    
    public ModeChooser() {
        initFrame();
    }
    
    private void initFrame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setUndecorated(true); 
        initButtons();
        setLocationRelativeTo(null);
        setLocation(getX(), getY() - 5);
        setVisible(true);
        setAlwaysOnTop(true);
    }
    
    private void initButtons() {
        sim = new JButton();
        back = new JButton();
        map = new JButton();
        
        sim.setBounds(10, 10, 256, 256);
        back.setBounds(276, 10, 256, 256);
        map.setBounds(542, 10, 256, 256);
        
        sim.setContentAreaFilled(false);
        sim.setBorderPainted(false);
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        map.setContentAreaFilled(false);
        map.setBorderPainted(false);
        
        sim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Display.currentPanel = Display.mainPanel;
                Display.updatePanelState();
                dispose();
            }
        });
        
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dispose();
            }
        });
        
        map.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Display.currentPanel = Display.editPanel;
                Display.updatePanelState();
                dispose();
            }
        });
            
        try {            
            sim.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/gui/mode/simulation.png"))));
            sim.setPressedIcon(new ImageIcon(ImageIO.read(getClass().getResource("/gui/mode/simulation2.png"))));
            back.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/gui/mode/back.png"))));
            back.setPressedIcon(new ImageIcon(ImageIO.read(getClass().getResource("/gui/mode/back2.png"))));
            map.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/gui/mode/mapmaking.png"))));
            map.setPressedIcon(new ImageIcon(ImageIO.read(getClass().getResource("/gui/mode/mapmaking2.png"))));
        } catch (IOException ex) {
            Logger.getLogger(ModeChooser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(new Color(20, 20, 20));
        btnPanel.setPreferredSize(new Dimension(808, 276));
        btnPanel.setMinimumSize(new Dimension(808, 276));
        btnPanel.setMaximumSize(new Dimension(808, 276));
        btnPanel.setLayout(null);
        btnPanel.add(sim);
        btnPanel.add(back);
        btnPanel.add(map);
        add(btnPanel);
        pack();
    }
    
}