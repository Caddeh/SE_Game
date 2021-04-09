package PRRR;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.input.virtual.VirtualButton;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import java.util.Map;
import static com.almasb.fxgl.dsl.FXGLForKtKt.*;
import static com.sun.media.jfxmedia.logging.Logger.setLevel;


public class PrinceGame extends GameApplication {

    private Entity player;
    private static final int MAX_LEVEL = 3;
    private static final int STARTING_LEVEL = 0;

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
          player = null;
          nextLevel();


          player = spawn("player", 50, 50);
          set("player", player);

    }

    private boolean isMouseEvents = true;

    private void nextLevel() {
        if (geti("level") == MAX_LEVEL) {
            showMessage("Pure Kanker");
            return;
        }

        FXGL.inc("level", +1);

        setLevel(geti("level"));
    }

    private void setLevel(int levelNum) {
        if (player != null) {
            player.getComponent(PhysicsComponent.class).overwritePosition(new Point2D(50, 50));
            }

        Level level = FXGL.setLevelFromMap("level" + levelNum + ".tmx");
    }

    @Override
    protected void initInput() {
        FXGL.getInput().addAction(new UserAction("left") {
                                      @Override
                                      protected void onAction() {
                                          player.getComponent(PlayerComponent.class).left();

                                      }

                                      @Override
                                      protected void onActionEnd() {
                                          isMouseEvents = true;
                                          player.getComponent(PlayerComponent.class).stop();
                                      }
                                  }, KeyCode.A, VirtualButton.LEFT);


        FXGL.getInput().addAction(new UserAction("right") {
                                      @Override
                                      protected void onAction() {
                                          player.getComponent(PlayerComponent.class).right();

                                      }

                                      @Override
                                      protected void onActionEnd() {
                                          player.getComponent(PlayerComponent.class).stop();
                                      }
                                  }, KeyCode.D, VirtualButton.RIGHT);



        FXGL.getInput().addAction(new UserAction("jump") {
            @Override
            protected void onActionBegin() {
                isMouseEvents = false;
                player.getComponent(PlayerComponent.class).jump();

            }
        }, KeyCode.SPACE, VirtualButton.A);
    }

    @Override
    protected void initPhysics() {
        getPhysicsWorld().setGravity(0, 760);

        FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityTypes.PLAYER, EntityTypes.TOOLS) {
            @Override
            protected void onCollision(Entity player, Entity tools) {
                FXGL.inc("collected tools", +1);
                tools.removeFromWorld();

            }
        });

        FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityTypes.PLAYER, EntityTypes.DOOR) {
            @Override
            protected void onCollisionBegin(Entity player, Entity door) {
                nextLevel();
            }
        });
    }

    @Override
    protected void initUI() {
        Label tools = new Label("tools");
        tools.setStyle("-fx-text-fill: black");
        tools.setTranslateX(109);
        tools.setTranslateY(50);

        Label highscore = new Label("Highscore:");
        highscore.setStyle("-fx-text-fill: black");
        highscore.setTranslateX(50);
        highscore.setTranslateY(50);

        tools.textProperty().bind(FXGL.getWorldProperties().intProperty("collected tools").asString());
        FXGL.getGameScene().addUINode(tools);
        FXGL.getGameScene().addUINode(highscore);

    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("collected tools", 0);
        vars.put("level", STARTING_LEVEL);
    }



    public static void main(String [] args) {
        launch(args);
    }
}
