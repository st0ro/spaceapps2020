package spaceapps2020.touhoustate;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;


public class Asteroid extends TouhouObject {
    static Image[] asteroidImg = new Image[3];
    float asteroidXMoveSpeed = (float)(Math.random() * 2 - 1) * 0.4f;
    float asteroidYMoveSpeed = (float)Math.random() * 0.4f;
    int index;

    public static void init() throws SlickException{
        asteroidImg[0] = new Image("assets/touhou/Asteroid1.png", false, Image.FILTER_NEAREST);
        asteroidImg[1] = new Image("assets/touhou/Asteroid2.png", false, Image.FILTER_NEAREST);
        asteroidImg[2] = new Image("assets/touhou/Asteroid3.png", false, Image.FILTER_NEAREST);
    }

    public Asteroid(float x, float y){
        index = (int) (Math.random() * 3);
        hitbox = new Rectangle(x, y, 130, 130);
    }

    public void update(GameContainer container, int delta){
        hitbox.setX(hitbox.getX() + delta * asteroidXMoveSpeed);
        hitbox.setY(hitbox.getY() + delta * asteroidYMoveSpeed);
    }

    public void render (Graphics g){
        asteroidImg[index].draw(hitbox.getX() - 11, hitbox.getY() - 11, 7.5f);
        if(TouhouState.DEBUG) {
            g.setColor(Color.red);
            g.draw(hitbox);
        }
    }


}