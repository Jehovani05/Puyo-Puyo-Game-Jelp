
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.sql.Time;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JTextField;

import graphics.Assets;
import input.KeyBoard;
import states.States;

public class Controller extends JFrame implements Runnable{
    // public static final int WIDTH = 100, HEIGHT = 100;
    public static final int WIDTH = 300, HEIGHT = 600;
    private Canvas canvas;
    private Thread thread;
    private Boolean running = false;
    
    private BufferStrategy bs;
    private Graphics g;

    private final int FPS = 8;
    private double TARGETTIME = 1000000000/FPS;
    private double delta = 0;
    private int AVERAGEFPS = FPS;

    private int SCORE;
    private long time = 0;
    private long seg = 0;

    private KeyBoard keyBoard;
    private States gameStates;


    public Controller(){ // constructor, inicializador de variables.
        SCORE = 0;
        setTitle("Jelp Puyo Game"); // Titulo de la ventana.
        setSize(WIDTH, HEIGHT); // Dimensión exacta de la ventana.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Permite cerrar la ventana al momento de dar clic en cerrar.
        setResizable(false); // False para no redimensionar la ventana.
        setLocationRelativeTo(null); // Para que la ventana se coloque en el centro.
        setVisible(true); // Ordenar que la ventana sea visible

        canvas = new Canvas(); // Plano donde se meteran todas las figuras del juego.
        keyBoard = new KeyBoard(); // Se cargan las funciones del escritorio.

        canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT)); // Dimensiones del canvas
        canvas.setMaximumSize(new Dimension(WIDTH, HEIGHT)); // Dimensiones del canvas
        canvas.setMinimumSize(new Dimension(WIDTH, HEIGHT)); // Dimensiones del canvas
        canvas.setFocusable(true); // Permite recibir entradas por parte del teclado.

        add(canvas); // Se añade el canvas dentro del frame.
        canvas.addKeyListener(keyBoard); // Se agregan los comando en la función para que sean escuchados.
    }

    public static void main(String args[]){
        new Controller().start();
    }

    private void update(){
        keyBoard.update();
        gameStates.update();
    }

    private void init(){
        Assets.init();
        gameStates = new States();
    }

    private void draw(){
        bs = canvas.getBufferStrategy();
        if(bs == null){
            canvas.createBufferStrategy(3);
            return;
        }

        g = bs.getDrawGraphics();

        // --------------[ Empezar a dibujar apartir de aquí ]----------------
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.red);
        String timer = String.valueOf(seg);
        g.drawString("TIME:", 150, 545);
        g.drawString(timer, 190, 545);
        gameStates.draw(g);

        // -------------------------------------------------------------------

        g.dispose();
        bs.show();
    }


    /*
     * Run se generó al momento de implementar la función Runnable.
     */
    @Override
    public void run() {
        long now = 0;
        long lastTime = System.nanoTime();
        int frames = 0;

        init();

        while(running){
            now = System.nanoTime();
            delta += (now - lastTime)/TARGETTIME;
            time += (now - lastTime);
            lastTime = now;

            if(delta >= 1){
                update();
                draw();
                delta --;
                frames ++;
                if(time >= 1000000000){
                    seg ++;
                    AVERAGEFPS = frames;
                    frames = 0;
                    time = 0;
                }
            }
        }
        stop();
    }

    private void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    

    private void stop(){
        try{
            thread.join();
            running = false;
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
