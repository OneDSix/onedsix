package net.onedsix.registry;

import lombok.Data;
import net.onedsix.assets.Asset;

@Data
public class Identifier<T extends Asset> {

    public final Class<T> clazz;
    public final String modid;

    public Identifier(Class<T> clazz, String modid) {
        this.clazz = clazz;
        this.modid = modid;
    }

	public String toString() {
		return modid+":"+clazz.toString();
	}
}
