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

    //ship   dimensions
    private static int width;
    private static int height;

    //ship   initial   position:
    private static double x;
    private static double y;

    public static double getX() {
        return x;
    }


    // New Feature
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

    //Ship   fuel
    private static int fuel;

    public static void start(Scanner input) {
        //Read   the   ship   art   assets
        shipImage = "D:/prm25/mars-lander-game/app/src/resources/assets/" + input.next(); // This is reading image filenames
        thrusterUpImage = "D:/prm25/mars-lander-game/app/src/resources/assets/" + input.next();
        thrusterLeftImage = "D:/prm25/mars-lander-game/app/src/resources/assets/" + input.next();
        thrusterRightImage = "D:/prm25/mars-lander-game/app/src/resources/assets//" + input.next();
        shipLandedImage = "D:/prm25/mars-lander-game/app/src/resources/assets/" + input.next();
        shipCrashedImage = "D:/prm25/mars-lander-game/app/src/resources/assets/" + input.next();

        //set   current   image
        sprite = shipImage;

        //Read   the   ship   dimensions
        width = input.nextInt();
        height = input.nextInt();

        //Read   the   ship   initial   position:
        System.out.println("Debug: Đang chờ input (vị trí x y)..."); // Thêm debug
        x = input.nextDouble(); // Dòng 45 (giả định)
        y = input.nextDouble();
        System.out.println("Debug: Vị trí x=" + x + ", y=" + y);

        //Read   the   ship   fuel
        fuel = input.nextInt();
    }

    public static void draw() {
        //Draw   Ship   to   Scene
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
                Physics.thrustRight();
                sprite = thrusterRightImage; // Sang phải dùng thrusterRightImage
                fuel--;
            } else if (ch == 'a') {
                Physics.thrustLeft();
                sprite = thrusterLeftImage; // Sang trái dùng thrusterLeftImage
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
