// ID - 318969748

/*
 * Osher Elhadad
 *
 * This file defines an CountdownAnimation that sleeps and counts down on the screen.
 */

package games.animations;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import games.GameLevel;
import sprites.shelpers.SpriteCollection;

import java.awt.Color;

/**
 * This CountdownAnimation class display the gameScreen and sleeps and count down,
 * implements the Animation interface.
 * The CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) seconds, before
 * it is replaced with the next one.
 *
 * @version 1.00 22 May 2021
 * @author Osher Elhadad
 */
public class CountdownAnimation implements Animation {

    // The number of seconds we count down.
    private double numOfSeconds;

    // The number we start from to count down.
    private int countFrom;

    // The sprites we shows on the screen while the count down.
    private SpriteCollection gameScreen;

    // The sleeper we use to sleep the game.
    private Sleeper sleeper;

    // The milliseconds per number we count down.
    private final int millisecondsPerFrame;

    // The number we start from to count down.
    private final int startFrom;

    // The background color of the game.
    private Color background;

    // FONT_SIZE is the default size of the font of the numbers.
    public static final int FONT_SIZE = 40;

    // The number we divide with to get the quarter.
    public static final int QUARTER = 4;

    // The last number of the count down.
    public static final int END = -1;

    /**
     * CountdownAnimation is the constructor method.
     * it sets the numOfSeconds, countFrom, gameScreen, millisecondsPerFrame, heightLocation and background.
     *
     * @param numOfSeconds is the number of seconds we count down.
     * @param countFrom is the number we start from to count down.
     * @param gameScreen is the sprites we shows on the screen while the count down.
     * @param background is the background color of the game.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen, Color background) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.startFrom = countFrom;
        this.gameScreen = gameScreen;

        // Each number will appear on the screen for (numOfSeconds / countFrom) seconds.
        this.millisecondsPerFrame = (int) (AnimationRunner.MILLI_SECONDS / (this.countFrom / this.numOfSeconds));
        this.background = background;
        this.sleeper = new Sleeper();
    }

    @Override
    public void doOneFrame(DrawSurface d) {

        // Draws the game on the screen and sets the color of the numbers to be opposite from the background color.
        this.gameScreen.drawAllOn(d);
        if (this.background.equals(Color.BLACK)) {
            d.setColor(Color.WHITE);
        } else {
            d.setColor(Color.BLACK);
        }

        /*
         * Draws the number on the screen.
         * The vertical location of the numbers is: half from the screen width - quarter to the font size.
         * The horizontal location of the numbers is 3/5 from the height of the screen.
         */
        d.drawText((d.getWidth() / GameLevel.HALF) - FONT_SIZE / QUARTER,
                (d.getHeight() * 3) / 5, "" + this.countFrom, FONT_SIZE);

        // Starts to sleeps from the second number.
        if (this.countFrom != this.startFrom) {
            long startTime = System.currentTimeMillis();
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = this.millisecondsPerFrame - usedTime;

            // Sleeps for millisecondsPerFrame milliseconds for every number.
            while (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
                usedTime = System.currentTimeMillis() - startTime;
                milliSecondLeftToSleep = this.millisecondsPerFrame - usedTime;
            }
        }
        this.countFrom--;
    }

    @Override
    public boolean shouldStop() {
        return (this.countFrom == END);
    }
}
