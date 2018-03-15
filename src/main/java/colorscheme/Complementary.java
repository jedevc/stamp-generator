package colorscheme;

import java.awt.Color;

public class Complementary extends Rotational {
    public Complementary() {
        this(0, 0, 0);
    }

    public Complementary(float hueOffset, float saturationOffset,
                         float blacknessOffset) {
        super(2, hueOffset, saturationOffset, blacknessOffset);
    }
}
