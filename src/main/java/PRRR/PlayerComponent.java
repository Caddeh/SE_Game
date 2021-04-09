package PRRR;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.scene.image.Image;

public class PlayerComponent extends Component {

    private PhysicsComponent physics;
    private int jumps = 1;

    public PlayerComponent() {
        Image image = FXGL.image("player.png");
    }

    @Override
    public void onUpdate(double tpf) {

    }

    public void left() {
        getEntity().setScaleX(-1);
        physics.setVelocityX(-150);
    }

    public void right() {
        getEntity().setScaleX(1);
        physics.setVelocityX(150);
    }

    public void stop() {
        physics.setVelocityX(0);
    }

    public void jump() {
        if (jumps == 0)
            return;

        physics.setVelocityY(-200);
    }
}
