package net.onedsix.assets.entities;

import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.math.Vector3;
import net.onedsix.registry.Identifier;
import net.onedsix.assets.Attributes;

/** An interactive object with no extra abailities */
public abstract class DumbEntity extends Entity {
	public DumbEntity(Identifier<? extends Entity> ident, Attributes attr, Decal img, Vector3 position) {
		super(ident, attr, img, position);
	}
}
