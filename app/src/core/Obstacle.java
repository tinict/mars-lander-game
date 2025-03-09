package core;

import modules.DrawGraphic.StdDraw;

import java.util.Random;

public class Obstacle {
    private double x;
    private double y;
    private double width;
    private double height;
    private boolean active;

    public Obstacle(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.active = true;
    }

    public void draw() {
        if (active) {
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.filledRectangle(x, y, width, height);
        }
    }

    public boolean checkCollision(double playerX, double playerY, double playerWidth, double playerHeight) {
        if (!active) return false;

        boolean collisionX = playerX + playerWidth / 2 > x - width &&
                playerX - playerWidth / 2 < x + width;
        boolean collisionY = playerY + playerHeight / 2 > y - height &&
                playerY - playerHeight / 2 < y + height;

        return collisionX && collisionY;
    }

    public void setInactive() {
        active = false;
    }

    public static Obstacle createRandomObstacle(int sceneWidth, int sceneHeight) {
        Random rand = new Random();
        double x, y, width, height;
        boolean validPosition;

        do {
            x = rand.nextDouble() * (sceneWidth - 40) + 20;
            y = rand.nextDouble() * (sceneHeight - 100) + 50;
            width = rand.nextDouble() * 30 + 10;
            height = rand.nextDouble() * 30 + 10;

            // Kiểm tra không chồng lấp với LandingPad
            validPosition = Math.abs(x - LandingPad.getX()) > (LandingPad.getWidth() + width);
        } while (!validPosition);

        return new Obstacle(x, y, width, height);
    }
}