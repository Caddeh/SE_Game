package PRRR;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.FXGLScene;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.app.scene.SceneFactory;
import org.jetbrains.annotations.NotNull;

public class UISceneFactory extends SceneFactory {

    @NotNull
    @Override
    public FXGLMenu newMainMenu() {
        return new MenuScene(MenuType.MAIN_MENU);
    }

//    public HighscoreScene startHighscoreScene(){
//        System.out.println("This is supposed to be the highscore scene");
//        return new HighscoreScene();
//    }

    public FXGLScene showWinScene() {
        return new FXGLScene() {
        };

    }
}


