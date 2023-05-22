package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import graphics.Assets;
import input.KeyBoard;
import math.Vector2D;

public class Player extends MovingObject{
    private Vector2D heading;
    private Vector2D acceleration;
    private final double ACC = 0.08;
    private Assets assets;
    private String b;
    private int turnId = 0;
    private long TIMELAPSE;
    private long millis = 0;
    int color;
    
    public Player(
        Vector2D position,
        Vector2D velocity,
        double maxVel,
        BufferedImage texture,
        String blockType,
        int color,
        boolean colisionA,
        boolean colisionB,
        int turnId
    )
        {
            super(position, velocity, maxVel, texture, color, colisionA, colisionB, turnId);
            colisionA = this.colisionA;
            colisionB = this.colisionB;
            TIMELAPSE = 0;
            assets = new Assets();
            heading = new Vector2D(0, 1);   
            acceleration = new Vector2D();
            color = this.color;
            b = blockType;
    }

    @Override
    public void update(){    
        if(colisionA && colisionB){
            turnId = 0;
            this.turnId = 0;
        }
        millis++;
        if(millis == 10){
            if(position.getY() <= 480){
                // position.setY(position.getY()+40);
            }
            TIMELAPSE++;
            millis = 0;
        }
        if(KeyBoard.TURN){
            if(b=="b"){
                position.turn(turnId, position.getX(), position.getY());
                turnId = 9;
            }
        }
        if(KeyBoard.DOWN && position.getY() <= 480)
            position.setY(position.getY()+40);

        if(KeyBoard.LEFT && position.getX() > 0 && !colisionA){
            int moveUnit = 40;
            position.setX(position.getX()-moveUnit);
            colisionB = false;
        }
        if(KeyBoard.RIGHT && position.getX() < 240 && !colisionB){
            int moveUnit = 40;
            position.setX(position.getX()+moveUnit);
            colisionA = false;
        }
        
        if(KeyBoard.START)
            System.out.println("ldelsmñlf,lñd");

        // velocity = velocity.add(acceleration);
        // velocity.limit(maxVel);
        heading = heading.setDirection(angle + Math.PI/2);
        if(position.getY() >= 520){
            Random r = new Random();
            int colorChange = r.nextInt(4);
            
            position.setY(0);
            this.texture = assets.typeFig(colorChange);
            this.color = colorChange;
            if(b == "b")
                position.setX(80);
            if(b == "a")
                position.setX(40);   
        }

        // position = position.add(velocity);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(texture, (int)position.getX(), (int)position.getY(), null);
    }    
}
