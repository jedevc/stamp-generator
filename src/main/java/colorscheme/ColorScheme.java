package colorscheme;

import java.awt.Color;

public interface ColorScheme {
    public Color[] generate(int count, float hue, float saturation,
            float blackness);

    public default Color[] generate(int count, Color base) {
        // FIXME: surely an easier way to do this?
        float[] hsb = base.RGBtoHSB(base.getRed(),
                                    base.getGreen(),
                                    base.getBlue(),
                                    null);

        return generate(count, hsb[0], hsb[1], hsb[2]);
    }
}
