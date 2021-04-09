package PRRR;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;

public class PrinceFactory implements EntityFactory {

    //Haal deze nuller niet weg, als je dit doet dan load hij geen @spawns meer.//
    @Spawns("")
    public Entity nuller(SpawnData data) {
        return new Entity();
    }

    @Spawns("tool")
    public Entity newTool(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(EntityTypes.TOOLS)
                .viewWithBBox("test.png")
                .with(new CollidableComponent(true)).with(new PhysicsComponent())
                .build();
    }

    @Spawns("door")
    public Entity newDoor(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(EntityTypes.DOOR)
                .viewWithBBox("test2.png")
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("player")
    public Entity newPlayer(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);

        return FXGL.entityBuilder()
                .type(EntityTypes.PLAYER)
                .viewWithBBox("player.png")
                .with(new CollidableComponent(true)).with(physics)
                .with(new PlayerComponent())
                .build();
    }

    @Spawns("platform")
    public Entity newPlatform(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(EntityTypes.PLATFORM)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new PhysicsComponent())
                .build();
    }

}
