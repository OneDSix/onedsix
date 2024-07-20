package net.onedsix.assets.attributes;

import net.onedsix.assets.Attribute;
import net.onedsix.registry.Identifier;
import net.onedsix.util.Vars;

import java.util.LinkedList;

public class ExpirenceAttribute extends Attribute<Float> {
	public ExpirenceAttribute(float xp) {
		super(
			new Identifier<>(LevelAttribute.class, Vars.ONEDSIX_ID),
			new LinkedList<>(),
			xp
		);
	}
}
