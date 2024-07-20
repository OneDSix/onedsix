package net.onedsix.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.math.Vector3;
import lombok.extern.slf4j.Slf4j;
import net.onedsix.assets.Attributes;
import net.onedsix.assets.entities.Entity;
import net.onedsix.assets.entities.PlayerEntity;
import net.onedsix.registry.Identifier;
import net.onedsix.util.Vars;

@Slf4j
public class ClientPlayer extends PlayerEntity {

    public ClientPlayer(Identifier<? extends Entity> ident, Attributes attr, Decal img, Vector3 position) {
        super(ident, attr, img, position);
        log.info("Successfully created player");
    }

    @Override
    public Vector3 perFrame() {
        if (Vars.keyCalls.movement) {
            if (Gdx.input.isKeyPressed(Input.Keys.UP) ||
                        Gdx.input.isKeyPressed(Input.Keys.W)) {
                this.position = new Vector3(
                        this.position.x -= (this.speed * Gdx.graphics.getDeltaTime()),
                        this.position.y,
                        this.position.z
                );
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN) ||
                        Gdx.input.isKeyPressed(Input.Keys.S)) {
                this.position = new Vector3(
                        this.position.x += (this.speed * Gdx.graphics.getDeltaTime()),
                        this.position.y,
                        this.position.z
                );
            }
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) ||
                        Gdx.input.isKeyPressed(Input.Keys.A)) {
                this.position = new Vector3(
                        this.position.x,
                        this.position.y,
                        this.position.z += (this.speed * Gdx.graphics.getDeltaTime())
                );
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) ||
                        Gdx.input.isKeyPressed(Input.Keys.D)) {
                this.position = new Vector3(
                        this.position.x,
                        this.position.y,
                        this.position.z -= (this.speed * Gdx.graphics.getDeltaTime())
                );
            }
        }

        // Needs to return position no matter what because camera uses it for its position!!!
        return this.position;
    }
}
