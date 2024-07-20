package net.onedsix.assets.entities;

import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.math.Vector3;
import net.onedsix.assets.Attributes;
import net.onedsix.registry.Identifier;

public abstract class PlayerEntity extends Entity {
	public PlayerEntity(Identifier<? extends Entity> ident, Attributes attr, Decal img, Vector3 position) {
		super(ident, attr, img, position);
	}
}
