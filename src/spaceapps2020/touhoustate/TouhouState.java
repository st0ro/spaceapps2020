package spaceapps2020.touhoustate;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.List;

public class TouhouState extends BasicGameState {
    public static final boolean DEBUG = false;

    List<Laser> laserList = new ArrayList<>();
    Spaceship ship;
    Debris debris;
    @Override
    public int getID() {
        return 2;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        Spaceship.init();
        Laser.init();

        ship = new Spaceship();
        debris = new Debris();
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        ship.render(graphics);
        for(Laser l:laserList){
            l.render(graphics);
        }
        graphics.drawString("Lasers alive: " + laserList.size(), 100, 100);
        debris.render(graphics);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
        ship.update(gameContainer, delta);
        for(int i = 0; i < laserList.size();){
            Laser l = laserList.get(i);
            l.update(gameContainer, delta);
            if(isOutOfBounds(l)) {
                laserList.remove(i);
            }else{
                i++;
            }
        }
        debris.update(gameContainer, delta);
    }

    @Override
    public void keyPressed(int key, char c){
        if(key == Input.KEY_SPACE){
            laserList.add(new Laser(ship.hitbox.getX() + 45, ship.hitbox.getY()));
        }
    }

    private boolean isOutOfBounds(TouhouObject t){
        return t.hitbox.getMinX() > 1920 || t.hitbox.getMaxX() < 0 || t.hitbox.getMinY() > 1080 || t.hitbox.getMaxY() < 0;
    }
}
