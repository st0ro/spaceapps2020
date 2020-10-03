package spaceapps2020.touhoustate;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

public class Spaceship extends TouhouObject{
    static Image shipImg[] = new Image[3];
    final float moveSpeed = 0.6f;
    static Color transColor;
    int direction;
    public Spaceship() {
        hitbox = new Rectangle(885, 800, 110, 58);
    }
    public static void init() throws SlickException{
        shipImg[0] = new Image("assets/touhou/shipleft.png", false, Image.FILTER_NEAREST);
        shipImg[1] = new Image("assets/touhou/ship.png", false, Image.FILTER_NEAREST);
        shipImg[2] = new Image("assets/touhou/shipright.png", false, Image.FILTER_NEAREST);
        transColor = new Color(255, 255, 255, 100);
    }
    public void update(GameContainer container, int delta){
        direction = 1;
        if(container.getInput().isKeyDown(Input.KEY_W)){
            hitbox.setY(Math.max(hitbox.getY() - delta * moveSpeed, 0));
        }
        if(container.getInput().isKeyDown(Input.KEY_S)){
            hitbox.setY(Math.min(hitbox.getY() + delta * moveSpeed, 1022));
        }
        if(container.getInput().isKeyDown(Input.KEY_A)){
            hitbox.setX(Math.max(hitbox.getX() - delta * moveSpeed, 0));
            direction--;
        }
        if(container.getInput().isKeyDown(Input.KEY_D)){
            hitbox.setX(Math.min(hitbox.getX() + delta * moveSpeed, 1810));
            direction++;
        }
    }

    public void render(Graphics g){}

    public void render (Graphics g, int invulTimer){
        Color c = Color.white;
        if(invulTimer % 400 > 200){
            c = transColor;
        }
        shipImg[direction].draw(hitbox.getX() - 20, hitbox.getY() - 20, 7.5f, c);
        if(TouhouState.DEBUG){
            g.setColor(Color.red);
            g.draw(hitbox);
        }
    }
}
