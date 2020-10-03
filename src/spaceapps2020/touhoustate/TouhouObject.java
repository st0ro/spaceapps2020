package spaceapps2020.touhoustate;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

public abstract class TouhouObject {
    Shape hitbox;
    public abstract void update(GameContainer container, int delta);
    public abstract void render(Graphics g);
    public boolean isColliding(TouhouObject other){
        return hitbox.intersects(other.hitbox);
    }
}
