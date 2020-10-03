package spaceapps2020.touhoustate;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

public class Laser extends TouhouObject{
    static Image laserImg;
    final float moveSpeed = 1.6f;

    public static void init() throws SlickException {
        laserImg = new Image("assets/touhou/laser.png", false, Image.FILTER_NEAREST);
    }

    public Laser(float x, float y){
        hitbox = new Rectangle(x, y, 60, 38);
    }

    @Override
    public void update(GameContainer container, int delta) {
        hitbox.setY(hitbox.getY() - moveSpeed * delta);
    }

    @Override
    public void render(Graphics g) {
        laserImg.draw(hitbox.getX(), hitbox.getY(), 7.5f);
        if(TouhouState.DEBUG){
            g.setColor(Color.red);
            g.draw(hitbox);
        }
    }
}
