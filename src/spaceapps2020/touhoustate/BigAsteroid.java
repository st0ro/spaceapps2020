package spaceapps2020.touhoustate;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class BigAsteroid extends TouhouObject {
    static Image bigAsteroidImg;
    static Image alertImg;
    float bigAsteroidMoveSpeed = (float) 4f;
    int bigAsteroidX = (int)(Math.random() * 1920);

    public static void init() throws SlickException{
        bigAsteroidImg = new Image("assets/touhou/bigAsteroid.png", false, Image.FILTER_NEAREST);
        alertImg = new Image("assets/touhou/alert.png", false, Image.FILTER_NEAREST);
    }

    public BigAsteroid(){
        hitbox = new Rectangle(bigAsteroidX, -8000, 450, 450);
    }

    public void update(GameContainer container, int delta){
        hitbox.setY(hitbox.getY()+ delta * bigAsteroidMoveSpeed);
    }

    public void render (Graphics g){
        bigAsteroidImg.draw(hitbox.getX(), hitbox.getY(), 7.5f);
        if(hitbox.getY() < 0 && hitbox.getY() > -4500){
            alertImg.draw(hitbox.getCenterX() - 75, 20, 7.5f);
        }
        if(TouhouState.DEBUG) {
            g.setColor(Color.red);
            g.draw(hitbox);
        }
    }
}
