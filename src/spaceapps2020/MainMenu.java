package spaceapps2020;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import spaceapps2020.ui.Button;
import spaceapps2020.ui.ImageComponent;

public class MainMenu extends EventBasedState{

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        components.add(new ImageComponent(gameContainer, "assets/mainscreen/title_screen.png", NotTouhou.WIDTH/2, NotTouhou.HEIGHT/2, NotTouhou.WIDTH, NotTouhou.HEIGHT));
        Button playBtn = new Button(gameContainer, 1400, 900, 330, 120, new Image("assets/mainscreen/playbtn.png")) {
            @Override
            public void onLeftClick() {
                stateBasedGame.enterState(2);
            }
        };
        components.add(playBtn);
    }

    @Override
    public int getID() {
        return 0;
    }

}
