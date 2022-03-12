
/*
 * Osher Elhadad
 *
 * This file creates a game and starts it.
 */

import biuoop.GUI;
import biuoop.Sleeper;
import games.GameLevel;
import games.animations.AnimationRunner;
import games.animations.GameFlow;
import games.levels.DirectHit;
import games.levels.Green3;
import games.levels.FinalFour;
import games.levels.WideEasy;
import games.levels.LevelInformation;
import geometry.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * This Ass6Game class has main function that creates a game and starts it.
 *
 * @version 1.00 22 May 2021
 * @author Osher Elhadad
 */
public class Ass6Game {

    // The default width of the screen.
    public static final int WIDTH = 800;

    // The default height of the screen.
    public static final int HEIGHT = 600;

    // The default width of the borders.
    public static final int BORDER_WIDTH = 25;

    // The default number of lives.
    public static final int NUMBER_OF_LIVES = 7;

    // The number of levels.
    public static final int NUMBER_OF_LEVELS = 4;

    // Level 1 in the game.
    public static final int DIRECT_HIT = 1;

    // Level 2 in the game.
    public static final int WIDE_EASY = 2;

    // Level 3 in the game.
    public static final int GREEN3 = 3;

    // Level 4 in the game.
    public static final int FINAL_FOUR = 4;

    // The default name of the game screen.
    public static final String TITLE = "Arkanoid";

    /**
     * This is the main function.
     * it creates the game and shows it on the screen.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {

        // Creates the gui screen.
        GUI gui = new GUI(TITLE, WIDTH, HEIGHT);

        GameFlow gameFlow = new GameFlow(new AnimationRunner(gui, GameLevel.FRAMES_PER_SECOND, new Sleeper()),
                gui.getKeyboardSensor(), BORDER_WIDTH, NUMBER_OF_LIVES);

        /*
         * Adds a new list that has only the valid levels.
         */
        List<Integer> levelNumbers = new ArrayList<>();
        for (String arg : args) {
            if (Point.isInteger(arg) && Integer.parseInt(arg) > 0
                    && Integer.parseInt(arg) <= NUMBER_OF_LEVELS) {
                levelNumbers.add(Integer.parseInt(arg));
            }
        }

        /*
         * If the new list is empty we run the all levels in their order.
         */
        List<LevelInformation> levels = new ArrayList<>();
        if (levelNumbers.size() == 0) {
            levels.add(new DirectHit());
            levels.add(new WideEasy());
            levels.add(new Green3());
            levels.add(new FinalFour());
        } else {

            /*
             * Adds to the levels list the levels by the order they appeared.
             */
            for (int levelNum : levelNumbers) {
                switch (levelNum) {
                    case DIRECT_HIT:
                        levels.add(new DirectHit());
                        break;
                    case WIDE_EASY:
                        levels.add(new WideEasy());
                        break;
                    case GREEN3:
                        levels.add(new Green3());
                        break;
                    case FINAL_FOUR:
                        levels.add(new FinalFour());
                        break;
                    default:
                        break;
                }
            }
        }

        // Run the game on the screen.
        gameFlow.runLevels(levels);
        gui.close();
    }
}
