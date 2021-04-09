package PRRR;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.scene.input.KeyCode;


public class PrinceGame extends GameApplication {

    private Entity player;
    private PhysicsComponent physics;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(1920);
        settings.setHeight(1080);
        settings.setMainMenuEnabled(true);
        settings.setTitle("Prince of Jank");
        settings.setVersion("0.1.2");
        settings.setSceneFactory(new UISceneFactory());
        settings.setTicksPerSecond(60);
        settings.setEntityPreloadEnabled(true);
    }

    @Override
    public void initGame() {
        FXGL.getGameWorld().addEntityFactory(new PrinceFactory());
        FXGL.setLevelFromMap("prince.tmx");

        player = FXGL.getGameWorld().spawn("player", 50, 50);

    }

    @Override
    protected void initInput() {
        FXGL.getInput().addAction(new UserAction("left") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponent.class).left();

            }
        }, KeyCode.A);

        FXGL.getInput().addAction(new UserAction("right") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponent.class).right();

            }
        }, KeyCode.D);

        FXGL.getInput().addAction(new UserAction("jump") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponent.class).jump();

            }
        }, KeyCode.SPACE);

    }

    public static void main(String [] args) {
        launch(args);
    }
}
