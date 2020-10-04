package spaceapps2020.navstate;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import spaceapps2020.EventBasedState;
import spaceapps2020.NotTouhou;
import spaceapps2020.touhoustate.StarManager;
import spaceapps2020.ui.Button;
import spaceapps2020.ui.ImageComponent;
import spaceapps2020.ui.Label;

public class NavState extends EventBasedState {
    static int initial_y = 60;
    static int initial_x = 1360;
    static int box_y = 130;
    static SpriteSheetFont pixelFontWhite;

    Image[] planetImages = new Image[4];
    int currentImageIndex = 0;

    StarManager[] managers = new StarManager[4];

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        components.add(new ImageComponent(gameContainer, "assets/nav/selectBack.png", 1600, 530,550,1000));
        Button backBtn = new Button(gameContainer, 150, 100, 225, 113, new Image("assets/nav/back.png")) {
            @Override
            public void onLeftClick() {
                stateBasedGame.enterState(0, new FadeOutTransition(Color.black, NotTouhou.FADE_TIME), new FadeInTransition(Color.black, NotTouhou.FADE_TIME));
            }
        };
        components.add(backBtn);

        Button selectBtn1 = new Button(gameContainer, 1650, 975, 500, 125) {
            @Override
            public void onLeftClick() {
                stateBasedGame.enterState(2, new FadeOutTransition(Color.black, NotTouhou.FADE_TIME), new FadeInTransition(Color.black, NotTouhou.FADE_TIME));
            }
        };
        components.add(selectBtn1);

        pixelFontWhite = new SpriteSheetFont(new SpriteSheet(new Image("assets/fontwhite.png", false, Image.FILTER_NEAREST).getScaledCopy(4f), 20, 20), ' ');
        addObjectLabel(gameContainer,"EXPLORER 7//1959-009A//PAYLOAD//MEDIUM", 0);
        addObjectLabel(gameContainer,"TELSTAR 2//1960-002D//PAYLOAD//MEDIUM", 1);
        addObjectLabel(gameContainer,"THOR ABLESTAR//1960-007C//ROCKET BODY//LARGE", 2);
        addObjectLabel(gameContainer,"VANGUARD 1//1958-002B//PAYLOAD//SMALL", 3);

        planetImages[0] = new Image("assets/explorer7pixl.png", false, Image.FILTER_NEAREST);
        planetImages[1] = new Image("assets/telstar2pixl.png", false, Image.FILTER_NEAREST);
        planetImages[2] = new Image("assets/thorablestarpixl.png", false, Image.FILTER_NEAREST);
        planetImages[3] = new Image("assets/vanguard1pixl.png", false, Image.FILTER_NEAREST);

        managers[0] = new StarManager();
        managers[1] = new StarManager();
        managers[2] = new StarManager();
        managers[3] = new StarManager();

        components.add(new ImageComponent(gameContainer, "assets/nav/pointer.png", 642, 578, 68, 68));
    }

    public void addObjectLabel(GameContainer gameContainer, String obj, int index) throws SlickException{
        String [] array = obj.split("//");
        Label name = new Label(gameContainer,"NAME: " +array[0], initial_x,initial_y,pixelFontWhite);
        Label id = new Label(gameContainer,"ID: " + array[1], initial_x,initial_y + 50,pixelFontWhite);
        Label type = new Label(gameContainer,"OBJECT TYPE: " + array[2], initial_x,initial_y + 100,pixelFontWhite);
        Label size = new Label(gameContainer,"RCS SIZE: " + array[3], initial_x,initial_y + 150,pixelFontWhite);
        components.add(name);
        components.add(id);
        components.add(type);
        components.add(size);
        components.add(new Button (gameContainer,initial_x,initial_y,500, 300){

            @Override
            public void onLeftClick() {
                currentImageIndex = index;

            }
        });
        initial_y += 220;
        box_y += 210;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame sBG, Graphics g) throws SlickException{
        managers[currentImageIndex].render(g);
        planetImages[currentImageIndex].draw(300, 250, 7.5f);
        super.render(gameContainer, sBG, g);
    }
}
