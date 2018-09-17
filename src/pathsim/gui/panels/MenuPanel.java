package pathsim.gui.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import pathsim.Display;
import pathsim.gui.ModeChooser;

public class MenuPanel extends javax.swing.JPanel {
    
    private int width, height;

    public MenuPanel(int w, int h) {
        width = w;
        height = h;
        initComponents();
    }
                        
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup = new JPanel();
        bStart = new JButton();
        bHelp = new JButton();
        bExit = new JButton();
        logo = new JLabel();
        bg = new JLabel();

        setBackground(new Color(255, 255, 255));
        setMaximumSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        setPreferredSize(new Dimension(width, height));
        setLayout(new GridBagLayout());

        buttonGroup.setMaximumSize(new Dimension(400, 375));
        buttonGroup.setMinimumSize(new Dimension(400, 375));
        buttonGroup.setOpaque(false);
        buttonGroup.setPreferredSize(new Dimension(400, 375));
        GridBagLayout jPanel3Layout = new GridBagLayout();
        jPanel3Layout.columnWidths = new int[] {0};
        jPanel3Layout.rowHeights = new int[] {0, 35, 0, 35, 0, 35, 0};
        buttonGroup.setLayout(jPanel3Layout);

        bStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/menu/start.png"))); // NOI18N
        bStart.setBorder(null);
        bStart.setContentAreaFilled(false);
        bStart.setFocusPainted(false);
        bStart.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/menu/start2.png"))); // NOI18N
        bStart.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                new ModeChooser();
            }
        });
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        buttonGroup.add(bStart, gridBagConstraints);

//        bHelp.setEnabled(false);
        bHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/menu/help.png"))); // NOI18N
        bHelp.setBorder(null);
        bHelp.setBorderPainted(false);
        bHelp.setContentAreaFilled(false);
        bHelp.setFocusPainted(false);
        bHelp.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/menu/help2.png"))); // NOI18N
        bHelp.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                Display.currentPanel = Display.helpPanel;
                Display.updatePanelState();
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        buttonGroup.add(bHelp, gridBagConstraints);

        bExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/menu/exit.png"))); // NOI18N
        bExit.setBorder(null);
        bExit.setBorderPainted(false);
        bExit.setContentAreaFilled(false);
        bExit.setFocusPainted(false);
        bExit.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/menu/exit2.png"))); // NOI18N
        bExit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                System.exit(0);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        buttonGroup.add(bExit, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 120;
        gridBagConstraints.anchor = GridBagConstraints.SOUTH;
        add(buttonGroup, gridBagConstraints);

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/menu/logo.png"))); // NOI18N
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(logo, gridBagConstraints);
        
        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/menu/bg.gif"))); // NOI18N
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(bg, gridBagConstraints);
    }                                
                 
    private javax.swing.JButton bExit;
    private javax.swing.JButton bHelp;
    private javax.swing.JButton bStart;
    private javax.swing.JPanel buttonGroup;
    private javax.swing.JLabel logo, bg;
}
