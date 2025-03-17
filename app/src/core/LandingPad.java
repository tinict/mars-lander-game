package core;

import modules.DrawGraphic.StdDraw;

import java.util.Scanner;

public class LandingPad {

    private static int x;
    private static int width;

    public static void start(Scanner input) {
        LandingPad.x = input.nextInt();
        LandingPad.width = input.nextInt();
    }

    public static void draw() {
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledRectangle(x, Scene.getHeight() - 20, width, 5);
    }

    public static boolean isTouching() {
        boolean isTouching;
        isTouching = Math.abs(Player.getX() - LandingPad.x) < LandingPad.width;
        return isTouching;
    }

    public static double getX() {
        return x;
    }

    public static double getWidth() {
        return width;
    }
}
