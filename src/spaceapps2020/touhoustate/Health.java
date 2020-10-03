package spaceapps2020.touhoustate;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Health extends TouhouObject{
    static Image shield;
    private int x = 100;
    private int y = 100;

    public Health(int x, int y){
        setX(x);
        setY(y);
    }

    public static void init() throws SlickException{
        shield =  new Image("assets/touhou/shield.png", false, Image.FILTER_NEAREST);
    }

    @Override
    public void update(GameContainer container, int delta) {

    }

    public void setX(int num){this.x = num;}
    public void setY(int num){this.y = num;}


    @Override
    public void render(Graphics g) {
        shield.draw(x,y,3f);
    }
}
