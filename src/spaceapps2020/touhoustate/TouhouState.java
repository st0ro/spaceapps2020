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
    public static final boolean DEBUG = true;

    Spaceship ship;
    List<Laser> laserList = new ArrayList<>();
    List<Debris> debrisList = new ArrayList<>();
    @Override
    public int getID() {
        return 2;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        Spaceship.init();
        Laser.init();
        Debris.init();

        ship = new Spaceship();
        debrisList.add(new Debris(960, 0));
        debrisList.add(new Debris(960, 0));
        debrisList.add(new Debris(960, 0));
        debrisList.add(new Debris(960, 0));
        debrisList.add(new Debris(960, 0));
        debrisList.add(new Debris(960, 0));
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        ship.render(graphics);
        for(Laser l:laserList){
            l.render(graphics);
        }
        for(Debris d:debrisList){
            d.render(graphics);
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
        ship.update(gameContainer, delta);
        updateAndRemove(gameContainer, delta, laserList);
        updateAndRemove(gameContainer, delta, debrisList);
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

    private void updateAndRemove(GameContainer gameContainer, int delta, List list){
        for(int i = 0; i < list.size();){
            TouhouObject t = (TouhouObject) list.get(i);
            t.update(gameContainer, delta);
            if(isOutOfBounds(t)) {
                list.remove(i);
            }else{
                i++;
            }
        }
    }
}
