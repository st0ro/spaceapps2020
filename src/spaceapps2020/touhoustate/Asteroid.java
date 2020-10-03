package spaceapps2020.touhoustate;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;


public class Asteroid extends TouhouObject {
    static Image asteroidImg;
    float asteroidXMoveSpeed = (float)(Math.random() * 2 - 1) * 0.4f;
    float asteroidYMoveSpeed = (float)Math.random() * 0.4f;

    public static void init() throws SlickException{
        asteroidImg = new Image("assets/touhou/asteroid.png", false, Image.FILTER_NEAREST);
    }

    public Asteroid(float x, float y){
            hitbox = new Rectangle(x, y, 50, 60);
    }

    public void update(GameContainer container, int delta){
        hitbox.setX(hitbox.getX() + delta * asteroidXMoveSpeed);
        hitbox.setY(hitbox.getY() + delta * asteroidYMoveSpeed);
    }

    public void render (Graphics g){
        asteroidImg.draw(hitbox.getX() - 38, hitbox.getY() + -80, 7.5f);
        if(TouhouState.DEBUG) {
            g.setColor(Color.red);
            g.draw(hitbox);
        }
    }


}