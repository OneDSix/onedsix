package net.onedsix.util;

import lombok.Data;

/** A pair of two associated non-key values. Mostly used for returning multiple values at once. */
@Data
public class Pair<K, T> {
	public final K k;
	public final T t;
}
