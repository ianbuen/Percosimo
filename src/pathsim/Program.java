package pathsim;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import pathsim.algo.Algorithm;
import pathsim.map.Map;
import pathsim.gfx.Assets;

public class Program implements Runnable {
    
    // Main Properties
    private Display display;
    private BufferStrategy bs, bs2;
    private Graphics g, g2;
    private Thread thread;
    private boolean running = false;
    
    public static Map map = null;
    public static Algorithm algo = null;
    
    public Program(String t, int w, int h) {
        Assets.init();
        display = new Display(t, w, h);
        start();
    }
    
    public void run() {
        final double timePerTick = 1000000000D / 60D;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
		
        // Test Variables
        //long timer = 0;
        //int ticks = 0;
        
        while(running){ 
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            //timer += now - lastTime; // Test Variable
            lastTime = now;
            
            if(delta >= 1) {
               tick();
               render();
               //ticks++;
               delta--;
            }
            
            //render();
            
/*            if(timer >= 1000000000) { // Tick Test Code
               System.out.println("Ticks and Frames: " + ticks);
               ticks = 0;
               timer = 0;
            }*/
        }
        
        stop();
    }
    
    public synchronized void start() {
        if(running)	
           return;
                	
        running = true;
                
        thread = new Thread(this);
        thread.start();        
    }
    
    public synchronized void stop() {
        if(!running)		
           return;
                	
        running = false;
                
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();	
        }
    }
    
    private void tick() {
        if(Display.currentPanel == Display.mainPanel) {
           if(algo != null)
               algo.tick();
        }
    }
    
    private void render() {           
        if(Display.currentPanel == Display.mainPanel) {
           Canvas c = display.getCanvas();
           bs = c.getBufferStrategy();

           if(bs == null){		
              c.createBufferStrategy(3);
              return;
           }

           g = bs.getDrawGraphics();                    
           g.clearRect(0, 0, c.getWidth(), c.getHeight());

           if(map != null)           
              map.drawMap(g);
           
           if(algo != null)
              algo.render(g);
           
           bs.show();
           g.dispose();
           
        } else if(Display.currentPanel == Display.editPanel) {
                  Canvas c = ((pathsim.gui.panels.EditPanel)Display.editPanel).getDrawspace();
                  Canvas pal = ((pathsim.gui.panels.EditPanel)Display.editPanel).getPalette();
                  bs = c.getBufferStrategy();
                  bs2 = pal.getBufferStrategy();
                  
                  if(bs == null){		
                     c.createBufferStrategy(3);
                     return;
                  }
                  
                  if(bs2 == null){		
                     pal.createBufferStrategy(3);
                     return;
                  }

                  g = bs.getDrawGraphics();                    
                  g2 = bs2.getDrawGraphics();                    
                  
                  g.clearRect(0, 0, c.getWidth(), c.getHeight());
                  g2.clearRect(0, 0, pal.getWidth(), pal.getHeight());
                  
                  ((pathsim.gui.panels.EditPanel)Display.editPanel).render(g, g2);
                  
                  bs.show();
                  bs2.show();
                  g.dispose();
                  g2.dispose();
        }
    }
}