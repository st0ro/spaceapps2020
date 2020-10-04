package spaceapps2020;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import spaceapps2020.navstate.NavState;
import spaceapps2020.touhoustate.TouhouState;

public class NotTouhou extends StateBasedGame {

    public static final int WIDTH = 1920, HEIGHT = 1080;
    public static final int FADE_TIME = 100;
    public static SpriteSheetFont pixelFontBlack;
    public static SpriteSheetFont pixelFontWhite;

    public NotTouhou() {
        super("Not Touhou");
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        addState(new MainMenu());
        addState(new NavState());
        addState(new TouhouState());
        pixelFontBlack = new SpriteSheetFont(new SpriteSheet(new Image("assets/fontblack.png", false, Image.FILTER_NEAREST).getScaledCopy(7f), 35, 35), ' ');
        pixelFontWhite = new SpriteSheetFont(new SpriteSheet(new Image("assets/fontwhite.png", false, Image.FILTER_NEAREST).getScaledCopy(7f), 35, 35), ' ');
    }

    public static void main(String args[]){
        try {
            AppGameContainer app = new AppGameContainer(new NotTouhou()); //create game in container
            app.setDisplayMode(WIDTH, HEIGHT, true); //set window size and if fullscreen
            app.setTargetFrameRate(120);
            app.setShowFPS(false);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace(); //in case of failure to start game, will print error
        }
    }
}
