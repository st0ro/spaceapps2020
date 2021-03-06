package spaceapps2020.touhoustate;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Transform;


public class Debris extends TouhouObject {
    static Image[] debrisImg = new Image[5];
    float debrisXMoveSpeed = (float)(Math.random() * 2 - 1) * 0.4f;
    float debrisYMoveSpeed = (float)Math.random() * 0.4f;
    int index;
    static int[] offsetXList = {-15, 2, -8, 0, 5};
    static int[] offsetYList = {0, -11, 0, -25, -8};

    public static void init() throws SlickException{
        debrisImg[0] = new Image("assets/touhou/debris1.png", false, Image.FILTER_NEAREST);
        debrisImg[1] = new Image("assets/touhou/debris2.png", false, Image.FILTER_NEAREST);
        debrisImg[2] = new Image("assets/touhou/debris3.png", false, Image.FILTER_NEAREST);
        debrisImg[3] = new Image("assets/touhou/debris4.png", false, Image.FILTER_NEAREST);
        debrisImg[4] = new Image("assets/touhou/debris5.png", false, Image.FILTER_NEAREST);
    }

    public Debris(float x, float y){
        index = (int) (Math.random() * 5);
        if (index == 0) {
            hitbox = new Rectangle(x, y, 130, 150);
        }
        else if (index == 1) {
            hitbox = new Rectangle(x, y, 155, 140);
        }
        else if(index == 2){
            hitbox = new Rectangle(x, y, 140, 150);
        }
        else if (index == 3){
            hitbox = new Rectangle(x, y, 150, 98);
        }
        else {
            hitbox = new Rectangle(x, y, 130, 120);
        }
    }

    public void update(GameContainer container, int delta){
        hitbox.setX(hitbox.getX() + delta * debrisXMoveSpeed);
        hitbox.setY(hitbox.getY() + delta * debrisYMoveSpeed);
    }

    public void render (Graphics g){
        debrisImg[index].draw(hitbox.getX() + offsetXList[index], hitbox.getY() + offsetYList[index], 7.5f);
        if(TouhouState.DEBUG) {
            g.setColor(Color.red);
            g.draw(hitbox);
        }
    }
}