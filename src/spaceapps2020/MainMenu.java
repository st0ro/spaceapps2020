package spaceapps2020;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import spaceapps2020.ui.ImageComponent;

public class MainMenu extends EventBasedState{

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        components.add(new ImageComponent())
    }

    @Override
    public int getID() {
        return 0;
    }

}
