package colorscheme;

import java.awt.Color;

public class Analogous implements ColorScheme {
    private final float offset;

    public Analogous(float offset) {
        this.offset = offset;
    }

    public Color[] generate(int count, float hue, float saturation,
                            float blackness) {
        Color[] colors = new Color[count];

        float bounds = (float) (count - 1) / 2;
        for (int i = 0; i < count; i++) {
            float factor = i - bounds;
            colors[i] = Color.getHSBColor(hue + factor * offset,
                                          saturation, blackness); 
        }

        return colors;
    }
}
