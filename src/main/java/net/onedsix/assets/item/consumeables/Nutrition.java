package net.onedsix.assets.item.consumeables;

public class Nutrition {

	/**
	 * An arcade style nutrition system. The preferred system for nutrition.
	 * */
	public class ArcadeNutrition implements AbstractNutrition {
		public long fruits = 50;
		public long[] fruitBounds = {25, 75};
		public long grains = 50;
		public long[] grainBounds = {0, 100};
		public long vegetables = 50;
		public long[] vegetableBounds = {Long.MIN_VALUE, Long.MAX_VALUE};
		public long protein = 50;
		public long[] proteinBounds = {20, 90};
		public long dairy = 50;
		public long[] dairyBounds = {0, 50};

		public boolean[] checkNutrition() {
			boolean[] out = {true, true, true, true, true};
			if (notWithinBounds(this.fruits, fruitBounds)) {out[0] = false;}
			if (notWithinBounds(this.grains, grainBounds)) {out[1] = false;}
			if (notWithinBounds(this.vegetables, vegetableBounds)) {out[2] = false;}
			if (notWithinBounds(this.protein, proteinBounds)) {out[3] = false;}
			if (notWithinBounds(this.dairy, dairyBounds)) {out[4] = false;}
			return out;
		}

		private boolean notWithinBounds(long value, long[] bounds) {
			return value < bounds[0] || value > bounds[1];
		}
	}

	/**
	 * A semi-realistic nutrition system, counting individual vitamins, minerals, and calories.<br>
	 * Not recommended for most use cases, as its far too complex for most campaigns.
	 * */
	public class RealisticNutrition implements AbstractNutrition {
	}

	public interface AbstractNutrition {
	}
}
