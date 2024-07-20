package net.onedsix.assets.entities;

import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.math.Vector3;
import net.onedsix.registry.Identifier;
import net.onedsix.assets.Attributes;

/** An intractable object with the ability to make it's own decisions, usually using. */
public abstract class SmartEntity extends Entity implements Brain {
	public SmartEntity(Identifier<? extends Entity> ident, Attributes attr, Decal img, Vector3 position) {
		super(ident, attr, img, position);
	}
}
