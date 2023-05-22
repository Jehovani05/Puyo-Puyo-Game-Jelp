package graphics;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;

public class Assets {
    public static BufferedImage blockBlue;
    public static BufferedImage blockGreen;
    public static BufferedImage blockRed;
    public static BufferedImage blockYellow;
    
    public static void init(){
        blockBlue   = Loader.ImageLoader("/assets/puyo_blue.png");
        blockGreen  = Loader.ImageLoader("/assets/puyo_green.png");
        blockRed    = Loader.ImageLoader("/assets/puyo_red.png");
        blockYellow = Loader.ImageLoader("/assets/puyo_yellow.png");        
    }

    public BufferedImage typeFig(int color){
        switch(color){
            case 0:
                return blockBlue;
            case 1:
                return blockGreen;
            case 2:
                return blockRed;
            case 3:
                return blockYellow;
            default:
                return null;
        }
    }
}
