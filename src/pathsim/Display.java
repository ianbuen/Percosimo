package pathsim;

import java.awt.Canvas;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import pathsim.gui.panels.EditPanel;
import pathsim.gui.panels.HelpPanel;
import pathsim.gui.panels.MainPanel;
import pathsim.gui.panels.MenuPanel;

public class Display {
    
    // Main Properties
    private String title;
    private int width, height;
    
    public Display(String t, int w, int h) {
        title = t;
        width = w;
        height = h;
        
        createDisplay();
    }
    
    // Panel States
    public static JPanel currentPanel = null;
    public static JPanel menuPanel, mainPanel, helpPanel, editPanel;
    
    private void initPanels() {
        menuPanel = new MenuPanel(width, height);
        menuPanel.setName("menu");
        mainPanel = new MainPanel(width, height);
        mainPanel.setName("main");
        helpPanel = new HelpPanel(width, height);
        helpPanel.setName("help");
        editPanel = new EditPanel(width, height);
        editPanel.setName("edit");
        
        deck.add(menuPanel, menuPanel.getName());
        deck.add(mainPanel, mainPanel.getName());
        deck.add(editPanel, editPanel.getName());
        deck.add(helpPanel, helpPanel.getName());
    }
    
    public static void updatePanelState() {
        ((CardLayout)deck.getLayout()).show(deck, currentPanel.getName());
    }
    
    // GUI Components
    private JFrame frame;
    private static JPanel deck;
    
    private void createDisplay() {
        initDeckPanel();
        initPanels();
        initFrame();
    }
    
    private void initFrame() {
        frame = new JFrame(title);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(deck);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setLocation(frame.getX(), frame.getY() - 15);
    }
    
    private void initDeckPanel() {
        deck = new JPanel();
        deck.setLayout(new CardLayout());
        
        setComponentSize(deck);
    }
    
    private void setComponentSize(Component c) {
        c.setPreferredSize(new Dimension(width, height));
        c.setMinimumSize(new Dimension(width, height));
        c.setMaximumSize(new Dimension(width, height));
    }
    
    public static Canvas getCanvas() {
        return ((MainPanel)mainPanel).getCanvas();
    }
}