package core;

import java.util.*;

import modules.DrawGraphic.StdDraw;

/**
 * Description: Class Game should algorithm and actions as start, render, update, ...
 * Functionality: start, render, update
 * Init date: 03/03/2025
 * Author: Tinmy Nguyen
 */
public class Game {
    private static boolean gameOver;

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

        Scanner input = new Scanner(System.in);

        Scene.start(input);
        Player.start(input);
        Physics.start(input);
        LandingPad.start(input);
    }

    public static void update() {
        Player.update();
    }

    public static void render() {
        Scene.draw();
        Player.draw();
        LandingPad.draw();
        Hud.draw();
        StdDraw.show(100);
    }
}
