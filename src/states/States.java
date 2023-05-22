package states;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Stack;

import graphics.Assets;
import math.Vector2D;
import objects.Player;
import objects.Stacks;

public class States {
    private Player blockA;
    private Player blockB;
    private Stacks stack;
    private Assets assets;
    public BufferedImage assetRandomA, assetRandomB;
    public boolean running;
    public int turnId;

    public States(){
        assets = new Assets();
        Random r = new Random();
        int colorA = r.nextInt(4);
        int colorB = r.nextInt(4);
        assetRandomA = assets.typeFig(colorA);
        assetRandomB = assets.typeFig(colorB);
        blockA = new Player(
            new Vector2D(80, 0),
            new Vector2D(),
            1000,
            assetRandomA,
            "a",
            colorA,
            false,
            false,
            turnId
        );
        blockB = new Player(
            new Vector2D(120, 0),
            new Vector2D(),
            1000, 
            assetRandomB,
            "b",
            colorB,
            false,
            false,
            turnId
        );
        stack  = new Stacks(blockA, blockB, running);
    }
    
    public void update(){
        blockA.update();
        blockB.update();
        stack.update();
    }

    public void draw(Graphics g){
        blockA.draw(g);
        blockB.draw(g);
        stack.draw(g);
    }
}
