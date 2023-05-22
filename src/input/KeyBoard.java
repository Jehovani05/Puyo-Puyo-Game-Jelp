package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoard implements KeyListener{
    private boolean[] keys = new boolean[256];
    public static boolean DOWN, LEFT, RIGHT, TURN, RESTART, START, PAUSE;

    public KeyBoard(){
        DOWN = false;
        LEFT = false;
        RIGHT = false;
        TURN = false;
        START = false;
        RESTART = false;
        PAUSE = false;
    }

    public void update(){
        DOWN = keys[KeyEvent.VK_DOWN];
        LEFT = keys[KeyEvent.VK_LEFT];
        RIGHT = keys[KeyEvent.VK_RIGHT];
        TURN = keys[KeyEvent.VK_SPACE];
        RESTART = keys[KeyEvent.VK_R];
        START = keys[KeyEvent.VK_ENTER];
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()]=true;
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()]=false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }
}
