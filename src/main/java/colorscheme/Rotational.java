package colorscheme;

import java.awt.Color;

class Rotational implements ColorScheme {
	private final int sections;

	private final float hueOffset;
	private final float saturationOffset;
	private final float blacknessOffset;

	public Rotational(int sections) {
		this(sections, 0, 0, 0);
	}

	public Rotational(int sections, float hueOffset, float saturationOffset,
					  float blacknessOffset) {
		this.sections = sections;

		this.hueOffset = hueOffset;
		this.saturationOffset = saturationOffset;
		this.blacknessOffset = blacknessOffset;
	}

	public Color[] generate(int count, float hue, float saturation,
							float blackness) {
        Color colors[] = new Color[count];
        colors[0] = Color.getHSBColor(hue, saturation, blackness);

		for (int i = 1; i < count; i++) {
			hue += 1f / sections;  // move round the color wheel
			hue %= 1;      		   // bind to inside of color wheel

			// apply offsets after each full rotation
			if (i % sections == 0) {
				hue += hueOffset;
				saturation += saturationOffset;
				blackness += blacknessOffset;
			}

        	colors[i] = Color.getHSBColor(hue, saturation, blackness);
		}

        return colors;
	}
}
