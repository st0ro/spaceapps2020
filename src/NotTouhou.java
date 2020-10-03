import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class NotTouhou extends StateBasedGame {

    public static final int WIDTH = 1920, HEIGHT = 1080;

    public NotTouhou() {
        super("Not Touhou");
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {

    }

    public static void main(String args[]){
        try {
            AppGameContainer app = new AppGameContainer(new NotTouhou()); //create game in container
            app.setDisplayMode(WIDTH, HEIGHT, true); //set window size and if fullscreen
            app.setTargetFrameRate(120);
            app.setShowFPS(true);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace(); //in case of failure to start game, will print error
        }
    }
}
