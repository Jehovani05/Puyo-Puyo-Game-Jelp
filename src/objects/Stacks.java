package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.text.Position;

import graphics.Assets;
import math.Vector2D;


public class Stacks{
    private Player componentA;
    private Assets assets;
    private Player componentB;
    private boolean positionTrigger;
    private int[] lastPositionY;
    private int timer = 0;
    private boolean triggerA, triggerB;
    private boolean triggerAdown, triggerBdown;
    private int[][] stack;
    private int Ay = 0;
    private int Ax = 0;
    private int By = 0;
    private int Bx = 0;

    private int combo = 1;
    private int comboY = 1;

    private long SCORE = 0;
    

    public Stacks(Player blockA, Player blockB, boolean running){
        stack = new int[7][13];
        lastPositionY = new int[13];
        assets = new Assets();
        componentA = blockA;
        componentB = blockB;
        positionTrigger = false;
    }
    
    public void update(){
        Ay = (int)componentA.position.getY()/40;
        Ax = (int)componentA.position.getX()/40;
        By = (int)componentB.position.getY()/40;
        Bx = (int)componentB.position.getX()/40;
                
        if(Ay <= 12){
            if(Ax > 0){
                if(stack[Ax-1][Ay] > 0)
                    componentA.colisionA = true;
                    if(componentA.colisionA){
                        componentB.colisionA = true;
                    }
                    if(stack[Ax-1][Ay] == 0){
                        componentA.colisionA = false;
                        if(!componentA.colisionA){
                            componentB.colisionA = false;
                        }
                    }
            }


            if(Ay == 12 && stack[Ax][Ay] == 0){
                stack[Ax][Ay] = componentA.color + 1;
                triggerA = true;
                componentA.colisionA = true;
                componentA.colisionB = true;
                componentB.colisionA = true;
                componentB.colisionB = true;
                

            }
            if(stack[Ax][Ay] == 0 && stack[Ax][Ay+1] > 0 ){
                stack[Ax][Ay] = componentA.color + 1;
                triggerA = true;
                componentA.colisionA = true;
                componentA.colisionB = true;
                componentB.colisionA = true;
                componentB.colisionB = true;
            }
        }
        if(By <= 12){
            if(Bx < 6){
                if(stack[Bx+1][By] > 0){
                    componentB.colisionB = true;
                    if(componentB.colisionB)
                        componentA.colisionB = true;
                }
                if(stack[Bx+1][By] == 0){
                    componentB.colisionB = false;
                    if(!componentB.colisionB)
                        componentA.colisionB = false;
                }
            }

            if(By == 12 && stack[Bx][By] == 0){
                stack[Bx][By] = componentB.color + 1;
                triggerB = true;
                componentA.colisionA = true;
                componentA.colisionB = true;
                componentB.colisionA = true;
                componentB.colisionB = true;
            }
            if(stack[Bx][By] == 0 && stack[Bx][By+1] > 0 ){
                stack[Bx][By] = componentB.color + 1;
                triggerB = true;
                componentA.colisionA = true;
                componentA.colisionB = true;
                componentB.colisionA = true;
                componentB.colisionB = true;
            }
        }

        if(triggerB && triggerA){
            Random r = new Random();
            int colorChangeA = r.nextInt(4);
            int colorChangeB = r.nextInt(4);
            componentA.texture = assets.typeFig(colorChangeA);
            componentA.color = colorChangeA;
            
            componentB.texture = assets.typeFig(colorChangeB);
            componentB.color = colorChangeB;

            componentA.position.setX(80);
            componentA.position.setY(0);
            componentB.position.setX(120);
            componentB.position.setY(0);

            triggerA = false;
            triggerB = false;
        }
    }

    public void deleteBlocksX(int columna, int fila){
        if(columna < 6){
            if(stack[columna][fila] == stack[columna+1][fila] && stack[columna][fila] != 0)
                combo ++;       

            if(stack[columna][fila] != stack[columna+1][fila] || stack[columna][fila] == 0){
                if(combo >= 4){
                    SCORE = SCORE + ((10+combo)*combo);
                    for(int i=0; i< combo; i++){
                        if(columna == 0){
                            int limitY = 12;
                            if(fila>0)
                                limitY = fila-1;
                            for(int consec = limitY; consec > 0; consec --){
                                if(stack[6-i][consec] > 0)
                                    stack[6-i][consec] = stack[6-i][consec-1];
                            }
                        }
                    }

                    for(int i=0; i< combo; i++){
                        if(columna >= 0){
                            for(int consec = fila; consec > 0; consec --){
                                if(columna-i > -1){
                                    if(stack[columna-i][consec] > 0){
                                        stack[columna-i][consec] = stack[columna-i][consec-1];
                                    }
                                }
                            }
                        }
                    }
                }                
                combo = 1;
            }
        }
    }


    public void deleteBlocksY(int fila){
        if(fila-1>0){
            for(int columna = 6; columna > 0; columna--){
                if(stack[columna][fila-1] == stack[columna][fila] && stack[columna][fila] != 0){
                    comboY++;
                }
                
                if(stack[columna][fila-1] != stack[columna][fila] || stack[columna][fila] == 0){
                    if(comboY > 4){
                        System.out.println(columna + ", " +(fila-1));
                        System.out.println(comboY);
                    }
                    comboY =1;
                }
            }            
            //         return;

            //     // if(stack[columna][fila] == 0){
            //     //     comboY = 0;
            //     // }

            //         // System.out.println(columna + ", " + (fila+1));
            //         // System.out.println(comboY);
            // }
        }

    }

    public void draw(Graphics g){
        for(int f = 0; f < 13; f++){
            // deleteBlocksY(f);
            for(int c = 0; c < 7; c++){
                deleteBlocksX(c, f);
                if(stack[c][f] != 0){
                    BufferedImage imgRandomA = assets.typeFig(stack[c][f] - 1);
                    BufferedImage imgRandomB = assets.typeFig(stack[c][f] - 1);
                    g.drawImage(imgRandomA, c*40, f*40, null);
                    g.drawImage(imgRandomB, c*40, f*40, null);
                    lastPositionY[f] = c;
                }
            }
        }
        
        g.setColor(Color.red);
        g.drawString("SCORE:", 10, 545);
        String label = String.valueOf(SCORE);
        g.drawString(label, 60, 545);
    }
    
}
