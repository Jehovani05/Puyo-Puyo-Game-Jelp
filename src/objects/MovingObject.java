package objects;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import math.Vector2D;

public abstract class MovingObject extends Objects{
    protected Vector2D velocity;
    protected AffineTransform at;
    protected double angle;
    protected double maxVel;
    protected int turnId;

    public MovingObject(
        Vector2D position,
        Vector2D velocity,
        double maxVel,
        BufferedImage texture,
        Integer color,
        boolean colisionA,
        boolean colisionB,
        int turnId
    ){
        super(position, texture, color, colisionA, colisionB, turnId);
        this.velocity = velocity;
        this.turnId = turnId;

        angle = 0;
    }
}
