package spaceapps2020.navstate;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import spaceapps2020.EventBasedState;
import spaceapps2020.NotTouhou;
import spaceapps2020.ui.Button;
import spaceapps2020.ui.ShapeComponent;

public class NavState extends EventBasedState {
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

        Button debris_one = new Button(gameContainer,1800,400,500,500,new Image("assets/nav/thorabledeb.png")){
            @Override
            public void onLeftClick(){System.out.println("dick");}
        };
        components.add(debris_one);

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }
}
