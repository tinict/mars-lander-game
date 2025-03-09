package core;

import java.util.*;

import modules.DrawGraphic.StdDraw;

public class Game {
    private static boolean gameOver;
    private static ArrayList<Obstacle> obstacles; // Danh sách chướng ngại vật

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

        // Tạo ngẫu nhiên 3-5 chướng ngại vật
        Random rand = new Random();
        int obstacleCount = rand.nextInt(3) + 3; // Số lượng từ 3-5
        for (int i = 0; i < obstacleCount; i++) {
            obstacles.add(Obstacle.createRandomObstacle(Scene.getWidth(), Scene.getHeight()));
        }
    }

    public static void update() {
        double playerX = Player.getX();
        double playerY = Player.getY();
        double playerWidth = Player.getWidth();
        double playerHeight = Player.getHeight();

        // Kiểm tra va chạm trước
        if (!gameOver) {
            for (Obstacle obstacle : obstacles) {
                if (obstacle.checkCollision(playerX, playerY, playerWidth, playerHeight)) {
                    Player.setSprite(Player.getShipCrashedImage());
                    setGameOver(true);
                    break;
                }
            }
        }

        // Chỉ cập nhật nếu chưa game over
        if (!gameOver) {
            Player.update();
        }
    }

    public static void render() {
        Scene.draw();
        Player.draw();
        LandingPad.draw();
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(); // Vẽ tất cả chướng ngại vật
        }
        Hud.draw();
        StdDraw.show(100);
    }

    // Getter cho Scene width (thêm vào lớp Scene)
    public static int getSceneWidth() {
        return Scene.getWidth();
    }

    public static int getSceneHeight() {
        return Scene.getHeight();
    }
}