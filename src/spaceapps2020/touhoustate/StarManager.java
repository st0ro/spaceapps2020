package spaceapps2020.touhoustate;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import spaceapps2020.NotTouhou;
import spaceapps2020.ui.ShapeComponent;

import java.util.ArrayList;
import java.util.List;

public class StarManager {
    private List<Star> stars = new ArrayList<Star>(200);

    public StarManager() {
        for (int i = 0; i < 200; i++) {
            stars.add(new Star());
        }
    }

    public void update(int delta) {
        for (Star s: stars) {
            s.update(delta);
        }
    }

    public void render(Graphics graphics) {
        for (Star s: stars) {
            s.render(graphics);
        }
    }

}
