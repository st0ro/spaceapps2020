package spaceapps2020.touhoustate;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;


public class Debris extends TouhouObject {
    Image debrisImg;
    float debrisXMoveSpeed = (float)(Math.random() * 2 - 1) * 0.7f;
    float debrisYMoveSpeed = (float)Math.random() * 0.7f;
    public Debris() throws SlickException {
        hitbox = new Rectangle(960, 0, 98, 150);
        debrisImg = new Image("assets/touhou/debris1.png", false, Image.FILTER_NEAREST);
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