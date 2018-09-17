package pathsim.gfx;

import java.awt.image.BufferedImage;

public class Assets {
    
    public static BufferedImage
            grass,
            tree,
            boulder,
            fence,
            fenceUL,
            fenceL,
            fenceDL,
            fenceR,
            fenceUR,
            fenceDR,
            
            dirt,
            mud,
            lava,
            water,
            crater,
            sand,
            sandstone,
            sandstone2,
            sandstone3,
            cactus,
            
            plank1,
            plank2,
            plank3,
            plank4,
            wall,
            stone,
            stonebrick,
            stonebrick2,
            stonebrick3,
            stonebrick4,
            
            snow,
            snowrock,
            snowstump,
            snowtree,
            ice,
            log,
            plant,
            plant2,
            plant3,
            waterlily,
            
            bridge,
            metalfloor,
            metalwall,
            metalfloor2,
            metalwall2,
            icewall,
            icepile,
            icepit,
            icefloor,            
            
            //player,
            
            origin,
            finish,
            trail,
            marker,
            highlight,
            grid;
    
    public static int locX = -1, locY = -1;
    
    public static void init() {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/images/textures.png"));
        
        grass = sheet.crop(0, 0);
        tree = sheet.crop(1, 0);
        boulder = sheet.crop(2, 0);
        fence = sheet.crop(3, 0);
        fenceUL = sheet.crop(4, 0);
        fenceL = sheet.crop(5, 0);
        fenceDL = sheet.crop(6, 0);
        fenceR = sheet.crop(7, 0);
        fenceUR = sheet.crop(8, 0);
        fenceDR = sheet.crop(9, 0);
        
        dirt = sheet.crop(0, 1);
        mud = sheet.crop(1, 1);
        lava = sheet.crop(2, 1);
        water = sheet.crop(3, 1);
        crater = sheet.crop(4, 1);
        sand = sheet.crop(5, 1);
        sandstone = sheet.crop(6, 1);
        sandstone2 = sheet.crop(7, 1);
        sandstone3 = sheet.crop(8, 1);
        cactus = sheet.crop(9, 1);
        
        plank1 = sheet.crop(0, 2);
        plank2 = sheet.crop(1, 2);
        plank3 = sheet.crop(2, 2);
        plank4 = sheet.crop(3, 2);
        wall = sheet.crop(4, 2);
        stone = sheet.crop(5, 2);
        stonebrick = sheet.crop(6, 2);
        stonebrick2 = sheet.crop(7, 2);
        stonebrick3 = sheet.crop(8, 2);
        stonebrick4 = sheet.crop(9, 2);
        
        snow = sheet.crop(0, 3);
        snowrock = sheet.crop(1, 3);
        snowstump = sheet.crop(2, 3);
        snowtree = sheet.crop(3, 3);
        ice = sheet.crop(4, 3);
        log = sheet.crop(5, 3);
        plant = sheet.crop(6, 3);
        plant2 = sheet.crop(7, 3);
        plant3 = sheet.crop(8, 3);
        waterlily = sheet.crop(9, 3);
        
        bridge = sheet.crop(0, 4);
        metalfloor = sheet.crop(1, 4);
        metalwall = sheet.crop(2, 4);
        metalfloor2 = sheet.crop(3, 4);
        metalwall2 = sheet.crop(4, 4);
        icewall = sheet.crop(5, 4);
        icepile = sheet.crop(6, 4);
        icepit = sheet.crop(7, 4);
        icefloor = sheet.crop(8, 4);
        
        origin = sheet.crop(0, 8);
        finish = sheet.crop(1, 8);
        trail = sheet.crop(2, 8);
        marker = sheet.crop(3, 8);
        highlight = sheet.crop(4, 8);
        grid = sheet.crop(9, 4);
    }
}