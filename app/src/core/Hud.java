package core;

import modules.DrawGraphic.StdDraw;

public class Hud {
    public static void draw() {
        StdDraw.text(50, 20, "Fuel:  " + Player.getFuel());
    }
}
