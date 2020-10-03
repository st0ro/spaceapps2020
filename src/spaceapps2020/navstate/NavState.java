package spaceapps2020.navstate;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import spaceapps2020.EventBasedState;
import spaceapps2020.ui.Button;

public class NavState extends EventBasedState {
    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        Button backBtn = new Button(gameContainer, 100, 100, 330, 120, new Image("assets/nav/back.png")) {
            @Override
            public void onLeftClick() {
                stateBasedGame.enterState(0);
            }
        };
        components.add(backBtn);
    }
    
    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }
}
