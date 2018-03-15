package colorscheme;

import java.awt.Color;

public class Triadic extends Rotational {
    public Triadic() {
        this(0, 0, 0);
    }

    public Triadic(float hueOffset, float saturationOffset,
                   float blacknessOffset) {
        super(3, hueOffset, saturationOffset, blacknessOffset);
    }
}
