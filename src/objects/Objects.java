package objects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import math.Vector2D;

public abstract class Objects {
    protected BufferedImage texture;
    protected Vector2D position;
    protected Integer color;
    protected boolean colisionA;
    protected boolean colisionB;
    protected int turnId;

    public Objects(
        Vector2D position,
        BufferedImage texture,
        Integer color,
        boolean colisionA,
        boolean colisionB,
        int turnId
    ){
        this.position = position;
        this.texture = texture;
        this.color = color;
        this.colisionA = colisionA;
        this.colisionB = colisionB;
        this.turnId = turnId;
    }

    public abstract void update();
    public abstract void draw(Graphics g);

    public Vector2D getPosition(){
        return position;
    }

    public void setPosition(Vector2D position){
        this.position = position;
    }
    
}
