package math;

public class Vector2D {
    private double x,y;
    private boolean running = true;
    private int turnId;
    int loop = 0;


    public Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Vector2D(){
        x=0;
        y=0;
    }

    public double getX(){
        return x;
    }

    public void setStatus(boolean running){
        this.running = running;
    }

    public boolean getStatus(boolean running){
        return running;
    }

    public void setX(double x){
        this.x = x;
    }

    public double getY(){
        return y;
    }
    
    public void turn(int num, double xD, double yD){
        if(num == 0)
            loop = 0;
        loop++;
        switch(loop){
            case 1:
                if(this.x > 0 && y > 0){
                    this.x = x - 40;
                    this.y = y - 40;
                }else{
                    loop--;
                }
                return;
            case 2:
                if(this.x > 0 && y < 480){
                    this.x = x - 40;
                    this.y = y + 40;
                }else{
                    loop--;
                }
                return;
            case 3:
                if(this.x < 280 && this.y < 480){
                    this.x = x + 40;
                    this.y = y + 40;
                }else{
                    loop--;
                }
                return;
            case 4:
                if(this.x < 280 && this.y > 0){
                    this.x = x + 40;
                    this.y = y - 40;
                    loop = 0;
                }else{
                    loop--;
                }
                return;
        }
    }

    public void setY(double y){
        this.y = y;
    }

    public double getMagnitude(){
        return Math.sqrt(x*x + y*y);
    }

    public Vector2D setDirection(double angle){
        return new Vector2D(Math.cos(angle)*getMagnitude(), Math.sin(angle)*getMagnitude());
    }

    
    public Vector2D add(Vector2D v){
        return new Vector2D(x + v.getX(), y+v.getY());
    }

    public Vector2D scale(double value){
        return new Vector2D(x*value, y*value);
    }

    public void limit(double value){
        if(x>value)
            x=value;
        if(x< -value)
            x = -value;
        if(y>value)
            y=value;
        if(y< -value)
            y = -value;
    }
}
