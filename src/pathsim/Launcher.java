package pathsim;

public class Launcher {
    
    public static final int WIDTH = 1000, HEIGHT = 660, SCALE = 1;
    public static final String TITLE = "Percosimo";
    
    public static void main(String[] args){
        new Program(TITLE, WIDTH * SCALE, HEIGHT * SCALE);
    }
}
