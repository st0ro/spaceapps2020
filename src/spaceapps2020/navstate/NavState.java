package spaceapps2020.navstate;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import spaceapps2020.EventBasedState;
import spaceapps2020.NotTouhou;
import spaceapps2020.ui.Button;
import spaceapps2020.ui.Label;
import spaceapps2020.ui.ShapeComponent;

public class NavState extends EventBasedState {
    static int initial_y = 50;
    static int initial_x = 1420;
    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        Button backBtn = new Button(gameContainer, 100, 100, 330, 330, new Image("assets/nav/back.png")) {
            @Override
            public void onLeftClick() {
                stateBasedGame.enterState(0);
            }
        };
        components.add(backBtn);

        Button selectBtn1 = new Button(gameContainer, 700, 1000, 550, 550, new Image("assets/nav/select.png")) {
            @Override
            public void onLeftClick() {
                stateBasedGame.enterState(2);
            }
        };
        components.add(selectBtn1);

        ShapeComponent box = new ShapeComponent(gameContainer, 1650, 530,500,1000, new Color(0x00336),Color.cyan);
        components.add(box);

        addObjectLabel(gameContainer,"NAME: THOR ABLE STAR//ID: 1960-002D//OBJECT TYPE: DEBRIS//RCS SIZE: SMALL");

    }

    public void addObjectLabel(GameContainer gameContainer, String obj) throws SlickException{

        SpriteSheetFont pixelFontWhite = new SpriteSheetFont(new SpriteSheet(new Image("assets/fontwhite.png", false, Image.FILTER_NEAREST).getScaledCopy(4f), 20, 20), ' ');
        String [] array = obj.split("//");
        Label name = new Label(gameContainer,array[0], initial_x,initial_y,pixelFontWhite);
        Label id = new Label(gameContainer,array[1], initial_x,initial_y + 50,pixelFontWhite);
        Label type = new Label(gameContainer,array[2], initial_x,initial_y + 100,pixelFontWhite);
        Label size = new Label(gameContainer,array[3], initial_x,initial_y + 150,pixelFontWhite);
        components.add(name);
        components.add(id);
        components.add(type);
        components.add(size);
        initial_y += 300;
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }
}
