package core;

import java.util.*;

import modules.DrawGraphic.StdDraw;

public class Player {
    private static String shipImage;
    private static String thrusterUpImage;
    private static String thrusterLeftImage;
    private static String thrusterRightImage;
    private static String shipLandedImage;
    private static String shipCrashedImage;

    private static String sprite;

    private static int width;
    private static int height;

    private static double x;
    private static double y;

    public static double getX() {
        return x;
    }

    public static double getWidth() {
        return width;
    }

    public static double getHeight() {
        return height;
    }

    public static double getY() {
        return y;
    }

    public static String getShipCrashedImage() {
        return shipCrashedImage;
    }

    public static void setSprite(String newSprite) {
        sprite = newSprite;
    }

    private static int fuel;

    public static void start(Scanner input) {
        shipImage = "D:/prm25/mars-lander-game/app/src/resources/assets/" + input.next();
        thrusterUpImage = "D:/prm25/mars-lander-game/app/src/resources/assets/" + input.next();
        thrusterLeftImage = "D:/prm25/mars-lander-game/app/src/resources/assets/" + input.next();
        thrusterRightImage = "D:/prm25/mars-lander-game/app/src/resources/assets//" + input.next();
        shipLandedImage = "D:/prm25/mars-lander-game/app/src/resources/assets/" + input.next();
        shipCrashedImage = "D:/prm25/mars-lander-game/app/src/resources/assets/" + input.next();

        sprite = shipImage;

        width = input.nextInt();
        height = input.nextInt();

        x = input.nextDouble();
        y = input.nextDouble();

        fuel = input.nextInt();
    }

    public static void draw() {
        StdDraw.picture(x, y, sprite);
    }

    public static void update() {
        Player.move();
        Physics.update();
        y += Physics.getVelocityY();
        x += Physics.getVelocityX();

        if (y + height >= Scene.getHeight() - 15) {
            if (LandingPad.isTouching() && Physics.isSurvivableSpeed()) {
                sprite = shipLandedImage;
                Game.setGameOver(true);
            } else {
                sprite = shipCrashedImage;
                Game.setGameOver(true);
            }
        }
    }

    public static void move() {
        if (StdDraw.hasNextKeyTyped()) {
            char ch = StdDraw.nextKeyTyped();
            if (ch == 'w') {
                Physics.thrustUp();
                sprite = thrusterUpImage;
                fuel--;
            } else if (ch == 'd') {
                Physics.thrustLeft();
                sprite = thrusterRightImage;
                fuel--;
            } else if (ch == 'a') {
                Physics.thrustRight();
                sprite = thrusterLeftImage; 
                fuel--;
            }
        } else {
            sprite = shipImage;
        }
    }

    public static int getFuel() {
        return fuel;
    }
}
