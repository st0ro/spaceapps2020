package spaceapps2020.touhoustate;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

public class DebrisPickup extends TouhouObject{
    static Image pickupImg;
    final float fallSpeed = 0.1f;

    public static void init() throws SlickException{
        pickupImg = new Image("assets/touhou/debrispickup.png", false, Image.FILTER_NEAREST);
    }

    public DebrisPickup(float x, float y){
        hitbox = new Rectangle(x, y, 60, 53);
    }

    @Override
    public void update(GameContainer container, int delta) {
        hitbox.setY(hitbox.getY() + fallSpeed * delta);
    }

    @Override
    public void render(Graphics g) {
        pickupImg.draw(hitbox.getX(), hitbox.getY(), 7.5f);
        if(TouhouState.DEBUG){
            g.setColor(Color.green);
            g.draw(hitbox);
        }
    }
}
