package spaceapps2020.touhoustate;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

public class BigAsteroid extends TouhouObject {
    static Image bigAsteroidImg;
    float bigAsteroidMoveSpeed = (float) 8f;
    int bigAsteroidX = (int)(Math.random() * 1920);

    public static void init() throws SlickException{
        bigAsteroidImg = new Image("assets/touhou/bigAsteroid.png", false, Image.FILTER_NEAREST);
    }

    public BigAsteroid(){
        hitbox = new Rectangle(bigAsteroidX, -10000, 50, 60);
    }

    public void update(GameContainer container, int delta){
        hitbox.setY(hitbox.getY()+ delta * bigAsteroidMoveSpeed);
    }

    public void render (Graphics g){
        bigAsteroidImg.draw(hitbox.getX() - 38, hitbox.getY() + -80, 7.5f);
        if(TouhouState.DEBUG) {
            g.setColor(Color.red);
            g.draw(hitbox);
        }
    }
}
