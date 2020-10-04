package spaceapps2020;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.util.FontUtils;
import spaceapps2020.touhoustate.StarManager;
import spaceapps2020.ui.Button;
import spaceapps2020.ui.ImageComponent;
import spaceapps2020.ui.Label;

public class MainMenu extends EventBasedState{

    StarManager manager;
    Font titleFont;

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        manager = new StarManager();
        Button playBtn = new Button(gameContainer, 1550, 900, 330, 120, new Image("assets/mainscreen/playbtn.png")) {
            @Override
            public void onLeftClick() {
                stateBasedGame.enterState(1, new FadeOutTransition(Color.black, NotTouhou.FADE_TIME), new FadeInTransition(Color.black, NotTouhou.FADE_TIME));
            }
        };
        components.add(playBtn);
        titleFont = new SpriteSheetFont(new SpriteSheet(new Image("assets/fontwhite.png", false, Image.FILTER_NEAREST).getScaledCopy(30f), 150, 150), ' ');
        components.add(new Label(gameContainer, "DEBRIS", 100, 100, titleFont, FontUtils.Alignment.LEFT));
        components.add(new Label(gameContainer, "DEFENDER", 100, 300, titleFont, FontUtils.Alignment.LEFT));
        components.add(new ImageComponent(gameContainer, new Image("assets/touhou/ship.png").getScaledCopy(22.5f), 600, 800));
    }

    @Override
    public void render(GameContainer container, StateBasedGame sBG, Graphics g) throws SlickException{
        manager.render(g);
        super.render(container, sBG, g);
    }

    @Override
    public int getID() {
        return 0;
    }

}
