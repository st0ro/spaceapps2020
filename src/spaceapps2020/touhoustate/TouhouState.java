package spaceapps2020.touhoustate;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class TouhouState extends BasicGameState {
    public static final boolean DEBUG = true;

    boolean gameRunning = true;
    Spaceship ship;
    BigAsteroid bigAsteroid;
    List<Laser> laserList = new ArrayList<>();
    List<Debris> debrisList = new ArrayList<>();
    List<Asteroid> asteroidList = new ArrayList<>();
    List<DebrisPickup> debrisPickupList = new ArrayList<>();
    Image explosionImg;
    int debrisCollected;
    StarManager starManager = new StarManager();
    @Override
    public int getID() {
        return 2;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        Spaceship.init();
        Laser.init();
        Debris.init();
        Asteroid.init();
        DebrisPickup.init();
        BigAsteroid.init();

        ship = new Spaceship();
        explosionImg = new Image("assets/touhou/explosion.png", false, Image.FILTER_NEAREST);
        debrisList.add(new Debris(960, 0));
        debrisList.add(new Debris(960, 0));
        debrisList.add(new Debris(960, 0));
        debrisList.add(new Debris(960, 0));
        debrisList.add(new Debris(960, 0));
        debrisList.add(new Debris(960, 0));
        asteroidList.add(new Asteroid(960, 0));
        asteroidList.add(new Asteroid(960, 0));
        asteroidList.add(new Asteroid(960, 0));
        asteroidList.add(new Asteroid(960, 0));

        debrisList.add(new Debris((float)Math.random()*960, 0));
        debrisList.add(new Debris((float)Math.random()*960, 0));
        debrisList.add(new Debris((float)Math.random()*960, 0));
        debrisList.add(new Debris((float)Math.random()*960, 0));
        debrisList.add(new Debris((float)Math.random()*960, 0));
        debrisList.add(new Debris((float)Math.random()*960, 0));
        bigAsteroid = new BigAsteroid();
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        starManager.render(graphics);
        ship.render(graphics);
        bigAsteroid.render(graphics);
        for(Laser l:laserList){
            l.render(graphics);
        }
        for(Debris d:debrisList){
            d.render(graphics);
        }
        for(Asteroid s:asteroidList){
            s.render(graphics);
        }
        for(DebrisPickup dp:debrisPickupList){
            dp.render(graphics);
        }
        ship.render(graphics);
        if(!gameRunning){
            explosionImg.draw(ship.hitbox.getX() - 20, ship.hitbox.getY() - 20, 7.5f);
        }
        graphics.drawString("Debris collected: " + debrisCollected, 100, 100);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
        if(gameRunning) {
            ship.update(gameContainer, delta);
            updateAndRemove(gameContainer, delta, laserList);
            updateAndRemove(gameContainer, delta, debrisList);
            updateAndRemove(gameContainer, delta, debrisPickupList);
            updateAndRemove(gameContainer, delta, asteroidList);
            ListIterator<Laser> liLaser = laserList.listIterator();
            while(liLaser.hasNext()){
                Laser l = liLaser.next();
                ListIterator<Debris> liDebris = debrisList.listIterator();
                while(liDebris.hasNext()){
                    Debris d = liDebris.next();
                    if(l.isColliding(d)){
                        debrisPickupList.add(new DebrisPickup(d.hitbox.getX(), d.hitbox.getY()));
                        liLaser.remove();
                        liDebris.remove();
                    }
                }
            }
            for(Debris d:debrisList){
                if(ship.isColliding(d)){
                    gameRunning = false;
                }
            }
            ListIterator<DebrisPickup> li = debrisPickupList.listIterator();
            while(li.hasNext()){
                if(li.next().isColliding(ship)){
                    li.remove();
                    debrisCollected++;
                }
            }
        }
        starManager.update(delta);
        ship.update(gameContainer, delta);
        bigAsteroid.update(gameContainer, delta);
        updateAndRemove(gameContainer, delta, laserList);
        updateAndRemove(gameContainer, delta, debrisList);
        updateAndRemove(gameContainer, delta, asteroidList);
    }

    @Override
    public void keyPressed(int key, char c){
        if(key == Input.KEY_SPACE){
            laserList.add(new Laser(ship.hitbox.getX() + 25, ship.hitbox.getY()));
        }
    }

    private boolean isOutOfBounds(TouhouObject t){
        return t.hitbox.getMinX() > 1920 || t.hitbox.getMaxX() < 0 || t.hitbox.getMinY() > 1080 || t.hitbox.getMaxY() < 0;
    }

    private void updateAndRemove(GameContainer gameContainer, int delta, List list){
        ListIterator<TouhouObject> li = list.listIterator();
        while(li.hasNext()){
            TouhouObject t = li.next();
            t.update(gameContainer, delta);
            if(isOutOfBounds(t)){
                li.remove();
            }
        }
    }
}
