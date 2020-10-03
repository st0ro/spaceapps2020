package spaceapps2020.touhoustate;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import spaceapps2020.NotTouhou;

public class Star {
    Rectangle star;
    float speed;
    Color color;

    public Star() {
        star = new Rectangle((float)(NotTouhou.WIDTH*Math.random()), (float)(NotTouhou.HEIGHT*Math.random()), 8, 8);
        speed = (float)(Math.random() / 10);
        color = new Color((int)(Math.random()*55) + 200, (int)(Math.random()*55) + 200, (int)(Math.random()*55) + 200);
    }

    public void update(int delta) {
        if (star.getY() >= NotTouhou.HEIGHT) {
            star.setY(0);
            star.setX((float)(NotTouhou.WIDTH*Math.random()));
            speed = (float)(Math.random() / 10);
        }
        else {
            star.setY(star.getY() + speed * delta);
        }
    }

    public void render(Graphics graphics) {
        graphics.setColor(color);
        graphics.fill(star);
    }
}
