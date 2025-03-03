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

    //Ship   fuel
    private static int fuel;

    public static void start(Scanner input) {
        //Read   the   ship   art   assets
        shipImage = "src/resources/assets/" + input.next(); // This is reading image filenames
        thrusterUpImage = "src/resources/assets/" + input.next();
        thrusterLeftImage = "src/resources/assets/" + input.next();
        thrusterRightImage = "src/resources/assets/" + input.next();
        shipLandedImage = "src/resources/assets/" + input.next();
        shipCrashedImage = "src/resources/assets/" + input.next();

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

        if ((y + height) > (Scene.getHeight() - 15)) {
            if (LandingPad.isTouching() && Physics.isSurvivableSpeed()) {
                sprite = shipLandedImage;
            } else {
                sprite = shipCrashedImage;
            }

            Game.setGameOver(true);
        }
    }

    public static void move() {
        if (StdDraw.hasNextKeyTyped()) {
            char ch = StdDraw.nextKeyTyped();
            if (ch == 'w') {
                Physics.thrustUp();                //   Go   faster   upwards
                sprite = thrusterUpImage;          //Set   image   for   up   thruster
                fuel--;
            } else if (ch == 'd') {
                Physics.thrustRight();             //   Go   faster   right
                sprite = thrusterLeftImage;
                fuel--;
            } else if (ch == 'a') {
                Physics.thrustLeft();             //   Go   faster   left
                sprite = thrusterRightImage;
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
