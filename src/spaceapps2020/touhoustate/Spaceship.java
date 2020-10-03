package spaceapps2020.touhoustate;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

public class Spaceship extends TouhouObject{
    static Image shipImg;
    final float moveSpeed = 0.7f;
    public Spaceship() {
        hitbox = new Rectangle(885, 800, 110, 58);
    }
    public static void init() throws SlickException{
        shipImg = new Image("assets/touhou/ship.png", false, Image.FILTER_NEAREST);
    }
    public void update(GameContainer container, int delta){
        if(container.getInput().isKeyDown(Input.KEY_W)){
            hitbox.setY(Math.max(hitbox.getY() - delta * moveSpeed, 0));
        }
        if(container.getInput().isKeyDown(Input.KEY_S)){
            hitbox.setY(Math.min(hitbox.getY() + delta * moveSpeed, 1022));
        }
        if(container.getInput().isKeyDown(Input.KEY_A)){
            hitbox.setX(Math.max(hitbox.getX() - delta * moveSpeed, 0));
        }
        if(container.getInput().isKeyDown(Input.KEY_D)){
            hitbox.setX(Math.min(hitbox.getX() + delta * moveSpeed, 1810));
        }
    }

    public void render (Graphics g){
        shipImg.draw(hitbox.getX() -20, hitbox.getY() - 20, 7.5f);
        if(TouhouState.DEBUG){
            g.setColor(Color.red);
            g.draw(hitbox);
        }
    }
}
