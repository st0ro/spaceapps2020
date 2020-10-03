package spaceapps2020.touhoustate;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;


public class Debris extends TouhouObject {
    static Image debrisImg;
    float debrisXMoveSpeed = (float)(Math.random() * 2 - 1) * 0.7f;
    float debrisYMoveSpeed = (float)Math.random() * 0.7f;

    public static void init() throws SlickException{
        debrisImg = new Image("assets/touhou/debris1.png", false, Image.FILTER_NEAREST);
    }

    public Debris(float x, float y){
        hitbox = new Rectangle(x, y, 98, 150);
    }

    public void update(GameContainer container, int delta){
        hitbox.setX(hitbox.getX() + delta * debrisXMoveSpeed);
        hitbox.setY(hitbox.getY() + delta * debrisYMoveSpeed);
    }

    public void render (Graphics g){
        debrisImg.draw(hitbox.getX(), hitbox.getY(), 7.5f);
        if(TouhouState.DEBUG) {
            g.setColor(Color.red);
            g.draw(hitbox);
        }
    }


}