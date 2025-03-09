package core;

import java.util.*;

public class Physics {
    private static double velocityX;
    private static double velocityY;
    private static double gravity;
    private static double maxSurvivableVelocity;
    private static double thrust;

    public static void start(Scanner input) {
        velocityX = 0.0;
        velocityY = 0.0;
        gravity = input.nextDouble();
        maxSurvivableVelocity = input.nextDouble();
        thrust = input.nextDouble();
    }

    public static void update() {
        velocityY -= gravity;
    }

    public static double getVelocityX() {
        return velocityX;
    }

    public static double getVelocityY() {
        return velocityY;
    }

    public static void thrustUp() {
        velocityY -= thrust;
    }

    public static void thrustRight() {
        velocityX += thrust;
    }

    public static void thrustLeft() {
        velocityX -= thrust;
    }

    public static boolean isSurvivableSpeed() {
        return Math.abs(velocityY) < maxSurvivableVelocity;
    }
}
