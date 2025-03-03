package core;

import java.util.*;

import modules.DrawGraphic.StdDraw;

public class Scene {
    private static int width;
    private static int height;
    private static String image;

    public static int getHeight() {
        return height;
    }

    public static void start(Scanner input) {
        Scene.width = input.nextInt();
        Scene.height = input.nextInt();
        Scene.image = "src/resources/assets/" + input.next();

        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0.0, width);
        StdDraw.setYscale(height, 0.0);
    }

    public static void draw() {
        StdDraw.picture((double) width / 2, (double) height / 2, image);
    }
}
