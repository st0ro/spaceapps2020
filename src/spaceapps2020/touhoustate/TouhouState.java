package spaceapps2020.touhoustate;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class TouhouState extends BasicGameState {
    public static final boolean DEBUG = false;

    boolean gameRunning = true;
    Spaceship ship;
    BigAsteroid bigAsteroid;
    List<Laser> laserList = new ArrayList<>();
    List<Debris> debrisList = new ArrayList<>();
    List<Asteroid> asteroidList = new ArrayList<>();
    List<DebrisPickup> debrisPickupList = new ArrayList<>();
    Image explosionImg;
    Image shieldImg;
    int debrisCollected, debrisNeeded = 40;
    int health = 6;
    int invulTimer = 0;
    Color barColor;
    Image loseImg;
    Image winImg;
    boolean hasWon = false;


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
        shieldImg =  new Image("assets/touhou/shield.png", false, Image.FILTER_NEAREST);
        bigAsteroid = new BigAsteroid();
        barColor = new Color(0x47bc4f);
        loseImg = new Image("assets/touhou/LossScreen.png", false, Image.FILTER_NEAREST);
        winImg = new Image("assets/touhou/VictoryScreen.png", false, Image.FILTER_NEAREST);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        starManager.render(graphics);
        ship.render(graphics, invulTimer);
        if (bigAsteroid != null)
            bigAsteroid.render(graphics);
        for (Laser l : laserList) {
            l.render(graphics);
        }
        for (Debris d : debrisList) {
            d.render(graphics);
        }
        for (Asteroid s : asteroidList) {
            s.render(graphics);
        }
        for (DebrisPickup dp : debrisPickupList) {
            dp.render(graphics);
        }

        ship.render(graphics);


        for(int i = 0; i < health; i++){
            shieldImg.draw(50 + i * 80, 900, 7.5f);
        }
        graphics.setColor(barColor);
        graphics.fillRect(50, 1000, (float)debrisCollected/debrisNeeded * 1820f, 40);

        if (!gameRunning) {
            if (hasWon) {
                winImg.draw(200, 190, 7.5f);
            }
            else {
                explosionImg.draw(ship.hitbox.getX() - 20, ship.hitbox.getY() - 20, 7.5f);
                loseImg.draw(200, 190, 7.5f);
            }
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
        if (gameRunning) {
            if (invulTimer > 0) {
                invulTimer -= delta;
                invulTimer = Math.max(invulTimer, 0);
            }
            for (int i = 0; i < delta; i++) {
                spawn();
            }
            starManager.update(delta);
            ship.update(gameContainer, delta);
            updateAndRemove(gameContainer, delta, laserList);
            updateAndRemove(gameContainer, delta, debrisList);
            updateAndRemove(gameContainer, delta, debrisPickupList);
            updateAndRemove(gameContainer, delta, asteroidList);
            bigAsteroid.update(gameContainer, delta);
            ListIterator<Laser> liLaser = laserList.listIterator();
            while (liLaser.hasNext()) {
                Laser l = liLaser.next();
                ListIterator<Debris> liDebris = debrisList.listIterator();
                while (liDebris.hasNext()) {
                    Debris d = liDebris.next();
                    if (l.isColliding(d)) {
                        debrisPickupList.add(new DebrisPickup(d.hitbox.getX(), d.hitbox.getY()));
                        liLaser.remove();
                        liDebris.remove();
                        break;
                    }
                }
            }
            if (invulTimer == 0) {
                for (Debris d : debrisList) {
                    if (ship.isColliding(d)) {
                        damageTaken(1);
                    }
                }
                for (TouhouObject t : asteroidList) {
                    if (ship.isColliding(t)) {
                        damageTaken(1);
                    }
                }
                if (bigAsteroid.isColliding(ship)) {
                    damageTaken(3);
                }
            }
            if (health <= 0 ) {
                gameRunning = false;
            }
            if (debrisCollected >= debrisNeeded) {
                gameRunning = false;
                hasWon = true;
            }

            ListIterator<DebrisPickup> li = debrisPickupList.listIterator();
            while (li.hasNext()) {
                if (li.next().isColliding(ship)) {
                    li.remove();
                    debrisCollected++;
                }
            }
        }

        else {
            if(gameContainer.getInput().isKeyDown(Input.KEY_SPACE)){
                stateBasedGame.enterState(1);
            }
        }
    }

    @Override
    public void keyPressed(int key, char c) {
        if (key == Input.KEY_SPACE) {
            laserList.add(new Laser(ship.hitbox.getX() + 25, ship.hitbox.getY()));
        }
    }

    private boolean isOutOfBounds(TouhouObject t) {
        return t.hitbox.getMinX() > 2920 || t.hitbox.getMaxX() < -1000 || t.hitbox.getMinY() > 2080 || t.hitbox.getMaxY() < -1000;
    }

    private void updateAndRemove(GameContainer gameContainer, int delta, List list) {
        ListIterator<TouhouObject> li = list.listIterator();
        while (li.hasNext()) {
            TouhouObject t = li.next();
            t.update(gameContainer, delta);
            if (isOutOfBounds(t)) {
                li.remove();
            }
        }
    }

    public void spawn() {

        if (Math.random() < 0.002) {
            debrisList.add(new Debris((float) Math.random() * 1920, -100));
        }
        if (Math.random() < 0.001) {
            asteroidList.add(new Asteroid((float) Math.random() * 1920, -1000));
        }
        if (Math.random() < 0.0005 && bigAsteroid.hitbox.getY() > 5000) {
            bigAsteroid.hitbox.setY(-7000);
            bigAsteroid.hitbox.setX((float) (Math.random() * 1920));
        }
    }

    private void damageTaken(int dmg) {
        health -= dmg;
        if (health <= 0) {
            gameRunning = false;
        }
        invulTimer = 2000;
    }
}
