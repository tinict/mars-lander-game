package core;

import java.util.*;

import modules.DrawGraphic.StdDraw;

public class Game {
    private static boolean gameOver;
    private static ArrayList<Obstacle> obstacles;

    public static void setGameOver(boolean gameOver) {
        Game.gameOver = gameOver;
    }

    public static void main(String[] args) {
        start();
        while (!gameOver) {
            update();
            render();
        }
    }

    public static void start() {
        gameOver = false;
        obstacles = new ArrayList<>();

        Scanner input = new Scanner(System.in);

        Scene.start(input);
        Player.start(input);
        Physics.start(input);
        LandingPad.start(input);

        Random rand = new Random();
        int obstacleCount = rand.nextInt(3) + 3;
        for (int i = 0; i < obstacleCount; i++) {
            obstacles.add(Obstacle.createRandomObstacle(Scene.getWidth(), Scene.getHeight()));
        }
    }

    public static void update() {
        double playerX = Player.getX();
        double playerY = Player.getY();
        double playerWidth = Player.getWidth();
        double playerHeight = Player.getHeight();

        if(Player.getFuel() == 0) {
            Player.setSprite(Player.getShipCrashedImage());
            setGameOver(true);
        }

        if (!gameOver) {
            for (Obstacle obstacle : obstacles) {
                if (obstacle.checkCollision(playerX, playerY, playerWidth, playerHeight)) {
                    Player.setSprite(Player.getShipCrashedImage());
                    setGameOver(true);
                    break;
                }
            }
        }

        if (!gameOver) {
            Player.update();
        }
    }

    public static void render() {
        Scene.draw();
        Player.draw();
        LandingPad.draw();
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(); 
            // obstacle.setInactive();
        }
        Hud.draw();
        StdDraw.show(100);
    }

    public static int getSceneWidth() {
        return Scene.getWidth();
    }

    public static int getSceneHeight() {
        return Scene.getHeight();
    }
}